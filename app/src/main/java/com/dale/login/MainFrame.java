package com.dale.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.dale.login.admin.*;
import com.dale.login.buttons.RoundButton;
import com.dale.login.data.MyData;
import com.dale.login.lostandfound.FindingIDPanel;
import com.dale.login.lostandfound.FindingPasswordPane;
import com.dale.login.signup.*;
import com.dale.login.sql.SQLHandler;
import com.dale.login.user.UserPanel;

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
	private SpecialPanel special = null;
	private UserPanel userpanel = null;
	private FindingIDPanel findingIDPanel = null;
	private FindingPasswordPane findingPasswordPane = null;
	JFrame notExistedFrame = null;
	JFrame wrongPasswordFrame = null;
	JFrame lackOfInformationFrame = null;
	
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
		
		signUpPanel = new SignUpPanel();
		special = new SpecialPanel();
		userpanel = new UserPanel();
		homePanel.createPanel();
		userpanel.createLoginAndSignout();
		findingIDPanel = new FindingIDPanel();
		findingPasswordPane = new FindingPasswordPane();
		
		this.homePanel.getSignUpButton().addActionListener(new ToSignUp());
		this.homePanel.getLoginButton().addActionListener(new LoginProcedure());
		this.homePanel.getFindingID().addActionListener(new ToIDFinder());
		this.homePanel.getFindingPassword().addActionListener(new ToPWFinder());
		this.signUpPanel.getSignUpButton().addActionListener(new SignUp());
		this.signUpPanel.getGoToBackButton().addActionListener(new ToHome());
		this.special.getToHome().addActionListener(new ToHome());
		this.userpanel.getLognoutButton().addActionListener(new Logout());
		this.userpanel.getSignoutButton().addActionListener(new Signout());
		this.findingIDPanel.getCancelButton().addActionListener(new FromFinderToHome());
		this.findingIDPanel.getFindButton().addActionListener(new IDFinding());
		this.findingPasswordPane.getFind().addActionListener(new PWFinding());
		this.findingPasswordPane.getCancel().addActionListener(new FromFinderToHome());
		
