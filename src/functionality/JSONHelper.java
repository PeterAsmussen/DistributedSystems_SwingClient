package functionality;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;

import model.AnswerDTO;
import model.App;
import model.EventDTO;
import model.QuestionDTO;
import model.RoomDTO;
import model.UserDTO;

@SuppressWarnings("unchecked")
public class JSONHelper {

	
	public static UserDTO jsonToUserDTO(JSONObject obj) {
		if (isJsonUser(obj)) {
			String username = obj.get("USERNAME").toString().trim();
			String email = obj.get("EMAIL").toString().trim();
			String firstname = obj.get("FIRSTNAME").toString().trim();
			String lastname = obj.get("LASTNAME").toString().trim();
			String password = obj.get("PASSWORD").toString().trim();
			String subbed = obj.get("SUBBEDROOMS").toString();
			List<String> subbedList = Arrays.asList(getStringArrayFromJsonListString(subbed));
			subbedList = new ArrayList<>(subbedList);
			return new UserDTO(username, email, firstname, lastname, password, subbedList);
			// Kan man bare caste subbedrooms til en List?
		} return null;
	}
	
	public static boolean isJsonUser(JSONObject obj) {
		if(!obj.containsKey("USERNAME")) return false;
		if(!obj.containsKey("EMAIL")) return false;
		if(!obj.containsKey("FIRSTNAME")) return false;
		if(!obj.containsKey("LASTNAME")) return false;
		if(!obj.containsKey("PASSWORD")) return false;
		if(!obj.containsKey("SUBBEDROOMS")) return false;
		return true;
	}
	
	public static EventDTO jsonToEventDTO(JSONObject obj) {
		if(isJsonEvent(obj)) {
			String title = obj.get("TITLE").toString();
			String creator = obj.get("CREATOR").toString();
			String timestamp = obj.get("TIMESTAMP").toString();
			String eventkey = obj.get("EVENTKEY").toString();
			String questionsString = obj.get("QUESTIONKEYS").toString();
			List<String> questions = Arrays.asList(getStringArrayFromJsonListString(questionsString));
			questions = new ArrayList<>(questions);
			return new EventDTO(title, timestamp, eventkey, creator, questions);
		} return null;
	}
	
	public static boolean isJsonEvent(JSONObject obj) {
		if(!obj.containsKey("CREATOR")) return false;
		if(!obj.containsKey("TIMESTAMP")) return false;
		if(!obj.containsKey("EVENTKEY")) return false;
		if(!obj.containsKey("QUESTIONKEYS")) return false;
		if(!obj.containsKey("TITLE")) return false;
		return true;
	}
	
	public static RoomDTO jsonToRoomDTO(JSONObject obj) {
		if(isJsonRoom(obj)) {
			String owner = obj.get("OWNER").toString();
			String roomkey = obj.get("ROOMKEY").toString();
			String title = obj.get("TITLE").toString();
			String type = obj.get("TYPE").toString();
			String events = obj.get("EVENTKEYS").toString();
			List<String> eventsList = Arrays.asList(getStringArrayFromJsonListString(events));
			eventsList = new ArrayList<>(eventsList);
			return new RoomDTO(title, roomkey, owner, type, eventsList);
		} return null;
	}
	
	public static boolean isJsonRoom(JSONObject obj) {
		if(!obj.containsKey("ROOMKEY")) return false;
		if(!obj.containsKey("OWNER")) return false;
		if(!obj.containsKey("EVENTKEYS")) return false;
		if(!obj.containsKey("TYPE")) return false;
		if(!obj.containsKey("TITLE")) return false;
		return true;
		
	}
	
	public static AnswerDTO jsonToAnswerDTO(JSONObject obj) {
		if(isJsonAnswer(obj)) {
			String answerkey = obj.get("ANSWERKEY").toString();
			String body = obj.get("BODY").toString();
			String timestamp = obj.get("TIMESTMAP").toString();
			String sender = obj.get("SENDER").toString();
			return new AnswerDTO(answerkey, body, timestamp, sender);
		} return null;
	}
	
	public static boolean isJsonAnswer(JSONObject obj) {
		if(!obj.containsKey("ANSWERKEY")) return false;
		if(!obj.containsKey("BODY")) return false;
		if(!obj.containsKey("TIMESTAMP")) return false;
		if(!obj.containsKey("SENDER")) return false;
		return true;
	}
	
	public static QuestionDTO jsonToQuestionDTO(JSONObject obj) {
		if(isJsonQuestion(obj)) {
			String title = obj.get("TITLE").toString();
			String body = obj.get("BODY").toString();
			String timeStamp = obj.get("TIMESTAMP").toString();
			String questionkey = obj.get("QUESTIONKEY").toString();
			String sender = obj.get("SENDER").toString();
			String answers = obj.get("ANSWERKEYS").toString();
			List<String> list = Arrays.asList(getStringArrayFromJsonListString(answers));
			list = new ArrayList<>(list);
			return new QuestionDTO(title, body, timeStamp, questionkey, sender, list);
		} return null;
	}
	
	public static boolean isJsonQuestion(JSONObject obj) {
		if(!obj.containsKey("QUESTIONKEY")) return false;
		if(!obj.containsKey("TITLE")) return false;
		if(!obj.containsKey("BODY")) return false;
		if(!obj.containsKey("SENDER")) return false;
		if(!obj.containsKey("TIMESTAMP")) return false;
		if(!obj.containsKey("ANSWERKEYS")) return false;
 		return true;
	}
	
	public static JSONObject getUserJSON(String username) {
		return new JSONObject() {{
			put("TASK", "getuser");
			put("USERNAME", username);
			put("SESSIONKEY", App.sessionKey);
			put("GETNAME", username);
		}};
	}
	
