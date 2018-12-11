package com.hk.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import com.hk.Models.LoginModel;
import com.hk.Models.RegisterModel;
import com.hk.Views.LoginScreen;
import com.hk.Views.RegisterScreen;
import com.hk.components.CurrentUser;
import com.hk.components.UserProfile;

public class LockPageController {
	private boolean authentication = false;	
	
	public LockPageController(LoginScreen loginView, LoginModel login, RegisterScreen registerView, RegisterModel register) {
		
		loginView.addLoginListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
						if(login.scanStoredUserData()) {
						login.setCredentials(loginView.getUserName(), new String(loginView.getPasswordField().getPassword()));
						if(login.doesUserExist()) {
						authentication = true;
						CurrentUser.init(login.getCurrentUser());
					}
					else {
						authentication = false;
						loginView.setStatusFailed();
					}
					}
					else {
						loginView.setStatusEmpty();
					}
			}
		});
		
		registerView.addRegisterListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				registerView.registerSuccessAlert();
				if(register.scanStoredUserData())
				register.setUser(new UserProfile(registerView.getUserName(), registerView.getDOB() , registerView.getPasswordField()));
				//creating folder for user
				File fd = new File("users\\" + register.getUser().getUserName());
				fd.mkdirs();
				//adding new user to user list
				register.getUsers().add(register.getUser());
				//writing user list back to file
				register.storeUser(register.getUsers());
			}
		});
		
	}

	public boolean getLoginStatus() {
		System.out.println("auth is "+authentication);
		return authentication;
	}
}
