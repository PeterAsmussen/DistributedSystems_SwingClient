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
		} else System.err.println("JSONObjektet indeholdt ikke \"REPLY\":\"succes\"");
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
		}else System.err.println("JSONObjektet indeholdt ikke \"REPLY\":\"succes\"");
		return null;
	}
	
	public List<EventDTO> getEventDTOList(String roomkey) throws IOException, ParseException {
		List<String> keyList = getEventKeyList(roomkey);
		List<EventDTO> eventList = new ArrayList<>();
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
				eventList.add(JSONHelper.jsonToEventDTO(reply));
			}else System.err.println("JSONObjektet indeholdt ikke \"REPLY\":\"succes\"");
		}
		return eventList;
	}

	@Override
	public boolean createEvent(EventDTO e) throws IOException, ParseException {
		JSONObject obj = JSONHelper.getCreateEventJSON(e.getTitle(), e.getTimeStamp());
		obj.put("SESSIONKEY", App.sessionKey);
		HttpURLConnection con = App.getHttpConnectionFromObject(obj);
		con.setDoOutput(true);
		con.setRequestMethod("PUT");
		OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
		out.write(obj.toString());
		out.close();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String response = in .readLine();
		con.disconnect();
		
		JSONObject reply = (JSONObject) parser.parse(response);
		if (reply.get("REPLY").equals("succes")) {
			reply = (JSONObject) reply.get("EVENT");
			String answerkey = reply.get("EVENTKEY").toString();
			App.currentRoom.addEventKey(answerkey);
			RoomController rc = new RoomController();
			rc.updateRoom(App.currentRoom);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateEvent(EventDTO e) throws IOException, ParseException {
		JSONObject obj = JSONHelper.getUpdateEventJSON(e.getQuestions(), e.getTimeStamp(), e.getEventKey(), e.getCreator());
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
