package com.hk.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.hk.Models.LoginScreenModel;
import com.hk.Views.LoginScreen;
import com.hk.components.CurrentUser;

public class LockPageController {
	private boolean authentication = false;
	private int counter = 0;
	private LoginScreen loginView;
	private LoginScreenModel login;	
	
	public LockPageController(LoginScreen loginView, LoginScreenModel login) {
		this.login = login;
		this.loginView = loginView;
		loginView.addLoginListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
						if(LoginScreenModel.scanStoredUserData()) {
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
		
	}

	public LoginScreenModel getLogin() {
		return login;
	}

	public boolean getLoginStatus() {
		System.out.println("auth is "+authentication);
		return authentication;
	}
}
