package cn.bugstack.chatbot.api.domain.zsxq.service;

import cn.bugstack.chatbot.api.domain.zsxq.model.res.AnswerRes;
import net.sf.json.JSONObject;
import cn.bugstack.chatbot.api.domain.zsxq.IZsxqApi;
import cn.bugstack.chatbot.api.domain.zsxq.model.aggregates.UnansweredQuestionsAggregates;
import cn.bugstack.chatbot.api.domain.zsxq.model.req.AnswerReq;
import cn.bugstack.chatbot.api.domain.zsxq.model.req.ReqData;
import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.io.IOException;

/**
 * @author cui
 * @create 2023--03--31--16:36
 */
@Service
public class ZsxqApi implements IZsxqApi {
    // 日志
    private Logger logger = LoggerFactory.getLogger(ZsxqApi.class);

    @Override
    public UnansweredQuestionsAggregates queryUnansweredQuestionsTopicId(String groupId, String cookie) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/"+ groupId +"/topics?scope=unanswered_questions&count=20");
        get.addHeader("cookie",cookie);
//        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/28885822144141/topics?scope=unanswered_questions&count=20");
//        get.addHeader("cookie","__cuid=93e78872cbf94b94af3c4ed2b184d4cd; amp_fef1e8=e2b14c3b-213c-4c30-aa8b-90a985b81a09R...1gsqm3dar.1gsqm4qkd.2.1.3; zsxq_access_token=B227DD67-B121-FD77-FB66-6CAFDA78AD50_5B727D7E22D63632; abtest_env=product; zsxqsessionid=be28da9c61d55b0bc3b4cf4f418c6a8d; sensorsdata2015jssdkcross={\"distinct_id\":\"1873563883fd25-0c038b445d065d-7a545471-1327104-18735638840a7d\",\"first_id\":\"\",\"props\":{},\"$device_id\":\"1873563883fd25-0c038b445d065d-7a545471-1327104-18735638840a7d\"}; sajssdk_2015_cross_new_user=1");
        get.addHeader("Content-Type","application/json; charset=UTF-8");

        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String jsonStr = EntityUtils.toString(response.getEntity());
            logger.info("拉取提问数据：groupId:{} jsonStr:{}",groupId,jsonStr);
            return JSON.parseObject(jsonStr,UnansweredQuestionsAggregates.class);
        }else{
            throw new RuntimeException("queryUnansweredQuestionsTopicId Error Code is " + response.getStatusLine().getStatusCode());
        }
    }

    @Override
    public boolean answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // uri中的数字是 要回答的unanswered_questions的topic_id
        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/" + topicId + "/answer");
        post.addHeader("cookie",cookie);
        post.addHeader("Content-Type","application/json; charset=UTF-8");
        post.addHeader("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36 Edg/111.0.1661.54");

        // text中的文字是回答的内容
//        String paramJson = "{\n" +
//                "  \"req_data\": {\n" +
//                "    \"text\": \"建议去查看JavaGuide\\n\",\n" +
//                "    \"image_ids\": []\n" +
//                "  }\n" +
//                "}";

        AnswerReq answerReq = new AnswerReq(new ReqData(text,silenced));
        String paramJson = JSONObject.fromObject(answerReq).toString();

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json","UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String jsonStr = EntityUtils.toString(response.getEntity());
//            System.out.println(jsonStr);
            logger.info("回答问题的结果：groupId:{} topicId:{} jsonStr:{}",groupId,topicId,jsonStr);
            AnswerRes answerRes = JSON.parseObject(jsonStr, AnswerRes.class);
            return answerRes.isSucceeded();
        }else{
            throw new RuntimeException("answer Error Code is " + response.getStatusLine().getStatusCode());
        }
    }
}
