package com.litaal.newsfx.dao;

import java.util.List;

import com.litaal.newsfx.constant.ERole;
import com.litaal.newsfx.model.NavLink;

public interface INavLinkDao {

	public List<NavLink> getNavLinksByRole(ERole role);

}
