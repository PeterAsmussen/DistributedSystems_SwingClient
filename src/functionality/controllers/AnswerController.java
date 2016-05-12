package functionality.controllers;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

import functionality.controllers.interfaces.IAnswerController;
import model.EventDTO;

public class AnswerController implements IAnswerController {

	@Override
	public EventDTO getAnswer(String answerkey) throws IOException, ParseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAnswerKeyList(String questionkey) throws IOException, ParseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAnswerTitleList(String questionkey) throws IOException, ParseException {
		// TODO Auto-generated method stub
		return null;
	}

}
