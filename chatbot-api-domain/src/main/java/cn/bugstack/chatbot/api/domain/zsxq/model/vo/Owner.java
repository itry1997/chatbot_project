package cn.bugstack.chatbot.api.domain.zsxq.model.vo;

/**
 * @author cui
 * @create 2023--03--31--16:05
 */
public class Owner
{
    private String user_id;

    private String name;

    private String avatar_url;

    private String location;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setAvatar_url(String avatar_url){
        this.avatar_url = avatar_url;
    }
    public String getAvatar_url(){
        return this.avatar_url;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public String getLocation(){
        return this.location;
    }
}
