package com.dale.login.user;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

import com.dale.login.MainFrame;
import com.dale.login.data.MyData;

public class UserPanel extends JPanel {
	Font arialBoldFont = new Font("Arial", Font.BOLD, 30);
	Font arialFont = new Font("Arial", Font.CENTER_BASELINE, 20);
	Font IDFont = new Font("Arial", Font.CENTER_BASELINE, 15);
	
	private JLabel label;
	private static JLabel idFieldLabel = new JLabel("Your ID:");
	private static JLabel passwordFieldLabel = new JLabel("Your password: ");
	private static JLabel nameFieldLabel = new JLabel("Your name: ");
	private static JLabel genderFieldLabel =  new JLabel("Your gender: ");
	private static JLabel phoneNumberFieldLabel = new JLabel("Your phone number: ");
	private static JLabel birthdayFieldLabel = new JLabel("Your birthday: ");
	
	public UserPanel() {
		this.setLayout(null);
		this.setBounds(0, 0, MainFrame.windwowWidth, MainFrame.windwowHeight);
		this.setBackground(Color.WHITE);		
	}
	 
	public void createPanel() {
		label = new JLabel("Welcom, " + MyData.loadedData.get(0).getU_id());
		label.setFont(arialBoldFont);
		label.setForeground(new Color(61,205,91));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBounds(MainFrame.windwowWidth* 4 / 100, 30, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		
		idFieldLabel.setFont(IDFont);
		
		
		this.add(label);
		this.add(idFieldLabel);
		this.add(passwordFieldLabel);
		this.add(nameFieldLabel);
		this.add(genderFieldLabel);
		this.add(phoneNumberFieldLabel);
		this.add(birthdayFieldLabel);
	 }
}
