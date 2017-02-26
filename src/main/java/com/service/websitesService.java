package com.service;

import com.domain.Website;
import com.tools.DBtool;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by Administrator on 2017/2/18.
 */
public class websitesService {

    public List<Website> getAllWebsites(){
        return DBtool.getSession().selectList("getAllWebsites",1);
    }

    public void insertWebsite(Website w){
        DBtool.getSession().insert("insertWebsite",w);
        DBtool.getSession().commit();
    }

    public List<Website> getWebsitesByTagid(Integer tag_id){
        return DBtool.getSession().selectList("getWebsitesByTagid",tag_id);
    }


    public void deleteWebsite(Integer web_id){
        DBtool.getSession().delete("deleteWebsite",web_id);
        DBtool.getSession().commit();
    }

    public Website getWebsiteById(Integer web_id){
        return DBtool.getSession().selectOne("getWebsiteById",web_id);
    }

    public void updateWebsite(Website w){
        DBtool.getSession().update("updateWebsite",w);
        DBtool.getSession().commit();
    }

}
