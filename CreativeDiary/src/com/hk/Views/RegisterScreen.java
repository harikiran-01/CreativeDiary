package com.hk.Views;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import com.hk.components.CurrentDay;
import com.hk.components.CustomDate;
import com.hk.components.DateConverter;
import com.toedter.calendar.JDateChooser;

public class RegisterScreen extends AccessUtilsView{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JDateChooser dateChooser;
	private JLabel doblabel;
	private JButton btnRegister;
	public RegisterScreen() {
		initComponents();
		addComponents();
	}
	
	private void initComponents() {
		//username label
		ulabel.setBounds(70, 18, 103, 14);
		//username field
		userNameField.setBounds(55, 43, 126, 20);
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
		btnRegister = new JButton("REGISTER");	
		btnRegister.setBounds(70, 183, 97, 23);
		}

	private void addComponents() {
		add(doblabel);
		add(dateChooser);
		add(btnRegister);	
	}
	
	public CustomDate getDOB() {
		return DateConverter.convertDate(dateChooser);
	}
	
	public void addRegisterListener(ActionListener register) {
		btnRegister.addActionListener(register);
	}
	
	public void registerSuccessAlert() {
		JOptionPane.showConfirmDialog(getParent(),"Congrats! You are registered",
				"Registration Successful",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,
				new ImageIcon("green_tick.png"));
	}
}
