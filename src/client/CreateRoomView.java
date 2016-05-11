package client;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import functionality.CreateRoomFunc;
import tests.Test_LocalRooms;

public class CreateRoomView extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPanel createroomPanel;
	public JButton btnExit, btnDone, btnBack;
	public JLabel lblRoomName, lblTopicQuestion;
	public JTextField txtRoomName, txtTopicQuestion;
	public static String roomname;
	private Test_LocalRooms testrooms = new Test_LocalRooms();
	CreateRoomFunc createroomfunc = new CreateRoomFunc();
	HomeScreenView homescreen;

	/**
	 * Create the application.
	 */
	public CreateRoomView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//Create the frame
		createroomPanel = new JPanel();
		createroomPanel.setLayout(new GridLayout(3,1));
		JPanel infoPanel = new JPanel();
		JPanel btnPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(4,1));
		btnPanel.setLayout(new GridBagLayout());
	
		JLabel lblCreateRoom = DefaultComponentFactory.getInstance().createTitle("Create new room");
		
		btnExit = new JButton();
		btnExit.setText("Exit");
		btnExit.addActionListener(this);
		
		btnDone = new JButton();
		btnDone.setText("Done");
		btnDone.addActionListener(this);
		
		lblRoomName = new JLabel();
		lblRoomName.setText("Enter Room name");
		lblTopicQuestion = new JLabel();
		lblTopicQuestion.setText("Enter a topic/question");
		
		txtRoomName = new JTextField();
		txtTopicQuestion = new JTextField();
		
		createroomPanel.add(infoPanel);
		infoPanel.add(lblRoomName);
		infoPanel.add(txtRoomName);
		infoPanel.add(lblTopicQuestion);
		infoPanel.add(txtTopicQuestion);
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
			
			roomname = txtRoomName.getText().toString();
			createroomfunc.createRoom();
			homescreen = new HomeScreenView();
			MainWindow.frame.getContentPane().add(homescreen.homeScreenPanel);
			
			System.out.println("Done-button was pressed:");
			System.out.println(testrooms.rooms);
			
			createroomPanel.setVisible(false);

		}
	}
}
