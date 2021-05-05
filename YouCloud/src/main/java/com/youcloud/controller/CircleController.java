package com.youcloud.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youcloud.pojo.Circle;
import com.youcloud.pojo.FileHolder;
import com.youcloud.pojo.Sources;

import com.youcloud.utils.FTPUtil;
import com.youcloud.utils.LoggerUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;
import java.util.Map;
import java.util.Random;


/**
 * @author so1esou1
 * @ClassName
 * @Date 2021.1.4
 * @TODO 朋友圈功能管理,用户的朋友圈
 */
@Slf4j
@Controller
public class CircleController extends BaseController{

    Logger logger = LoggerUtil.getInstance(CircleController.class);




    /**
        @ClassName CircleController.java
        @author so1esou1
        @Date 2021.1.8
        @TODO 加入某个朋友圈

    */
    @PostMapping("/joinCircle")
    @ResponseBody
    public String joinCircle(@RequestParam("circleName") String circleName,@RequestParam("circlePwd") String circlePwd, Map<String, Object> map){

        if (null != circleService.queryCircleByName(circleName)){
            Circle circle = circleService.queryCircleByName(circleName);
            //首先需要用户输入的验证码与该朋友圈验证码一致:
            if (circlePwd == null){
                logger.error("进群密码未填写");
                map.put("msg","请输入进群密码");
                return "error1";
            }
            if (circlePwd != circle.getCirclePassword()){
                logger.error("进群密码错误");
                map.put("msg","进群密码错误");
                return "error2";
            }

            //在mapper层实现增加:
            usersCirclesService.addUsersCircles(loginUser.getUserID(),circle.getCircleID());

            return "ok";
        }

        logger.info("不存在这个朋友圈");
        map.put("msg","不存在这个朋友圈");
        return "error3";
    }

    /**
        @ClassName CircleController.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 创建一个朋友圈，创建者为管理员，权限为1
    */
    @PostMapping("/foundCircle")
    @ResponseBody
    public String foundCircle(@RequestParam("circleName") String circleName,
                              @RequestParam("circlePwd") String circlePwd,
                              @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                              @RequestParam(defaultValue="5",value="pageSize")Integer pageSize,
                              Map<String, Object> map){
        if (circleName == null){
            logger.error("朋友圈名未填写");
            map.put("msg", "请输入朋友圈名");
            return "error1";
        }
        //首先判断是否已有
        if (null != circleService.queryCircleByName(circleName)){   //说明已经有了朋友圈
            logger.error("朋友圈已存在，请更换名字");
            map.put("msg", "朋友圈已存在，请更换名字");
            return "error2";
        }

        Circle circle = new Circle();


        Integer rcircleid = getRandomID();
        System.out.println(rcircleid);


        circle.setCircleID(rcircleid);
        circle.setCircleName(circleName);
        circle.setCirclePassword(circlePwd);
        circle.setFoundName(loginUser.getUserNick());
        circle.setCap(1024000);
        circle.setCapLevel(1);
        circle.setCapUsed(0);
        circle.setCircleUserCount(1);
        //向数据库中写入:
        boolean b = circleService.addCircle(circle);

        usersCirclesService.addUsersCircles(loginUser.getUserID(),rcircleid);

        if (!b){
            logger.error("创建朋友圈失败!");
        }
        logger.info("创建朋友圈" + circleName + "成功!朋友圈ID是:" + rcircleid);
        return "ok";
    }

    /**
        @ClassName CircleController.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 删除朋友圈，要求用户权限必须是1，会迭代删除所有的文件夹和相应的文件
    */
    @PostMapping("/deleteCircle")
    @ResponseBody
    public String deleteCircle(@RequestParam("circleName") String circleName,Map<String, Object> map){
        Circle circle = circleService.queryCircleByName(circleName);
        //判断用户身份:
        if (!circle.getFoundName().equals(loginUser.getUserNick())){
            logger.error("删除失败!请联系管理员");
            map.put("msg","删除失败!请联系管理员");
            return "error";
        }

        //获取当前朋友圈所有的文件夹:
        List<FileHolder> fileHolders = fileHolderService.queryFileHolderByCircle(circle.getCircleID());
        //递归删除资源和文件夹:
        if (fileHolders.size() != 0){
            for (int i = 0;i < fileHolders.size();i ++){
                FileHolder holder = fileHolders.get(i);
                List<Sources> sources = sourcesService.querySourcesByHolder(holder.getFileName());  //资源的集合
                for (int j = 0;j < sources.size();j ++){
                    Sources sour = sources.get(j);
                    String sourName = sour.getSourceName();
                    String sourAddr = sour.getSourceAddr();
                    //删除资源:
                    boolean flag = FTPUtil.deleteFile("/"+sourAddr, sourName);
                    if (flag){
                        sourcesService.deleteSource(sourName);
                    }
                }
                fileHolderService.deleteFileHolderByName(holder.getFileName());
                fileHolderService.subHolderCap(holder.getFileName(),holder.getFileCap());
            }
        }
        circleService.deleteCircleByName(circleName);
        logger.info("删除成功");
        return "ok";
    }


