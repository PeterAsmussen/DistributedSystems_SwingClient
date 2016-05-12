package functionality.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import functionality.JSONHelper;
import model.App;
import model.RoomDTO;

public class RoomController {
	
	JSONParser parser;
	
	public RoomController() {
		parser = new JSONParser();
	}
	
	public boolean createRoom(String roomTitle, String type) throws IOException, ParseException {
		JSONObject obj = JSONHelper.getCreateRoomJSON(roomTitle, type);
		HttpURLConnection con = App.getHttpConnectionFromObject(obj);
		
        con.setDoOutput(true);
        con.setRequestMethod("PUT");
        OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
        out.write(obj.toString());
        out.close();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        
        String response;
        response = in.readLine();
        con.disconnect();
        
        System.out.println("-------------------------------");
        System.out.println("Create room: "+response);
        System.out.println("-------------------------------");
        
        JSONObject reply = (JSONObject) parser.parse(response); 
        reply = (JSONObject) reply.get("ROOM");
        System.out.println("reply i createRoom: "+reply);
        String roomkey = reply.get("ROOMKEY").toString();
        App.currentUser.addRoomKey(roomkey);
        
        UserController uc = new UserController();
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        uc.updateUser(App.currentUser);

        return true;
	}
	
	
	public List<String> getUserRoomKeyList(String username) throws IOException {
		List<String> list = new ArrayList<>();
		JSONObject obj = JSONHelper.getUserJSON(username);
		HttpURLConnection c = App.getHttpConnectionFromObject(obj);
		//System.out.println("yo mama");
		BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
		//System.out.println("yo papa");
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
			//System.out.println("this shit" +reply.toString());
			reply = (JSONObject) reply.get("USER");
			String sub = reply.get("SUBBEDROOMS").toString();
			//System.out.println("more shit" + reply.toString());
			//System.out.println("username: "+ App.getCurrentUsername());
			String[] rooms = JSONHelper.getRoomStringArrayFromJsonRoomString(sub);
			list = Arrays.asList(rooms);
		} else System.err.println("JSONObjektet indeholdt ikke \"REPLY\":\"succes\"");
		
		return list;	
	}
	
	public List<String> getUserRoomTitleList(String username) throws IOException {
		List<String> keyList = getUserRoomKeyList(username);
		System.out.println("getUserRoomTitleList keyList:"+keyList.toString());
		List<String> nameList = new ArrayList<>();
		BufferedReader in;
		String response;
		for(String s : keyList) {
			//System.out.println("string:"+ s);
			JSONObject obj = JSONHelper.getRoomJSON(s);
			//System.out.println("object: "+obj.toString());
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
	
	
}
