package com.dale.login.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.MaskFormatter;

import com.dale.login.MainFrame;
import com.dale.login.buttons.RoundButton;
import com.dale.login.data.MyData;

public class UserPanel extends JPanel {
	Font arialBoldFont = new Font("Arial", Font.BOLD, 30);
	Font arialFont = new Font("Arial", Font.CENTER_BASELINE, 20);
	Font IDFont = new Font("Arial", Font.CENTER_BASELINE, 15);
	Font smallFont = new Font("Arial", Font.CENTER_BASELINE, 10);
	
	private JLabel label;
	private static JLabel idFieldLabel = new JLabel("Your ID:");
	private static JLabel passwordFieldLabel = new JLabel("Your password: ");
	private static JLabel nameFieldLabel = new JLabel("Your name: ");
	private static JLabel genderFieldLabel =  new JLabel("Your gender: ");
	private static JLabel phoneNumberFieldLabel = new JLabel("Your phone number: ");
	private static JLabel birthdayFieldLabel = new JLabel("Your birthday: ");
	private JLabel passwordConfirmCheck = null;
	private JLabel passwordCheck = null;
	private JToggleButton male;
	private JToggleButton female;
	
	private ButtonGroup buttonGroup;
	private Container container;
	
	
	private TextField idField;
	private JPasswordField passwordField;
	private TextField nameField;
	private JPanel togglePanel;
	private JFormattedTextField phoneNumberField;
	private JFormattedTextField birthdayField;
	
	private RoundButton setVisiblityOnPasswordField;
	private RoundButton editPasswordButton;
	private RoundButton passwordEditGoButton;
	private RoundButton passwordEditCancelButton;
	private RoundButton nameEditButton;
	private RoundButton genderEditButton;
	private RoundButton phoneNumberEditButton;
	private RoundButton birthdayEditButton;
	private RoundButton saveButton;
	private RoundButton logoutButton;
	private RoundButton signoutButton;
	
	private boolean isVisiblePasswordField = false;
	private boolean doesPasswordHaveNumber = false;
	private boolean doesPasswordHaveCapitol = false;
	private boolean doesPasswordHaveLower = false;
	private boolean isPasswordLongEnough = false;
	private boolean isPasswordSame = false;
	private boolean isPasswordChanged = false;
	private boolean isNameEdittedButtonPressed = false;
	private boolean isNameEditted = false;
	private boolean isGenderEdittedButtonPressed = false;
	private boolean isPhoneNumberEdittedButtonPressed = false;
	private boolean isBirthdayEdittedButtonPressed = false;
	private boolean isGenderChanged = false;
	
	private String enteredCurrentPassword = null;
	private String changedPassword = null;
	private String gender;
	
	
	private JFrame passwordEditFrame;
	private RoundButton setVisiblityOnCurrentPasswordField ;
	private boolean isVisibleCurrentPasswordField =false;
	private RoundButton setVisiblityOnChangePasswordField ;
	private boolean isVisibleChangePasswordField =false;
	private RoundButton setVisiblityOnChangeConfirmPasswordField ;
	private boolean isVisibleChangeConfirmPasswordField =false;
	
	private MaskFormatter formatter;
	private MaskFormatter dateFormatter;
	
	public UserPanel() {
		this.setLayout(null);
		this.setBounds(0, 0, MainFrame.windwowWidth, MainFrame.windwowHeight);
		this.setBackground(Color.WHITE);		
	}
	 
