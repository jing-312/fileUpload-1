package com.leakey;
import java.util.List;

/**
 * 上传类
 */
public class Upload {
    /**
     * 主函数
     * @param args
     */
    public static void main(String[] args) {
          if (args.length <= 0) {
            Logger.loadLogger().error("请输入要上传的文件");
            //System.out.println("请输入要上传的文件");
            System.exit(0);
        }
        List<LocalSite> localSiteList = ConfigReader.parseLocalConfig();
        //String fileName = "F:\\test\\haha";
        String fileName = args[0];
        LocalUploadObject localUploadObject = new LocalUploadObject(fileName);
        if (!localUploadObject.isExist()) {
            Logger.loadLogger().error(localUploadObject.name + "不存在!");
            System.exit(0);
        }
        int count = 0;
        for (LocalSite localSite : localSiteList) {
            if (localSite.isValidPath(localUploadObject.name)) {
                localUploadObject.localsite = localSite;
                localUploadObject.setRelativePath();
                count++;
                localSite.transferToServer(localUploadObject);
                break;
            }
        }
        if (count <= 0) {
            Logger.loadLogger().error(fileName + " 找不到此文件对应的配置信息");
        }
    }

   
}
