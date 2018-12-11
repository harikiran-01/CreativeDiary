package com.hk.Models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.List;

import com.hk.components.CustomDate;
import com.hk.components.DateConverter;
import com.hk.components.DiaryPage;
import com.hk.components.QA;
import com.hk.components.StorageSpace;

public class ReadWriteModel {
	public final DiaryPage blankpage = new DiaryPage();
	private DiaryPage page;
	private CustomDate selectedDate, lastDate = new CustomDate();
	public static final boolean ADD_ENTRY = true, DELETE_ENTRY = false;
	
	public ReadWriteModel() {
		//selected date
		selectedDate = new CustomDate();
	}
	
	public String reviseFileName() {
		return StorageSpace.currentpath+"\\"+
                Integer.toString(selectedDate.getYear())+"\\"
		          +Integer.toString(selectedDate.getMonth())+"\\"+Integer.toString(selectedDate.getDay())+".txt";
	}
	
	public boolean entryExists() {
		return new File(reviseFileName()).exists();
	}
	
	public void fetchDiaryPage() {	
		FileInputStream file = null;
		ObjectInputStream in = null;
		if(entryExists()) {		
			try {
				file = new FileInputStream(reviseFileName());
				in = new ObjectInputStream(file); 
				page = (DiaryPage)in.readObject();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				try {
					in.close();
					file.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else {
			page = new DiaryPage(selectedDate);
		}
			
	}
	
	public void EncryptFile() throws IOException {
		File f = new File(reviseFileName());
		f.getParentFile().mkdirs();
		FileOutputStream outputStream = new FileOutputStream(reviseFileName());
        ObjectOutputStream out = new ObjectOutputStream(outputStream);
	    out.writeObject(page);
	    outputStream.close();
	}

	public DiaryPage getPage() {
		return page;
	}

	public void fillPage(String content, int rating, String dayInfoMsg, List<QA> qa) {
		page.setContent(content);
		page.setRating(rating);
		page.setDayInfo(dayInfoMsg);
		page.setQAData(qa);
	}

	public CustomDate getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(Date date) {
		this.selectedDate = DateConverter.convertDate(date);
	}
	
	public void setLastDate(CustomDate date) {
		this.lastDate = date;
	}
	
	public CustomDate getLastDate() {
		return lastDate;
	}
	
	public boolean isContentEmpty(){
		return page.getContent().equals("Start writing here");
	}
	
	public boolean isSamePage() {
		return selectedDate.equals(lastDate);
	}
}
