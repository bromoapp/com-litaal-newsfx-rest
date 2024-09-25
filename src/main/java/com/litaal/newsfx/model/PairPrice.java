package com.litaal.newsfx.model;

public class PairPrice implements PairData {

	private Long id;
	private Long time;
	private Double open;
	private Double high;
	private Double low;
	private Double close;
	private Double volume;
	private Double pipGap;
	private Double swingUp;
	private Double swingDown;

	public PairPrice() {
		super();
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getTime() {
		return time;
	}

	@Override
	public void setTime(Long time) {
		this.time = time;
	}

	public Double getOpen() {
		return open;
	}

	public void setOpen(Double open) {
		this.open = open;
	}

	public Double getHigh() {
		return high;
	}

	public void setHigh(Double high) {
		this.high = high;
	}

	public Double getLow() {
		return low;
	}

	public void setLow(Double low) {
		this.low = low;
	}

	public Double getClose() {
		return close;
	}

	public void setClose(Double close) {
		this.close = close;
	}

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public Double getPipGap() {
		return this.pipGap;
	}

	public void setPipGap(Double pipGap) {
		this.pipGap = pipGap;
	}

	public Double getSwingUp() {
		return swingUp;
	}

	public void setSwingUp(Double swingUp) {
		this.swingUp = swingUp;
	}

	public Double getSwingDown() {
		return swingDown;
	}

	public void setSwingDown(Double swingDown) {
		this.swingDown = swingDown;
	}

}
