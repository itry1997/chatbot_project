package cn.bugstack.chatbot.api.domain.zsxq.model.vo;

/**
 * @author cui
 * @create 2023--03--31--16:05
 */
public class Group
{
    private String group_id;

    private String name;

    private String type;

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
}
