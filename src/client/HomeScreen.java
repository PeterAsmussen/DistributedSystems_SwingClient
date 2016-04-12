package client;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jgoodies.forms.factories.DefaultComponentFactory;

public class HomeScreen extends JPanel implements ActionListener{

	private JFrame frame;
	public JButton btnExit;
	public LoginScreen loginscreen;
	String username = loginscreen.username;

	/**
	 * Launch the application.
	 */
	public static void HomeScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeScreen window = new HomeScreen();
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
	public HomeScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(200, 200, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAvailableRooms = DefaultComponentFactory.getInstance().createTitle("Available rooms for:");
		JLabel lblUser = new JLabel(username);
		lblAvailableRooms.setBounds(19, 20, 250, 16);
		lblUser.setBounds(19, 40, 250, 16);
		frame.getContentPane().add(lblUser);
		frame.getContentPane().add(lblAvailableRooms);
		
		btnExit = new JButton();
		btnExit.setBounds(780, 20, 100, 20);
		btnExit.setText("Exit");
		btnExit.addActionListener(this);
		frame.getContentPane().add(btnExit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if(cmd.equals("Exit")){
			frame.setVisible(false);
			frame.dispose();
			System.exit(0);
		}
		
	}
}
