package com.dale.login.signup;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.dale.login.MainFrame;

public class SignUpPanel extends JPanel {
	
	public SignUpPanel(){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBounds(0, 0, MainFrame.windwowWidth, MainFrame.windwowHeight);
	}
}
