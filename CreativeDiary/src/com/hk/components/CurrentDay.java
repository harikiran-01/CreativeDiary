package com.hk.components;

import java.util.Calendar;
import java.util.Date;

public class CurrentDay{
	private static Calendar c = Calendar.getInstance();
	public static int getDay() {
		return c.get(Calendar.DAY_OF_MONTH);
	}
	public static int getMonth() {
		return (c.get(Calendar.MONTH)+1);
	}
	public static int getYear() {
		return c.get(Calendar.YEAR);
	}
	public static Date getDate() {
		return new Date();
	}
}
