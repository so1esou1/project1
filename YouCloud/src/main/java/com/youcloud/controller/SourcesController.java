package com.youcloud.controller;

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
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author so1esou1
 * @ClassName
 * @Date 2021.1.4
 * @TODO 资源界面的管理
 */
@Slf4j
@Controller
public class SourcesController extends BaseController{
    Logger logger = LoggerUtil.getInstance(SourcesController.class);



    /**
        @ClassName SourcesController.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 文件的上传,需要验证用户权限是否为1
    */
    @PostMapping("/uploadSource")
    public String uploadSource(@RequestParam("file") MultipartFile source, Model model){
        String sourceName = source.getOriginalFilename();       //资源的名字
        System.out.println("sourceOriginalname=" + sourceName);
        System.out.println("sourcename="+source.getName());
        Map<String, Object> map = new HashMap<>();

        FileHolder sholder = (FileHolder)session.getAttribute("sholder");
        String fileName = sholder.getFileName();


        Integer circleID = fileHolderService.queryFileHolderByFileHolder(fileName).getFileCircleID();
        Circle circle = circleService.queryCircleByID(circleID);
        if (!circle.getFoundName().equals(loginUser.getUserNick())){
            logger.error("上传失败，权限不足");
            map.put("msg", "您没有权限上传文件！请联系本群管理员!");
            return "error/501";
        }
        FileHolder holder = fileHolderService.queryFileHolderByFileHolder(fileName);    //获取到文件夹
        String name = source.getOriginalFilename().replaceAll(" ","");

        //获取文件夹中所有的文件以查重
        List<Sources> holderSources = sourcesService.querySourcesByHolder(fileName);
        //设置文件上传保存的路径:
        String path = circle.getCircleID() + "/" + fileName ;
        //路径:朋友圈ID/上传用户ID/文件名

        //文件已上传:
        for (int i = 0; i < holderSources.size(); i++) {
            if ((holderSources.get(i).getSourceName().equals(sourceName))){
                logger.error("当前文件已存在!");
                map.put("msg", "文件上传失败，请检查是否已上传过");
                return "error/501";
            }
        }


        //检查仓库容量:
        Integer restCap = (circle.getCap() - circle.getCapUsed());

        //获取资源的大小:
        Integer sizeInt = Math.toIntExact(source.getSize() / 1024);
        //是否仓库放不下该文件
        if(sizeInt > restCap){
            logger.error("上传失败!仓库已满。");
            map.put("msg", "上传失败，仓库容量不足");
            return "error/501";
        }

        //处理文件名:
        int size = (int) source.getSize() / 1024;


        int index = name.lastIndexOf(".");
        String tempName = name;
        String postfix = "";    //后缀
        String type = "";
        if (index!=-1){
            tempName = name.substring(index);
            name = name.substring(0,index);
            //获得文件类型
            type = getFileType(tempName.toLowerCase());
            postfix = tempName.toLowerCase();

        }
        try {
            //提交到FTP服务器
            System.out.println("path="+path);
            System.out.println("name="+name);
            System.out.println("postfix="+postfix);

            boolean hefa = FTPUtil.uploadFile("/"+path, sourceName, source.getInputStream());
            if (hefa){
                //上传成功
                logger.info(sourceName + "上传成功!");
                //向数据库文件表写入数据

                //注意：不是真正的资源，是放入数据库的代表物
                Sources fakeSource = new Sources();
                fakeSource.setSourceAddr(path);
                fakeSource.setSourceName(name);
                fakeSource.setSourceSize(size);
                fakeSource.setSourceType(type);
                fakeSource.setUploadTime(new Date());
                fakeSource.setSourceUserID(loginUser.getUserID());
                fakeSource.setSourceFileName(fileName);
                fakeSource.setPostFix(postfix);

                sourcesService.addSource(fakeSource);


                //更新仓库表的当前大小
                fileHolderService.addHolderCap(fileName,Integer.valueOf(size));
                //更新朋友圈容量：
                circleService.addCircleSize(circleID,size);

            }else{
                logger.error("文件" + sourceName + "上传失败!");
                map.put("msg", "文件上传失败，请检查文件是否正常!");
                return "error/501";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }


    /**
        @ClassName SourcesController.java
        @author so1esou1

        @Date 2021.1.6
        @TODO 文件的下载
    */
    @GetMapping("/downloadSource")
    public String downloadSource(String fileName,@RequestParam("sourceName") String sourceName){    //这里的sourceName应该是不带后缀的
        //获取文件信息
        FileHolder holder = fileHolderService.queryFileHolderByFileHolder(fileName);    //得到这个文件夹
        String remotePath = sourcesService.querySourcesByName(sourceName).getSourceAddr();    //获取资源的路径,不带根目录
        Sources theSource = sourcesService.querySourcesByName(sourceName);      //得到文件
        String postfix = theSource.getPostFix();
        try {
            //去FTP上拉取

            response.setCharacterEncoding("utf-8");
            // 设置返回类型
            response.setContentType("multipart/form-data");
            // 文件名转码一下，不然会出现中文乱码
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
            boolean flag = FTPUtil.downloadFile("/" + remotePath, sourceName+postfix);
            if (flag) {
                logger.info("文件下载成功!" + sourceName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error/501";
    }


    /**
     * 模糊查询资源
     * @return
     */
    @GetMapping("/fuzzySource")
    public String fuzzySource(String sourceName,Map<String, Object> map){
        List<Sources> sources = sourcesService.fuzzyQuerySource(sourceName);

        if (0 != sources.size()){
            FileHolder preHodler = fileHolderService.queryFileHolderByFileHolder(sources.get(0).getSourceFileName());
            List<Sources> sources1 = sourcesService.querySourcesByHolder(preHodler.getFileName());
            map.put("fsource",sources);
            map.put("sources1",sources1);
        }else {
            logger.info("找不到相关文件");
        }

        return "fsources";
    }


    /**
        @ClassName SourcesController.java
        @author so1esou1
        @Date 2021.1.6
        @TODO 删除文件,需要验证用户权限是否为1
    */
    @GetMapping("/deleteSource")
    @ResponseBody
    public String deleteSource(String fileName,@RequestParam("sourceName") String sourceName, Map<String, Object> map){
        Circle circle = circleService.queryCircleByID(fileHolderService.queryFileHolderByFileHolder(fileName).getFileCircleID());
        if (circle.getFoundName().equals(loginUser.getUserNick())){        //如果该用户是管理员的话，可以进行删除
            //得到资源的相关信息
            Sources mySources = sourcesService.querySourcesByName(sourceName);
            String sourceAddr = mySources.getSourceAddr();
            String postFix = mySources.getPostFix();
            //从FTP文件服务器上删除文件
            boolean deladdr = FTPUtil.deleteFile("/"+sourceAddr, sourceName+postFix);
            if (deladdr){
                //删除成功,返回空间
                fileHolderService.addHolderCap(sourceName,Integer.valueOf(mySources.getSourceSize()));
                //删除文件表对应的数据
                sourcesService.deleteSource(sourceName);

                circleService.subCircleSize(circle.getCircleID(), mySources.getSourceSize());
            }
            logger.info("删除文件成功!"+fileName);

            return "ok";

        }

        logger.info("抱歉，您没有权限进行删除!请联系管理员删除!");
        map.put("msg","抱歉，您没有权限进行删除!请联系管理员删除!");
        return "error";

    }



    /**
        @ClassName SourcesController.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 重命名文件，需要用户权限
    */
    @PostMapping("/renameSource")
    @ResponseBody
    public String renameSource(String oldSourceName,@RequestParam("newName") String newName,Map<String, Object> map){

        Sources oldSources = sourcesService.querySourcesByName(oldSourceName);

        FileHolder fileHodler = fileHolderService.queryFileHolderByFileHolder(oldSources.getSourceFileName()); //获取所在文件夹
        //判断权限
        System.out.println("holder=" + fileHodler);
        Circle circle = circleService.queryCircleByID(fileHodler.getFileCircleID());
        if (!circle.getFoundName().equals(loginUser.getUserNick())){
            logger.error("修改失败，权限不足");
            map.put("msg", "您没有权限修改文件名！请联系本群管理员!");
            return "error1";
        }

        String postfix = oldSources.getPostFix();    //获取资源名的后缀
        if (oldSources != null){
            String oldName = oldSources.getSourceName();
            if (!oldName.equals(newName)){
                boolean flag = FTPUtil.renameFile(oldName, newName + postfix);
                if (flag){
                    Boolean update = sourcesService.renameSource(oldSources.getSourceName(),newName);
                    System.out.println("Name"+oldSources.getSourceName());
                    System.out.println("newName"+newName);
                    if (update){
                        logger.info("修改文件名成功!");
                    }else{
                        logger.error("修改文件名失败!请检查是否重名");
                        map.put("msg","修改文件名失败!请检查是否重名");
                        return "error2";
                    }
                }
            }
        }
        return "ok";
    }


    /**
        @ClassName SourcesController.java
        @author so1esou1
        @Date 2021.1.4
        @TODO 根据文件后缀判断其类型,1:文本类型   2:图像类型  3:视频类型  4:音乐类型  5:其他类型
    */
    public String getFileType(String type){
        if (".chm".equals(type)||".txt".equals(type)||".xmind".equals(type)||".xlsx".equals(type)||".md".equals(type)
                ||".doc".equals(type)||".docx".equals(type)||".pptx".equals(type)
                ||".wps".equals(type)||".word".equals(type)||".html".equals(type)||".pdf".equals(type)){
            return  "文档";
        }else if (".bmp".equals(type)||".gif".equals(type)||".jpg".equals(type)||".ico".equals(type)||".vsd".equals(type)
                ||".pic".equals(type)||".png".equals(type)||".jepg".equals(type)||".jpeg".equals(type)||".webp".equals(type)
                ||".svg".equals(type)){
            return "图像";
        } else if (".avi".equals(type)||".mov".equals(type)||".qt".equals(type)
                ||".asf".equals(type)||".rm".equals(type)||".navi".equals(type)||".wav".equals(type)
                ||".mp4".equals(type)||".mkv".equals(type)||".webm".equals(type)){
            return "视频";
        } else if (".mp3".equals(type)||".wma".equals(type)){
            return "音乐";
        } else {
            return "其他";
        }
    }

}
