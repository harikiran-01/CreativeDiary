package com.hk.panelbuilders;
import javax.swing.*;
import com.hk.components.*;
import com.hk.ui.HomePage;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteDiaryPanelCreator{
	private UserProfile currentuser = CurrentUser.getInstance();
	private String content="";
	private JPanel writeDiaryPanel;
	private JLabel greetMessage,dayInfo,lblPickDate;
	private JTextArea contentfield;
	private JScrollPane contentScroll;
	private JDateChooser dateChooser;
	private CustomDate selectedDate;
	private JButton save,setDate;
	
	public WriteDiaryPanelCreator(){
		initComponents();
		addComponents();
		
		//content field focus
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
		
	//set button action	
	setDate.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			if(dateBoundary()) {
				contentfield.setEnabled(true);
				boolean samepage = new SimpleDateFormat("dd/MM/yyyy").format(dateChooser.getDate()).equals(new SimpleDateFormat("dd/MM/yyyy").format(DateConverter.convertfromCustom(selectedDate)));
				CustomDate lastDate = selectedDate;
				selectedDate = DateConverter.convertDate(dateChooser);
				if(!samepage) {
				if(isAlreadyWritten())
				{
					try {
						int option = readOrEditDialog();
						if(option==0) {
							HomePage.replacePanel(HomePage.read.getPanel());
							HomePage.read.updateFields(selectedDate);
						}
						else if(option==1)
						updateEditFields(selectedDate, HomePage.read.getContentFromFile(selectedDate));
						else {
							selectedDate = lastDate;
							dateChooser.setDate(DateConverter.convertfromCustom(selectedDate));
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else{
					contentfield.setText("Start writing here");
					dayInfo.setText("You are making entry for: "+ new SimpleDateFormat("dd/MM/yyyy").format(DateConverter.convertfromCustom(selectedDate)));
				}
				}
			}
			else {
				dateChooser.setDate(DateConverter.convertfromCustom(selectedDate));
			}
		}
	});
	
	//save button action
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

	private void initComponents() {
		writeDiaryPanel = new JPanel();
		writeDiaryPanel.setLayout(null);
		//welcome user
		greetMessage = new JLabel("Welcome "+currentuser.getUserName().toUpperCase());
		greetMessage.setFont(new Font("Script MT Bold", Font.PLAIN, 20));
		greetMessage.setBounds(22, 9, 179, 21);
		//pick date label
		lblPickDate = new JLabel("Pick Date:");
		lblPickDate.setFont(new Font("Viner Hand ITC", Font.PLAIN, 16));
		lblPickDate.setBounds(252, 9, 81, 25);
		//date chooser
		dateChooser = new JDateChooser(CurrentDay.getDate());	
		dateChooser.setDateFormatString("dd MM yyyy");
		dateChooser.setBounds(343, 9, 91, 20);
		//selected date
		selectedDate = new CustomDate(0, 0, 0);
		//day info
		dayInfo = new JLabel("Click SET to select the date");
		dayInfo.setBounds(22, 61, 286, 14);
		//content field
		contentfield = new JTextArea("Start writing here");
		contentfield.setWrapStyleWord(true);
		contentfield.setLineWrap(true);
		contentfield.setEnabled(false);
		//content scroll pane
		contentScroll = new JScrollPane(contentfield);
		contentScroll.setBounds(10,96, 508, 334);
		//set date button
		setDate = new JButton("SET");
		setDate.setBounds(444, 9, 74, 23);
		//save button
		save = new JButton("SAVE");
		save.setBounds(221, 441, 88, 23);
	}
	
	private void addComponents() {
		writeDiaryPanel.add(lblPickDate);
		writeDiaryPanel.add(contentScroll);
		writeDiaryPanel.add(save);
		writeDiaryPanel.add(dateChooser);
		writeDiaryPanel.add(greetMessage);
		writeDiaryPanel.add(dayInfo);	
		writeDiaryPanel.add(setDate);
	}
	
	public String reviseFileName() {
		return StorageSpace.currentpath+currentuser.getUserName()+"\\"+
                Integer.toString(selectedDate.getYear())+"\\"
		          +Integer.toString(selectedDate.getMonth())+"\\"+Integer.toString(selectedDate.getDay())+".txt";
	}
	
	private void EncryptFile() throws IOException {
		File f = new File(reviseFileName());
		f.getParentFile().mkdirs();
		FileOutputStream outputStream = new FileOutputStream(reviseFileName());
	    outputStream.write(content.getBytes());
	    outputStream.close();
	}
	
	public boolean dateBoundary() {
		Object[] option = {"I get it","My Bad"};
		if(new SimpleDateFormat("dd/MM/yyyy").format(dateChooser.getDate()).equals(new SimpleDateFormat("dd/MM/yyyy").format(CurrentDay.getDate()))) {
			return true;
		}
		else if(new SimpleDateFormat("dd/MM/yyyy").format(dateChooser.getDate()).equals(new SimpleDateFormat("dd/MM/yyyy").format(DateConverter.convertfromCustom(currentuser.getDob())))) {
			return true;
		}
		else if(dateChooser.getDate().after(CurrentDay.getDate())) {
			JOptionPane.showOptionDialog(HomePage.getFrame(),"We strongly believe you can't know your future",
					"",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,option,option[0]);
			return false;
		}	
		else if(dateChooser.getDate().before(DateConverter.convertfromCustom(currentuser.getDob()))){
			JOptionPane.showOptionDialog(HomePage.getFrame(),"Well we don't see those dates in your life",
					"",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE,null,option,option[0]);
			return false;
		}
		return true;
	}
	
	public boolean isAlreadyWritten() {
		File f = new File(reviseFileName());
		return f.length()!=0;
	}
	
	public int readOrEditDialog() {
		Object[] option = {"Read","Edit"};
		return JOptionPane.showOptionDialog(HomePage.getFrame(),"You already updated diary for this day. Do you want to edit?",
				"",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,option,option[0]);
	}
	
	public void updateEditFields(CustomDate searchDate, String excontent) {
		contentfield.setEnabled(true);
		dateChooser.setDate(DateConverter.convertfromCustom(searchDate));
		selectedDate = searchDate;
		dayInfo.setText("You are editing entry for: "+ new SimpleDateFormat("dd/MM/yyyy").format(DateConverter.convertfromCustom(searchDate)));
		contentfield.setText(excontent);
	}
	
	
	public JPanel getPanel() {
		return writeDiaryPanel;
		}
}
