package client;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jgoodies.forms.factories.DefaultComponentFactory;

public class HomeScreen extends JPanel implements ActionListener{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void HomeScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeScreen window = new HomeScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HomeScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAvailableRooms = DefaultComponentFactory.getInstance().createTitle("Available rooms");
		lblAvailableRooms.setBounds(19, 20, 122, 16);
		frame.getContentPane().add(lblAvailableRooms);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
