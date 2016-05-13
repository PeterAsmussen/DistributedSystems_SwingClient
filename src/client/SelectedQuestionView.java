package client;

import java.awt.EventQueue;

public class SelectedQuestionView {

	public static void SelectedQuestion() {		
		EventQueue.invokeLater(new Runnable() {		
			public void run() {		
				try {		
					SelectedRoomView window = new SelectedRoomView();	
					window.frame.setVisible(true);		
					frame.getContentPane().add();
				} catch (Exception e) {		
					e.printStackTrace();		
				}		
			}		
		});		
	}
	
}
