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
	public static final int READ_MODE = 1;
	public static final int WRITE_MODE = 2;
	private int totalq = InsightQuestions.totalq;
	private JDialog qaDialog;
	private List<SQAPanel> qaListPanel;
	private List<QA> qaList;
	private JPanel filledPanel;
	private JButton saveButton;
	
	public QADialog(List<QA> genList, int accessmode) {	
		initComponents();
		
		this.qaList = genList;
		generateQAPanels(qaList, accessmode);
		if(accessmode == READ_MODE) {
			updatePanelSettings();
			saveButton.setText("COOL");
		}
		buildDialog();
		
		//save button action
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(accessmode == WRITE_MODE)
				for(int i=0;i<totalq;i++) {					
					qaList.get(i).setAnswer(qaListPanel.get(i).getAnswerfromField());
				}
				qaDialog.dispose();
			}
		});
	}
	

	private void updatePanelSettings() {
		
	}


	private void buildDialog() {
		Box list = Box.createVerticalBox();
		
		Iterator<SQAPanel> n = qaListPanel.iterator();
		while(n.hasNext()) {
			list.add((SQAPanel)n.next());
		}
		list.add(buttonPanel());
		filledPanel.add(list);
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
		//save button
		saveButton = new JButton("SAVE");
	}
	
	private void generateQAPanels(List<QA> filledqaList, int accessmode) {	
		for(int i=0; i<totalq; i++) {
		SQAPanel sqa = new SQAPanel(filledqaList.get(i).getQuestion(), filledqaList.get(i).getAnswer());	
		if(accessmode == READ_MODE)
		sqa.setAnswerFieldViewable();
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
	
	public List<QA> getQAData() {
		return qaList;
	}
	
	public void showDialog() {  
		qaDialog.setContentPane(filledPanel);
		qaDialog.setVisible(true);
	}
	
}
