package com.service;

import com.domain.Tag;
import com.domain.User;
import com.tools.DBtool;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by Administrator on 2017/2/18.
 */
public class tagsService {

    public List<Tag> getTagsByUser(User user){
        String user_name=user.getUser_name();
        return DBtool.getSession().selectList("getTagsByUsername",user_name);
    }

    public void insertTag(Tag t){
        DBtool.getSession().insert("insertTag",t);
        DBtool.getSession().commit();
    }

    public void deleteTag(Integer tag_id){
        DBtool.getSession().delete("deleteTag",tag_id);
        DBtool.getSession().commit();
    }

    public Tag getTagById(Integer tag_id){
        return DBtool.getSession().selectOne("getTagById",tag_id);
    }

    public void updateTag(Tag t){
        DBtool.getSession().update("updateTag",t);
        DBtool.getSession().commit();
    }

}
