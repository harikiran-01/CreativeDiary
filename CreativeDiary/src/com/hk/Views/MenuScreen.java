package com.hk.Views;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuScreen extends JPanel{
	private JButton btnEntry, btnSearch, btnExit;
	public MenuScreen() {
		initComponents();
		addComponents();
	}
	private void initComponents() {
		//menu panel
		setBounds(0, 2, 167, 575);
		setLayout(new FlowLayout(FlowLayout.CENTER, 20, 80));
		//write button
		btnEntry = new JButton("My Diary");
		//search button
		btnSearch = new JButton("Search");
		
		//exit button
		btnExit = new JButton("Exit");
	}
	
	private void addComponents() {
		add(btnEntry);
		add(btnSearch);
		add(btnExit);
	}
	
	public void addEntryButtonListener(ActionListener write) {
		btnEntry.addActionListener(write);
	}
	
	public void addSearchButtonListener(ActionListener search) {
		btnSearch.addActionListener(search);
	}
}
