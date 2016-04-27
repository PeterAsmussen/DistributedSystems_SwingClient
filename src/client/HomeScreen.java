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

	public JPanel homeScreenPanel;
	private JList roomList;
	private JPanel listPanel;
	private JScrollPane scrollPanel;
	public JButton btnExit, btnCreateRoom, btnUpdate;
	private Test_LocalRooms testrooms = new Test_LocalRooms();
	LoginScreen loginscreen = new LoginScreen();
	
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
		
		/*
		 * HomeScreen-panelet oprettes, og der tilfoejes yderligere under-paneler
		 * som indeholder knapper/ScrollPanes osv
		 */
		homeScreenPanel = new JPanel();
		JPanel labelPanel = new JPanel();
		homeScreenPanel.setLayout(new BorderLayout());
		homeScreenPanel.add(labelPanel, BorderLayout.NORTH);

		/*
		 * Titel på vindue
		 */
		JLabel lblAvailableRooms = DefaultComponentFactory.getInstance().createTitle("Available rooms for:" );
		labelPanel.add(lblAvailableRooms);
		
		/*
		 * Knapper oprettes
		 */
		btnExit = new JButton();
		btnExit.setText("Exit");
		btnExit.addActionListener(this);
		
		btnCreateRoom = new JButton();
		btnCreateRoom.setText("Create room");
		btnCreateRoom.addActionListener(this);
	
		btnUpdate = new JButton();
		btnUpdate.setText("Update");
		btnUpdate.addActionListener(this);
		
		/*
		 * Data fra databasen skal hentes, manipuleres så den kan indsættes i et ScrollPane,
		 * og så man kan 'vælge' et specifikt datapunkt, og åbne dette (det vil være et Room) 
		 */
		JScrollPane listScrollPane = new JScrollPane();
		
		
		/*
		 * Objekter tilfoejes til HomeScreen-panelet
		 */
		homeScreenPanel.add(listScrollPane);
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new GridLayout(0,1));
		homeScreenPanel.add(btnPanel, BorderLayout.SOUTH);
		btnPanel.add(btnCreateRoom);
		btnPanel.add(btnUpdate);
		btnPanel.add(btnExit);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if(cmd.equals("Exit")){
			System.out.println("Exit-button was pressed, shutting down...");
			homeScreenPanel.setVisible(false);
			System.exit(0);
		}
		
		if(cmd.equals("Create room")){
			System.out.println("Create Room was pressed, proceeding...");
			homeScreenPanel.setVisible(false);
			CreateRoom createroom = new CreateRoom();
			MainWindow.frame.getContentPane().add(createroom.createroomPanel);
			createroom.setVisible(true);	
		}
		
		if(cmd.equals("Update")){
			//TODO
		}
	}
}
