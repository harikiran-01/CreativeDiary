package com.hk.ui;
import javax.swing.*;
import com.hk.panelbuilders.*;

public class HomePage{
	private JFrame creativeDiary;
	private WriteDiaryPanelCreator write;
	public ReadDiaryPanelCreator read;
	public SearchDiaryPanelCreator search;
	
	private JPanel menuPanel;
	private JPanel diaryContainerPanel;
		public HomePage() {
				System.out.println("creating homepage");
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
			//search diary panel creator
			search = new SearchDiaryPanelCreator();
			//menu panel
			menuPanel = new MenuPanelCreator().getPanel();
			//diary container panel
			diaryContainerPanel = new DiaryContainerPanelCreator().getPanel();
		}
		
		private void addComponents() {
			//adding menu panel to frame
			creativeDiary.add(menuPanel);
			creativeDiary.repaint();
			creativeDiary.revalidate();
			//adding diary container panel to frame
			creativeDiary.getContentPane().add(diaryContainerPanel);
		}
		
		public void replacePanel(JPanel replace) {
			diaryContainerPanel.removeAll();
			diaryContainerPanel.repaint();
			diaryContainerPanel.revalidate();
			
			diaryContainerPanel.add(replace);
			diaryContainerPanel.repaint();
			diaryContainerPanel.revalidate();
		}
		public JFrame getFrame() {
			return creativeDiary;
		}
		
		public void disposeScreen() {
			creativeDiary.dispose();
		}
		
		public WriteDiaryPanelCreator getWriteDiaryPage() {
			return write;
		}
}
