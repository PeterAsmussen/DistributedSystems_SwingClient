package client;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import functionality.controllers.EventController;
import functionality.controllers.RoomController;

public class SelectedRoom {

	public static JPanel selectedroomPanel;
	private JList eventList;
	private JScrollPane scrollPanel;
	public JButton btn1, btn2, btn3, btn4;
	RoomController roomcontroller = new RoomController();
	EventController eventcontroller = new EventController();
	HomeScreenView homescreenview = new HomeScreenView();
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
		JPanel btnPanel = new JPanel();
		JPanel listPanel = new JPanel();
		selectedroomPanel.setLayout(new GridBagLayout());
		selectedroomPanel.add(listPanel);
		selectedroomPanel.add(btnPanel);
		btnPanel.setLayout(new GridBagLayout());

		btn1 = new JButton();
		btn2 = new JButton();
		btn3 = new JButton();
		btn4 = new JButton();
		eventList = new JList();

		scrollPanel = new JScrollPane(eventList);

		listPanel.add(scrollPanel);		
		btnPanel.add(btn1);
		btnPanel.add(btn2);
		btnPanel.add(btn3);
		btnPanel.add(btn4);

		
	}

}
