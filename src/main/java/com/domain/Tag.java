package com.domain;

/**
 * Created by Administrator on 2017/2/18.
 */
public class Tag {
    private int tag_id;
    private String tag_name="new tag";
    private String user_name;

    public Tag(){
    }

    public Tag(String user_name) {
        this.user_name = user_name;
    }

    public Tag(int tag_id,String tag_name, String user_name) {
        this.tag_id=tag_id;
        this.tag_name = tag_name;
        this.user_name = user_name;
    }

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
