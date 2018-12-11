package com.hk.app;

import com.hk.Controllers.HomePageController;
import com.hk.Controllers.LockPageController;
import com.hk.Models.*;
import com.hk.Views.*;

public class DiaryApp {
	public static void main(String[] args) { 
		LoginModel login = new LoginModel();
		LoginScreen loginView = new LoginScreen();
		RegisterModel register = new RegisterModel();
		RegisterScreen registerView = new RegisterScreen();
		LockPageScreen lockView = new LockPageScreen(loginView, registerView);
		LockPageController lockController = new LockPageController(loginView, login, registerView, register);
		while(true) {
		if(lockController.getLoginStatus()) {
			lockView.dispose();
			break;
		}
		}
		MenuModel menu = new MenuModel();
		MenuScreen menuView = new MenuScreen();
		DiaryContainerModel diaryCardModel = new DiaryContainerModel();
		DiaryContainerScreen diaryCard = new DiaryContainerScreen();
		HomePageScreen homeView = new HomePageScreen(menuView, diaryCard);
		@SuppressWarnings("unused")
		HomePageController homeController = new HomePageController(homeView, menu, menuView, diaryCardModel, diaryCard);
	}
}
