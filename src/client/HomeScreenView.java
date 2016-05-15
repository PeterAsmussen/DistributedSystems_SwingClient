package client;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import functionality.controllers.RoomController;
import model.App;
import model.EventDTO;
import model.RoomDTO;

public class HomeScreenView extends JPanel implements ActionListener{

	public JPanel homeScreenPanel;
	public JList titleList;
	private JPanel listPanel;
	private JScrollPane scrollPanel;
	public JButton btnLogout, btnCreateRoom, btnEnterroom;
	public static String selectedvalue;
	DefaultListModel<String> list2;
	DefaultListModel<RoomDTO> list3;
	RoomController roomcontroller = new RoomController();
	EventDTO eventDTO;
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
		
		list2 = new DefaultListModel<String>();
		list3 = new DefaultListModel<RoomDTO>();
		
		try {
			for(RoomDTO u : roomcontroller.getRoomDTOList(App.getCurrentUsername())){
				list2.addElement(u.getTitle());
				list3.addElement(u);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		titleList = new JList(list2);
		JScrollPane listScrollPane = new JScrollPane(titleList);
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
			
			selectedvalue = titleList.getSelectedValue().toString();
			for(int i = 0; i < list3.getSize(); i++){
				if(list3.get(i).getTitle().equals(selectedvalue)){
					App.currentRoom = list3.get(i);
				}
			}
			System.out.println("Enter room was pressed, proceeding...");
			SelectedRoomView.SelectedRoom();

		}
	}
}
