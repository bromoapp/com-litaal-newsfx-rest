package com.litaal.newsfx.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewsMql5 {
	public static String TBLNAME = "news_mql5";

	private String uuid;
	private long id;
	private int eventType;
	private int timeMode;
	private int processed;
	private String url;
	private String eventName;
	private String importance;
	private String currencyCode;
	private String forecastValue;
	private String previousValue;
	private String oldPreviousValue;
	private String actualValue;
	private long releaseDate;
	private int impactDirection;
	private String impactValue;
	private String impactValueF;
	private String country;
	private String countryName;
	private String fullDate;

	public NewsMql5() {
		super();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getEventType() {
		return eventType;
	}

	public void setEventType(int eventType) {
		this.eventType = eventType;
	}

	public int getTimeMode() {
		return timeMode;
	}

	public void setTimeMode(int timeMode) {
		this.timeMode = timeMode;
	}

	public int getProcessed() {
		return processed;
	}

	public void setProcessed(int processed) {
		this.processed = processed;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getImportance() {
		return importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getForecastValue() {
		return forecastValue;
	}

	public void setForecastValue(String forecastValue) {
		this.forecastValue = forecastValue;
	}

	public String getPreviousValue() {
		return previousValue;
	}

	public void setPreviousValue(String previousValue) {
		this.previousValue = previousValue;
	}

	public String getOldPreviousValue() {
		return oldPreviousValue;
	}

	public void setOldPreviousValue(String oldPreviousValue) {
		this.oldPreviousValue = oldPreviousValue;
	}

	public String getActualValue() {
		return actualValue;
	}

	public void setActualValue(String actualValue) {
		this.actualValue = actualValue;
	}

	public long getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(long releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int getImpactDirection() {
		return impactDirection;
	}

	public void setImpactDirection(int impactDirection) {
		this.impactDirection = impactDirection;
	}

	public String getImpactValue() {
		return impactValue;
	}

	public void setImpactValue(String impactValue) {
		this.impactValue = impactValue;
	}

	public String getImpactValueF() {
		return impactValueF;
	}

	public void setImpactValueF(String impactValueF) {
		this.impactValueF = impactValueF;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getFullDate() {
		return fullDate;
	}

	public void setFullDate(String fullDate) {
		this.fullDate = fullDate;
	}

}
