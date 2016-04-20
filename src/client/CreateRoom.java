package client;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
	public JTextField txtRoomName, txtTopicQuestion;
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
		createroomPanel.setLayout(new GridLayout(0,1));
		JPanel rnPanel = new JPanel();
		rnPanel.setLayout(new BorderLayout(2,2));
		JPanel tqPanel = new JPanel();
		tqPanel.setLayout(new BorderLayout(2,2));
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new GridLayout(0,1));
	
		JLabel lblCreateRoom = DefaultComponentFactory.getInstance().createTitle("Create new room");
		
		btnExit = new JButton();
		btnExit.setText("Exit");
		btnExit.addActionListener(this);
		
		btnDone = new JButton();
		btnDone.setText("Done");
		btnDone.addActionListener(this);
		
		
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
		lblTopicQuestion = new JLabel();
		lblTopicQuestion.setText("Enter a topic/question");
		
		txtRoomName = new JTextField();
		txtTopicQuestion = new JTextField();
		
		createroomPanel.add(lblCreateRoom);
		createroomPanel.add(rnPanel);
		rnPanel.add(lblRoomName, BorderLayout.WEST);
		rnPanel.add(txtRoomName, BorderLayout.CENTER);
		createroomPanel.add(tqPanel);
		tqPanel.add(lblTopicQuestion, BorderLayout.WEST);
		tqPanel.add(txtTopicQuestion, BorderLayout.CENTER);
		createroomPanel.add(btnPanel);
		btnPanel.add(btnDone);
		btnPanel.add(btnExit);
	
		
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
			//testrooms.rooms.add(txtRoomName.getText());
			
			homescreen = new HomeScreen();
			MainWindow.frame.getContentPane().add(homescreen.homePanel);
			
			System.out.println("Done-button was pressed:");
			System.out.println(testrooms.rooms);
			
			createroomPanel.setVisible(false);

		}
	}
}
