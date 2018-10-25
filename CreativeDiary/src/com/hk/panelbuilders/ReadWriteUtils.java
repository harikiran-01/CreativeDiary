package com.hk.panelbuilders;

import com.hk.components.CurrentDay;
import com.hk.components.CurrentUser;
import com.hk.components.DateConverter;
import com.hk.components.FilledIndicator;
import com.toedter.calendar.JDateChooser;

public abstract class ReadWriteUtils {
	protected JDateChooser dateChooser;
	
	public ReadWriteUtils() {
		dateChooser = new JDateChooser(CurrentDay.getDate());	
		dateChooser.setDateFormatString("dd MM yyyy");
		dateChooser.setSelectableDateRange(DateConverter.convertfromCustom(CurrentUser.getInstance().getDob()), CurrentDay.getDate());
		dateChooser.getJCalendar().getDayChooser().addDateEvaluator(FilledIndicator.evaluator);
	}
}
