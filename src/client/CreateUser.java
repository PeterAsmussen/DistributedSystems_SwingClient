package client;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CreateUser extends JPanel implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPanel createUserPanel;
	public JButton btnCreate, btnExit, btnBack;
	public JTextField username, password;
	
	public CreateUser(){
		initialize();
	}

	private void initialize(){
		createUserPanel = new JPanel();
		createUserPanel.setLayout(new GridLayout(3, 1));
		JPanel infoPanel = new JPanel();
		JPanel btnPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(4,3));
		btnPanel.setLayout(new GridBagLayout());
		
		JLabel lblUsername = new JLabel("Username:	");
		JLabel lblPassword = new JLabel("Password:	");
		
		username = new JTextField();
		username.setColumns(20);
		
		password = new JTextField();
		password.setColumns(20);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(this);
		btnCreate = new JButton("Create user");
		btnCreate.addActionListener(this);
		btnExit = new JButton("Exit");
		btnExit.addActionListener(this);
		
		createUserPanel.add(infoPanel);
		infoPanel.add(lblUsername);
		infoPanel.add(username);
		infoPanel.add(lblPassword);
		infoPanel.add(password);
		createUserPanel.add(btnPanel);
		btnPanel.add(btnBack);
		btnPanel.add(btnCreate);
		btnPanel.add(btnExit);
	
		//Alt der skal bruges er et username og et password, saa er brugeren oprettet
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if(cmd.equals("Exit")){
			System.out.println("Exit-button was pressed, shutting down...");
			createUserPanel.setVisible(false);
			System.exit(0);
		}
		
		if(cmd.equals("Back")){
			LoginScreen loginscreen = new LoginScreen();
			MainWindow.frame.getContentPane().add(loginscreen.loginPanel);
			createUserPanel.setVisible(false);
			
			System.out.println("Back-button was pressed!");
		}
		
		if(cmd.equals("Create user")){
			/*
			 * TODO:
			 * Data skal sendes til database her
			 */
		}
		
	}
}
