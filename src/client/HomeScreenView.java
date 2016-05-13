package client;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import functionality.controllers.RoomController;
import model.App;
import model.RoomDTO;

public class HomeScreenView extends JPanel implements ActionListener{

	public JPanel homeScreenPanel;
	public JList list;
	private JPanel listPanel;
	private JScrollPane scrollPanel;
	public JButton btnLogout, btnCreateRoom, btnEnterroom;
	public static String selectedvalue;
//	RetrieveData retrievedata = new RetrieveData();
	RoomController roomcontroller = new RoomController();
	LoginScreenView loginscreen = new LoginScreenView();
	
	/**
	 * Create the application.
	 */
	public HomeScreenView() {
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
		btnLogout = new JButton();
		btnLogout.setText("Log out");
		btnLogout.addActionListener(this);
		
		btnCreateRoom = new JButton();
		btnCreateRoom.setText("Create room");
		btnCreateRoom.addActionListener(this);
	
		btnEnterroom = new JButton();
		btnEnterroom.setText("Enter room");
		btnEnterroom.addActionListener(this);
		
		DefaultListModel<String> list2 = new DefaultListModel<String>();
		try {

			System.out.println("2222"+App.getCurrentUsername());
			for(RoomDTO u : roomcontroller.getRoomDTOList(App.getCurrentUsername())){
				list2.addElement(u.getTitle());
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		
		list = new JList(list2);
		JScrollPane listScrollPane = new JScrollPane(list);
		listScrollPane.setVisible(true);
		homeScreenPanel.add(listScrollPane);
		homeScreenPanel.add(btnPanel);
		btnPanel.add(btnCreateRoom);
		btnPanel.add(btnEnterroom);
		btnPanel.add(btnLogout);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if(cmd.equals("Log out")){
			System.out.println("Logging out...");
			homeScreenPanel.setVisible(false);
			loginscreen = new LoginScreenView();
			MainWindow.frame.getContentPane().add(loginscreen.loginPanel);
		}
		
		if(cmd.equals("Create room")){
			System.out.println("Create Room was pressed, proceeding...");
			homeScreenPanel.setVisible(false);
			CreateRoomView createroom = new CreateRoomView();
			MainWindow.frame.getContentPane().add(createroom.createroomPanel);
			createroom.setVisible(true);	
		}
		
		if(cmd.equals("Enter room")){
			System.out.println("Enter room was pressed, proceeding...");
			SelectedRoom selectedroom = new SelectedRoom();
			SelectedRoom.SelectedRoom();
			selectedvalue = list.getSelectedValue().toString();
			
		}
	}
}
