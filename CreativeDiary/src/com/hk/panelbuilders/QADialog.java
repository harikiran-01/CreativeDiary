package com.hk.panelbuilders;

import java.awt.Dimension;
import javax.swing.*;

import com.hk.components.InsightQuestions;
import com.hk.components.QA;
import com.hk.components.SQAPanel;
import com.hk.ui.HomePage;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;

public class QADialog {
	private int totalq = InsightQuestions.totalq;
	private JDialog qaDialog;
	private List<SQAPanel> qaListPanel;
	private List<QA> qaList;
	private JPanel filledPanel;
	private JButton saveButton;
	private InsightQuestions qGenerator;
	public QADialog() {	
		initComponents();
		
		Box list = Box.createVerticalBox();
		populateQAPanel();
		Iterator<SQAPanel> n = qaListPanel.iterator();
		while(n.hasNext()) {
			list.add((SQAPanel)n.next());
		}
		list.add(buttonPanel());
		filledPanel.add(list);
		
		//save button action
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(int i=0;i<totalq;i++) {
					qaList.add(new QA(qaListPanel.get(i).getQuestionfromLabel(), qaListPanel.get(i).getAnswerfromField()));
				}
			}
		});
		
		//adding panel
		addComponents(filledPanel);
	}
	

	private void initComponents() {
		//qa Dialog
		qaDialog = new JDialog(HomePage.getFrame(), "Questions", true);
		qaDialog.setSize(500, totalq*100);	
		//qa list
		qaList = new ArrayList<QA>();
		//qa list panel
		qaListPanel = new ArrayList<SQAPanel>();
		//filled panel
		filledPanel = new JPanel();
		filledPanel.setLayout(new BoxLayout(filledPanel, BoxLayout.Y_AXIS));
		//Insight Questions
		qGenerator = new InsightQuestions();
		//save button
		saveButton = new JButton("SAVE");
	}
	
	private void addComponents(JPanel qaListPanel) {  
		qaDialog.setContentPane(qaListPanel);
		qaDialog.setVisible(true);
	}

	private void populateQAPanel() {	
		for(int i=0; i<totalq; i++) {
		SQAPanel sqa = new SQAPanel();
		sqa.setQuestiontoLabel(qGenerator.getGeneratedQuestions().get(i).getQuestion());
		qaListPanel.add(sqa);
		}
	}
	
	private JPanel buttonPanel() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(500, 30));
		buttonPanel.setMaximumSize(buttonPanel.getPreferredSize());
		buttonPanel.add(saveButton);
		return buttonPanel;
	}
	
	public JDialog getDialog() {
		return qaDialog;
	}
	
	public List<QA> getQAData() {
		return qaList;
	}
	
}
