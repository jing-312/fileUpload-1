package com.leakey;

import com.zehon.BatchTransferProgressDefault;
import com.zehon.FileTransferStatus;
import com.zehon.exception.FileTransferException;
import com.zehon.ftp.FTP;
import com.zehon.sftp.SFTP;

/**
 * 文件传输类
 */
public class FileTransfer {
	/**
	 * 本地上传对象类
	 */
    private LocalUploadObject localUploadObject;
    /**
     * 构造函数
     * @param localUploadObject
     */
    public FileTransfer(LocalUploadObject localUploadObject) {
          this.localUploadObject = localUploadObject;
    }
    /**
	 * 开始运行
	 */
    public void run() {
        if (this.localUploadObject.isFolder()) {
            this.uploadFolder();
        } else {
            this.uploadFile();
        }
    }
    /**
	 * 上传文件夹
	 */
    public void uploadFolder() {
        Logger.loadLogger().info("begin upload local folder:" + this.localUploadObject.name + " to serverSite:" + this.localUploadObject.serverSite.getHost() + ":" + this.localUploadObject.uploadServerPath);
        int status = 0;
        try {
            if (this.localUploadObject.serverSite.getProtocolType().equals("ftp")) {
                //多线程传输，ftp服务端需取消连接数限制，否则超过连接数限制则会自动被断开,不支持中文文件,不支持端口选择
                status = FTP.sendFolder(this.localUploadObject.name, this.localUploadObject.uploadServerPath, new BatchTransferProgressDefault(), this.localUploadObject.serverSite.getHost(), this.localUploadObject.serverSite.getUser(), this.localUploadObject.serverSite.getPwd());

            } else if (this.localUploadObject.serverSite.getProtocolType().equals("sftp")) {
                status = SFTP.sendFolder(this.localUploadObject.name, this.localUploadObject.uploadServerPath, new BatchTransferProgressDefault(), this.localUploadObject.serverSite.getHost(), this.localUploadObject.serverSite.getUser(), this.localUploadObject.serverSite.getPwd());

            } else {
                Logger.loadLogger().error("config error");
            }
            if (FileTransferStatus.SUCCESS == status) {
                Logger.loadLogger().info("upload success");
            } else if (FileTransferStatus.FAILURE == status) {
                Logger.loadLogger().error("upload Failed");
            }
        } catch (FileTransferException e) {
            Logger.loadLogger().error("upload error");
            //e.printStackTrace();
        }


    }
    /**
	 * 上传文件
	 */
    public void uploadFile() {
        Logger.loadLogger().info("begin upload local file:" + this.localUploadObject.name + " to serverSite:" + this.localUploadObject.serverSite.getHost() + ":" + this.localUploadObject.uploadServerPath);
        int status = 0;
        try {
            if (this.localUploadObject.serverSite.getProtocolType().equals("ftp")) {
                status = FTP.sendFile(this.localUploadObject.name, this.localUploadObject.uploadServerPath, this.localUploadObject.serverSite.getHost(), this.localUploadObject.serverSite.getUser(), this.localUploadObject.serverSite.getPwd());

            } else if (this.localUploadObject.serverSite.getProtocolType().equals("sftp")) {
                status = SFTP.sendFile(this.localUploadObject.name, this.localUploadObject.uploadServerPath, this.localUploadObject.serverSite.getHost(), this.localUploadObject.serverSite.getUser(), this.localUploadObject.serverSite.getPwd());

            } else {
                Logger.loadLogger().error("config error");
            }
            if (FileTransferStatus.SUCCESS == status) {
                Logger.loadLogger().info("upload success");
            } else if (FileTransferStatus.FAILURE == status) {
                Logger.loadLogger().error("upload Failed");
            }
        } catch (FileTransferException e) {
            Logger.loadLogger().error("upload error");
            //e.printStackTrace();
        }
    }
}
