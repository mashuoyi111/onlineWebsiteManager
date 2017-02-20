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
    private tagsService tagsService = new tagsService();
    private usersService usersService = new usersService();
    private websitesService websitesService = new websitesService();

    @RequestMapping("/index.do")
    public ModelAndView welcome(HttpServletRequest request,
                                @RequestParam(required = false) Integer tagNum){
        String name= cookieTool.checkUserNameFromCookie(request);
        if(name==""){
            return new ModelAndView("expired");
        }
        User user=usersService.getUserByName(name);
        List<Tag> tags=tagsService.getTagsByUser(user);
        if(tags.size()==0){
            Tag t=new Tag(user.getUser_name());
            tagsService.insertTag(t);
            tags.add(t);
        }
        Tag currentTag=tags.get(0);
        if(tagNum!=null && tags.size()>=tagNum && tagNum>0){
             currentTag=tags.get(tagNum-1);
        }
        List<Website> websites=new ArrayList<Website>();
        for(Tag t:tags){
            websites.addAll(websitesService.getWebsitesByTagid(t.getTag_id()));
        }
        String message=user.getNickname();

        ModelAndView mv=new ModelAndView("welcome","message",message);

        mv.addObject("user",user);
        mv.addObject("tags",tags);
        mv.addObject("currentTag",currentTag);
        mv.addObject("websites",websites);

        return mv;
    }



}