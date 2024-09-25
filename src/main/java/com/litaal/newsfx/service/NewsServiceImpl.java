package com.litaal.newsfx.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.litaal.newsfx.constant.ECurrency;
import com.litaal.newsfx.constant.ENewsOrigin;
import com.litaal.newsfx.constant.EPair;
import com.litaal.newsfx.constant.EReality;
import com.litaal.newsfx.dao.INewsResultProviderDao;
import com.litaal.newsfx.dao.NewsDailyFxDaoImpl;
import com.litaal.newsfx.dao.NewsDukascopyDaoImpl;
import com.litaal.newsfx.dao.NewsFxStreetDaoImpl;
import com.litaal.newsfx.dao.NewsMql5DaoImpl;
import com.litaal.newsfx.dto.ForecastDto;
import com.litaal.newsfx.dto.NewsAndResult;
import com.litaal.newsfx.dto.NewsDto;
import com.litaal.newsfx.model.NewsDailyFx;
import com.litaal.newsfx.model.NewsDukascopy;
import com.litaal.newsfx.model.NewsFxStreet;
import com.litaal.newsfx.model.NewsMql5;
import com.litaal.newsfx.model.NewsResult;
import com.litaal.newsfx.model.Page;
import com.litaal.newsfx.util.CommonUtil;

@Component
public class NewsServiceImpl implements INewsService {

	private Random random = new Random();

	@Autowired
	private CommonUtil util;

	@Autowired
	private NewsDukascopyDaoImpl newsDukascopyDao;

	@Autowired
	private NewsMql5DaoImpl newsMql5Dao;

	@Autowired
	private NewsFxStreetDaoImpl newsFxStreetDao;

	@Autowired
	private NewsDailyFxDaoImpl newsDailyFxDao;

	@Autowired
	private INewsResultProviderDao newsResultDao;

	@Override
	public Page<NewsDto> findAllEventsForPair(ENewsOrigin provider, ECurrency currency, int page, int max,
			String title) {
		Page<NewsDto> resp = null;
		switch (provider) {
		case DUKASCOPY: {
			Page<NewsDukascopy> objects = null;
			objects = newsDukascopyDao.findByTitleInPages(currency.name(), title, page, max);
			if (objects != null) {
				List<NewsDto> list = new ArrayList<>();
				resp = new Page<NewsDto>();
				resp.setNumber(objects.getNumber());
				resp.setTotalPages(objects.getTotalPages());
				int rnd = random.nextInt(900) + 100;
				int index = 1;
				for (NewsDukascopy o : (List<NewsDukascopy>) objects.getContent()) {
					NewsDto dto = util.toNewsDto(rnd, index, o);
					int year = util.parseYearFromUnixtime(o.getDate());
					NewsResult res = newsResultDao.findByParentRecNo(provider, year, o.getUuid());
					if (res != null) {
						util.injectNewsResult(dto, res);
					}
					rnd += 1;
					index += 1;
					list.add(dto);
				}
				resp.setContent(list);
			}
		}
			break;
		case MQL5: {
			Page<NewsMql5> objects = null;
			objects = newsMql5Dao.findByTitleInPages(currency.name(), title, page, max);
			if (objects != null) {
				List<NewsDto> list = new ArrayList<>();
				resp = new Page<NewsDto>();
				resp.setNumber(objects.getNumber());
				resp.setTotalPages(objects.getTotalPages());
				int rnd = random.nextInt(900) + 100;
				int index = 1;
				for (NewsMql5 o : (List<NewsMql5>) objects.getContent()) {
					o.setCountryName(null);
					NewsDto dto = util.toNewsDto(rnd, index, o);
					int year = util.parseYearFromUnixtime(o.getReleaseDate());
					NewsResult res = newsResultDao.findByParentRecNo(provider, year, o.getUuid());
					if (res != null) {
						util.injectNewsResult(dto, res);
					}
					rnd += 1;
					index += 1;
					list.add(dto);
				}
				resp.setContent(list);
			}
		}
			break;
		case FXSTREET: {
			Page<NewsFxStreet> objects = null;
			objects = newsFxStreetDao.findByTitleInPages(currency.name(), title, page, max);
			if (objects != null) {
				List<NewsDto> list = new ArrayList<>();
				resp = new Page<NewsDto>();
				resp.setNumber(objects.getNumber());
				resp.setTotalPages(objects.getTotalPages());
				int rnd = random.nextInt(900) + 100;
				int index = 1;
				for (NewsFxStreet o : (List<NewsFxStreet>) objects.getContent()) {
					NewsDto dto = util.toNewsDto(rnd, index, o);
					int year = util.parseYearFromUnixtime(o.getDate());
					NewsResult res = newsResultDao.findByParentRecNo(provider, year, o.getUuid());
					if (res != null) {
						util.injectNewsResult(dto, res);
					}
					rnd += 1;
					index += 1;
					list.add(dto);
				}
				resp.setContent(list);
			}
		}
			break;
		case DAILYFX: {
			Page<NewsDailyFx> objects = null;
			objects = newsDailyFxDao.findByTitleInPages(currency.name(), title, page, max);
			if (objects != null) {
				List<NewsDto> list = new ArrayList<>();
				resp = new Page<NewsDto>();
				resp.setNumber(objects.getNumber());
				resp.setTotalPages(objects.getTotalPages());
				int rnd = random.nextInt(900) + 100;
				int index = 1;
				for (NewsDailyFx o : (List<NewsDailyFx>) objects.getContent()) {
					NewsDto dto = util.toNewsDto(rnd, index, o);
					int year = util.parseYearFromUnixtime(o.getDate());
					NewsResult res = newsResultDao.findByParentRecNo(provider, year, o.getUuid());
					if (res != null) {
						util.injectNewsResult(dto, res);
					}
					rnd += 1;
					index += 1;
					list.add(dto);
				}
				resp.setContent(list);
			}
		}
			break;
		}
		return resp;
	}

