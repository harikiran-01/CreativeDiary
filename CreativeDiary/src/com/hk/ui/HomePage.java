package com.hk.ui;
import javax.swing.*;
import com.hk.panelbuilders.*;

public class HomePage{
	private static JFrame creativeDiary;
	public static WriteDiaryPanelCreator write;
	public static ReadDiaryPanelCreator read;
	private JPanel menuPanel;
	private static JPanel diaryContainerPanel;
		public HomePage() {
				initComponents();
				addComponents();
			}
		
		private void initComponents() {
			//main frame
			creativeDiary = new JFrame("Creative Diary");
			creativeDiary.getContentPane().setLayout(null);
			creativeDiary.setResizable(false);
			creativeDiary.setVisible(true);
			creativeDiary.setSize(800,600);
			creativeDiary.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//write diary panel creator
			write = new WriteDiaryPanelCreator();
			Thread writePanelThread = new Thread(write);
			writePanelThread.start();
			//read diary panel creator
			read = new ReadDiaryPanelCreator();
			Thread readPanelThread = new Thread(read);
			readPanelThread.start();
			//highlighting dates for initial launch
			//menu panel
			menuPanel = new MenuPanelCreator().getPanel();
			//diary container panel
			diaryContainerPanel = new DiaryContainerPanelCreator().getPanel();
		}
		
		private void addComponents() {
			//adding menu panel to frame
			creativeDiary.getContentPane().add(menuPanel);
			//adding diary container panel to frame
			creativeDiary.getContentPane().add(diaryContainerPanel);
		}
		
		public static void replacePanel(JPanel replace) {
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
