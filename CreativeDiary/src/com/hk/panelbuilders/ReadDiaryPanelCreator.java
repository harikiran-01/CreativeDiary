package com.hk.panelbuilders;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import com.hk.ui.HomePage;
import com.toedter.calendar.JDateChooser;
import com.hk.components.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ReadDiaryPanelCreator{
	private JPanel readDiaryPanel;
	private JLabel lblEnterDate;
	private JTextArea contentField;
	private JScrollPane contentScroll;
	private JButton btnSearch, btnEdit, insightButton, btnDelete; 
	public static JDateChooser dateChooser;
	private StarRater rating;
	private DiaryPage page;
	private JLabel lblRating;
	public final boolean ADD_ENTRY = true, DELETE_ENTRY = false;
	
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
		//insights
		insightButton = new JButton("VIEW INSIGHTS");
		insightButton.setBounds(188, 62, 122, 23);
		//delete entry
		btnDelete = new JButton("DELETE");
		btnDelete.setBounds(321, 62, 95, 23);
	}
	
	
	private void addComponents() {
		readDiaryPanel.add(lblEnterDate);
		readDiaryPanel.add(dateChooser);
		readDiaryPanel.add(contentScroll);
		readDiaryPanel.add(btnSearch);
		readDiaryPanel.add(btnEdit);
		readDiaryPanel.add(rating);
		readDiaryPanel.add(lblRating);
		readDiaryPanel.add(insightButton);
		readDiaryPanel.add(btnDelete);
		toggleComponents(false);
	}
	
	public ReadDiaryPanelCreator() {
		initComponents();
		addComponents();
		//search button action
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					page = getDiaryPage(DateConverter.convertDate(dateChooser));	
					if(page.getContent().equals("")) {
						page.setContent("Wow! Such Empty");
						toggleComponents(false);
					}
					else 
						toggleComponents(true);
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
		
		//insight button action
		insightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				QADialogCreator qa = new QADialogCreator(page.getQAData(), QADialogCreator.READ_MODE);	
				qa.showDialog();
			}
		});
		
		//delete button action
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(new File(reviseFileName(page.getDate())).delete()) {
					HighlightsEditor(DELETE_ENTRY, page.getDate());								
					JOptionPane.showConfirmDialog(HomePage.getFrame(),"Entry Deleted!",
							"Delete Entry",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,
							new ImageIcon("green_tick.png"));
					page = new DiaryPage();	
				}
				else
					JOptionPane.showConfirmDialog(HomePage.getFrame(),"Delete Failed!",
							"Delete Entry",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
				
			}
		});
	}
	
	public String reviseFileName(CustomDate date) {
		return StorageSpace.currentpath+"\\"+
                Integer.toString(date.getYear())+"\\"
		          +Integer.toString(date.getMonth())+"\\"+Integer.toString(date.getDay())+".txt";
	}
	
	public DiaryPage getDiaryPage(CustomDate date) throws IOException, ClassNotFoundException {
		DiaryPage page;
		if(!new File(reviseFileName(date)).exists()) {
			page = new DiaryPage();
		}
		else {
			FileInputStream file = new FileInputStream(reviseFileName(date));
			ObjectInputStream in = new ObjectInputStream(file);   
			page = (DiaryPage)in.readObject();
			in.close();
			file.close();
		}
		return page;
	}
	
	public void updateFields(DiaryPage newpage) throws ClassNotFoundException {
		page = newpage;
		toggleComponents(true);		
		dateChooser.setDate(DateConverter.convertfromCustom(page.getDate()));
			contentField.setText(page.getContent());
			rating.setSelection(page.getRating());
	}
	
	public void toggleComponents(boolean switcher) {
		btnEdit.setEnabled(switcher);
		insightButton.setEnabled(switcher);
		btnDelete.setEnabled(switcher);
	}
	
	public JPanel getPanel() {
		return readDiaryPanel;
	}
	
	public void HighlightsEditor(boolean status, CustomDate date) {
		Calendar c = Calendar.getInstance();
		 c.set(Calendar.YEAR, date.getYear());
		 c.set(Calendar.MONTH, date.getMonth()-1);
		 c.set(Calendar.DAY_OF_MONTH, date.getDay());
		 c.set(Calendar.HOUR_OF_DAY, 0);
	     c.set(Calendar.MINUTE, 0);
	     c.set(Calendar.SECOND, 0);
	     c.set(Calendar.MILLISECOND, 0);
		if(status == ADD_ENTRY) {
		    FilledIndicator.evaluator.add(c.getTime());
			}
		else {
			System.out.println("passed date"+date);
			System.out.println("delete detected for"+c.getTime());
			FilledIndicator.evaluator.remove(c.getTime());
			// DeletedIndicator.evaluator1.add(c.getTime());
			
			    }
		dateChooser.repaint();
		dateChooser.revalidate();
		
	}
}