    /**
        @ClassName CircleController.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 退出一个朋友圈
    */
    @PostMapping("/quitCircle")
    @ResponseBody
    public String quitCircle(@RequestParam("circleName") String circleName,Map<String, Object> map){
        Circle circle = circleService.queryCircleByName(circleName);
        //如果是创建者，不能退出，只能删除这个朋友圈
        if (circle.getFoundName().equals(loginUser.getUserNick())){
            logger.info("您是创始人，不能退出这个朋友圈，只能进行删除!");
            map.put("msg","您是创始人，不能退出这个朋友圈，只能进行删除!");
            return "error";
        }

        //向数据层写入:
        //用户表和朋友圈表都要写入
        usersCirclesService.deleteUsersCircles(loginUser.getUserID(), circle.getCircleID());

        logger.info("退出成功!");
        return "ok";
    }



    /**
        @ClassName CircleController.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 前往某个具体的朋友圈内部的文件夹页面
    */
    @GetMapping("/toHolder")
    public String toHolder(@RequestParam("circleName") String circleName,
                           @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                           @RequestParam(defaultValue="5",value="pageSize")Integer pageSize,
                           Model model){

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
        System.out.println("当前页是："+pageNum+"显示条数是："+pageSize);

        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count

        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            System.out.println(circleName);
            Circle circle = circleService.queryCircleByName(circleName);

            System.out.println(circle.getCircleID());

            List<FileHolder> allFileHolders = fileHolderService.queryFileHolderByCircle(circle.getCircleID());

            PageInfo<FileHolder> fileHolders = fileHolderService.findFileHolderByPage(pageNum, pageSize, circle.getCircleID());


            System.out.println("分页数据："+ fileHolders);

            //4.使用model/map/modelandview等带回前端
            model.addAttribute("fileHolders",fileHolders);
            model.addAttribute("circle",circle);

            model.addAttribute("circleUserCount",circle.getCircleUserCount());
            model.addAttribute("fileHoldersNum",allFileHolders.size());



            session.setAttribute("fileHoldersSize",circle.getCapUsed());    //计算这个朋友圈文件已用容量
            session.setAttribute("pageCircleName",circle.getCircleName());  //用于分页的朋友圈名
            session.setAttribute("scircle",circle); //全局session，让siderbar也能拿到
            session.setAttribute("scircleUserCount",circle.getCircleUserCount());
            int count = 0;
            for (int i = 0 ;i < allFileHolders.size();i ++){
                FileHolder holder = allFileHolders.get(i);
                int size = sourcesService.querySourcesByHolder(holder.getFileName()).size();
                count += size;
            }
            session.setAttribute("sourcesCount",count);      //朋友圈中资源的数量



        }finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        //5.设置返回的jsp/html等前端页面
        // thymeleaf默认就会拼串classpath:/templates/xxxx.html
        return "thecircle";

    }


    /**
     * 重命名朋友圈
     * @param newName
     * @param oldName
     * @param map
     * @return
     */
    @PostMapping("renameCircle")
    @ResponseBody
    public String renameCircle(@RequestParam("newName") String newName,@RequestParam("oldName") String oldName,Map<String, Object> map){
        //判断权限:
        Circle circle = circleService.queryCircleByName(oldName);
        if (!circle.getFoundName().equals(loginUser.getUserNick())){
            logger.error("重命名失败!请联系管理员");
            map.put("msg", "重命名失败!请联系管理员!");
            return "error1";
        }

        if (null != circleService.queryCircleByName(newName)){
            logger.error("重命名失败!与其他朋友圈重名!");
            map.put("msg", "重命名失败!与其他朋友圈重名!");
            return "error2";
        }

        circle.setCircleName(newName);
        circleService.renameCircle(circle.getCircleID(),newName);

        logger.info("重命名朋友圈成功!");
        return "ok";
    }

}
