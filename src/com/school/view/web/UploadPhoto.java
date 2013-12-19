package com.school.view.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UploadPhoto extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 265319402741020404L;
	private File uploadPhoto;
	private String uploadPhotoFileName; // 文件名称
	private String uploadPhotoContentType; // 文件类型

	public void setUploadPhoto(File uploadPhoto) {
		this.uploadPhoto = uploadPhoto;
	}

	public void setUploadPhotoFileName(String uploadPhotoFileName) {
		this.uploadPhotoFileName = uploadPhotoFileName;
	}

	public void setUploadPhotoContentType(String uploadPhotoContentType) {
		this.uploadPhotoContentType = uploadPhotoContentType;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		String realpath = ServletActionContext.getServletContext().getRealPath(
				"/photo");
		// System.out.println("realpath: "+realpath);
		if (uploadPhoto != null) {
			this.setFileName();
			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().append("<script type='text/javascript'>");
			response.getWriter().append(
					"parent.setActionToStudent();");
			if (uploadPhotoFileName.endsWith(".jpg")) {
				if(uploadPhoto.length()/1024<26&&uploadPhoto.length()/1024>10){
				File savefile = new File(new File(realpath),
						uploadPhotoFileName);
				if (!savefile.getParentFile().exists())
					savefile.getParentFile().mkdirs();
				FileUtils.copyFile(uploadPhoto, savefile);

				response.getWriter().append(
						"parent.setPhoto('../photo/" + uploadPhotoFileName
								+ "');");
				response.getWriter().append(
						"parent.setPhotoName('" + uploadPhotoFileName + "');");
			}else{
				response.getWriter().append("alert('照片大小应小于25K');");
			}
			} else {
				response.getWriter().append("alert('照片格式需为jpg格式');");
			}
			response.getWriter().append("</script>");
			response.getWriter().flush();
			response.getWriter().close();
			
		}
		return null;
	}

	private void setFileName() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String now = sdf.format(new Date());
		uploadPhotoFileName = now
				+ uploadPhotoFileName.substring(uploadPhotoFileName
						.lastIndexOf("."));
	}

}
