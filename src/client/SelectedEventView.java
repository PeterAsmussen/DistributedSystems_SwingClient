package client;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SelectedEventView {
	
	private static JFrame frame;
	public static JPanel selectedEventPanel;
	public JButton btn1, btn2, btn3;
	public JPanel panel1, panel2;
	
	public static void SelectedEvent() {		
		EventQueue.invokeLater(new Runnable() {		
			public void run() {		
				try {		
					SelectedEventView window = new SelectedEventView();	
					window.frame.setVisible(true);		
					frame.getContentPane().add(selectedEventPanel);
				} catch (Exception e) {		
					e.printStackTrace();		
				}		
			}		
		});		
	}
	
	

}
