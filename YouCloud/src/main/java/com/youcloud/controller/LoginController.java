package com.youcloud.controller;



import com.youcloud.pojo.User;

import com.youcloud.utils.LoggerUtil;
import com.youcloud.utils.MailSendUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.HashMap;


/**
 * @author so1esou1
 * @ClassName
 * @Date 2021.1.4
 * @TODO
 */
@Slf4j
@Controller
public class LoginController extends BaseController{
    @Autowired
    JavaMailSenderImpl mailSender;
    MailSendUtil mailSendUtil;
    Logger logger = LoggerUtil.getInstance(LoginController.class);

    @RequestMapping("/tologin")
    public String tologin(){
        return "login";
    }

    @RequestMapping("/registry")
    public String registry(){
        return "registry";
    }


    /**
        @ClassName LoginController.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 注册的功能，用户需要填写id、密码和邮箱信息
    */
    @ResponseBody
    @RequestMapping("/register")
    public String register(Integer userID,String password,String email, String code){
        String uCode = (String) session.getAttribute(email + "的code");
        ModelAndView mv = new ModelAndView();
        HashMap<String, Object> map = new HashMap<>();

        if (!code.equals(uCode)){
            logger.error("验证码错误");

            return "error";
        }

            User user=new User();
            user.setUserID(userID);
            user.setUserPassword(password);
            user.setUserNick(String.valueOf(user.getUserID()));
            user.setUserEmail(email);
            user.setUserProfile(null);
            user.setUserRole("普通用户");
            userService.addUser(user);

            session.setAttribute("sessionUserID",user.getUserID());

            logger.info("注册成功");
            //返回主页面

            return "right";
    }


    /**
        @ClassName LoginController.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 普通登录的方式
    */
    @GetMapping("/login")
    public String login(
            @RequestParam(value = "UserID") Integer UserID,
            @RequestParam(value = "userPassword") String userPassword) {
        User userByid = userService.queryByID(UserID);
        String passwordByid = userByid.getUserPassword();

        if (null != userByid && StringUtils.isNotEmpty(passwordByid) && userPassword.equals(passwordByid)) {
            session.setAttribute("loginUser", userByid);
            session.setAttribute("mysource",sourcesService.querySourcesByID(userByid.getUserID()));

            System.out.println(sourcesService.querySourcesByID(userByid.getUserID()));


            return "redirect:/showMyFile";
        } else {

            session.setAttribute("loginerror", "用户名或密码错误！");
            return "login";
        }
    }


    /**
        @ClassName 发送邮件验证注册
        @author so1esou1
        @Date 2021.1.4  
        @TODO
    */
    @RequestMapping("/sendCode")
    @ResponseBody
    public String sendCode(String email){

        if (userService.getUserByEmail(email) != null){
            logger.error("发送验证码失败！邮箱已被注册！");
            session.setAttribute("msg","邮箱已被注册！");
            System.out.println("邮箱已被注册");
            return "exitEmail";
        }

        String checkCode = String.valueOf(new MailSendUtil(mailSender).IdentifyCode(email));
        
        session.setAttribute(email + "的code", checkCode);
        return "success";
    }





    /**
        @ClassName LoginController.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 用户注销退出的功能
    */
    @RequestMapping("/logout")
    public String logout() {
        session.invalidate();
        return "login";
    }


    @RequestMapping("/404")
    public String fourZeroFour(){
        return "404";
    }

    @RequestMapping("/501")
    public String fiveZeroOne(){
        return "501";
    }
}
