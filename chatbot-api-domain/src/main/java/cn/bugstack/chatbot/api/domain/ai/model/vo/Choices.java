package cn.bugstack.chatbot.api.domain.ai.model.vo;

/**
 * @author cui
 * @create 2023--04--02--9:42
 */
//public class Choices {
//    private String text;
//
//    private int index;
//
//    private String logprobs;
//
//    private String finish_reason;
//
//    public String getText() {
//        return text;
//    }
//
//    public void setText(String text) {
//        this.text = text;
//    }
//
//    public int getIndex() {
//        return index;
//    }
//
//    public void setIndex(int index) {
//        this.index = index;
//    }
//
//    public String getLogprobs() {
//        return logprobs;
//    }
//
//    public void setLogprobs(String logprobs) {
//        this.logprobs = logprobs;
//    }
//
//    public String getFinish_reason() {
//        return finish_reason;
//    }
//
//    public void setFinish_reason(String finish_reason) {
//        this.finish_reason = finish_reason;
//    }
//
//}
public class Choices
{
    private Message message;

    private String finish_reason;

    private int index;

    public void setMessage(Message message){
        this.message = message;
    }
    public Message getMessage(){
        return this.message;
    }
    public void setFinish_reason(String finish_reason){
        this.finish_reason = finish_reason;
    }
    public String getFinish_reason(){
        return this.finish_reason;
    }
    public void setIndex(int index){
        this.index = index;
    }
    public int getIndex(){
        return this.index;
    }
}
