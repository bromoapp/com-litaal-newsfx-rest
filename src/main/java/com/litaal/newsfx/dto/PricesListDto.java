package com.litaal.newsfx.dto;

import com.litaal.newsfx.constant.EPair;
import com.litaal.newsfx.constant.ETimeFrame;

public class PricesListDto {

	private EPair pair;
	private ETimeFrame timeframe;
	private EventMarkDto mark;
	private int total;
	private Object[] categories;
	private Object[] prices;
	private Object[] volumes;
	private Object[] asks;
	private Object[] bids;

	public PricesListDto() {
		super();
	}

	public EventMarkDto getMark() {
		return mark;
	}

	public void setMark(EventMarkDto mark) {
		this.mark = mark;
	}

	public EPair getPair() {
		return pair;
	}

	public void setPair(EPair pair) {
		this.pair = pair;
	}

	public ETimeFrame getTimeframe() {
		return timeframe;
	}

	public void setTimeframe(ETimeFrame timeframe) {
		this.timeframe = timeframe;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Object[] getCategories() {
		return categories;
	}

	public void setCategories(Object[] categories) {
		this.categories = categories;
	}

	public Object[] getPrices() {
		return prices;
	}

	public void setPrices(Object[] prices) {
		this.prices = prices;
	}

	public Object[] getVolumes() {
		return volumes;
	}

	public void setVolumes(Object[] volumes) {
		this.volumes = volumes;
	}

	public Object[] getAsks() {
		return asks;
	}

	public void setAsks(Object[] asks) {
		this.asks = asks;
	}

	public Object[] getBids() {
		return bids;
	}

	public void setBids(Object[] bids) {
		this.bids = bids;
	}

}
