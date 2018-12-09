package com.hk.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JPanel;

import com.hk.Models.DiaryContainerModel;
import com.hk.Models.MenuModel;
import com.hk.Models.QAModel;
import com.hk.Models.ReadWriteModel;
import com.hk.Views.DiaryContainerScreen;
import com.hk.Views.HomePageScreen;
import com.hk.Views.MenuScreen;
import com.hk.Views.QAScreen;
import com.hk.Views.ReadWriteView;
import com.hk.components.CurrentDay;
import com.hk.components.DateConverter;

public class HomePageController {
	private HomePageScreen homeView;
	private MenuModel menu;
	private MenuScreen menuView;
	private DiaryContainerModel diaryCardModel;
	private DiaryContainerScreen diaryCard;
	private ReadWriteView readwriteScreen;
	private ReadWriteModel readWrite;
	private ReadWriteController rw;

	public HomePageController(HomePageScreen homeView, MenuModel menu, MenuScreen menuView, DiaryContainerModel diaryCardModel, DiaryContainerScreen diaryCard) {
		this.homeView = homeView;
		this.menu = menu;
		this.menuView = menuView;
		this.diaryCardModel = diaryCardModel;
		this.diaryCard = diaryCard;
		readwriteScreen = new ReadWriteView();
		readWrite = new ReadWriteModel();
		rw = new ReadWriteController(readWrite, readwriteScreen);
		
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
