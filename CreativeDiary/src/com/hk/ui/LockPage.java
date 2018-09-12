package com.hk.ui;
import java.awt.CardLayout;
import javax.swing.*;

import com.hk.ui.panelbuilders.loginPanelCreator;
import com.hk.ui.panelbuilders.signupPanelCreator;

public class LockPage {
	public static JFrame UserLogin = new JFrame("Creative Diary Login");
	private static JTabbedPane optionTab;
	
	public static void main(String[] args) throws Exception {
		UserLogin.setLayout(new CardLayout(0,0));
		UserLogin.setVisible(true);
		UserLogin.setSize(250,300);
		UserLogin.setResizable(false);
		UserLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		optionTab = new JTabbedPane();
		UserLogin.add(optionTab);
		optionTab.addTab("Log In", new loginPanelCreator().returnPanel());
		optionTab.addTab("Sign Up", new signupPanelCreator().returnPanel());		
	}
	
	public static JFrame getFrame() {
		return UserLogin;
	}
}