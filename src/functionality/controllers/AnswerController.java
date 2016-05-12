package functionality.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
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
		}
		return null;
	}

	@Override
	public List<String> getAnswerKeyList(String questionkey) throws IOException, ParseException {
		List<String> list = new ArrayList<>();
		JSONObject obj = JSONHelper.getQuestionJSON(questionkey);
		return null;
	}

	@Override
	public List<String> getAnswerTitleList(String questionkey) throws IOException, ParseException {
		// TODO Auto-generated method stub
		return null;
	}

}
