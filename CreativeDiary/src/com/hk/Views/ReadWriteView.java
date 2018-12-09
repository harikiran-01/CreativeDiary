package com.hk.Views;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.hk.Models.ReadWriteModel;
import com.hk.components.CurrentDay;
import com.hk.components.CurrentUser;
import com.hk.components.CustomDate;
import com.hk.components.DateConverter;
import com.hk.components.DiaryPage;
import com.hk.components.FilledIndicator;
import com.hk.components.StarRater;
import com.toedter.calendar.JDateChooser;

import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Calendar;
import java.util.Date;
import java.awt.Color;

public class ReadWriteView extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel greetMessage,dayInfo;
	private JDateChooser dateChooser;
	private JButton btnSet, btnNext , btnDelete;
	private JTextArea contentField;
	protected JScrollPane contentScroll;
	protected StarRater ratingBar;
	
	public ReadWriteView() {
		initComponents();
		addComponents();
		
		contentField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(getContent().equals("Start writing here"))
				setContent("");
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				if(getContent().equals(""))
				setContent("Start writing here");
			}
		});
		
	}
		
	private void initComponents() {
		setLayout(null);
		setBackground(new Color(0, 0, 0));
		setBounds(166, 0, 627, 575);
		//greetings
		greetMessage = new JLabel("Welcome "+CurrentUser.getInstance().getUserName().toUpperCase());
		greetMessage.setForeground(new Color(255, 255, 255));
		greetMessage.setBackground(new Color(0, 0, 0));
		greetMessage.setBounds(10, 15, 184, 26);
		greetMessage.setFont(new Font("Script MT Bold", Font.PLAIN, 20));
		//day info
		dayInfo = new JLabel("Click SET to select the date");
		dayInfo.setForeground(new Color(255, 255, 255));
		dayInfo.setBounds(10, 66, 296, 14);
		//date chooser
		dateChooser = new JDateChooser(CurrentDay.getDate());	
		dateChooser.setBounds(437, 21, 91, 20);
		dateChooser.setDateFormatString("dd MM yyyy");
		dateChooser.setSelectableDateRange(DateConverter.convertfromCustom(CurrentUser.getInstance().getDob()), CurrentDay.getDate());
		dateChooser.getJCalendar().getDayChooser().addDateEvaluator(new FilledIndicator().evaluator);
		//set button
		btnSet = new JButton("SET");
		btnSet.setForeground(new Color(0, 204, 0));
		btnSet.setBackground(new Color(255, 255, 255));
		btnSet.setBounds(538, 21, 69, 23);
		//next button
		btnNext = new JButton("NEXT");
		btnNext.setForeground(new Color(0, 102, 255));
		btnNext.setBackground(new Color(255, 255, 255));
		btnNext.setBounds(260, 528, 89, 23);
		//delete button
		btnDelete = new JButton("DELETE");
		btnDelete.setForeground(new Color(255, 0, 0));
		btnDelete.setBackground(new Color(255, 255, 255));
		btnDelete.setBounds(518, 62, 89, 23);	
		//content field
		contentField = new JTextArea("Start Writing Here");
		contentField.setBackground(new Color(204, 255, 255));
		contentField.setWrapStyleWord(true);
		contentField.setLineWrap(true);
		//content scroll pane
		contentScroll = new JScrollPane(contentField);
		contentScroll.setBounds(10,96, 597, 388);
		//star rater
		ratingBar = new StarRater();
		ratingBar.setBounds(264, 495, 81, 25);
	}
	
	private void addComponents() {	
		add(greetMessage);
		add(dateChooser);
		add(dayInfo);
		add(contentScroll);
		add(btnSet);
		add(btnNext);
		add(btnDelete);
		add(ratingBar);	
		setComponentsEnabled(false);
	}
	
	public boolean isContentEmpty() {
		return getContent().equals("") || getContent().equals("Start writing here");
	}
	
	public void emptyPageError() {
		Object[] option = {"I get it","My Bad!"};
		JOptionPane.showOptionDialog(getRootPane(),"Uh Oh! Can't save an empty page",
				"",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,option,option[0]);
	}
	
	public void successEntry() {
		JOptionPane.showConfirmDialog(getRootPane(),"Diary Updated! If you want to make changes, edit and save again!",
				"Saved",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,
				new ImageIcon("green_tick.png"));
	}
	
	public void fillScreen(DiaryPage page) {
		setContent(page.getContent());		
		setRatingBar(page.getRating());
		setDayInfo(page.getDayInfo());
	}
	
	public void setComponentsEnabled(boolean switcher) {
		contentField.setEnabled(switcher);
		ratingBar.setEnabled(switcher);
		btnNext.setEnabled(switcher);
	}
	
	public void setDeleteEnabled(boolean switcher) {
		btnDelete.setEnabled(switcher);
	}
	public Date getCalendarDate() {
		return dateChooser.getDate();
	}
	
	public String getContent() {
		return contentField.getText();
	}
	
	public void setContent(String content) {
		contentField.setText(content);
	}
	
	public void setDayInfo(String dayInfoMsg) {
		dayInfo.setText(dayInfoMsg);
	}
	
	public void setRatingBar(int rating) {
		ratingBar.setSelection(rating);
	}
	
	public int getRating() {
		return ratingBar.getSelection();
	}
	
	public void addSetButtonListener(ActionListener set) {
		btnSet.addActionListener(set);
	}
	
	public void addNextButtonListener(ActionListener next) {
		btnNext.addActionListener(next);
	}
	
	public void addDeleteButtonListener(ActionListener delete) {
		btnDelete.addActionListener(delete);
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
		if(status == ReadWriteModel.ADD_ENTRY) 
		    FilledIndicator.evaluator.add(c.getTime());
		if(status == ReadWriteModel.DELETE_ENTRY)
			FilledIndicator.evaluator.remove(c.getTime());			
		dateChooser.repaint();
		dateChooser.revalidate();		
	}
}

