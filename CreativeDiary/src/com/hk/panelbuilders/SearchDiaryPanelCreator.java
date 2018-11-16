package com.hk.panelbuilders;

import java.awt.GridBagLayout;
import java.io.IOException;
import java.util.Date;

import javax.swing.*;

import com.hk.components.DateConverter;
import com.hk.components.FilledIndicator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchDiaryPanelCreator extends ReadWriteUtils{
	private JPanel searchPanel;
	private JTextField searchKey; 
	private JButton searchButton;

	public SearchDiaryPanelCreator() {
		initComponents();
		addComponents();
		//search button action
		searchButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String key = "";
        		key = searchKey.getText();
        		if(key!="" || key!=null)
					try {
						searchFilebyDate(key);
					} catch (ClassNotFoundException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        	}
        });
	}
	
	private void addComponents() {
		searchPanel.setLayout(new GridBagLayout());
		searchPanel.add(searchKey);
		searchPanel.add(searchButton);
	}

	private void initComponents() {
		//search panel
		searchPanel = new JPanel();
		searchPanel.setBounds(166, 0, 628, 575);
		//search key field
        searchKey = new JTextField(20);
		//search button
        searchButton = new JButton("SEARCH");
        
	}
	public void searchFilebyDate(String key) throws ClassNotFoundException, IOException {
			
			for(Date avldat : FilledIndicator.evaluator.getDates()) {
				selectedDate = DateConverter.convertDate(avldat);
				page = getDiaryPage();
				if(page.getContent().contains(key))
					System.out.println(selectedDate);					
			}
	}

	@Override
	public JPanel getPanel() {
		return searchPanel;
	}
	
	
}
