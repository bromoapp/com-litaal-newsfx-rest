package com.litaal.newsfx.service;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.litaal.newsfx.constant.ECurrency;
import com.litaal.newsfx.constant.ENewsOrigin;
import com.litaal.newsfx.constant.EPair;
import com.litaal.newsfx.constant.ETimeFrame;
import com.litaal.newsfx.dao.IPairPriceDao;
import com.litaal.newsfx.dao.IPairTickDao;
import com.litaal.newsfx.dao.NewsDailyFxDaoImpl;
import com.litaal.newsfx.dao.NewsDukascopyDaoImpl;
import com.litaal.newsfx.dao.NewsFxStreetDaoImpl;
import com.litaal.newsfx.dao.NewsMql5DaoImpl;
import com.litaal.newsfx.dto.AvgSwingValues;
import com.litaal.newsfx.dto.AvgVolatilityValue;
import com.litaal.newsfx.dto.EventMarkDto;
import com.litaal.newsfx.dto.NewsSwingValues;
import com.litaal.newsfx.dto.NewsVolatilityValues;
import com.litaal.newsfx.dto.PricesListDto;
import com.litaal.newsfx.dto.SwingValue;
import com.litaal.newsfx.dto.SwingsDto;
import com.litaal.newsfx.dto.VolatilitiesDto;
import com.litaal.newsfx.model.NewsDailyFx;
import com.litaal.newsfx.model.NewsDukascopy;
import com.litaal.newsfx.model.NewsFxStreet;
import com.litaal.newsfx.model.NewsMql5;
import com.litaal.newsfx.model.Page;
import com.litaal.newsfx.model.PairPrice;
import com.litaal.newsfx.model.PairTick;
import com.litaal.newsfx.util.CommonUtil;

@Component
public class PricesServiceImpl implements IPricesService {

	@Value("${visible_bars}")
	private int visibleBars;

	@Value("${atr_bars}")
	private int atrBars;

	@Autowired
	private CommonUtil util;

	@Autowired
	private NewsMql5DaoImpl newsMql5Dao;

	@Autowired
	private NewsDukascopyDaoImpl newsDukascopyDao;

	@Autowired
	private NewsFxStreetDaoImpl newsFxStreetDao;

	@Autowired
	private NewsDailyFxDaoImpl newsDailyFxDao;

	@Autowired
	private IPairPriceDao pairPriceDao;

	@Autowired
	private IPairTickDao tickDao;

	@Async
	@Override
	public CompletableFuture<PricesListDto> getPricesByTime(EPair pair, ETimeFrame tf, long time) {
		int year = util.parseYearFromUnixtime(time);
		long markTime = util.getNearestTimeInUnixtime(tf, time);
		Object obj = pairPriceDao.findByTime(pair, tf, year, markTime);
		if (obj != null) {
			PairPrice markPrice = (PairPrice) obj;
			EventMarkDto markDto = new EventMarkDto(markPrice.getHigh(), markTime);

			PricesListDto dto = new PricesListDto();
			dto.setPair(pair);
			dto.setTimeframe(tf);
			dto.setMark(markDto);

			PairPrice cPair = (PairPrice) obj;
			Long id = cPair.getId();
			long start = 0;
			long end = 0;
			if (id - ((visibleBars / 4) * 1) > 0) {
				start = id - ((visibleBars / 4) * 1);
			}
			if (id + ((visibleBars / 4) * 3) <= ((PairPrice) pairPriceDao.lastRecord(pair, tf, year)).getId()) {
				end = id + ((visibleBars / 4) * 3);
			}
			List<PairPrice> prices = getPrices(pair, tf, year, start, end);
			if (prices != null && prices.size() > 0) {
				dto.setTotal(prices.size());
				dto.setCategories(util.parsePairPriceTimesFromList(prices));
				dto.setPrices(util.parsePricesFromList(prices));
				dto.setVolumes(util.parseVolumesFromList(prices));
			}
			List<PairTick> ticks = getTicksByTime(pair, tf, time);
			if (ticks != null && ticks.size() > 0) {
				dto.setAsks(util.parseAsksFromList(ticks));
				dto.setBids(util.parseBidskFromList(ticks));
			}
			return CompletableFuture.completedFuture(dto);
		} else {
			return CompletableFuture.completedFuture(null);
		}
	}

