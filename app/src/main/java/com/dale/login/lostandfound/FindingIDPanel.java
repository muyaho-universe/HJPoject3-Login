package com.dale.login.lostandfound;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;

import javax.swing.*;

import com.dale.login.MainFrame;

public class FindingIDPanel extends JPanel {
	Font arialBoldFont = new Font("Arial", Font.BOLD, 30);
	Font arialFont = new Font("Arial", Font.CENTER_BASELINE, 20);
	Font IDFont = new Font("Arial", Font.CENTER_BASELINE, 15);
	Font smallFont = new Font("Arial", Font.CENTER_BASELINE, 10);
	
	private static JLabel title = new JLabel("ID Finder");
	private static JLabel nameFieldLabel = new JLabel("Enter your name: ");
	private static JLabel phoneNumberFieldLabel = new JLabel("Enter your phone number: ");
	
	private TextField nameField = null;
	private TextField phoneField = null;
	
	public FindingIDPanel(){
		this.setLayout(null);
		
		this.setBounds(0, 0, MainFrame.windwowWidth, MainFrame.windwowHeight);
		this.setBackground(Color.WHITE);
		createPanel();
	}
	
	private void createPanel() {
		title.setForeground(new Color(61,205,91));
		title.setFont(arialBoldFont);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setBounds(MainFrame.windwowWidth* 4 / 100, 30, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		
		nameFieldLabel.setFont(IDFont);
		nameFieldLabel.setBounds(MainFrame.windwowWidth* 4 / 100, 150, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		nameField = new TextField();
		nameField.setBounds(MainFrame.windwowWidth* 4 / 100, 180, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		nameField.setFont(arialFont);
		
		phoneNumberFieldLabel.setFont(IDFont);
		phoneNumberFieldLabel.setBounds(MainFrame.windwowWidth* 4 / 100, 230, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		phoneField = new TextField();
		phoneField.setBounds(MainFrame.windwowWidth* 4 / 100, 270, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		phoneField.setFont(arialFont);
		
		
		this.add(title);
		this.add(nameFieldLabel);
		this.add(nameField);
		this.add(phoneNumberFieldLabel);
		this.add(phoneField);
	}
	
	
}
