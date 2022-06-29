package com.dale.login.signup;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import com.dale.login.MainFrame;
import com.dale.login.buttons.RoundButton;

public class SignUpPanel extends JPanel {
	static JLabel label = new JLabel("Sign up");
	private static JLabel idFieldLabel = new JLabel("Enter the ID:");
	private static JLabel passwordFieldLabel = new JLabel("Enter the password: ");
	private static JLabel passwordConfirmFieldLabel = new JLabel("Re-enter the password:");
	private JLabel passwordConfirmCheck = null;
	private JLabel passwordCheck = null;
	private static JLabel nameFieldLabel = null;
	private static JLabel genderFieldLabel = null;
	private static JLabel phoneNumberFieldLabel = null;
	private static JLabel birthdayFieldLabel = null;
	
	private TextField idField = null;
	private JPasswordField passwordField = null;
	private JPasswordField passwordConfirmField = null;
	private TextField nameField = null;
	private TextField phoneNumberField = null; // use mask 010-0000-0000 format
	
	private JComboBox yearComboBox = null;
	private JComboBox monthComboBox = null;
	private JComboBox dayComboBox = null;
	
	private String[] years;
	private String[] months;
	private String[] days;
	
	private JToggleButton male;
	private JToggleButton female;
	
	private ButtonGroup buttonGroup;
	private Container container;
	
	private RoundButton goToBack;
	private RoundButton idConfirmButton;
	private RoundButton setVisiblityOnPasswordField;
	private RoundButton setVisiblityOnPasswordConfirmField;
	
	private boolean isIDConfirmed;
	private boolean doesPasswordHaveNumber = false;
	private boolean doesPasswordHaveCapitol = false;
	private boolean doesPasswordHaveLower = false;
	private boolean isPasswordLongEnough = false;
	private boolean isPasswordSame = false;
	private boolean isNameFilled;
	private boolean isVisiblePasswordField = false;
	private boolean isVisiblePasswordConfirmField = false;
	
	Font arialBoldFont = new Font("Arial", Font.BOLD, 30);
	Font arialFont = new Font("Arial", Font.CENTER_BASELINE, 20);
	Font IDFont = new Font("Arial", Font.CENTER_BASELINE, 15);
	Font smallFont = new Font("Arial", Font.CENTER_BASELINE, 10);
	
	public SignUpPanel(){
		this.setLayout(null);
		this.setBounds(0, 0, MainFrame.windwowWidth, MainFrame.windwowHeight);
		this.setBackground(Color.WHITE);
		createSignUpPanel();
	}
	
