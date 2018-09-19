package com.hk.components;

import java.io.Serializable;

public class DiaryPage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2576655342086348513L;
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
	
	public DiaryPage() {
		this.date = new CustomDate(0, 0, 0);
		this.content = "Wow! Such Empty";
		this.rating = 0;
	}
	
	public DiaryPage(CustomDate date, String content, int rating) {
		this.date = date;
		this.content = content;
		this.rating = rating;
	}
}
