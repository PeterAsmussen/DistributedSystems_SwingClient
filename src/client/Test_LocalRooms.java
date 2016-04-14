package client;

import java.util.ArrayList;

public class Test_LocalRooms {

	ArrayList<String> rooms = new ArrayList<String>();
	ArrayList<String> questions = new ArrayList<String>();
	
	public ArrayList<String> getRooms() {
		return rooms;
	}


	public void setRooms(ArrayList<String> rooms) {
		this.rooms = rooms;
	}


	public void questionIncrement(String question){
		questions.add(question); 
	}
	
	public ArrayList<String> getQuestions(){
		return questions;
	}
}