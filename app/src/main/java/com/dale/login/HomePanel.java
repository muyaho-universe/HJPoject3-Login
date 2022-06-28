package com.dale.login;

import java.awt.*;

import javax.swing.*;

import com.dale.login.buttons.DesignedButton;

public class HomePanel extends JPanel {
	private DesignedButton signUp;
	private TextField idField;
	private JLabel label;
	
	HomePanel(){
		this.setLayout(null);
		
		this.setBounds(0, 0, MainFrame.windwowWidth, MainFrame.windwowHeight);
		this.setBackground(Color.WHITE);
	}
	
	public void createPanel() {
		Font font = new Font("Arial", Font.BOLD, 25);
		label = new JLabel("환영합니다.");
		idField = new TextField();
		signUp = new DesignedButton("Sign up");
		
		signUp.setBounds(MainFrame.windwowWidth* 4 / 100, MainFrame.windwowHeight - 150, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 8 / 100);
		signUp.setFont(font);
		
		
		idField.setBounds(MainFrame.windwowWidth* 4 / 100, 150, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		
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