	public static JSONObject getRoomJSON(String roomKey) {
		return new JSONObject() {{
			put("TASK", "getroom");
			put("USERNAME", App.getCurrentUsername());
			put("SESSIONKEY", App.sessionKey);
			put("ROOMKEY", roomKey);
		}};
	}
	
	public static JSONObject getQuestionJSON(String questionkey) {
		return new JSONObject() {{
			
		}};
	}
	
	public static JSONObject getEventJSON(String eventkey) {
		return new JSONObject() {{
			put("TASK", "getevent");
			put("USERNAME", App.getCurrentUsername());
			put("SESSIONKEY", App.sessionKey);
			put("EVENTKEY", eventkey);
		}};
	}
	
	public static JSONObject getAnswerJSON(String answerkey) {
		return new JSONObject() {{
			put("TASK", "getanswer");
			put("USERNAME", App.getCurrentUsername());
			put("SESSIONKEY", App.sessionKey);
			put("ANSWERKEY", answerkey);
		}};
	}
	
	public static JSONObject getCreateRoomJSON(String title, String type) {
		return new JSONObject() {{
			put("TASK", "CREATEROOM");
			put("TITLE", title);
			put("OWNER", App.getCurrentUsername());
			put("TYPE", type);
			put("SESSIONKEY", App.sessionKey);
			put("USERNAME", App.getCurrentUsername());
		}};
	}
	
	public static JSONObject getCreateUserJSON(String username, String passone, String passtwo, 
			String email, String firstname, String lastname){
		return new JSONObject(){{
			put("TASK", "CREATEUSER");
			put("USERNAME", username);
			put("PASSONE", passone);
			put("PASSTWO", passtwo);
			put("EMAIL", email);
			put("FIRSTNAME", firstname);
			put("LASTNAEM", lastname);
		}};
	}
	
	public static JSONObject getUpdateUserJSON(UserDTO u) {
		return new JSONObject() {{
			put("TASK", "UPDATEUSER");
			put("USERNAME", u.getUsername());
			put("EMAIL", u.getEmail());
			put("FIRSTNAME", u.getFirstname());
			put("LASTNAME", u.getLastname());
			put("PASSWORD", u.getPassword());
			put("SUBBEDROOMS", u.getSubscribedRooms());
		}};
	}
	
	
	public static JSONObject getLoginJSON(UserDTO u){
		return new JSONObject(){{
			put("TASK", "loginauth");
			put("USERNAME", u.getUsername());
			put("PASSWORD", u.getPassword());
		}};
	}
	
	public static JSONObject getOtherLoginJSON(UserDTO u){
		return new JSONObject(){{
			put("TASK", "otherlogin");
			put("USERNAME", u.getUsername());
			put("PASSWORD", u.getPassword());
		}};
	}
	
	public static JSONObject getCreateQuestionJSON(String body, String timestamp, String title){
		return new JSONObject(){{
			put("TASK", "CREATEQUESTION");
			put("BODY", body);
			put("TIMESTAMP", timestamp);
			put("SENDER", App.getCurrentUsername());
			put("TITLE", title);
		}};
	}
	
	public static JSONObject getCreateAnswerJSON(String body, String timestamp) {
		return new JSONObject(){{
			put("TASK", "CREATEANSWER");
			put("BODY", body);
			put("TIMESTAMP", timestamp);
			put("SENDER", App.getCurrentUsername());
		}};
	}
	
	public static JSONObject getCreateEventJSON(String title, String timestamp) {
		return new JSONObject(){{
			put("TASK", "CREATEEVENT");
			put("TITLE", title);
			put("TIMESTAMP", timestamp);
			put("CREATOR", App.getCurrentUsername());
		}};
	}
	
	public static JSONObject getUpdateQuestionJSON(String answerkeys, String body, String questionkeys,
			String timestamp, String sender) {
		return new JSONObject(){{
			put("TASK", "UPDATEQUESTION");
			put("BODY", body);
			put("ANSWERKEYS", answerkeys);
			put("QUESTIONKEYS", questionkeys);
			put("TIMESTAMP", timestamp);
			put("SENDER", sender);
		}};
	}
	
	public static JSONObject getUpdateEventJSON(List<String> questionkeys, String timestamp, String eventkey, String creator) {
		return new JSONObject(){{
			put("TASK", "UPDATEEVENT");
			put("QUESTIONKEYS", questionkeys);
			put("TIMESTAMP", timestamp);
			put("EVENTKEY", eventkey);
			put("CREATOR", creator);
		}};
	}
	
	public static JSONObject getUpdateAnswerJSON(String answerkey, String body, String timestamp, String sender) {
		return new JSONObject(){{
			put("TASK", "UPDATEANSWER");
			put("ANSWERKEY", answerkey);
			put("BODY", body);
			put("TIMESTAMP", timestamp);
			put("SENDER", sender);
		}};
	}
	
	public static JSONObject getUpdateRoomJSON(List<String> eventkeys, String roomkey, String title, String owner) {
		return new JSONObject(){{
			put("TASK", "UPDATEROOM");
			put("EVENTKEYS", eventkeys);
			put("ROOMKEYS", roomkey);
			put("TITLE", title);
			put("OWNER", owner);
		}};
	}
	
	public static String[] getStringArrayFromJsonListString(String s) {
		String str;
		str = s.replace("[", "");
		str = str.replace("]", "");
		str = str.replace("\"", "");
		str = str.replace("\\", "");
		str = str.replaceAll("_", "");
		System.out.println("str: "+str);
		return str.split(",");
	}
	
}
