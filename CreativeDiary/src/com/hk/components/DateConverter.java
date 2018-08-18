package com.hk.components;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.toedter.calendar.JDateChooser;

public class DateConverter {
	public static CustomDate date;
	public static CustomDate convertDate(Date d) {
		String dmy[] = new SimpleDateFormat("dd/MM/yyyy").format(d).split("/");
		return new CustomDate(Integer.parseInt(dmy[0]),Integer.parseInt(dmy[1]),Integer.parseInt(dmy[2]));
	}
	public static CustomDate convertDate(JDateChooser d) {
		String dmy[] = new SimpleDateFormat("dd/MM/yyyy").format(d.getDate()).split("/");
		return new CustomDate(Integer.parseInt(dmy[0]),Integer.parseInt(dmy[1]),Integer.parseInt(dmy[2]));
	}
	public static Date convertfromCustom(CustomDate d) {
		Calendar cal = Calendar.getInstance();
		cal.set(d.getYear(), d.getMonth()-1, d.getDay());
		return cal.getTime();
	}
}
