package functionality.controllers.interfaces;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

import model.QuestionDTO;

public interface IQuestionController {
	
	QuestionDTO getQuestion(String questionkey) throws IOException, ParseException;
	List<String> getQuestionKeyList(String eventkey) throws IOException, ParseException;
	List<QuestionDTO> getQuestionDTOList(String eventkey) throws IOException, ParseException;
	void updateQuestion(QuestionDTO q);
	void createQuestion(QuestionDTO q);

}
