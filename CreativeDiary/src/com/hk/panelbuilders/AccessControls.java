package com.hk.panelbuilders;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.hk.components.UserProfile;
public abstract class AccessControls {
	protected File f;
	protected final String filename = "userdetails.yo";
	protected JLabel ulabel;
	protected JLabel plabel;
	protected JTextField userName;
	protected JPasswordField passwordField;
	protected List<UserProfile> users;
	public AccessControls() {
		initComponents();
	}
	public AccessControls(JPanel accessPanel) {
		this();
		addComponents(accessPanel);
	}
	private void addComponents(JPanel accessPanel) {
		accessPanel.add(ulabel);
		accessPanel.add(userName);
		accessPanel.add(plabel);
		accessPanel.add(passwordField);
	}

	private void initComponents() {
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
	
	public abstract JPanel getPanel();

}
