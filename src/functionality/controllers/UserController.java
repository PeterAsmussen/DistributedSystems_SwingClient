package functionality.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.App;
import model.UserDTO;

public class UserController {
	
	JSONParser parser;
	
	public UserController() {
		parser = new JSONParser();
	}
	
	public UserDTO login(String username, String password) throws IOException {
		UserDTO user;
		JSONObject object = new JSONObject() {{
			put("TASK", "loginauth");
			put("USERNAME", username);
			put("PASSWORD", password);
		}}; 
		
		HttpURLConnection c = App.getHttpConnectionFromObject(object);
		BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
		JSONObject reply = new JSONObject();
		try {
			reply = (JSONObject) parser.parse(in.readLine());
			if(App.isReplySuccessful(reply)) {
				App.sessionKey = (String) reply.get("SESSIONKEY");
				// få user ud fra navn
				JSONObject obj = App.getUserJSON(username);
				c = App.getHttpConnectionFromObject(obj);
				in = new BufferedReader(new InputStreamReader(c.getInputStream()));
				reply = (JSONObject) parser.parse(in.readLine());
				user = App.jsonToUserDTO(reply);
				return user;
			} System.err.println("JSONObjektet indeholdt ikke \"REPLY\":\"succes\"");
		} catch (ParseException e) {
			System.err.println("Der er problemer med at parse JSONObjektet i UserController");
			e.printStackTrace();
		}
		return null;
	}
	
	public List<String> getUserRoomKeyList(String username) throws IOException {
		List<String> list = new ArrayList<>();
		JSONObject obj = App.getUserJSON(App.getCurrentUsername());
		HttpURLConnection c = App.getHttpConnectionFromObject(obj);
		BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
		
		String response = in.readLine();
		c.disconnect();
		in.close();
		JSONObject reply = new JSONObject();
		try {
			reply = (JSONObject) parser.parse(response);
		} catch (ParseException e) {
			System.err.println("Der var et problem med at parse i RoomController");
			e.printStackTrace();
		}
		
		if(reply.get("REPLY").equals("succes")) {
			String sub = reply.get("SUBBEDROOMS").toString();
			String[] rooms = getRoomStringArrayFromJSONRoomString(sub);
			list = Arrays.asList(rooms);
		} else System.err.println("JSONObjektet indeholdt ikke \"REPLY\":\"succes\"");
		
		return list;	
	}
	
	public List<String> getUserRoomTitleList(String username) throws IOException {
		List<String> keyList = getUserRoomKeyList(username);
		List<String> nameList = new ArrayList<>();
		BufferedReader in = null;
		String response;
		for(String s : keyList) {
			JSONObject obj = App.getRoomJSON(s);
			HttpURLConnection c = App.getHttpConnectionFromObject(obj);
			in = new BufferedReader(new InputStreamReader(c.getInputStream()));
			response = in.readLine();
			c.disconnect();
			in.close();
			JSONObject reply = new JSONObject();
			try {
				reply = (JSONObject) parser.parse(response);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if(reply.get("REPLY").equals("succes")){
				String title = reply.get("TITLE").toString();
				nameList.add(title);
			} else System.err.println("JSONObjektet indeholdt ikke \"REPLY\":\"succes\"");
		}
		in.close();
		return nameList;
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
