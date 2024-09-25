package com.litaal.newsfx.model;

import com.litaal.newsfx.constant.ECurrency;

public class IndexedNews {

	private Long id;
	private String title;
	private String country;
	private Integer impact;
	private ECurrency currency;
	private String provider;

	public IndexedNews() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getImpact() {
		return impact;
	}

	public void setImpact(Integer impact) {
		this.impact = impact;
	}

	public ECurrency getCurrency() {
		return currency;
	}

	public void setCurrency(ECurrency currency) {
		this.currency = currency;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	@Override
	public String toString() {
		return "IndexedNews [id=" + id + ", title=" + title + ", country=" + country + ", impact=" + impact
				+ ", currency=" + currency + ", provider=" + provider + "]";
	}

}
