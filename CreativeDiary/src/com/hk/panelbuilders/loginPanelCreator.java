package com.hk.panelbuilders;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import com.hk.components.CurrentUser;
import com.hk.components.UserProfile;

import core.CDCore;

public class loginPanelCreator extends AccessControls implements ActionListener{	
	private JButton login;
	private JLabel status;
	private JProgressBar load;
	int loadcount;
	Timer t;
	private void initComponents() {
		//username label
		ulabel.setBounds(70, 18, 103, 14);
		//username field
		userName.setBounds(55, 43, 126, 20);
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
		//progress bar
		load = new JProgressBar(0,2);
		load.setBounds(55, 193, 126, 15);
		load.setVisible(false);
		//loading bar counter
		loadcount = 0;
		//loading bar timer
		t = new Timer(250,this);
	}

	private void addComponents() {
	loginSignupPanel.add(login);
	loginSignupPanel.add(load);
	loginSignupPanel.add(status);
	}
	
	public void resetFields() {
		initComponents();
	}
	
	public loginPanelCreator(){	
		initComponents();
		addComponents();
		
		
		login.addActionListener(new ActionListener() {		
				public void actionPerformed(ActionEvent ae) {
					if(getStoredUserData()) {
					for(UserProfile finduser : users)
					{
					if(finduser.getUserName().equals(userName.getText()) && Arrays.equals(finduser.getPasswordField().getPassword(), passwordField.getPassword())) {
						status.setVisible(false);
						load.setVisible(true);
						CurrentUser.init(finduser);
						t.start();
					}
					else {
						status.setText("Invalid credentials! Try Again");
					}
					}
					}
					else {
						status.setBounds(52, 190, 190, 40);
						status.setText("<html><body>  No Registered Users!<br>Sign Up to start using</body></html>");
					}
				}
			});

}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(loadcount==2)
		{
			CDCore.getHomePage();
			CDCore.getLockPage().disposeScreen();		 
		}
		loadcount++;
		load.setValue(loadcount);
	}
}
