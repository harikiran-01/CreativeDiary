package com.hk.panelbuilders;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import com.hk.components.CurrentUser;
import com.hk.components.UserProfile;
import com.hk.ui.HomePage;
import com.hk.ui.LockPage;

public class loginPanelCreator extends AccessControls implements ActionListener{	
	private UserProfile user;
	private static JPanel loginPanel =  new JPanel();
	private JButton login;
	private JLabel status;
	private JProgressBar load;
	int loadcount=0;
	public loginPanelCreator(){	
		super(loginPanel);
		initComponents();
		addComponents();
		
		Timer t = new Timer(250,this);
		login.addActionListener(new ActionListener() {		
				public void actionPerformed(ActionEvent ae) {
					if(getStoredUserData()) {
					for(UserProfile finduser : users)
					{
					if(finduser.getUserName().equals(userName.getText()) && Arrays.equals(finduser.getPasswordField().getPassword(), passwordField.getPassword())) {
						status.setVisible(false);
						load.setVisible(true);
						user = finduser;
						CurrentUser.init(user);
						t.start();
					}
					else {
						status.setText("Invalid passcode. Try Again");
					}
					}
					}
					else {
						status.setText("<html><body>No Registered Users!<br>Sign Up to start using</body></html>");
					}
				}
			});

}

private void initComponents() {
		//login panel
		loginPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 15));
		//login button
		login = new JButton("LOGIN");	
		//login status label
		status = new JLabel();
		//progress bar
		load = new JProgressBar(0,2);
		load.setVisible(false);
	}

private void addComponents() {
	loginPanel.add(login);
	loginPanel.add(load);
	loginPanel.add(status);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(loadcount==2)
		{
			new HomePage();
			LockPage.UserLogin.dispose();		 
		}
		loadcount++;
		load.setValue(loadcount);
	}
	
public JPanel getPanel() {
	return loginPanel;
}
}
