package com.litaal.newsfx.dao;

import java.util.List;

import com.litaal.newsfx.model.Page;

public interface INewsProviderDao<T> {

	Page<T> findByTitleInPages(String currency, String title, int offset, int max);

	Page<T> findByTitleInRecords(String currency, String title, int offset, int max);

	List<T> findByTimeExcept(Long time, String uuid, Object[] currencies);

	List<T> findUniqueEventsByCurrencies(Object[] currencies);
}
