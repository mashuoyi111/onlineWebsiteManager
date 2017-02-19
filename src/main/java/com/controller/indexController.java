package com.controller;

import com.domain.User;
import com.domain.Website;
import com.service.tagsService;
import com.service.usersService;
import com.service.websitesService;
import com.tools.DBtool;
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
public class indexController {
    private tagsService tagsService = new tagsService();
    private usersService usersService = new usersService();
    private websitesService websitesService = new websitesService();





}