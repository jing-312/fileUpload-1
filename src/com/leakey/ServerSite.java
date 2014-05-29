package com.leakey;

/**
 * 服务器站点类
 */
public class ServerSite {
	/**
	 * 主机名
	 */
    private String host ;
    /**
     * 用户名
     */
    private String user ;
    /**
     * 密码
     */
    private String pwd ;
    /**
     *  端口
     */
    private String port ;
    /**
     * 服务器站点路径
     */
    private String path ;
    /**
     * 登陆协议
     */
    private String protocolType ;
    
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

	public String getProtocolType() {
		return protocolType;
	}

	public void setProtocolType(String protocolType) {
		this.protocolType = protocolType;
	}

  
}
