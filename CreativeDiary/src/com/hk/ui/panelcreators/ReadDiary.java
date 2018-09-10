package com.hk.ui.panelcreators;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.hk.components.CurrentDay;
import com.hk.components.CurrentUser;
import com.hk.components.CustomDate;
import com.hk.components.StorageSpace;
import com.hk.ui.HomePage;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ItemEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReadDiary{
	JPanel readDiaryPanel;
	ArrayList<String> shortmonths = new ArrayList<String>(Arrays.asList("4", "6", "9", "11"));
	String[] days,months,years;
	public ReadDiary() {
		readDiaryPanel = new JPanel();
		readDiaryPanel.setLayout(null);
		days = new String[31];
		months = new String[12];
		years = new String[Calendar.getInstance().get(Calendar.YEAR) - CurrentUser.getInstance().getDob().getYear() + 1];
		fillValues(days, 0,days.length);
		fillValues(months, 0,months.length);
		fillValues(years, CurrentUser.getInstance().getDob().getYear()-1, Calendar.getInstance().get(Calendar.YEAR));
		
		JLabel lblEnterDate = new JLabel("Enter Date:");
		lblEnterDate.setBounds(35, 25, 87, 14);
		readDiaryPanel.add(lblEnterDate);
		//day selector
		JComboBox<String> daySelector = new JComboBox<String>();
		daySelector.setModel(new DefaultComboBoxModel<String>(days));
		daySelector.setBounds(101, 22, 43, 20);
		readDiaryPanel.add(daySelector);
		//month selector
		JComboBox<String> monthSelector = new JComboBox<String>();
		monthSelector.setModel(new DefaultComboBoxModel<String>(months));
		monthSelector.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				daySelector.setModel(new DefaultComboBoxModel<String>(days));
				if(shortmonths.contains(monthSelector.getSelectedItem())) {
					daySelector.removeItem("31");
				}
				else if(monthSelector.getSelectedItem().equals("2"))
				{
					daySelector.removeItem("29");
					daySelector.removeItem("30");
					daySelector.removeItem("31");
				}
			}
		});
		monthSelector.setBounds(154, 22, 43, 20);
		readDiaryPanel.add(monthSelector);
		//year selector
		JComboBox<String> yearSelector = new JComboBox<String>();
		yearSelector.setModel(new DefaultComboBoxModel<String>(years));
		yearSelector.setBounds(207, 22, 67, 20);
		readDiaryPanel.add(yearSelector);
		//setting current date
		yearSelector.setSelectedItem(Integer.toString(CurrentDay.getYear()));
		monthSelector.setSelectedItem(Integer.toString(CurrentDay.getMonth()));
		daySelector.setSelectedItem(Integer.toString(CurrentDay.getDay()));
		//content Field
		JTextArea contentField = new JTextArea("hello, select a date to relive your memory");
		JScrollPane contentscroll = new JScrollPane(contentField);
		contentField.setBounds(10, 90, 430, 184);
		contentField.setEditable(false);
		contentField.setWrapStyleWord(true);
		contentField.setLineWrap(true);
		contentscroll.setBounds(10,96, 508, 334);
		readDiaryPanel.add(contentscroll);
		
		JButton btnSet = new JButton("SEARCH");
		JButton btnEdit = new JButton("EDIT");
		btnEdit.setVisible(false);
		CustomDate searchdate = new CustomDate(0,0,0);
		btnSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String content="";
				String filename = StorageSpace.currentpath+CurrentUser.getInstance().getUserName()+"\\"+
	                      yearSelector.getSelectedItem().toString()+"\\"
				          +monthSelector.getSelectedItem().toString()+"\\"+daySelector.getSelectedItem().toString()+".txt";
				try {
					if(new File(filename).exists())
					{
						searchdate.setDay(Integer.parseInt(daySelector.getSelectedItem().toString()));
						searchdate.setMonth(Integer.parseInt(monthSelector.getSelectedItem().toString()));
						searchdate.setYear(Integer.parseInt(yearSelector.getSelectedItem().toString()));
						content = getContentFromFile(searchdate.getDay(), searchdate.getMonth(), searchdate.getYear());
						btnEdit.setVisible(true);
					}
				else {
					btnEdit.setVisible(false);
					content = "Content not found";
				}
					contentField.setText(content.trim());
					contentField.setCaretPosition(0);
			}
			catch(IOException ex) {
				System.out.println(ex);}
			}
		});
		btnSet.setBounds(297, 21, 89, 23);
		readDiaryPanel.add(btnSet);
		
		//edit button
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HomePage.replacePanel(HomePage.write.getPanel());
				HomePage.write.updateEditFields(searchdate, contentField.getText().trim());
			}
		});
		btnEdit.setBounds(297, 44, 89, 23);
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
	
	public static String getContentFromFile(int day,int month,int year) throws IOException {
		String filename = StorageSpace.currentpath+CurrentUser.getInstance().getUserName()+"\\"+
                Integer.toString(year)+"\\"
		          +Integer.toString(month)+"\\"+Integer.toString(day)+".txt";
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
			return null;
	}
}
