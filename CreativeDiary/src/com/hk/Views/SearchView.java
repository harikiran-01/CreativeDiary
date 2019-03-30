package com.hk.Views;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;

import com.hk.components.DiaryPage;

import java.awt.Color;
import java.awt.Dimension;

public class SearchView extends JPanel{
	
	JScrollPane scrollPane;
	private JTextField searchKey; 
	private JButton searchButton;
	JPanel resultPanel;
	JPanel resultComponent;
	JSeparator separator;
	
	public SearchView() {
		initComponents();
		addComponents();
	}
	
	private void initComponents() {
		setBounds(166, 0, 628, 575);
		setLayout(new FlowLayout());
		//setPreferredSize(new Dimension(462, 575));
		
		//search key field
        searchKey = new JTextField(20);
        searchKey.setBounds(121, 9, 166, 20);
		//search button
        searchButton = new JButton("SEARCH");
        searchButton.setBounds(297, 8, 94, 23);
        //line seperator
        separator = new JSeparator();
		separator.setBounds(0, 42, 628, 2);
		//
		resultPanel = new JPanel();
		resultPanel.setLayout(new FlowLayout(20,10,5));
		//scroll view
		scrollPane = new JScrollPane(resultPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(20, 75, 400, 400);
		scrollPane.setPreferredSize(new Dimension(492, 400));	
	}
	
	
	private void addComponents() {
		add(searchKey);
		add(searchButton);
		add(separator);
		add(scrollPane);
	}
	
	private JPanel resultComponentFactory(DiaryPage page) {
		System.out.println("sml frame "+page.getDate());
		resultComponent = new JPanel();
		resultComponent.setLayout(new BoxLayout(resultComponent, BoxLayout.Y_AXIS));
		resultComponent.setBorder(new LineBorder(Color.green));
		resultComponent.setPreferredSize(new Dimension(450, 50));
		JLabel dateStamp = new JLabel(""+page.getDate());
		JLabel contentGist = new JLabel(page.getContent().substring(0, (page.getContent().length()<60)?page.getContent().length():60)+"...");
		resultComponent.add(dateStamp);
		resultComponent.add(contentGist);
		return resultComponent;
	}
	
	public void gatherResults(List<DiaryPage> page) {
		resultPanel.setPreferredSize(new Dimension(492, page.size() * 55));
		for(int i=0; i< page.size(); i++)
		resultPanel.add(resultComponentFactory(page.get(i)));
		
		
		repaint();
		revalidate();
	}
	
	public void clearResults() {
		resultPanel.removeAll();
	}

	public String getKeyword() {
		return searchKey.getText();
	}

	public void setSearchKey(String key) {
		searchKey.setText(key);
	}

	public void performSearchListener(ActionListener search) {
		searchButton.addActionListener(search);
	}
}
