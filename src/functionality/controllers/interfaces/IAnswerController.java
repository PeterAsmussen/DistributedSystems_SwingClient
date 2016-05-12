package functionality.controllers.interfaces;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

import model.AnswerDTO;
import model.EventDTO;

public interface IAnswerController {
	
	EventDTO getAnswer(String answerkey) throws IOException, ParseException;
	List<String> getAnswerKeyList(String questionkey) throws IOException, ParseException;
	List<String> getAnswerTitleList(String questionkey) throws IOException, ParseException;
	
}
