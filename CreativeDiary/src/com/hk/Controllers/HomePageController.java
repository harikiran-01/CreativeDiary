package com.hk.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.hk.Models.DiaryContainerModel;
import com.hk.Models.HomePageModel;
import com.hk.Models.MenuModel;
import com.hk.Views.DiaryContainerScreen;
import com.hk.Views.HomePageScreen;
import com.hk.Views.MenuScreen;

public class HomePageController {
	MenuModel menu;
	MenuScreen menuView;
	DiaryContainerModel diaryContainer;
	DiaryContainerScreen diaryContainerView;
	Write
	
	public HomePageController(MenuModel menu, MenuScreen menuView, DiaryContainerModel diaryContainer, DiaryContainerScreen diaryContainerView) {
		this.menu = menu;
		this.menuView = menuView;
		this.diaryContainer = diaryContainer;
		this.diaryContainerView = diaryContainerView;
		
		menuView.addReadButtonListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
	}
	
}
