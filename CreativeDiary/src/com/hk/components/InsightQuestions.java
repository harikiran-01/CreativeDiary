package com.hk.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InsightQuestions {
	public static final int totalq = 3;
	private List<List<QA>> questions;
	private List<QA> selectquestions;
	
	public List<QA> getGeneratedQuestions() {
		return selectquestions;
	}

	public InsightQuestions() {
		questions = new ArrayList<List<QA>>();
		populateQuestions();
		selectquestions = new ArrayList<QA>();
		generatePickedQuestions();
	}
	
	private void populateQuestions() {
		List<QA> personq = new ArrayList<QA>();
		personq.add(new QA("Who did you spend most of your time with today?"));
		personq.add(new QA("Do you wish you had not met this person today?"));
		personq.add(new QA("Have you helped anyone close to you today?"));
		List<QA> timeq = new ArrayList<QA>();
		timeq.add(new QA("What is the most regretting thing you have done today?"));
		timeq.add(new QA("Do you wish you had done anything differently?"));
		timeq.add(new QA("What is the most nicest thing you've done today?"));
		List<QA> habitsq = new ArrayList<QA>();
		habitsq.add(new QA("Did you pick on any new habits today?"));
		habitsq.add(new QA("How much time have you spent on your favourite hobbies?"));
		habitsq.add(new QA("Are you proud of any special thing you acheived in your hobby?"));
		
		questions.add(personq);
		questions.add(timeq);
		questions.add(habitsq);
	}

	public void generatePickedQuestions(){
		Random rand = new Random();
		for(int i=0;i<totalq;i++) 
		selectquestions.add(questions.get(i).get(rand.nextInt(3)));
	}
}
