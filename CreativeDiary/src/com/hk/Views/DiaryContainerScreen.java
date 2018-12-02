package com.hk.Views;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import com.hk.components.CurrentUser;

public class DiaryContainerScreen extends JPanel{
	private JPanel welcomePanel;
	public DiaryContainerScreen() {
		initComponents();
		addComponents();
	}

	private void initComponents() {
		//diary container panel
		setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
		setBounds(166, 0, 628, 575);
		setLayout(new CardLayout(0, 0));
		//greeting label
		JLabel greetLabel = new JLabel("Welcome "+CurrentUser.getInstance().getUserName()+"! Hope you are having a great day!");
		greetLabel.setFont(new Font("MV Boli", Font.BOLD, 16));
		greetLabel.setBounds(92, 239, 444, 16);
		//welcome panel
		welcomePanel = new JPanel();
		welcomePanel.setLayout(null);	
		welcomePanel.add(greetLabel);
	}
	
	private void addComponents(){
		add(welcomePanel);
	}
	
	public void replaceView(JPanel replace) {
		removeAll();
		repaint();
		revalidate();
		
		add(replace);
		repaint();
		revalidate();
	}
	
}
	