//		this.add(special);
		
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
			MainFrame.this.homePanel.getIDField().setText(" ");
			MainFrame.this.homePanel.getPasswordField().setText(" ");
			MainFrame.this.getContentPane().removeAll();
			MainFrame.this.getContentPane().add(signUpPanel);
			revalidate();
			repaint();
		}
		
	}
	
	class ToPWFinder implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			MainFrame.this.homePanel.getIDField().setText("");
			MainFrame.this.homePanel.getPasswordField().setText("");
			MainFrame.this.getContentPane().removeAll();
			MainFrame.this.getContentPane().add(findingPasswordPane);
			revalidate();
			repaint();
		}
		
	}
	
	class ToHome implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			MainFrame.this.homePanel.getIDField().setText("");
			MainFrame.this.homePanel.getPasswordField().setText("");
			MainFrame.this.signUpPanel.getIDField().setText("");
			MainFrame.this.signUpPanel.getPasswordField().setText("");
			MainFrame.this.getContentPane().removeAll();
			MainFrame.this.getContentPane().add(homePanel);
			revalidate();
			repaint();
		}
		
	}
	
	class FromFinderToHome implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			MainFrame.this.findingIDPanel.getNameField().setText("");
			MainFrame.this.findingIDPanel.getPhoneField().setText("");
			MainFrame.this.getContentPane().removeAll();
			MainFrame.this.getContentPane().add(homePanel);
			revalidate();
			repaint();
		}
		
	}
	
	class ToIDFinder implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			MainFrame.this.getContentPane().removeAll();
			MainFrame.this.getContentPane().add(findingIDPanel);
			revalidate();
			repaint();
		}
		
	}
	
	class Logout implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame logoutConfirm = new JFrame();
			logoutConfirm.setVisible(true);
			logoutConfirm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			logoutConfirm.setLayout(new BorderLayout());
			logoutConfirm.setSize(400, 200);
			JLabel message = new JLabel("Are you sure?");
			message.setFont(arialBoldFont);
			logoutConfirm.add(message, BorderLayout.CENTER);
			JButton yes = new JButton("Yes");
			JButton no = new JButton("No");
			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new FlowLayout());
			yes.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					MyData.loadedData.clear();
					MainFrame.this.getContentPane().removeAll();
					MainFrame.this.getContentPane().add(homePanel);
					revalidate();
					repaint();
					logoutConfirm.dispose();
				}
				
			});
			no.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					logoutConfirm.dispose();
				}
				
			});
			yes.setBackground(Color.WHITE);
			no.setBackground(Color.GRAY);
			buttonPanel.add(yes);
			buttonPanel.add(no);
			logoutConfirm.add(buttonPanel, BorderLayout.SOUTH);
		}
		
	}
	
	class Signout implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame signoutConfirm = new JFrame();
			signoutConfirm.setVisible(true);
			signoutConfirm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			signoutConfirm.setLayout(new BorderLayout());
			signoutConfirm.setSize(400, 200);
			JLabel message = new JLabel("We will miss you");
			message.setFont(arialBoldFont);
			signoutConfirm.add(message, BorderLayout.CENTER);
			JButton yes = new JButton("Yes");
			JButton no = new JButton("No");
			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new FlowLayout());
			yes.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println(MyData.loadedData.get(0).getU_id());
					SQLHandler.signOut(MyData.loadedData.get(0).getU_id());
					MyData.loadedData.clear();
					MainFrame.this.getContentPane().removeAll();
					MainFrame.this.getContentPane().add(homePanel);
					revalidate();
					repaint();
					signoutConfirm.dispose();
				}
				
			});
			no.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					signoutConfirm.dispose();
				}
				
			});
			yes.setFont(arialFont);
			yes.setBackground(Color.WHITE);
			no.setBackground(Color.GRAY);
			no.setFont(arialFont);
			buttonPanel.add(yes);
			buttonPanel.add(no);
			signoutConfirm.add(buttonPanel, BorderLayout.SOUTH);
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
				notExistedFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				buttonPanel.setSize(40, 5);
			    buttonPanel.setLayout(new FlowLayout());
			    buttonPanel.add(signUp);
			    buttonPanel.add(cancel);
			    signUp.setSize(20, 5);
			    signUp.setFont(IDFont);
			    signUp.addActionListener(new ActionListener(){
			    	@Override
					public void actionPerformed(ActionEvent e) {
			    		notExistedFrame.dispose();
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
						notExistedFrame.dispose();
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
				wrongPasswordFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				buttonPanel.setSize(40, 5);
			    buttonPanel.setLayout(new FlowLayout());
			    buttonPanel.add(toPasswordFinder);
			    buttonPanel.add(cancel);
			    toPasswordFinder.setSize(20, 5);
			    toPasswordFinder.setFont(IDFont);
			    toPasswordFinder.addActionListener(new ActionListener() {
			    	@Override
					public void actionPerformed(ActionEvent e) {
			    		wrongPasswordFrame.dispose();
						MainFrame.this.homePanel.getIDField().setText("");
						MainFrame.this.homePanel.getPasswordField().setText("");
						MainFrame.this.getContentPane().removeAll();
						MainFrame.this.getContentPane().add(findingPasswordPane);
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
						wrongPasswordFrame.dispose();
					}
			    });
			    cancel.setFont(IDFont);
			    
			    wrongPasswordFrame.add(label, BorderLayout.NORTH);
			    wrongPasswordFrame.add(context, BorderLayout.CENTER);
			    wrongPasswordFrame.add(buttonPanel, BorderLayout.SOUTH);
			}
			if(whereToGo == 1) {
				MainFrame.this.homePanel.getIDField().setText("");
				MainFrame.this.homePanel.getPasswordField().setText("");
				MainFrame.this.getContentPane().removeAll();
				userpanel.createPanel();
				MainFrame.this.getContentPane().add(userpanel);
				revalidate();
				repaint();	
			}
			
			if(whereToGo == 2) {
				
				SQLHandler.selectAll();
				MainFrame.this.homePanel.getIDField().setText("");
				MainFrame.this.homePanel.getPasswordField().setText("");
				MainFrame.this.getContentPane().removeAll();
				adminPanel = new AdminPanel();
				MainFrame.this.getContentPane().add(adminPanel);
				adminPanel.drawPanel();
				
				revalidate();
				repaint();
			}
			System.out.println(whereToGo);
		}
	}
	
	class SignUp implements ActionListener {
//		boolean isNameFilled = !MainFrame.this.signUpPanel.getName().isEmpty();
		boolean isNumberThere = !MainFrame.this.signUpPanel.getPhoneNumberField().getText().isEmpty();
		boolean isBirthdaySelected = !MainFrame.this.signUpPanel.getBirthday().isEmpty();
		@Override
		public void actionPerformed(ActionEvent e) {
			
			System.out.println("MainFrame.this.signUpPanel.isAgreed() "+ MainFrame.this.signUpPanel.isAgreed()+ " MainFrame.this.signUpPanel.isDoesPasswordHaveCapitol() "
					+MainFrame.this.signUpPanel.isDoesPasswordHaveCapitol()+" MainFrame.this.signUpPanel.isDoesPasswordHaveLower() "+MainFrame.this.signUpPanel.isDoesPasswordHaveLower() +" MainFrame.this.signUpPanel.isDoesPasswordHaveNumber() "
					+ MainFrame.this.signUpPanel.isDoesPasswordHaveNumber()+" MainFrame.this.signUpPanel.isGenderSelected() " +MainFrame.this.signUpPanel.isGenderSelected() +" MainFrame.this.signUpPanel.isIDConfirmed() " + MainFrame.this.signUpPanel.isIDConfirmed()
					+ " MainFrame.this.signUpPanel.isIDLongEnough() " + MainFrame.this.signUpPanel.isIDLongEnough() + " isNameFilled "  + " isNumberThere " + isNumberThere + " isBirthdaySelected " + isBirthdaySelected);
			if(MainFrame.this.signUpPanel.isAgreed() && MainFrame.this.signUpPanel.isDoesPasswordHaveCapitol() 
					&& MainFrame.this.signUpPanel.isDoesPasswordHaveLower() && MainFrame.this.signUpPanel.isDoesPasswordHaveNumber()
					&&MainFrame.this.signUpPanel.isGenderSelected() && MainFrame.this.signUpPanel.isIDConfirmed()
					&&MainFrame.this.signUpPanel.isIDLongEnough() && !MainFrame.this.signUpPanel.getName().isEmpty() &&isNumberThere &&isBirthdaySelected) {
				String password = new String(MainFrame.this.signUpPanel.getPasswordField().getPassword());
				SQLHandler.insert(MainFrame.this.signUpPanel.getIDField().getText(), password, MainFrame.this.signUpPanel.getName(), MainFrame.this.signUpPanel.getGender(), MainFrame.this.signUpPanel.getPhoneNumberField().getText(), MainFrame.this.signUpPanel.getBirthday());
				MainFrame.this.getContentPane().removeAll();
				MainFrame.this.getContentPane().add(special);
				revalidate();
				repaint();
			}
			else{
				lackOfInformationFrame = new JFrame();
				MainFrame.this.setEnabled(false);
				lackOfInformationFrame.setLayout(new BorderLayout());
				lackOfInformationFrame.setSize(windwowWidth + 300, 200);
				
				JLabel label = new JLabel("Lack of Information");
				JLabel context = new JLabel("You missed some information. Try again!");
				
				JButton ok = new JButton("I understan");
				JPanel buttonPanel = new JPanel();
				
				label.setFont(arialBoldFont);
				context.setFont(arialFont);
								
				
				lackOfInformationFrame.setVisible(true);
				lackOfInformationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				buttonPanel.setSize(40, 5);
			    buttonPanel.setLayout(new FlowLayout());
			    buttonPanel.add(ok);
			    
			    ok.setSize(20, 5);
			    ok.addActionListener(new ActionListener(){
			    	@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						MainFrame.this.setEnabled(true);
						lackOfInformationFrame.dispose();
					}
			    });
			    ok.setFont(IDFont);
			    
			    lackOfInformationFrame.add(label, BorderLayout.NORTH);
			    lackOfInformationFrame.add(context, BorderLayout.CENTER);
			    lackOfInformationFrame.add(buttonPanel, BorderLayout.SOUTH);
			}
		}
	}
	class IDFinding implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String foundId = SQLHandler.findID(findingIDPanel.getNameField().getText(), findingIDPanel.getPhoneField().getText());
		
			if(foundId.equals("null")) {
				JFrame notExisted = new JFrame();
				notExisted.setSize(300, 200);
				notExisted.setLayout(new BorderLayout());
				
				JLabel message = new JLabel("Cannot find ID :( ");
				message.setFont(arialBoldFont);
				
				RoundButton ok = new RoundButton("OK");
				ok.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						notExisted.dispose();							
					}
					
				});
				notExisted.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				notExisted.add(ok,BorderLayout.SOUTH);
				notExisted.add(message, BorderLayout.CENTER);
				notExisted.setVisible(true);
			}
			else {
				JFrame existed = new JFrame();
				existed.setSize(300, 200);
				existed.setLayout(new BorderLayout());
				existed.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				existed.setVisible(true);
				JPanel messagePanel = new JPanel();
				messagePanel.setLayout(new FlowLayout());
				
				JLabel message = new JLabel("ID Found! It's ");
				JLabel message2 = new JLabel(foundId);
				message2.setForeground(new Color(61,205,91));
				message2.setFont(arialBoldFont);
				JLabel message3 = new JLabel("!");
				
				messagePanel.setFont(arialBoldFont);
				messagePanel.add(message);
				messagePanel.add(message2);
				messagePanel.add(message3);
				existed.add(messagePanel, BorderLayout.CENTER);
				
				JPanel buttonPanel = new JPanel();
				buttonPanel.setLayout(new FlowLayout());
				RoundButton toLogin = new RoundButton("To Login");
				toLogin.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						existed.dispose();
						MainFrame.this.findingIDPanel.getNameField().setText("");
						MainFrame.this.findingIDPanel.getPhoneField().setText("");
						MainFrame.this.homePanel.getIDField().setText(foundId);
						MainFrame.this.getContentPane().removeAll();
						MainFrame.this.getContentPane().add(homePanel);
						revalidate();
						repaint();
					}
					
				});
				RoundButton toPasswordFinder = new RoundButton("To Password Finder", Color.WHITE);
				toPasswordFinder.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						existed.dispose();
						MainFrame.this.findingIDPanel.getNameField().setText("");
						MainFrame.this.findingIDPanel.getPhoneField().setText("");
						MainFrame.this.homePanel.getIDField().setText(foundId);
						MainFrame.this.findingPasswordPane.getIdField().setText(foundId);
						MainFrame.this.getContentPane().removeAll();
						MainFrame.this.getContentPane().add(findingPasswordPane);
						revalidate();
						repaint();
						
					}
					
				});
				buttonPanel.add(toPasswordFinder);
				buttonPanel.add(toLogin);
				existed.add(buttonPanel, BorderLayout.SOUTH);
			}
		}
		
	}
	class PWFinding implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String foundPassword = SQLHandler.findPassword(findingPasswordPane.getIdField().getText(),findingPasswordPane.getNameField().getText(), findingPasswordPane.getPhoneField().getText());
		
			if(foundPassword.equals("null")) {
				JFrame notExisted = new JFrame();
				notExisted.setSize(300, 200);
				notExisted.setLayout(new BorderLayout());
				
				JLabel message = new JLabel("Cannot find Password :( ");
				message.setFont(arialBoldFont);
				
				RoundButton ok = new RoundButton("OK");
				ok.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						notExisted.dispose();							
					}
					
				});
				notExisted.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				notExisted.add(ok,BorderLayout.SOUTH);
				notExisted.add(message, BorderLayout.CENTER);
				notExisted.setVisible(true);
			}
			else {
				JFrame existed = new JFrame();
				existed.setSize(300, 200);
				existed.setLayout(new BorderLayout());
				existed.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				existed.setVisible(true);
				JPanel messagePanel = new JPanel();
				messagePanel.setLayout(new FlowLayout());
				
				JLabel message = new JLabel("Password Found! It's ");
				JLabel message2 = new JLabel(foundPassword);
				message2.setForeground(new Color(61,205,91));
				message2.setFont(arialBoldFont);
				JLabel message3 = new JLabel("!");
				
				messagePanel.setFont(arialBoldFont);
				messagePanel.add(message);
				messagePanel.add(message2);
				messagePanel.add(message3);
				existed.add(messagePanel, BorderLayout.CENTER);
				
				JPanel buttonPanel = new JPanel();
				buttonPanel.setLayout(new FlowLayout());
				RoundButton toLogin = new RoundButton("To Login");
				toLogin.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						existed.dispose();
						MainFrame.this.findingPasswordPane.getNameField().setText("");
						MainFrame.this.findingPasswordPane.getPhoneField().setText("");
						MainFrame.this.homePanel.getPasswordField().setText(foundPassword);
						MainFrame.this.getContentPane().removeAll();
						MainFrame.this.getContentPane().add(homePanel);
						revalidate();
						repaint();
					}
					
				});
				
				buttonPanel.add(toLogin);
				existed.add(buttonPanel, BorderLayout.SOUTH);
			}
		}
		
	}
}
