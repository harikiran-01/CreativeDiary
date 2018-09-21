package com.hk.components;

public class QA {
	private String question;
	private String answer;
	
	public QA(String question){
		this.question = question;
		this.answer = "";
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
