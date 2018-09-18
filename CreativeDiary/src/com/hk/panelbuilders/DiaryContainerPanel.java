package com.hk.panelbuilders;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import com.hk.components.CurrentUser;

public class DiaryContainerPanel {
	private JPanel diaryContainerPanel;
	private JPanel welcomePanel;
	
	public DiaryContainerPanel() {
		initComponents();
		addComponents();	
	}
	private void initComponents() {
		//diary container panel
		diaryContainerPanel = new JPanel();
		diaryContainerPanel.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
		diaryContainerPanel.setBounds(166, 0, 528, 475);
		diaryContainerPanel.setLayout(new CardLayout(0, 0));
		//greeting label
		JLabel greetLabel = new JLabel("Welcome "+CurrentUser.getInstance().getUserName()+"! Hope you are having a great day!");
		greetLabel.setFont(new Font("MV Boli", Font.BOLD, 16));
		greetLabel.setBounds(42, 189, 444, 16);
		//welcome panel
		welcomePanel = new JPanel();
		welcomePanel.setLayout(null);	
		welcomePanel.add(greetLabel);
	}
	
	private void addComponents() {
		diaryContainerPanel.add(welcomePanel, "welcomepanel");
	}
	
	public JPanel getPanel() {
		return diaryContainerPanel;
	}
}
