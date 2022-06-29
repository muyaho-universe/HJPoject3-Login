package com.dale.login.signup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

import com.dale.login.MainFrame;

public class SpecialPanel extends JPanel {
	
	Font arialBoldFont = new Font("Arial", Font.BOLD, 50);
	Font arialFont = new Font("Arial", Font.CENTER_BASELINE, 20);
	Font IDFont = new Font("Arial", Font.CENTER_BASELINE, 15);
	Font smallFont = new Font("Arial", Font.CENTER_BASELINE, 10);
	
	public SpecialPanel() {
		this.setBounds(0, 0, MainFrame.windwowWidth, MainFrame.windwowHeight);
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		JLabel cong = new JLabel("Welcome!");
		cong.setFont(arialBoldFont);
		cong.setForeground(new Color(61,205,91));
		this.add(cong, BorderLayout.CENTER);
	}
}
