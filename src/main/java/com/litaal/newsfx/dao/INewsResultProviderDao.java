package com.litaal.newsfx.dao;

import com.litaal.newsfx.constant.ENewsOrigin;
import com.litaal.newsfx.model.NewsResult;

public interface INewsResultProviderDao {

	public NewsResult findByParentRecNo(ENewsOrigin provider, int year, String uuid);
}
