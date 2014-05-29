package com.leakey;

import java.io.File;

/**
 * 本地上传对象类
 */
public class LocalUploadObject {
    /**
     * 上传对象名字(包括路径)
     */
    public String name ;

    /**
     * 上传对象相对路径
     */
    public String relativePath ;
    /**
     * 上传对象服务器路径
     */
    public String uploadServerPath ;
    /**
     * 上传对象所属本地站点
     */
    public LocalSite localsite ;
    /**
     * 上传对象所属服务器站点
     */
    public ServerSite serverSite ;

    /**
     * 构造函数
     * @param fileName
     */
    public LocalUploadObject(String fileName) {
        this.name = fileName ;
    }
    /**
     * 判断是否存在
     * @return boolean
     */
    public boolean isExist() {
        File file = new File(this.name) ;
        return file.exists();
    }
    /**
     * 判断是否是文件夹
     * @return boolean
     */
    public boolean isFolder() {
        File file = new File(this.name) ;
        return file.isDirectory() ;
    }

    /**
     * 设置相对路径
     */
    public void setRelativePath() {
        String relativePath = this.name.replace(this.localsite.getPath(), "");
        relativePath = relativePath.replace("\\","/") ;
        if (relativePath.length()==0 || (relativePath.length()==1 && relativePath.substring(0,1).equals("/"))) {
            this.relativePath = "/" ;
            return ;
        }
        //路径第一个字符加/
        if (!relativePath.substring(0,1).equals("/")) {
            relativePath = "/"+relativePath ;
        }
        //去掉路径最后一个/
        if (relativePath.substring(relativePath.length()-1).equals("/")) {
            relativePath = relativePath.substring(0,relativePath.length()-1) ;
        }
        if (!this.isFolder())
            this.relativePath = relativePath.substring(0,relativePath.lastIndexOf('/')) ;
        else
            this.relativePath = relativePath ;
    }
    /**
     * 设置上传路径
     */
    public void setUploadPath() {
        this.uploadServerPath =serverSite.getPath()+this.relativePath.replace("//","/") ;

    }


}
