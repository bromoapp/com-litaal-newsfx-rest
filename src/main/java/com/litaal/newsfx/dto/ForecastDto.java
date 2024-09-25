package com.litaal.newsfx.dto;

public class ForecastDto {

	private int total;
	private int correct;
	private int wrong;
	private int precise;
	private int bigger;
	private int smaller;

	public ForecastDto(int total) {
		super();
		this.total = total;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCorrect() {
		return correct;
	}

	public void setCorrect(int correct) {
		this.correct = correct;
	}

	public int getWrong() {
		return wrong;
	}

	public void setWrong(int wrong) {
		this.wrong = wrong;
	}

	public int getPrecise() {
		return precise;
	}

	public void setPrecise(int precise) {
		this.precise = precise;
	}

	public int getBigger() {
		return bigger;
	}

	public void setBigger(int bigger) {
		this.bigger = bigger;
	}

	public int getSmaller() {
		return smaller;
	}

	public void setSmaller(int smaller) {
		this.smaller = smaller;
	}

}
