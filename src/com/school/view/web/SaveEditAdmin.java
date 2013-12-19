package com.school.view.web;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.school.domain.SystemAdmin;
import com.school.domain.UserLoginDetails;
import com.school.publicSomething.PublicSTH;
import com.school.service.ServiceDao;

public class SaveEditAdmin extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7867991027447076109L;
	@Autowired
	private ServiceDao serviceDao;
	private String oldPassword;
	private String newPassword1;
	private String newPassword2;

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword.trim();
	}

	public void setNewPassword1(String newPassword1) {
		this.newPassword1 = newPassword1.trim();
	}

	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2.trim();
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		SystemAdmin admin = new SystemAdmin();
		String info = this.checkPassword(admin);
		String success = "密码修改成功，请重新登录\\n";
		if (info == null || "".equals(info)) {
			// 验证通过
			serviceDao.updateSystemAdminPassword(admin);
			info = success;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().append("<script type='text/javascript'>");
		response.getWriter().append("alert('" + info + "');");

		if (info.equals(success)) {
			response.getWriter().append("parent.exit();");
		}
		response.getWriter().append("</script>");
		response.getWriter().flush();
		response.getWriter().close();
		return null;
	}

	private String checkPassword(SystemAdmin admin) {
		StringBuffer info = new StringBuffer();
		UserLoginDetails loginUser = serviceDao.getLoginUserInfo();
		if (oldPassword == null) {
			info.append("请输入 旧密码\\n");
		} else if (newPassword1 == null || newPassword2 == null) {
			info.append("新密码不能为空\\n");
		} else if (!(DigestUtils.md5Hex(oldPassword + PublicSTH.MD5_SALT_ADMIN)
				.equals(loginUser.getPassword()))) {
			info.append("旧密码输入有误\\n");
		} else if (newPassword1.length() < 3) {
			info.append("新密码太短\\n");
		} else if (!newPassword1.equals(newPassword2)) {
			info.append("新密码和确认新密码不符\\n");
		} else {
			admin.setPassword(DigestUtils.md5Hex(newPassword1
					+ PublicSTH.MD5_SALT_ADMIN));
			admin.setId(loginUser.getUserId());
		}
		return info.toString();
	}

}
