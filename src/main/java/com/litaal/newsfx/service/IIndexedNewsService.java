package com.litaal.newsfx.service;

import java.util.List;

import com.litaal.newsfx.constant.ECurrency;
import com.litaal.newsfx.constant.ENewsOrigin;
import com.litaal.newsfx.constant.EPair;
import com.litaal.newsfx.dto.NewsDto;

public interface IIndexedNewsService {

	List<NewsDto> findAllUniqueEventsByPair(ENewsOrigin origin, EPair pair);

	List<NewsDto> findAllUniqueEventsByCurrency(ENewsOrigin origin, ECurrency currency);

	List<NewsDto> findAllEventsByWords(String words);
}