	@Override
	public Page<NewsDto> findAllEventsForPairs(ENewsOrigin provider, ECurrency currency, int page, String title) {
		Page<NewsDto> resp = null;
		switch (provider) {
		case DUKASCOPY: {
			Page<NewsDukascopy> objects = null;
			objects = newsDukascopyDao.findByTitleInPages(currency.name(), title, page, 1);
			if (objects != null) {
				List<NewsDto> list = new ArrayList<>();
				resp = new Page<NewsDto>();
				resp.setNumber(objects.getNumber());
				resp.setTotalPages(objects.getTotalPages());
				NewsDukascopy o = objects.getContent().get(0);
				NewsDto dto = util.toNewsDto(random.nextInt(900) + 100, 1, o);
				int year = util.parseYearFromUnixtime(o.getDate());
				NewsResult res = newsResultDao.findByParentRecNo(provider, year, o.getUuid());
				if (res != null) {
					util.injectNewsResult(dto, res);
				}
				list.add(dto);
				resp.setContent(list);
			}
		}
			break;
		case MQL5: {
			Page<NewsMql5> objects = null;
			objects = newsMql5Dao.findByTitleInPages(currency.name(), title, page, 1);
			if (objects != null) {
				List<NewsDto> list = new ArrayList<>();
				resp = new Page<NewsDto>();
				resp.setNumber(objects.getNumber());
				resp.setTotalPages(objects.getTotalPages());
				NewsMql5 o = objects.getContent().get(0);
				o.setCountryName(null);
				NewsDto dto = util.toNewsDto(random.nextInt(900) + 100, 1, o);
				int year = util.parseYearFromUnixtime(o.getReleaseDate());
				NewsResult res = newsResultDao.findByParentRecNo(provider, year, o.getUuid());
				if (res != null) {
					util.injectNewsResult(dto, res);
				}
				list.add(dto);
				resp.setContent(list);
			}
		}
			break;
		case FXSTREET: {
			Page<NewsFxStreet> objects = null;
			objects = newsFxStreetDao.findByTitleInPages(currency.name(), title, page, 1);
			if (objects != null) {
				List<NewsDto> list = new ArrayList<>();
				resp = new Page<NewsDto>();
				resp.setNumber(objects.getNumber());
				resp.setTotalPages(objects.getTotalPages());
				NewsFxStreet o = objects.getContent().get(0);
				NewsDto dto = util.toNewsDto(random.nextInt(900) + 100, 1, o);
				int year = util.parseYearFromUnixtime(o.getDate());
				NewsResult res = newsResultDao.findByParentRecNo(provider, year, o.getUuid());
				if (res != null) {
					util.injectNewsResult(dto, res);
				}
				list.add(dto);
				resp.setContent(list);
			}
		}
			break;
		case DAILYFX: {
			Page<NewsDailyFx> objects = null;
			objects = newsDailyFxDao.findByTitleInPages(currency.name(), title, page, 1);
			if (objects != null) {
				List<NewsDto> list = new ArrayList<>();
				resp = new Page<NewsDto>();
				resp.setNumber(objects.getNumber());
				resp.setTotalPages(objects.getTotalPages());
				NewsDailyFx o = objects.getContent().get(0);
				NewsDto dto = util.toNewsDto(random.nextInt(900) + 100, 1, o);
				int year = util.parseYearFromUnixtime(o.getDate());
				NewsResult res = newsResultDao.findByParentRecNo(provider, year, o.getUuid());
				if (res != null) {
					util.injectNewsResult(dto, res);
				}
				list.add(dto);
				resp.setContent(list);
			}
		}
			break;
		}
		return resp;
	}

