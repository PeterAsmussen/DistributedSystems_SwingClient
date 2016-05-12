package functionality.controllers.interfaces;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;

import model.RoomDTO;

public interface IRoomController {
	
	boolean createRoom(String roomTitle, String type) throws IOException, ParseException;
	List<String> getUserRoomKeyList(String username) throws IOException;
	List<String> getUserRoomTitleList(String username) throws IOException;
	RoomDTO getRoom(String roomkey) throws IOException, ParseException;

}
