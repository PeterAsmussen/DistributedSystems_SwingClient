package functionality;

import java.util.List;

import org.json.simple.JSONObject;

import model.App;
import model.UserDTO;

public class JSONHelper {
	
	public static UserDTO jsonToUserDTO(JSONObject obj) {
		return new UserDTO(obj.get("USERNAME").toString(), obj.get("EMAIL").toString(),
				obj.get("FIRSTNAME").toString(), obj.get("LASTNAME").toString(),
				obj.get("PASSWORD").toString(), (List<String>) obj.get("SUBBEDROOMS"));
		// Kan man bare caste subbedrooms til en List?
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
		}};
	}
	
	
	public static JSONObject getLoginJSON(UserDTO u){
		return new JSONObject(){{
			put("TASK", "loginauth");
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
	
	public static JSONObject getUpdateEventJSON(String questionkeys, String timestamp, String eventkey, String creator) {
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
	
	public static JSONObject getUpdateRoomJSON(String eventkeys, String roomkeys, String title, String owner) {
		return new JSONObject(){{
			put("TASK", "UPDATEROOM");
			put("EVENTKEYS", eventkeys);
			put("ROOMKEYS", roomkeys);
			put("TITLE", title);
			put("OWNER", owner);
		}};
	}
	
}
