package cn.bugstack.chatbot.api.domain.ai.model.vo;

/**
 * @author cui
 * @create 2023--04--02--11:10
 */
public class Message {
    private String role;

    private String content;

    public void setRole(String role){
        this.role = role;
    }
    public String getRole(){
        return this.role;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return this.content;
    }
}
