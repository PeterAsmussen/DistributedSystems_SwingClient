package functionality.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import client.CreateRoom;
import client.LoginScreen;
import functionality.LoginFunc;
import model.App;

public class RoomController {
	
	private HttpURLConnection connection;
	private CreateRoom view;
	private JSONParser parser;
	
	public RoomController(CreateRoom view) {
		this.view = view;	
	}
	
	private HttpURLConnection getConnectionFromObject(JSONObject obj) throws IOException {
		String message = "?logininfo="+obj.toString();
		URL url = new URL("http://52.58.112.107:8080/HelpingTeacherServer2/HTSservlet"+message);
		return (HttpURLConnection) url.openConnection();
	}
	
	public List<String> getRoomKeyList(String username) throws IOException {
		List<String> list = new ArrayList<>();
		JSONObject obj = new JSONObject();
		try {
			obj.put("TASK", "getuser");
			obj.put("USERNAME", App.currentUser);
			obj.put("SESSIONKEY", LoginFunc.sessionkey);
			obj.put("GETNAME" , LoginScreen.username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		BufferedReader in;
		String response = "";

		connection = getConnectionFromObject(obj);
		in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		response = in.readLine();
		
		JSONObject received = new JSONObject();
		try {
			received = (JSONObject) parser.parse(response);
		} catch (ParseException e) {
			System.err.println("Der var et problem med at parse i RoomController");
			e.printStackTrace();
		}
		
		String[] rooms;
		if(received.get("REPLY").equals("succes")) {
			String subbedRoomString = received.get("SUBBEDROOMS").toString();
			rooms = getRoomStringArrayFromJSONRoomString(subbedRoomString);
			list = Arrays.asList(rooms);
		}
		return list;
		
	}
	
	public List<String> getRoomList(String username) throws IOException {
		List<String> keyList = getRoomKeyList(username);
		List<String> nameList
		for(String s : keyList) {
			
		}
		return null;
	}
	
	private String[] getRoomStringArrayFromJSONRoomString(String s) {
		String str;
		str = s.replace('[', '_');
		str = str.replace(']', '_');
		str = str.replace('"', '_');
		str.replace("_", "");
		return str.split(",");
	}

}
