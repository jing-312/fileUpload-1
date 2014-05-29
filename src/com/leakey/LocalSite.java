package com.leakey;

import java.util.List;

/**
 * 本地站点类
 */
public class LocalSite {
    /**
     * 本地站点物理路径
     */
    private String path ;
    /**
     * 服务器站点
     */
    private List<ServerSite> serverSiteList;

    /**
     * 获得物理路径
     * @return String
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置物理路径
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }
    /**
     * 获得服务器站点对象集合
     * @return List<ServerSite>
     */
    public List<ServerSite> getServerSiteList() {
        return serverSiteList;
    }
    /**
     * 设置服务器站点对象
     * @param serverSiteList
     */
    public void setServerSiteList(List<ServerSite> serverSiteList) {
        this.serverSiteList = serverSiteList;
    }
    /**
     * 是否是有效路径
     * @param submitPath
     * @return Boolean
     */
    public Boolean isValidPath(String submitPath) {
        return submitPath.indexOf(path)==-1 ? false:true ;
    }
    /**
     * 上传到服务器
     * @param localUploadObject
     */
    public void transferToServer(LocalUploadObject localUploadObject) {
        for (ServerSite serverSite : this.serverSiteList) {
            localUploadObject.serverSite = serverSite;
            localUploadObject.setUploadPath();
            FileTransfer fileTransfer = new FileTransfer(localUploadObject);
            fileTransfer.run() ;
        }
    }


}
