package com.litaal.newsfx.dto;

import com.litaal.newsfx.constant.EReality;

public class NewsAndResult {

	private Long time;
	private EReality avp;
	private Double avpDiff;
	private EReality avf;
	private Double avfDiff;
	private EReality fvp;
	private Double fvpDiff;

	public NewsAndResult() {
		super();
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
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

}
