package com.litaal.newsfx.dao;

import java.util.List;

import com.litaal.newsfx.constant.ECurrency;
import com.litaal.newsfx.constant.ENewsOrigin;
import com.litaal.newsfx.constant.EPair;
import com.litaal.newsfx.model.IndexedNews;

public interface IIndexedNewsDao {

	List<IndexedNews> findAllUniqueEventsByPair(ENewsOrigin origin, EPair pair);

	List<IndexedNews> findAllUniqueEventsByCurrency(ENewsOrigin origin, ECurrency curr);

	List<IndexedNews> findAllEventsByWords(String words);

}