	@Override
	public List<NewsDto> findAllOtherEventsByTime(ENewsOrigin provider, EPair pair, long time, String uuid) {
		List<NewsDto> others = new ArrayList<>();
		switch (provider) {
		case DUKASCOPY: {
			List<NewsDukascopy> events = newsDukascopyDao.findByTimeExcept(time, uuid, util.getAvailCurrencies(pair));
			if (events != null && !events.isEmpty()) {
				int rnd = 1;
				int index = 1;
				others = new ArrayList<>();
				for (NewsDukascopy o : events) {
					NewsDto dto = util.toNewsDto(rnd, index, o);
					int year = util.parseYearFromUnixtime(time);
					NewsResult res = newsResultDao.findByParentRecNo(provider, year, o.getUuid());
					if (res != null) {
						util.injectNewsResult(dto, res);
					}
					rnd += 1;
					index += 1;
					others.add(dto);
				}
			}
		}
			break;
		case MQL5: {
			List<NewsMql5> events = newsMql5Dao.findByTimeExcept(time, uuid, util.getAvailCurrencies(pair));
			if (events != null && !events.isEmpty()) {
				int rnd = 1;
				int index = 1;
				others = new ArrayList<>();
				for (NewsMql5 o : events) {
					NewsDto dto = util.toNewsDto(rnd, index, o);
					int year = util.parseYearFromUnixtime(time);
					NewsResult res = newsResultDao.findByParentRecNo(provider, year, o.getUuid());
					if (res != null) {
						util.injectNewsResult(dto, res);
					}
					rnd += 1;
					index += 1;
					others.add(dto);
				}
			}
		}
			break;
		case FXSTREET: {
			List<NewsFxStreet> events = newsFxStreetDao.findByTimeExcept(time, uuid, util.getAvailCurrencies(pair));
			if (events != null && !events.isEmpty()) {
				int rnd = 1;
				int index = 1;
				others = new ArrayList<>();
				for (NewsFxStreet o : events) {
					NewsDto dto = util.toNewsDto(rnd, index, o);
					int year = util.parseYearFromUnixtime(time);
					NewsResult res = newsResultDao.findByParentRecNo(provider, year, o.getUuid());
					if (res != null) {
						util.injectNewsResult(dto, res);
					}
					rnd += 1;
					index += 1;
					others.add(dto);
				}
			}
		}
			break;
		case DAILYFX: {
			List<NewsDailyFx> events = newsDailyFxDao.findByTimeExcept(time, uuid, util.getAvailCurrencies(pair));
			if (events != null && !events.isEmpty()) {
				int rnd = 1;
				int index = 1;
				others = new ArrayList<>();
				for (NewsDailyFx o : events) {
					NewsDto dto = util.toNewsDto(rnd, index, o);
					int year = util.parseYearFromUnixtime(time);
					NewsResult res = newsResultDao.findByParentRecNo(provider, year, o.getUuid());
					if (res != null) {
						util.injectNewsResult(dto, res);
					}
					rnd += 1;
					index += 1;
					others.add(dto);
				}
			}
		}
			break;
		}
		return others;
	}

