package com.school.dao.jdbc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DateFormat;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.school.dao.DatabaseDao;
import com.school.publicSomething.PublicSTH;

@Component
public class DatabaseDaoImpl implements DatabaseDao {

//	@Autowired
//	private JdbcTemplate jdbcTemplate;
	
	@Override
	public boolean backup(String path, boolean autoBack) throws IOException {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		
		boolean flag = false;
		String schoolTable = " -R --default-character-set=utf8 "+PublicSTH.DATABASE_NAME;
		
		String date = DateFormat.getDateInstance().format(new java.util.Date());
		String time = DateFormat.getTimeInstance().format(new java.util.Date());
		time = time.replace(":", "-");
		date = date + "-" + time;
		// boolean autoBack = true;

		Runtime rt = Runtime.getRuntime();

		/*
		 * if (path.indexOf("autoback") < 0) { path = path + "\\backup\\";
		 * autoBack = false; }else{ path=path.replaceAll("\\\\", "\\\\\\\\"); }
		 */
		path = path.replaceAll("\\\\", "\\\\\\\\");
		// System.out.println(path);
		java.io.File myFilePath = new java.io.File(path);
		if (!myFilePath.exists()) {
			myFilePath.mkdir();
		}

		File f = new File(this.getClass().getResource("/").getPath());
		// System.out.println(f);
		String mysqldumpPath = f.getPath();
		mysqldumpPath = mysqldumpPath.substring(0, mysqldumpPath
				.indexOf("webapps"));
		
		//替换%20为空格,在windows下不能识别%20是空格的意思
		mysqldumpPath=mysqldumpPath.replace("%20", " ");
		
		java.io.File mysqldump55File = new java.io.File(mysqldumpPath+ "bin\\MySQL Server 5.5\\bin");
//		java.io.File mysqldump50File = new java.io.File(mysqldumpPath+ "bin\\MySQL Server 5.0\\bin");
		if(mysqldump55File.exists()){
			mysqldumpPath = mysqldumpPath + "bin\\MySQL Server 5.5\\bin";
		}
//		else if(mysqldump50File.exists()){
//			mysqldumpPath = mysqldumpPath + "bin\\MySQL Server 5.0\\bin";
//		}
		else{
			System.out.println("没有找到备份文件mysqldump");
			return false;
		}
//		mysqldumpPath = mysqldumpPath + "bin/MySQL Server 5.5/bin";
		String str = "\""
				+ mysqldumpPath
				+ "\\mysqldump\" --no-defaults -ubkuser -pjfclientuser --opt -x";

		// 查找文件是否存在
		File fileMysqldump = new File(mysqldumpPath);
		String file_list[] = fileMysqldump.list();
		boolean haveMysqldump = false;
		if (file_list != null) {
			for (int i = 0; i < file_list.length; i++) {
				if (file_list[i].equals("mysqldump.exe")) {
					haveMysqldump = true;
					break;
				}
			}
		}
		if (!haveMysqldump) {
			str = "mysqldump -ubkuser -pjfclientuser --opt -x";
		}
		System.out.println("备份命令："+str+schoolTable);
		// 备份巡查数据库表
		System.out.println("开始备份数据库。");
		Process child = rt.exec(str + schoolTable);
		InputStream in = child.getInputStream();
		InputStreamReader xx = new InputStreamReader(in, "UTF-8");
		String inStr;
		StringBuffer sb = new StringBuffer("");
		String outStr;
		// out of memory
		BufferedReader br = new BufferedReader(xx);
		//sb.append("set names gb2312; \r\n");
		while ((inStr = br.readLine()) != null) {
			sb.append(inStr + "\r\n");
		}
	
		outStr = sb.toString();
//		System.out.println(outStr);

		String filepath = "";// 文件完整路径名

		if (autoBack) {
//			String autoBackSqlStr = "select bk_num from version";
//			int num = 0;
//			org.springframework.jdbc.support.rowset.SqlRowSet rs = jdbcTemplate
//					.queryForRowSet(autoBackSqlStr);
//			if (rs.next()) {
//				num = rs.getInt("bk_num") % 10 + 1;
//			}
//			filepath = path + "user-autoback" + num + "-" + date + ".sql";
//			jdbcTemplate.update("update version set bk_num=" + num + "");
//			
//			String sqlStr="select path from databackup where path like '%autoback"+(num)+"%'";
//			SqlRowSet srs=jdbcTemplate.queryForRowSet(sqlStr);
//			while(srs.next()){
//				new File(srs.getString("path")).delete();
//			}
			filepath = path + PublicSTH.DATABASE_NAME + date + ".sql";
		} else {
			filepath = path + PublicSTH.DATABASE_NAME + date + ".sql";
		}
		System.out.println("创建备份文件"+filepath);
		FileOutputStream fout = new FileOutputStream(filepath);
		OutputStreamWriter writer = new OutputStreamWriter(fout, "UTF-8");
		writer.write(outStr);

		writer.flush();

		// in.close();
		// xx.close();
		// br.close();
		// writer.close();
		// fout.close();
		

		in.close();
		xx.close();
		br.close();
		writer.close();
		fout.close();

		// ***********************************验证备份成功与否*************************
		String currentRecord = null;// 保存文本的变量
		BufferedReader file; // BufferedReader对象，用于读取文件数据

		file = new BufferedReader(new FileReader(filepath));
		currentRecord = file.readLine();

		// 读取一行数据并保存到currentRecord变量中
		while (currentRecord != null) {
			if (currentRecord
					.equals("/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;")) {
				flag = true;
			}
			currentRecord = file.readLine();
		}
		file.close();
		if (!flag) {
			java.io.File myDelFile = new java.io.File(filepath);
			myDelFile.delete();
			System.out.println("备份失败，删除错误的备份文件："+filepath);
		} else {
			// ************************************数据库记录备份路径及文件名，还原用************
			// String sqlStr = "select count(*) from databackup where path='"+
			// path + "\\user" + date + ".sql'";
//			String sqlStr = "select count(*) from databackup where path='"
//					+ filepath + "'";
//			if (jdbcTemplate.queryForInt(sqlStr) == 0) {
//				// sqlStr = "insert into databackup (path) values('" + path+
//				// "\\user" + date + ".sql')";
//				sqlStr = "insert into databackup (path) values('" + filepath
//						+ "')";
//				jdbcTemplate.update(sqlStr);
//			}
		}
		return flag;
	}

}
