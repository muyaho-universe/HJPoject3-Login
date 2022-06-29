package com.dale.login.signup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

import com.dale.login.MainFrame;
import com.dale.login.buttons.RoundButton;

public class SpecialPanel extends JPanel {
	RoundButton toHome;
	
	Font arialBoldFont = new Font("Arial", Font.BOLD, 50);
	Font arialFont = new Font("Arial", Font.CENTER_BASELINE, 20);
	Font IDFont = new Font("Arial", Font.CENTER_BASELINE, 15);
	Font smallFont = new Font("Arial", Font.CENTER_BASELINE, 10);
	
	public SpecialPanel() {
		this.setBounds(0, 0, MainFrame.windwowWidth, MainFrame.windwowHeight);
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		JLabel cong = new JLabel("  Welcome!");
		cong.setFont(arialBoldFont);
		cong.setForeground(new Color(61,205,91));
		cong.setBounds(0, getHeight()/2, MainFrame.windwowWidth, 50);
		
		
		toHome = new RoundButton("To Home!");
		toHome.setFont(arialBoldFont);
		
		toHome.setBounds(MainFrame.windwowWidth * 4/ 100 + 25, MainFrame.windwowHeight - 200, 230, 105);
		
		this.add(cong);
		this.add(toHome,BorderLayout.SOUTH);
	}
	
	public RoundButton getToHome() {
		return toHome;
	}
}
