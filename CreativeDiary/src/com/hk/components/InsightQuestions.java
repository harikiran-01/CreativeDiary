package com.hk.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InsightQuestions {
	private List<List<QA>> questions;
	private List<QA> selectquestions;
	
	public InsightQuestions() {
		questions = new ArrayList<List<QA>>();
		questionbank();
		selectquestions = new ArrayList<QA>();
	}
	
	private void questionbank() {
		List<QA> personq = new ArrayList<QA>();
		personq.add(new QA("Who did you spend most of your time with today?"));
		personq.add(new QA("Do you wish you had not met this person today?"));
		personq.add(new QA("Did you helped anyone close to you today?"));
		List<QA> timeq = new ArrayList<QA>();
		timeq.add(new QA("Which is the most regretting thing you have done today?"));
		timeq.add(new QA("Do you wish you had anything differently?"));
		timeq.add(new QA("Which is the most nicest thing you've done today?"));
		
		questions.add(personq);
		questions.add(timeq);
	}

	public List<QA> generateQuestions(){
		Random rand = new Random();
		for(int i=0;i<2;i++) 
		selectquestions.add(questions.get(i).get(rand.nextInt(3)));
		return selectquestions;
	}
}
