package com.hk.Models;

import com.hk.components.UserProfile;

public class LoginModel extends AccessControlsModel{
	UserProfile currentUser;
	
	public LoginModel() {
	
	}
	
	public boolean doesUserExist() {
			for(UserProfile finduser : getUsers())
			{
			if(finduser.getUserName().equals(userName) && new String(finduser.getPasswordField().getPassword()).equals(Password)) {
				setCurrentUser(finduser);
				return true;
			}
			}
		return false;
		}
	
	public UserProfile getCurrentUser() {
		return currentUser;
	}

	private void setCurrentUser(UserProfile currentUser) {
		this.currentUser = currentUser;
	}

}
