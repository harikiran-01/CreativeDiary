package com.hk.components;

public class DiaryPage {
	private CustomDate date;
	private String content;
	private int rating;

	public CustomDate getDate() {
		return date;
	}
	public void setDate(CustomDate date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}	
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public DiaryPage(CustomDate date, String content, int rating) {
		this.date = date;
		this.content = content;
		this.rating = rating;
	}
}
