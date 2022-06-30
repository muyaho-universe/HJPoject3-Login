package com.dale.login.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.*;

import com.dale.login.MainFrame;
import com.dale.login.buttons.RoundButton;
import com.dale.login.data.LoadedData;
import com.dale.login.data.MyData;
import com.dale.login.sql.SQLHandler;

public class AdminPanel extends JPanel {
	
	Font arialBoldFont = new Font("Arial", Font.BOLD, 30);
	Font arialFont = new Font("Arial", Font.CENTER_BASELINE, 20);
	Font IDFont = new Font("Arial", Font.CENTER_BASELINE, 15);
	Font smallFont = new Font("Arial", Font.CENTER_BASELINE, 10);
	public AdminPanel(){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBounds(0, 0, MainFrame.windwowWidth, MainFrame.windwowHeight);
		this.setBackground(Color.WHITE);
	}
//	super(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	public void drawPanel() {
		JPanel draw = new JPanel();
//		draw.setBounds(0, 0, MainFrame.windwowWidth, MainFrame.windwowHeight);
//		JScrollPane scrollSingle = new JScrollPane(draw, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		Container container = new Container();
		container.setSize(0, 50);
		JPanel columnBar = new JPanel();
		columnBar.setLayout(new FlowLayout());
		columnBar.setBackground(Color.WHITE);
		JLabel idLabel = new JLabel("ID");
		JLabel nameLabel = new JLabel("Name");
		JLabel passwordLabel = new JLabel("Password");
		idLabel.setFont(IDFont);
		passwordLabel.setFont(IDFont);
		nameLabel.setFont(IDFont);
		columnBar.add(idLabel);
		columnBar.add(passwordLabel);
		columnBar.add(nameLabel);
//		draw.add(container);
		this.add(columnBar);
		
		RoundButton deleteAll = new RoundButton("DELETE ALL",Color.RED);
		this.add(deleteAll);
		deleteAll.setAlignmentX(CENTER_ALIGNMENT);
		deleteAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame sure = new JFrame();
				sure.setSize(400, 300);
				sure.setVisible(true);
				sure.setLayout(new BorderLayout());
				JPanel buttonPanel = new JPanel();
				JLabel sureAboutIt = new JLabel("Are you sure about it? ");
				sureAboutIt.setFont(arialBoldFont);
				
				buttonPanel.setLayout(new FlowLayout());
				RoundButton yes = new RoundButton("Yes", Color.RED);
				RoundButton no = new RoundButton("No");
				yes.setFont(arialFont);
				no.setFont(arialFont);
				buttonPanel.add(no);
				buttonPanel.add(yes);
				
