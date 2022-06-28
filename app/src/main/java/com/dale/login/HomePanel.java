package com.dale.login;

import java.awt.*;

import javax.swing.*;

import com.dale.login.buttons.DesignedButton;

public class HomePanel extends JPanel {
	private DesignedButton signUp;
	private TextField idField;
	
	HomePanel(){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.setBounds(0, 0, MainFrame.windwowWidth, MainFrame.windwowHeight);
		this.setBackground(Color.WHITE);
	}
	
	public void createPanel() {
		idField = new TextField();
		signUp = new DesignedButton("회원가입");
		
		signUp.setAlignmentX(CENTER_ALIGNMENT);
		signUp.setBackground(Color.ORANGE);
		this.add(idField);
		this.add(signUp);
	}
	
	public JButton getSignUpButton() {
		return signUp;
	}
	
	public TextField getIDField() {
		return idField;
	}
	
}
