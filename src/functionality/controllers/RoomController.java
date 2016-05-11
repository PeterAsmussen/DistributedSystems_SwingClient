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
      
        String response = "lol";
        response = in.readLine();
        
        System.out.println("-------------------------------");
        System.out.println("Create room: "+response);
        System.out.println("-------------------------------");
        
        JSONObject reply = (JSONObject) parser.parse(response); 
        reply = (JSONObject) reply.get("ROOM");
        String roomkey = reply.get("ROOMKEY").toString();
        App.currentUser.addRoomKey(roomkey);
        
        JSON
        out = new OutputStreamWriter(con.getOutputStream());
        out.write(send.toString());
        
        out.close();

        return null;
	
	}
}
