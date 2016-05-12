package functionality.controllers;

import java.util.List;

import org.json.simple.parser.JSONParser;

import functionality.controllers.interfaces.IQuestionController;
import model.QuestionDTO;

public class QuestionController implements IQuestionController {
	
	JSONParser parser;
	
	public QuestionController() {
		parser = new JSONParser(); 
	}

	@Override
	public QuestionDTO getQuestion(String questionkey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getQuestionKeyList(String eventkey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getQuestionTitleList(String eventkey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateQuestion(QuestionDTO q) {
		// TODO Auto-generated method stub
		
	}
	
	

}
