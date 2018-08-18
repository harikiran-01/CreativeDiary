package com.hk.components;

public class CurrentUser{
	private static UserProfile currentuser;
	private CurrentUser() {
		
	}
	public static UserProfile getInstance() {
		if(currentuser==null) {
			currentuser = new UserProfile();
		}
		return currentuser;
	}
	public static void init(UserProfile user) {
		currentuser = user;
	}
}
