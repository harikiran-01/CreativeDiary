package com.hk.Views;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.hk.components.CurrentDay;
import com.hk.components.CurrentUser;
import com.hk.components.DateConverter;
import com.hk.components.FilledIndicator;
import com.toedter.calendar.JDateChooser;

import java.awt.Panel;

public class ReadWriteView extends JPanel{
	public ReadWriteView() {
		initComponents();
		addComponents();
	}
	private JLabel greetMessage,dayInfo,lblPickDate;
	private JDateChooser dateChooser;
	private JButton next,btnSet; 
	private JButton btnNext;
	
	
	private void initComponents() {
		setLayout(null);
		setBounds(166, 0, 628, 575);
		
		greetMessage = new JLabel("Welcome "+CurrentUser.getInstance().getUserName().toUpperCase());
		greetMessage.setBounds(10, 15, 184, 26);
		greetMessage.setFont(new Font("Script MT Bold", Font.PLAIN, 20));
		
		dateChooser = new JDateChooser(CurrentDay.getDate());	
		dateChooser.setBounds(455, 21, 91, 20);
		dateChooser.setDateFormatString("dd MM yyyy");
		dateChooser.setSelectableDateRange(DateConverter.convertfromCustom(CurrentUser.getInstance().getDob()), CurrentDay.getDate());
		dateChooser.getJCalendar().getDayChooser().addDateEvaluator(new FilledIndicator().evaluator);
		
		btnSet = new JButton("SET");
		btnSet.setBounds(556, 21, 51, 23);
		
	}
	
	private void addComponents() {
		
		add(greetMessage);
		add(dateChooser);		
		add(btnSet);
		
		btnNext = new JButton("NEXT");
		btnNext.setBounds(268, 541, 89, 23);
		add(btnNext);
	}
}

