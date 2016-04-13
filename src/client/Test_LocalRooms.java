package client;

import java.util.ArrayList;
import java.util.List;

public class Test_LocalRooms {

	
	List<String> rooms = new ArrayList<String>();
	List<String> questions = new ArrayList<String>();
	
	public void roomIncrement(String roomName){
		rooms.add(roomName);		
	}
	
	
	public List<String> getRooms() {
		return rooms;
	}


	public void setRooms(ArrayList<String> rooms) {
		this.rooms = rooms;
	}


	public void questionIncrement(String question){
		questions.add(question); 
	}
	
	public List<String> getQuestions(){
		return questions;
	}
}