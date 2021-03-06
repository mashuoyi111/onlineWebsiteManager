package com.controller;

import com.domain.User;
import com.service.impl.tagsServiceImpl;
import com.service.impl.usersServiceImpl;
import com.service.impl.websitesServiceImpl;
import com.service.tagsService;
import com.service.usersService;
import com.service.websitesService;
import com.tools.cookieTool;
import org.springframework.stereotype.Controller;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/2/16.
 */

@Controller
@RequestMapping("/home")
public class loginController {
    private tagsService tagsServiceImpl = new tagsServiceImpl();
    private usersService usersServiceImpl = new usersServiceImpl();
    private websitesService websitesServiceImpl = new websitesServiceImpl();


    @RequestMapping("/userlogin.do")
    public String loginpage(HttpServletRequest request,
                                @RequestParam(required = false) Integer type) {
        String name=cookieTool.checkUserNameFromCookie(request);
        if(name==""){
            if(type!=null&&type==0) {
                return "redirect:/login.jsp?type=0";
            }else if(type!=null&&type==-1){
                return "redirect:/login.jsp?type=-1";
            }
            else{
                return "redirect:/login.jsp";
            }
        }else{

            User user= usersServiceImpl.getUserByName(name);
            if(user!=null) {
                 return "redirect:/home/index.do";
            }else{
                return "redirect:/login.jsp";
            }
        }
    }

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public String login(HttpServletRequest request,
                        HttpServletResponse response,
                        @RequestParam(required = true) String username,
                        @RequestParam(required = true) String password) {
        String name=cookieTool.checkUserNameFromCookie(request);
        if(name!=""){
            return "redirect:/home/index.do";
        }
        try{
            String sha1Password= DigestUtils.shaHex(password);
            User testUser= usersServiceImpl.checkUser(username,sha1Password);
            if(testUser!=null){
                System.out.println("User: "+testUser.getUser_name()+" Just logged in");
                Cookie cookie = new Cookie("curr_user", username);
                cookie.setMaxAge(3600);
                cookie.setPath("/");
                response.addCookie(cookie);
            }else{
                System.out.println("Someone tried but failed to log in by using username:"+username+" and password:"+password);
            }
        }catch (Exception e){

        }
        return "redirect:/home/userlogin.do?type=0";
    }


    @RequestMapping("/userlogout.do")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        String name=cookieTool.checkUserNameFromCookie(request);
        System.out.println("User: "+name+" Just logged out");
        cookieTool.logoutCookie(request,response);
        return "redirect:/home/userlogin.do?type=-1";
    }


    @RequestMapping("/register.do")
    public String register(HttpServletRequest request,
                           @RequestParam(required = true) String username,
                           @RequestParam(required = true) String password,
                           @RequestParam(required = true) String confirmed_password,
                           @RequestParam(required = true) String nickname
                           ){
        if(username==null||password==null||confirmed_password==null||nickname==null){
            return "redirect:/register.jsp";
        }
        if(usersServiceImpl.getUserByName(username)!=null){
            return "redirect:/register.jsp?type=-1";
        }
        if(!password.equals(confirmed_password)){
            return "redirect:/register.jsp?type=0";
        }
        try {
            User u = new User(username, DigestUtils.shaHex(password), 0, nickname);
            usersServiceImpl.insertUser(u);
        }catch (Exception e){
            System.out.println("insert user with an unexpected error");
            return "redirect:/register.jsp";
        }
    return "redirect:/login.jsp?type=1";
    }
}