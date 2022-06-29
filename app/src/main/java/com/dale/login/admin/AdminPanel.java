package com.dale.login.admin;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.dale.login.MainFrame;

public class AdminPanel extends JPanel {
	public AdminPanel(){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBounds(0, 0, MainFrame.windwowWidth, MainFrame.windwowHeight);
		this.setBackground(Color.PINK);
	}
}
