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

}
