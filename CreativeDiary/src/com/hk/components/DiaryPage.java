package com.hk.components;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class DiaryPage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2576655342086348513L;
	private CustomDate date;
	private String content;
	private String dayInfoMsg = "";
	private int rating;
	private List<QA> questions;
	
	@Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof DiaryPage)) {
            return false;
        }
        DiaryPage page = (DiaryPage) o;
        return page.getDate().equals(date) && page.getContent().equals(content) && page.getRating() == rating;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date , content , rating);
    }

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
	public List<QA> getQAData() {
		return questions;
	}
	public void setQAData(List<QA> questions) {
		this.questions = questions;
	}
	
	public void setDayInfo(String s) {
		this.dayInfoMsg = s; 
	}
	
	public String getDayInfo() {
		return dayInfoMsg;
	}
	
	public DiaryPage() {
		this(new CustomDate(), "Start writing here", 0, "Click SET to select date");
	}
	
	public DiaryPage(CustomDate date){
		this(date, "Start writing here", 0, "Jot your memories for "+date);
	}
	
	public DiaryPage(CustomDate date, String content, int rating, String dayInfo) {
		this.date = date;
		this.content = content;
		this.rating = rating;
		this.dayInfoMsg = dayInfo;
	}
	
	
}
