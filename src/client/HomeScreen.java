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
	public JButton btnExit, btnCreateRoom;
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
		//Create the frame
		frame = new JFrame();
		frame.setBounds(200, 200, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Title label, top left corner
		JLabel lblAvailableRooms = DefaultComponentFactory.getInstance().createTitle("Available rooms for:");
		lblAvailableRooms.setBounds(19, 20, 250, 16);
		frame.getContentPane().add(lblAvailableRooms);

		//Name label, below title label
		JLabel lblUser = new JLabel(username);
		lblUser.setBounds(19, 40, 250, 16);
		frame.getContentPane().add(lblUser);
		
		//Exit-button, top right corner
		btnExit = new JButton();
		btnExit.setBounds(780, 20, 100, 20);
		btnExit.setText("Exit");
		btnExit.addActionListener(this);
		frame.getContentPane().add(btnExit);
		
		//Create Room-button, left to Exit-button
		btnCreateRoom = new JButton();
		btnCreateRoom.setBounds(675, 20, 100, 20);
		btnCreateRoom.setText("Create room");
		btnCreateRoom.addActionListener(this);
		frame.getContentPane().add(btnCreateRoom);
		
		/*
		 * TODO!
		 * Der skal oprettes en liste, hvori tilgængelige Rooms skal være.
		 * Derudover skal man kunne klikke på én af disse, og blive sendt videre
		 * til 'SelectedRoom'-siden
		 */
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if(cmd.equals("Exit")){
			System.out.println("Exit-button was pressed, shutting down...");
			frame.setVisible(false);
			frame.dispose();
			System.exit(0);
		}
		
		if(cmd.equals("Create room")){
			System.out.println("Create Room was pressed, proceeding...");
			frame.setVisible(false);
			frame.dispose();
			CreateRoom createroom = new CreateRoom();
			createroom.CreateRoom();
			createroom.setVisible(true);
			
		}
		
	}
}
