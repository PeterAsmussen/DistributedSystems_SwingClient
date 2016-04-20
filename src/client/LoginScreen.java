package client;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import tests.VerifyLogin;

public class LoginScreen extends JPanel implements ActionListener {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPanel loginPanel;
	private JTextField txtUsername;
	private JPasswordField password;
	private JButton btnLogIn;
	private JButton btnLoginAsGuest;
	public static String username;
	VerifyLogin verify = new VerifyLogin();

	public LoginScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		loginPanel = new JPanel();
		JPanel btnPanel = new JPanel();
		JPanel unPanel = new JPanel();
		JPanel pwPanel = new JPanel();
		loginPanel.setBounds(100, 100, 450, 300);
		loginPanel.setLayout(new GridLayout(0,1));
		btnPanel.setLayout(new GridLayout(0,1));
		unPanel.setLayout(new BorderLayout(2,2));
		pwPanel.setLayout(new BorderLayout(2,2));
		
		txtUsername = new JTextField();
		password = new JPasswordField();
		password.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username	");
		JLabel lblPassword = new JLabel("Password	");
		
		btnLogIn = new JButton("Log in");
		btnLoginAsGuest = new JButton("Login as guest");		
		
		password.setActionCommand("Log in");
		btnLogIn.addActionListener(this);
		btnLoginAsGuest.addActionListener(this);
		
		loginPanel.add(unPanel);
		unPanel.add(lblUsername, BorderLayout.WEST);
		unPanel.add(txtUsername, BorderLayout.CENTER);
		loginPanel.add(pwPanel);
		pwPanel.add(lblPassword, BorderLayout.WEST);
		pwPanel.add(password, BorderLayout.CENTER);
		loginPanel.add(btnPanel);
		btnPanel.add(btnLogIn);
		btnPanel.add(btnLoginAsGuest);
	}
	
	public void actionPerformed(ActionEvent e){
		String cmd = e.getActionCommand();
		
		if(cmd.equals("Log in")){
			char[] passInput = password.getPassword();
			
			if(verify.isPasswordCorrect(passInput)){
				username = txtUsername.getText();
				HomeScreen homescreen = new HomeScreen();
				MainWindow.frame.getContentPane().add(homescreen.homePanel);
				loginPanel.setVisible(false);

				System.out.println("Log in was pressed");
			} else {
				JOptionPane.showMessageDialog(loginPanel, "Bad password, try again");
				System.out.println("Incorrect password!");
			}
		}
		
		if(cmd.equals("Login as guest")){
			int i = verify.randomInteger(1, 8);
			username = verify.nameGenerator(i);
			HomeScreen homescreen = new HomeScreen();
			MainWindow.frame.getContentPane().add(homescreen.homePanel);
			loginPanel.setVisible(false);
			
	
			System.out.println("Login as guest was pressed!");
		}
	}
}
