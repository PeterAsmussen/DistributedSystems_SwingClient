package client;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import tests.Test_LocalRooms;

public class CreateRoom extends JPanel implements ActionListener {

	private JFrame frame;
	public JButton btnExit, btnDone, btnBack;
	public JLabel lblRoomName, lblTopicQuestion;
	public JTextArea txtRoomName, txtTopicQuestion;
	private Test_LocalRooms testrooms = new Test_LocalRooms();
	HomeScreen homescreen;

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
		
		//Done-button
		btnDone = new JButton();
		btnDone.setBounds(20, 170, 100, 20);
		btnDone.setText("Done");
		btnDone.addActionListener(this);
		frame.getContentPane().add(btnDone);
		
		/*
		 * TODO!
		 * Der skal oprettes nogle tekstfelter, hvori der skal tilføjes:
		 * 	Room-navn
		 * 	Et spørgsmål
		 * 	Evt(?)
		 * Når rummet er oprettet, skal der sendes en put/post-besked til 
		 * Firebase. Herefter skal man sendes tilbage til 'HomeScreen'-vinduet
		 */
		
		lblRoomName = new JLabel();
		lblRoomName.setText("Enter Room name");
		lblRoomName.setBounds(20, 40, 200, 20);
		lblTopicQuestion = new JLabel();
		lblTopicQuestion.setText("Enter a topic/question");
		lblTopicQuestion.setBounds(20, 120, 200, 20);
		
		txtRoomName = new JTextArea();
		txtRoomName.setBounds(20, 65, 500, 20);
		txtTopicQuestion = new JTextArea();
		txtTopicQuestion.setBounds(20, 145, 500, 20);
		
		
		frame.getContentPane().add(txtRoomName);
		frame.getContentPane().add(lblRoomName);
		frame.getContentPane().add(txtTopicQuestion);
		frame.getContentPane().add(lblTopicQuestion);
	
		
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
		if(cmd.equals("Done")){
			testrooms.rooms.add(txtRoomName.getText());
			System.out.println("Done-button was pressed:");
			System.out.println(testrooms.rooms);
			
			frame.setVisible(false);
			frame.dispose();
			homescreen = new HomeScreen();
			homescreen.HomeScreen();
			homescreen.setVisible(true);
		}
	}
}
