package com.dale.login.signup;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.MaskFormatter;

import com.dale.login.MainFrame;
import com.dale.login.buttons.RoundButton;

public class SignUpPanel extends JPanel {
	static JLabel label = new JLabel("Sign up");
	private static JLabel idFieldLabel = new JLabel("Enter the ID:");
	private static JLabel passwordFieldLabel = new JLabel("Enter the password: ");
	private static JLabel passwordConfirmFieldLabel = new JLabel("Re-enter the password:");
	private JLabel idFieldcheck = null;
	private JLabel passwordConfirmCheck = null;
	private JLabel passwordCheck = null;
	private static JLabel nameFieldLabel = new JLabel("Enter your name: ");
	private static JLabel genderFieldLabel =  new JLabel("Select your gender: ");
	private static JLabel phoneNumberFieldLabel = new JLabel("Enter your phone number: ");
	private static JLabel birthdayFieldLabel = new JLabel("Select your birthday: ");
	private static JLabel confirmLabel = new JLabel("I agree! ");
	
	private TextField idField = null;
	private JPasswordField passwordField = null;
	private JPasswordField passwordConfirmField = null;
	private TextField nameField = null;
	private JFormattedTextField phoneNumberField = null; // use mask 010-0000-0000 format
	
	private JComboBox yearComboBox = null;
	private JComboBox monthComboBox = null;
	private JComboBox dayComboBox = null;
	
	private String[] years;
	private String[] months;
	private String[] days;
	private String gender = null;
	
	private JToggleButton male;
	private JToggleButton female;
	
	private ButtonGroup buttonGroup;
	private Container container;
	
	private RoundButton goToBack;
	private RoundButton idConfirmButton;
	private RoundButton setVisiblityOnPasswordField;
	private RoundButton setVisiblityOnPasswordConfirmField;
	private RoundButton signUp;
	
	private JCheckBox agree;
	
	private boolean isIDLongEnough;
	private boolean isIDConfirmed;
	private boolean doesPasswordHaveNumber = false;
	private boolean doesPasswordHaveCapitol = false;
	private boolean doesPasswordHaveLower = false;
	private boolean isPasswordLongEnough = false;
	private boolean isPasswordSame = false;
	private boolean isNameFilled;
	private boolean isVisiblePasswordField = false;
	private boolean isVisiblePasswordConfirmField = false;
	private boolean isGenderSelected = false;
	private boolean isAgreed = false;
	
	private JPanel togglePanel;
	private JPanel birthDayComboPanel;
	
	private MaskFormatter formatter;
	
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
		goToBack = new RoundButton("ก็", new Color(230, 230, 230));
		goToBack.setBounds(5,5, 43, 20);
		
		label.setFont(arialBoldFont);
		label.setForeground(new Color(61,205,91));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBounds(MainFrame.windwowWidth* 4 / 100, 30, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		
		idFieldLabel.setFont(IDFont);
		idFieldLabel.setBounds(MainFrame.windwowWidth* 4 / 100, 85, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		idField = new TextField();
		idField.setBounds(MainFrame.windwowWidth* 4 / 100, 120, MainFrame.windwowWidth * 88 / 100- 60, MainFrame.windwowHeight * 5 / 100);
		idFieldcheck = new JLabel("");
		idFieldcheck.setForeground(Color.RED);
		idFieldcheck.setBounds(MainFrame.windwowWidth* 4 / 100, 140, MainFrame.windwowWidth * 88 / 100- 60, MainFrame.windwowHeight * 5 / 100);
		idFieldcheck.setFont(smallFont);
		idField.setFont(arialFont);
		idField.addTextListener(new TextListener() {
			@Override
			public void textValueChanged(TextEvent e) {
				// TODO Auto-generated method stub
				if(idField.getText().length()>= 4 ) {
					isIDLongEnough = true;
					idFieldcheck.setText("Long enough");
					idFieldcheck.setForeground(Color.BLACK);
				}
				else {
					idFieldcheck.setText("Too short! 4 or more!");
					isIDLongEnough =false;
				}
			}
		});
		
		idConfirmButton = new RoundButton("Confirm");
		idConfirmButton.setBounds(MainFrame.windwowWidth * 88 / 100-50, 120, 50, MainFrame.windwowHeight * 5 / 100);
		idConfirmButton.addActionListener(new )
		
		
		passwordFieldLabel.setFont(IDFont);
		passwordFieldLabel.setBounds(MainFrame.windwowWidth* 4 / 100, 165, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		passwordField = new JPasswordField();
		passwordField.setBounds(MainFrame.windwowWidth* 4 / 100, 190, MainFrame.windwowWidth * 88 / 100- 60, MainFrame.windwowHeight * 5 / 100);
		passwordField.setFont(arialFont);
		passwordField.setEchoChar('*');
		passwordCheck = new JLabel("8 characters or More");
		passwordCheck.setFont(smallFont);
		passwordCheck.setBounds(MainFrame.windwowWidth* 4 / 100, 210, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		passwordCheck.setForeground(Color.RED);
		passwordField.addCaretListener(new CaretListener(){
			@Override
			public void caretUpdate(CaretEvent e) {
				String temp =new String(passwordField.getPassword());
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
				else {
					doesPasswordHaveNumber = false;
					doesPasswordHaveCapitol = false;
					doesPasswordHaveLower = false;
					passwordCheck.setForeground(Color.RED);
				}
			}
			
		});
		setVisiblityOnPasswordField = new RoundButton("Show");
		setVisiblityOnPasswordField.setBounds(MainFrame.windwowWidth * 88 / 100-50, 190, 50, MainFrame.windwowHeight * 5 / 100);
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
		passwordConfirmFieldLabel.setBounds(MainFrame.windwowWidth* 4 / 100, 230, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		passwordConfirmField = new JPasswordField();
		passwordConfirmField.setBounds(MainFrame.windwowWidth* 4 / 100, 255, MainFrame.windwowWidth * 88 / 100- 60, MainFrame.windwowHeight * 5 / 100);
		passwordConfirmField.setFont(arialFont);
		passwordConfirmField.setEchoChar('*');
		setVisiblityOnPasswordConfirmField = new RoundButton("Show");
		setVisiblityOnPasswordConfirmField.setBounds(MainFrame.windwowWidth * 88 / 100-50, 255, 50, MainFrame.windwowHeight * 5 / 100);
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
		passwordConfirmCheck.setBounds(MainFrame.windwowWidth* 4 / 100, 275, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
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
		
		nameFieldLabel.setBounds(MainFrame.windwowWidth* 4 / 100, 290, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 4 / 100);
		nameFieldLabel.setFont(IDFont);
		nameField = new TextField();
		nameField.setBounds(MainFrame.windwowWidth* 4 / 100, 315, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		nameField.setFont(arialFont);
		
		togglePanel = new JPanel();
		genderFieldLabel.setBounds(MainFrame.windwowWidth* 4 / 100, 350, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		genderFieldLabel.setFont(IDFont);
		male = new JToggleButton("Male");
		male.setBackground(Color.WHITE);
		male.setFont(smallFont);
		male.setForeground(new Color(61,205,91));
		male.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED) {
                	isGenderSelected = true;
                	gender = "male";
                	System.out.println(gender);
                }              
            }
        });
		female = new JToggleButton("Female");
		female.setBackground(Color.WHITE);
		female.setFont(smallFont);
		female.setForeground(new Color(61,205,91));
		female.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED) {
                	isGenderSelected = true;
                	gender = "female";
                	System.out.println(gender);
                }              
            }
        });
		buttonGroup = new ButtonGroup();
		container = new Container();
		buttonGroup.add(male);
		buttonGroup.add(female);
		
