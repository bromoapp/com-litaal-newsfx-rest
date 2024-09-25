package com.litaal.newsfx.service;

import java.util.concurrent.CompletableFuture;

import com.litaal.newsfx.constant.ECurrency;
import com.litaal.newsfx.constant.ENewsOrigin;
import com.litaal.newsfx.constant.EPair;
import com.litaal.newsfx.constant.ETimeFrame;
import com.litaal.newsfx.dto.PricesListDto;
import com.litaal.newsfx.dto.SwingsDto;
import com.litaal.newsfx.dto.VolatilitiesDto;

public interface IPricesService {

	public CompletableFuture<PricesListDto> getPricesByTime(EPair pair, ETimeFrame tf, long time);

	public CompletableFuture<VolatilitiesDto> getEventVolatilities(ENewsOrigin origin, String title, EPair pair,
			ECurrency currency, Long time, int page, int max);

	public CompletableFuture<SwingsDto> getEventSwings(ENewsOrigin origin, String title, EPair pair, ECurrency currency,
			Long time, int page, int max);

}
