package com.dale.login;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.dale.login.admin.*;
import com.dale.login.data.MyData;
import com.dale.login.signup.*;
import com.dale.login.sql.SQLHandler;

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
	JFrame notExistedFrame = null;
	JFrame wrongPasswordFrame = null;
	
	private int whereToGo = 0;
	
	Font arialBoldFont = new Font("Arial", Font.BOLD, 30);
	Font arialFont = new Font("Arial", Font.CENTER_BASELINE, 20);
	Font IDFont = new Font("Arial", Font.CENTER_BASELINE, 15);
	
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
		this.homePanel.getLoginButton().addActionListener(new LoginProcedure());
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
	
	class LoginProcedure implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String password = new String(homePanel.getPasswordField().getPassword());
			MyData.loginData.put(homePanel.getIDField().getText(), password);
			whereToGo = SQLHandler.loginSelect(homePanel.getIDField().getText(), password);
			System.out.println("123 "+password);
			
			if(whereToGo == 0) {
				MainFrame.this.setEnabled(false);
				notExistedFrame = new JFrame();
				notExistedFrame.setLayout(new BorderLayout());
				notExistedFrame.setSize(windwowWidth+ 300, 200);
				
				JLabel label = new JLabel("ID doesn't exist!");
				JLabel context = new JLabel("It seems ID doesn't exit. Try again, or Sign up now!");
				
				JButton signUp = new JButton("To Sign Up");
				JButton cancel = new JButton("Try again");
				JPanel buttonPanel = new JPanel();
				
				label.setFont(arialBoldFont);
				context.setFont(arialFont);
								
				
				notExistedFrame.setVisible(true);
				notExistedFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				buttonPanel.setSize(40, 5);
			    buttonPanel.setLayout(new FlowLayout());
			    buttonPanel.add(signUp);
			    buttonPanel.add(cancel);
			    signUp.setSize(20, 5);
			    signUp.setFont(IDFont);
			    signUp.addActionListener(new ActionListener(){
			    	@Override
					public void actionPerformed(ActionEvent e) {
			    		notExistedFrame.hide();
			    		MainFrame.this.setEnabled(true);
						MainFrame.this.getContentPane().removeAll();
						MainFrame.this.getContentPane().add(signUpPanel);
						revalidate();
						repaint();
					}
			    });
			    
			    cancel.setSize(20, 5);
			    cancel.addActionListener(new ActionListener(){
			    	@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						MainFrame.this.setEnabled(true);
						notExistedFrame.hide();
					}
			    });
			    cancel.setFont(IDFont);
			    
			    notExistedFrame.add(label, BorderLayout.NORTH);
			    notExistedFrame.add(context, BorderLayout.CENTER);
			    notExistedFrame.add(buttonPanel, BorderLayout.SOUTH);
			}
			if(whereToGo == -1) {
				MainFrame.this.setEnabled(false);
				wrongPasswordFrame = new JFrame();
				wrongPasswordFrame.setLayout(new BorderLayout());
				wrongPasswordFrame.setSize(windwowWidth + 300, 200);
				
				JLabel label = new JLabel("Password doesn't match!");
				JLabel context = new JLabel("You may forget your password. Try again, or use password finder!");
				
				JButton toPasswordFinder = new JButton("To Password Finder");
				JButton cancel = new JButton("Try again");
				JPanel buttonPanel = new JPanel();
				
				label.setFont(arialBoldFont);
				context.setFont(arialFont);
								
				
				wrongPasswordFrame.setVisible(true);
				wrongPasswordFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				buttonPanel.setSize(40, 5);
			    buttonPanel.setLayout(new FlowLayout());
			    buttonPanel.add(toPasswordFinder);
			    buttonPanel.add(cancel);
			    toPasswordFinder.setSize(20, 5);
			    toPasswordFinder.setFont(IDFont);
			    toPasswordFinder.addActionListener(new ActionListener(){
			    	@Override
					public void actionPerformed(ActionEvent e) {
			    		wrongPasswordFrame.hide();
			    		MainFrame.this.setEnabled(true);
//						MainFrame.this.getContentPane().removeAll();
//						MainFrame.this.getContentPane().add(signUpPanel);
						revalidate();
						repaint();
					}
			    });
			    
			    cancel.setSize(20, 5);
			    cancel.addActionListener(new ActionListener(){
			    	@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						MainFrame.this.setEnabled(true);
						wrongPasswordFrame.hide();
					}
			    });
			    cancel.setFont(IDFont);
			    
			    wrongPasswordFrame.add(label, BorderLayout.NORTH);
			    wrongPasswordFrame.add(context, BorderLayout.CENTER);
			    wrongPasswordFrame.add(buttonPanel, BorderLayout.SOUTH);
			}
			if(whereToGo == 1) {
//				MainFrame.this.getContentPane().removeAll();
//				MainFrame.this.getContentPane().add(adminPanel);
				revalidate();
				repaint();	
			}
			
			if(whereToGo == 2) {
				MainFrame.this.getContentPane().removeAll();
				MainFrame.this.getContentPane().add(adminPanel);
				revalidate();
				repaint();
			}
			System.out.println(whereToGo);
		}
	}
	
}
