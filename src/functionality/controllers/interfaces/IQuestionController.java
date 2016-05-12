package functionality.controllers.interfaces;

import java.util.List;

import model.QuestionDTO;

public interface IQuestionController {
	
	QuestionDTO getQuestion(String questionkey);
	List<String> getQuestionKeyList(String eventkey);
	List<String> getQuestionTitleList(String eventkey);
	void updateQuestion(QuestionDTO q);

}
