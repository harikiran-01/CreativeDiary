package com.hk.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import com.hk.Models.QAModel;
import com.hk.Models.ReadWriteModel;
import com.hk.Views.QAScreen;
import com.hk.Views.ReadWriteView;
import com.hk.components.InsightQuestions;

public class ReadWriteController {
	ReadWriteModel readWrite;
	ReadWriteView readWriteScreen;
	QAModel qaModel;
	QAScreen qaView;
	
	public ReadWriteController(ReadWriteModel readWrite, ReadWriteView readWriteScreen) {
		this.readWrite = readWrite;
		this.readWriteScreen = readWriteScreen;
		
		qaModel = new QAModel();
		qaView = new QAScreen();
		
		
		readWriteScreen.addSetButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				readWrite.setSelectedDate(readWriteScreen.getCalendarDate());
				if(!readWrite.isSamePage()) {
				try {
					readWrite.fetchDiaryPage();
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				qaModel.setQaList(readWrite.getPage().getQAData());
				if(qaModel.getQaList()==null)
					qaModel.setQaList(new InsightQuestions().getGeneratedQuestions());
				for(int i=0; i<QAModel.totalq; i++)
					qaView.fillQA(qaModel.getQaList());
				
				readWriteScreen.setComponentsEnabled(true);
				readWriteScreen.fillScreen(readWrite.getPage());
				readWrite.setLastDate(readWrite.getPage().getDate());				
					if(readWrite.getPage().equals(readWrite.blankpage))
					readWriteScreen.setDeleteEnabled(false);
					else 
						readWriteScreen.setDeleteEnabled(true);	
					
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
					readWriteScreen.successEntry();
			}
		});
		
	}
	
	
}
