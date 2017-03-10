package com.service.impl;

import com.domain.Website;
import com.service.websitesService;
import com.tools.DBtool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/18.
 */
public class websitesServiceImpl implements websitesService {

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

    public void setWebsiteFav(Integer web_id){
        Website w=getWebsiteById(web_id);
        w.changeFav();
        updateWebsite(w);
    }

    public List<Website> searchWebsitesByName(String user_name,String web_name){
        Map<String,String> searchMap=new HashMap<String, String>();
        searchMap.put("user_name",user_name);
        searchMap.put("web_name",web_name);
        return DBtool.getSession().selectList("searchWebsitesByName",searchMap);
    }

}
