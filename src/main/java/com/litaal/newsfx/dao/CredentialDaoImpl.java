package com.litaal.newsfx.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.litaal.newsfx.constant.Property.DbName;
import com.litaal.newsfx.model.Credential;
import com.litaal.newsfx.model.CredentialMapper;

@Component
public class CredentialDaoImpl implements ICredentialDao {

	private static String GET_BY_USERNAME = "SELECT * FROM %s AS o WHERE o.username = '%s'";
	private static String INSERT = "INSERT INTO %s (uuid, username, password, enabled) "
			+ "VALUES ('%s','%s','%s','%s')";
	private static String UPDATE_PASS = "UPDATE %s SET password = '%s' WHERE uuid = '%s'";

	private JdbcTemplate template;

	public CredentialDaoImpl(@Qualifier(DbName.USER_DB) DataSource ds) {
		super();
		this.template = new JdbcTemplate(ds);
	}

	@Override
	public Credential findByUsername(String email) {
		try {
			String sql = String.format(GET_BY_USERNAME, Credential.TBLNAME, email);
			return template.queryForObject(sql, new CredentialMapper());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean createNew(Credential obj) {
		try {
			String sql = String.format(INSERT, Credential.TBLNAME, obj.getUuid(), obj.getUsername(), obj.getPassword(),
					obj.getEnabled());
			System.out.println("INSERT: " + sql);
			return (template.update(sql) > 0);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updatePassword(Credential obj) {
		try {
			String sql = String.format(UPDATE_PASS, obj.getPassword(), obj.getUuid());
			return (template.update(sql) > 0);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
