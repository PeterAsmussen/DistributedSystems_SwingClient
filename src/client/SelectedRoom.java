package client;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.json.simple.parser.ParseException;

import functionality.controllers.EventController;
import functionality.controllers.RoomController;
import model.App;
import model.RoomDTO;

public class SelectedRoom implements ActionListener {

	public static JPanel selectedroomPanel;
	private JList eventList;
	private JScrollPane scrollpanel;
	public JButton btnEnterEvent, btnCreateEvent;
	RoomController roomcontroller = new RoomController();
	EventController eventcontroller = new EventController();
	HomeScreenView homescreenview = new HomeScreenView();
	RoomDTO roomdto;
	private static JFrame frame;

	/**
	 * Create the application.
	 */

	public static void SelectedRoom() {		
		EventQueue.invokeLater(new Runnable() {		
			public void run() {		
				try {		
					SelectedRoom window = new SelectedRoom();	
					window.frame.setVisible(true);		
					frame.getContentPane().add(selectedroomPanel);
				} catch (Exception e) {		
					e.printStackTrace();		
				}		
			}		
		});		
	}

	public SelectedRoom() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(400, 220, 700, 350);
		frame.setTitle("Welcome to room: " + homescreenview.selectedvalue);

		selectedroomPanel = new JPanel();
		selectedroomPanel.setVisible(true);
		selectedroomPanel.setLayout(new GridLayout(2,0));

		JPanel btnPanel = new JPanel();
		JPanel listPanel = new JPanel();
		JScrollPane scrollpanel = new JScrollPane(listPanel);
		scrollpanel.setVisible(true);
		btnPanel.setLayout(new GridBagLayout());

		DefaultListModel<String> list2 = new DefaultListModel<String>();
		try {
			for(String u : eventcontroller.getEventTitleList(App.currentRoom.getRoomKey())){
				list2.addElement(u);		
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		btnEnterEvent = new JButton("Enter event");
		btnEnterEvent.addActionListener(this);
		btnCreateEvent = new JButton("Create event");
		btnCreateEvent.addActionListener(this);
		eventList = new JList(list2);
		scrollpanel = new JScrollPane(eventList);		
		
		btnPanel.add(btnEnterEvent);
		btnPanel.add(btnCreateEvent);

		selectedroomPanel.add(scrollpanel);
		selectedroomPanel.add(btnPanel);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if(cmd.equals("Enter Event")){
			/*
			 * do stuff
			 */
		}
		
		if(cmd.equals("Create event")){
			/*
			 * do stuff
			 */
		}
	}
}
