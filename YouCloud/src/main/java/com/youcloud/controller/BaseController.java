package com.youcloud.controller;


import com.youcloud.pojo.User;
import com.youcloud.service.*;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * @author so1esou1
 * @ClassName
 * @Date 2021.1.4
 * @TODO    Controller的基类，拥有共享的属性和方法所有controller都继承，
 */
@Controller
public class BaseController {
    @Autowired
    CircleService circleService;
    @Autowired
    UserService userService;
    @Autowired
    FileHolderService fileHolderService;
    @Autowired
    SourcesService sourcesService;
    @Autowired
    UsersCirclesService usersCirclesService;


    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
    protected User loginUser;

    @ModelAttribute
    public void preReqResp(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession(true);
        loginUser = (User) session.getAttribute("loginUser");
    }


    //生成6位不重复的随机数字的方法
    public static Integer getRandomID(){
        long l = System.currentTimeMillis();
        Long i = l;
        String s = i.toString();
        return Integer.valueOf(s.substring(0, 10));
    }


}
