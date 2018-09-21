package com.hk.panelbuilders;
import javax.swing.*;
import com.hk.components.*;
import com.hk.ui.LockPage;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;
import java.awt.event.ActionEvent;

public class signupPanelCreator extends AccessControls{
	private static JPanel signupPanel = new JPanel();
	private JButton register;
	private JLabel doblabel;
	JDateChooser dateChooser;
	
	private void initComponents() {
		//signup panel
		signupPanel.setLayout(null);
		//username label
		ulabel.setBounds(70, 18, 103, 14);
		//username field
		userName.setBounds(55, 43, 126, 20);
		//password label
		plabel.setBounds(70, 127, 100, 14);
		//password field
		passwordField.setBounds(55, 152, 126, 20);
		//dob label
		doblabel = new JLabel("Select DOB");
		doblabel.setBounds(25, 83, 81, 14);
		//date chooser
		dateChooser = new JDateChooser();
		dateChooser.setBounds(108, 83, 91, 20);
		dateChooser.setMaxSelectableDate(CurrentDay.getDate());
		//register button
		register = new JButton("REGISTER");	
		register.setBounds(70, 183, 97, 23);
		}

	private void addComponents() {
		signupPanel.add(doblabel);
		signupPanel.add(dateChooser);
		signupPanel.add(register);	
	}
	
	public signupPanelCreator() {
		super(signupPanel);
		initComponents();
		addComponents();
		
		//register button action
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showConfirmDialog(LockPage.getFrame(),"Congrats! You are registered",
						"Registration Successful",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,
						new ImageIcon("green_tick.png"));
				getStoredUserData();
				//adding new data
				UserProfile user = new UserProfile(userName.getText(), DateConverter.convertDate(dateChooser), passwordField);
				//creating folder for user
				File fd = new File("users\\" + user.getUserName());
				fd.mkdirs();
				//adding new user to user list
				users.add(user);
				//writing user list back to file
				storeUser(users);
			}
		});
	}
	
public void storeUser(List<UserProfile> users) {
         try {
        	 f.setWritable(true);
        	 FileOutputStream file = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(file);
             if(users==null)
             out.reset();
             //writing user login details
			 out.writeObject(users);
			 
			 //closing streams
			 out.close();
	         file.close();
		} 
        catch (IOException e) {
			System.out.println(e);
		}
        f.setReadOnly();
	}

	public JPanel getPanel() {
		return signupPanel;
	}
}
