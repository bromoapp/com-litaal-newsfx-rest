package com.litaal.newsfx.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.litaal.newsfx.model.Paging;
import com.litaal.newsfx.util.CommonUtil;

public abstract class AbstractNewsProvider<T> implements INewsProviderDao<T> {

	@Autowired
	protected CommonUtil util;

	protected Paging getPagingInfo(int total, int maxPerPage, int pgno) {
		int pages = total / maxPerPage;
		int remains = total % maxPerPage;
		if (remains > 0) {
			pages += 1;
		}
		return new Paging(pages, ((pgno - 1) * maxPerPage));
	}

}
