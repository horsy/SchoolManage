package com.school.publicSomething;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.school.domain.DiskInfo;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class PublicSTH {

	private PublicSTH() {
		// cache.put("rightList", serviceDao.getAllRight());
	};

	public static final int ENABLE = 1;

	public static final int USER_TYPE_OF_ADMIN = 1;

	public static final int USER_TYPE_OF_STUDENT = 3;

	public static final String TITLE = "学生信息管理系统";

	public static final int LOCAL_SCHOOL_TYPE = 0;

	// 系统信息表默认配置
	public static final int SYSTEM_CONFIG_SHOW_LEVEL = 0;
	// 系统信息表默认配置
	public static final int SYSTEM_CONFIG_LOG_STORE_MOUTH = 12;

	public static final int NO_ID = -1;

	public static final String SUCCESS = "success";

	public static final String NODE_TYPE_ROOT = "root";

	public static final String NODE_TYPE_SCHOOL = "school";

	public static final String NODE_TYPE_GRADE = "grade";

	public static final String NODE_TYPE_CLASS = "class";

	public static final String NODE_TYPE_STUDENT = "student";

	public static final int ALL_ROW = -1;

	public static final int NOT_ENABLE_LOGIN = 0;

	// 身份证 号码系数
	public static final int[] IDENTITY_CARD_COEFFICIENT = { 7, 9, 10, 5, 8, 4,
			2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 2 };
	public static final String[] IDENTITY_CARD_LAST_CHAR = { "1", "0", "X",
			"9", "8", "7", "6", "5", "4", "3", "2" };

	public static boolean isCorrentIdentityCard(String identityCard) {
		boolean is = false;
		identityCard = identityCard.trim();
		if (identityCard != null) {
			if (identityCard.length() == 18) {
				if (isNumber(identityCard.substring(0, 18))) {
					int sum = 0;
					for (int i = 0; i < 17; i++) {
						sum += Integer.parseInt(identityCard
								.substring(i, i + 1))
								* PublicSTH.IDENTITY_CARD_COEFFICIENT[i];
					}
					String last = IDENTITY_CARD_LAST_CHAR[sum % 11];
					if (last.equals(identityCard.substring(17))) {
						is = true;
					}
				}
			}
		}

		return is;
	}

	public static final int DEFAULT_ID = 0;

	// 待录入信息
	public static final int REVIEW_NOT_ADD = -1;
	// 待审核
	public static final int REVIEW_NOT = 0;
	// 未通过
	public static final int REVIEW_NOT_PASS = 2;
	// 通过
	public static final int REVIEW_PASS = 1;

	// 登录后用户的权限
	public static final String[] PRIV = { "PRIV_0", "PRIV_1", "PRIV_ERROR" };

	// 登录后访问的页面
	public static final String[] PAGE_LOGINED = { "editStudent", "main",
			"errorInfo" };

	public static final String DEFAULT_PASSWORD = "1234321";

	public static final String DEFAULT_TEACHER_LOGIN_NAME = "ln";

	// ******************no_use*****************************************************

	public static final String VERSION = "1.0.2";

	public static final int DB_VERSION = 6;

	private static PublicSTH publicSTH = new PublicSTH();

	public static PublicSTH getInstance() {
		return publicSTH;
	}

	// web默认端口
	public static final String WEB_PORT = "80";
	// 跨级管理默认端口
	public static final int SOCKET_PORT = 8000;

	// 数据库名称
	public static final String DATABASE_NAME = "school";

	public static final String CREATE_VERSION_TABLE = "create table `t_version`("
			+ "  `id` INTEGER not null auto_increment comment '编号',"
			+ "  `database_version` int(11) comment '数据库版本',"
			+ "  `web_version` VARCHAR(32) comment 'web版本',"
			+ "  primary key (`id`)"
			+ ")ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='版本信息'//"
			+ "insert into t_version (database_version,web_version) values("
			+ DB_VERSION + ",'" + VERSION + "')//";
	public static final String CREATE_ALARM_INFO_TABLE = "CREATE TABLE `t_alarm_info` ("
			+ "  `sipuri` varchar(64) not NULL COMMENT '域名',"
			+ "  `user_id` varchar(64) not NULL COMMENT '流媒体设备ID',"
			+ "  `flag` char(1) not NULL COMMENT '标志 0 开始；1 结束',"
			+ "  `date_time` int(10) not null  COMMENT '报警时间',"
			+ "  `chan_num` int(11) DEFAULT NULL COMMENT '报警通道',"
			+ "  `alarm_type` char(1) not NULL COMMENT '报警类型',"
			+ "  KEY `select_alarm` (`user_id`,`sipuri`,`date_time`,`alarm_type`),"
			+ "  KEY `date_time` (`date_time`)"
			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报警信息'//";

	// 版本信息表名
	private static final String[][] TABLES_TO_CHECK = {
			{ "t_version", CREATE_VERSION_TABLE },
			{ "t_alarm_info", CREATE_ALARM_INFO_TABLE } };

	public static String[][] getTablesToCheck() {
		return TABLES_TO_CHECK;
	}

	// 客户端密码，MD5密钥
	public static final String MD5_SALT_KHD_HEAD = "@jf-ptc";
	// 客户端密码，MD5密钥
	public static final String MD5_SALT_KHD_END = "2012&";
	// 管理员密码，MD5密钥,和SPRING配置文件里面的加密盐要对应
	public static final String MD5_SALT_ADMIN = "{wyytxml,clybq}";

	public static final String ADMIN_LOGIN_NAME = "admin";

	public static final String SIP_TYPE_START = "3";
	public static final String ZF_TYPE_START = "4";
	public static final String GROUP_TYPE = "group";

	public static final String NVR_TYPE = "3";

	private static final String[][] EQUIPMENT_TYPE = { { "0", "服务器" },
			{ "1", "三合一" }, { "2", "四合一" }, { "3", "NVR" }, { "4", "SIP" },
			{ "5", "转发" }, { "6", "客户端" }, { "7", "流媒体" } };

	public static String getEquipmentTypeByCode(String code) {
		String type = "";
		if (code != null) {
			for (int i = 0, len = EQUIPMENT_TYPE.length; i < len; i++) {
				if (code.equals(EQUIPMENT_TYPE[i][0])) {
					type = EQUIPMENT_TYPE[i][1];
					break;
				}
			}
		}
		return type;
	}

	// 磁盘信息
	public static final Map<String, Object> DISK_INFO = getDiskInfo_1();
	public static final List<DiskInfo> DISK_LIST = getDiskInfo();

	// 转发服务器url是在sip服务器url的后面加上ZF+ID
	public static final String CHARACTER_OF_ZF_SIPURI = "zf";
	// 在线
	public static final String ONLINE = "1";
	// 启动成功
	public static final String START_SUCCESS = "1";
	// 注册成功
	public static final String REGISTE_SUCCESS = "1";
	// 特殊字符
	public static final String SPECIAL_CHAR_REGEX = "[^~`!@#$%&*()=|<>\\[\\]{}?.\\\\ ]{1,}";
	// 字母、数字、下划线
	public static final String LOGIN_NAME_REGEX = "^\\w+$";

	public static boolean hasSpecialChar(String str, String regEx) {
		boolean has = false;
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		if (!m.matches()) {
			has = true;
		}
		return has;
	}

	public static boolean isNumber(String numStr) {
		boolean isNumber = false;
		if (numStr == null) {
			return isNumber;
		}
		Pattern pattern = Pattern.compile("^[0-9]\\d*$");
		Matcher matcher = pattern.matcher(numStr);
		if (matcher.matches()) {
			isNumber = true;
		}
		return isNumber;
	}
	
	public static String byteToBase64(byte[]bytes){
		return Base64.encode(bytes);
	}

	public static byte[] imageToByte(String filePath) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filePath);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];

		try {
			for (int readNum; (readNum = fis.read(buf)) != -1;) {
				bos.write(buf, 0, readNum);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		byte[] bytes = bos.toByteArray();
		
		try {
			bos.close();
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bytes;
	}

	public static String getPassword() {
		// 动态生成密码
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyyMMdd");
		String temp = sdf.format(new java.util.Date());
		String passwd = "";

		temp = String.valueOf(Integer.parseInt(temp.substring(0, 2))
				+ Integer.parseInt(temp.substring(2, 4))
				+ Integer.parseInt(temp.substring(4, 6))
				+ Integer.parseInt(temp.substring(6)));
		passwd = temp;
		int tempInt = 0;
		int j = 1;
		while (temp.length() > 1) {
			tempInt = 0;
			for (int i = 0; i < temp.length(); i++) {
				tempInt += Integer.parseInt(temp.substring(i, i + 1));
			}
			temp = String.valueOf(tempInt);
			if (j % 2 > 0) {
				passwd = passwd + temp;
			} else {
				passwd = temp + passwd;
			}
			j++;
		}

		java.text.NumberFormat nf = new java.text.DecimalFormat("000000");

		passwd = nf.format(Integer.parseInt(passwd));
		return passwd;
	}

	/**
	 * @description 检测端口是否正确
	 * @param portStr
	 *            待检测端口
	 * @return true 端口正确，false 端口错误
	 */
	public static boolean isPort(String portStr) {
		boolean isport = false;
		Pattern pattern = Pattern.compile("\\d*");
		Matcher matcher = pattern.matcher(portStr);
		if (matcher.matches()) {
			int port = 0;
			try {
				port = Integer.parseInt(portStr);
			} catch (NumberFormatException ne) {
				isport = false;
			}
			if (port >= 0 && port < 65535) {
				isport = true;
			}
		}
		return isport;
	}

	/**
	 * 
	 * @param ip
	 *            待检测的IP地址
	 * @return true 错误IP地址格式，false 正确的IP地址格式
	 */
	public static boolean isIncorrectIP(String ip) {
		Pattern pattern = Pattern
				.compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
		Matcher matcher = pattern.matcher(ip);
		// System.out.println(matcher.matches());
		return (!matcher.matches());
	}

	/**
	 * 是否为字母数字下划线组成
	 * 
	 * @param str
	 *            待检测字符串
	 * @param regEx
	 *            正则表达式
	 * @return true 没有特殊字符,false 有特殊字符
	 */
	public static boolean isCharAndDownLine(String str, String regEx) {
		boolean has = false;
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		if (m.matches()) {
			has = true;
		}
		return has;
	}

	/**
	 * 获取数据库的路径
	 * 
	 * @return 数据库路径文件夹
	 */
	public String getDataPath() {
		String dataPath = "";

		File f = new File(this.getClass().getResource("/").getPath());
		// System.out.println(f);
		String dataPathTemp = f.getPath();
		dataPathTemp = dataPathTemp.substring(0, dataPathTemp
				.indexOf("webapps"));

		// 替换%20为空格,在windows下不能识别%20是空格的意思
		dataPathTemp = dataPathTemp.replace("%20", " ");

		java.io.File mysql55File = new java.io.File(dataPathTemp
				+ "bin\\MySQL Server 5.5\\data");
		java.io.File mysql50File = new java.io.File(dataPathTemp
				+ "bin\\MySQL Server 5.0\\data");
		if (mysql55File.exists()) {
			dataPath = dataPathTemp + "bin\\MySQL Server 5.5\\data";
		} else if (mysql50File.exists()) {
			dataPath = dataPathTemp + "bin\\MySQL Server 5.0\\data";
		}
		// dataPath = dataPath + File.separator + PublicSTH.DATABASE_NAME;
		return dataPath;

	}

	public static String getDataBaseSize() {
		long dataSize = getFileSize(new File(PublicSTH.getInstance()
				.getDataPath()
				+ File.separator + PublicSTH.DATABASE_NAME));
		long ibdata1Size = getFileSize(new File(PublicSTH.getInstance()
				.getDataPath()
				+ File.separator + "ibdata1"));
		return getSize(dataSize + ibdata1Size);
	}

	public static String getSize(long dataSize) {
		String fileSizeString = "";
		DecimalFormat df = new DecimalFormat("#");
		if (dataSize < 1024) {
			fileSizeString = df.format((double) dataSize) + "B";
		} else if (dataSize < 1048576) {
			fileSizeString = df.format((double) dataSize / 1024) + "K";
		} else if (dataSize < 1073741824) {
			fileSizeString = df.format((double) dataSize / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) dataSize / 1073741824) + "G";
		}
		// System.out.println(fileSizeString);
		return fileSizeString;
	}

	/**
	 * 获取文件夹大小
	 * 
	 * @param directory
	 *            文件夹
	 * @return
	 */
	public static long getFileSize(File directory) {

		long size = 0;
		if (directory.isDirectory()) {
			File flist[] = directory.listFiles();
			for (int i = 0; i < flist.length; i++) {
				if (flist[i].isDirectory()) {
					size = size + getFileSize(flist[i]);
				} else {
					size = size + flist[i].length();
				}
			}
		} else {
			size = directory.length();
		}

		return size;
	}

	public static List<DiskInfo> getDiskInfo() {
		List<DiskInfo> diskList = new ArrayList<DiskInfo>();
		File[] dev = File.listRoots();
		// long unit = 1024 * 1024 * 1024;

		for (File file : dev) {

			DiskInfo diskInfo = new DiskInfo();

			diskInfo.setDiskName(file.getPath().replace(":\\", ""));// 获取磁盘的路径名称

			long usableSpace = file.getUsableSpace();// 获取磁盘的可用空间

			diskInfo.setUseableSize(getSize(usableSpace));

			long totalSpace = file.getTotalSpace();// 获取磁盘的总空间

			diskInfo.setTotalSpace(getSize(totalSpace));

			// System.out.println("磁盘名称为：" + diskInfo.getDiskName());
			//
			// System.out.println("总空间为：" + diskInfo.getTotalSpace() + "GB");
			//
			// System.out.println("可用空间为：" + diskInfo.getUseableSize() + "GB");
			long useablePercent = 0;
			if (totalSpace > 0) {
				useablePercent = (usableSpace * 100 / totalSpace);
			}
			diskInfo.setUseablePercent(useablePercent);
			diskList.add(diskInfo);

		}

		return diskList;
	}

	public static Map<String, Object> getDiskInfo_1() {
		Map<String, Object> diskMap = new HashMap<String, Object>();
		File[] dev = File.listRoots();
		// long unit = 1024 * 1024 * 1024;

		for (File file : dev) {

			DiskInfo diskInfo = new DiskInfo();

			diskInfo.setDiskName(file.getPath());// 获取磁盘的路径名称

			long usableSpace = file.getUsableSpace();// 获取磁盘的可用空间

			diskInfo.setUseableSize(getSize(usableSpace));

			long totalSpace = file.getTotalSpace();// 获取磁盘的总空间

			diskInfo.setTotalSpace(getSize(totalSpace));

			// System.out.println("磁盘名称为：" + diskInfo.getDiskName());
			//
			// System.out.println("总空间为：" + diskInfo.getTotalSpace() + "GB");
			//
			// System.out.println("可用空间为：" + diskInfo.getUseableSize() + "GB");

			diskMap.put(diskInfo.getDiskName(), diskInfo);

		}

		return diskMap;
	}

	/**
	 * 获取网页内容
	 * 
	 * @param url
	 *            网页url地址
	 * @return 网页内容
	 * @throws IOException
	 */
	public static String getPageContent(String url) throws IOException {
		StringBuffer page = new StringBuffer();
		URL site = new URL(url);
		URLConnection agent = site.openConnection();
		InputStream is = agent.getInputStream();
		BufferedReader input = new BufferedReader(new InputStreamReader(is));
		int ch;
		while ((ch = input.read()) != -1) {
			page.append((char) ch);
		}
		input.close();
		return page.toString();
	}

	public static final String ONLINE_HTML = "<font color='green'>在线</font>";

	public static final String OFF_LINE_HTML = "<font color='red'>离线</font>";

	public static final String USER_LIST_SPLIT = "_obj";

	public static final String BR = "&#13;";

	private static final String[] RIGHT_NAME_ARRAY = { "名称", "通道标签", "流媒体参数查询",
			"远程参数设置", "流媒体远程管理", "视频上传控制", "报警管理", "服务器时间管理", "服务器远程管理", "云台锁定" };

	public static List<String> getRightNameList() {
		return Arrays.asList(RIGHT_NAME_ARRAY);
	}

	public static final int PAGE_SIZE = 15;

	public static final String TRUE_STRING = "true";

	public static final String FALSE_STRING = "false";

	public static final String INSTRUCTION_TABLES = " t_admin t_alarm_info t_client_right t_equipment t_equipment_type t_log t_log_type t_nvr_work_state t_sip_work_state t_update_table t_update_upload_alarm t_user t_user_right t_version";

	public static final String INSTRUCTION_TABLES_BACKUP = " t_admin t_client_right t_equipment t_equipment_type t_log t_log_type t_nvr_work_state t_sip_work_state t_update_table t_update_upload_alarm t_user t_user_right t_version";

	public static final String SQL_SPLIT_CHAR = "//";

	public static final String INITIALIZATION_DATABASE = "drop database if exists `instruction`//"
			+ "create database `instruction`//"
			+ "use instruction//"
			// 管理员表
			+ "CREATE TABLE `t_admin` ("
			+ "  `admin_id` int(11) auto_increment NOT NULL COMMENT 'ID号',"
			+ "  `login_name` varchar(16) NOT NULL COMMENT '登录名',"
			+ "  `login_password` varchar(32) NOT NULL COMMENT '登录密码 MD5加密',"
			+ "  `user_name` varchar(16) NOT NULL COMMENT '显示名',"
			+ "  `last_login` datetime DEFAULT NULL COMMENT '最后登录时间',"
			+ "  `last_ip` varchar(20) DEFAULT NULL COMMENT '最后登录IP',"
			+ "  PRIMARY KEY (`admin_id`),"
			+ "  UNIQUE KEY `IDXU_t_admin_login_name` (`login_name`)"
			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员信息'//"
			+ "insert into t_admin (login_name,login_password,user_name) values('admin','3daf2f3c647c79d847980684168bd722','admin')//"
			// 指令服务器客户端表
			+ "CREATE TABLE `t_user` ("
			+ "  `user_id` int(11) auto_increment NOT NULL COMMENT 'ID号',"
			+ "  `login_name` varchar(32) NOT NULL COMMENT '登录名 客户端的登录名',"
			+ "  `login_password` varchar(24) NOT NULL COMMENT '登录密码 客户端的登录密码',"
			+ "  `display_name` varchar(32) NOT NULL COMMENT '显示名 显示在客户端和其他界面上用',"
			+ "  `version` varchar(32) DEFAULT NULL COMMENT '版本信息 客户端版本号。',"
			+ "  `last_date` datetime DEFAULT NULL COMMENT '最后登录时间',"
			+ "  `last_ip` varchar(20) DEFAULT NULL COMMENT '最后登录IP',"
			+ "  `is_online` char(1) NOT NULL DEFAULT '0' COMMENT '是否在线 0 不在线,1在线',"
			+ "  `right_id` int(11) not null comment '权限Id',"
			+ "  PRIMARY KEY (`user_id`),"
			+ "  UNIQUE KEY `IDXU_t_user_login_name` (`login_name`)"
			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户端信息表'//"
			// 设备类型表
			+ "CREATE TABLE `t_equipment_type` ("
			+ "  `equipment_type_id` varchar(3) NOT NULL COMMENT '设备类型ID',"
			+ "  `equipment_type` varchar(20) NOT NULL COMMENT '设备类型',"
			+ "  PRIMARY KEY (`equipment_type_id`),"
			+ "  UNIQUE KEY `IDXU_t_equipmeype_equipmentype` (`equipment_type`)"
			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备类型'//"
			+ "INSERT INTO `t_equipment_type` (`equipment_type_id`,`equipment_type`) VALUES ('000','超级管理员'),('001','巡查管理员'),('002','会议管理员'),('100','远程客户端'),('101','巡查主机'),('102','网络数字矩阵内'),('103','网络数字矩阵外'),('104','画面分割器'),('105','中心存储服务器'),('107','电子地图工作站'),('108','报警工作站'),('109','巡查电视墙'),('110','巡查指挥电视墙'),('111','巡查指挥控制器'),('112','巡查控制器'),('200','流媒体服务器'),('201','NVR存储服务器'),('220','带存储IPC/DVS(H)'),('221','不带存储IPC/DVS(H)'),('222','带存储IPC/DVS(L)'),('223','不带存储IPC/DVS(L)'),('224','流媒体服务器(H)'),('225','流媒媒体服务器(DL)'),('226','流媒体服务器(DH)'),('227','带存储IPC/DVS(DH)'),('228','不带存储IPC/DVS(DH)'),('230','流媒体服务器(TD)'),('231','带存储IPC(TD)'),('232','不带存储IPC(TD)'),('233','流媒体服务器(HB)'),('234','带存储IPC/DVS(HB)'),('235','不带存储IPC/DVS(HB)'),('300','SIP网关'),('301','SIP路由'),('400','转发服务器')//"
			// 设备表
			+ "CREATE TABLE `t_equipment` ("
			+ "  `equipment_id` int(11) auto_increment NOT NULL COMMENT 'ID号',"
			+ "  `parent_id` int(11) NOT NULL COMMENT '父ID 顶级ID的父ID为0',"
			+ "  `sipuri` varchar(64) NOT NULL COMMENT 'SIP域名',"
			+ "  `display_name` varchar(64) NOT NULL COMMENT '别名 显示用',"
			+ "  `version` varchar(255) NOT NULL COMMENT '版本信息',"
			+ "  `database_version` varchar(20) DEFAULT NULL COMMENT '数据库版本',"
			+ "  `is_online` char(1) NOT NULL COMMENT '是否在线 0 不在线；1 在线',"
			+ "  `is_upload_alarm` CHAR(6) default '000000' comment '是否上传报警信息 0 不上传；1 上传',"
			+ "  `is_lock_ptz_khd` char(1) DEFAULT NULL COMMENT '是否锁定云台 0 不锁定；1 锁定(客户端)',"
			+ "  `is_lock_ptz_up` char(1) DEFAULT NULL COMMENT '是否锁定云台 0 不锁定；1 锁定(上级)',"
			+ "  `equipment_type_id` varchar(3) DEFAULT NULL COMMENT '设备类型 转发、SIP',"
			+ "  `dog_id` VARCHAR(20) comment '软件狗ID',"
			+ "  `ip` VARCHAR(20) comment 'IP地址',"
			+ "  `port` VARCHAR(5) comment '端口',"
			+ "  PRIMARY KEY (`equipment_id`),"
			+ "  UNIQUE KEY `IDXU_t_equipment_sipuri` (`sipuri`),"
			+ "  KEY `parent_id` (`parent_id`)"
			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备信息表'//"
			// SIP服务器当前状态表
			+ "CREATE TABLE `t_sip_work_state` ("
			+ "  `sipuri` varchar(64) NOT NULL COMMENT 'SIP域名',"
			+ "  `sys_state` varchar(1) NOT NULL COMMENT '服务器状态 0 启动失败；1 正常运行',"
			+ "  `registe_info` varchar(1) NOT NULL COMMENT '注册状态 0 注册成功；1 注册失败',"
			+ "  `cpu` varchar(20) DEFAULT NULL COMMENT 'cpu信息',"
			+ "  `memory` varchar(128) DEFAULT NULL COMMENT '内存信息',"
			+ "  `type` varchar(2) NOT NULL COMMENT '设备类型',"
			+ "  `sip_ip_out` varchar(15) default null comment 'sip服务器外网IP地址',"
			+ "  `sip_ip_in` varchar(15) default null comment 'sip服务器内网IP地址',"
			+ "  `sip_port` varchar(5) default null comment 'sip服务端口',"
			+ "  `web_ip` VARCHAR(15) comment '管理中心IP地址',"
			+ "  `web_port` VARCHAR(5) default '80' comment '管理中心端口',"
			+ "  `socket_port` int(11) default 8000 comment '跨级管理端口',"
			+ "  PRIMARY KEY (`sipuri`)"
			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='SIP服务器当前信息'//"
			// NVR存储服务器当前状态表
			+ "CREATE TABLE `t_nvr_work_state` ("
			+ "  `sipuri` varchar(64) NOT NULL COMMENT 'nvr域名',"
			+ "  `is_timer_store` char(1) DEFAULT NULL COMMENT '是否按时间计划录像 0 否；1是',"
			+ "  `packaging_time` int(11) DEFAULT NULL COMMENT '打包时间 分钟',"
			+ "  `disk_start` varchar(2) DEFAULT NULL COMMENT '录像存盘开始磁盘',"
			+ "  `disk_end` varchar(2) DEFAULT NULL COMMENT '录像存盘结束磁盘',"
			+ "  `disk_info` varchar(128) DEFAULT NULL COMMENT '磁盘信息',"
			+ "  PRIMARY KEY (`sipuri`)"
			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='NVR存储服务器信息'//"
			// 日志类别表
			+ "CREATE TABLE `t_log_type` ("
			+ "  `type_id` int(11) NOT NULL COMMENT 'ID号',"
			+ "  `log_type` varchar(10) NOT NULL COMMENT '类别名称',"
			+ "  PRIMARY KEY (`type_id`),"
			+ "  UNIQUE KEY `IDXU_t_log_type_log_type` (`log_type`)"
			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志类别'//"
			+ "insert into `t_log_type` (`type_id`,`log_type`) values (1,'客户端日志'),(2,'服务器日志'),(3,'管理员日志')//"
			// 日志表
			+ "CREATE TABLE `t_log` ("
			+ "  `type_id` int(11) DEFAULT NULL COMMENT '日志类别ID',"
			+ "  `log_content` varchar(128) NOT NULL COMMENT '日志内容',"
			+ "  `log_time` datetime DEFAULT NULL COMMENT '日志时间',"
			+ "  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',"
			+ "  `user_name` varchar(32) DEFAULT NULL COMMENT '用户名',"
			+ "  `ip` varchar(20) DEFAULT NULL COMMENT 'IP地址',"
			+ "  KEY `IDX_t_log_log_time` (`log_time`)"
			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志信息'//"
			// 用户权限表
			+ "CREATE TABLE `t_user_right` ("
			+ "  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',"
			+ "  `equipment_id` int(11) DEFAULT NULL COMMENT '设备ID',"
			+ "  `enable` char(1) NOT NULL DEFAULT '1' COMMENT '是否有权限',"
			+ "  primary key (`user_id`,`equipment_id`)"
			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户权限'//"
			// 更新标志表(table)
			+ "CREATE TABLE `t_update_table` ("
			+ "  `table_name` varchar(30) NOT NULL COMMENT '表名称',"
			+ "  `is_update` char(1) NOT NULL DEFAULT '0' COMMENT '是否更新 0 无更新；1 更新',"
			+ "  PRIMARY KEY (`table_name`)"
			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='更新表'//"
			+ "insert into `t_update_table` (`table_name`,`is_update`) values('t_equipment','0'),('t_update_upload_alarm','0')//"
			// 更新标志表(报警信息)
			+ "CREATE TABLE `t_update_upload_alarm` ("
			+ "  `equipment_id` int(11) DEFAULT NULL COMMENT '设备ID',"
			+ "  `is_upload_alarm` CHAR(6) default '000000' comment ' 0 更新为不上传；1 更新为上传；',"
			+ "  PRIMARY KEY (`equipment_id`)"
			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报警信息上传更新表'//"
			// 报警信息表
			+ "CREATE TABLE `t_alarm_info` ("
			+ "  `sipuri` varchar(64) not NULL COMMENT '域名',"
			+ "  `user_id` varchar(64) not NULL COMMENT '流媒体设备ID',"
			+ "  `flag` char(1) not NULL COMMENT '标志 0 开始；1 结束',"
			+ "  `date_time` int(10) not null  COMMENT '报警时间',"
			+ "  `chan_num` int(11) DEFAULT NULL COMMENT '报警通道',"
			+ "  `alarm_type` char(1) not NULL COMMENT '报警类型',"
			+ "  KEY `select_alarm` (`user_id`,`sipuri`,`date_time`,`alarm_type`),"
			+ "  KEY `date_time` (`date_time`)"
			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报警信息'//"
			// 客户端权限表
			+ "create table  `t_client_right`("
			+ "  `right_id`        int(11) not null auto_increment comment '权限ID',"
			+ "  `channel_label`   CHAR(1) not null comment '通道标签 主要是流媒体通道标签方案新增、保存、修改、设置',"
			+ "  `distance_set_view` CHAR(1) not null comment '远程设置查询 主要是对流媒体的参数进行查询',"
			+ "  `distance_set_write` CHAR(1) not null comment '远程参数设置 主要是对流媒体的参数进行设置(注：有设置功能就有查询)',"
			+ "  `stream_media_control` CHAR(1) not null comment '流媒体管理 关机、重启功能',"
			+ "  `upload_video_stream_media` CHAR(1) not null comment '流媒体上传数据 控制流媒体数据向服务器上传',"
			+ "  `alarm_control`   CHAR(1) not null comment '报警管理 包括历史数据报警查询和实时报警查询',"
			+ "  `server_time`     CHAR(1) not null comment '服务器时间 查询和设置服务器的系统时间',"
			+ "  `server_control`  CHAR(1) not null comment '服务器管理 关机、重启功能',"
			+ "  `ptz_control`     CHAR(1) not null comment '云台控制 控制云台的锁定功能，主要是对上级的锁定和本级的锁定',"
			+ "  `right_name`      VARCHAR(10) not null comment '权限名称',"
			+ "  primary key (`right_id`)"
			+ ")ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户端权限'//"
			+ "insert into t_client_right(channel_label,distance_set_view,distance_set_write,stream_media_control,upload_video_stream_media,alarm_control,server_time,server_control,ptz_control,right_name)values('0','0','0','0','0','0','0','0','0','一级权限'),('1','1','1','1','1','0','0','0','0','二级权限'),('1','1','1','1','1','1','1','1','1','三级权限')//"
			// 版本信息表
			+ "create table `t_version`("
			+ "  `id` INTEGER not null auto_increment comment '编号',"
			+ "  `database_version` int(11) comment '数据库版本',"
			+ "  `web_version` VARCHAR(32) comment 'web版本',"
			+ "  primary key (`id`)"
			+ ")ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='版本信息'//"
			+ "insert into t_version (database_version,web_version) values("
			+ DB_VERSION
			+ ",'"
			+ VERSION
			+ "')//"
			// 报警信息条数表
			+ "CREATE TABLE `t_alarm_info_num` ("
			+ "  `row_num` int(11) not NULL ,"
			+ "  primary KEY (`row_num`)"
			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8//"
			+ "insert into t_alarm_info_num (row_num) select count(*) from t_alarm_info where date_time>0//"
			// 控制报警信息条数存储过程
			+ "drop procedure if exists  delAlarmInfo//"

			+ "create procedure delAlarmInfo()"
			+ "begin"
			+ "	declare sqlStr varchar(20000) default '';"
			+ "	declare real_num int;"
			+ "	declare save_num int;"
			+ "	declare num_gate int;"
			+ "	set save_num=10000000;"
			+ "	set num_gate=10001000;"
			+ "	set real_num=(select row_num from t_alarm_info_num);"
			+ "	if real_num>num_gate then"
			+ "		set @del_num=real_num-save_num;"
			+ "		set @del_real=0;"
			+ "		set @step=100;"
			+ "		while @del_num>0 do"
			+ "			prepare sqlStr from 'delete from t_alarm_info order by date_time asc limit ?';"
			+ "			execute sqlStr using @step;"
			+ "			deallocate prepare sqlStr;"
			+ "			set @del_num=@del_num-@step;"
			+ "			set @del_real=@del_real+@step;"
			+ "		end while;"
			+ "		prepare sqlStr from 'update t_alarm_info_num set row_num=row_num-?';"
			+ "		execute sqlStr using @del_real;"
			+ "		deallocate prepare sqlStr;"
			+ "	end if;"
			+ "end//"

			// 设置备份账号
			+ "grant select,lock tables,reload on *.* to bkuser@127.0.0.1 identified by \"jfclientuser\"//"

			// 设置远程连接账号
			+ "grant select,insert,update,delete,execute on instruction.* to jfuser@\"%\" identified by \"jfkjyfb\"//";
}
