package com.litaal.newsfx.model;

import com.litaal.newsfx.constant.EReality;

public class NewsResult {

	private String uuid;
	private EReality avp;
	private Double avpDiff;
	private EReality avf;
	private Double avfDiff;
	private EReality fvp;
	private Double fvpDiff;
	private Integer effect;

	public NewsResult() {
		super();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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
