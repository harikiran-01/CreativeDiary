package com.hk.ui.panelcreators;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.hk.components.CurrentDay;
import com.hk.components.CurrentUser;
import com.hk.components.CustomDate;
import com.hk.components.DateConverter;
import com.hk.components.StorageSpace;
import com.hk.ui.HomePage;
import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReadDiary{
	JPanel readDiaryPanel;
	ArrayList<String> shortmonths = new ArrayList<String>(Arrays.asList("4", "6", "9", "11"));
	String[] days,months,years;
	JLabel lblEnterDate;
	JTextArea contentField;
	JButton btnSearch, btnEdit;
	CustomDate searchDate = null; 
	JDateChooser dateChooser = new JDateChooser(CurrentDay.getDate());
	public ReadDiary() {
		readDiaryPanel = new JPanel();
		readDiaryPanel.setLayout(null);
		//pick date
		lblEnterDate = new JLabel("Pick Date:");
		lblEnterDate.setBounds(82, 25, 87, 14);
		//date chooser
		dateChooser.setBounds(142, 24, 122, 20);
		//content Field
		contentField = new JTextArea("hello, select a date to relive your memory");
		JScrollPane contentscroll = new JScrollPane(contentField);
		contentField.setBounds(10, 90, 430, 184);
		contentField.setEditable(false);
		contentField.setWrapStyleWord(true);
		contentField.setLineWrap(true);
		contentscroll.setBounds(10,96, 508, 334);
		readDiaryPanel.add(lblEnterDate);
		readDiaryPanel.add(contentscroll);
		readDiaryPanel.add(dateChooser);
		btnSearch = new JButton("SEARCH");
		btnEdit = new JButton("EDIT");
		btnEdit.setVisible(false);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searchDate = DateConverter.convertDate(dateChooser);
				String content = "";
				try {
						content = getContentFromFile(searchDate);	
					if(content.equals("Wow! Such Empty"))
						btnEdit.setVisible(false);
					else
						btnEdit.setVisible(true);
					contentField.setText(content.trim());
					contentField.setCaretPosition(0);
			}
			catch(IOException ex) {
				System.out.println(ex);}
			}
		});
		btnSearch.setBounds(297, 21, 95, 23);
		readDiaryPanel.add(btnSearch);
		//edit button
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HomePage.replacePanel(HomePage.write.getPanel());
				HomePage.write.updateEditFields(searchDate, contentField.getText().trim());
			}
		});
		btnEdit.setBounds(403, 21, 64, 23);
		readDiaryPanel.add(btnEdit);
	
	}
	
	public void fillValues(String[] arr,int begin,int end) {
		int k=0;
		for(int i=begin;i<end;i++) {
			arr[k] = Integer.toString(i+1);
			k++;}
	}
	
	public JPanel getPanel() {
		return readDiaryPanel;
	}
	
	public String getContentFromFile(CustomDate date) throws IOException {
		String filename = StorageSpace.currentpath+CurrentUser.getInstance().getUserName()+
				"\\"+date.getYear()+"\\"+date.getMonth()+"\\"+date.getDay()+".txt";
		if(new File(filename).exists())
		{
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			StringBuilder stringBuilder = new StringBuilder();
			String line = null;
			String ls = System.getProperty("line.separator");
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}
			// deleting the last new line separator
			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
			reader.close();
			return stringBuilder.toString().trim();
		}
		else
			return "Wow! Such Empty";
	}
	
	public void updateFields(CustomDate findDate) {
		btnEdit.setVisible(true);
		searchDate = findDate;
		dateChooser.setDate(DateConverter.convertfromCustom(findDate));
		try {
			contentField.setText(getContentFromFile(findDate));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
