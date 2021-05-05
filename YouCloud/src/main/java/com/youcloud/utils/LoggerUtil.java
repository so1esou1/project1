package com.youcloud.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author so1esou1
 * @ClassName
 * @Date 2021.1.4
 * @TODO 日志功能工具类
 */
public class LoggerUtil {
    private static Logger logger;
    //打印日志:
    public static Logger getInstance(Class c){
        return logger =  LoggerFactory.getLogger(c);
    }
    private LoggerUtil(){

    }
}
