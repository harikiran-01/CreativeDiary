package com.hk.Views;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class HomePageScreen extends JFrame{
	private JPanel menuPanel;
	private JPanel diaryPanel;
	public HomePageScreen(JPanel menuPanel, JPanel diaryPanel) {
		this.menuPanel = menuPanel;
		this.diaryPanel = diaryPanel;
		initComponents();
		addComponents();
	}
	
	private void initComponents() {
		//main frame
		setTitle("Creative Diary");
		setLayout(null);
		setResizable(false);
		setVisible(true);
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}
	
	private void addComponents() {
		add(menuPanel);
		add(diaryPanel);
	}
	
	public void emptyPageError() {
		Object[] option = {"I get it","My Bad!"};
		JOptionPane.showOptionDialog(this,"Uh Oh! Can't save an empty page",
				"",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,option,option[0]);
	}

}
