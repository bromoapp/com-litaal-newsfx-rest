package com.litaal.newsfx.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.litaal.newsfx.constant.ERole;
import com.litaal.newsfx.constant.Property;
import com.litaal.newsfx.model.NavLink;
import com.litaal.newsfx.model.NavLinkMapper;

@Component
public class NavLinkDaoImpl implements INavLinkDao {

	private static String LINKS_BY_ROLE = "SELECT o.title, o.icon, o.link FROM %s AS o WHERE o.roles LIKE ('%s')";

	private JdbcTemplate template;

	public NavLinkDaoImpl(@Qualifier(Property.DbName.USER_DB) DataSource ds) {
		super();
		this.template = new JdbcTemplate(ds);
	}

	@Override
	public List<NavLink> getNavLinksByRole(ERole role) {
		try {
			String sql = String.format(LINKS_BY_ROLE, NavLink.TBLNAME, "%" + role.name() + "%");
			return this.template.query(sql, new NavLinkMapper());
		} catch (Exception e) {
			return null;
		}
	}

}
