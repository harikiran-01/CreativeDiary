package com.hk.components;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class SQAPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel question;
	private JTextArea answerField;
	public SQAPanel(String ques, String answer) {
		initComponents(ques, answer);
		question.setText(ques);
		answerField.setText(answer);
		setPreferredSize(new Dimension(500, 70));
		setMaximumSize(getPreferredSize());
		add(question);
		add(answerField);
		setVisible(true);
	}
	
	private void initComponents(String ques, String answer) {
		question = new JLabel(ques, 10);
		answerField = new JTextArea(2, 30);
		answerField.setText(answer);
	}

	public String getQuestionfromLabel() {
		return question.getText();
	}

	public String getAnswerfromField() {
		return answerField.getText();
	}
	
	public void setAnswerFieldViewable() {
		answerField.setEditable(false);
	}
	
}
