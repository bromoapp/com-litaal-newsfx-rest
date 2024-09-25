package com.litaal.newsfx.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.litaal.newsfx.constant.Property;
import com.litaal.newsfx.model.Count;
import com.litaal.newsfx.model.CountMapper;
import com.litaal.newsfx.model.NewsDukascopy;
import com.litaal.newsfx.model.NewsDukascopyMapper;
import com.litaal.newsfx.model.Page;
import com.litaal.newsfx.model.Paging;

@Component
public class NewsDukascopyDaoImpl extends AbstractNewsProvider<NewsDukascopy> {

	private static String COUNT_BY_TITLE = "SELECT COUNT(*) AS 'total' FROM %s AS o WHERE o.currency = '%s' AND o.title = \"%s\"";
	private static String GET_BY_TITLE = "SELECT * FROM %s AS o WHERE o.currency = '%s' AND o.title = \"%s\" ORDER BY o.date DESC LIMIT %s,%s";
	private static String GET_UUID_n_DATE_BY_TITLE = "SELECT o.uuid AS 'uuid', o.date AS 'date' FROM %s AS o WHERE o.currency = '%s' AND o.title = \"%s\" ORDER BY o.date DESC LIMIT %s,%s";
	private static String COUNT_BY_TITLE_N_PERIODICITY = "SELECT COUNT(*) AS 'total' FROM %s AS o WHERE o.currency = '%s' AND o.title = \"%s\" AND o.periodicity = '%s'";
	private static String GET_BY_TITLE_N_PERIODICITY = "SELECT * FROM %s AS o WHERE o.currency = '%s' AND o.title = \"%s\" AND o.periodicity = '%s' ORDER BY o.date DESC LIMIT %s,%s";
	private static String GET_UUID_n_DATE_BY_TITLE_N_PERIODICITY = "SELECT o.uuid AS 'uuid', o.date AS 'date' FROM %s AS o WHERE o.currency = '%s' AND o.title = \"%s\" AND o.periodicity = '%s' ORDER BY o.date DESC LIMIT %s,%s";
	private static String GET_BY_TIME_EXCEPT = "SELECT * FROM %s AS o WHERE o.uuid != '%s' AND o.date = %s AND o.currency IN (%s) ORDER BY o.impact DESC";
	private static String GET_UNIQUES_BY_CURRENCY = "SELECT DISTINCT(o.title), o.periodicity, o.country, o.currency, o.impact FROM %s AS o WHERE o.title IS NOT NULL AND o.currency IN (%s) ORDER BY o.title ASC";

	private JdbcTemplate template;

	public NewsDukascopyDaoImpl(@Qualifier(Property.DbName.NEWS_DB) DataSource ds) {
		super();
		this.template = new JdbcTemplate(ds);
	}

	/**
	 * This method is to get search result to be rendered in result page
	 */
	@Override
	public Page<NewsDukascopy> findByTitleInPages(String currency, String title, int page, int max) {
		Page<NewsDukascopy> objects = null;
		String[] raw = title.split("\\|");
		if (raw.length > 1) {
			String realTitle = raw[0].trim();
			String periodicity = raw[1].trim();
			objects = findByTitleAndPeriodicity(currency, realTitle, periodicity, page, max, true);
		} else {
			objects = findByTitleOnly(currency, title, page, max, true);
		}
		return objects;
	}

	/**
	 * This method is to get results to be calculated and presented in volatility
	 * tab
	 */
	@Override
	public Page<NewsDukascopy> findByTitleInRecords(String currency, String title, int start, int max) {
		Page<NewsDukascopy> objects = null;
		String[] raw = title.split("\\|");
		if (raw.length > 1) {
			String realTitle = raw[0].trim();
			String periodicity = raw[1].trim();
			objects = findByTitleAndPeriodicity(currency, realTitle, periodicity, start, max, false);
		} else {
			objects = findByTitleOnly(currency, title, start, max, false);
		}
		return objects;
	}

	private Page<NewsDukascopy> findByTitleOnly(String currency, String title, int offset, int max, boolean ispage) {
		String countSql = String.format(COUNT_BY_TITLE, NewsDukascopy.TBLNAME, currency, title);
		Count obj = template.queryForObject(countSql, new CountMapper());
		if (obj != null) {
			int start = offset;
			int total = 0;
			String sql = null;
			if (ispage) {
				Paging res = getPagingInfo(obj.getTotal(), max, offset);
				start = res.getOffset();
				total = res.getTotal();
				sql = String.format(GET_BY_TITLE, NewsDukascopy.TBLNAME, currency, title, start, max);
			} else {
				sql = String.format(GET_UUID_n_DATE_BY_TITLE, NewsDukascopy.TBLNAME, currency, title, start, max);
			}
			List<NewsDukascopy> list = template.query(sql, new NewsDukascopyMapper());
			Page<NewsDukascopy> page = new Page<NewsDukascopy>();
			page.setTotalPages(total);
			page.setNumber(offset);
			page.setContent(list);
			return page;
		} else {
			return null;
		}
	}

	private Page<NewsDukascopy> findByTitleAndPeriodicity(String currency, String title, String periodicity, int offset,
			int max, boolean ispage) {
		String countSql = String.format(COUNT_BY_TITLE_N_PERIODICITY, NewsDukascopy.TBLNAME, currency, title,
				periodicity);
		Count obj = template.queryForObject(countSql, new CountMapper());
		if (obj != null) {
			int start = offset;
			int total = 0;
			String sql = null;
			if (ispage) {
				Paging res = getPagingInfo(obj.getTotal(), max, offset);
				start = res.getOffset();
				total = res.getTotal();
				sql = String.format(GET_BY_TITLE_N_PERIODICITY, NewsDukascopy.TBLNAME, currency, title, periodicity,
						start, max);
			} else {
				sql = String.format(GET_UUID_n_DATE_BY_TITLE_N_PERIODICITY, NewsDukascopy.TBLNAME, currency, title,
						periodicity, start, max);
			}
			List<NewsDukascopy> list = template.query(sql, new NewsDukascopyMapper());
			Page<NewsDukascopy> page = new Page<NewsDukascopy>();
			page.setTotalPages(total);
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
	public List<NewsDukascopy> findByTimeExcept(Long time, String uuid, Object[] currencies) {
		try {
			String sql = String.format(GET_BY_TIME_EXCEPT, NewsDukascopy.TBLNAME, uuid, time,
					util.formatArray(currencies));
			return template.query(sql, new NewsDukascopyMapper());
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * This method is to get results to be rendered in side navigation
	 */
	@Override
	public List<NewsDukascopy> findUniqueEventsByCurrencies(Object[] currencies) {
		try {
			String sql = String.format(GET_UNIQUES_BY_CURRENCY, NewsDukascopy.TBLNAME, util.formatArray(currencies));
			return template.query(sql, new NewsDukascopyMapper());
		} catch (Exception e) {
			return null;
		}
	}

}
