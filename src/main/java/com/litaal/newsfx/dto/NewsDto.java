package com.litaal.newsfx.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.litaal.newsfx.constant.EReality;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewsDto {

	private int id;
	private int index;
	private String uuid;
	private Long date;
	private String title;
	private String country;
	private String provider;
	private String currency;
	private Integer impact;
	private String actual;
	private String previous;
	private String forecast;
	private Boolean showDescription;
	private String description;
	private EReality avp;
	private Double avpDiff;
	private EReality avf;
	private Double avfDiff;
	private EReality fvp;
	private Double fvpDiff;
	private Integer effect;

	public NewsDto() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
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

	public String getPrevious() {
		return previous;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

	public String getForecast() {
		return forecast;
	}

	public void setForecast(String forecast) {
		this.forecast = forecast;
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

	public EReality getAvp() {
		return avp;
	}

	public void setAvp(EReality avp) {
		this.avp = avp;
	}

	public Double getAvpDiff() {
		return avpDiff;
	}

	public void setAvpDiff(Double avpDiff) {
		this.avpDiff = avpDiff;
	}

	public EReality getAvf() {
		return avf;
	}

	public void setAvf(EReality avf) {
		this.avf = avf;
	}

	public Double getAvfDiff() {
		return avfDiff;
	}

	public void setAvfDiff(Double avfDiff) {
		this.avfDiff = avfDiff;
	}

	public EReality getFvp() {
		return fvp;
	}

	public void setFvp(EReality fvp) {
		this.fvp = fvp;
	}

	public Double getFvpDiff() {
		return fvpDiff;
	}

	public void setFvpDiff(Double fvpDiff) {
		this.fvpDiff = fvpDiff;
	}

	public Integer getEffect() {
		return effect;
	}

	public void setEffect(Integer effect) {
		this.effect = effect;
	}

}
