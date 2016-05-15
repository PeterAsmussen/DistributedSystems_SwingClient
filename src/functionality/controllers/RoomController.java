package functionality.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import functionality.JSONHelper;
import functionality.controllers.interfaces.IRoomController;
import model.App;
import model.RoomDTO;

public class RoomController implements IRoomController {
	
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
        JSONObject reply = (JSONObject) parser.parse(response); 
        reply = (JSONObject) reply.get("ROOM");
        System.out.println("reply i createRoom: "+reply);
        String roomkey = reply.get("ROOMKEY").toString();
        App.currentUser.addRoomKey(roomkey);
        UserController uc = new UserController();
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        uc.updateUser(App.currentUser);

        return true;
	}
	
	
	public List<String> getUserRoomKeyList(String username) throws IOException {
		List<String> list = new ArrayList<>();
		JSONObject obj = JSONHelper.getUserJSON(username);
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
			reply = (JSONObject) reply.get("USER");
			String sub = reply.get("SUBBEDROOMS").toString();
			String[] rooms = JSONHelper.getStringArrayFromJsonListString(sub);
			list = Arrays.asList(rooms);
			list = new ArrayList<>(list);
			return list;
		} else System.err.println("JSONObjektet indeholdt ikke \"REPLY\":\"succes\"");
		return null;	
	}
	
	public List<RoomDTO> getRoomDTOList(String username) throws IOException {
		List<String> keyList = getUserRoomKeyList(username);
		List<RoomDTO> nameList = new ArrayList<>();
		BufferedReader in;
		String response;
		for(String s : keyList) {
			JSONObject obj = JSONHelper.getRoomJSON(s);
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
				nameList.add(JSONHelper.jsonToRoomDTO(reply));
			} else System.err.println("JSONObjektet indeholdt ikke \"REPLY\":\"succes\"");
		}
		return nameList;
	}
	
	public RoomDTO getRoom(String roomkey) throws IOException, ParseException {
		RoomDTO room = null;
		JSONObject obj = JSONHelper.getRoomJSON(roomkey);
		HttpURLConnection con = App.getHttpConnectionFromObject(obj);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String response = in.readLine();
		con.disconnect();
		in.close();
		JSONObject reply = (JSONObject) parser.parse(response);
		if(reply.get("REPLY").equals("succes")) {
			reply = (JSONObject) reply.get("ROOM");
			room = JSONHelper.jsonToRoomDTO(reply);
			return room;
		}
		
		return room;
	}

	@Override
	public boolean updateRoom(RoomDTO r) throws IOException, ParseException {
		JSONObject obj = JSONHelper.getUpdateRoomJSON(r.getEventKeys(), r.getRoomKey(), r.getTitle(), r.getOwner());
		obj.put("SESSIONKEY", App.sessionKey);
		HttpURLConnection con = App.getHttpConnectionFromObject(obj);
		con.setDoOutput(true);
		con.setRequestMethod("PUT");
		
		OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
		out.write(obj.toString());
		out.close();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		JSONObject reply = (JSONObject) parser.parse(in.readLine());
		
		if(reply.get("REPLY").equals("succes")) {
			return true;
		} return false;
		
	}
	
	
}
