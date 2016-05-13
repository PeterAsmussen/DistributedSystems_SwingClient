package functionality.controllers.interfaces;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

import model.EventDTO;

public interface IEventController {
	
	EventDTO getEvent(String eventkey) throws IOException, ParseException;
	List<String> getEventKeyList(String roomkey) throws IOException, ParseException;
	List<EventDTO> getEventDTOList(String roomkey) throws IOException, ParseException;
	void createEvent(EventDTO e);
	void updateEvent(EventDTO e);
	

}
