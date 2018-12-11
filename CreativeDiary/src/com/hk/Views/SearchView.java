package com.hk.Views;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JSeparator;
import javax.swing.JScrollBar;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

public class SearchView extends JPanel{
	private JTextField searchKey; 
	private JButton searchButton;
	
	public SearchView() {
		initComponents();
		addComponents();
	}
	
	private void initComponents() {
		setBounds(166, 0, 628, 575);
		//setPreferredSize(new Dimension(462, 575));
		//search key field
        searchKey = new JTextField(20);
        searchKey.setBounds(121, 9, 166, 20);
		//search button
        searchButton = new JButton("SEARCH");
        searchButton.setBounds(297, 8, 94, 23);
        
	}
	private void addComponents() {
		add(searchKey);
		add(searchButton);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 42, 628, 2);
		add(separator);
		setLayout(null);
		
		
		
		
		JPanel panel = new JPanel();
	
		panel.setLayout(new FlowLayout(20,10,5));
		panel.setPreferredSize(new Dimension(492, 500));
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel_1.setPreferredSize(new Dimension(490, 50));
		panel.add(panel_1);
		
		for(int i=1;i<20;i++) {
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(255, 200, 0), 3));
		panel_2.setPreferredSize(new Dimension(490, 50));
		panel.add(panel_2);
		panel.setPreferredSize(new Dimension(492, 60 + i*60));
		}
		
		JScrollPane scrollPane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(20, 69, 544, 450);
		setPreferredSize(new Dimension(492, 450));
		
	    
		
		add(scrollPane);
	}
	
	

	public void performSearchListener(ActionListener search) {
		searchButton.addActionListener(search);
	}
}
