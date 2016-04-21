package client;

import javax.swing.JFrame;

public class SelectedRoom {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public SelectedRoom() {
		initialize();
	}

	/*
	 * TODO:
	 * SelectedRoom-panelet tilfoejes naar data-manipulation fra HomeScreen er implementeret
	 */
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
