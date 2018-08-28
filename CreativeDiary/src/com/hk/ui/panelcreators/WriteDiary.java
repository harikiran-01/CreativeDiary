package com.hk.ui.panelcreators;
import javax.swing.*;
import com.hk.components.*;
import com.hk.ui.HomePage;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteDiary{
	private static int changecount=0;
	private UserProfile user = CurrentUser.getInstance();
	private static Date currentDate = new Date();
	public static CustomDate selectedDate = DateConverter.convertDate(currentDate);
	private static String filename = StorageSpace.currentpath+CurrentUser.getInstance().getUserName()+"\\"+
            Integer.toString(selectedDate.getYear())+"\\"
	          +Integer.toString(selectedDate.getMonth())+"\\"+Integer.toString(selectedDate.getDay())+".txt";
	private static String content="";
	private static JPanel writeDiaryPanel = null;
	static JLabel dayinfo = new JLabel("");
	static JTextArea contentfield = new JTextArea("");
	public static JDateChooser dateChooser = new JDateChooser();
	
	private static PropertyChangeListener lis = new PropertyChangeListener(){
	      @Override
	      public void propertyChange(PropertyChangeEvent e) {
	    	    if(dateBoundary(dateChooser))	{
	    	    	changecount++;
	    	    	selectedDate = DateConverter.convertDate(dateChooser);
	    	    	filename = StorageSpace.currentpath+CurrentUser.getInstance().getUserName()+"\\"+
	    	                Integer.toString(selectedDate.getYear())+"\\"
	    	    	          +Integer.toString(selectedDate.getMonth())+"\\"+Integer.toString(selectedDate.getDay())+".txt";
	    	    	if(isAlreadyWritten())
	    	    	{
	    	    	try {
						contentfield.setText(ReadDiary.getContentFromFile(selectedDate.getDay(), selectedDate.getMonth(), selectedDate.getYear()));
						if(changecount%2==0)
							readOrEditDialog();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
	    	    	}
	    	    	else
	    	    	{
		    	    	System.out.print("fired\t");
	    	    	contentfield.setText("Start writing here");
				    dayinfo.setText("You are making entry for: "+ new SimpleDateFormat("dd/MM/yyyy").format(dateChooser.getDate()));
				    
	    	    	}
				}
				else {
					updateDateChooser(selectedDate);
				}
	      }      
	    };
	public WriteDiary(){
	writeDiaryPanel = new JPanel();
	//welcome user
	JLabel lblWelcomeUser = new JLabel("Welcome "+user.getUserName().toUpperCase());
	lblWelcomeUser.setFont(new Font("Script MT Bold", Font.PLAIN, 20));
	lblWelcomeUser.setBounds(22, 9, 124, 21);
	//pick date label
	JLabel lblPickDate = new JLabel("Pick Date:");
	lblPickDate.setFont(new Font("Viner Hand ITC", Font.PLAIN, 16));
	lblPickDate.setBounds(336, 9, 81, 25);
	writeDiaryPanel.add(lblPickDate);
	//date picker
	dateChooser.setDateFormatString("dd MM yyyy");
	dateChooser.setDate(currentDate);
	dateChooser.setBounds(427, 9, 91, 20);
	//day info
	dayinfo.setBounds(22, 61, 286, 14);
	dayinfo.setText("You are making entry for: "+ new SimpleDateFormat("dd/MM/yyyy").format(currentDate));
	//content field
	JScrollPane contentscroll = new JScrollPane(contentfield);
	contentfield.addFocusListener(new FocusAdapter() {
		@Override
		public void focusGained(FocusEvent arg0) {
			if(contentfield.getText().equals("Start writing here"))
				contentfield.setText("");
		}
		@Override
		public void focusLost(FocusEvent arg0) {
			if(contentfield.getText().equals(""))
				contentfield.setText("Start writing here");
		}
	});
	contentfield.setWrapStyleWord(true);
	contentfield.setLineWrap(true);
	contentscroll.setBounds(10,96, 508, 334);
	//save button
	JButton save = new JButton("SAVE");
	save.setBounds(221, 441, 88, 23);
	//date update
	dateChooser.addPropertyChangeListener(lis);
	writeDiaryPanel.setLayout(null);
	writeDiaryPanel.setSize(528, 475);
	writeDiaryPanel.add(contentscroll);
	writeDiaryPanel.add(save);
	writeDiaryPanel.add(dateChooser);
	writeDiaryPanel.add(lblWelcomeUser);
	writeDiaryPanel.add(dayinfo);	
	save.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			content = contentfield.getText();
			if(content.equals("") || content.equals("Start writing here"))
			{
				Object[] option = {"I get it","My Bad!"};
				JOptionPane.showOptionDialog(HomePage.getFrame(),"Uh Oh! Can't save an empty page",
						"",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,option,option[0]);
			}
			else {				
			try {
				EncryptFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			JOptionPane.showConfirmDialog(HomePage.getFrame(),"Diary Updated! If you want to make changes, edit and save again!",
					"Saved",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,
					new ImageIcon("green_tick.png"));
			}
		}
	});

	}
	
	protected static boolean isAlreadyWritten() {
		File f = new File(filename);
		System.out.println(filename);
		if(f.length()!=0)
		{
			System.out.println(" file exists ");
			return true;
		}
		else{
			System.out.println(" file does not ");
			return false;
		}
	}

	private void EncryptFile() throws IOException {
		filename = StorageSpace.currentpath+CurrentUser.getInstance().getUserName()+"\\"+
	                      Integer.toString(selectedDate.getYear())+"\\"
				          +Integer.toString(selectedDate.getMonth())+"\\"+Integer.toString(selectedDate.getDay())+".txt";
		File f = new File(filename);
		f.getParentFile().mkdirs();
		FileOutputStream outputStream = new FileOutputStream(filename);
	    outputStream.write(content.getBytes());
	    outputStream.close();
	}
	
	public static boolean dateBoundary(JDateChooser dateChooser) {
		Object[] option = {"I get it","My Bad!"};
		if(new SimpleDateFormat("dd/MM/yyyy").format(dateChooser.getDate()).equals(new SimpleDateFormat("dd/MM/yyyy").format(currentDate))) {
			return true;
		}
		else if(new SimpleDateFormat("dd/MM/yyyy").format(dateChooser.getDate()).equals(new SimpleDateFormat("dd/MM/yyyy").format(DateConverter.convertfromCustom(CurrentUser.getInstance().getDob())))) {
			return true;
		}
		else if(dateChooser.getDate().compareTo(currentDate)>0) {
			JOptionPane.showOptionDialog(HomePage.getFrame(),"We strongly believe you can't know your future",
					"",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,option,option[0]);
			return false;
		}	
		else if(dateChooser.getDate().compareTo(DateConverter.convertfromCustom(CurrentUser.getInstance().getDob()))<0){
			JOptionPane.showOptionDialog(HomePage.getFrame(),"Well we don't see those dates in your life",
					"",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,option,option[0]);
			return false;
		}
		return true;
	}
	
	public static void updateDateChooser(CustomDate date) {
	    dateChooser.removePropertyChangeListener(lis);
		dateChooser.setDate(DateConverter.convertfromCustom(date));
		dateChooser.addPropertyChangeListener(lis);
		}

	public JPanel getPanel() {
	return writeDiaryPanel;
	}
	
	public static void readOrEditDialog() {
		Object[] option = {"Read","Edit"};
		JOptionPane.showOptionDialog(HomePage.getFrame(),"We strongly believe you can't know your future",
				"",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,option,option[0]);
	}
	
	public static void updateEditFields(CustomDate searchDate, String excontent ) {
		System.out.println("entered updatefields");
		System.out.println("search date is"+searchDate.getDay()+"/"+searchDate.getMonth()+"/"+searchDate.getYear());
		updateDateChooser(searchDate);
		selectedDate = DateConverter.convertDate(WriteDiary.dateChooser);
		contentfield.setText(excontent);
	}
	
}
