package com.litaal.newsfx.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.litaal.newsfx.constant.ECurrency;
import com.litaal.newsfx.constant.ENewsOrigin;
import com.litaal.newsfx.constant.EPair;
import com.litaal.newsfx.dao.IIndexedNewsDao;
import com.litaal.newsfx.dto.NewsDto;
import com.litaal.newsfx.model.IndexedNews;
import com.litaal.newsfx.util.CommonUtil;

@Component
public class IndexedNewsServiceImpl implements IIndexedNewsService {

	@Autowired
	private CommonUtil util;

	@Autowired
	private IIndexedNewsDao dao;

	@Override
	public List<NewsDto> findAllUniqueEventsByPair(ENewsOrigin origin, EPair pair) {
		List<IndexedNews> list = dao.findAllUniqueEventsByPair(origin, pair);
		if (list != null) {
			List<NewsDto> dtos = new ArrayList<>();
			for (IndexedNews o : list) {
				NewsDto dto = util.toNewsDto(o.getId().intValue(), o);
				dtos.add(dto);
			}
			return dtos;
		} else {
			return null;
		}
	}

	public List<NewsDto> findAllUniqueEventsByCurrency(ENewsOrigin origin, ECurrency curr) {
		List<IndexedNews> list = dao.findAllUniqueEventsByCurrency(origin, curr);
		if (list != null) {
			List<NewsDto> dtos = new ArrayList<>();
			for (IndexedNews o : list) {
				NewsDto dto = util.toNewsDto(o.getId().intValue(), o);
				dtos.add(dto);
			}
			return dtos;
		} else {
			return null;
		}
	}

	@Override
	public List<NewsDto> findAllEventsByWords(String words) {
		List<IndexedNews> list = dao.findAllEventsByWords(words);
		if (list != null) {
			List<NewsDto> dtos = new ArrayList<>();
			for (IndexedNews o : list) {
				NewsDto dto = util.toNewsDto(o.getId().intValue(), o);
				dtos.add(dto);
			}
			return dtos;
		} else {
			return null;
		}
	}

}
