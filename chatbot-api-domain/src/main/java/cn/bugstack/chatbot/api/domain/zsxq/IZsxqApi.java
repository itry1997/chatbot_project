package cn.bugstack.chatbot.api.domain.zsxq;

import cn.bugstack.chatbot.api.domain.zsxq.model.aggregates.UnansweredQuestionsAggregates;

import java.io.IOException;

/**
 * @author cui
 * @create 2023--03--31--15:55
 */
public interface IZsxqApi {
    UnansweredQuestionsAggregates queryUnansweredQuestionsTopicId(String groupId, String cookie) throws IOException;

    boolean answer(String groupId,String cookie,String topicId,String text,boolean silenced) throws IOException;
}
