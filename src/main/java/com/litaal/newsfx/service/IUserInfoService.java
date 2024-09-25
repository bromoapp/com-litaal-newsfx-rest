package com.litaal.newsfx.service;

import java.util.List;

import com.litaal.newsfx.dto.NewUserDto;
import com.litaal.newsfx.model.NavLink;
import com.litaal.newsfx.model.UserInfo;

public interface IUserInfoService {

	public UserInfo register(NewUserDto dto);

	public List<NavLink> getNavLinksByRole(String role);

}
