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
	private UserProfile user = CurrentUser.getInstance();
	private Date currentDate = new Date();
	private CustomDate selectedDate = null;
	private String content;
	private JPanel writeDiaryPanel;
	JLabel dayinfo = new JLabel("");
	JDateChooser dateChooser = new JDateChooser();
	PropertyChangeListener lis = new PropertyChangeListener(){
	      @Override
	      public void propertyChange(PropertyChangeEvent e) {
	    	    if(dateBoundary(dateChooser))	{
				    dayinfo.setText("You are making entry for: "+ new SimpleDateFormat("dd/MM/yyyy").format(dateChooser.getDate()));
				    selectedDate = DateConverter.convertDate(dateChooser);
				}
				else {
					updateDateChooser();
				}
	      }      
	    };
	public WriteDiary(){
	writeDiaryPanel = new JPanel();
	selectedDate = DateConverter.convertDate(currentDate);
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
	JTextArea contentfield = new JTextArea("Start writing here");
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
	
	private void EncryptFile() throws IOException {
		String filename = StorageSpace.currentpath+CurrentUser.getInstance().getUserName()+"\\"+
	                      Integer.toString(selectedDate.getYear())+"\\"
				          +Integer.toString(selectedDate.getMonth())+"\\"+Integer.toString(selectedDate.getDay())+".txt";
		File f = new File(filename);
		f.getParentFile().mkdirs();
		FileOutputStream outputStream = new FileOutputStream(filename);
	    outputStream.write(content.getBytes());
	    outputStream.close();
	}
	
	public boolean dateBoundary(JDateChooser dateChooser) {
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
	
	public void updateDateChooser() {
	    dateChooser.removePropertyChangeListener(lis);
		dateChooser.setDate(DateConverter.convertfromCustom(selectedDate));
		dateChooser.addPropertyChangeListener(lis);
		}

	public JPanel returnPanel() {
	return this.writeDiaryPanel;
	}
	
}
