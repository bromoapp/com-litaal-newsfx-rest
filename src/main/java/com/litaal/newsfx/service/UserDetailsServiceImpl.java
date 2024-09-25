package com.litaal.newsfx.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.litaal.newsfx.constant.ERole;
import com.litaal.newsfx.dao.ICredentialDao;
import com.litaal.newsfx.dao.INavLinkDao;
import com.litaal.newsfx.dao.IUserInfoDao;
import com.litaal.newsfx.dto.NewUserDto;
import com.litaal.newsfx.model.Credential;
import com.litaal.newsfx.model.NavLink;
import com.litaal.newsfx.model.UserInfo;
import com.litaal.newsfx.model.UserInfoDetails;

@Component
public class UserDetailsServiceImpl implements UserDetailsService, IUserInfoService {

	@Autowired
	private ICredentialDao credentialDao;

	@Autowired
	private IUserInfoDao userInfoDao;

	@Autowired
	private INavLinkDao navLinkDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Credential cred = credentialDao.findByUsername(username);
		if (cred != null) {
			UserInfo user = userInfoDao.findByUuid(cred.getUuid());
			return new UserInfoDetails(cred, user);
		} else {
			return null;
		}
	}

	@Override
	public UserInfo register(NewUserDto dto) {
		UserInfo obj = new UserInfo();
		obj.setBirthYear(dto.getBirthYear());
		obj.setCountry(dto.getCountry());
		obj.setFirstName(dto.getFirstName());
		obj.setGender(dto.getGender());
		obj.setJobTitle(dto.getJobTitle());
		obj.setLastName(dto.getLastName());
		obj.setSalutation(dto.getSalutation());
		obj.setUuid(UUID.randomUUID().toString());
		obj.setRole(ERole.GUEST);
		if (userInfoDao.create(obj)) {
			return obj;
		} else {
			return null;
		}
	}

	@Override
	public List<NavLink> getNavLinksByRole(String role) {
		return navLinkDao.getNavLinksByRole(ERole.valueOf(role));
	}

}
