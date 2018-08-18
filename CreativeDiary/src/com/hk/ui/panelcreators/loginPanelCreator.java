package com.hk.ui.panelcreators;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.Timer;
import com.hk.components.CurrentUser;
import com.hk.components.UserProfile;
import com.hk.ui.HomePage;
import com.hk.ui.LockPage;

public class loginPanelCreator implements ActionListener{
	private String filename = "userdetails.yo";
	private File f = new File(filename);
	private UserProfile user=null;
	private List<UserProfile> users = null;
	private JPanel loginPanel =  new JPanel();
	private JTextField userName;
	private JPasswordField passwordField;
	private JButton login;
	private JLabel ulabel,plabel,status;
	private JProgressBar load;
	int i=0;
	public loginPanelCreator(){	
		loginPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 15));
		userName = new JTextField(15);
		passwordField = new JPasswordField(15);
		login = new JButton("LOGIN");	
		ulabel = new JLabel("Enter Username");
		plabel = new JLabel("Enter Passcode");
		status = new JLabel();
		load = new JProgressBar(0,2);
		loginPanel.add(ulabel);
		loginPanel.add(userName);
		loginPanel.add(plabel);
		loginPanel.add(passwordField);
		loginPanel.add(login);
		loginPanel.add(load);
		loginPanel.add(status);
		load.setVisible(false);
		Timer t = new Timer(250,this);
		login.addActionListener(new ActionListener() {		
				public void actionPerformed(ActionEvent ae) {
					if(getStoredUserData())
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
					else {
						status.setText("<html><body>No Registered Users!<br>Sign Up to start using</body></html>");
					}
				}
			});

}
	
@SuppressWarnings("unchecked")
public boolean getStoredUserData() {
		//reading existing data
		f.setReadOnly();
		if(f.length() == 0)
			return false;
		else {
		try
        {   
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);           
            users = (List<UserProfile>) in.readObject();
            in.close();
            file.close();
        }	         
        catch(IOException ex)
        {
            System.out.println("IOException is caughte");
        }
	 	catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }
		}
		return true;
}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(i==2)
		{
			new HomePage();
			LockPage.UserLogin.dispose();		 
		}
		i++;
		load.setValue(i);
	}
	
public JPanel returnPanel() {
	return this.loginPanel;
}
}
