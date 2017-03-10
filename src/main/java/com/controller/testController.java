package com.controller;

import com.domain.User;
import com.domain.Website;
import com.service.impl.tagsServiceImpl;
import com.service.impl.usersServiceImpl;
import com.service.impl.websitesServiceImpl;
import com.service.tagsService;
import com.service.usersService;
import com.service.websitesService;
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
public class testController {
    private tagsService tagsServiceImpl = new tagsServiceImpl();
    private usersService usersServiceImpl = new usersServiceImpl();
    private websitesService websitesServiceImpl = new websitesServiceImpl();

    @RequestMapping("/hello")
    public ModelAndView helloWorld() {
        SqlSession session = DBtool.getSession();
        String message = "Website names are: ";
        List<Website> websites= websitesServiceImpl.getAllWebsites();
 //       Website web=new Website("sina","www.sina.com",1,"Max");
        User u= usersServiceImpl.checkUser("Max","123");
        message=message.concat(u.getNickname());
        for(Website w:websites){
           message=message.concat(w.getWeb_name());
        }
        return new ModelAndView("hellopage", "message", message);
    }

}
