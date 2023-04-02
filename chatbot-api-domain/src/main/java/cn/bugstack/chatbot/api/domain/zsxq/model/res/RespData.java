package cn.bugstack.chatbot.api.domain.zsxq.model.res;

import cn.bugstack.chatbot.api.domain.zsxq.model.vo.Topics;

import java.util.List;

/**
 * @author cui
 * @create 2023--03--31--16:19
 */
public class RespData {

    private List<Topics> topics;

    public List<Topics> getTopics() {
        return topics;
    }

    public void setTopics(List<Topics> topics) {
        this.topics = topics;
    }

}
