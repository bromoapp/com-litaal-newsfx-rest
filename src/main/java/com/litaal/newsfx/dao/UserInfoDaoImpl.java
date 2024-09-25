package com.litaal.newsfx.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.litaal.newsfx.constant.Property;
import com.litaal.newsfx.model.UserInfo;
import com.litaal.newsfx.model.UserInfoMapper;

@Component
@SuppressWarnings("static-access")
public class UserInfoDaoImpl implements IUserInfoDao {

	private static String INSERT = "INSERT INTO %s (uuid, f_name, l_name, gender, salutation, birth_year, job_title, country, role) "
			+ "VALUES ('%s','%s','%s','%s','%s',%s,'%s','%s','%s');";
	private static String UPDATE = "UPDATE %s SET f_name = '%s', l_name = '%s', birth_year = %s, "
			+ "job_title = '%s', country = '%s', role = '%s' WHERE uuid = '%s'";
	private static String SELECT = "SELECT * FROM %s AS o WHERE o.uuid = '%s'";

	private JdbcTemplate template;

	public UserInfoDaoImpl(@Qualifier(Property.DbName.USER_DB) DataSource ds) {
		super();
		this.template = new JdbcTemplate(ds);
	}

	@Override
	public boolean create(UserInfo user) {
		try {
			String insert = String.format(INSERT, user.TBLNAME, user.getUuid(), user.getFirstName(), user.getLastName(),
					user.getGender(), user.getSalutation(), user.getBirthYear(), user.getJobTitle(), user.getCountry(),
					user.getRole().name());
			template.update(insert);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(UserInfo user) {
		try {
			String update = String.format(UPDATE, user.TBLNAME, user.getFirstName(), user.getLastName(),
					user.getGender(), user.getSalutation(), user.getBirthYear(), user.getJobTitle(), user.getCountry(),
					user.getRole().name(), user.getUuid());
			template.update(update);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public UserInfo findByUuid(String uuid) {
		try {
			String select = String.format(SELECT, UserInfo.TBLNAME, uuid);
			return template.queryForObject(select, new UserInfoMapper());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
