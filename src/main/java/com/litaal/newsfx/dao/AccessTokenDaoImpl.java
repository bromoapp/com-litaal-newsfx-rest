package com.litaal.newsfx.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.litaal.newsfx.constant.Property;
import com.litaal.newsfx.model.AccessToken;
import com.litaal.newsfx.model.AccessTokenMapper;

@Component
public class AccessTokenDaoImpl implements IAccessTokenDao {

	private static String INSERT_TOKEN = "INSERT INTO %s (token, expired) VALUES ('%s', %s)";
	private static String SELECT_TOKEN = "SELECT * FROM %s AS o WHERE o.token = '%s'";
	private static String DELETE_TOKEN = "DELETE FROM %s AS o WHERE o.token = '%s'";

	private JdbcTemplate template;

	public AccessTokenDaoImpl(@Qualifier(Property.DbName.USER_DB) DataSource ds) {
		super();
		this.template = new JdbcTemplate(ds);
	}

	@Override
	public boolean create(AccessToken obj) {
		String sql = String.format(INSERT_TOKEN, AccessToken.TBLNAME, obj.getToken(), obj.getExpired());
		return template.update(sql) > 0;
	}

	@Override
	public AccessToken select(String token) {
		try {
			String sql = String.format(SELECT_TOKEN, AccessToken.TBLNAME, token);
			return template.queryForObject(sql, new AccessTokenMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public boolean delete(String token) {
		String sql = String.format(DELETE_TOKEN, AccessToken.TBLNAME, token);
		return template.update(sql) > 0;
	}

}
