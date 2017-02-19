package com.controller;

import com.domain.Tag;
import com.domain.User;
import com.domain.Website;
import com.service.tagsService;
import com.service.usersService;
import com.service.websitesService;
import com.tools.DBtool;
import com.tools.cookieTool;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/16.
 */

@Controller
@RequestMapping("/home")
public class indexController {
    private tagsService tagsService = new tagsService();
    private usersService usersService = new usersService();
    private websitesService websitesService = new websitesService();

    @RequestMapping("/index.do")
    public ModelAndView welcome(HttpServletRequest request){
        String name= cookieTool.checkCookie(request);
        User user=usersService.getUserByName(name);
        List<Tag> tags=tagsService.getTagsByUser(user);
        List<Website> websites=new ArrayList<Website>();
        for(Tag t:tags){
            websites.addAll(websitesService.getWebsitesByTagid(t.getTag_id()));
        }
        String message=user.getNickname()+"  ";
        for(Website w:websites){
            message=message.concat(w.getWeb_name()+"  ");
        }

        ModelAndView mv=new ModelAndView("welcome","message",message);
        return mv;
    }



}