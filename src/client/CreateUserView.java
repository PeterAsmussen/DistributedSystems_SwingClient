package client;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import functionality.CreateUserFunc;

public class CreateUserView extends JPanel implements ActionListener {
	
	CreateUserFunc createuserfunc = new CreateUserFunc();
	
	private static final long serialVersionUID = 1L;
	public JPanel createUserPanel;
	private JButton btnCreate, btnExit, btnBack;
	private JTextField username, password, repeatPassword, email, firstname, lastname;
	public static String getUsername, getEmail, getPassword, getPassword2, getFirstname, getLastname;
	
	public CreateUserView(){
		initialize();
	}

	private void initialize(){
		createUserPanel = new JPanel();
		createUserPanel.setLayout(new GridLayout(2, 1));
		JPanel infoPanel = new JPanel();
		JPanel btnPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(6,0));
		btnPanel.setLayout(new GridBagLayout());
		
		JLabel lblUsername = new JLabel("Username	");
		JLabel lblPassword = new JLabel("Password	");
		JLabel lblRepeatPassword = new JLabel("Repeat password	");
		JLabel lblEmail = new JLabel("E-mail	");
		JLabel lblFirstname = new JLabel("First name"	);
		JLabel lblLastname = new JLabel("Last name	");
		
		username = new JTextField();
		username.setColumns(20);
		
		password = new JTextField();
		password.setColumns(20);
		
		repeatPassword = new JTextField();
		repeatPassword.setColumns(20);
		
		email = new JTextField();
		email.setColumns(20);
		
		firstname = new JTextField();
		firstname.setColumns(20);
		
		lastname = new JTextField();
		lastname.setColumns(20);
		
		
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
		infoPanel.add(lblRepeatPassword);
		infoPanel.add(repeatPassword);
		infoPanel.add(lblEmail);
		infoPanel.add(email);
		infoPanel.add(lblFirstname);
		infoPanel.add(firstname);
		infoPanel.add(lblLastname);
		infoPanel.add(lastname);
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
			LoginScreenView loginscreen = new LoginScreenView();
			MainWindow.frame.getContentPane().add(loginscreen.loginPanel);
			createUserPanel.setVisible(false);
			
			System.out.println("Back-button was pressed!");
		}
		
		if(cmd.equals("Create user")){
			getUsername = username.getText().toString();
			getPassword = password.getText().toString();
			getPassword2 = password.getText().toString();
			getEmail = email.getText().toString();
			getFirstname = firstname.getText().toString();
			getLastname = lastname.getText().toString();
			
			System.out.println(getUsername);
			System.out.println(getPassword);
			System.out.println(getPassword2);
			System.out.println(getEmail);
			System.out.println(getFirstname);
			System.out.println(getLastname);
			
			/*
			 * insert if-statement to determine if user was created or not
			 */
			
			JOptionPane.showMessageDialog(createUserPanel, "User successfully created");
			
			createuserfunc.createUser();
		}
		
	}
}
