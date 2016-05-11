package functionality.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.App;
import model.RoomDTO;
import model.UserDTO;

public class RoomController {
	
	JSONParser parser;
	
	public RoomController() {
		parser = new JSONParser();
	}
	
	public RoomDTO createRoom(String roomTitle, String type) throws IOException, ParseException {
		JSONObject obj = App.getCreateRoomJSON(roomTitle, type);
		HttpURLConnection con = App.getHttpConnectionFromObject(obj);
		
        con.setDoOutput(true);
        con.setRequestMethod("PUT");
        OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
        out.write(obj.toString());
        out.close();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		
        
        String response = "";
        response = in.readLine();
        
        System.out.println("-------------------------------");
        System.out.println("Create room: "+response);
        System.out.println("-------------------------------");
        
        JSONObject recieve = (JSONObject) parser.parse(response); 
        String roomkey = recieve.get("ROOMKEY").toString();
        App.currentUser.addRoomKey(roomkey);
        
        JSONObject send = App.currentUser.toJSONObject();
        con = App.getHttpConnectionFromObject(send);
        
        return null;
	
	
	}
}
