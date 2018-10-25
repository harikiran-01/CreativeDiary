package com.hk.panelbuilders;

import java.util.Calendar;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.hk.components.CurrentDay;
import com.hk.components.CurrentUser;
import com.hk.components.CustomDate;
import com.hk.components.DateConverter;
import com.hk.components.FilledIndicator;
import com.toedter.calendar.JDateChooser;

public abstract class ReadWriteUtils {
	protected JDateChooser dateChooser;
	protected JTextArea contentField;
	protected JScrollPane contentScroll;
	protected final boolean ADD_ENTRY = true, DELETE_ENTRY = false;
	
	public ReadWriteUtils() {
		init();
		
	}
	
	private void init() {
		//date chooser
		dateChooser = new JDateChooser(CurrentDay.getDate());	
		dateChooser.setDateFormatString("dd MM yyyy");
		dateChooser.setSelectableDateRange(DateConverter.convertfromCustom(CurrentUser.getInstance().getDob()), CurrentDay.getDate());
		dateChooser.getJCalendar().getDayChooser().addDateEvaluator(FilledIndicator.evaluator);
		//content field
		contentField = new JTextArea();
		contentField.setWrapStyleWord(true);
		contentField.setLineWrap(true);
		//content scroll pane
		contentScroll = new JScrollPane(contentField);
		contentScroll.setBounds(10,96, 608, 380);
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
		if(status == ADD_ENTRY) 
		    FilledIndicator.evaluator.add(c.getTime());
		else 
			FilledIndicator.evaluator.remove(c.getTime());			
		dateChooser.repaint();
		dateChooser.revalidate();		
	}
}
