package com.hk.Views;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class LoginScreen extends AccessUtilsView{
	private JButton login;
	private JLabel status;
	
	public LoginScreen() {
		initComponents();
		addComponents();
	}
	
	public JLabel getStatus() {
		return status;
	}
	
	public void setStatusFailed() {
		status.setText("Invalid credentials! Try Again");
	}
	
	public void setStatusEmpty() {
		status.setBounds(52, 190, 190, 40);
		status.setText("<html><body>  No Registered Users!<br>Sign Up to start using</body></html>");
	}
	
	private void initComponents() {
		//username label
		ulabel.setBounds(70, 18, 103, 14);
		//username field
		userNameField.setBounds(55, 43, 126, 20);
		//password label
		plabel.setBounds(70, 83, 103, 14);
		//password field
		passwordField.setBounds(55, 107, 126, 20);
		//login button
		login = new JButton("LOGIN");
		login.setBounds(67, 147, 97, 23);
		//login status label
		status = new JLabel();
		status.setBounds(37, 193, 190, 40);
	}
	
	private void addComponents() {
		add(login);
		add(status);
	}
	
	public void addLoginListener(ActionListener loginButtonListener) {
		login.addActionListener(loginButtonListener);
	}
	
}
