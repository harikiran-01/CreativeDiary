package com.hk.Models;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import com.hk.components.UserProfile;

abstract class AccessControlsModel {
	String userName, Password;
	protected static File f = new File("userdetails.yo");
	private static List<UserProfile> users = new ArrayList<UserProfile>();
	
	public AccessControlsModel() {
		init();
	}
	
	public void init() {
		userName = "";
		Password = "";
	}
	
	public List<UserProfile> getUsers() {
		return users;
	}
	
	public void setCredentials(String userName, String Password) {
		this.userName = userName;
		this.Password = Password;
	}
	
	@SuppressWarnings("unchecked")
	public boolean scanStoredUserData() {
			//reading existing data
			f.setReadOnly();
			if(f.length() == 0)
				return false;
			else {
			try
	        {   
	            FileInputStream file = new FileInputStream(f);
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
}
