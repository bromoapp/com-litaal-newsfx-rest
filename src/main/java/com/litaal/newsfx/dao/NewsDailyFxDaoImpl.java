package com.litaal.newsfx.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.litaal.newsfx.constant.Property;
import com.litaal.newsfx.model.Count;
import com.litaal.newsfx.model.CountMapper;
import com.litaal.newsfx.model.NewsDailyFx;
import com.litaal.newsfx.model.NewsDailyFxMapper;
import com.litaal.newsfx.model.Page;
import com.litaal.newsfx.model.Paging;

@Component
public class NewsDailyFxDaoImpl extends AbstractNewsProvider<NewsDailyFx> {

	private static String COUNT_BY_TITLE = "SELECT COUNT(*) AS 'total' FROM %s AS o WHERE o.currency = '%s' AND o.title = \"%s\"";
	private static String GET_BY_TITLE = "SELECT * FROM %s AS o WHERE o.currency = '%s' AND o.title = \"%s\" ORDER BY o.date DESC LIMIT %s,%s";
	private static String GET_UUID_n_DATE_BY_TITLE = "SELECT o.uuid AS 'uuid', o.date AS 'date' FROM %s AS o WHERE o.currency = '%s' AND o.title = \"%s\" ORDER BY o.date DESC LIMIT %s,%s";
	private static String GET_BY_TIME_EXCEPT = "SELECT * FROM %s AS o WHERE o.uuid != '%s' AND o.date = %s AND o.currency IN (%s) ORDER BY o.importance DESC";
	private static String GET_UNIQUES_BY_CURRENCY = "SELECT DISTINCT(o.title), o.country, o.currency, o.importance FROM %s AS o WHERE o.title IS NOT NULL AND o.currency IN (%s) ORDER BY o.title ASC";

	private JdbcTemplate template;

	public NewsDailyFxDaoImpl(@Qualifier(Property.DbName.NEWS_DB) DataSource ds) {
		super();
		this.template = new JdbcTemplate(ds);
	}

	/**
	 * This method is to get search result to be rendered in result page
	 */
	@Override
	public Page<NewsDailyFx> findByTitleInPages(String currency, String title, int offset, int max) {
		String countSql = String.format(COUNT_BY_TITLE, NewsDailyFx.TBLNAME, currency, title);
		Count obj = template.queryForObject(countSql, new CountMapper());
		if (obj != null) {
			Paging res = getPagingInfo(obj.getTotal(), max, offset);
			String sql = String.format(GET_BY_TITLE, NewsDailyFx.TBLNAME, currency, title, res.getOffset(), max);
			List<NewsDailyFx> list = template.query(sql, new NewsDailyFxMapper());
			Page<NewsDailyFx> page = new Page<NewsDailyFx>();
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
	public Page<NewsDailyFx> findByTitleInRecords(String currency, String title, int offset, int max) {
		String countSql = String.format(COUNT_BY_TITLE, NewsDailyFx.TBLNAME, currency, title);
		Count obj = template.queryForObject(countSql, new CountMapper());
		if (obj != null) {
			String sql = String.format(GET_UUID_n_DATE_BY_TITLE, NewsDailyFx.TBLNAME, currency, title, offset, max);
			List<NewsDailyFx> list = template.query(sql, new NewsDailyFxMapper());
			Page<NewsDailyFx> page = new Page<NewsDailyFx>();
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
	public List<NewsDailyFx> findByTimeExcept(Long time, String uuid, Object[] currencies) {
		try {
			String sql = String.format(GET_BY_TIME_EXCEPT, NewsDailyFx.TBLNAME, uuid, time,
					util.formatArray(currencies));
			return template.query(sql, new NewsDailyFxMapper());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * This method is to get results to be rendered in side navigation
	 */
	@Override
	public List<NewsDailyFx> findUniqueEventsByCurrencies(Object[] currencies) {
		try {
			String sql = String.format(GET_UNIQUES_BY_CURRENCY, NewsDailyFx.TBLNAME, util.formatArray(currencies));
			return template.query(sql, new NewsDailyFxMapper());
		} catch (Exception e) {
			return null;
		}
	}

}
