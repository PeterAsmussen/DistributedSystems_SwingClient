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
import functionality.controllers.interfaces.IQuestionController;
import model.App;
import model.EventDTO;
import model.QuestionDTO;

public class QuestionController implements IQuestionController {
	
	JSONParser parser;
	
	public QuestionController() {
		parser = new JSONParser(); 
	}

	@Override
	public QuestionDTO getQuestion(String questionkey) throws IOException, ParseException {
		QuestionDTO question;
		JSONObject obj = JSONHelper.getQuestionJSON(questionkey);
		HttpURLConnection con = App.getHttpConnectionFromObject(obj);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String response = in.readLine();
		con.disconnect();
		in.close();
		JSONObject reply = (JSONObject) parser.parse(response);
		if(reply.get("REPLY").equals("succes")) {
			reply = (JSONObject) reply.get("QUESTION");
			question = JSONHelper.jsonToQuestionDTO(obj);
			return question;
		}
		return null;
	}

	@Override
	public List<String> getQuestionKeyList(String eventkey) throws IOException, ParseException {
		List<String> list = new ArrayList<>();
		JSONObject obj = JSONHelper.getEventJSON(eventkey);
		HttpURLConnection con = App.getHttpConnectionFromObject(obj);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String response = in.readLine();
		con.disconnect();
		in.close();
		JSONObject reply = (JSONObject) parser.parse(response);
		if(reply.get("REPLY").equals("succes")) {
			reply = (JSONObject) reply.get("EVENT");
			String questions = reply.get("QUESTIONKEYS").toString();
			list = new ArrayList<>(Arrays.asList(JSONHelper.getStringArrayFromJsonListString(questions)));
			return list;
		}else System.err.println("JSONObjektet indeholdt ikke \"REPLY\":\"succes\"");
		return null;
	}

	@Override
	public List<QuestionDTO> getQuestionDTOList(String eventkey) throws IOException, ParseException {
		List<String> keyList = getQuestionKeyList(eventkey);
		List<QuestionDTO> eventList = new ArrayList<>();
		String response;
		for(String s : keyList) {
			JSONObject obj = JSONHelper.getQuestionJSON(s);
			HttpURLConnection con = App.getHttpConnectionFromObject(obj);
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			response = in.readLine();
			con.disconnect();
			in.close();
			JSONObject reply = (JSONObject) parser.parse(response);
			if(reply.get("REPLY").equals("succes")) {
				reply = (JSONObject) reply.get("QUESTION");
				eventList.add(JSONHelper.jsonToQuestionDTO(reply));
			}else System.err.println("JSONObjektet indeholdt ikke \"REPLY\":\"succes\"");
		}
		return eventList;
	}

	@Override
	public void updateQuestion(QuestionDTO q) {
		/*
		 * Work in progress
		 */
		
	}

	@Override
	public void createQuestion(QuestionDTO q) {
		/*
		 * Work in progress
		 */
		
	}
	
	

}
