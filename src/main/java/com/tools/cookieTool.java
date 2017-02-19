package com.tools;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/2/18.
 */
public class cookieTool {

    public static String  checkCookie(HttpServletRequest request) {
        Cookie[] cookies =  request.getCookies();
        String name="";
        if(cookies!=null){
            for(Cookie c:cookies){
                if(c.getName().equals("curr_user")) {
                    name = name.concat(c.getValue());
                }
            }
        }
        return name;
    }
}
