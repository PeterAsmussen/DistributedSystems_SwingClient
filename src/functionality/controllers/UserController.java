package functionality.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;

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
		@SuppressWarnings("unchecked")
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
				//System.out.println("----->"+reply.toString());
				App.sessionKey = reply.get("SESSIONKEY").toString();
				// f� user ud fra navn
				JSONObject obj = JSONHelper.getUserJSON(username);
				c = App.getHttpConnectionFromObject(obj);
				in = new BufferedReader(new InputStreamReader(c.getInputStream()));
				reply = (JSONObject) parser.parse(in.readLine());
				reply = (JSONObject) parser.parse(reply.get("USER").toString());
				//System.out.println("###>"+reply.toString());
				
				user = JSONHelper.jsonToUserDTO(reply);
				App.currentUser = user;
				//System.out.println("Aaa"+ App.getCurrentUsername());
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
	
	public boolean updateUser(UserDTO u) throws IOException, ParseException {
		JSONObject obj = JSONHelper.getUpdateUserJSON(u);
		obj.put("SESSIONKEY", App.sessionKey);
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
		in.close();
		
		JSONObject reply = (JSONObject) parser.parse(response);
		
		if(reply.get("REPLY").equals("succes")) {
			return true;
		} return false;
		
	}

}

