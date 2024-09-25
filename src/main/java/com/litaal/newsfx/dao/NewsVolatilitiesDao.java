package com.litaal.newsfx.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import com.litaal.newsfx.constant.Property;

public class NewsVolatilitiesDao implements INewsVolatilitiesDao {

	private JdbcTemplate template;

	public NewsVolatilitiesDao(@Qualifier(Property.DbName.NEWS_DB) DataSource ds) {
		super();
		this.template = new JdbcTemplate(ds);
	}

}
