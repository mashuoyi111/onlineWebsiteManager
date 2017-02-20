package com.tools;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/2/18.
 */
public class cookieTool {

    public static String checkUserNameFromCookie(HttpServletRequest request) {
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

    public static void logoutCookie(HttpServletRequest request,
                                    HttpServletResponse response){
        Cookie[] cookies =  request.getCookies();
        if(cookies!=null){
            for(Cookie c:cookies){
                if(c.getName().equals("curr_user")) {
                    c.setValue(null);
                    c.setPath("/");
                    c.setMaxAge(0);
                    response.addCookie(c);
                }
            }
        }
    }
}
