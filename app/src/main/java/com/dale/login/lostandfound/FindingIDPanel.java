package com.dale.login.lostandfound;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import com.dale.login.MainFrame;
import com.dale.login.buttons.RoundButton;
import com.dale.login.sql.SQLHandler;

public class FindingIDPanel extends JPanel {
	Font arialBoldFont = new Font("Arial", Font.BOLD, 30);
	Font arialFont = new Font("Arial", Font.CENTER_BASELINE, 20);
	Font IDFont = new Font("Arial", Font.CENTER_BASELINE, 15);
	Font smallFont = new Font("Arial", Font.CENTER_BASELINE, 10);
	
	private static JLabel title = new JLabel("ID Finder");
	private static JLabel nameFieldLabel = new JLabel("Enter your name: ");
	private static JLabel phoneNumberFieldLabel = new JLabel("Enter your phone number: ");
	
	private TextField nameField = null;
	private JFormattedTextField phoneField = null;
	
	private RoundButton find;
	private RoundButton cancel;
	
	private JPanel buttonPanel;
	
	private MaskFormatter formatter;
	
	
	
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
		title.setBounds(MainFrame.windwowWidth* 4 / 100, 50, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		
		nameFieldLabel.setFont(IDFont);
		nameFieldLabel.setBounds(MainFrame.windwowWidth* 4 / 100, 150, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		nameField = new TextField();
		nameField.setBounds(MainFrame.windwowWidth* 4 / 100, 180, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		nameField.setFont(arialFont);
		
		phoneNumberFieldLabel.setFont(IDFont);
		phoneNumberFieldLabel.setBounds(MainFrame.windwowWidth* 4 / 100, 230, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		try {
			formatter = new MaskFormatter("010-####-####");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		phoneField = new JFormattedTextField();
		formatter.install(phoneField);
		phoneField.setBounds(MainFrame.windwowWidth* 4 / 100, 270, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		phoneField.setFont(arialFont);
		
		buttonPanel = new JPanel();
		
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBounds(MainFrame.windwowWidth* 4 / 100, 350, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 10 / 100);
		
		find = new RoundButton("Find!");
		cancel  = new RoundButton("Cancel", Color.GRAY);
		
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.add(cancel);
		buttonPanel.add(find);
		
		this.add(title);
		this.add(nameFieldLabel);
		this.add(nameField);
		this.add(phoneNumberFieldLabel);
		this.add(phoneField);
		this.add(buttonPanel);
	}
	
	public RoundButton getFindButton() {
		return find;
	}
	public RoundButton getCancelButton() {
		return cancel;
	}

	public TextField getNameField() {
		return nameField;
	}

	public void setNameField(TextField nameField) {
		this.nameField = nameField;
	}

	public JFormattedTextField getPhoneField() {
		return phoneField;
	}
}
