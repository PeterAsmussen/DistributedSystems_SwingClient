package functionality.controllers.interfaces;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

import model.AnswerDTO;
import model.EventDTO;

public interface IAnswerController {
	
	AnswerDTO getAnswer(String answerkey) throws IOException, ParseException;
	List<String> getAnswerKeyList(String questionkey) throws IOException, ParseException;
	List<AnswerDTO> getAnswerDTOList(String questionkey) throws IOException, ParseException;
	boolean createAnswer(AnswerDTO a) throws IOException, ParseException;
	boolean updateAnswer(AnswerDTO a) throws IOException, ParseException;
	
}
