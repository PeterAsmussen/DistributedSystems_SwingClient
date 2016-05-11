package functionality.controllers;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import model.App;
import model.RoomDTO;

public class RoomController {
	
	JSONParser parser;
	
	public RoomController() {
		parser = new JSONParser();
	}
	
	public RoomDTO createRoom(String roomTitle, String type) {
		JSONObject obj = App.getCreateRoomJSON(roomTitle, type);
		return null;
	}

}
