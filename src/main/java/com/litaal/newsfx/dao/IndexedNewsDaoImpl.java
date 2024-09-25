package com.litaal.newsfx.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.litaal.newsfx.constant.ECurrency;
import com.litaal.newsfx.constant.ENewsOrigin;
import com.litaal.newsfx.constant.EPair;
import com.litaal.newsfx.model.IndexedNews;
import com.litaal.newsfx.model.IndexedNewsMapper;
import com.litaal.newsfx.util.CommonUtil;

@Component
public class IndexedNewsDaoImpl implements IIndexedNewsDao {

	private static String BY_PAIR = "SELECT * FROM indexed_news AS o WHERE o.currency IN (%s) "
			+ "AND o.provider = '%s' ORDER BY o.title ASC";
	private static String BY_CURRENCY = "SELECT * FROM indexed_news AS o WHERE o.currency = '%s' "
			+ "AND o.provider = '%s' ORDER BY o.title ASC";
	private static String BY_WORDS = "SELECT * FROM indexed_news AS o WHERE o.title LIKE ('%s') ORDER "
			+ "BY o.title ASC";

	@Autowired
	private CommonUtil util;

	private JdbcTemplate template;

	public IndexedNewsDaoImpl(@Qualifier("news_db") DataSource ds) {
		super();
		this.template = new JdbcTemplate(ds);
	}

	@Override
	public List<IndexedNews> findAllUniqueEventsByPair(ENewsOrigin origin, EPair pair) {
		Object[] currencies = util.getAvailCurrencies(pair);
		String sql = String.format(BY_PAIR, util.formatArray(currencies), origin.name());
		List<IndexedNews> list = template.query(sql, new IndexedNewsMapper());
		if (list != null) {
			return list;
		}
		return null;
	}

	@Override
	public List<IndexedNews> findAllUniqueEventsByCurrency(ENewsOrigin origin, ECurrency curr) {
		String sql = String.format(BY_CURRENCY, curr.name(), origin.name());
		List<IndexedNews> list = template.query(sql, new IndexedNewsMapper());
		if (list != null) {
			return list;
		}
		return null;
	}

	@Override
	public List<IndexedNews> findAllEventsByWords(String words) {
		String args = "%" + words.trim().replaceAll(" ", "%") + "%";
		String sql = String.format(BY_WORDS, args);
		List<IndexedNews> list = template.query(sql, new IndexedNewsMapper());
		if (list != null) {
			return list;
		}
		return null;
	}
}
