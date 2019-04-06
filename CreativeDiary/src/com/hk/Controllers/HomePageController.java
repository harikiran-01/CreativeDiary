package com.hk.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;

import com.hk.Models.DiaryContainerModel;
import com.hk.Models.MenuModel;
import com.hk.Models.ReadWriteModel;
import com.hk.Models.SearchModel;
import com.hk.Views.DiaryContainerScreen;
import com.hk.Views.HomePageScreen;
import com.hk.Views.MenuScreen;
import com.hk.Views.ReadWriteView;
import com.hk.Views.SearchView;
import com.hk.components.DateConverter;
import com.hk.components.DiaryPage;
import com.hk.components.FilledIndicator;

public class HomePageController {
	private DiaryContainerScreen diaryCard;
	private ReadWriteView readwriteScreen;
	private ReadWriteModel readWrite;
	private SearchView searchView;
	private SearchModel search;
	private ReadWriteController rw;
	
	Action action = new AbstractAction()
	{
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {
	        searchPages();
	    }
	};	

	public HomePageController(HomePageScreen homeView, MenuModel menu, MenuScreen menuView, DiaryContainerModel diaryCardModel, DiaryContainerScreen diaryCard) {
		this.diaryCard = diaryCard;
		readwriteScreen = new ReadWriteView();
		readWrite = new ReadWriteModel();
		searchView = new SearchView();
		searchView.addSearchListener(action);
		search = new SearchModel();
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
	}
	
	private void searchPages() {
		searchView.clearResults();
		List<DiaryPage> avlPages = new ArrayList<DiaryPage>();
		List<DiaryPage> resultPages = new ArrayList<DiaryPage>();
		String key = "";
		key = searchView.getKeyword();
		if(key!="" && key!=null) {
			search.setResultCount(FilledIndicator.evaluator.getDates().size());
			for(Date avldat : FilledIndicator.evaluator.getDates()) {
				System.out.println(avldat);
				avlPages.add(readWrite.getPageforDate(DateConverter.convertDate(avldat)));
			}
			for(int i=0; i< search.getResultCount(); i++) {
				if(avlPages.get(i).getContent().contains(key)) {	
					System.out.println("content found in result pages "+ avlPages.get(i).getContent());
					resultPages.add(avlPages.get(i));
				}
			}
			searchView.gatherResults(resultPages);
		}
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
