package com.litaal.newsfx.model;

import java.util.List;

public class Page<T> {

	private int totalPages;
	private int number;
	private List<T> content;

	public Page() {
		super();
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

}
