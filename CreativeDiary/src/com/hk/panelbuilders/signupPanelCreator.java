package com.hk.panelbuilders;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.hk.components.DateConverter;
import com.hk.components.UserProfile;
import com.hk.ui.LockPage;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;

public class signupPanelCreator {
	private String filename = "userdetails.yo";
	private File f = new File(filename);
	public List<UserProfile> users = new ArrayList<UserProfile>();
	private JPanel signupPanel =  new JPanel();
	private JTextField userName;
	private JPasswordField passwordField;
	private JButton register;
	private JLabel ulabel,plabel,doblabel;
	
	public signupPanelCreator() {
		ulabel = new JLabel("Enter Username");
		ulabel.setBounds(70, 18, 103, 14);
		userName = new JTextField(15);
		userName.setBounds(55, 43, 126, 20);
		doblabel = new JLabel("Select DOB");
		doblabel.setBounds(25, 83, 81, 14);
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(108, 83, 91, 20);
		dateChooser.setMaxSelectableDate(new Date());
		plabel = new JLabel("Enter Passcode");
		plabel.setBounds(70, 127, 100, 14);
		passwordField = new JPasswordField(15);
		passwordField.setBounds(55, 152, 126, 20);
		register = new JButton("REGISTER");	
		register.setBounds(70, 183, 97, 23);
		signupPanel.setLayout(null);
		signupPanel.add(ulabel);
		signupPanel.add(userName);
		signupPanel.add(plabel);
		signupPanel.add(passwordField);
		signupPanel.add(register);	
		signupPanel.add(doblabel);
		signupPanel.add(dateChooser);
		
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showConfirmDialog(LockPage.getFrame(),"Congrats! You are registered",
						"Registration Successful",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,
						new ImageIcon("green_tick.png"));
				getStoredUserData();
				//adding new data
				UserProfile user = new UserProfile(userName.getText(), DateConverter.convertDate(dateChooser), passwordField);
				users.add(user);
				storeUser(users);
			}
		});
	}
	
@SuppressWarnings("unchecked")
public void getStoredUserData() {
		//reading existing data
		f.setReadOnly();
		if(f.length() == 0)
			System.out.println("empty file");
		else {
		try
        {   
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);
            users = (List<UserProfile>) in.readObject();
            in.close();
            file.close();
        }	         
        catch(IOException ex)
        {
            System.out.println("IOException is caughte");
        }
	 	catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }
		}
}
	
public void storeUser(List<UserProfile> users) {
         try {
        	 f.setWritable(true);
        	 FileOutputStream file = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(file);
             if(users==null)
             out.reset();
             
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

	public JPanel returnPanel() {
		return this.signupPanel;
	}
}
