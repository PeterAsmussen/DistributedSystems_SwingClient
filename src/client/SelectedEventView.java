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

import functionality.controllers.QuestionController;
import model.App;
import model.QuestionDTO;

public class SelectedEventView implements ActionListener{
	
	private static JFrame frame;
	public static JPanel selectedEventPanel;
	QuestionController qcontroller = new QuestionController();
	SelectedRoomView selectedroomview = new SelectedRoomView();
	public JButton btn1, btn2, btn3;
	public JPanel panel1, btnPanel;
	private JList questionList;
	DefaultListModel<String> list2;
	DefaultListModel<QuestionDTO> list3;
	
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
	
	public SelectedEventView(){
		initialize();
	}
	
	private void initialize(){
		
		frame = new JFrame();
		frame.setBounds(420, 230, 700, 350);
		frame.setTitle("Welcome to event: " + SelectedRoomView.selectedvalue);
		
		selectedEventPanel = new JPanel();	
		selectedEventPanel.setLayout(new GridLayout(2,0));

		btnPanel = new JPanel();
		btnPanel.setLayout(new GridBagLayout());
		
		btn1 = new JButton("Button1");
		btn1.addActionListener(this);
		btn2 = new JButton("Button2");
		btn2.addActionListener(this);
		btn3 = new JButton("Button3");
		btn3.addActionListener(this);
		
		list2 = new DefaultListModel<String>();
		list3 = new DefaultListModel<QuestionDTO>();
		try {
			for(QuestionDTO u : qcontroller.getQuestionDTOList(App.currentEvent.getEventKey())){
				list2.addElement(u.getTitle());		
				list3.addElement(u);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
		}
		
		questionList = new JList(list2);
		JScrollPane scrollpanel = new JScrollPane(panel1);
		scrollpanel.setVisible(true);
		btnPanel.add(btn1);
		btnPanel.add(btn2);
		btnPanel.add(btn3);
		
		selectedEventPanel.add(scrollpanel);
		selectedEventPanel.add(btnPanel);
		selectedEventPanel.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
	}	
}
