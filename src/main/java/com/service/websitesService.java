package com.service;

import com.domain.Website;
import com.tools.DBtool;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by Administrator on 2017/2/18.
 */
public class websitesService {
    SqlSession session = DBtool.getSession();

    public List<Website> getAllWebsites(){
        session = DBtool.getSession();
        return session.selectList("getAllWebsites",1);
    }

    public void insertWebsite(Website w){
        session.insert("insertWebsite",w);
        session.commit();
    }

}
