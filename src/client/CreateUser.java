package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class CreateUser extends JPanel implements ActionListener {
	
	public JPanel createUserPanel;
	
	public CreateUser(){
		initialize();
	}

	private void initialize(){
		createUserPanel = new JPanel();
		createUserPanel.setBounds(200, 200, 900, 450);
	
		//Alt der skal bruges er et username og et password, saa er brugeren oprettet
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
