package com.controller;

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
import java.util.List;

/**
 * Created by Administrator on 2017/2/16.
 */

@Controller
@RequestMapping("/home")
public class loginController {
    private tagsService tagsService=new tagsService();
    private usersService usersService=new usersService();
    private websitesService websitesService=new websitesService();


    @RequestMapping("/userlogin.do")
    public ModelAndView welcome(HttpServletRequest request,
                                @RequestParam(required = false) Integer type) {
        String name=cookieTool.checkCookie(request);
        if(name==""){
            if(type!=null&&type==0) {
                return new ModelAndView("login","message","wrong user name or password!");
            }else{
                return new ModelAndView("login");
            }
        }else{
            User thisuser=usersService.getUserByName(name);
            String message=thisuser.getNickname();
            return new ModelAndView("welcome","message",message);
        }
    }

    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    public String login(HttpServletRequest request,
                        HttpServletResponse response,
                        @RequestParam(required = true) String username,
                        @RequestParam(required = true) String password) {
        try{
            User testUser=usersService.checkUser(username,password);
            if(testUser==null){
                System.out.println("no!");
            }else{
                System.out.println("Yes!");
                Cookie cookie = new Cookie("curr_user", username);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }catch (Exception e){

        }
        return "redirect:/home/userlogin.do?type=0";
    }

}