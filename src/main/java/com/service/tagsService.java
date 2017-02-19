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
    SqlSession session = DBtool.getSession();

    public List<Tag> getTagsByUser(User user){
        String user_name=user.getUser_name();
        return session.selectList("getTagsByUsername",user_name);
    }
}
