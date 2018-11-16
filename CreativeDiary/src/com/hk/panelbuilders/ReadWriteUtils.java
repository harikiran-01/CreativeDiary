package com.hk.panelbuilders;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Calendar;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.hk.components.CurrentDay;
import com.hk.components.CurrentUser;
import com.hk.components.CustomDate;
import com.hk.components.DateConverter;
import com.hk.components.DiaryPage;
import com.hk.components.FilledIndicator;
import com.hk.components.StarRater;
import com.hk.components.StorageSpace;
import com.toedter.calendar.JDateChooser;

public abstract class ReadWriteUtils {
	public final DiaryPage blankpage = new DiaryPage();
	protected DiaryPage page;
	protected JDateChooser dateChooser;
	protected CustomDate selectedDate;
	protected JTextArea contentField;
	protected JScrollPane contentScroll;
	protected StarRater rating;
	protected final boolean ADD_ENTRY = true, DELETE_ENTRY = false;
	
	public ReadWriteUtils() {
		init();
		//recommit
	}
	
	private void init() {
		//diary page
		page = new DiaryPage();
		
		//date chooser
		dateChooser = new JDateChooser(CurrentDay.getDate());	
		dateChooser.setDateFormatString("dd MM yyyy");
		dateChooser.setSelectableDateRange(DateConverter.convertfromCustom(CurrentUser.getInstance().getDob()), CurrentDay.getDate());
		dateChooser.getJCalendar().getDayChooser().addDateEvaluator(FilledIndicator.evaluator);
		//selected date
		selectedDate = new CustomDate();
		//content field
		contentField = new JTextArea();
		contentField.setWrapStyleWord(true);
		contentField.setLineWrap(true);
		//content scroll pane
		contentScroll = new JScrollPane(contentField);
		contentScroll.setBounds(10,96, 608, 380);
		//star rater
		rating = new StarRater();
	}
	
	public String reviseFileName() {
		return StorageSpace.currentpath+"\\"+
                Integer.toString(selectedDate.getYear())+"\\"
		          +Integer.toString(selectedDate.getMonth())+"\\"+Integer.toString(selectedDate.getDay())+".txt";
	}
	
	public DiaryPage getDiaryPage() throws IOException, ClassNotFoundException {
		DiaryPage temppage;
		if(new File(reviseFileName()).exists()) {
			FileInputStream file = new FileInputStream(reviseFileName());
			ObjectInputStream in = new ObjectInputStream(file);   
			temppage = (DiaryPage)in.readObject();
			in.close();
			file.close();			
		}
		else 
			temppage = new DiaryPage();
		return temppage;
	}
	
	public void HighlightsManager(boolean status, CustomDate date) {
		Calendar c = Calendar.getInstance();
		 c.set(Calendar.YEAR, date.getYear());
		 c.set(Calendar.MONTH, date.getMonth()-1);
		 c.set(Calendar.DAY_OF_MONTH, date.getDay());
		 c.set(Calendar.HOUR_OF_DAY, 0);
	     c.set(Calendar.MINUTE, 0);
	     c.set(Calendar.SECOND, 0);
	     c.set(Calendar.MILLISECOND, 0);
		if(status == ADD_ENTRY) 
		    FilledIndicator.evaluator.add(c.getTime());
		else 
			FilledIndicator.evaluator.remove(c.getTime());			
		dateChooser.repaint();
		dateChooser.revalidate();		
	}
	
	public abstract JPanel getPanel();
}
