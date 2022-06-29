package com.dale.login.signup;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.TextField;

import javax.swing.*;

import com.dale.login.MainFrame;
import com.dale.login.buttons.RoundButton;

public class SignUpPanel extends JPanel {
	static JLabel label = new JLabel("Sign up");
	private static JLabel idFieldLabel = new JLabel("Enter the ID:");
	private static JLabel passwordFieldLabel = new JLabel("Enter the password:");
	private static JLabel passwordConfirmFieldLabel = new JLabel("Enter the password again:");
	private JLabel passwordConfirmCheck = null;
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
	private boolean isPasswordSame = false;
	private boolean isNameFilled;
	
	Font arialBoldFont = new Font("Arial", Font.BOLD, 30);
	Font arialFont = new Font("Arial", Font.CENTER_BASELINE, 20);
	Font IDFont = new Font("Arial", Font.CENTER_BASELINE, 15);
	
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
		
		this.add(goToBack);
		this.add(label);
		this.add(idFieldLabel);
		this.add(idField);
		this.add(idConfirmButton);
	}
	
	public RoundButton getGoToBackButton() {
		return goToBack;
	}
}
