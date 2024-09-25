package com.litaal.newsfx.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.litaal.newsfx.constant.ECurrency;
import com.litaal.newsfx.constant.ENewsOrigin;
import com.litaal.newsfx.constant.EPair;
import com.litaal.newsfx.dto.ForecastDto;
import com.litaal.newsfx.dto.NewsDto;
import com.litaal.newsfx.model.Page;

public interface INewsService {

	public Page<NewsDto> findAllEventsForPair(ENewsOrigin provider, ECurrency currency, int page, int max,
			String title);

	public Page<NewsDto> findAllEventsForPairs(ENewsOrigin provider, ECurrency currency, int page, String title);

	public List<NewsDto> findAllOtherEventsByTime(ENewsOrigin origin, EPair pair, long time, String uuid);

	public CompletableFuture<ForecastDto> getEventForecasts(ENewsOrigin origin, String title, EPair pair,
			ECurrency currency, Long time, int offset, int max);

}
