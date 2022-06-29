package com.dale.login;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import com.dale.login.buttons.DesignedButton;
import com.dale.login.buttons.RoundButton;

public class HomePanel extends JPanel {
	private RoundButton signUp;
	private TextField idField;
	private JPasswordField passwordField;
	private JLabel welcome;
	private JLabel IDInput;
	private JLabel passwordInput;
	private boolean isVisible = false;
	private RoundButton setVisiblity;
	
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
		signUp = new RoundButton("Sign up");
		IDInput = new JLabel("Enter your ID: ");
		passwordInput = new JLabel("Enter your password: ");
		setVisiblity = new RoundButton("Hide");
		
		signUp.setBounds(MainFrame.windwowWidth* 4 / 100, MainFrame.windwowHeight - 150, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 8 / 100);
		signUp.setFont(arialBoldFont);
		
		welcome.setFont(arialBoldFont);
		welcome.setHorizontalAlignment(JLabel.CENTER);
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
					setVisiblity.setColor("Hide", new Color(61,205,91));
					passwordField.setEchoChar('*');
					isVisible = false;
				}
				else {
					setVisiblity.setColor("Show", Color.GRAY);
					passwordField.setEchoChar((char)0);
					isVisible = true;
				}
			}
		});
		setVisiblity.setBounds(MainFrame.windwowWidth * 84 / 100 -30, 150 + MainFrame.windwowHeight * 10 / 100, MainFrame.windwowWidth * 16 / 100, MainFrame.windwowHeight * 4 / 100);
		
		IDInput.setBounds(MainFrame.windwowWidth* 4 / 100, 150+5 - MainFrame.windwowHeight * 5 / 100, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 4 / 100);
		IDInput.setFont(IDFont);
		
		passwordInput.setBounds(MainFrame.windwowWidth* 4 / 100, 150+5 - MainFrame.windwowHeight * 5 / 100 + MainFrame.windwowHeight * 10 / 100, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 4 / 100);
		passwordInput.setFont(IDFont);
		
		this.add(setVisiblity);
		this.add(passwordField);
		this.add(IDInput);
		this.add(welcome);
		this.add(idField);
		this.add(signUp);
		this.add(passwordInput);
	}
	
	public JButton getSignUpButton() {
		return signUp;
	}
	
	public TextField getIDField() {
		return idField;
	}
	
}
