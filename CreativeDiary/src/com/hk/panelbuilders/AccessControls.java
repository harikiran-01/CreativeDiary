package com.hk.panelbuilders;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.hk.components.UserProfile;
public abstract class AccessControls {
	protected File f;
	protected final String filename = "userdetails.yo";
	public JPanel loginSignupPanel;
	protected JLabel ulabel;
	protected JLabel plabel;
	protected JTextField userName;
	protected JPasswordField passwordField;
	protected JCheckBox rememberMe;
	protected List<UserProfile> users = new ArrayList<UserProfile>();
	public AccessControls() {
		initComponents();
		addComponents();
	}

	private void initComponents() {
		//login panel
		loginSignupPanel = new JPanel();
		loginSignupPanel.setLayout(null);
		//file
		f = new File(filename);
		//username label
		ulabel = new JLabel("Enter Username");
		//username field
		userName = new JTextField(15);
		//password label
		plabel = new JLabel("Enter Passcode");
		//password field
		passwordField = new JPasswordField(15);	
		//save credentials checkbox
		rememberMe = new JCheckBox("Remember Me");
	}
	
	private void addComponents() {
		loginSignupPanel.add(ulabel);
		loginSignupPanel.add(userName);
		loginSignupPanel.add(plabel);
		loginSignupPanel.add(passwordField);
		loginSignupPanel.add(rememberMe);
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
	            System.out.println("IOException is caught while reading user details file");
	        }
		 	catch(ClassNotFoundException ex)
	        {
	            System.out.println("ClassNotFoundException is caught");
	        }
			}
			return true;
	}
	
	public JPanel getPanel() {
		return loginSignupPanel;
	}

}