	public void createPanel() {
		label = new JLabel("Welcom, " + MyData.loadedData.get(0).getU_id());
		label.setFont(arialBoldFont);
		label.setForeground(new Color(61,205,91));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBounds(MainFrame.windwowWidth* 4 / 100, 30, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		
		idFieldLabel.setFont(IDFont);
		idFieldLabel.setBounds(MainFrame.windwowWidth* 4 / 100, 85, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		idField = new TextField(MyData.loadedData.get(0).getU_id());
		idField.setBounds(MainFrame.windwowWidth* 4 / 100, 120, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		idField.setFont(arialFont);
		idField.setEnabled(false);
		
		passwordFieldLabel.setFont(IDFont);
		passwordFieldLabel.setBounds(MainFrame.windwowWidth* 4 / 100, 165, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		passwordField = new JPasswordField(MyData.loadedData.get(0).getPassword());
		passwordField.setBounds(MainFrame.windwowWidth* 4 / 100, 190, MainFrame.windwowWidth * 88 / 100- 120, MainFrame.windwowHeight * 5 / 100);
		passwordField.setEchoChar('*');
		passwordField.setEnabled(false);
		setVisiblityOnPasswordField = new RoundButton("Show");
		setVisiblityOnPasswordField.setBounds(MainFrame.windwowWidth * 88 / 100-100, 190, 50, MainFrame.windwowHeight * 5 / 100);
		setVisiblityOnPasswordField.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(isVisiblePasswordField) {
					setVisiblityOnPasswordField.setColor("Show", new Color(61,205,91));
					passwordField.setEchoChar('*');
					isVisiblePasswordField = false;
				}
				else {
					setVisiblityOnPasswordField.setColor("Hide", Color.GRAY);
					passwordField.setEchoChar((char)0);
					
					isVisiblePasswordField = true;
				}
			}
		});
		editPasswordButton= new RoundButton("Edit");
		editPasswordButton.setBounds(MainFrame.windwowWidth * 88 / 100-40, 190, 50, MainFrame.windwowHeight * 5 / 100);
		editPasswordButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				passwordEditFrame = new JFrame();
				passwordEditFrame.setSize(MainFrame.windwowWidth+ 300, 350);
				passwordEditFrame.setVisible(true);
				passwordEditFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				UserPanel.this.setEnabled(false);
				
				passwordEditFrame.setLayout(null);
				
				JLabel currentPasswordLabel = new JLabel("Enter your currnet password: ");
				JLabel changePasswordLabel = new JLabel("Enter your new password: ");
				JLabel passwordConfirmFieldLabel = new JLabel("Reenter your new password: ");
				
				
				JPasswordField currentPassword = new JPasswordField();
				JPasswordField changePassword = new JPasswordField();
				JPasswordField changePasswordConfirm = new JPasswordField();
				
				
				currentPasswordLabel.setFont(IDFont);
				currentPasswordLabel.setBounds(15, 5, 300, 25);
				currentPassword.setBounds(15, 35, 300, 30);
				currentPassword.setFont(arialFont);
				currentPassword.setEchoChar('*');
				currentPassword.addCaretListener(new CaretListener() {
					@Override
					public void caretUpdate(CaretEvent e) {
						enteredCurrentPassword = new String(currentPassword.getPassword());
						if(enteredCurrentPassword.isEmpty()) {
							passwordEditGoButton.setColor("Change", Color.GRAY);
							passwordEditGoButton.setEnabled(false);
						}
					}
				});
				setVisiblityOnCurrentPasswordField = new RoundButton("Show");
				setVisiblityOnCurrentPasswordField.setBounds(320, 35, 50, 30);
				setVisiblityOnCurrentPasswordField.addActionListener(new ActionListener (){
					@Override
					public void actionPerformed(ActionEvent e) {
						if(isVisibleCurrentPasswordField) {
							setVisiblityOnCurrentPasswordField.setColor("Show", new Color(61,205,91));
							currentPassword.setEchoChar('*');
							isVisibleCurrentPasswordField = false;
						}
						else {
							setVisiblityOnCurrentPasswordField.setColor("Hide", Color.GRAY);
							currentPassword.setEchoChar((char)0);			
							isVisibleCurrentPasswordField = true;
						}
					}
				});
				
				changePasswordLabel.setFont(IDFont);
				changePasswordLabel.setBounds(15, 80, 300, 25);
				changePassword.setBounds(15, 110, 300, 30);
				changePassword.setFont(arialFont);
				changePassword.setEchoChar('*');
				passwordCheck = new JLabel();
				changePassword.addCaretListener(new CaretListener() {
					@Override
					public void caretUpdate(CaretEvent e) {
						String temp =new String(changePassword.getPassword());
						char[] charTemp = temp.toCharArray();
						if(temp.length() >= 8) {
							isPasswordLongEnough = true;
						}
						else {
							isPasswordLongEnough = false;
							passwordCheck.setText("8 characters or More");
							passwordEditGoButton.setColor("Change", Color.GRAY);
							passwordEditGoButton.setEnabled(false);
						}
						for(char c: charTemp) {
							if( 48 <= c && c <= 57) {
								doesPasswordHaveNumber = true;
							}
							if( 65 <= c && c <= 90) {
								doesPasswordHaveCapitol = true;
							}
							if( 97 <= c && c <= 122) {
								doesPasswordHaveLower = true;
							}
						}
						if(isPasswordLongEnough) {
							passwordCheck.setText("You should include at least one upper, one lower, and one number in password");
							if(doesPasswordHaveNumber && doesPasswordHaveCapitol && doesPasswordHaveLower) {
								passwordCheck.setText("Good to GO~~!!");
								passwordCheck.setForeground(new Color(61,205,91));
								if(isPasswordSame) {
									passwordEditGoButton.setColor("Change", new Color(61,205,91));
									passwordEditGoButton.setEnabled(true);
								}
							}
							else {
								doesPasswordHaveNumber = false;
								doesPasswordHaveCapitol = false;
								doesPasswordHaveLower = false;
								passwordCheck.setForeground(Color.RED);
								passwordEditGoButton.setColor("Change", Color.GRAY);
								passwordEditGoButton.setEnabled(false);
							}
						}
						else {
							doesPasswordHaveNumber = false;
							doesPasswordHaveCapitol = false;
							doesPasswordHaveLower = false;
							isPasswordSame = false;
							passwordCheck.setForeground(Color.RED);
							passwordEditGoButton.setColor("Change", Color.GRAY);
							passwordEditGoButton.setEnabled(false);
						}
					}
				});
				setVisiblityOnChangePasswordField = new RoundButton("Show");
				setVisiblityOnChangePasswordField.setBounds(320, 110, 50, 30);
				setVisiblityOnChangePasswordField.addActionListener(new ActionListener (){
					@Override
					public void actionPerformed(ActionEvent e) {
						if(isVisibleChangePasswordField) {
							setVisiblityOnChangePasswordField.setColor("Show", new Color(61,205,91));
							changePassword.setEchoChar('*');
							isVisibleChangePasswordField = false;
						}
						else {
							setVisiblityOnChangePasswordField.setColor("Hide", Color.GRAY);
							changePassword.setEchoChar((char)0);			
							isVisibleChangePasswordField = true;
						}
					}
				});
				passwordCheck.setBounds(15, 135, 300, 30);
				passwordCheck.setFont(smallFont);
				
				passwordConfirmFieldLabel.setFont(IDFont);
				passwordConfirmFieldLabel.setBounds(15, 155, 300, 30);
				changePasswordConfirm.setBounds(15, 185, 300, 30);
				changePasswordConfirm.setFont(arialFont);
				changePasswordConfirm.setEchoChar('*');
				setVisiblityOnChangeConfirmPasswordField = new RoundButton("Show");
				setVisiblityOnChangeConfirmPasswordField.setBounds(315, 185, 50, 30);
				setVisiblityOnChangeConfirmPasswordField.addActionListener(new ActionListener (){
					@Override
					public void actionPerformed(ActionEvent e) {
						if(isVisibleChangeConfirmPasswordField) {
							setVisiblityOnChangeConfirmPasswordField.setColor("Show", new Color(61,205,91));
							changePasswordConfirm.setEchoChar('*');
							isVisibleChangeConfirmPasswordField = false;
						}
						else {
							setVisiblityOnChangeConfirmPasswordField.setColor("Hide", Color.GRAY);
							changePasswordConfirm.setEchoChar((char)0);
							isVisibleChangeConfirmPasswordField = true;
						}
					}
				});
				passwordConfirmCheck =new JLabel("");
				passwordConfirmCheck.setFont(smallFont);
				passwordConfirmCheck.setBounds(15, 210, 300, 30);
				changePasswordConfirm.addCaretListener(new CaretListener(){
					String password ;
					String passwordConfirmed; 
					@Override
					public void caretUpdate(CaretEvent e) {
						if(isPasswordLongEnough && doesPasswordHaveNumber && doesPasswordHaveCapitol && doesPasswordHaveLower) {
							password = new String(changePassword.getPassword());
							passwordConfirmed = new  String(changePasswordConfirm.getPassword());
							System.out.println(password + " " + passwordConfirmed + " " +password.equals(passwordConfirmed));
							if(password.equals(passwordConfirmed)) {
								passwordConfirmCheck.setText("Password Confirmed");
								passwordConfirmCheck.setForeground(new Color(61,205,91));
								isPasswordSame = true;
								if(currentPassword.getPassword() != null) {
									passwordEditGoButton.setColor("Change", new Color(61,205,91));
									passwordEditGoButton.setEnabled(true);
								}
								else {
									passwordEditGoButton.setColor("Change", Color.GRAY);
									passwordEditGoButton.setEnabled(false);
								}
							}
							else {
								isPasswordSame =false;
								passwordConfirmCheck.setText("Password not Matched");
								passwordConfirmCheck.setForeground(Color.RED);
								passwordEditGoButton.setColor("Change", Color.GRAY);
								passwordEditGoButton.setEnabled(false);
							}
						}
						else {
							passwordConfirmCheck.setText("Check the pass word first");
							passwordConfirmCheck.setForeground(Color.RED);
							isPasswordSame = false;
							passwordEditGoButton.setColor("Change", Color.GRAY);
							passwordEditGoButton.setEnabled(false);
						}
					}
				});
				
				JPanel buttonPanel= new JPanel();
				buttonPanel.setLayout(new FlowLayout());
				passwordEditGoButton = new RoundButton("Change", Color.GRAY);
				passwordEditGoButton.setFont(IDFont);
				passwordEditGoButton.setEnabled(false);
				passwordEditGoButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						String realPassword = new String(passwordField.getPassword());
						if(realPassword.equals(enteredCurrentPassword)) {
							changedPassword = new String(changePassword.getPassword());
							JFrame passwordEditSuccess = new JFrame();
							JLabel gooodGood = new JLabel("Password Changed");
							RoundButton nice = new RoundButton("Nice");
							gooodGood.setFont(arialBoldFont);
							nice.setFont(arialFont);
							nice.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									String temp = new String(changePassword.getPassword());
									passwordEditSuccess.dispose();
									passwordEditFrame.dispose();
									UserPanel.this.passwordField.setText(temp);
									isPasswordChanged = true;
								}
								
							});
							passwordEditSuccess.setVisible(true);
							passwordEditSuccess.setSize(200, 200);
							passwordEditSuccess.setLayout(new BorderLayout());
							passwordEditSuccess.add(gooodGood, BorderLayout.CENTER);
							passwordEditSuccess.add(nice,BorderLayout.SOUTH );
							passwordEditSuccess.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						}
						else {
							JFrame passwordEditFail = new JFrame();
							JLabel noGood = new JLabel("Try Again");
							RoundButton again = new RoundButton("again");
							noGood.setFont(arialBoldFont);
							again.setFont(arialFont);
							again.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									passwordEditFail.dispose();
								}
								
							});
							passwordEditFail.setVisible(true);
							passwordEditFail.setSize(200, 200);
							passwordEditFail.setLayout(new BorderLayout());
							passwordEditFail.add(noGood, BorderLayout.CENTER);
							passwordEditFail.add(again,BorderLayout.SOUTH );
							passwordEditFail.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						}
					}
					
				});
				passwordEditCancelButton = new RoundButton("Cancel", Color.LIGHT_GRAY);
				passwordEditCancelButton.setFont(IDFont);
				passwordEditCancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						passwordEditFrame.dispose();
					}
					
				});
				
				buttonPanel.add(passwordEditCancelButton);
				buttonPanel.add(passwordEditGoButton);
				buttonPanel.setBounds(15, 250, passwordEditFrame.getWidth()/2, 60);
				
				passwordEditFrame.add(currentPasswordLabel);
				passwordEditFrame.add(currentPassword);
				passwordEditFrame.add(changePasswordLabel);
				passwordEditFrame.add(changePassword);
				passwordEditFrame.add(setVisiblityOnCurrentPasswordField);
				passwordEditFrame.add(setVisiblityOnChangePasswordField);
				passwordEditFrame.add(passwordCheck);
				passwordEditFrame.add(passwordConfirmFieldLabel);
				passwordEditFrame.add(changePasswordConfirm);
				passwordEditFrame.add(setVisiblityOnChangeConfirmPasswordField);
				passwordEditFrame.add(passwordConfirmCheck);
				passwordEditFrame.add(buttonPanel);
			}
			
		});
		
		nameFieldLabel.setBounds(MainFrame.windwowWidth* 4 / 100, 225, MainFrame.windwowWidth * 88 / 100- 120, MainFrame.windwowHeight * 4 / 100);
		nameFieldLabel.setFont(IDFont);
		String tempName = MyData.loadedData.get(0).getName().trim();
		nameField = new TextField(tempName);
		nameField.setFont(arialFont);
		nameField.setBounds(MainFrame.windwowWidth* 4 / 100, 255, MainFrame.windwowWidth * 88 / 100- 60, MainFrame.windwowHeight * 5 / 100);
		nameField.setEnabled(false);
		nameEditButton= new RoundButton("Edit");
		nameEditButton.setBounds(MainFrame.windwowWidth * 88 / 100-40, 255, 50, MainFrame.windwowHeight * 5 / 100);
		nameEditButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(isNameEdittedButtonPressed) {
					if(!nameField.getText().isBlank()) {
						isNameEditted= true;
						nameField.setEnabled(false);
						nameEditButton.setColor("Edit", new Color(61,205,91));
						isNameEdittedButtonPressed= false;
					}
					
				}
				else {
					nameEditButton.setColor("Save", Color.YELLOW);
					nameField.setEnabled(true);
					isNameEdittedButtonPressed = true;
				}
			}
		});
		
		genderFieldLabel.setBounds(MainFrame.windwowWidth* 4 / 100, 290, MainFrame.windwowWidth * 88 / 100- 60, MainFrame.windwowHeight * 5 / 100);
		genderFieldLabel.setFont(IDFont);
		togglePanel = new JPanel();
		male = new JToggleButton("Male", MyData.loadedData.get(0).getGender().equals("male"));
		male.setBackground(Color.WHITE);
		male.setFont(smallFont);
		male.setForeground(new Color(61,205,91));
		male.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED) {
                	isGenderChanged = true;
                	gender = "male";
                }              
            }
        });
		male.setEnabled(false);
		female = new JToggleButton("Female",MyData.loadedData.get(0).getGender().equals("female"));
		female.setBackground(Color.WHITE);
		female.setFont(smallFont);
		female.setForeground(new Color(61,205,91));
		female.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED) {
                	isGenderChanged = true;
                	gender = "female";
                	
                }              
            }
        });
		female.setEnabled(false);
		buttonGroup = new ButtonGroup();
		container = new Container();
		buttonGroup.add(male);
		buttonGroup.add(female);
		container.add(male);
		container.add(female);
		container.setBounds(MainFrame.windwowWidth* 4 / 100, 310, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		container.setLayout(new FlowLayout());
		genderEditButton= new RoundButton("Edit");
		genderEditButton.setBounds(MainFrame.windwowWidth * 88 / 100-40, 310, 50, MainFrame.windwowHeight * 5 / 100);
		genderEditButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(isGenderEdittedButtonPressed) {
					male.setEnabled(false);
					female.setEnabled(false);
					isGenderEdittedButtonPressed = false;
					genderEditButton.setColor("Edit", new Color(61,205,91));
				}
				else {
					genderEditButton.setColor("Save", Color.YELLOW);
					male.setEnabled(true);
					female.setEnabled(true);
					isGenderEdittedButtonPressed = true;
				}
			}
		});
		
		phoneNumberFieldLabel.setBounds(MainFrame.windwowWidth* 4 / 100, 335, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		phoneNumberFieldLabel.setFont(IDFont);
		try {
			formatter = new MaskFormatter("010-####-####");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		phoneNumberField = new JFormattedTextField(MyData.loadedData.get(0).getPhoneNumber());
		formatter.install(phoneNumberField);
		phoneNumberField.setBounds(MainFrame.windwowWidth* 4 / 100, 360, MainFrame.windwowWidth * 88 / 100- 60, MainFrame.windwowHeight * 5 / 100);
		phoneNumberField.setEnabled(false);
		phoneNumberEditButton= new RoundButton("Edit");
		phoneNumberEditButton.setBounds(MainFrame.windwowWidth * 88 / 100 - 40, 360, 50, MainFrame.windwowHeight * 5 / 100);
		phoneNumberEditButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(isPhoneNumberEdittedButtonPressed) {
					phoneNumberField.setEnabled(false);
					isPhoneNumberEdittedButtonPressed = false;
					phoneNumberEditButton.setColor("Edit", new Color(61,205,91));
				}
				else {
					phoneNumberEditButton.setColor("Save", Color.YELLOW);
					phoneNumberField.setEnabled(true);
					isPhoneNumberEdittedButtonPressed = true;
				}
			}
		});
		
		birthdayFieldLabel.setBounds(MainFrame.windwowWidth* 4 / 100, 390, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		birthdayFieldLabel.setFont(IDFont);
		try {
			dateFormatter = new MaskFormatter("####-##-##");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println((MyData.loadedData.get(0).getDate()));
		birthdayField = new JFormattedTextField(MyData.loadedData.get(0).getDate());
		dateFormatter.install(birthdayField);
		birthdayField.setBounds(MainFrame.windwowWidth* 4 / 100, 415, MainFrame.windwowWidth * 88 / 100- 60, MainFrame.windwowHeight * 5 / 100);
		birthdayField.setEnabled(false);
		birthdayEditButton= new RoundButton("Edit");
		birthdayEditButton.setBounds(MainFrame.windwowWidth * 88 / 100 - 40, 415, 50, MainFrame.windwowHeight * 5 / 100);
		birthdayEditButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(isBirthdayEdittedButtonPressed) {
					birthdayField.setEnabled(false);
					isBirthdayEdittedButtonPressed = false;
					birthdayEditButton.setColor("Edit", new Color(61,205,91));
				}
				else {
					birthdayEditButton.setColor("Save", Color.YELLOW);
					birthdayField.setEnabled(true);
					isBirthdayEdittedButtonPressed = true;
				}
			}
		});
		
		saveButton = new RoundButton("Save");
		saveButton.setBounds(MainFrame.windwowWidth* 4 / 100, 460, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		saveButton.setFont(arialBoldFont);
		saveButton.addActionListener(new ActionListener() {
			
		});
		
		logoutButton = new RoundButton("Log out");
		logoutButton.setBounds(MainFrame.windwowWidth* 4 / 100, 495, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		logoutButton.setFont(arialBoldFont);
		signoutButton = new RoundButton("Sign out");
		signoutButton.setBounds(MainFrame.windwowWidth* 4 / 100, 530, MainFrame.windwowWidth * 88 / 100, MainFrame.windwowHeight * 5 / 100);
		signoutButton.setFont(arialBoldFont);
		
		this.add(label);
		this.add(idFieldLabel);
		this.add(idField);
		this.add(passwordFieldLabel);
		this.add(passwordField);
		this.add(setVisiblityOnPasswordField);
		this.add(editPasswordButton);
		this.add(nameFieldLabel);
		this.add(nameField);
		this.add(nameEditButton);
		this.add(genderFieldLabel);
		this.add(container);
		this.add(genderEditButton);
		this.add(phoneNumberFieldLabel);
		this.add(phoneNumberField);
		this.add(phoneNumberEditButton);
		this.add(birthdayFieldLabel);
		this.add(birthdayField);
		this.add(birthdayEditButton);
		this.add(saveButton);
		this.add(logoutButton);
		this.add(signoutButton);
	 }

	public RoundButton getSignoutButton() {
		return signoutButton;
	}
}
