package com.service;

import com.domain.User;
import com.tools.DBtool;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by Administrator on 2017/2/18.
 */
public class usersService {

    public List<User> getAllUsers(){
        return DBtool.getSession().selectList("getAllUsers",1);
    }

    public User checkUser(String username,String password){
        User u=new User(username,password);
        return DBtool.getSession().selectOne("checkUser",u);
    }

    public User getUserByName(String user_name){
        return DBtool.getSession().selectOne("getUserByName",user_name);
    }
}
