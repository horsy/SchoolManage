package com.school.service;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.school.dao.DatabaseDao;
import com.school.publicSomething.PublicSTH;

@Component
public class Initialization extends Thread {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	private DatabaseDao databaseDao;

	public void run() {

		// timer 定时连接、检查、初始化数据库，避免第二天登录报500错误。
		java.util.Timer timer = new java.util.Timer();
		java.text.SimpleDateFormat sdfDate = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		java.text.SimpleDateFormat sdfDateTime = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String date = sdfDate.format(new java.util.Date());
		java.util.Date dateTime = null;
		try {
			dateTime = sdfDateTime.parse(date + " 05:30:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				// 初始化数据库
				// String sqlStr =
				// "select schema_name from information_schema.schemata where schema_name='"
				// + PublicSTH.DATABASE_NAME + "'";
				// SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlStr);
				// if (!rs.next()) {
				// String[] sqlArray = PublicSTH.INITIALIZATION_DATABASE
				// .split(PublicSTH.SQL_SPLIT_CHAR);
				// for (int i = 0, len = sqlArray.length; i < len; i++) {
				// jdbcTemplate.execute(sqlArray[i]);
				// }
				// } else {
				// String[][] tablesToCheck = PublicSTH.getTablesToCheck();
				// for (int i = 0, len = tablesToCheck.length; i < len; i++) {
				// if (!hasTable(tablesToCheck[i][0])) {
				// createTable(tablesToCheck[i][1]);
				// }
				// }
				//
				// int oldDatabaseVersion = getDatabaseVersion();
				// if (getDatabaseVersion() < PublicSTH.DB_VERSION) {
				// updateTo6(oldDatabaseVersion);
				// updateDatabaseVersion();
				// }
				// }
				backup();
			}
		}, dateTime, 86400000);
	}

	private void backup() {
		File[] files = File.listRoots();
		String systemPath = System.getenv("SystemRoot");
		// System.out.println("systemPath="+systemPath);
		String path = "";
		long size = 0;
		boolean canBackup = false;

		for (File file : files) {
			if (file.canWrite()) {
				if (file.getUsableSpace() > size) {
					if (!systemPath.startsWith(file.getPath())) {
						size = file.getUsableSpace();
						path = file.getPath();
						canBackup = true;
					}
				}
			}

		}
		if (canBackup) {
			try {
				databaseDao.backup(path + "\\autoback\\", true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 更新数据库，增加报警信息存储过程
	 * 
	 * @param oldDatabaseVersion
	 */
	private void updateTo6(int oldDatabaseVersion) {
		// this.updateTo4(oldDatabaseVersion);
		int databaseVersion = 6;
		if (oldDatabaseVersion < databaseVersion) {
			String sqlStr = "drop procedure if exists "
					+ PublicSTH.DATABASE_NAME + ".delAlarmInfo";
			this.jdbcTemplate.execute(sqlStr);
			sqlStr = "create procedure "
					+ PublicSTH.DATABASE_NAME
					+ ".delAlarmInfo()"
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
					+ "		deallocate prepare sqlStr;" + "	end if;" + "end";
			this.jdbcTemplate.execute(sqlStr);
		}
	}

	/**
	 * 更新数据库版本、web版本标志
	 */
	private void updateDatabaseVersion() {
		String sqlStr = "update " + PublicSTH.DATABASE_NAME
				+ ".t_version set database_version=?,web_version=?";
		Object[] obj = { PublicSTH.DB_VERSION, PublicSTH.VERSION };
		this.jdbcTemplate.update(sqlStr, obj);
	}

	//
	// /**
	// * 更新数据库版本为4,增加SIP服务器-管理中心的跨级管理端口
	// *
	// * @param oldDatabaseVersion
	// */
	// private void updateTo4(int oldDatabaseVersion) {
	// this.updateTo3(oldDatabaseVersion);
	// int databaseVersion = 4;
	// if (oldDatabaseVersion < databaseVersion) {
	// String sqlStr = "";
	// sqlStr = "describe " + PublicSTH.DATABASE_NAME
	// + ".t_sip_work_state socket_port";
	// SqlRowSet rs = this.jdbcTempalte.queryForRowSet(sqlStr);
	// if (!rs.next()) {
	// sqlStr = "alter table "
	// + PublicSTH.DATABASE_NAME
	// +
	// ".t_sip_work_state add socket_port int(11) default 8000 comment '跨级管理端口'";
	// this.jdbcTempalte.update(sqlStr);
	// }
	// }
	// }
	//
	// /**
	// * 更新数据库版本为3，增加SIP服务器的WEB端口和IP地址
	// *
	// * @param oldDatabaseVersion
	// * 原数据库版本号
	// */
	// private void updateTo3(int oldDatabaseVersion) {
	// this.updateTo2(oldDatabaseVersion);
	// int databaseVersion = 3;
	// if (oldDatabaseVersion < databaseVersion) {
	//
	// String sqlStr = "";
	// sqlStr = "describe " + PublicSTH.DATABASE_NAME
	// + ".t_sip_work_state web_ip";
	// SqlRowSet rs = this.jdbcTempalte.queryForRowSet(sqlStr);
	// if (!rs.next()) {
	// sqlStr = "alter table "
	// + PublicSTH.DATABASE_NAME
	// + ".t_sip_work_state add web_ip varchar(15) comment '管理中心IP地址'";
	// this.jdbcTempalte.update(sqlStr);
	// }
	// sqlStr = "describe " + PublicSTH.DATABASE_NAME
	// + ".t_sip_work_state web_port";
	// rs = this.jdbcTempalte.queryForRowSet(sqlStr);
	// if (!rs.next()) {
	// sqlStr = "alter table "
	// + PublicSTH.DATABASE_NAME
	// +
	// ".t_sip_work_state add web_port varchar(5) default '80' comment '管理中心端口'";
	// this.jdbcTempalte.update(sqlStr);
	// }
	//
	// }
	// }
	//
	// /**
	// * 更新数据库版本为2，删除t_alarm_info主键；增加索引select_alarm
	// *
	// * @param oldDatabaseVersion
	// * 原数据库版本号
	// */
	// private void updateTo2(int oldDatabaseVersion) {
	// this.updateTo1(oldDatabaseVersion);
	// int databaseVersion = 2;
	// if (oldDatabaseVersion < databaseVersion) {
	// String sqlStr = "";
	// sqlStr = "show index from t_alarm_info from "
	// + PublicSTH.DATABASE_NAME + " where Key_name='PRIMARY'";
	// SqlRowSet rs = this.jdbcTempalte.queryForRowSet(sqlStr);
	// if (rs.next()) {
	// sqlStr = "alter table " + PublicSTH.DATABASE_NAME
	// + ".t_alarm_info drop primary key";
	// this.jdbcTempalte.update(sqlStr);
	// }
	// sqlStr =
	// "show index from t_alarm_info from instruction where Key_name='select_alarm'";
	// rs = this.jdbcTempalte.queryForRowSet(sqlStr);
	// if (rs.next()) {
	//
	// } else {
	// sqlStr = "alter table "
	// + PublicSTH.DATABASE_NAME
	// +
	// ".t_alarm_info add index select_alarm(`date_time`,`user_id`,`sipuri`,`alarm_type`)";
	// this.jdbcTempalte.update(sqlStr);
	// }
	//
	// }
	// }
	//
	// /**
	// * 更新数据库版本1，增加t_sip_work_state表字段：SIP外网、内网IP，端口
	// *
	// * @param oldDatabaseVersion
	// */
	// private void updateTo1(int oldDatabaseVersion) {
	// int databaseVersion = 1;
	// if (oldDatabaseVersion < databaseVersion) {
	// String sqlStr = "";
	// sqlStr = "describe " + PublicSTH.DATABASE_NAME
	// + ".t_sip_work_state sip_ip_out";
	// SqlRowSet rs = this.jdbcTempalte.queryForRowSet(sqlStr);
	// if (!rs.next()) {
	// sqlStr = "alter table "
	// + PublicSTH.DATABASE_NAME
	// + ".t_sip_work_state add sip_ip_out varchar(15) comment 'SIP外网IP地址'";
	// this.jdbcTempalte.update(sqlStr);
	// sqlStr = "alter table "
	// + PublicSTH.DATABASE_NAME
	// + ".t_sip_work_state add sip_ip_in varchar(15) comment 'SIP内网IP地址'";
	// this.jdbcTempalte.update(sqlStr);
	// sqlStr = "alter table "
	// + PublicSTH.DATABASE_NAME
	// + ".t_sip_work_state add sip_port varchar(5) comment 'SIP端口'";
	// this.jdbcTempalte.update(sqlStr);
	// }
	//
	// }
	// }

	/**
	 * 创建版本信息表
	 */
	private void createTable(String createStr) {
		String sqlStr = "use " + PublicSTH.DATABASE_NAME;
		this.jdbcTemplate.execute(sqlStr);
		String[] sqlArray = createStr.split(PublicSTH.SQL_SPLIT_CHAR);
		for (int i = 0, len = sqlArray.length; i < len; i++) {
			jdbcTemplate.execute(sqlArray[i]);
		}
		//
		// sqlStr = "create table `t_version`("
		// + "  `id` INTEGER not null auto_increment comment '编号',"
		// + "  `database_version` int(11) comment '数据库版本',"
		// + "  `web_version` VARCHAR(32) comment 'web版本',"
		// + "  primary key (`id`)"
		// + ")ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='版本信息';";
		// this.jdbcTempalte.execute(sqlStr);
		// sqlStr =
		// "insert into t_version (database_version,web_version) values(0,'1.0.18');";
		// this.jdbcTempalte.update(sqlStr);
	}

	/**
	 * 检查是否有版本信息表
	 * 
	 * @return true 有;false 没有
	 */
	private boolean hasTable(String tableName) {
		boolean has = false;
		String sqlStr = "SELECT table_name FROM INFORMATION_SCHEMA.TABLES  WHERE table_schema = '"
				+ PublicSTH.DATABASE_NAME
				+ "'  AND table_name = '"
				+ tableName
				+ "'";
		SqlRowSet rs = this.jdbcTemplate.queryForRowSet(sqlStr);
		if (rs.next()) {
			has = true;
		}
		return has;
	}

	/**
	 * 获取当前数据库版本
	 * 
	 * @return 数据库版本号
	 */
	private int getDatabaseVersion() {
		int databaseVersion = 0;
		String sqlStr = "select database_version from "
				+ PublicSTH.DATABASE_NAME + ".t_version";
		SqlRowSet rs = this.jdbcTemplate.queryForRowSet(sqlStr);
		if (rs.next()) {
			databaseVersion = rs.getInt("database_version");
		}
		return databaseVersion;
	}
}
