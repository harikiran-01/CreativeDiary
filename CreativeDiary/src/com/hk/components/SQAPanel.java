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
	public SQAPanel() {
		initComponents();
		setPreferredSize(new Dimension(500, 70));
		setMaximumSize(getPreferredSize());
		add(question);
		add(answerField);
		setVisible(true);
	}
	
	public String getQuestionfromLabel() {
		return question.getText();
	}

	public void setQuestiontoLabel(String question) {
		this.question.setText(question);
	}

	public String getAnswerfromField() {
		return answerField.getText();
	}

	public void setAnswertoField(String answer) {
		this.answerField.setText(answer);
	}

	private void initComponents() {
		question = new JLabel("question");
		answerField = new JTextArea(2,30);
	}
	
}
