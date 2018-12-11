package com.hk.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import com.hk.Models.DiaryContainerModel;
import com.hk.Models.MenuModel;
import com.hk.Models.ReadWriteModel;
import com.hk.Views.DiaryContainerScreen;
import com.hk.Views.HomePageScreen;
import com.hk.Views.MenuScreen;
import com.hk.Views.ReadWriteView;
import com.hk.Views.SearchView;

public class HomePageController {
	private DiaryContainerScreen diaryCard;
	private ReadWriteView readwriteScreen;
	private ReadWriteModel readWrite;
	private SearchView searchView;
	private ReadWriteController rw;

	public HomePageController(HomePageScreen homeView, MenuModel menu, MenuScreen menuView, DiaryContainerModel diaryCardModel, DiaryContainerScreen diaryCard) {
		this.diaryCard = diaryCard;
		readwriteScreen = new ReadWriteView();
		readWrite = new ReadWriteModel();
		searchView = new SearchView();
		rw = new ReadWriteController(readWrite, readwriteScreen);
		
		menuView.addEntryButtonListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				fetchCard(readwriteScreen);
			}
		});
		
		menuView.addSearchButtonListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fetchCard(searchView);
			}
		});
		
		searchView.performSearchListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				
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
