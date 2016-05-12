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
import functionality.controllers.interfaces.IEventController;
import model.App;
import model.EventDTO;

public class EventController implements IEventController{
	
	JSONParser parser;
	
	public EventController() {
		parser = new JSONParser();
	}
	
	public EventDTO getEvent(String eventkey) throws IOException, ParseException {
		EventDTO event;
		JSONObject obj = JSONHelper.getEventJSON(eventkey);
		HttpURLConnection con = App.getHttpConnectionFromObject(obj);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String response = in.readLine();
		con.disconnect();
		in.close();
		JSONObject reply = (JSONObject) parser.parse(response);
		if(reply.get("REPLY").equals("succes")) {
			reply = (JSONObject) reply.get("EVENT");
			event = JSONHelper.jsonToEventDTO(reply);
			return event;
		}
		return null;
	}
	
	public List<String> getEventKeyList(String roomkey) throws IOException, ParseException {
		List<String> list = new ArrayList<>();
		JSONObject obj = JSONHelper.getRoomJSON(roomkey);
		HttpURLConnection con = App.getHttpConnectionFromObject(obj);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String response = in.readLine();
		con.disconnect();
		in.close();
		JSONObject reply = (JSONObject) parser.parse(response);
		if(reply.get("REPLY").equals("succes")) {
			reply = (JSONObject) reply.get("ROOM");
			String events = reply.get("EVENTKEYS").toString();
			list = new ArrayList<>(Arrays.asList(JSONHelper.getStringArrayFromJsonListString(events)));
			return list;
		}
		return null;
	}
	
	public List<String> getEventTitleList(String roomkey) throws IOException, ParseException {
		List<String> keyList = getEventKeyList(roomkey);
		List<String> nameList = new ArrayList<>();
		BufferedReader in;
		String response;
		for(String s : keyList) {
			JSONObject obj = JSONHelper.getEventJSON(s);
			HttpURLConnection con = App.getHttpConnectionFromObject(obj);
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			response = in.readLine();
			con.disconnect();
			in.close();
			JSONObject reply = (JSONObject) parser.parse(response);
			if(reply.get("REPLY").equals("succes")) {
				reply = (JSONObject) reply.get("EVENT");
				String title = reply.get("TITLE").toString();
				nameList.add(title);
				return nameList;
			}
		}
		return null;
	}

}
