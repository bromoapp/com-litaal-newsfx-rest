package com.litaal.newsfx.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewsDukascopy {
	public static String TBLNAME = "news_dukascopy";

	private String uuid;
	private Long date;
	private Long id;
	private String country;
	private String currency;
	private String title;
	private String periodicity;
	private Boolean showDescription;
	private String description;
	private Integer impact;
	private String actual;
	private String actualNorm;
	private String forecast;
	private String forecastNorm;
	private String previous;
	private String previousNorm;
	private String valueOrder;
	private String valueFormat;
	private String tag;
	private Integer historicalCount;
	private Integer effect;
	private String ddSource;
	private String ddMeasures;
	private String ddUsualEffect;
	private String ddFrequency;
	private String ddNextRelease;
	private String ddDerivedVia;
	private String ddAcro;

	private NewsResult fact;

	public NewsDukascopy() {
		super();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPeriodicity() {
		return periodicity;
	}

	public void setPeriodicity(String periodicity) {
		this.periodicity = periodicity;
	}

	public Boolean getShowDescription() {
		return showDescription;
	}

	public void setShowDescription(Boolean showDescription) {
		this.showDescription = showDescription;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getImpact() {
		return impact;
	}

	public void setImpact(Integer impact) {
		this.impact = impact;
	}

	public String getActual() {
		return actual;
	}

	public void setActual(String actual) {
		this.actual = actual;
	}

	public String getActualNorm() {
		return actualNorm;
	}

	public void setActualNorm(String actualNorm) {
		this.actualNorm = actualNorm;
	}

	public String getForecast() {
		return forecast;
	}

	public void setForecast(String forecast) {
		this.forecast = forecast;
	}

	public String getForecastNorm() {
		return forecastNorm;
	}

	public void setForecastNorm(String forecastNorm) {
		this.forecastNorm = forecastNorm;
	}

	public String getPrevious() {
		return previous;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

	public String getPreviousNorm() {
		return previousNorm;
	}

	public void setPreviousNorm(String previousNorm) {
		this.previousNorm = previousNorm;
	}

	public String getValueOrder() {
		return valueOrder;
	}

	public void setValueOrder(String valueOrder) {
		this.valueOrder = valueOrder;
	}

	public String getValueFormat() {
		return valueFormat;
	}

	public void setValueFormat(String valueFormat) {
		this.valueFormat = valueFormat;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer getHistoricalCount() {
		return historicalCount;
	}

	public void setHistoricalCount(Integer historicalCount) {
		this.historicalCount = historicalCount;
	}

	public Integer getEffect() {
		return effect;
	}

	public void setEffect(Integer effect) {
		this.effect = effect;
	}

	public String getDdSource() {
		return ddSource;
	}

	public void setDdSource(String ddSource) {
		this.ddSource = ddSource;
	}

	public String getDdMeasures() {
		return ddMeasures;
	}

	public void setDdMeasures(String ddMeasures) {
		this.ddMeasures = ddMeasures;
	}

	public String getDdUsualEffect() {
		return ddUsualEffect;
	}

	public void setDdUsualEffect(String ddUsualEffect) {
		this.ddUsualEffect = ddUsualEffect;
	}

	public String getDdFrequency() {
		return ddFrequency;
	}

	public void setDdFrequency(String ddFrequency) {
		this.ddFrequency = ddFrequency;
	}

	public String getDdNextRelease() {
		return ddNextRelease;
	}

	public void setDdNextRelease(String ddNextRelease) {
		this.ddNextRelease = ddNextRelease;
	}

	public String getDdDerivedVia() {
		return ddDerivedVia;
	}

	public void setDdDerivedVia(String ddDerivedVia) {
		this.ddDerivedVia = ddDerivedVia;
	}

	public String getDdAcro() {
		return ddAcro;
	}

	public void setDdAcro(String ddAcro) {
		this.ddAcro = ddAcro;
	}

	public NewsResult getFact() {
		return fact;
	}

	public void setFact(NewsResult fact) {
		this.fact = fact;
	}

}
