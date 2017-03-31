package com.controller;

import com.service.exampleService;
import com.service.impl.websitesServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/2/16.
 */

@Controller
@RequestMapping("/home")
public class indexController {
    private exampleService exampleServiceImpl = new websitesServiceImpl();

    @RequestMapping("/index.do")
    public ModelAndView welcome(HttpServletRequest request,
                                @RequestParam(required = false) Integer tagNum,
                                @RequestParam(required = false) Integer tagId){
       String message="";
            ModelAndView mv=new ModelAndView("websiteManagerWelcome","message",message);

            return mv;

    }




}