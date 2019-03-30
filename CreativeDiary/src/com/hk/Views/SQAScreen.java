package com.hk.Views;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.hk.components.QA;

public class SQAScreen extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel question;
	private JTextArea answerField;
	
	public SQAScreen(QA qa) {
		question = new JLabel(qa.getQuestion());
		answerField = new JTextArea(2, 30);
		answerField.setText(qa.getAnswer());
		add(question);
		add(answerField);
		setPreferredSize(new Dimension(500, 70));
		setMaximumSize(getPreferredSize());		
		setVisible(true);
	}
	
	public void setQuestion(String q) {
		question.setText(q);
	}
	
	public void setAnswer(String a) {
		answerField.setText(a);
	}
	
	public String getQuestionfromLabel() {
		return question.getText();
	}

	public String getAnswerfromField() {
		return answerField.getText();
	}
}
