package com.dale.login;

import java.awt.Color;

import javax.swing.*;

public class HomePanel extends JPanel {
	private JButton signUp;
	
	HomePanel(){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.setBounds(0, 0, MainFrame.windwowWidth, MainFrame.windwowHeight);
		this.setBackground(Color.WHITE);
	}
	
	public void createPanel() {
		signUp = new JButton("회원가입");
		signUp.setSize(400, 55);
		signUp.setAlignmentX(CENTER_ALIGNMENT);
		signUp.setBackground(Color.ORANGE);
		this.add(signUp);
	}
	
	public JButton getSignUpButton() {
		return signUp;
	}
}
