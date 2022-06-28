package com.dale.login;

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.dale.login.admin.*;
import com.dale.login.signup.*;

import javax.swing.*;


public class MainFrame extends JFrame {
	public static int monitorWidth;
	public static int monitorHeight;
	public static int windwowWidth;
	public static int windwowHeight;
	private static final String TITLE = "로그인 모듈";
	
	private HomePanel homePanel = null;
	private AdminPanel adminPanel = null;
	private SignUpPanel signUpPanel = null;
	
	public static void main(String[] args) {
		MainFrame mainFrame = new MainFrame();
		mainFrame.run();
	}
	MainFrame(){
		super(TITLE);
		Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
		
		monitorWidth = resolution.width;
		monitorHeight = resolution.height;
		
		windwowHeight = monitorHeight-60;
		windwowWidth = windwowHeight * 105 / 216;
		
	}
	private void run() {
		homePanel = new HomePanel();
		adminPanel = new AdminPanel();
		signUpPanel = new SignUpPanel();
		
		homePanel.createPanel();
		
		
		this.homePanel.getSignUpButton().addActionListener(new ToSignUp());
		
		this.add(homePanel);
		this.setSize(windwowWidth, windwowHeight);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
	}
	
	
	
	class ToSignUp implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			MainFrame.this.getContentPane().removeAll();
			MainFrame.this.getContentPane().add(signUpPanel);
			revalidate();
			repaint();
		}
		
	}
}
