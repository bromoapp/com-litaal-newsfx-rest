package com.litaal.newsfx.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.litaal.newsfx.constant.ENewsOrigin;
import com.litaal.newsfx.constant.Property;
import com.litaal.newsfx.model.NewsResult;
import com.litaal.newsfx.model.NewsResultMapper;

@Component
public class NewsResultDaoImpl implements INewsResultProviderDao {

	private static String GET_BY_RECNO = "SELECT * FROM news_result_%s AS o WHERE o.uuid = '%s'";

	private JdbcTemplate template;

	public NewsResultDaoImpl(@Qualifier(Property.DbName.NEWS_DB) DataSource ds) {
		super();
		this.template = new JdbcTemplate(ds);
	}

	@Override
	public NewsResult findByParentRecNo(ENewsOrigin provider, int year, String uuid) {
		try {
			String sql = String.format(GET_BY_RECNO, provider.name().toLowerCase() + "_" + year, uuid);
			return template.queryForObject(sql, new NewsResultMapper());
		} catch (Exception e) {
			return null;
		}
	}

}
