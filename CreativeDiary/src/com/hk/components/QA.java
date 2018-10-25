package com.hk.components;

import java.io.Serializable;

public class QA implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5361940671232901066L;
	private String question;
	private String answer;
	
	public QA(String question){
		this(question, "");
	}
	
	public QA(String question, String answer){
		this.question = question;
		this.answer = answer;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
