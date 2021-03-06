package com.controller;

import com.domain.Tag;
import com.domain.User;
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
public class tagsController {
    private tagsService tagsServiceImpl = new tagsServiceImpl();
    private usersService usersServiceImpl = new usersServiceImpl();
    private websitesService websitesServiceImpl = new websitesServiceImpl();


    @RequestMapping(value = "/insertTag.do", method = RequestMethod.POST)
    public String  insertWebsite(HttpServletRequest request,
                                 @RequestParam(required = false) String tagName,
                                 @RequestParam(required = true) String userName){

        String name=cookieTool.checkUserNameFromCookie(request);
        if(name==""){
            return "redirect:expired.jsp";
        }
        User user= usersServiceImpl.getUserByName(userName);
        if(user!=null) {
            if(tagName=="") {
                tagName="new tag";
            }
            Tag t= new Tag(tagName, userName);
            tagsServiceImpl.insertTag(t);
            return "redirect:/home/index.do";
        }
        return "redirect:/home/index.do";
    }


    @RequestMapping(value = "/deleteTag.do", method = RequestMethod.POST)
    public String  deleteTag(HttpServletRequest request,
                                 @RequestParam(required = true) Integer tagId
    ) {
        String name=cookieTool.checkUserNameFromCookie(request);
        if(name==""){
            return "redirect:expired.jsp";
        }
        User user= usersServiceImpl.getUserByName(name);
        if(user!=null) {
            Tag t= tagsServiceImpl.getTagById(tagId);
            if(t!=null&&t.getUser_name().equals(name)){
                tagsServiceImpl.deleteTag(tagId);
            }
        }
        return "redirect:/home/index.do";    }



    @RequestMapping(value = "/updateTag.do", method = RequestMethod.POST)
    public String  updateTag(HttpServletRequest request,
                             @RequestParam(required = false) String tagName,
                             @RequestParam(required = true) Integer tagId) {
        String name=cookieTool.checkUserNameFromCookie(request);
        if(name==""){
            return "redirect:expired.jsp";
        }
        User user= usersServiceImpl.getUserByName(name);

        if(user!=null) {
            if(tagName=="") {
                tagName="new tag";
            }
            Tag t= tagsServiceImpl.getTagById(tagId);
            if(t!=null&&t.getUser_name().equals(name)){
                t.setTag_name(tagName);
                tagsServiceImpl.updateTag(t);
            }
            return "redirect:/home/index.do?tagId="+tagId;
        }
        return "redirect:/home/index.do";    }

}
