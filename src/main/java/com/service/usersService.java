package com.service;

import com.domain.User;

import java.util.List;

/**
 * Created by Ma on 17/3/10.
 */
public interface usersService {

    public List<User> getAllUsers();

    public User checkUser(String username,String password);

    public User getUserByName(String user_name);

    public void insertUser(User u);
}
