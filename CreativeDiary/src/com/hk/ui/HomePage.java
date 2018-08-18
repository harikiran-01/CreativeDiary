package com.hk.ui;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.FlowLayout;
import javax.swing.border.BevelBorder;
import com.hk.components.CurrentUser;
import com.hk.ui.panelcreators.*;
import java.awt.Color;
import java.awt.Font;

public class HomePage{
	private static JFrame creativeDiary = new JFrame("Creative Diary");
	JPanel menupanel,diaryContainerPanel,writeDiaryPanel,readDiaryPanel;
		public HomePage() {
				creativeDiary.getContentPane().setLayout(null);
				//menu panel
				menupanel = new JPanel();
				menupanel.setBounds(0, 0, 167, 475);
				menupanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 50));
				//write diary button
				JButton btnWriteDiary = new JButton("Write Diary");
				
				btnWriteDiary.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						replacePanel(writeDiaryPanel);
					}
				});
				menupanel.add(btnWriteDiary);		
				//read diary button
				JButton btnReadDiary = new JButton("Read Diary");
				btnReadDiary.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						replacePanel(readDiaryPanel);
					}
				});
				menupanel.add(btnReadDiary);
				//exit button
				JButton btnExit = new JButton("Exit");
				btnExit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int choice = JOptionPane.showConfirmDialog(creativeDiary, "Any unsaved information will be lost. Are you sure?",
								"Exit",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
						if(choice==0)
						creativeDiary.dispose();
					}	
				});
				menupanel.add(btnExit);
				//adding menu panel to frame
				creativeDiary.getContentPane().add(menupanel);
				
				//card layout of second screen
				diaryContainerPanel = new JPanel();
				diaryContainerPanel.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
				diaryContainerPanel.setBounds(166, 0, 528, 475);
				diaryContainerPanel.setLayout(new CardLayout(0, 0));
				
				//welcome panel
				JPanel welcomepanel = new JPanel();
				welcomepanel.setLayout(null);	
				JLabel greetLabel = new JLabel("Welcome "+CurrentUser.getInstance().getUserName()+"! Hope you are having a great day!");
				greetLabel.setFont(new Font("MV Boli", Font.BOLD, 16));
				greetLabel.setBounds(42, 189, 444, 16);
				welcomepanel.add(greetLabel);
				diaryContainerPanel.add(welcomepanel, "welcomepanel");
				
				//write diary panel
				writeDiaryPanel = new JPanel();
				writeDiaryPanel = new WriteDiary().returnPanel();
				//read diary panel
				readDiaryPanel = new JPanel();	
				readDiaryPanel = new ReadDiary().returnPanel();
				
				
				creativeDiary.getContentPane().add(diaryContainerPanel);
				creativeDiary.setResizable(false);
				creativeDiary.setVisible(true);
				creativeDiary.setSize(700,500);
				creativeDiary.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		
		public void replacePanel(JPanel replace) {
			diaryContainerPanel.removeAll();
			diaryContainerPanel.repaint();
			diaryContainerPanel.revalidate();
			
			diaryContainerPanel.add(replace);
			diaryContainerPanel.repaint();
			diaryContainerPanel.revalidate();
		}
		public static JFrame getFrame() {
			return creativeDiary;
		}
}
