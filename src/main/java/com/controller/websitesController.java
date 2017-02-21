package com.controller;

import com.domain.Tag;
import com.domain.User;
import com.domain.Website;
import com.service.tagsService;
import com.service.usersService;
import com.service.websitesService;
import com.tools.cookieTool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/16.
 */

@Controller
@RequestMapping("/home")
public class websitesController {
    private tagsService tagsService = new tagsService();
    private usersService usersService = new usersService();
    private websitesService websitesService = new websitesService();


    @RequestMapping(value = "/insertWebsite.do", method = RequestMethod.POST)
    public String  insertWebsite(HttpServletRequest request,
        @RequestParam(required = false) String webName,
        @RequestParam(required = false) String webUrl,
        @RequestParam(required = false) String webComment,
        @RequestParam(required = true) String userName,
        @RequestParam(required = true) Integer tagId){

        String name=cookieTool.checkUserNameFromCookie(request);
        if(name==""){
            return "redirect:expired.jsp";
        }
        User user=usersService.getUserByName(userName);
        if(user!=null) {
            if(webName==""){
                webName="new website";
            }
            if(webUrl==""){
                webUrl="about:blank";
            }
            Website w=new Website(webName,webComment,webUrl,tagId,name);
            websitesService.insertWebsite(w);
            return "redirect:/home/index.do?tagId="+tagId;
        }
        return "redirect:/home/index.do";
    }



    @RequestMapping(value = "/deleteWebsite.do", method = RequestMethod.POST)
    public String  deleteWebsite(HttpServletRequest request,
                                 @RequestParam(required = false) Integer webId) {
        String name=cookieTool.checkUserNameFromCookie(request);
        if(name==""){
            return "redirect:expired.jsp";
        }
        User user=usersService.getUserByName(name);

        if(user!=null) {
            Website w=websitesService.getWebsiteById(webId);
            Integer tagId=w.getTag_id();
            if(w!=null&&w.getUser_name().equals(name)){
                websitesService.deleteWebsite(webId);
            }
            return "redirect:/home/index.do?tagId="+tagId;
        }
        return "redirect:/home/index.do";    }
}
