package com.controller;

import com.domain.User;
import com.domain.Website;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.Reader;
import java.util.List;

/**
 * Created by Administrator on 2017/2/16.
 */

@Controller
public class indexController {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static{
        try{
            reader    = Resources.getResourceAsReader("mybatis.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSession(){
        return sqlSessionFactory;
    }


    @RequestMapping("/hello")
    public ModelAndView helloWorld() {
        SqlSession session = sqlSessionFactory.openSession();
        String message = "Website names are: ";
        try {
            List<Website> websites = session.selectList("getAllWebsites", 1);
            List<User> users=session.selectList("getAllUsers",1);
            Website we=new Website();
            we.setName("sadwa");
            we.setId(4);
        //    session.insert("insertWebsite",we);
            for (Website w:websites) {
                message=message.concat(w.getName());
            }
            for(User u:users){
                message=message.concat(u.getUser_name());
            }
        } finally {
            session.close();
        }
        return new ModelAndView("hellopage", "message", message);
    }

}
