package com.hk.Views;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public abstract class AccessUtilsView extends JPanel{
	protected JLabel ulabel;
	protected JLabel plabel;
	protected JTextField userNameField;
	protected JPasswordField passwordField;
	protected JCheckBox rememberMe;
	
	public AccessUtilsView() {
		System.out.println("called");
		initComponents();
		addComponents();
	}
	
	private void initComponents() {
		//login panel
		setLayout(null);
		//username label
		ulabel = new JLabel("Enter Username");
		//username field
		userNameField = new JTextField(15);
		//password label
		plabel = new JLabel("Enter Passcode");
		//password field
		passwordField = new JPasswordField(15);	
		//save credentials checkbox
		rememberMe = new JCheckBox("Remember Me");
	}
	
	private void addComponents() {
		this.add(ulabel);
		this.add(userNameField);
		this.add(plabel);
		this.add(passwordField);
		this.add(rememberMe);
	}
	
	public String getUserName() {
		return userNameField.getText();
	}

	public void setUserName(String userName) {
		userNameField.setText(userName);
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}
}
