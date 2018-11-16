package com.hk.ui;
import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.*;

import com.hk.panelbuilders.loginPanelCreator;
import com.hk.panelbuilders.signupPanelCreator;

import core.CDCore;

public class LockPage {
	
	private JFrame UserLogin;
	private JTabbedPane optionTab;
	public LockPage(){
		UserLogin = new JFrame("Creative Diary Login");
		initLockScreen();
	}
	public static void main(String[] args) throws Exception {
		CDCore.getLockPage();
	}
	
	public void disposeScreen() {
		UserLogin.dispose();
	}
	
	public void initLockScreen() {
		UserLogin.setLayout(new CardLayout(0,0));
		UserLogin.setVisible(true);
		UserLogin.setSize(250,300);
		UserLogin.setResizable(false);
		UserLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		optionTab = new JTabbedPane();
		UserLogin.add(optionTab);
		optionTab.addTab("Log In", new loginPanelCreator().getPanel());
		optionTab.addTab("Sign Up", new signupPanelCreator().getPanel());		
	}
	public Component getFrame() {
		return UserLogin;
	}
}