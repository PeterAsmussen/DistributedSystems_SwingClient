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

import functionality.JSONHelper;
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
				System.out.println("----->"+reply.toString());
				App.sessionKey = reply.get("SESSIONKEY").toString();
				// f� user ud fra navn
				JSONObject obj = JSONHelper.getUserJSON(username);
				c = App.getHttpConnectionFromObject(obj);
				in = new BufferedReader(new InputStreamReader(c.getInputStream()));
				reply = (JSONObject) parser.parse(in.readLine());
				reply = (JSONObject) parser.parse(reply.get("USER").toString());
				System.out.println("###>"+reply.toString());
				
				user = JSONHelper.jsonToUserDTO(reply);
				App.currentUser = user;
				System.out.println("Aaa"+ App.getCurrentUsername());
				return user;
			} System.err.println("JSONObjektet indeholdt ikke \"REPLY\":\"succes\"");
		} catch (ParseException e) {
			System.err.println("Der er problemer med at parse JSONObjektet i UserController");
			e.printStackTrace();
		}
		return null;
	}
	
	public void logout() {
		App.currentUser = null;
	}
	
	public List<String> getUserRoomKeyList(String username) throws IOException {
		List<String> list = new ArrayList<>();
		JSONObject obj = App.getUserJSON(username);
		HttpURLConnection c = App.getHttpConnectionFromObject(obj);
		System.out.println("yo mama");
		BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
		System.out.println("yo papa");
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
			System.out.println("this shit" +reply.toString());
			reply = (JSONObject) reply.get("USER");
			String sub = reply.get("SUBBEDROOMS").toString();
			System.out.println("more shit" + reply.toString());
			System.out.println("username: "+ App.getCurrentUsername());
			String[] rooms = getRoomStringArrayFromJSONRoomString(sub);
			list = Arrays.asList(rooms);
		} else System.err.println("JSONObjektet indeholdt ikke \"REPLY\":\"succes\"");
		
		return list;	
	}
	
	public List<String> getUserRoomTitleList(String username) throws IOException {
		List<String> keyList = getUserRoomKeyList(username);
		System.out.println(keyList.toString());
		List<String> nameList = new ArrayList<>();
		BufferedReader in;
		String response;
		for(String s : keyList) {
			System.out.println("string:"+ s);
			JSONObject obj = App.getRoomJSON(s);
			System.out.println("object: "+obj.toString());
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
				reply = (JSONObject) reply.get("ROOM");
				String title = reply.get("TITLE").toString();
				nameList.add(title);
			} else System.err.println("JSONObjektet indeholdt ikke \"REPLY\":\"succes\"");
		}
		//in.close();
		return nameList;
	}
	
	private String[] getRoomStringArrayFromJSONRoomString(String s) {
		String str;
		str = s.replace('[', '_');
		str = str.replace(']', '_');
		str = str.replace('"', '_');
		str = str.replaceAll("_","");
		return str.split(",");
	}

}

