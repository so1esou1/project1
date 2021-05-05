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

/**
 * @author so1esou1
 * @ClassName
 * @Date 2021.1.4
 * @TODO
 */
@Slf4j
@Controller
public class FileHolderController extends BaseController{
    Logger logger = LoggerUtil.getInstance(FileHolderController.class);


    /**
        @ClassName FileHolderController.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 新建文件夹，需要用户权限为1
    */
    @PostMapping("/foundHolder")
    @ResponseBody
    public String foundHolder(@RequestParam("fileName") String fileName, Integer circleID, Map<String, Object> map){   //这里的fileName是用户创建的文件夹的名字
        //检测用户权限:
        System.out.println("asdja");
        System.out.println(circleID);
        Circle circle = circleService.queryCircleByID(circleID);
        System.out.println(circle);
        if (!circle.getFoundName().equals(loginUser.getUserNick())){
            logger.error("创建失败，权限不足");
            map.put("msg", "您没有权限创建文件夹！请联系本群管理员!");
            return "error1";
        }
        //进行一次查重:
        List<FileHolder> fileHolderList = fileHolderService.queryFileHolderByCircle(circle.getCircleID());
        for (int i = 0; i < fileHolderList.size(); i++) {
            FileHolder file = fileHolderList.get(i);
            if (file.getFileName().equals(fileName)){
                logger.error("添加文件夹失败!文件夹已存在！");
                map.put("msg", "添加文件夹失败!文件夹已存在!");
                return "error2";
            }
        }

        //设置文件夹信息
        FileHolder fileHolder = new FileHolder();
        fileHolder.setFileCircleID(circle.getCircleID());
        fileHolder.setFileName(fileName);
        fileHolder.setFileCap(0);

        //向数据库写入数据
        fileHolderService.addFileHolder(fileHolder);
        logger.info("添加文件夹成功!");
        return "ok";
    }


    /**
        @ClassName FileHolderController.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 删除文件夹，会递归删除里面的文件，需要用户权限为1
    */
    @PostMapping("/deleteHolder")
    @ResponseBody
    public String deleteHolder(@RequestParam("fileName")String fileName,Map<String, Object> map){
        FileHolder fileHolder = fileHolderService.queryFileHolderByFileHolder(fileName);
        Circle circle = circleService.queryCircleByID(fileHolder.getFileCircleID());
        if (!circle.getFoundName().equals(loginUser.getUserNick())){  //判断是否有权限
            logger.error("删除失败，权限不足");
            map.put("msg", "您没有权限删除该文件！请联系本群管理员!");
            return "error";
        }

        //获取当前文件夹的所有文件:
        List<Sources> sourcesList = sourcesService.querySourcesByHolder(fileName);
        if (sourcesList.size() != 0){

            for (int i = 0;i < sourcesList.size();i ++){
                String sourceAddr = sourcesList.get(i).getSourceAddr();
                String sourceName = sourcesList.get(i).getSourceName();
                Integer sourceSize = sourcesList.get(i).getSourceSize();
                boolean b = FTPUtil.deleteFile("/"+sourceAddr, sourceName);
                int sousize = 0;
                if (b){
                    fileHolderService.deleteFileHolderByName(fileName);
                    sousize += sourceSize;
                }

                circleService.subCircleSize(circle.getCircleID(), sousize);
            }

        }
        return "ok";
    }


    /**
        @ClassName FileHolderController.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 前往某个具体的文件夹内部的资源页面
    */
    @GetMapping("/toSources")
    public String toSources(@RequestParam("fileName")String fileName,
                            @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                            @RequestParam(defaultValue="5",value="pageSize")Integer pageSize,
                            Model model){   //文件夹的名字
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


        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            FileHolder holder = fileHolderService.queryFileHolderByFileHolder(fileName);
            PageInfo<Sources> pageInfo = sourcesService.findFSourcesByPage(pageNum, pageSize, fileName);
            List<Sources> allSources = sourcesService.querySourcesByHolder(holder.getFileName());


            System.out.println("分页数据："+pageInfo);

            //4.使用model/map/modelandview等带回前端
            model.addAttribute("sources",pageInfo);
            model.addAttribute("holder",holder);
            session.setAttribute("sourcesNum",allSources.size());   //资源的数量
            session.setAttribute("sourcesSize",sourcesService.sumCap(fileName));  //资源总的大小
            model.addAttribute("sourceholder",holder.getFileName());        //父文件夹的名称
            session.setAttribute("sholder",holder);
            session.setAttribute("pageFileName",holder.getFileName());     //用于分页的文件夹名字

            Circle scircle = circleService.queryCircleByID(holder.getFileCircleID());

        }finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        //5.设置返回的jsp/html等前端页面
        // thymeleaf默认就会拼串classpath:/templates/xxxx.html
        return "sources";

    }


    /**
     * 模糊查询文件夹
     * @param fileName
     * @param map
     * @return
     */
    @GetMapping("/fuzzyHolder")
    public String fuzzyHolder(String fileName,Map<String, Object> map){
        List<FileHolder> fileHolders = fileHolderService.fuzzyQueryHolder(fileName);
        if (0 != fileHolders.size()){
            Circle preCircle =circleService.queryCircleByID(fileHolders.get(0).getFileCircleID());
            map.put("preCircle",preCircle);
            map.put("fholder",fileHolders);

        }else {
            logger.info("找不到相关文件夹");
        }

        return "fthecircle";

    }


    /**
        @ClassName FileHolderController.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 重命名文件夹，需要用户权限为1
    */
    @PostMapping("/renameHolder")
    @ResponseBody
    public String renameHolder(String fileName,String newName,Map<String, Object> map){
        Integer fileCircleID = fileHolderService.queryFileHolderByFileHolder(fileName).getFileCircleID();
        Circle circle = circleService.queryCircleByID(fileCircleID);        //获取朋友圈

        FileHolder fileHolder = fileHolderService.queryFileHolderByFileHolder(fileName);
        if (!circle.getFoundName().equals(loginUser.getUserNick())){  //判断是否有权限
            logger.error("修改失败，权限不足");
            map.put("msg", "您没有权限修改文件夹名！请联系本群管理员!");
            return "error1";
        }
        //进行一次查重:
        List<FileHolder> fileHolders = fileHolderService.queryFileHolderByCircle(circle.getCircleID());//拿到全部文件夹名字
        for (int i = 0; i < fileHolders.size(); i++) {
            FileHolder holder = fileHolders.get(i);
            if (holder.getFileName().equals(newName)){
                logger.info("重命名文件夹失败!文件夹已存在...");
                return "error2";
            }
        }
        //符合要求，可以重命名
        fileHolder.setFileName(newName);
        fileHolderService.renameHolder(fileName,newName);
        logger.info("重命名文件夹成功!");

        return "ok";
    }
}
