package functionality.controllers.interfaces;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

import model.EventDTO;

public interface IEventController {
	
	EventDTO getEvent(String eventkey) throws IOException, ParseException;
	List<String> getEventKeyList(String roomkey) throws IOException, ParseException;
	List<String> getEventTitleList(String roomkey) throws IOException, ParseException;
	

}
