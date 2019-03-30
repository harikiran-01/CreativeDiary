package com.hk.Views;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class LockPageScreen extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTabbedPane optionTab;
	
	public LockPageScreen(LoginScreen loginView, RegisterScreen register) { 
		initComponents();
		addComponents(loginView, register);		
	}
	
	private void initComponents() {
		setLayout(new CardLayout(0,0));
		setVisible(true);
		setSize(250,300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		optionTab = new JTabbedPane();
	}
	
	private void addComponents(LoginScreen loginView, RegisterScreen register) {
		add(optionTab);
		optionTab.addTab("Log In", loginView);
		optionTab.addTab("Register", register);
	}
}
