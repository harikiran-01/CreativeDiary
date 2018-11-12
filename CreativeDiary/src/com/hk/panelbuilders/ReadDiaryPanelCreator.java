package com.hk.panelbuilders;
import javax.swing.JPanel;
import com.hk.ui.HomePage;
import com.hk.components.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ReadDiaryPanelCreator extends ReadWriteUtils implements Runnable{
	private JPanel readDiaryPanel;
	private JLabel lblEnterDate;
	private JButton btnSearch, btnEdit, insightButton, btnDelete; 
	private JLabel lblRating;
	
	private void initComponents() {
		//read diary panel
		readDiaryPanel = new JPanel();
		readDiaryPanel.setLayout(null);
		readDiaryPanel.setBounds(166, 0, 628, 575);
		//pick date label
		lblEnterDate = new JLabel("Pick Date:");
		lblEnterDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEnterDate.setBounds(114, 27, 87, 14);
		//date chooser
		dateChooser.setBounds(189, 23, 122, 20);
		//content Field
		contentField.setText("hello, select a date to relive your memory");
		contentField.setEditable(false);
		//search button
		btnSearch = new JButton("SEARCH");
		btnSearch.setBounds(321, 22, 95, 23);
		//edit button
		btnEdit = new JButton("EDIT");
		btnEdit.setBounds(426, 22, 64, 23);
		//rating label
		lblRating = new JLabel("Rating: ");
		lblRating.setFont(new Font("Stencil", Font.PLAIN, 14));
		lblRating.setBounds(22, 65, 81, 14);
		//star rating
		rating.setEnabled(false);
		rating.setBounds(82, 62, 88, 23);
		//insights
		insightButton = new JButton("VIEW INSIGHTS");
		insightButton.setBounds(188, 62, 122, 23);
		//delete entry
		btnDelete = new JButton("DELETE");
		btnDelete.setBounds(321, 62, 95, 23);
	}
	
	
	private void addComponents() {
		readDiaryPanel.add(lblEnterDate);
		readDiaryPanel.add(dateChooser);
		readDiaryPanel.add(contentScroll);
		readDiaryPanel.add(btnSearch);
		readDiaryPanel.add(btnEdit);
		readDiaryPanel.add(rating);
		readDiaryPanel.add(lblRating);
		readDiaryPanel.add(insightButton);
		readDiaryPanel.add(btnDelete);
		toggleComponents(false);
	}
	
	@Override
	public void run() {

		initComponents();
		addComponents();
		//search button action
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectedDate = DateConverter.convertDate(dateChooser); 
				try {
					page = getDiaryPage(selectedDate);	
					if(page.getContent().equals("")) {
						page.setContent("Wow! Such Empty");
						toggleComponents(false);
					}
					else 
						toggleComponents(true);
					contentField.setText(page.getContent().trim());
					contentField.setCaretPosition(0);
					rating.setSelection(page.getRating());	
			}
			catch(IOException | ClassNotFoundException e) {
				System.out.println(e);}
			}
		});
		
		//edit button action
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HomePage.replacePanel(HomePage.write.getPanel());
				HomePage.write.updateEditFields(page);
			}
		});	
		
		//insight button action
		insightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				QADialogCreator qa = new QADialogCreator(page.getQAData(), QADialogCreator.READ_MODE);	
				qa.showDialog();
			}
		});
		
		//delete button action
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(new File(reviseFileName()).delete()) {
					rating.setSelection(0);
					contentField.setText("Wow! Such Empty");
					toggleComponents(false);
					HighlightsManager(DELETE_ENTRY, page.getDate());								
					JOptionPane.showConfirmDialog(HomePage.getFrame(),"Entry Deleted!",
							"Delete Entry",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,
							new ImageIcon("green_tick.png"));
					page = new DiaryPage();	
				}
				else
					JOptionPane.showConfirmDialog(HomePage.getFrame(),"Delete Failed!",
							"Delete Entry",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
				
			}
		});
	
	}
	
	public void updateFields(DiaryPage newpage) throws ClassNotFoundException {
		page = newpage;
		toggleComponents(true);		
		dateChooser.setDate(DateConverter.convertfromCustom(page.getDate()));
			contentField.setText(page.getContent());
			rating.setSelection(page.getRating());
	}
	
	public void toggleComponents(boolean switcher) {
		btnEdit.setEnabled(switcher);
		insightButton.setEnabled(switcher);
		btnDelete.setEnabled(switcher);
	}
	
	public JPanel getPanel() {
		return readDiaryPanel;
	}
	
}
