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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteDiary{
	private boolean samepage = true;
	private UserProfile user = CurrentUser.getInstance();
	private Date currentDate = new Date();
	public CustomDate selectedDate = DateConverter.convertDate(currentDate);
	private String content="";
	private JPanel writeDiaryPanel = null;
	private JLabel dayinfo = new JLabel("");
	private JTextArea contentfield = new JTextArea("Start writing here");
	private JDateChooser dateChooser = new JDateChooser(currentDate);	
	public WriteDiary(){
	writeDiaryPanel = new JPanel();
	//welcome user
	JLabel lblWelcomeUser = new JLabel("Welcome "+user.getUserName().toUpperCase());
	lblWelcomeUser.setFont(new Font("Script MT Bold", Font.PLAIN, 20));
	lblWelcomeUser.setBounds(22, 9, 179, 21);
	//pick date label
	JLabel lblPickDate = new JLabel("Pick Date:");
	lblPickDate.setFont(new Font("Viner Hand ITC", Font.PLAIN, 16));
	lblPickDate.setBounds(252, 9, 81, 25);
	writeDiaryPanel.add(lblPickDate);
	//date chooser
	dateChooser.setDateFormatString("dd MM yyyy");
	dateChooser.setBounds(343, 9, 91, 20);
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
	//set button
	JButton setdate = new JButton("SET");
	setdate.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(dateBoundary()) {
				if(new SimpleDateFormat("dd/MM/yyyy").format(dateChooser.getDate()).equals(new SimpleDateFormat("dd/MM/yyyy").format(DateConverter.convertfromCustom(selectedDate))))
					samepage= true;
				else
					samepage= false;
				selectedDate = DateConverter.convertDate(dateChooser);
				if(!samepage && isAlreadyWritten())
				{
					try {
						updateEditFields(selectedDate, ReadDiary.getContentFromFile(selectedDate.getDay(), selectedDate.getMonth(), selectedDate.getYear()));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(!samepage){
					contentfield.setText("Start writing here");
					dayinfo.setText("You are making entry for: "+ new SimpleDateFormat("dd/MM/yyyy").format(DateConverter.convertfromCustom(selectedDate)));
				}
			}
			else {
				dateChooser.setDate(DateConverter.convertfromCustom(selectedDate));
			}
		}
	});
	setdate.setBounds(444, 9, 74, 23);
	//save button
	JButton save = new JButton("SAVE");
	save.setBounds(221, 441, 88, 23);
	
	writeDiaryPanel.setLayout(null);
	writeDiaryPanel.setSize(528, 475);
	writeDiaryPanel.add(contentscroll);
	writeDiaryPanel.add(save);
	writeDiaryPanel.add(dateChooser);
	writeDiaryPanel.add(lblWelcomeUser);
	writeDiaryPanel.add(dayinfo);	
	writeDiaryPanel.add(setdate);
	
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
		File f = new File(reviseFileName());
		f.getParentFile().mkdirs();
		FileOutputStream outputStream = new FileOutputStream(reviseFileName());
	    outputStream.write(content.getBytes());
	    outputStream.close();
	}
	
	public String reviseFileName() {
		return StorageSpace.currentpath+CurrentUser.getInstance().getUserName()+"\\"+
                Integer.toString(selectedDate.getYear())+"\\"
		          +Integer.toString(selectedDate.getMonth())+"\\"+Integer.toString(selectedDate.getDay())+".txt";
	}
	
	public boolean dateBoundary() {
		Object[] option = {"I get it","My Bad"};
		if(new SimpleDateFormat("dd/MM/yyyy").format(dateChooser.getDate()).equals(new SimpleDateFormat("dd/MM/yyyy").format(currentDate))) {
			return true;
		}
		else if(new SimpleDateFormat("dd/MM/yyyy").format(dateChooser.getDate()).equals(new SimpleDateFormat("dd/MM/yyyy").format(DateConverter.convertfromCustom(CurrentUser.getInstance().getDob())))) {
			return true;
		}
		else if(dateChooser.getDate().after(currentDate)) {
			JOptionPane.showOptionDialog(HomePage.getFrame(),"We strongly believe you can't know your future",
					"",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,option,option[0]);
			return false;
		}	
		else if(dateChooser.getDate().before(DateConverter.convertfromCustom(CurrentUser.getInstance().getDob()))){
			JOptionPane.showOptionDialog(HomePage.getFrame(),"Well we don't see those dates in your life",
					"",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,option,option[0]);
			return false;
		}
		return true;
	}

	public JPanel getPanel() {
	return writeDiaryPanel;
	}
	
	public void updateEditFields(CustomDate searchDate, String excontent) {
		dateChooser.setDate(DateConverter.convertfromCustom(searchDate));
		dayinfo.setText("You are editing entry for: "+ new SimpleDateFormat("dd/MM/yyyy").format(DateConverter.convertfromCustom(searchDate)));
		contentfield.setText(excontent);
	}
	
	public boolean isAlreadyWritten() {
		File f = new File(reviseFileName());
		if(f.length()!=0)
		{
			Object[] option = {"Read","Edit"};
			JOptionPane.showOptionDialog(HomePage.getFrame(),"You already updated diary for this day. Do you want to edit?",
					"",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,option,option[0]);
			return true;
		}
		else
			return false;
	}
}
