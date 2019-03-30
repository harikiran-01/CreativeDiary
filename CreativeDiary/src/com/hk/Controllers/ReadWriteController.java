package com.hk.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import com.hk.Models.QAModel;
import com.hk.Models.ReadWriteModel;
import com.hk.Views.QAScreen;
import com.hk.Views.ReadWriteView;
import com.hk.components.CurrentDay;
import com.hk.components.CurrentUser;
import com.hk.components.DateConverter;
import com.hk.components.InsightQuestions;

public class ReadWriteController {
	ReadWriteModel readWrite;
	ReadWriteView readWriteScreen;
	QAModel qaModel;
	QAScreen qaView;
	
	public ReadWriteController(ReadWriteModel readWrite, ReadWriteView readWriteScreen) {
		this.readWrite = readWrite;
		this.readWriteScreen = readWriteScreen;
		
		readWriteScreen.setComponentsEnabled(false);
		
		qaModel = new QAModel();
		qaView = new QAScreen();
		
		
		readWriteScreen.addSetButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				readWriteScreen.setComponentsEnabled(true);
				Date datePicker = (Date) readWriteScreen.getCalendarDate();
				if(datePicker.before(CurrentDay.getDate()) && datePicker.after(DateConverter.convertfromCustom(CurrentUser.getInstance().getDob()))) {
				readWrite.setSelectedDate(datePicker);
				if(!readWrite.isSamePage()) {
				readWrite.fetchDiaryPage();
				qaModel.setQaList(readWrite.getPage().getQAData());
				if(qaModel.getQaList()==null)
					qaModel.setQaList(new InsightQuestions().getGeneratedQuestions());
				for(int i=0; i<QAModel.totalq; i++)
					qaView.fillQA(qaModel.getQaList());
				
				readWriteScreen.setComponentsEnabled(true);
				readWriteScreen.fillScreen(readWrite.getPage());
				readWrite.setLastDate(readWrite.getPage().getDate());				
					if(readWrite.isContentEmpty())
					readWriteScreen.setDeleteEnabled(false);
					else 
					readWriteScreen.setDeleteEnabled(true);	
					
			}
			}
				else
				{
					readWriteScreen.setCalendarDate(DateConverter.convertfromCustom(readWrite.getLastDate()));
				}
			}
		});
		
		readWriteScreen.addNextButtonListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(readWriteScreen.isContentEmpty())
					readWriteScreen.emptyPageError();
				else {
					
					qaView.setDialogVisible();
					
					if(!readWrite.entryExists()) {
						readWriteScreen.HighlightsManager(ReadWriteModel.ADD_ENTRY, readWrite.getSelectedDate());
						}
				}
			}

		});
		
		readWriteScreen.addDeleteButtonListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(new File(readWrite.reviseFileName()).delete()) {
					readWrite.fetchDiaryPage();
					readWriteScreen.fillScreen(readWrite.getPage());
					readWriteScreen.HighlightsManager(ReadWriteModel.DELETE_ENTRY, readWrite.getSelectedDate());								
					readWriteScreen.deletedEntryAlert(true);
					readWriteScreen.setDeleteEnabled(false);
				}
				else
					readWriteScreen.deletedEntryAlert(false);
					
			}
		});
		
		qaView.addSaveButtonListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0; i<QAModel.totalq; i++)
					qaModel.getQaList().get(i).setAnswer(qaView.getSQAScreen().get(i).getAnswerfromField());
				readWrite.fillPage(readWriteScreen.getContent(), readWriteScreen.getRating(), "Here's your memory made on "+readWrite.getSelectedDate(), qaModel.getQaList());
				try {
					readWrite.EncryptFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
					qaView.dispose();
					readWriteScreen.successEntryAlert();
					readWriteScreen.setDeleteEnabled(true);
			}
		});
		
	}
	
	
}
