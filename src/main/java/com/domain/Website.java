package com.domain;

/**
 * Created by Administrator on 2017/2/16.
 */
public class Website {
    private int web_id;
    private String web_name="new website";
    private String web_comment;
    private String web_url="about:blank";
    private int fav=0;
    private int tag_id;
    private String user_name;

    public Website() {
    }

    public Website(int web_id, String web_name, String web_comment, String web_url, int fav, int tag_id, String user_name) {
        this.web_id = web_id;
        this.web_name = web_name;
        this.web_comment = web_comment;
        this.web_url = web_url;
        this.fav = fav;
        this.tag_id = tag_id;
        this.user_name = user_name;
    }

    public Website(String web_name, String web_url, int tag_id, String user_name) {
        this.web_name = web_name;
        this.web_url = web_url;
        this.tag_id = tag_id;
        this.user_name = user_name;
    }

    public int getWeb_id() {
        return web_id;
    }

    public String getWeb_name() {
        return web_name;
    }

    public String getWeb_comment() {
        return web_comment;
    }

    public String getWeb_url() {
        return web_url;
    }

    public int getFav() {
        return fav;
    }

    public int getTag_id() {
        return tag_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setWeb_id(int web_id) {
        this.web_id = web_id;
    }

    public void setWeb_name(String web_name) {
        this.web_name = web_name;
    }

    public void setWeb_comment(String web_comment) {
        this.web_comment = web_comment;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public void setFav(int fav) {
        this.fav = fav;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
