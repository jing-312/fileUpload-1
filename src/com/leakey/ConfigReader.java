package com.leakey;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 配置文件读取器
 */
public class ConfigReader {

    private static final String mainConfigName = "config.properties";

    /**
     * 解析server文件配置
     * @param localConfig
     * @param localIndex
     * @return List<LocalSite>
     */
    public static List<ServerSite> parseServerConfig(String localConfig,String localIndex) {
        Properties pro = ConfigReader.getProperties(localConfig) ;
        List<ServerSite> serverSiteList = new ArrayList<ServerSite>() ;
        try {
            String serverTxt = pro.getProperty("server");
            String[] serverStr = serverTxt.split(",");
            int len = serverStr.length;
            for (int i=0;  i<len; i++) {
                String serverFile = "server_"+localIndex+"_"+serverStr[i]+".properties" ;
                Properties serverConfig = getProperties(serverFile) ;
                ServerSite serverSite = new ServerSite() ;
                serverSite.setPath(serverConfig.getProperty("path"));
                serverSite.setHost(serverConfig.getProperty("host"));
                serverSite.setUser(serverConfig.getProperty("user"));
                serverSite.setPwd(serverConfig.getProperty("pwd"));
                serverSite.setProtocolType(serverConfig.getProperty("fileProtocol"));
                serverSite.setPort(serverConfig.getProperty("port"));
                serverSiteList.add(serverSite);
            }
        } catch (Exception e) {
            Logger.loadLogger().error("server配置文件读取出错!");
            System.exit(0);
            //e.printStackTrace();
        }
        return serverSiteList;
    }
    /**
     * 解析本地文件配置
     * @return List<LocalSite>
     */
    public static List<LocalSite> parseLocalConfig() {
        Properties mainConfig = ConfigReader.getProperties(mainConfigName) ;
        List<LocalSite> localSiteList = new ArrayList<LocalSite>() ;
        try {
            String localTxt = mainConfig.getProperty("local");
            String[] localStr = localTxt.split(",");
            int len = localStr.length;
            for (int i=0;  i<len; i++) {
                String localFile = "local_"+localStr[i]+".properties" ;
                Properties localConfig = getProperties(localFile) ;
                LocalSite localSite = new LocalSite() ;
                localSite.setPath(localConfig.getProperty("path"));
                localSite.setServerSiteList(parseServerConfig(localFile, localStr[i]));
                localSiteList.add(localSite);
            }
        } catch (Exception e) {
            //e.printStackTrace();
            Logger.loadLogger().error("配置文件读取出错!");
            System.exit(0);
        }
        return localSiteList;
    }

    /**
     * 获得相关文件内容
     * @param fileName
     * @return Properties
     */
    public static Properties getProperties(String fileName) {
        Properties pro = new Properties() ;
        FileInputStream is = null;
            String filePath = getPath() + "/" + fileName;
        File file = new File(filePath) ;
        if (file.exists()) {
            try {
                is = new FileInputStream(file);
                pro.load(is);
            } catch (Exception e) {
                //e.printStackTrace();
                Logger.loadLogger().error("文件读取错误:" + filePath);
                System.exit(0);
            }
        } else {
            Logger.loadLogger().error("找不到文件:" + filePath);
            System.exit(0);
        }

        return pro;
    }

    /**
     * 获得配置文件路径
     *
     * @return String
     */
    public static String getPath() {
        return System.getProperty("user.dir") +
                "/config";
    }
}
