package com.school.Acegi;

import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.school.domain.SystemAdmin;
import com.school.domain.UserLoginDetails;
import com.school.publicSomething.PublicSTH;
import com.school.service.ServiceDao;

public class UserLoginServiceImpl implements UserDetailsService{

	@Autowired
	private ServiceDao serviceDao;
	
	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException, DataAccessException {
		// TODO Auto-generated method stub
		
		UserLoginDetails userDetails = new UserLoginDetails();
//		Admin adminInfo=new Admin();
		//只允许admin登录
		if(userName!=null&&userName.equals(PublicSTH.ADMIN_LOGIN_NAME)){
			userDetails=serviceDao.getUserLoginDetailsByLoginNameAndUserType(userName,PublicSTH.USER_TYPE_OF_ADMIN);
		}else{
			userDetails=serviceDao.getUserLoginDetailsByLoginNameAndUserType(userName,PublicSTH.USER_TYPE_OF_STUDENT);
		}
		
		if(userName!=null&&userName.equals(PublicSTH.ADMIN_LOGIN_NAME)){
			if(userDetails==null||userDetails.getPassword()==null||"".equals(userDetails.getPassword())){
				SystemAdmin systemAdmin=new SystemAdmin();
				systemAdmin.setLoginName(PublicSTH.ADMIN_LOGIN_NAME);
				systemAdmin.setUserName(PublicSTH.ADMIN_LOGIN_NAME);
				systemAdmin.setPassword(DigestUtils.md5Hex("123456"+PublicSTH.MD5_SALT_ADMIN));
				systemAdmin.setEnableLogin(PublicSTH.ENABLE);
				serviceDao.addSystemAdmin(systemAdmin);
			}
		}
		
		
//		GrantedAuthority[] authorities = new GrantedAuthority[1];
//		authorities[0] = new GrantedAuthorityImpl("PRIV_1");
//		userDetails.setAuthorities(authorities);
		
//		userDetails.setEnabled(true);
//		userDetails.setPassword(adminInfo.getLoginPassword());
//		userDetails.setUserId(adminInfo.getAdminId());
//		userDetails.setUsername(adminInfo.getLoginName());
		return userDetails;
	}
}
