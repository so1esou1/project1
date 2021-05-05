package com.youcloud.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youcloud.pojo.Circle;
import com.youcloud.pojo.Sources;

import com.youcloud.utils.LoggerUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author so1esou1
 * @ClassName 主页控制器
 * @Date 2021.1.5
 * @TODO 个人页面相关功能的管理
 */
@Slf4j
@Controller     //将controller创建的实例交给controller管理
public class UserController extends BaseController{
    Logger logger = LoggerUtil.getInstance(UserController.class);


    /**
     * 分页查询我上传过的资源
     * @param model
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/showMyFile")
    public String showMyFile(Model model,
                             @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                             @RequestParam(defaultValue="5",value="pageSize")Integer pageSize){
        //为了程序的严谨性，判断非空：
        if(pageNum == null){
            pageNum = 1;   //设置默认当前页
        }
        if(pageNum <= 0){
            pageNum = 1;
        }
        if(pageSize == null){
            pageSize = 5;    //设置默认每页显示的数据数,每页5条
        }
        System.out.println("当前页是："+pageNum+"  显示条数是："+pageSize);

        try {
            List<Sources> sources = sourcesService.querySourcesByID(loginUser.getUserID());

            PageInfo<Sources> sourcesByPage = sourcesService.findSourcesByPage(pageNum, pageSize, loginUser.getUserID());
            System.out.println("分页数据："+sourcesByPage);

            //4.使用model/map/modelandview等带回前端
            model.addAttribute("mysources",sourcesByPage);
        }finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        //5.设置返回的jsp/html等前端页面
        // thymeleaf默认就会拼串classpath:/templates/xxxx.html
        return "center";
    }


    /**
        @ClassName UserController.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 前往朋友圈总界面,需要包括朋友圈的人数、资源数、用户的权限等级
    */
    @GetMapping("/toCircle")
    public String toCircle(Model model,
                           @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                           @RequestParam(defaultValue="5",value="pageSize")Integer pageSize){

        //为了程序的严谨性，判断非空：
        if(pageNum == null){
            pageNum = 1;   //设置默认当前页
        }
        if(pageNum <= 0){
            pageNum = 1;
        }
        if(pageSize == null){
            pageSize = 10;    //设置默认每页显示的数据数,每页5条
        }
        System.out.println("当前页是："+pageNum+"显示条数是："+pageSize);

        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count

        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<Circle> allCircles = circleService.queryCircleID(loginUser.getUserID());
            PageInfo<Circle> myCircle = circleService.findCirclesByPage(pageNum, pageSize, loginUser.getUserID());
            System.out.println("分页数据："+myCircle);

            //4.使用model/map/modelandview等带回前端
            model.addAttribute("circle",myCircle);
            model.addAttribute("circleNum",allCircles.size());    //朋友圈数量
        }finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        //5.设置返回的jsp/html等前端页面
        // thymeleaf默认就会拼串classpath:/templates/xxxx.html
        return "mycircles";
    }


    /**
     * 前往修改用户信息页面
     * @return
     */
    @RequestMapping("/toInfo")
    public String toInfo(){
        return "info";
    }


    /**
        @ClassName UserController.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 修改用户的资料
     userNick   userPassword    userEmail   userAddr    userContact     可以更改
    */
    @PostMapping("/updateInfo")
    @ResponseBody
    public String updateInfo(String userNick,
                             String userPassword,
                             String userEmail,
                             String userAddr,
                             String userContact,
                             Map<String, Object> map){
        
        userService.changeUser(loginUser.getUserID(),userNick,userPassword,userEmail,userAddr,userContact);
        logger.info("修改成功，即将返回主页");
        map.put("win","修改成功，将回到主页面!");

        return "ok";
    }


    /**
     * 前往帮助页面
     * @return
     */
    @RequestMapping("/toAbout")
    public String toAbout(){
        return "about";
    }
}
