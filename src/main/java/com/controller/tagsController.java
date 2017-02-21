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
public class tagsController {
    private tagsService tagsService = new tagsService();
    private usersService usersService = new usersService();
    private websitesService websitesService = new websitesService();


    @RequestMapping(value = "/insertTag.do", method = RequestMethod.POST)
    public String  insertWebsite(HttpServletRequest request,
                                 @RequestParam(required = false) String tagName,
                                 @RequestParam(required = true) String userName){

        String name=cookieTool.checkUserNameFromCookie(request);
        if(name==""){
            return "redirect:expired.jsp";
        }
        User user=usersService.getUserByName(userName);
        if(user!=null) {
            Tag t;
            if(tagName=="") {
                tagName="new tag";
            }
                t= new Tag(tagName, userName);
            tagsService.insertTag(t);
            return "redirect:/home/index.do";
        }
        return "redirect:/home/index.do";
    }


    @RequestMapping(value = "/deleteTag.do", method = RequestMethod.POST)
    public String  deleteWebsite(HttpServletRequest request,
                                 @RequestParam(required = false) Integer tagId
    ) {
        String name=cookieTool.checkUserNameFromCookie(request);
        if(name==""){
            return "redirect:expired.jsp";
        }
        User user=usersService.getUserByName(name);
        if(user!=null) {
            Tag t=tagsService.getTagById(tagId);
            if(t!=null&&t.getUser_name().equals(name)){
                tagsService.deleteTag(tagId);
            }
        }
        return "redirect:/home/index.do";    }

}
