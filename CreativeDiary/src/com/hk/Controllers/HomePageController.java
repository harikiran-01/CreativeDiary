package com.hk.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.hk.Models.DiaryContainerModel;
import com.hk.Models.HomePageModel;
import com.hk.Models.MenuModel;
import com.hk.Views.DiaryContainerScreen;
import com.hk.Views.HomePageScreen;
import com.hk.Views.MenuScreen;
import com.hk.Views.ReadWriteView;

public class HomePageController {
	MenuModel menu;
	MenuScreen menuView;
	DiaryContainerModel diaryCardModel;
	DiaryContainerScreen diaryCard;
	ReadWriteView readwriteScreen;
	
	public HomePageController(MenuModel menu, MenuScreen menuView, DiaryContainerModel diaryCardModel, DiaryContainerScreen diaryCard, ReadWriteView readwriteScreen) {
		this.menu = menu;
		this.menuView = menuView;
		this.diaryCardModel = diaryCardModel;
		this.diaryCard = diaryCard;
		this.readwriteScreen = readwriteScreen;
		
		menuView.addReadButtonListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				fetchCard(readwriteScreen);
			}
		});
		
	}
	
	public void fetchCard(JPanel card) {
		diaryCard.removeAll();
		diaryCard.repaint();
		diaryCard.revalidate();
		
		diaryCard.add(card);
		diaryCard.repaint();
		diaryCard.revalidate();
	}
	
}
