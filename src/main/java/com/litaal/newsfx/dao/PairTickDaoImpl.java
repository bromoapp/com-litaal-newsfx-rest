package com.litaal.newsfx.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.litaal.newsfx.constant.EPair;
import com.litaal.newsfx.constant.ETimeFrame;
import com.litaal.newsfx.constant.Property;
import com.litaal.newsfx.model.PairTick;
import com.litaal.newsfx.model.PairTickMapper;

@Component
public class PairTickDaoImpl implements IPairTickDao {

	private static String GET_BETWEEN_RECS = "SELECT * FROM %s AS o WHERE o.id BETWEEN %s AND %s";
	private static String GET_BY_TIME = "SELECT * FROM %s AS o WHERE o.time = %s";
	private static String GET_LAST_REC = "SELECT * FROM %s AS o ORDER BY o.id DESC LIMIT 1";

	private JdbcTemplate template;

	public PairTickDaoImpl(@Qualifier(Property.DbName.NEWS_DB) DataSource ds) {
		super();
		this.template = new JdbcTemplate(ds);
	}

	@Override
	public List<PairTick> findBetweenRecords(EPair pair, ETimeFrame tf, int year, Long start, Long end) {
		try {
			String tblName = pair.name().toLowerCase() + "_" + tf.name().toLowerCase() + "t_" + year;
			String sql = String.format(GET_BETWEEN_RECS, tblName, start, end);
			return template.query(sql, new PairTickMapper());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public PairTick findByTime(EPair pair, ETimeFrame tf, int year, Long time) {
		try {
			String tblName = pair.name().toLowerCase() + "_" + tf.name().toLowerCase() + "t_" + year;
			String sql = String.format(GET_BY_TIME, tblName, time);
			return template.queryForObject(sql, new PairTickMapper());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public PairTick lastRecord(EPair pair, ETimeFrame tf, int year) {
		try {
			String tblName = pair.name().toLowerCase() + "_" + tf.name().toLowerCase() + "t_" + year;
			String sql = String.format(GET_LAST_REC, tblName);
			return template.queryForObject(sql, new PairTickMapper());
		} catch (Exception e) {
			return null;
		}
	}

}
