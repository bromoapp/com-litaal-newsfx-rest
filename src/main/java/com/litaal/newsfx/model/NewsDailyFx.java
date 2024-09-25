package com.litaal.newsfx.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewsDailyFx {
	public static String TBLNAME = "news_dailyfx";

	private String uuid;
	private Long id;
	private String ticker;
	private String symbol;
	private Long date;
	private String title;
	private String description;
	private String importance;
	private String previous;
	private Double previousVal;
	private String forecast;
	private Double forecastVal;
	private String country;
	private String actual;
	private Double actualVal;
	private Boolean allDayEvent;
	private String currency;
	private String reference;
	private String revised;
	private String meanActual;
	private String meanPrevious;
	private String lastUpdate;

	public NewsDailyFx() {
		super();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImportance() {
		return importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}

	public String getPrevious() {
		return previous;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

	public Double getPreviousVal() {
		return previousVal;
	}

	public void setPreviousVal(Double previousVal) {
		this.previousVal = previousVal;
	}

	public String getForecast() {
		return forecast;
	}

	public void setForecast(String forecast) {
		this.forecast = forecast;
	}

	public Double getForecastVal() {
		return forecastVal;
	}

	public void setForecastVal(Double forecastVal) {
		this.forecastVal = forecastVal;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getActual() {
		return actual;
	}

	public void setActual(String actual) {
		this.actual = actual;
	}

	public Double getActualVal() {
		return actualVal;
	}

	public void setActualVal(Double actualVal) {
		this.actualVal = actualVal;
	}

	public Boolean getAllDayEvent() {
		return allDayEvent;
	}

	public void setAllDayEvent(Boolean allDayEvent) {
		this.allDayEvent = allDayEvent;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getRevised() {
		return revised;
	}

	public void setRevised(String revised) {
		this.revised = revised;
	}

	public String getMeanActual() {
		return meanActual;
	}

	public void setMeanActual(String meanActual) {
		this.meanActual = meanActual;
	}

	public String getMeanPrevious() {
		return meanPrevious;
	}

	public void setMeanPrevious(String meanPrevious) {
		this.meanPrevious = meanPrevious;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
