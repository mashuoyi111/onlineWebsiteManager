package com.controller;

import com.domain.Tag;
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
public class indexController {
    private tagsService tagsServiceImpl = new tagsServiceImpl();
    private usersService usersServiceImpl = new usersServiceImpl();
    private websitesService websitesServiceImpl = new websitesServiceImpl();

    @RequestMapping("/index.do")
    public ModelAndView welcome(HttpServletRequest request,
                                @RequestParam(required = false) Integer tagNum,
                                @RequestParam(required = false) Integer tagId){
        String name= cookieTool.checkUserNameFromCookie(request);
        if(name==""){
            return new ModelAndView("expired");
        }
        User user= usersServiceImpl.getUserByName(name);
        List<Tag> tags= tagsServiceImpl.getTagsByUser(user);
        if(tags.size()==0){
            Tag t=new Tag(user.getUser_name());
            tagsServiceImpl.insertTag(t);
            tags.add(t);
        }
        Tag currentTag=tags.get(0);
        if(tagNum!=null && tags.size()>=tagNum && tagNum>0){
             currentTag=tags.get(tagNum-1);
        }
        if(tagId!=null){
            Tag t= tagsServiceImpl.getTagById(tagId);
            if(t!=null&&t.getUser_name().equals(name)){
                currentTag=t;
            }
        }
        List<Website> websites=new ArrayList<Website>();
        for(Tag t:tags){
            websites.addAll(websitesServiceImpl.getWebsitesByTagid(t.getTag_id()));
        }
        String message=user.getNickname();
        if(tagNum!=null||tagId!=null){
        ModelAndView mv=new ModelAndView("websiteManager","message",message);

        mv.addObject("user",user);
        mv.addObject("tags",tags);
        mv.addObject("currentTag",currentTag);
        mv.addObject("websites",websites);

        return mv;
        } else{
            message="Hello, "+user.getNickname()+". Here are your favorite websites:";
            ModelAndView mv=new ModelAndView("websiteManagerWelcome","message",message);
            mv.addObject("user",user);
            mv.addObject("tags",tags);
            mv.addObject("websites",websites);
            return mv;
        }
    }



    @RequestMapping("/search.do")
    public ModelAndView welcome(HttpServletRequest request,
                                @RequestParam(required = true) String web_name) {
        String name = cookieTool.checkUserNameFromCookie(request);
        if (name == "") {
            return new ModelAndView("expired");
        }
        User user = usersServiceImpl.getUserByName(name);
        String message=user.getNickname();

        List<Tag> tags = tagsServiceImpl.getTagsByUser(user);
        List<Website> websites= websitesServiceImpl.searchWebsitesByName(user.getUser_name(), web_name);
        message="Hello, "+user.getNickname()+". Results for: "+ web_name;
        ModelAndView mv=new ModelAndView("websiteManagerSearch","message",message);
        mv.addObject("user",user);
        mv.addObject("tags",tags);
        mv.addObject("websites",websites);
        mv.addObject("web_name",web_name);
        return mv;
    }

}