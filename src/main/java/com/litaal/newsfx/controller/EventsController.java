package com.litaal.newsfx.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.litaal.newsfx.constant.ECurrency;
import com.litaal.newsfx.constant.ENewsOrigin;
import com.litaal.newsfx.constant.EPair;
import com.litaal.newsfx.dto.ForecastDto;
import com.litaal.newsfx.dto.NewsDto;
import com.litaal.newsfx.dto.NewsListDto;
import com.litaal.newsfx.model.Page;
import com.litaal.newsfx.service.IIndexedNewsService;
import com.litaal.newsfx.service.INewsService;

@RestController
@RequestMapping(path = "api/events")
public class EventsController {

	@Value("${currencies.avail}")
	private String[] availCurrencies;

	@Autowired
	private INewsService newsService;

	@Autowired
	private IIndexedNewsService indexedNewsService;

	/**
	 * Get all unique events for a specific pair
	 * 
	 * @param provider the news provider
	 * @param pair     the pair for which the events will be retrieved
	 * @return the list of events
	 */
	@PostMapping(path = "/by_pair")
	public ResponseEntity<NewsListDto> getNavItemsByPair(@RequestParam("provider") ENewsOrigin provider,
			@RequestParam("pair") EPair pair) {
		List<NewsDto> list = indexedNewsService.findAllUniqueEventsByPair(provider, pair);
		if (list != null && !list.isEmpty()) {
			return ResponseEntity.ok(new NewsListDto(pair, list));
		}
		return ResponseEntity.noContent().build();
	}

	/**
	 * Get all unique events for a specific currency
	 * 
	 * @param provider the news provider
	 * @param currency the currency for which the events will be retrieved
	 * @return the list of events
	 */
	@PostMapping(path = "/by_currency")
	public ResponseEntity<NewsListDto> getNavItemsByCurrency(@RequestParam("provider") ENewsOrigin provider,
			@RequestParam("currency") ECurrency currency) {
		List<NewsDto> list = indexedNewsService.findAllUniqueEventsByCurrency(provider, currency);
		if (list != null && !list.isEmpty()) {
			return ResponseEntity.ok(new NewsListDto(currency, list));
		}
		return ResponseEntity.noContent().build();
	}

	/**
	 * Get all events that contain specific words
	 * 
	 * @param words the words that the events should contain
	 * @return the list of events
	 */
	@PostMapping(path = "/by_words")
	public ResponseEntity<NewsListDto> getNavItemsByWords(@RequestParam("words") String words) {
		List<NewsDto> list = indexedNewsService.findAllEventsByWords(words);
		if (list != null && !list.isEmpty()) {
			return ResponseEntity.ok(new NewsListDto(list));
		}
		return ResponseEntity.noContent().build();
	}

	/**
	 * Get all events for a specific pair
	 * 
	 * @param provider the news provider
	 * @param title    the title of the event
	 * @param currency the currency for which the events will be retrieved
	 * @param page     the page number
	 * @param max      the maximum number of events per page
	 * @return the list of events
	 */
	@PostMapping(path = "/for_pair")
	public ResponseEntity<Page<NewsDto>> getEventsForPair(@RequestParam("provider") ENewsOrigin provider,
			@RequestParam("title") String title, @RequestParam("currency") ECurrency currency,
			@RequestParam("page") int page, @RequestParam("max") int max) {
		Page<NewsDto> pg = newsService.findAllEventsForPair(provider, currency, page, max, title);
		if (pg != null) {
			return ResponseEntity.ok(pg);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	/**
	 * Get all events for all pairs
	 * 
	 * @param provider the news provider
	 * @param title    the title of the event
	 * @param currency the currency for which the events will be retrieved
	 * @param page     the page number
	 * @return the list of events
	 */
	@PostMapping(path = "/for_pairs")
	public ResponseEntity<Page<NewsDto>> getEventsForPairs(@RequestParam("provider") ENewsOrigin provider,
			@RequestParam("title") String title, @RequestParam("currency") ECurrency currency,
			@RequestParam("page") int page) {
		Page<NewsDto> pg = newsService.findAllEventsForPairs(provider, currency, page, title);
		if (pg != null) {
			return ResponseEntity.ok(pg);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	/**
	 * Get other events
	 * 
	 * @param provider the news provider
	 * @param pair     the pair for which the events will be retrieved
	 * @param time     the time of the event
	 * @param uuid     the uuid of the event
	 * @return the list of events
	 */
	@PostMapping(path = "/others")
	public ResponseEntity<List<NewsDto>> getOtherEvents(@RequestParam("provider") ENewsOrigin provider,
			@RequestParam("pair") EPair pair, @RequestParam("time") long time, @RequestParam("uuid") String uuid) {
		List<NewsDto> ls = newsService.findAllOtherEventsByTime(provider, pair, time, uuid);
		if (ls != null && !ls.isEmpty()) {
			return ResponseEntity.ok(ls);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	/**
	 * Get forecast for a specific pair and news provider
	 * 
	 * @param provider the news provider
	 * @param pair     the pair for which the forecast will be retrieved
	 * @return the forecast
	 */
	@PostMapping(path = "/forecast")
	public CompletableFuture<ForecastDto> forecast(@RequestParam("provider") ENewsOrigin provider,
			@RequestParam("title") String title, @RequestParam("pair") EPair pair,
			@RequestParam("currency") ECurrency currency, @RequestParam("time") Long time,
			@RequestParam("page") int page, @RequestParam("index") int index, @RequestParam("perpage") int perpage,
			@RequestParam("max") int max) {
		int offset = (max * (page - 1) + (index - 1)) - ((page - 1) * perpage);
		return newsService.getEventForecasts(provider, title, pair, currency, time, offset, max);
	}

}
