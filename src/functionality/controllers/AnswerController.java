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
import functionality.controllers.interfaces.IAnswerController;
import model.AnswerDTO;
import model.App;

public class AnswerController implements IAnswerController {
	
	JSONParser parser;
	
	public AnswerController() {
		parser = new JSONParser();
	}

	@Override
	public AnswerDTO getAnswer(String answerkey) throws IOException, ParseException {
		AnswerDTO answer;
		JSONObject obj = JSONHelper.getAnswerJSON(answerkey);
		HttpURLConnection con = App.getHttpConnectionFromObject(obj);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String response = in.readLine();
		con.disconnect();
		in.close();
		JSONObject reply = (JSONObject) parser.parse(response);
		if(reply.get("REPLY").equals("succes")) {
			reply = (JSONObject) reply.get("ANSWER");
			answer = JSONHelper.jsonToAnswerDTO(reply);
			return answer;
		} else System.err.println("JSONObjektet indeholdt ikke \"REPLY\":\"succes\"");
		return null;
	}

	@Override
	public List<String> getAnswerKeyList(String questionkey) throws IOException, ParseException {
		List<String> list = new ArrayList<>();
		JSONObject obj = JSONHelper.getQuestionJSON(questionkey);
		HttpURLConnection con = App.getHttpConnectionFromObject(obj);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String response = in.readLine();
		con.disconnect();
		in.close();
		JSONObject reply = (JSONObject) parser.parse(response);
		if(reply.get("REPLY").equals("succes")) {
			reply = (JSONObject) reply.get("QUESTION");
			String answers = reply.get("ANSWERKEYS").toString();
			list = new ArrayList<>(Arrays.asList(JSONHelper.getStringArrayFromJsonListString(answers)));
			return list;
		} else System.err.println("JSONObjektet indeholdt ikke \"REPLY\":\"succes\"");
		return null;
	}

	@Override
	public List<AnswerDTO> getAnswerDTOList(String questionkey) throws IOException, ParseException {
		List<String> keyList = getAnswerKeyList(questionkey);
		List<AnswerDTO> answerList = new ArrayList<>();
		BufferedReader in;
		String response;
		for(String s : keyList) {
			JSONObject obj = JSONHelper.getAnswerJSON(s);
			HttpURLConnection con = App.getHttpConnectionFromObject(obj);
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			response = in.readLine();
			con.disconnect();
			in.close();
			JSONObject reply = (JSONObject) parser.parse(response);
			if(reply.get("REPLY").equals("succes")) {
				reply = (JSONObject) reply.get("ANSWER");
				answerList.add(JSONHelper.jsonToAnswerDTO(reply));
			}else System.err.println("JSONObjektet indeholdt ikke \"REPLY\":\"succes\"");
		}
		return answerList;
	}

	@Override
	public boolean createAnswer(AnswerDTO a) throws IOException, ParseException {
		JSONObject obj = JSONHelper.getCreateAnswerJSON(a.getBody(), a.getTimeStamp());
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
			reply = (JSONObject) reply.get("ANSWER");
			String answerkey = reply.get("ANSWERKEY").toString();
			App.currentQuestion.addAnswerKey(answerkey);
			QuestionController qc = new QuestionController();
			qc.updateQuestion(App.currentQuestion);
			return true;
		}
		return false;
		
	}

	@Override
	public boolean updateAnswer(AnswerDTO a) throws IOException, ParseException {
		JSONObject obj = JSONHelper.getUpdateAnswerJSON(a.getAnswerKey(), a.getBody(), a.getTimeStamp(), a.getSender());
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
