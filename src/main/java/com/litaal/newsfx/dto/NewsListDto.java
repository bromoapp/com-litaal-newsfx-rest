package com.litaal.newsfx.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.litaal.newsfx.constant.ECurrency;
import com.litaal.newsfx.constant.EPair;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewsListDto {

	private EPair pair;
	private int total;
	private ECurrency currency;
	private List<NewsDto> events;

	public NewsListDto(List<NewsDto> events) {
		super();
		this.total = events.size();
		this.events = events;
	}

	public NewsListDto(EPair pair, List<NewsDto> events) {
		super();
		this.pair = pair;
		this.total = events.size();
		this.events = events;
	}

	public NewsListDto(ECurrency currency, List<NewsDto> events) {
		super();
		this.currency = currency;
		this.total = events.size();
		this.events = events;
	}

	public EPair getPair() {
		return pair;
	}

	public void setPair(EPair pair) {
		this.pair = pair;
	}

	public ECurrency getCurrency() {
		return currency;
	}

	public void setCurrency(ECurrency currency) {
		this.currency = currency;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<NewsDto> getEvents() {
		return events;
	}

	public void setEvents(List<NewsDto> events) {
		this.events = events;
	}

}
