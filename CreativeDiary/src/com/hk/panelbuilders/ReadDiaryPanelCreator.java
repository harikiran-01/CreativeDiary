package com.hk.panelbuilders;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import com.hk.ui.HomePage;
import com.toedter.calendar.JDateChooser;
import com.hk.components.*;
import javax.swing.JLabel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReadDiaryPanelCreator{
	private JPanel readDiaryPanel;
	private JLabel lblEnterDate;
	private JTextArea contentField;
	private JScrollPane contentScroll;
	private JButton btnSearch, btnEdit;
	private CustomDate searchDate; 
	private JDateChooser dateChooser;
	
	public ReadDiaryPanelCreator() {
		initComponents();
		addComponents();
		
		//search button action
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
		
		//edit button action
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HomePage.replacePanel(HomePage.write.getPanel());
				HomePage.write.updateEditFields(searchDate, contentField.getText().trim());
			}
		});	
	}

	private void initComponents() {
		//read diary panel
		readDiaryPanel = new JPanel();
		readDiaryPanel.setLayout(null);
		//pick date label
		lblEnterDate = new JLabel("Pick Date:");
		lblEnterDate.setBounds(82, 25, 87, 14);
		//date chooser
		dateChooser = new JDateChooser(CurrentDay.getDate());
		dateChooser.setSelectableDateRange(DateConverter.convertfromCustom(CurrentUser.getInstance().getDob()), CurrentDay.getDate());
		dateChooser.setBounds(142, 24, 122, 20);
		//content Field
		contentField = new JTextArea("hello, select a date to relive your memory");
		contentField.setBounds(10, 90, 430, 184);
		contentField.setEditable(false);
		contentField.setWrapStyleWord(true);
		contentField.setLineWrap(true);
		//content scroll pane
		contentScroll = new JScrollPane(contentField);
		contentScroll.setBounds(10,96, 508, 334);
		//search button
		btnSearch = new JButton("SEARCH");
		btnSearch.setBounds(297, 21, 95, 23);
		//edit button
		btnEdit = new JButton("EDIT");
		btnEdit.setVisible(false);
		btnEdit.setBounds(403, 21, 64, 23);
	}
	
	
	private void addComponents() {
		readDiaryPanel.add(lblEnterDate);
		readDiaryPanel.add(dateChooser);
		readDiaryPanel.add(contentScroll);
		readDiaryPanel.add(btnSearch);
		readDiaryPanel.add(btnEdit);
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
	
	public JPanel getPanel() {
		return readDiaryPanel;
	}
}
