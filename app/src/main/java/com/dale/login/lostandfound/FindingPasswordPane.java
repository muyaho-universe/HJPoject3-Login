package com.dale.login.lostandfound;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextField;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

import com.dale.login.MainFrame;
import com.dale.login.buttons.RoundButton;

public class FindingPasswordPane extends JPanel {
	Font arialBoldFont = new Font("Arial", Font.BOLD, 30);
	Font arialFont = new Font("Arial", Font.CENTER_BASELINE, 20);
	Font IDFont = new Font("Arial", Font.CENTER_BASELINE, 15);
	Font smallFont = new Font("Arial", Font.CENTER_BASELINE, 10);
	
	private static JLabel title = new JLabel("Password Finder");
	private static JLabel idFieldLabel = new JLabel("Enter your id: ");
	private static JLabel nameFieldLabel = new JLabel("Enter your name: ");
	private static JLabel phoneNumberFieldLabel = new JLabel("Enter your phone number: ");
	private static JLabel birthdayFieldLabel = new JLabel("Enter your birthday: ");
	
	private TextField nameField = null;
	private TextField idField = null;
	private JFormattedTextField phoneField = null;
	private JFormattedTextField birthdayField = null;
	
	private RoundButton find;
	private RoundButton cancel;
	
	private JPanel buttonPanel;
	
	private MaskFormatter formatter;
	private MaskFormatter birthdayFormatter;
	
	public FindingPasswordPane(){
		this.setLayout(null);
		
		this.setBounds(0, 0, MainFrame.windwowWidth, MainFrame.windwowHeight);
		this.setBackground(Color.WHITE);
		createPanel();
	}
	
	public void createPanel() {
		title.setForeground(new Color(61,205,91));
		title.setFont(arialBoldFont);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setBounds(MainFrame.windwowWidth* 4 / 100, 50, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		
		idFieldLabel.setFont(IDFont);
		idFieldLabel.setBounds(MainFrame.windwowWidth* 4 / 100, 150 - 30, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		idField = new TextField();
		idField.setBounds(MainFrame.windwowWidth* 4 / 100, 180- 30, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		idField.setFont(arialFont);
		
		nameFieldLabel.setFont(IDFont);
		nameFieldLabel.setBounds(MainFrame.windwowWidth* 4 / 100, 230- 30, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		nameField = new TextField();
		nameField.setBounds(MainFrame.windwowWidth* 4 / 100, 260- 30, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		nameField.setFont(arialFont);
		
		phoneNumberFieldLabel.setFont(IDFont);
		phoneNumberFieldLabel.setBounds(MainFrame.windwowWidth* 4 / 100, 310- 30, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		try {
			formatter = new MaskFormatter("010-####-####");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		phoneField = new JFormattedTextField();
		formatter.install(phoneField);
		phoneField.setBounds(MainFrame.windwowWidth* 4 / 100, 340- 30, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		phoneField.setFont(arialFont);
		
//		birthdayFieldLabel.setFont(IDFont);
//		birthdayFieldLabel.setBounds(MainFrame.windwowWidth* 4 / 100, 390- 30, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
//		try {
//			birthdayFormatter = new MaskFormatter("####-##-##");
//		} catch (ParseException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		birthdayField = new JFormattedTextField();
//		formatter.install(phoneField);
//		birthdayField.setBounds(MainFrame.windwowWidth* 4 / 100, 420- 30, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
//		birthdayField.setFont(arialFont);
		
		buttonPanel = new JPanel();
		
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBounds(MainFrame.windwowWidth* 4 / 100, 470- 30, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 10 / 100);
		
		find = new RoundButton("Find!");
		cancel  = new RoundButton("Cancel", Color.GRAY);
		
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.add(cancel);
		buttonPanel.add(find);
		
		this.add(title);
		this.add(idFieldLabel);
		this.add(idField);
		this.add(nameFieldLabel);
		this.add(nameField);
		this.add(phoneNumberFieldLabel);
		this.add(phoneField);
//		this.add(birthdayFieldLabel);
//		this.add(birthdayField);
		this.add(buttonPanel);
	}

	public TextField getNameField() {
		return nameField;
	}

	public void setNameField(TextField nameField) {
		this.nameField = nameField;
	}

	public TextField getIdField() {
		return idField;
	}

	public void setIdField(TextField idField) {
		this.idField = idField;
	}

	public JFormattedTextField getPhoneField() {
		return phoneField;
	}

	public void setPhoneField(JFormattedTextField phoneField) {
		this.phoneField = phoneField;
	}

	public JFormattedTextField getBirthdayField() {
		return birthdayField;
	}

	public void setBirthdayField(JFormattedTextField birthdayField) {
		this.birthdayField = birthdayField;
	}

	public RoundButton getFind() {
		return find;
	}

	public void setFind(RoundButton find) {
		this.find = find;
	}

	public RoundButton getCancel() {
		return cancel;
	}

	public void setCancel(RoundButton cancel) {
		this.cancel = cancel;
	}
}
