package cn.bugstack.chatbot.api.application.job;

import cn.bugstack.chatbot.api.domain.ai.IOpenAI;
import cn.bugstack.chatbot.api.domain.zsxq.IZsxqApi;
import cn.bugstack.chatbot.api.domain.zsxq.model.aggregates.UnansweredQuestionsAggregates;
import cn.bugstack.chatbot.api.domain.zsxq.model.vo.Topics;
import com.alibaba.fastjson.JSON;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

/**
 * @author cui
 * @create 2023--04--02--11:31
 */
@EnableScheduling
@Configuration
public class ChatbotSchedule {

    private Logger logger = LoggerFactory.getLogger(ChatbotSchedule.class);

    @Value("${chatbot-api.groupId}")
    private String groupId;

    @Value("${chatbot-api.cookie}")
    private String cookie;

    @Resource
    private IZsxqApi zsxqApi;

    @Resource
    private IOpenAI openAI;

    @Scheduled(cron = "0/30 * * * * ?")
    public void run(){
        try {
            // 0、避免被风控
            if (new Random().nextBoolean()){
                logger.info("随机打烊中...");
                return;
            }

            GregorianCalendar calendar = new GregorianCalendar();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            if (hour > 22 || hour < 8){
                logger.info("非工作时间，不执行查询");
                return;
            }

            // 1、检索问题
            UnansweredQuestionsAggregates unansweredQuestionsAggregates = zsxqApi.queryUnansweredQuestionsTopicId(groupId, cookie);
            logger.info("待回答问题：{}",JSON.toJSONString(unansweredQuestionsAggregates));
            List<Topics> topics = unansweredQuestionsAggregates.getRespData().getTopics();
            if (null == topics || topics.isEmpty()){
                logger.info("本次检索未发现待回答的问题。");
                return;
            }
            // 2、AI回答
            Topics topic = topics.get(0);
            String answer = openAI.doChatGPT(topic.getQuestion().getText().trim());

            // 3、问题回复
            boolean status = zsxqApi.answer(groupId, cookie, topic.getTopic_id(), answer, false);
            logger.info("总体概况日志：编号：{} 问题：{} 回答:{} 状态：{}",topic.getTopic_id(),topic.getQuestion().getText(),answer,status);
        }catch (Exception e){
            logger.error("自动回答问题异常",e);
        }
    }
}
