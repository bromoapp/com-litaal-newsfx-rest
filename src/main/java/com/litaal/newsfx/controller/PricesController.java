package com.litaal.newsfx.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.litaal.newsfx.constant.ECurrency;
import com.litaal.newsfx.constant.ENewsOrigin;
import com.litaal.newsfx.constant.EPair;
import com.litaal.newsfx.constant.ETimeFrame;
import com.litaal.newsfx.dto.PricesListDto;
import com.litaal.newsfx.dto.SwingsDto;
import com.litaal.newsfx.dto.VolatilitiesDto;
import com.litaal.newsfx.service.IPricesService;

@RestController
@RequestMapping(path = "api/prices")
public class PricesController {

	@Autowired
	private IPricesService pricesService;

	/**
	 * Get prices by event time *
	 * 
	 * @param pair      the pair for which the prices will be retrieved
	 * @param timeframe the timeframe for which the prices will be retrieved
	 * @param time      the time for which the prices will be retrieved
	 * @return a list of prices and related data
	 * @throws Exception
	 */
	@PostMapping(path = "/by_eventtime")
	public CompletableFuture<PricesListDto> prices(@RequestParam("pair") String pair,
			@RequestParam("timeframe") String timeframe, @RequestParam("time") Long time) throws Exception {
		EPair currPair = EPair.valueOf(pair);
		ETimeFrame tframe = ETimeFrame.valueOf(timeframe);
		return pricesService.getPricesByTime(currPair, tframe, time);
	}

	/**
	 * Get volatilities by event time
	 * 
	 * @param provider the news provider
	 * @param title    the title of the event
	 * @param pair     the pair for which the volatilities will be retrieved
	 * @param currency the currency for which the volatilities will be retrieved
	 * @param time     the time for which the volatilities will be retrieved
	 * @param page     the page of the volatilities
	 * @param index    the index of the volatilities
	 * @param perpage  the number of volatilities per page
	 * @param max      the maximum number of volatilities
	 * @return a list of volatilities
	 */
	@PostMapping(path = "/volatilities")
	public CompletableFuture<VolatilitiesDto> volatilities(@RequestParam("provider") ENewsOrigin provider,
			@RequestParam("title") String title, @RequestParam("pair") EPair pair,
			@RequestParam("currency") ECurrency currency, @RequestParam("time") Long time,
			@RequestParam("page") int page, @RequestParam("index") int index, @RequestParam("perpage") int perpage,
			@RequestParam("max") int max) {
		int offset = (max * (page - 1) + (index - 1)) - ((page - 1) * perpage);
		if (perpage == 1) {
			offset = page - 1;
		}
		return pricesService.getEventVolatilities(provider, title, pair, currency, time, offset, max);
	}

	/**
	 * Get swings by event time
	 * 
	 * @param provider the news provider
	 * @param title    the title of the event
	 * @param pair     the pair for which the swings will be retrieved
	 * @param currency the currency for which the swings will be retrieved
	 * @param time     the time for which the swings will be retrieved
	 * @param page     the page of the swings
	 * @param index    the index of the swings
	 * @param perpage  the number of swings per page
	 * @param max      the maximum number of swings
	 * @return a list of swings
	 */
	@PostMapping(path = "/swings")
	public CompletableFuture<SwingsDto> swings(@RequestParam("provider") ENewsOrigin provider,
			@RequestParam("title") String title, @RequestParam("pair") EPair pair,
			@RequestParam("currency") ECurrency currency, @RequestParam("time") Long time,
			@RequestParam("page") int page, @RequestParam("index") int index, @RequestParam("perpage") int perpage,
			@RequestParam("max") int max) {
		int offset = (max * (page - 1) + (index - 1)) - ((page - 1) * perpage);
		if (perpage == 1) {
			offset = page - 1;
		}
		return pricesService.getEventSwings(provider, title, pair, currency, time, offset, max);
	}

}
