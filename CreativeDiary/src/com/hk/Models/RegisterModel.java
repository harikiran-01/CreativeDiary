package com.hk.Models;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import com.hk.components.UserProfile;

public class RegisterModel extends AccessControlsModel{
	public RegisterModel() {
		
	}
	
	public void storeUser(List<UserProfile> users) {
        try {
       	 getUserDataFile().setWritable(true);
       	 FileOutputStream foStream = new FileOutputStream(getUserDataFile());
            ObjectOutputStream out = new ObjectOutputStream(foStream);
            if(users==null)
            out.reset();
            //writing user login details
			 out.writeObject(users);
			 
			 //closing streams
			 out.close();
	         foStream.close();
		} 
       catch (IOException e) {
			System.out.println(e);
		}
       getUserDataFile().setReadOnly();
	}
}
