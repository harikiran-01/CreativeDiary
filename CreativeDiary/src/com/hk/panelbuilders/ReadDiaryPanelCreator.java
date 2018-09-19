package com.hk.panelbuilders;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import com.hk.ui.HomePage;
import com.toedter.calendar.IDateEvaluator;
import com.toedter.calendar.JDateChooser;
import com.hk.components.*;
import javax.swing.JLabel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;

public class ReadDiaryPanelCreator{
	private JPanel readDiaryPanel;
	private JLabel lblEnterDate;
	private JTextArea contentField;
	private JScrollPane contentScroll;
	private JButton btnSearch, btnEdit; 
	private JDateChooser dateChooser;
	private StarRater rating;
	private DiaryPage page;
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
		//diary page
		page = new DiaryPage(new CustomDate(0, 0, 0), "", 0);
		//star rating
		rating = new StarRater();
		rating.setEnabled(false);
		rating.setBounds(10, 62, 88, 23);
	}
	
	
	private void addComponents() {
		readDiaryPanel.add(lblEnterDate);
		readDiaryPanel.add(dateChooser);
		readDiaryPanel.add(contentScroll);
		readDiaryPanel.add(btnSearch);
		readDiaryPanel.add(btnEdit);
		readDiaryPanel.add(rating);
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
	
	private static class HighlightEvaluator implements IDateEvaluator {
	private final List<Date> list = new ArrayList<>();
	public void add(Date date) {
        list.add(date);
    }
	
	@Override
	public Color getInvalidBackroundColor() {
		return null;
	}

	@Override
	public Color getInvalidForegroundColor() {
		return null;
	}

	@Override
	public String getInvalidTooltip() {
		return null;
	}

	@Override
	public Color getSpecialBackroundColor() {
		return Color.GREEN;
	}

	@Override
	public Color getSpecialForegroundColor() {
		return Color.RED;
	}

	@Override
	public String getSpecialTooltip() {
		return "filled";
	}

	@Override
	public boolean isInvalid(Date date) {
		return false;
	}

	@Override
	public boolean isSpecial(Date date) {
		return list.contains(date);
	}
}
	
	public void displayHighlights() {
		 HighlightEvaluator evaluator = new HighlightEvaluator();
	     for(Date filled : getFilledDates())  {  
	    	 evaluator.add(filled);
	     }
		 dateChooser.getJCalendar().getDayChooser().addDateEvaluator(evaluator);
	}
	
	private List<Date> getFilledDates() {
		List<Date> filledDates = new ArrayList<Date>();
		String filename = StorageSpace.currentpath;
		File folder = new File(filename);
		String[] yearfolders = folder.list(new FilenameFilter() {					
			@Override
			public boolean accept(File dir, String name) {
				return new File(dir, name).isDirectory();
			}
		});
		for(int i=0; i<yearfolders.length;i++) {
			folder = new File(filename + "\\" + yearfolders[i]);
			if(folder.exists()) {
				String[] monthfolders = folder.list(new FilenameFilter() {					
					@Override
					public boolean accept(File dir, String name) {
						return new File(dir, name).isDirectory();
					}
				});
				for(int j=0;j<monthfolders.length;j++) {
					folder = new File(filename + "\\" + yearfolders[i] + "\\" + monthfolders[j]);
					if(folder.exists()) {
						File[] listOfFiles = folder.listFiles();
						for (int n = 0; n < listOfFiles.length; n++) {
							 Calendar c = Calendar.getInstance();
							 c.set(Calendar.YEAR, Integer.parseInt(yearfolders[i]));
							 c.set(Calendar.MONTH, Integer.parseInt(monthfolders[j])-1);
							 c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(listOfFiles[n].getName().replaceAll(".txt", "")));
							 c.set(Calendar.HOUR_OF_DAY, 0);
						     c.set(Calendar.MINUTE, 0);
						     c.set(Calendar.SECOND, 0);
						     c.set(Calendar.MILLISECOND, 0);
							 filledDates.add(c.getTime());
						}
					}
				}
			}
		}
		return filledDates;
	}
}
