package com.hk.panelbuilders;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import com.hk.ui.HomePage;
import com.toedter.calendar.JDateChooser;
import com.hk.components.*;
import javax.swing.JLabel;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ReadDiaryPanelCreator{
	private JPanel readDiaryPanel;
	private JLabel lblEnterDate;
	private JTextArea contentField;
	private JScrollPane contentScroll;
	private JButton btnSearch, btnEdit; 
	private JDateChooser dateChooser;
	private StarRater rating;
	private DiaryPage page;
	private JLabel lblRating;
	
	private void initComponents() {
		//read diary panel
		readDiaryPanel = new JPanel();
		readDiaryPanel.setLayout(null);
		readDiaryPanel.setBounds(166, 0, 628, 575);
		//pick date label
		lblEnterDate = new JLabel("Pick Date:");
		lblEnterDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEnterDate.setBounds(114, 27, 87, 14);
		//date chooser
		dateChooser = new JDateChooser(CurrentDay.getDate());
		dateChooser.setSelectableDateRange(DateConverter.convertfromCustom(CurrentUser.getInstance().getDob()), CurrentDay.getDate());
		dateChooser.setBounds(189, 23, 122, 20);
		//content Field
		contentField = new JTextArea("hello, select a date to relive your memory");
		contentField.setBounds(10, 90, 430, 184);
		contentField.setEditable(false);
		contentField.setWrapStyleWord(true);
		contentField.setLineWrap(true);
		//content scroll pane
		contentScroll = new JScrollPane(contentField);
		contentScroll.setBounds(10,96, 608, 380);
		//search button
		btnSearch = new JButton("SEARCH");
		btnSearch.setBounds(321, 22, 95, 23);
		//edit button
		btnEdit = new JButton("EDIT");
		btnEdit.setVisible(false);
		btnEdit.setBounds(426, 22, 64, 23);
		//diary page
		page = new DiaryPage(new CustomDate(0, 0, 0), "", 0);
		//rating label
		lblRating = new JLabel("Rating: ");
		lblRating.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblRating.setBounds(22, 65, 81, 14);
		//star rating
		rating = new StarRater();
		rating.setEnabled(false);
		rating.setBounds(82, 62, 88, 23);
	}
	
	
	private void addComponents() {
		readDiaryPanel.add(lblEnterDate);
		readDiaryPanel.add(dateChooser);
		readDiaryPanel.add(contentScroll);
		readDiaryPanel.add(btnSearch);
		readDiaryPanel.add(btnEdit);
		readDiaryPanel.add(rating);
		
		
		readDiaryPanel.add(lblRating);
	}
	
	public ReadDiaryPanelCreator() {
		initComponents();
		addComponents();
		displayHighlights();
		//search button action
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					page = getDiaryPage(DateConverter.convertDate(dateChooser));	
					if(page.getContent().equals("")) {
						page.setContent("Wow! Such Empty");
						btnEdit.setVisible(false);
					}
					else
						btnEdit.setVisible(true);
					contentField.setText(page.getContent().trim());
					contentField.setCaretPosition(0);
					rating.setSelection(page.getRating());
	
			}
			catch(IOException | ClassNotFoundException e) {
				System.out.println(e);}
			}
		});
		
		//edit button action
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HomePage.replacePanel(HomePage.write.getPanel());
				HomePage.write.updateEditFields(page);
			}
		});	
	}
	
	public DiaryPage getDiaryPage(CustomDate date) throws IOException, ClassNotFoundException {
		DiaryPage page;
		String filename = StorageSpace.currentpath+"\\"+date.getYear()+"\\"+date.getMonth()+"\\"+date.getDay()+".txt";
		if(!new File(filename).exists()) {
			page = new DiaryPage();
		}
		else {
			FileInputStream file = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(file);   
			page = (DiaryPage)in.readObject();
			in.close();
			file.close();
		}
		return page;
	}
	
	public void updateFields(DiaryPage newpage) throws ClassNotFoundException {
		displayHighlights();
		btnEdit.setVisible(true);
		page = newpage;
		dateChooser.setDate(DateConverter.convertfromCustom(page.getDate()));
			contentField.setText(page.getContent());
			rating.setSelection(page.getRating());
	}
	
	public JPanel getPanel() {
		return readDiaryPanel;
	}
	
	
	
	public void displayHighlights() {
		 Thread t = new Thread(new FilledIndicator(dateChooser));
		 t.start();
	}
}
