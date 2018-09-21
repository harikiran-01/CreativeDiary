package com.hk.panelbuilders;

import java.awt.FlowLayout;

import javax.swing.*;

import com.hk.components.InsightQuestions;
import com.hk.ui.HomePage;

public class QAPanel {
	JDialog qaDialog;
	JPanel sqaPanel;
	int genqarr[];
	InsightQuestions q = new InsightQuestions();
	public QAPanel() {	
		qaDialog = new JDialog(HomePage.getFrame(), "Questions", true);
		genqarr = new int[2];
		qaDialog.setSize(500, 350);	
		qaDialog.setLayout(new FlowLayout(FlowLayout.CENTER, 600, 10));
		for(int i=0;i<2;i++) {
		JPanel qpa =  populatePanel(i);
		qpa.setBounds(75+(i*80), 10, 425, 80);
		qaDialog.add(qpa);					
		}
		qaDialog.repaint();
		qaDialog.revalidate();
		qaDialog.setVisible(true);
	}
	
	private JPanel populatePanel(int qn) {	
		sqaPanel = new JPanel();
		sqaPanel.setSize(500, 100);
		sqaPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 600, 10));
		sqaPanel.add(new JLabel(q.generateQuestions().get(qn).getQuestion()));		
		sqaPanel.add(new JTextArea(2,25));
		return sqaPanel;
	}
	
	public JDialog getDialog() {
		return qaDialog;
	}
	
}
