package com.litaal.newsfx.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.litaal.newsfx.constant.Property;
import com.litaal.newsfx.model.Count;
import com.litaal.newsfx.model.CountMapper;
import com.litaal.newsfx.model.NewsMql5;
import com.litaal.newsfx.model.NewsMql5Mapper;
import com.litaal.newsfx.model.Page;
import com.litaal.newsfx.model.Paging;

@Component
public class NewsMql5DaoImpl extends AbstractNewsProvider<NewsMql5> {

	private static String COUNT_BY_TITLE = "SELECT COUNT(*) AS 'total' FROM %s AS o WHERE o.currency_code = '%s' AND o.event_name = \"%s\"";
	private static String GET_BY_TITLE = "SELECT * FROM %s AS o WHERE o.currency_code = '%s' AND o.event_name = \"%s\" ORDER BY o.release_date DESC LIMIT %s,%s";
	private static String GET_UUID_n_DATE_BY_TITLE = "SELECT o.uuid AS 'uuid', o.release_date AS 'release_date' FROM %s AS o WHERE o.currency_code = '%s' AND o.event_name = \"%s\" ORDER BY o.release_date DESC LIMIT %s,%s";
	private static String GET_BY_TIME_EXCEPT = "SELECT * FROM %s AS o WHERE o.uuid != '%s' AND o.release_date = %s AND o.currency_code IN (%s) ORDER BY o.importance DESC";
	private static String GET_UNIQUES_BY_CURRENCY = "SELECT DISTINCT(o.event_name), o.country, o.currency_code, o.importance FROM %s AS o WHERE o.currency_code IN (%s) ORDER BY o.event_name ASC;";

	private JdbcTemplate template;

	public NewsMql5DaoImpl(@Qualifier(Property.DbName.NEWS_DB) DataSource ds) {
		super();
		this.template = new JdbcTemplate(ds);
	}

	/**
	 * This method is to get search result to be rendered in result page
	 */
	@Override
	public Page<NewsMql5> findByTitleInPages(String currency, String title, int offset, int max) {
		String countSql = String.format(COUNT_BY_TITLE, NewsMql5.TBLNAME, currency, title);
		Count obj = template.queryForObject(countSql, new CountMapper());
		if (obj != null) {
			Paging res = getPagingInfo(obj.getTotal(), max, offset);
			String sql = String.format(GET_BY_TITLE, NewsMql5.TBLNAME, currency, title, res.getOffset(), max);
			List<NewsMql5> list = template.query(sql, new NewsMql5Mapper());
			Page<NewsMql5> page = new Page<NewsMql5>();
			page.setTotalPages(res.getTotal());
			page.setNumber(offset);
			page.setContent(list);
			return page;
		} else {
			return null;
		}
	}

	/**
	 * This method is to get results to be calculated and presented in volatility
	 * tab
	 */
	@Override
	public Page<NewsMql5> findByTitleInRecords(String currency, String title, int offset, int max) {
		String countSql = String.format(COUNT_BY_TITLE, NewsMql5.TBLNAME, currency, title);
		Count obj = template.queryForObject(countSql, new CountMapper());
		if (obj != null) {
			String sql = String.format(GET_UUID_n_DATE_BY_TITLE, NewsMql5.TBLNAME, currency, title, offset, max);
			List<NewsMql5> list = template.query(sql, new NewsMql5Mapper());
			Page<NewsMql5> page = new Page<NewsMql5>();
			page.setTotalPages(0);
			page.setNumber(offset);
			page.setContent(list);
			return page;
		} else {
			return null;
		}
	}

	/**
	 * This method is to get results to be rendered in other events table
	 */
	@Override
	public List<NewsMql5> findByTimeExcept(Long time, String uuid, Object[] currencies) {
		try {
			String sql = String.format(GET_BY_TIME_EXCEPT, NewsMql5.TBLNAME, uuid, time, util.formatArray(currencies));
			return template.query(sql, new NewsMql5Mapper());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * This method is to get results to be rendered in side navigation
	 */
	@Override
	public List<NewsMql5> findUniqueEventsByCurrencies(Object[] currencies) {
		try {
			String sql = String.format(GET_UNIQUES_BY_CURRENCY, NewsMql5.TBLNAME, util.formatArray(currencies));
			return template.query(sql, new NewsMql5Mapper());
		} catch (Exception e) {
			return null;
		}
	}

}
