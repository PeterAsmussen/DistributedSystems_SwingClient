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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPanel createroomPanel;
	public JButton btnExit, btnDone, btnBack;
	public JLabel lblRoomName, lblTopicQuestion;
	public JTextArea txtRoomName, txtTopicQuestion;
	private Test_LocalRooms testrooms = new Test_LocalRooms();
	HomeScreen homescreen;

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
		createroomPanel = new JPanel();
		createroomPanel.setBounds(200, 200, 900, 600);
	
		//Title label, top left corner
		JLabel lblCreateRoom = DefaultComponentFactory.getInstance().createTitle("Create new room");
		lblCreateRoom.setBounds(19, 20, 250, 16);
		createroomPanel.add(lblCreateRoom);
		
		//Exit-button, top right corner
		btnExit = new JButton();
		btnExit.setBounds(780, 20, 100, 20);
		btnExit.setText("Exit");
		btnExit.addActionListener(this);
		createroomPanel.add(btnExit);
		
		//Done-button
		btnDone = new JButton();
		btnDone.setBounds(20, 170, 100, 20);
		btnDone.setText("Done");
		btnDone.addActionListener(this);
		createroomPanel.add(btnDone);
		
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
		
		
		createroomPanel.add(txtRoomName);
		createroomPanel.add(lblRoomName);
		createroomPanel.add(txtTopicQuestion);
		createroomPanel.add(lblTopicQuestion);
	
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if(cmd.equals("Exit")){
			System.out.println("Exit-button was pressed, shutting down...");
			createroomPanel.setVisible(false);
			System.exit(0);
		}
		if(cmd.equals("Done")){
			testrooms.rooms.add(txtRoomName.getText());
			System.out.println("Done-button was pressed:");
			System.out.println(testrooms.rooms);
			
			createroomPanel.setVisible(false);
			homescreen = new HomeScreen();
			homescreen.setVisible(true);
		}
	}
}
