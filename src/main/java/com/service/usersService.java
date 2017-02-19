package com.service;

import com.domain.User;
import com.tools.DBtool;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by Administrator on 2017/2/18.
 */
public class usersService {
    SqlSession session = DBtool.getSession();

    public List<User> getAllUsers(){
        return session.selectList("getAllUsers",1);
    }

    public User checkUser(String username,String password){
        User u=new User(username,password);
        return session.selectOne("checkUser",u);
    }

    public User getUserByName(String username){
        return session.selectOne("getUserByName",username);
    }
}
