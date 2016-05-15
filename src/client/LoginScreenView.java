package client;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.App;

public class LoginScreenView extends JPanel implements ActionListener {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPanel loginPanel;
	public JTextField txtUsername;
	public JPasswordField txtPassword;
	private JButton btnLogIn, btnJacobsServer, btnCreateUser;
	public static String username;
	public String password;

	public LoginScreenView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		/*
		 * LoginScreen-panelet oprettes, samt de oenksede under-paneler
		 * som skal indeholder knapper/andre obejkter 
		 */
		loginPanel = new JPanel();
		JPanel btnPanel = new JPanel();
		JPanel infoPanel = new JPanel();
		loginPanel.setLayout(new GridLayout(3,1));
		btnPanel.setLayout(new GridBagLayout());
		infoPanel.setLayout(new GridLayout(4,3));

		/*
		 * Username og password-felter oprettes
		 */
		txtUsername = new JTextField();
		txtUsername.setColumns(20);
		txtPassword = new JPasswordField();
		txtPassword.setColumns(20);

		/*
		 * Labels oprettes
		 */
		JLabel lblUsername = new JLabel("Username:	");
		JLabel lblPassword = new JLabel("Password:	");

		/*
		 * Knapper oprettes
		 */
		btnLogIn = new JButton("Log in");
		btnJacobsServer = new JButton("Jacobs server");
		btnCreateUser = new JButton("Create user");

		txtPassword.setActionCommand("Log in");
		btnLogIn.addActionListener(this);
		btnJacobsServer.addActionListener(this);
		btnCreateUser.addActionListener(this);

		/*
		 * Objekter tilfoejes til LoginScreen-panelet
		 */
		loginPanel.add(infoPanel);
		infoPanel.add(lblUsername);
		infoPanel.add(txtUsername);
		infoPanel.add(lblPassword);
		infoPanel.add(txtPassword);
		loginPanel.add(btnPanel);
		btnPanel.add(btnLogIn);
		btnPanel.add(btnCreateUser);
		btnPanel.add(btnJacobsServer);

	}

	public void actionPerformed(ActionEvent e){
		String cmd = e.getActionCommand();

		if(cmd.equals("Log in")){
			username = txtUsername.getText();
			String password = String.valueOf(txtPassword.getPassword());

			try {
				if(App.getUserController().login(username, password) != null){
					System.out.println(App.getCurrentUsername());
					HomeScreenView homescreen = new HomeScreenView();
					MainWindow.frame.getContentPane().add(homescreen.homeScreenPanel);
					loginPanel.setVisible(false);
					System.out.println("Log in was pressed");

				} else {

					JOptionPane.showMessageDialog(loginPanel, "Bad password, try again");
					System.out.println("Incorrect password!");

				}
			} catch (HeadlessException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		if(cmd.equals("Jacobs server")){
			username = txtUsername.getText();
			String password = String.valueOf(txtPassword.getPassword());

			System.out.println(username);
			System.out.println(password);
			
			try{
				if(App.getUserController().otherLogin(username, password) != null){
					System.out.println(App.getCurrentUsername());
					HomeScreenView homescreen = new HomeScreenView();
					MainWindow.frame.getContentPane().add(homescreen.homeScreenPanel);
					loginPanel.setVisible(false);
					System.out.println("Log in was pressed");

				} else {

					JOptionPane.showMessageDialog(loginPanel, "Bad password, try again");
					System.out.println("Incorrect password!");
				}
			} catch (HeadlessException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}


		if(cmd.equals("Create user")){

			CreateUserView createuser = new CreateUserView();
			MainWindow.frame.getContentPane().add(createuser.createUserPanel);
			loginPanel.setVisible(false);
			System.out.println("Create user was pressed!");
		}
	}
}
