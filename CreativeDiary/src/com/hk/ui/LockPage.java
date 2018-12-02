package com.hk.ui;
import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.*;
import com.hk.panelbuilders.signupPanelCreator;


public class LockPage {
	
	private JFrame UserLogin;
	private JTabbedPane optionTab;
	public LockPage(){
		UserLogin = new JFrame("Creative Diary Login");
		initLockScreen();
	}
	
	public void disposeScreen() {
		UserLogin.dispose();
	}
	
	public void initLockScreen() {
		
	}
	public Component getFrame() {
		return UserLogin;
	}
}