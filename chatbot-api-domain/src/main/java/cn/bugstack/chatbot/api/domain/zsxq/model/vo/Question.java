package cn.bugstack.chatbot.api.domain.zsxq.model.vo;

/**
 * @author cui
 * @create 2023--03--31--16:06
 */
public class Question
{
    private Owner owner;

    private Questionee questionee;

    private String text;

    private boolean expired;

    private boolean anonymous;

    private OwnerDetail ownerDetail;

    private String owner_location;

    public void setOwner(Owner owner){
        this.owner = owner;
    }
    public Owner getOwner(){
        return this.owner;
    }
    public void setQuestionee(Questionee questionee){
        this.questionee = questionee;
    }
    public Questionee getQuestionee(){
        return this.questionee;
    }
    public void setText(String text){
        this.text = text;
    }
    public String getText(){
        return this.text;
    }
    public void setExpired(boolean expired){
        this.expired = expired;
    }
    public boolean getExpired(){
        return this.expired;
    }
    public void setAnonymous(boolean anonymous){
        this.anonymous = anonymous;
    }
    public boolean getAnonymous(){
        return this.anonymous;
    }
    public void setOwnerDetail(OwnerDetail ownerDetail){
        this.ownerDetail = ownerDetail;
    }
    public OwnerDetail getOwnerDetail(){
        return this.ownerDetail;
    }
    public void setOwner_location(String owner_location){
        this.owner_location = owner_location;
    }
    public String getOwner_location(){
        return this.owner_location;
    }
}
