package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class LoginScreen {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnLogIn;
	private JButton btnLoginAsGuest;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen window = new LoginScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(160, 82, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(160, 136, 130, 26);
		frame.getContentPane().add(textField_1);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(163, 64, 92, 16);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(163, 120, 92, 16);
		frame.getContentPane().add(lblPassword);
		
		btnLogIn = new JButton("Log in");
		btnLogIn.setBounds(160, 174, 130, 29);
		frame.getContentPane().add(btnLogIn);
		
		btnLoginAsGuest = new JButton("Login as guest");
		btnLoginAsGuest.setBounds(160, 211, 130, 29);
		frame.getContentPane().add(btnLoginAsGuest);
	}
}
