package com.dale.login;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import com.dale.login.buttons.RoundButton;
import com.dale.login.data.MyData;

public class HomePanel extends JPanel {
	private RoundButton signUp;
	private RoundButton login;
	private RoundButton findingID;
	private RoundButton findingPassword;
	private TextField idField;
	private JPasswordField passwordField;
	private JLabel welcome;
	private JLabel IDInput;
	private JLabel findingLabel;
	private JLabel newToHere;
	private JLabel passwordInput;
	private boolean isVisible = false;
	private RoundButton setVisiblity;
	private JPanel findIDAndPasswordPanel;
	
	Font arialBoldFont = new Font("Arial", Font.BOLD, 30);
	Font arialFont = new Font("Arial", Font.CENTER_BASELINE, 20);
	Font IDFont = new Font("Arial", Font.CENTER_BASELINE, 15);
	
	
	HomePanel(){
		this.setLayout(null);
		
		this.setBounds(0, 0, MainFrame.windwowWidth, MainFrame.windwowHeight);
		this.setBackground(Color.WHITE);
	}
	
	public void createPanel() {
		welcome = new JLabel("Welcome!");
		idField = new TextField();
		passwordField = new JPasswordField();
		login = new RoundButton("Login");
		signUp = new RoundButton("Sign up");
		IDInput = new JLabel("Enter your ID: ");
		passwordInput = new JLabel("Enter your password: ");
		setVisiblity = new RoundButton("Show");
		findingLabel = new JLabel("Did you forget your account ? ");
		findingID = new RoundButton("Forgot ID", Color.WHITE, false);
		findingPassword = new RoundButton("Forgot Password", Color.WHITE, false);
		findIDAndPasswordPanel = new JPanel();
		newToHere = new JLabel("Are you new to here? ");
		
		signUp.setBounds(MainFrame.windwowWidth* 4 / 100, MainFrame.windwowHeight - 200, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 8 / 100);
		signUp.setFont(arialBoldFont);
		
		welcome.setFont(arialBoldFont);
		welcome.setHorizontalAlignment(JLabel.CENTER);
		welcome.setForeground(new Color(61,205,91));
		welcome.setBounds(MainFrame.windwowWidth* 4 / 100, 50, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		
		
		idField.setBounds(MainFrame.windwowWidth* 4 / 100, 150, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 4 / 100);
		idField.setFont(arialFont);
		
		passwordField.setBounds(MainFrame.windwowWidth* 4 / 100, 150 + MainFrame.windwowHeight * 10 / 100, MainFrame.windwowWidth * 88 / 100 - 60, MainFrame.windwowHeight * 4 / 100);
		passwordField.setFont(arialFont);
		passwordField.setEchoChar('*');
		setVisiblity.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(isVisible) {
					setVisiblity.setColor("Show", new Color(61,205,91));
					passwordField.setEchoChar('*');
					isVisible = false;
				}
				else {
					setVisiblity.setColor("Hide", Color.GRAY);
					passwordField.setEchoChar((char)0);
					isVisible = true;
				}
			}
		});
		setVisiblity.setBounds(MainFrame.windwowWidth * 84 / 100 -30, 150 + MainFrame.windwowHeight * 10 / 100, MainFrame.windwowWidth * 16 / 100, MainFrame.windwowHeight * 4 / 100);
		
		login.setBounds(MainFrame.windwowWidth* 4 / 100,150 + MainFrame.windwowHeight * 10 / 100 + 45, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 8 / 100);
		login.setFont(arialBoldFont);
		login.addActionListener(null);
		
		IDInput.setBounds(MainFrame.windwowWidth* 4 / 100, 150+5 - MainFrame.windwowHeight * 5 / 100, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 4 / 100);
		IDInput.setFont(IDFont);
		
		passwordInput.setBounds(MainFrame.windwowWidth* 4 / 100, 150+5 - MainFrame.windwowHeight * 5 / 100 + MainFrame.windwowHeight * 10 / 100, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 4 / 100);
		passwordInput.setFont(IDFont);
		
		findingLabel.setBounds(MainFrame.windwowWidth* 4 / 100, 250 + MainFrame.windwowHeight * 10 / 100 + 17, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 8 / 100);
		findingLabel.setFont(IDFont);
		
		findIDAndPasswordPanel.setBounds(MainFrame.windwowWidth* 4 / 100, 300 + MainFrame.windwowHeight * 10 / 100 , MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 8 / 100);
		findIDAndPasswordPanel.setLayout(new GridLayout());
		findingID.setAlignmentX(CENTER_ALIGNMENT);
		findingPassword.setAlignmentX(CENTER_ALIGNMENT);
		findIDAndPasswordPanel.add(findingID);
		findIDAndPasswordPanel.add(findingPassword);
		
		newToHere.setBounds(MainFrame.windwowWidth* 4 / 100, 350 + MainFrame.windwowHeight * 10 / 100 , MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 8 / 100);
		newToHere.setFont(IDFont);
		
		this.add(newToHere);
		this.add(findingLabel);
		this.add(setVisiblity);
		this.add(passwordField);
		this.add(IDInput);
		this.add(welcome);
		this.add(idField);
		this.add(signUp);
		this.add(login);
		this.add(passwordInput);
		this.add(findIDAndPasswordPanel);
	}
	
	public RoundButton getSignUpButton() {
		return signUp;
	}
	
	public RoundButton getLoginButton() {
		return login;
	}
	
	public TextField getIDField() {
		return idField;
	}
	
	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public RoundButton getFindingID() {
		return findingID;
	}
	
	public RoundButton getFindingPassword() {
		return findingPassword;
	}

	public void setFindingID(RoundButton findingID) {
		this.findingID = findingID;
	}
}
