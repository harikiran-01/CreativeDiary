package com.hk.components;

import java.awt.Color;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.toedter.calendar.IDateEvaluator;
import com.toedter.calendar.JDateChooser;

public class FilledIndicator implements Runnable{
	private JDateChooser dateChooser;
	
	public FilledIndicator(JDateChooser dateChooser) {
		this.dateChooser = dateChooser;
	}

	@Override
	public void run() {
		HighlightEvaluator evaluator = new HighlightEvaluator();
	     for(Date filled : getFilledDates())  {  
	    	 evaluator.add(filled);
	     }
		 dateChooser.getJCalendar().getDayChooser().addDateEvaluator(evaluator);
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
