package client;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jgoodies.forms.factories.DefaultComponentFactory;

public class CreateRoom extends JPanel implements ActionListener {

	private JFrame frame;
	public JButton btnExit;

	/**
	 * Launch the application.
	 */
	public static void CreateRoom() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateRoom window = new CreateRoom();
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
	public CreateRoom() {
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
		JLabel lblCreateRoom = DefaultComponentFactory.getInstance().createTitle("Create new room");
		lblCreateRoom.setBounds(19, 20, 250, 16);
		frame.getContentPane().add(lblCreateRoom);
		
		//Exit-button, top right corner
		btnExit = new JButton();
		btnExit.setBounds(780, 20, 100, 20);
		btnExit.setText("Exit");
		btnExit.addActionListener(this);
		frame.getContentPane().add(btnExit);
		
		/*
		 * TODO!
		 * Der skal oprettes nogle tekstfelter, hvori kan skal skrive:
		 * 	Room-navn
		 * 	Et spørgsmål
		 * 	Evt(?)
		 * Når rummet er oprettet, skal der sendes en put/post-besked til 
		 * Firebase. Herefter skal man sendes tilbage til 'HomeScreen'-vinduet
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
		
	}

}
