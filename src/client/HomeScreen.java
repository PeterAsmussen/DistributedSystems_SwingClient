package client;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import tests.Test_LocalRooms;

public class HomeScreen extends JPanel implements ActionListener{

	public JPanel homePanel;
	private JList roomList;
	private JPanel listPanel;
	private JScrollPane scrollPanel;
	public JButton btnExit, btnCreateRoom, btnUpdate;
	public LoginScreen loginscreen;
	private Test_LocalRooms testrooms = new Test_LocalRooms();
	String username = loginscreen.username;
	
	/**
	 * Create the application.
	 */
	public HomeScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("unchecked")
	private void initialize() {
		//Create the frame
		homePanel = new JPanel();
		JPanel labelPanel = new JPanel();
		homePanel.setBounds(200, 200, 900, 600);
		homePanel.setLayout(new BorderLayout());
		
		//LABELS
		homePanel.add(labelPanel, BorderLayout.NORTH);

		JLabel lblAvailableRooms = DefaultComponentFactory.getInstance().createTitle("Available rooms for:");
		lblAvailableRooms.setBounds(19, 20, 250, 16);
		labelPanel.add(lblAvailableRooms);
		JLabel lblUser = new JLabel(username);
		lblUser.setBounds(39, 20, 250, 16);
		labelPanel.add(lblUser);
		
		//Exit-button, top right corner
		btnExit = new JButton();
		btnExit.setBounds(780, 20, 100, 20);
		btnExit.setText("Exit");
		btnExit.addActionListener(this);
		
		//Create Room-button, left to Exit-button
		btnCreateRoom = new JButton();
		btnCreateRoom.setBounds(675, 20, 100, 20);
		btnCreateRoom.setText("Create room");
		btnCreateRoom.addActionListener(this);
		
		//Update-button
		btnUpdate = new JButton();
		btnUpdate.setBounds(520, 60, 100, 20);
		btnUpdate.setText("Update");
		btnUpdate.addActionListener(this);
		
		JList listOfRooms = new JList(testrooms.getRooms().toArray());
		listOfRooms.setVisibleRowCount(5);
		JList listOfQuestions = new JList();
		JScrollPane listScrollPane = new JScrollPane(listOfRooms);
		listScrollPane.setBounds(19, 60, 500, 400);
		
		
		homePanel.add(listScrollPane);
		
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new GridLayout(0,1));
		homePanel.add(btnPanel, BorderLayout.SOUTH);
		
		btnPanel.add(btnCreateRoom);
		btnPanel.add(btnUpdate);
		btnPanel.add(btnExit);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if(cmd.equals("Exit")){
			System.out.println("Exit-button was pressed, shutting down...");
			homePanel.setVisible(false);
			System.exit(0);
		}
		
		if(cmd.equals("Create room")){
			System.out.println("Create Room was pressed, proceeding...");
			homePanel.setVisible(false);
			CreateRoom createroom = new CreateRoom();
			MainWindow.frame.getContentPane().add(createroom.createroomPanel);
			createroom.setVisible(true);	
		}
		
		if(cmd.equals("Update")){
			JList<ArrayList> newList = new JList(testrooms.getRooms().toArray());
			JScrollPane listScrollPane = new JScrollPane(newList);
			listScrollPane.setBounds(19, 60, 500, 400);
			homePanel.add(listScrollPane);
		}
	}
}
