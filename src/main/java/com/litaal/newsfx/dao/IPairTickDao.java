package com.litaal.newsfx.dao;

import java.util.List;

import com.litaal.newsfx.constant.EPair;
import com.litaal.newsfx.constant.ETimeFrame;
import com.litaal.newsfx.model.PairTick;

public interface IPairTickDao {

	public List<PairTick> findBetweenRecords(EPair pair, ETimeFrame tf, int year, Long start, Long end);

	public PairTick findByTime(EPair pair, ETimeFrame tf, int year, Long time);

	public PairTick lastRecord(EPair pair, ETimeFrame tf, int year);
}
