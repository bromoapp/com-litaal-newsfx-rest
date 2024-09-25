package com.litaal.newsfx.dao;

import java.util.List;

import com.litaal.newsfx.constant.EPair;
import com.litaal.newsfx.constant.ETimeFrame;
import com.litaal.newsfx.model.PairPrice;

public interface IPairPriceDao {

	public List<PairPrice> findBetweenRecords(EPair pair, ETimeFrame tf, int year, Long start, Long end);

	public PairPrice findByTime(EPair pair, ETimeFrame tf, int year, Long time);

	public PairPrice lastRecord(EPair pair, ETimeFrame tf, int year);
}
