package com.school.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.acegisecurity.Authentication;
import org.acegisecurity.event.authentication.AuthenticationSuccessEvent;
import org.acegisecurity.ui.WebAuthenticationDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.school.dao.LogDao;
import com.school.dao.SystemAdminDao;
import com.school.domain.Log;
import com.school.domain.SystemAdmin;
import com.school.domain.UserLoginDetails;

@Component
public class LoginSuccessListener implements ApplicationListener {

	@Autowired
	ServiceDao serviceDao;

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		// TODO Auto-generated method stub
		if (event instanceof AuthenticationSuccessEvent) {
			//成功登录系统事件
			AuthenticationSuccessEvent authEvent = (AuthenticationSuccessEvent) event;
			Authentication auth = authEvent.getAuthentication();
			UserLoginDetails user = new UserLoginDetails();
			user = (UserLoginDetails) auth.getPrincipal();
			WebAuthenticationDetails details = (WebAuthenticationDetails) authEvent
					.getAuthentication().getDetails();
			Log log=new Log();
			
			log.setIp(details.getRemoteAddress());
			log.setContent("登录系统");
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			log.setDateTime(sdf.format(new Date()));
			log.setUserTypeId(user.getUserType());
			log.setUserId(user.getUserId());
			log.setUserName(user.getUsername());
			serviceDao.updateLoginInfo(log);
			
//			SystemAdmin admin=new SystemAdmin();
//			admin.setLastLoginIP(log.getIp());
//			admin.setLastLoginTime(log.getDateTime());
//			admin.setId(log.getUserId());
//			this.setLoginTimeAndIp(admin);
		}
	}
}
