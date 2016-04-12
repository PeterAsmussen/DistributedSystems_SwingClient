package client;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

public class LoginScreen implements ActionListener {

	private JFrame frame;
	private JTextField txtUsername;
	private JPasswordField password;
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
		
		txtUsername = new JTextField();
		txtUsername.setBounds(160, 82, 130, 26);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		password = new JPasswordField();
		password.setColumns(10);
		password.setBounds(160, 136, 130, 26);
		frame.getContentPane().add(password);
		
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
		
		
		password.setActionCommand("Log in");
		btnLogIn.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){
		String cmd = e.getActionCommand();
		
		if(cmd.equals("Log in")){
			JFrame frame = new JFrame ("HomeScreen");
            frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(btnLogIn, new HomeScreen());
            frame.pack();
            frame.setVisible (true);
		}
		
	}
}
