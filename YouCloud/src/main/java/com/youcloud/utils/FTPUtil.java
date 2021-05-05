package com.youcloud.utils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;

/**
 * @author so1esou1
 * @ClassName
 * @Date 2021.1.4
 * @TODO
 */
public class FTPUtil {
    /**
     * FTP服务器hostname
     */
    private static String HOST = "39.103.151.171";
    /**
     * FTP服务器端口
     */
    private static int PORT = 21;
    /**
     * FTP登录账号
     */
    private static String USERNAME = "ftpuser";
    /**
     * FTP登录密码
     */
    private static String PASSWORD = "ftpuser";
    /**
     * FTP服务器基础目录
     */
    private static String BASEPATH = "/usr/local/ftpdir/files";
    /**
     * FTP客户端
     */
    private static FTPClient ftp;

    /**
     * @Description 初始化FTP客户端
     * @Author xw
     * @Date 12:34 2020/2/5
     * @Param []
     * @return boolean
     **/
    public static boolean initFtpClient(){
        ftp = new FTPClient();
        int reply;
        try {
            // 连接FTP服务器
            ftp.connect(HOST, PORT);
            //登录, 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftp.login(USERNAME, PASSWORD);
            ftp.setBufferSize(10240);
            //设置传输超时时间为60秒
            ftp.setDataTimeout(600000);
            //连接超时为60秒
            ftp.setConnectTimeout(600000);
            //FTP以二进制形式传输
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            reply = ftp.getReplyCode();
            System.out.println("FTP 连接是否成功：" + ftp.isConnected());
            System.out.println("FTP 连接是否有效：" + ftp.isAvailable());
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }


    /**
     * Description: 向FTP服务器上传文件
     * @param filePath FTP服务器文件存放路径。例如分日期存放：/2015/01/01。文件的路径为basePath+filePath
     * @param filename 上传到FTP服务器上的文件名
     * @param input 本地要上传的文件的 输入流
     * @return 成功返回true，否则返回false
     */
    public static boolean uploadFile(String filePath, String filename, InputStream input) {
        System.out.println("filename="+filename);
        boolean result = false;
        try {
            if (!initFtpClient()) {
                System.out.println("是否进入？");
                return result;
            }
            filename = new String(filename.getBytes("UTF-8"),"iso-8859-1");
            System.out.println("newfileName="+filename);
            ftp.setControlEncoding("UTF-8");

            //切换到上传目录
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);

            ftp.changeWorkingDirectory(BASEPATH);
            System.out.println(BASEPATH);
            //上传文件
            ftp.enterLocalPassiveMode();

            if (!ftp.storeFile(filename, input)) {
                System.out.println("还是进这里了吗");
                System.out.println("返回码:" + ftp.getReplyCode());
                return result;
            }
            input.close();
            ftp.logout();
            result = true;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return result;

    }



    /**
     * Description: 从FTP服务器下载文件
     * @param remotePath FTP服务器上的相对路径
     * @param fileName 要下载的文件名
     * @return
     */
    public static boolean downloadFile( String remotePath,String fileName,String localPath) {
        boolean result = false;

        try {
            remotePath = new String(remotePath.getBytes("UTF-8"),"iso-8859-1");
            fileName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
            if (!initFtpClient()){
                return result;
            };
            // 转移到FTP服务器目录
            ftp.changeWorkingDirectory(remotePath);
            ftp.enterLocalPassiveMode();
            FTPFile[] fs = ftp.listFiles();
            for (FTPFile ff : fs) {
                if (ff.getName().equals(fileName)) {
                    ftp.enterLocalPassiveMode();
                    FileOutputStream outputStream = new FileOutputStream(new File(localPath));
                    ftp.retrieveFile(remotePath+"/"+fileName,outputStream);

                    result = true;
                    outputStream.close();
                }
            }
            ftp.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return result;
    }

    /**
     * @Description 从ftp服务器下载文件到指定输出流
     * @Author xw
     * @Date 22:30 2020/3/5
     * @Param [remotePath, fileName, outputStream]
     * @return boolean
     **/
    public static boolean downloadFile(String remotePath, String fileName) throws FileNotFoundException {
        boolean result = false;
        File theSource = new File(BASEPATH+"/"+fileName);
        OutputStream os = new FileOutputStream(theSource);
        try {
            fileName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
            if (!initFtpClient()){
                return result;
            };
            // 转移到FTP服务器目录
            ftp.changeWorkingDirectory(BASEPATH);
            ftp.enterLocalPassiveMode();
            ftp.retrieveFile(BASEPATH+"/"+fileName,os);

            ftp.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return result;
    }

    /**
     * @Description 删除文件
     * @Author xw
     * @Date 11:38 2020/2/6
     * @Param [remotePath, fileName]
     * @return void
     **/
    public static boolean deleteFile( String remotePath,String fileName){
        boolean flag = false;
        try {
            remotePath = new String(remotePath.getBytes("GBK"),"iso-8859-1");
            fileName = new String(fileName.getBytes("GBK"),"iso-8859-1");
            if (!initFtpClient()){
                return flag;
            };
            // 转移到FTP服务器目录
            ftp.changeWorkingDirectory(BASEPATH);
            ftp.enterLocalPassiveMode();
            String filePath = BASEPATH+"/"+fileName;
            ftp.deleteFile(filePath);
            ftp.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return flag;
    }

    /**
     * @Description 删除文件夹
     * @Author xw
     * @Date 11:38 2020/2/6
     * @Param [remotePath, fileName]
     * @return void
     **/
    public static boolean deleteFolder( String remotePath){
        boolean flag = false;
        try {
            remotePath = new String(remotePath.getBytes("GBK"),"iso-8859-1");
            if (!initFtpClient()){
                return flag;
            };
            // 转移到FTP服务器目录
            ftp.changeWorkingDirectory(remotePath);
            ftp.enterLocalPassiveMode();
            FTPFile[] fs = ftp.listFiles();
            if (fs.length==0){
                ftp.removeDirectory(remotePath);
                flag = true;
            }
            ftp.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return flag;
    }

    /**
     * @Description 修改文件名称或者文件夹名
     * @Author xw
     * @Date 21:18 2020/2/11
     * @Param [oldAllName, newAllName]
     * @return boolean
     **/
    public static boolean renameFile( String oldAllName,String newAllName){
        boolean flag = false;
        try {
            oldAllName = new String(oldAllName.getBytes("GBK"),"iso-8859-1");
            newAllName = new String(newAllName.getBytes("GBK"),"iso-8859-1");
            if (!initFtpClient()){
                return flag;
            };
            ftp.enterLocalPassiveMode();
            ftp.rename(oldAllName,newAllName);
            flag = true;
            ftp.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return flag;
    }
}
