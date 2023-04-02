package cn.bugstack.chatbot.api.domain.zsxq.model.req;

/**
 * @author cui
 * @create 2023--03--31--16:17
 */
public class AnswerReq {

    private ReqData reqData;

    public AnswerReq(ReqData req_data) {
        this.reqData = req_data;
    }

    public ReqData getReq_data() {
        return reqData;
    }

    public void setReq_data(ReqData req_data) {
        this.reqData = req_data;
    }

}