				sure.add(sureAboutIt, BorderLayout.CENTER);
				sure.add(buttonPanel, BorderLayout.SOUTH);
				no.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						sure.dispose();
					}
				});
				yes.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						SQLHandler.deleteAll();
						SQLHandler.selectAll();
						AdminPanel.this.drawPanel();
						draw.hide();
						
						sure.dispose();
					}
				});
				
			}
			
		});
		
		for(LoadedData loadedData:MyData.loadedData ) {
			JPanel doublePanel = new JPanel();
			JPanel customPanel = new JPanel();
			JPanel buttonPart = new JPanel();
			doublePanel.setLayout(new BoxLayout(doublePanel, BoxLayout.Y_AXIS));
			customPanel.setLayout(new FlowLayout());
			buttonPart.setLayout(new FlowLayout());
			customPanel.setBackground(Color.WHITE);
			JLabel ID = new JLabel(loadedData.getU_id());
			JLabel name = new JLabel(loadedData.getName());
			JLabel password = new JLabel(loadedData.getPassword());
			RoundButton showDetail = new RoundButton("Show Detail");
			RoundButton delete = new RoundButton("Delete", Color.RED);
			buttonPart.add(showDetail);
			buttonPart.add(delete);
			showDetail.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame showDetail = new JFrame();
					JPanel infoPanel = new JPanel();
					JPanel buttonPanel = new JPanel();
					
					infoPanel.setLayout(new FlowLayout());
					buttonPanel.setLayout(new FlowLayout());
					showDetail.setSize(300, 200);
					showDetail.setVisible(true);
					showDetail.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					showDetail.setLayout(new BorderLayout());
					JLabel thisID = new JLabel(loadedData.getU_id());
					JLabel thisname = new JLabel(loadedData.getName());
					JLabel thispassword = new JLabel(loadedData.getPassword());
					JLabel thisGender = new JLabel(loadedData.getGender());
					JLabel thisPhonenum = new JLabel(loadedData.getPhoneNumber());
					JLabel thisBirthday = new JLabel(loadedData.getDate());
					
//					thisID.setFont(smallFont);
////					thisname.setFont(smallFont);
//					thispassword.setFont(smallFont);
//					thisGender.setFont(smallFont);
//					thisPhonenum.setFont(smallFont);
//					thisBirthday.setFont(smallFont);
					
					infoPanel.add(thisID);
					infoPanel.add(thisname);
					infoPanel.add(thispassword);
					infoPanel.add(thisGender);
					infoPanel.add(thisPhonenum);
					infoPanel.add(thisBirthday);
					showDetail.add(infoPanel, BorderLayout.CENTER);
					
					RoundButton check = new RoundButton("Check");
					RoundButton delete = new RoundButton("Delete", Color.RED);
					buttonPanel.add(check);
					buttonPanel.add(delete);
					showDetail.add(buttonPanel, BorderLayout.SOUTH);
					check.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							showDetail.dispose();
						}
					});
					delete.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							JFrame sure = new JFrame();
							sure.setVisible(true);
							sure.setLayout(new BorderLayout());
							sure.setSize(400, 300);
							JPanel buttonPanel = new JPanel();
							JLabel sureAboutIt = new JLabel("Are you sure about it? ");
							sureAboutIt.setFont(arialBoldFont);
							
							buttonPanel.setLayout(new FlowLayout());
							RoundButton yes = new RoundButton("Yes", Color.RED);
							RoundButton no = new RoundButton("No");
							yes.setFont(arialFont);
							no.setFont(arialFont);
							buttonPanel.add(no);
							buttonPanel.add(yes);
							sure.add(sureAboutIt, BorderLayout.CENTER);
							sure.add(buttonPanel, BorderLayout.SOUTH);
							no.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									sure.dispose();
								}
							});
							yes.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									showDetail.dispose();
									SQLHandler.deleteOne(thisID.getText());
									customPanel.setVisible(false);
									sure.dispose();
								}
							});
						}
					});
				}
				
			});
			delete.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							
							JFrame sure = new JFrame();
							sure.setSize(400, 300);
							sure.setVisible(true);
							sure.setLayout(new BorderLayout());
							JPanel buttonPanel = new JPanel();
							JLabel sureAboutIt = new JLabel("Are you sure about it? ");
							sureAboutIt.setFont(arialBoldFont);
							
							buttonPanel.setLayout(new FlowLayout());
							RoundButton yes = new RoundButton("Yes", Color.RED);
							RoundButton no = new RoundButton("No");
							yes.setFont(arialFont);
							no.setFont(arialFont);
							buttonPanel.add(no);
							buttonPanel.add(yes);
							
							sure.add(sureAboutIt, BorderLayout.CENTER);
							sure.add(buttonPanel, BorderLayout.SOUTH);
							no.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									sure.dispose();
								}
							});
							yes.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									SQLHandler.deleteOne(ID.getText());
									customPanel.setVisible(false);
									sure.dispose();
								}
							});
						}
					});
			
			ID.setFont(arialFont);
//			name.setFont(arialFont);
			password.setFont(arialFont);
			
			customPanel.add(ID);
			customPanel.add(name);
			customPanel.add(password);
			
			doublePanel.add(customPanel);
			doublePanel.add(buttonPart);
			draw.add(doublePanel);
		}
		this.add(draw);
	}
}
