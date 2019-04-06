package com.hk.Views;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import com.hk.components.QA;

public class SQAScreen extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel question;
	private JTextArea answerField;
	private JScrollPane answerScroll;
	
	public SQAScreen(QA qa) {
		question = new JLabel(qa.getQuestion());
		answerField = new JTextArea();
		answerField.setLineWrap(true);
		answerField.setWrapStyleWord(true);
		answerField.setText(qa.getAnswer());
		answerScroll = new JScrollPane(answerField,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		answerScroll.setPreferredSize(new Dimension(400, 70));
		add(question);
		add(answerScroll);
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
