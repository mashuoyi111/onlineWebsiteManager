package com.controller;

import com.service.exampleService;
import com.service.impl.websitesServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
            ModelAndView mv=new ModelAndView("expired","message",message);

            return mv;

    }

    @RequestMapping(value="/hello.do",method = RequestMethod.POST)
    public ModelAndView hello(Integer password){
        ModelAndView mv;
        if(password!=null&&password==123){
            mv=new ModelAndView("hello","message","Your are right!!!");
        }else {
            mv = new ModelAndView("hello", "message", "Incorrect!!!");
        }
        return mv;
    }




}