	@Async
	@Override
	public CompletableFuture<ForecastDto> getEventForecasts(ENewsOrigin provider, String title, EPair pair,
			ECurrency currency, Long time, int offset, int max) {
		int maxRecs = max;
		String curr = currency.name();
		List<NewsAndResult> values = new ArrayList<>();
		switch (provider) {
		case DUKASCOPY: {
			Page<NewsDukascopy> pg = newsDukascopyDao.findByTitleInRecords(curr, title, offset, maxRecs);
			for (NewsDukascopy o : pg.getContent()) {
				NewsAndResult vo = new NewsAndResult();
				int year = util.parseYearFromUnixtime(o.getDate());
				NewsResult nr = newsResultDao.findByParentRecNo(provider, year, o.getUuid());
				if (nr != null) {
					if (nr.getAvf() != null || nr.getFvp() != null) {
						vo.setTime(o.getDate());
						vo.setAvf(nr.getAvf());
						vo.setAvfDiff(nr.getAvfDiff());
						vo.setAvp(nr.getAvp());
						vo.setAvpDiff(nr.getAvpDiff());
						vo.setFvp(nr.getFvp());
						vo.setFvpDiff(nr.getFvpDiff());
						values.add(vo);
					}
				}
			}
		}
			break;
		case MQL5: {
			Page<NewsMql5> pg = newsMql5Dao.findByTitleInRecords(curr, title, offset, maxRecs);
			for (NewsMql5 o : pg.getContent()) {
				NewsAndResult vo = new NewsAndResult();
				int year = util.parseYearFromUnixtime(o.getReleaseDate());
				NewsResult nr = newsResultDao.findByParentRecNo(provider, year, o.getUuid());
				if (nr != null) {
					if (nr.getAvf() != null || nr.getFvp() != null) {
						vo.setTime(o.getReleaseDate());
						vo.setAvf(nr.getAvf());
						vo.setAvfDiff(nr.getAvfDiff());
						vo.setAvp(nr.getAvp());
						vo.setAvpDiff(nr.getAvpDiff());
						vo.setFvp(nr.getFvp());
						vo.setFvpDiff(nr.getFvpDiff());
						values.add(vo);
					}
				}
			}
		}
			break;
		case FXSTREET: {
			Page<NewsFxStreet> pg = newsFxStreetDao.findByTitleInRecords(curr, title, offset, maxRecs);
			for (NewsFxStreet o : pg.getContent()) {
				NewsAndResult vo = new NewsAndResult();
				int year = util.parseYearFromUnixtime(o.getDate());
				NewsResult nr = newsResultDao.findByParentRecNo(provider, year, o.getUuid());
				if (nr != null) {
					if (nr.getAvf() != null || nr.getFvp() != null) {
						vo.setTime(o.getDate());
						vo.setAvf(nr.getAvf());
						vo.setAvfDiff(nr.getAvfDiff());
						vo.setAvp(nr.getAvp());
						vo.setAvpDiff(nr.getAvpDiff());
						vo.setFvp(nr.getFvp());
						vo.setFvpDiff(nr.getFvpDiff());
						values.add(vo);
					}
				}
			}
		}
			break;
		case DAILYFX: {
			Page<NewsDailyFx> pg = newsDailyFxDao.findByTitleInRecords(curr, title, offset, maxRecs);
			for (NewsDailyFx o : pg.getContent()) {
				NewsAndResult vo = new NewsAndResult();
				int year = util.parseYearFromUnixtime(o.getDate());
				NewsResult nr = newsResultDao.findByParentRecNo(provider, year, o.getUuid());
				if (nr != null) {
					if (nr.getAvf() != null || nr.getFvp() != null) {
						vo.setTime(o.getDate());
						vo.setAvf(nr.getAvf());
						vo.setAvfDiff(nr.getAvfDiff());
						vo.setAvp(nr.getAvp());
						vo.setAvpDiff(nr.getAvpDiff());
						vo.setFvp(nr.getFvp());
						vo.setFvpDiff(nr.getFvpDiff());
						values.add(vo);
					}
				}
			}
		}
			break;
		}
		if (!values.isEmpty()) {
			ForecastDto dto = new ForecastDto(values.size());
			for (NewsAndResult nr : values) {
				if (compare(nr.getAvp(), nr.getFvp())) {
					dto.setCorrect(dto.getCorrect() + 1);
					if (nr.getAvf() == EReality.AEF) {
						dto.setPrecise(dto.getPrecise() + 1);
					} else {
						if (nr.getAvf() == EReality.ABF) {
							dto.setBigger(dto.getBigger() + 1);
						}
						if (nr.getAvf() == EReality.ASF) {
							dto.setSmaller(dto.getSmaller() + 1);
						}
					}
				} else {
					dto.setWrong(dto.getWrong() + 1);
				}
			}
			return CompletableFuture.completedFuture(dto);
		}
		return CompletableFuture.completedFuture(null);
	}

	private boolean compare(EReality avp, EReality fvp) {
		boolean status = false;
		if ((avp == EReality.AEP) && (fvp == EReality.FEP)) {
			status = true;
		}
		if ((avp == EReality.ABP) && (fvp == EReality.FBP)) {
			status = true;
		}
		if ((avp == EReality.ASP) && (fvp == EReality.FSP)) {
			status = true;
		}
		return status;
	}

}