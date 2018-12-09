package com.hk.Views;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import com.hk.Models.QAModel;
import com.hk.components.InsightQuestions;
import com.hk.components.QA;

public class QAScreen extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel childPanel;
	private JButton saveButton;
	private int totalq = InsightQuestions.totalq;
	private List<SQAScreen> qaListPanel;
	
	public QAScreen() {	
		initComponents();
		fillDialog();
	}
	
	private void fillDialog() {
		Box list = Box.createVerticalBox();
		for(int i=0; i< QAModel.totalq; i++) {
		qaListPanel.add(new SQAScreen(new QA()));
		list.add(qaListPanel.get(i));
		}
		list.add(new JPanel().add(saveButton));
		childPanel.add(list);
	}

	public void fillQA(List<QA> qa) {
		for(int i=0; i< QAModel.totalq; i++) {
		qaListPanel.get(i).setQuestion(qa.get(i).getQuestion());
		qaListPanel.get(i).setAnswer(qa.get(i).getAnswer());
		}
	}
	
	public List<SQAScreen> getSQAScreen(){
		return qaListPanel;
	}
	
	private void initComponents() {
			setSize(500, totalq*100);
			setLocation(200, 150);
			setResizable(false);
			//qa list panel
			qaListPanel = new ArrayList<SQAScreen>();
			//filled panel
			childPanel = new JPanel();
			childPanel.setLayout(new BoxLayout(childPanel, BoxLayout.Y_AXIS));
			//save button
			saveButton = new JButton("SAVE");
		}
	
	public void setDialogVisible() {
		setContentPane(childPanel);
		setVisible(true);
	}
		
	public void addSaveButtonListener(ActionListener save) {
		saveButton.addActionListener(save);
	}
}
