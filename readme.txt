###################################
# 文件上传工具，可以将本地文件或目录传到多台服务器上,目前只支持ftp,sftp文件传输协议,尚不支持中文文件或目录。
# author: 瞌睡虫
# email:easy4php@qq.com
# site: http://www.easy4php.com/
###################################
使用说明：
1.安装完本程序后，右键选中某个文件或目录就会出现上传到服务器的菜单。
2.打开config目录，里面有如何配置上传文件的参考例子。
3.config.properties:  
	定义了本地要上传的两个站点:site1与site2(多个站点用","隔开.)
4.local_site1.properties:
	定义了site1的相关信息: path表示site1所在的本地路径(记住用两个斜杠"\\",严格区分大小写), server表示要上传到的两个服务器127,128
5.local_site2.properties:
	同上
6.server_site1_127.properties
	定义了本地site1站点上传127服务器的相关信息:若账号是用ssh2登陆的则fileProtocol值为sftp，与之对应的port为22
	如果是ftp登陆账号则fileProtocol值为ftp与之对应的port为21
7.server_site1_128.properties
	定义了本地site1站点上传128服务器的相关信息
8.server_site2_129.properties
	定义了本地site2站点上传129服务器的相关信息
9.log4j.properties
	日志配置，默认不用更改。当你每次点击上传到服务器菜单时候会把相关信息记录到upload.log日志文件里，以方便调试及查看。
	

