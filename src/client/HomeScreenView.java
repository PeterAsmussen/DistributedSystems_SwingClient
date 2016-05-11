package client;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

import org.json.simple.JSONObject;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import functionality.RetrieveData;
import tests.Test_LocalRooms;

public class HomeScreenView extends JPanel implements ActionListener{

	public JPanel homeScreenPanel;
	private JList roomList;
	private JPanel listPanel;
	private JScrollPane scrollPanel;
	public JButton btnExit, btnCreateRoom, btnUpdate;
	RetrieveData retrievedata = new RetrieveData();
	LoginScreenView loginscreen = new LoginScreenView();
	
	/**
	 * Create the application.
	 */
	public HomeScreenView() {
		//retrievedata.getData();
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
		JPanel btnPanel = new JPanel();
		homeScreenPanel.setLayout(new GridLayout(2,0));
		btnPanel.setLayout(new GridBagLayout());
		

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
		 * nedenstående skal have et array af en art, som argument, for at kunne vise rum for 
		 * den tilsvarende bruger
		 */
		
		
		DefaultListModel<String> list2 = new DefaultListModel<String>();
		for(JSONObject u : retrievedata.getRooms()){
			list2.addElement(u.get("TITLE").toString());
			
		}
		JList list = new JList(list2);
		
		
		JScrollPane listScrollPane = new JScrollPane(list);
		listScrollPane.setVisible(true);
		homeScreenPanel.add(listScrollPane);
		homeScreenPanel.add(btnPanel);
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
			CreateRoomView createroom = new CreateRoomView();
			MainWindow.frame.getContentPane().add(createroom.createroomPanel);
			createroom.setVisible(true);	
		}
		
		if(cmd.equals("Update")){
			retrievedata.getRooms();
		}
	}
}