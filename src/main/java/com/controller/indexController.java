package com.controller;

import com.domain.User;
import com.domain.Website;
import com.tools.DBtool;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Administrator on 2017/2/16.
 */

@Controller
public class indexController {


    @RequestMapping("/hello")
    public ModelAndView helloWorld() {
        SqlSession session = DBtool.getSession();
        String message = "Website names are: ";
        try {
            List<Website> websites = session.selectList("getAllWebsites",1);
            List<User> users=session.selectList("getAllUsers",1);
            Website we=new Website();
            we.setName("sadwa");
            we.setId(4);
            System.out.println(we.getName());
 //           session.insert("insertWebsite",we);
 //           session.commit();
            for (Website w:websites) {
                message=message.concat(w.getName());
            }
            for(User u:users){
                message=message.concat(u.getUser_name());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return new ModelAndView("login", "login", message);
    }

}