	private void createSignUpPanel() {
		goToBack = new RoundButton("<-", new Color(230, 230, 230));
		goToBack.setBounds(5,5, 43, 20);
		
		label.setFont(arialBoldFont);
		label.setForeground(new Color(61,205,91));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBounds(MainFrame.windwowWidth* 4 / 100, 50, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		
		idFieldLabel.setFont(IDFont);
		idFieldLabel.setBounds(MainFrame.windwowWidth* 4 / 100, 105, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		idField = new TextField();
		idField.setBounds(MainFrame.windwowWidth* 4 / 100, 140, MainFrame.windwowWidth * 88 / 100- 60, MainFrame.windwowHeight * 5 / 100);
		idField.setFont(arialFont);
		idConfirmButton = new RoundButton("Confirm");
		idConfirmButton.setBounds(MainFrame.windwowWidth * 88 / 100-50, 140, 50, MainFrame.windwowHeight * 5 / 100);
		
		passwordFieldLabel.setFont(IDFont);
		passwordFieldLabel.setBounds(MainFrame.windwowWidth* 4 / 100, 185, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		passwordField = new JPasswordField();
		passwordField.setBounds(MainFrame.windwowWidth* 4 / 100, 210, MainFrame.windwowWidth * 88 / 100- 60, MainFrame.windwowHeight * 5 / 100);
		passwordField.setFont(arialFont);
		passwordField.setEchoChar('*');
		passwordCheck = new JLabel("8 characters or More");
		passwordCheck.setFont(smallFont);
		passwordCheck.setBounds(MainFrame.windwowWidth* 4 / 100, 230, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		passwordCheck.setForeground(Color.RED);
		passwordField.addCaretListener(new CaretListener(){
			@Override
			public void caretUpdate(CaretEvent e) {
				String temp =new String( passwordField.getPassword());
				char[] charTemp = temp.toCharArray();
				if(temp.length() >= 8) {
					isPasswordLongEnough = true;
				}
				else {
					isPasswordLongEnough = false;
					passwordCheck.setText("8 characters or More");
				}
				for(char c: charTemp) {
					if( 48 <= c && c <= 57) {
						doesPasswordHaveNumber = true;
					}
					if( 65 <= c && c <= 90) {
						doesPasswordHaveCapitol = true;
					}
					if( 97 <= c && c <= 122) {
						doesPasswordHaveLower = true;
					}
				}
				if(isPasswordLongEnough) {
					passwordCheck.setText("You should include at least one upper, one lower, and one number in password");
					if(doesPasswordHaveNumber && doesPasswordHaveCapitol && doesPasswordHaveLower) {
						passwordCheck.setText("Good to GO~~!!");
						passwordCheck.setForeground(new Color(61,205,91));
					}
					else {
						doesPasswordHaveNumber = false;
						doesPasswordHaveCapitol = false;
						doesPasswordHaveLower = false;
						passwordCheck.setForeground(Color.RED);
					}
				}
			}
			
		});
		setVisiblityOnPasswordField = new RoundButton("Show");
		setVisiblityOnPasswordField.setBounds(MainFrame.windwowWidth * 88 / 100-50, 210, 50, MainFrame.windwowHeight * 5 / 100);
		setVisiblityOnPasswordField.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(isVisiblePasswordField) {
					setVisiblityOnPasswordField.setColor("Show", new Color(61,205,91));
					passwordField.setEchoChar('*');
					isVisiblePasswordField = false;
				}
				else {
					setVisiblityOnPasswordField.setColor("Hide", Color.GRAY);
					passwordField.setEchoChar((char)0);
					isVisiblePasswordField = true;
				}
			}
		});
		
		passwordConfirmFieldLabel.setFont(IDFont);
		passwordConfirmFieldLabel.setBounds(MainFrame.windwowWidth* 4 / 100, 250, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		passwordConfirmField = new JPasswordField();
		passwordConfirmField.setBounds(MainFrame.windwowWidth* 4 / 100, 275, MainFrame.windwowWidth * 88 / 100- 60, MainFrame.windwowHeight * 5 / 100);
		passwordConfirmField.setFont(arialFont);
		passwordConfirmField.setEchoChar('*');
		setVisiblityOnPasswordConfirmField = new RoundButton("Show");
		setVisiblityOnPasswordConfirmField.setBounds(MainFrame.windwowWidth * 88 / 100-50, 275, 50, MainFrame.windwowHeight * 5 / 100);
		setVisiblityOnPasswordConfirmField.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(isVisiblePasswordConfirmField) {
					setVisiblityOnPasswordConfirmField.setColor("Show", new Color(61,205,91));
					passwordConfirmField.setEchoChar('*');
					isVisiblePasswordConfirmField = false;
				}
				else {
					setVisiblityOnPasswordConfirmField.setColor("Hide", Color.GRAY);
					passwordConfirmField.setEchoChar((char)0);
					isVisiblePasswordConfirmField = true;
				}
			}
		});
		passwordConfirmCheck =new JLabel("");
		passwordConfirmCheck.setFont(smallFont);
		passwordConfirmCheck.setBounds(MainFrame.windwowWidth* 4 / 100, 295, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		passwordConfirmField.addCaretListener(new CaretListener(){
			String password ;
			String passwordConfirmed; 
			@Override
			public void caretUpdate(CaretEvent e) {
				if(isPasswordLongEnough && doesPasswordHaveNumber && doesPasswordHaveCapitol && doesPasswordHaveLower) {
					password = new String(passwordField.getPassword());
					passwordConfirmed = new  String(passwordConfirmField.getPassword());
					System.out.println(password + " " + passwordConfirmed + " " +password.equals(passwordConfirmed));
					if(password.equals(passwordConfirmed)) {
						passwordConfirmCheck.setText("Password Confirmed");
						passwordConfirmCheck.setForeground(new Color(61,205,91));
						isPasswordSame = true;
					}
					else {
						isPasswordSame =false;
						passwordConfirmCheck.setText("Password not Matched");
						passwordConfirmCheck.setForeground(Color.RED);
					}
				}
				else {
					passwordConfirmCheck.setText("Check the pass word first");
					passwordConfirmCheck.setForeground(Color.RED);
					isPasswordSame = false;
				}
			}
		});
		
		
		this.add(goToBack);
		this.add(label);
		this.add(idFieldLabel);
		this.add(idField);
		this.add(idConfirmButton);
		this.add(passwordFieldLabel);
		this.add(passwordField);
		this.add(setVisiblityOnPasswordField);
		this.add(passwordCheck);
		this.add(passwordConfirmFieldLabel);
		this.add(passwordConfirmField);
		this.add(setVisiblityOnPasswordConfirmField);
		this.add(passwordConfirmCheck);
	}
	
	public RoundButton getGoToBackButton() {
		return goToBack;
	}
}
