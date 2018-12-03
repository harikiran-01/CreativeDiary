package com.hk.Views;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuScreen extends JPanel{
	private JButton btnWriteDiary, btnReadDiary, btnSearch, btnExit;
	public MenuScreen() {
		initComponents();
		addComponents();
	}
	private void initComponents() {
		//menu panel
		setBounds(0, 2, 167, 575);
		setLayout(new FlowLayout(FlowLayout.CENTER, 20, 80));
		//write button
		btnWriteDiary = new JButton("Write Diary");
		//read button
		btnReadDiary = new JButton("Read Diary");
		//search button
		btnSearch = new JButton("Search");
		//exit button
		btnExit = new JButton("Exit");
	}
	
	private void addComponents() {
		add(btnWriteDiary);
		add(btnReadDiary);
		add(btnSearch);
		add(btnExit);
	}
	
	public void addWriteButtonListener(ActionListener write) {
		btnReadDiary.addActionListener(write);
	}
	
	public void addReadButtonListener(ActionListener read) {
		btnReadDiary.addActionListener(read);
	}
	
	public void addSearchButtonListener(ActionListener search) {
		btnReadDiary.addActionListener(search);
	}
}
