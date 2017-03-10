package com.controller;

import com.domain.User;
import com.domain.Website;
import com.service.impl.tagsServiceImpl;
import com.service.impl.usersServiceImpl;
import com.service.impl.websitesServiceImpl;
import com.service.tagsService;
import com.service.usersService;
import com.service.websitesService;
import com.tools.cookieTool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/2/16.
 */

@Controller
@RequestMapping("/home")
public class websitesController {
    private tagsService tagsServiceImpl = new tagsServiceImpl();
    private usersService usersServiceImpl = new usersServiceImpl();
    private websitesService websitesServiceImpl = new websitesServiceImpl();


    @RequestMapping(value = "/insertWebsite.do", method = RequestMethod.POST)
    public String  insertWebsite(HttpServletRequest request,
        @RequestParam(required = false) String webFav,
        @RequestParam(required = false) String webName,
        @RequestParam(required = false) String webUrl,
        @RequestParam(required = false) String webComment,
        @RequestParam(required = true) String userName,
        @RequestParam(required = true) Integer tagId){

        String name=cookieTool.checkUserNameFromCookie(request);
        if(name==""){
            return "redirect:expired.jsp";
        }
        User user= usersServiceImpl.getUserByName(userName);
        if(user!=null) {
            int fav=0;
            if(webFav.trim().toLowerCase().equals("yes")){
                fav=1;
            }
            if(webName==""){
                webName="new website";
            }
            if(webUrl==""){
                webUrl="about:blank";
            }
            Website w=new Website(fav,webName,webComment,webUrl,tagId,name);
            websitesServiceImpl.insertWebsite(w);
            return "redirect:/home/index.do?tagId="+tagId;
        }
        return "redirect:/home/index.do";
    }



    @RequestMapping(value = "/deleteWebsite.do", method = RequestMethod.POST)
    public String  deleteWebsite(HttpServletRequest request,
                                 @RequestParam(required = true) Integer webId) {
        String name=cookieTool.checkUserNameFromCookie(request);
        if(name==""){
            return "redirect:expired.jsp";
        }
        User user= usersServiceImpl.getUserByName(name);

        if(user!=null) {
            Website w= websitesServiceImpl.getWebsiteById(webId);
            Integer tagId=w.getTag_id();
            if(w!=null&&w.getUser_name().equals(name)){
                websitesServiceImpl.deleteWebsite(webId);
            }
            return "redirect:/home/index.do?tagId="+tagId;
        }
        return "redirect:/home/index.do";    }



    @RequestMapping(value = "/updateWebsite.do", method = RequestMethod.POST)
    public String  updateWebsite(HttpServletRequest request,
                                 @RequestParam(required = false) String webName,
                                 @RequestParam(required = false) String webUrl,
                                 @RequestParam(required = false) String webComment,
                                 @RequestParam(required = true) Integer webId) {
        String name=cookieTool.checkUserNameFromCookie(request);
        if(name==""){
            return "redirect:expired.jsp";
        }
        User user= usersServiceImpl.getUserByName(name);

        if(user!=null) {
            if(webName==""){
                webName="new website";
            }
            if(webUrl==""){
                webUrl="about:blank";
            }
            Website w= websitesServiceImpl.getWebsiteById(webId);
            Integer tagId=w.getTag_id();
            if(w!=null&&w.getUser_name().equals(name)){
                w.setWeb_name(webName);
                w.setWeb_url(webUrl);
                w.setWeb_comment(webComment);
                websitesServiceImpl.updateWebsite(w);
            }
            return "redirect:/home/index.do?tagId="+tagId;
        }
        return "redirect:/home/index.do";    }



    @RequestMapping(value = "/favWebsite.do", method = RequestMethod.POST)
    public String  favWebsite(HttpServletRequest request,
                                 @RequestParam(required = true) Integer webId,
                                 @RequestParam(required = false) String searchPage) {
        String name=cookieTool.checkUserNameFromCookie(request);
        if(name==""){
            return "redirect:expired.jsp";
        }
        User user= usersServiceImpl.getUserByName(name);

        if(user!=null) {
            Website w= websitesServiceImpl.getWebsiteById(webId);
            Integer tagId=w.getTag_id();
            if(w!=null&&w.getUser_name().equals(name)){
                websitesServiceImpl.setWebsiteFav(webId);
            }
            if(searchPage!=null&&searchPage.equals("no")){
                return "redirect:/home/index.do";
            }else if(searchPage!=null){
                return "redirect:/home/search.do?web_name="+searchPage;
            }else
            return "redirect:/home/index.do?tagId="+tagId;
        }
        return "redirect:/home/index.do";
    }


}
