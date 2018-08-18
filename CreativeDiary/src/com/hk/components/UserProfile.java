package com.hk.components;

import java.io.Serializable;

import javax.swing.JPasswordField;

public class UserProfile implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1501970447052842312L;
	private String name;
	CustomDate dob;
	private JPasswordField passwordField;
	public UserProfile() {
		
	}
	public UserProfile(String name, CustomDate dob, JPasswordField passwordField){
		this.name = name;
		this.dob = dob;
		this.passwordField = passwordField;
	}
	public String getUserName() {
		return name;
	}
	public CustomDate getDob(){
		return dob;
	}
	public JPasswordField getPasswordField() {
		return passwordField;
	}
}