	private List<PairTick> getTicksByTime(EPair pair, ETimeFrame tf, long time) {
		int year = util.parseYearFromUnixtime(time);
		time = util.getNearestTimeInUnixtime(tf, time);
		PairTick obj = tickDao.findByTime(pair, tf, year, time);
		if (obj != null) {
			Long id = obj.getId();
			long start = 0;
			long end = 0;
			if (id - ((visibleBars / 4) * 1) > 0) {
				start = id - ((visibleBars / 4) * 1);
			}
			if (id + ((visibleBars / 4) * 3) <= ((PairPrice) pairPriceDao.lastRecord(pair, tf, year)).getId()) {
				end = id + ((visibleBars / 4) * 3);
			}
			return getTicksTimeRange(pair, tf, year, start, end);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	private List<PairTick> getTicksTimeRange(EPair pair, ETimeFrame tf, int year, Long start, Long end) {
		Object obj = tickDao.findBetweenRecords(pair, tf, year, start, end);
		if (obj != null) {
			List<PairTick> list = (List<PairTick>) obj;
			return list;
		} else {
			return null;
		}
	}

	@Async
	@Override
	public CompletableFuture<VolatilitiesDto> getEventVolatilities(ENewsOrigin origin, String title, EPair pair,
			ECurrency currency, Long time, int offset, int max) {
		System.out.println(
				new Date().toGMTString() + " RECEIVED VOLATILITY QUERY - " + currency.name() + " | " + pair.name());
		VolatilitiesDto dto = new VolatilitiesDto();
		String curr = currency.name();
		LinkedList<NewsVolatilityValues> values = new LinkedList<>();
		switch (origin) {
		case DUKASCOPY: {
			Page<NewsDukascopy> pg = newsDukascopyDao.findByTitleInRecords(curr, title, offset, max);
			NewsDukascopy main = pg.getContent().get(0);
			NewsVolatilityValues mpv = parsePairVolatility(main.getUuid(), pair, origin.name().toLowerCase(),
					main.getDate());
			dto.setMain(mpv);
			for (NewsDukascopy o : pg.getContent()) {
				NewsVolatilityValues vo = parsePairVolatility(o.getUuid(), pair, origin.name().toLowerCase(),
						o.getDate());
				values.add(vo);
			}
		}
			break;
		case MQL5: {
			Page<NewsMql5> pg = newsMql5Dao.findByTitleInRecords(curr, title, offset, max);
			NewsMql5 main = pg.getContent().get(0);
			NewsVolatilityValues mpv = parsePairVolatility(main.getUuid(), pair, origin.name().toLowerCase(),
					main.getReleaseDate());
			dto.setMain(mpv);
			for (NewsMql5 o : pg.getContent()) {
				NewsVolatilityValues vo = parsePairVolatility(o.getUuid(), pair, origin.name().toLowerCase(),
						o.getReleaseDate());
				values.add(vo);
			}
		}
			break;
		case FXSTREET: {
			Page<NewsFxStreet> pg = newsFxStreetDao.findByTitleInRecords(curr, title, offset, max);
			NewsFxStreet main = pg.getContent().get(0);
			NewsVolatilityValues mpv = parsePairVolatility(main.getUuid(), pair, origin.name().toLowerCase(),
					main.getDate());
			dto.setMain(mpv);
			for (NewsFxStreet o : pg.getContent()) {
				NewsVolatilityValues vo = parsePairVolatility(o.getUuid(), pair, origin.name().toLowerCase(),
						o.getDate());
				values.add(vo);
			}
		}
			break;
		case DAILYFX: {
			Page<NewsDailyFx> pg = newsDailyFxDao.findByTitleInRecords(curr, title, offset, max);
			NewsDailyFx main = pg.getContent().get(0);
			NewsVolatilityValues mpv = parsePairVolatility(main.getUuid(), pair, origin.name().toLowerCase(),
					main.getDate());
			dto.setMain(mpv);
			for (NewsDailyFx o : pg.getContent()) {
				NewsVolatilityValues vo = parsePairVolatility(o.getUuid(), pair, origin.name().toLowerCase(),
						o.getDate());
				values.add(vo);
			}
		}
			break;
		}
		if (!values.isEmpty()) {
			int div = values.size();
			AvgVolatilityValue avg = new AvgVolatilityValue();
			avg.setPeriod(div);
			NewsVolatilityValues nv = new NewsVolatilityValues();
			Double h1 = values.stream().filter(o -> o.getH1() != null && o.getH1() > 0.0).mapToDouble(o -> o.getH1())
					.sum();
			Double m30 = values.stream().filter(o -> o.getM30() != null && o.getM30() > 0.0)
					.mapToDouble(o -> o.getM30()).sum();
			Double m15 = values.stream().filter(o -> o.getM15() != null && o.getM15() > 0.0)
					.mapToDouble(o -> o.getM15()).sum();
			Double m5 = values.stream().filter(o -> o.getM5() != null && o.getM5() > 0.0).mapToDouble(o -> o.getM5())
					.sum();
			Double m1 = values.stream().filter(o -> o.getM1() != null && o.getM1() > 0.0).mapToDouble(o -> o.getM1())
					.sum();
			nv.setM1(util.roundUp(m1 / div));
			nv.setM5(util.roundUp(m5 / div));
			nv.setM15(util.roundUp(m15 / div));
			nv.setM30(util.roundUp(m30 / div));
			nv.setH1(util.roundUp(h1 / div));
			avg.setValue(nv);

			dto.setAverage(avg);

			List<Long> times = new LinkedList<>();
			List<Double> m1s = new LinkedList<>();
			List<Double> m5s = new LinkedList<>();
			List<Double> m15s = new LinkedList<>();
			List<Double> m30s = new LinkedList<>();
			List<Double> h1s = new LinkedList<>();

			Collections.reverse(values);
			for (NewsVolatilityValues nvv : values) {
				times.add(nvv.getTime());
				m1s.add(nvv.getM1());
				m5s.add(nvv.getM5());
				m15s.add(nvv.getM15());
				m30s.add(nvv.getM30());
				h1s.add(nvv.getH1());
			}
			dto.setTimes(times.toArray());
			dto.setH1Values(h1s.toArray());
			dto.setM15Values(m15s.toArray());
			dto.setM1Values(m1s.toArray());
			dto.setM30Values(m30s.toArray());
			dto.setM5Values(m5s.toArray());

			System.out.println(
					new Date().toGMTString() + " RETURN VOLATILITY QUERY - " + currency.name() + " | " + pair.name());
			return CompletableFuture.completedFuture(dto);
		} else {
			return CompletableFuture.completedFuture(null);
		}
	}

	private NewsVolatilityValues parsePairVolatility(String uuid, EPair pair, String provider, long time) {
		int year = util.parseYearFromUnixtime(time);
		long m1Time = util.getNearestTimeInUnixtime(ETimeFrame.M1, time);
		PairPrice ppM1 = pairPriceDao.findByTime(pair, ETimeFrame.M1, year, m1Time);
		long m5Time = util.getNearestTimeInUnixtime(ETimeFrame.M5, time);
		PairPrice ppM5 = pairPriceDao.findByTime(pair, ETimeFrame.M5, year, m5Time);
		long m15Time = util.getNearestTimeInUnixtime(ETimeFrame.M15, time);
		PairPrice ppM15 = pairPriceDao.findByTime(pair, ETimeFrame.M15, year, m15Time);
		long m30Time = util.getNearestTimeInUnixtime(ETimeFrame.M30, time);
		PairPrice ppM30 = pairPriceDao.findByTime(pair, ETimeFrame.M30, year, m30Time);
		long h1Time = util.getNearestTimeInUnixtime(ETimeFrame.H1, time);
		PairPrice ppH1 = pairPriceDao.findByTime(pair, ETimeFrame.H1, year, h1Time);

		NewsVolatilityValues mpv = new NewsVolatilityValues(uuid, provider, time);
		if (ppM1 != null) {
			mpv.setM1(ppM1.getPipGap());
		}
		if (ppM5 != null) {
			mpv.setM5(ppM5.getPipGap());
		}
		if (ppM15 != null) {
			mpv.setM15(ppM15.getPipGap());
		}
		if (ppM30 != null) {
			mpv.setM30(ppM30.getPipGap());
		}
		if (ppH1 != null) {
			mpv.setH1(ppH1.getPipGap());
		}
		return mpv;
	}

	@Async
	@Override
	public CompletableFuture<SwingsDto> getEventSwings(ENewsOrigin origin, String title, EPair pair, ECurrency currency,
			Long time, int offset, int max) {
		System.out
				.println(new Date().toGMTString() + " RECEIVED SWING QUERY - " + currency.name() + " | " + pair.name());
		SwingsDto dto = new SwingsDto();
		String curr = currency.name();
		List<SwingValue> swingUps = new LinkedList<>();
		List<SwingValue> swingDowns = new LinkedList<>();
		List<Long> times = new LinkedList<>();
		switch (origin) {
		case DUKASCOPY: {
			Page<NewsDukascopy> pg = newsDukascopyDao.findByTitleInRecords(curr, title, offset, max);
			NewsDukascopy main = pg.getContent().get(0);
			NewsSwingValues mpv = parsePairSwingValues(main.getUuid(), pair, origin.name().toLowerCase(),
					main.getDate());
			dto.setMain(mpv);
			for (NewsDukascopy o : pg.getContent()) {
				NewsSwingValues vo = parsePairSwingValues(o.getUuid(), pair, origin.name().toLowerCase(), o.getDate());
				times.add(vo.getTime());
				swingUps.add(vo.getSwingUp());
				swingDowns.add(vo.getSwingDown());
			}
		}
			break;
		case MQL5: {
			Page<NewsMql5> pg = newsMql5Dao.findByTitleInRecords(curr, title, offset, max);
			NewsMql5 main = pg.getContent().get(0);
			NewsSwingValues mpv = parsePairSwingValues(main.getUuid(), pair, origin.name().toLowerCase(),
					main.getReleaseDate());
			dto.setMain(mpv);
			for (NewsMql5 o : pg.getContent()) {
				NewsSwingValues vo = parsePairSwingValues(o.getUuid(), pair, origin.name().toLowerCase(),
						o.getReleaseDate());
				times.add(vo.getTime());
				swingUps.add(vo.getSwingUp());
				swingDowns.add(vo.getSwingDown());
			}
		}
			break;
		case FXSTREET: {
			Page<NewsFxStreet> pg = newsFxStreetDao.findByTitleInRecords(curr, title, offset, max);
			NewsFxStreet main = pg.getContent().get(0);
			NewsSwingValues mpv = parsePairSwingValues(main.getUuid(), pair, origin.name().toLowerCase(),
					main.getDate());
			dto.setMain(mpv);
			for (NewsFxStreet o : pg.getContent()) {
				NewsSwingValues vo = parsePairSwingValues(o.getUuid(), pair, origin.name().toLowerCase(), o.getDate());
				times.add(vo.getTime());
				swingUps.add(vo.getSwingUp());
				swingDowns.add(vo.getSwingDown());
			}
		}
			break;
		case DAILYFX: {
			Page<NewsDailyFx> pg = newsDailyFxDao.findByTitleInRecords(curr, title, offset, max);
			NewsDailyFx main = pg.getContent().get(0);
			NewsSwingValues mpv = parsePairSwingValues(main.getUuid(), pair, origin.name().toLowerCase(),
					main.getDate());
			dto.setMain(mpv);
			for (NewsDailyFx o : pg.getContent()) {
				NewsSwingValues vo = parsePairSwingValues(o.getUuid(), pair, origin.name().toLowerCase(), o.getDate());
				times.add(vo.getTime());
				swingUps.add(vo.getSwingUp());
				swingDowns.add(vo.getSwingDown());
			}
		}
			break;
		}
		if (!swingUps.isEmpty() && !swingDowns.isEmpty()) {
			Collections.reverse(times);
			dto.setTimes(times.toArray());

			int div = swingUps.size();
			AvgSwingValues avg = new AvgSwingValues();
			avg.setPeriod(div);

			SwingValue swingUp = new SwingValue();
			Double swingUpH1 = swingUps.stream().filter(o -> o.getH1() != null && o.getH1() > 0.0)
					.mapToDouble(o -> o.getH1()).sum();
			Double swingUpM30 = swingUps.stream().filter(o -> o.getM30() != null && o.getM30() > 0.0)
					.mapToDouble(o -> o.getM30()).sum();
			Double swingUpM15 = swingUps.stream().filter(o -> o.getM15() != null && o.getM15() > 0.0)
					.mapToDouble(o -> o.getM15()).sum();
			Double swingUpM5 = swingUps.stream().filter(o -> o.getM5() != null && o.getM5() > 0.0)
					.mapToDouble(o -> o.getM5()).sum();
			Double swingUpM1 = swingUps.stream().filter(o -> o.getM1() != null && o.getM1() > 0.0)
					.mapToDouble(o -> o.getM1()).sum();
			swingUp.setM1(util.roundUp(swingUpM1 / div));
			swingUp.setM5(util.roundUp(swingUpM5 / div));
			swingUp.setM15(util.roundUp(swingUpM15 / div));
			swingUp.setM30(util.roundUp(swingUpM30 / div));
			swingUp.setH1(util.roundUp(swingUpH1 / div));
			avg.setSwingUp(swingUp);

			SwingValue swingDown = new SwingValue();
			Double swingDwH1 = swingDowns.stream().filter(o -> o.getH1() != null && o.getH1() > 0.0)
					.mapToDouble(o -> o.getH1()).sum();
			Double swingDwM30 = swingDowns.stream().filter(o -> o.getM30() != null && o.getM30() > 0.0)
					.mapToDouble(o -> o.getM30()).sum();
			Double swingDwM15 = swingDowns.stream().filter(o -> o.getM15() != null && o.getM15() > 0.0)
					.mapToDouble(o -> o.getM15()).sum();
			Double swingDwM5 = swingDowns.stream().filter(o -> o.getM5() != null && o.getM5() > 0.0)
					.mapToDouble(o -> o.getM5()).sum();
			Double swingDwM1 = swingDowns.stream().filter(o -> o.getM1() != null && o.getM1() > 0.0)
					.mapToDouble(o -> o.getM1()).sum();
			swingDown.setM1(util.roundUp(swingDwM1 / div));
			swingDown.setM5(util.roundUp(swingDwM5 / div));
			swingDown.setM15(util.roundUp(swingDwM15 / div));
			swingDown.setM30(util.roundUp(swingDwM30 / div));
			swingDown.setH1(util.roundUp(swingDwH1 / div));
			avg.setSwingDown(swingDown);

			dto.setAverage(avg);

			System.out.println(
					new Date().toGMTString() + " RETURN SWING QUERY - " + currency.name() + " | " + pair.name());
			return CompletableFuture.completedFuture(dto);
		} else {
			return CompletableFuture.completedFuture(null);
		}
	}

	@SuppressWarnings("unchecked")
	private List<PairPrice> getPrices(EPair pair, ETimeFrame tf, int year, Long start, Long end) {
		Object obj = pairPriceDao.findBetweenRecords(pair, tf, year, start, end);
		if (obj != null) {
			List<PairPrice> list = (List<PairPrice>) obj;
			return list;
		} else {
			return null;
		}
	}

	private NewsSwingValues parsePairSwingValues(String uuid, EPair pair, String provider, long time) {
		int year = util.parseYearFromUnixtime(time);
		long m1Time = util.getNearestTimeInUnixtime(ETimeFrame.M1, time);
		PairPrice ppM1 = pairPriceDao.findByTime(pair, ETimeFrame.M1, year, m1Time);
		long m5Time = util.getNearestTimeInUnixtime(ETimeFrame.M5, time);
		PairPrice ppM5 = pairPriceDao.findByTime(pair, ETimeFrame.M5, year, m5Time);
		long m15Time = util.getNearestTimeInUnixtime(ETimeFrame.M15, time);
		PairPrice ppM15 = pairPriceDao.findByTime(pair, ETimeFrame.M15, year, m15Time);
		long m30Time = util.getNearestTimeInUnixtime(ETimeFrame.M30, time);
		PairPrice ppM30 = pairPriceDao.findByTime(pair, ETimeFrame.M30, year, m30Time);
		long h1Time = util.getNearestTimeInUnixtime(ETimeFrame.H1, time);
		PairPrice ppH1 = pairPriceDao.findByTime(pair, ETimeFrame.H1, year, h1Time);

		NewsSwingValues vo = new NewsSwingValues(uuid, provider, time);
		if (ppM1 != null) {
			vo.getSwingUp().setM1(ppM1.getSwingUp());
			vo.getSwingDown().setM1(ppM1.getSwingDown());
		}
		if (ppM5 != null) {
			vo.getSwingUp().setM5(ppM5.getSwingUp());
			vo.getSwingDown().setM5(ppM5.getSwingDown());
		}
		if (ppM15 != null) {
			vo.getSwingUp().setM15(ppM15.getSwingUp());
			vo.getSwingDown().setM15(ppM15.getSwingDown());
		}
		if (ppM30 != null) {
			vo.getSwingUp().setM30(ppM30.getSwingUp());
			vo.getSwingDown().setM30(ppM30.getSwingDown());
		}
		if (ppH1 != null) {
			vo.getSwingUp().setH1(ppH1.getSwingUp());
			vo.getSwingDown().setH1(ppH1.getSwingDown());
		}
		return vo;
	}
//
//	@Async
//	public CompletableFuture<Object> getPreviousTrueRange(ENewsOrigin origin, String title, EPair pair,
//			ECurrency currency, Long time, int offset, int max) {
//		switch (origin) {
//		case DUKASCOPY: {
//			Page<NewsDukascopy> pg = newsDukascopyDao.findByTitleInRecords(curr, title, offset, max);
//			for (NewsDukascopy o : pg.getContent()) {
//				NewsSwingValues vo = parsePairSwingValues(o.getUuid(), pair, origin.name().toLowerCase(), o.getDate());
//				times.add(vo.getTime());
//				swingUps.add(vo.getSwingUp());
//				swingDowns.add(vo.getSwingDown());
//			}
//		}
//			break;
//		case MQL5: {
//			Page<NewsMql5> pg = newsMql5Dao.findByTitleInRecords(curr, title, offset, max);
//			NewsMql5 main = pg.getContent().get(0);
//			for (NewsMql5 o : pg.getContent()) {
//				NewsSwingValues vo = parsePairSwingValues(o.getUuid(), pair, origin.name().toLowerCase(),
//						o.getReleaseDate());
//				times.add(vo.getTime());
//				swingUps.add(vo.getSwingUp());
//				swingDowns.add(vo.getSwingDown());
//			}
//		}
//			break;
//		case FXSTREET: {
//			Page<NewsFxStreet> pg = newsFxStreetDao.findByTitleInRecords(curr, title, offset, max);
//			NewsFxStreet main = pg.getContent().get(0);
//			for (NewsFxStreet o : pg.getContent()) {
//				NewsSwingValues vo = parsePairSwingValues(o.getUuid(), pair, origin.name().toLowerCase(), o.getDate());
//				times.add(vo.getTime());
//				swingUps.add(vo.getSwingUp());
//				swingDowns.add(vo.getSwingDown());
//			}
//		}
//			break;
//		case DAILYFX: {
//			Page<NewsDailyFx> pg = newsDailyFxDao.findByTitleInRecords(curr, title, offset, max);
//			NewsDailyFx main = pg.getContent().get(0);
//			NewsSwingValues mpv = parsePairSwingValues(main.getUuid(), pair, origin.name().toLowerCase(),
//					main.getDate());
//			dto.setMain(mpv);
//			for (NewsDailyFx o : pg.getContent()) {
//				NewsSwingValues vo = parsePairSwingValues(o.getUuid(), pair, origin.name().toLowerCase(), o.getDate());
//				times.add(vo.getTime());
//				swingUps.add(vo.getSwingUp());
//				swingDowns.add(vo.getSwingDown());
//			}
//		}
//			break;
//		}
//		return null;
//	}

}