		container.add(male);
		container.add(female);
		container.setBounds(MainFrame.windwowWidth* 4 / 100, 375, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		container.setLayout(new FlowLayout());
		
		phoneNumberFieldLabel.setBounds(MainFrame.windwowWidth* 4 / 100, 395, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		phoneNumberFieldLabel.setFont(IDFont);
		try {
			formatter = new MaskFormatter("010-####-####");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		phoneNumberField = new JFormattedTextField();
		formatter.install(phoneNumberField);
		phoneNumberField.setBounds(MainFrame.windwowWidth* 4 / 100, 420, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		
		birthdayFieldLabel.setBounds(MainFrame.windwowWidth* 4 / 100, 455, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		birthdayFieldLabel.setFont(IDFont);
		years = new String[73];
		for(int i = 0; i < 73; i ++) {
			years[i] = i+1950+"";
		}
		months = new String[12];
		for(int i = 0; i < 12; i ++) {
			months[i] = i+1+"";
		}
		days = new String[31];
		for(int i = 0; i < 31; i ++) {
			days[i] = i+1+"";
		}
		yearComboBox = new JComboBox(years);
		yearComboBox.setFont(IDFont);
		monthComboBox = new JComboBox(months);
		monthComboBox.setFont(IDFont);
		dayComboBox = new JComboBox(days);
		dayComboBox.setFont(IDFont);
		birthDayComboPanel = new JPanel();
		birthDayComboPanel.setLayout(new FlowLayout());
		birthDayComboPanel.setBounds(MainFrame.windwowWidth* 4 / 100, 480, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		birthDayComboPanel.add(yearComboBox);
		birthDayComboPanel.add(monthComboBox);
		birthDayComboPanel.add(dayComboBox);
		birthDayComboPanel.setBackground(Color.WHITE);
		
		confirmLabel.setBounds(MainFrame.windwowWidth* 4 / 100, 510, MainFrame.windwowWidth * 44 / 100, MainFrame.windwowHeight * 5 / 100);
		confirmLabel.setBackground(Color.WHITE);
		confirmLabel.setFont(IDFont);
		agree = new JCheckBox();
		agree.setBounds(MainFrame.windwowWidth * 64 / 100, 515,  MainFrame.windwowHeight * 3/ 100, MainFrame.windwowHeight * 3 / 100);
		agree.setBackground(Color.WHITE);
		agree.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED)
                	isAgreed = true;
                else
                	isAgreed = false;
            }
        });
		
		signUp = new RoundButton("Make an Account!");
		signUp.setFont(arialBoldFont);
		signUp.setBounds(MainFrame.windwowWidth * 4 / 100, 550,  MainFrame.windwowWidth * 88/ 100, MainFrame.windwowHeight * 8 / 100);
		signUp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		this.add(goToBack);
		this.add(label);
		this.add(idFieldLabel);
		this.add(idField);
		this.add(idConfirmButton);
		this.add(idFieldcheck);
		this.add(passwordFieldLabel);
		this.add(passwordField);
		this.add(setVisiblityOnPasswordField);
		this.add(passwordCheck);
		this.add(passwordConfirmFieldLabel);
		this.add(passwordConfirmField);
		this.add(setVisiblityOnPasswordConfirmField);
		this.add(passwordConfirmCheck);
		this.add(nameFieldLabel);
		this.add(nameField);
		this.add(genderFieldLabel);
		this.add(container);
		this.add(phoneNumberFieldLabel);
		this.add(phoneNumberField);
		this.add(birthdayFieldLabel);
		this.add(birthDayComboPanel);
		this.add(confirmLabel);
		this.add(agree);
		this.add(signUp);
	}
	
	public RoundButton getGoToBackButton() {
		return goToBack;
	}
}
