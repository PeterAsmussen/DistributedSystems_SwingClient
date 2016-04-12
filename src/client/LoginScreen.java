package client;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginScreen extends JPanel implements ActionListener {

	
	private JFrame frame;
	private JTextField txtUsername;
	private JPasswordField password;
	private JButton btnLogIn;
	private JButton btnLoginAsGuest;
	public static String username;
	VerifyLogin verify = new VerifyLogin();

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
		btnLoginAsGuest.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){
		String cmd = e.getActionCommand();
		
		if(cmd.equals("Log in")){
			char[] passInput = password.getPassword();
			
			if(verify.isPasswordCorrect(passInput)){
				username = txtUsername.getText();
				HomeScreen homescreen = new HomeScreen();
				homescreen.HomeScreen();
				homescreen.setVisible(true);
				frame.setVisible(false);

				System.out.println("Log in was pressed");
			} else {
				JOptionPane.showMessageDialog(frame, "Bad password, try again");
				System.out.println("Incorrect password!");
			}
		}
		
		if(cmd.equals("Login as guest")){
			int i = verify.randomInteger(1, 8);
			username = verify.nameGenerator(i);
			HomeScreen homescreen = new HomeScreen();
			homescreen.HomeScreen();
			homescreen.setVisible(true);
			frame.setVisible(false);
			frame.dispose();
	
			System.out.println("Login as guest was pressed!");
		}
	}
}
