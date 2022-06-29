package com.dale.login.signup;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;

import javax.swing.BoxLayout;
import javax.swing.*;

import com.dale.login.MainFrame;

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
	
	Font arialBoldFont = new Font("Arial", Font.BOLD, 30);
	Font arialFont = new Font("Arial", Font.CENTER_BASELINE, 20);
	Font IDFont = new Font("Arial", Font.CENTER_BASELINE, 15);
	
	public SignUpPanel(){
		this.setLayout(null);
		this.setBounds(0, 0, MainFrame.windwowWidth, MainFrame.windwowHeight);
		createSignUpPanel();
	}
	
	private void createSignUpPanel() {
		
	}
}
