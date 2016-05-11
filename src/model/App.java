package model;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.json.simple.JSONObject;

import functionality.controllers.UserController;

public class App {
	
	public static UserDTO currentUser;
	public static RoomDTO currentRoom;
	public static EventDTO currentEvent;
	public static QuestionDTO currentQuestion;
	public static AnswerDTO currentAnswer;
	public static String sessionKey;
	public static UserController usercontroller = new UserController();
	
//	App app = new App();
//	public App getInstance() {
//		return app;
//	}
	
	private App() {
		
	}
	
	public static UserController getUserController(){
		return usercontroller;
	}
	
	public static String getCurrentUsername() {
		if(currentUser != null) return currentUser.getUsername();
		return null;
	}
	
	
	/**
	 * returnerer en forbindelse til HTSservlet med det givne request object 
	 * @param obj
	 * @return
	 * @throws IOException
	 */
	
	public static HttpURLConnection getHttpConnectionFromObject(JSONObject obj) {
		try {
			String message = "?logininfo="+obj.toString();
			URL url = new URL("http://52.58.112.107:8080/HelpingTeacherServer2/HTSservlet"+message);
			return (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static JSONObject getUserJSON(String username) {
		return new JSONObject() {{
			put("TASK", "getuser");
			put("USERNAME", username);
			put("SESSIONKEY", sessionKey);
			put("GETNAME", username);
		}};
	}
	
	public static JSONObject getRoomJSON(String roomKey) {
		return new JSONObject() {{
			put("TASK", "getroom");
			put("USERNAME", getCurrentUsername());
			put("SESSIONKEY", sessionKey);
			put("GETROOM", roomKey);
		}};
	}
	
	public static JSONObject getCreateRoomJSON(String title, String type) {
		return new JSONObject() {{
			put("TASK", "CREATEROOM");
			put("TITLE", title);
			put("OWNER", getCurrentUsername());
			put("TYPE", type);
			put("SESSIONKEY", sessionKey);
			put("USERNAME", getCurrentUsername());
		}};
	}
	
	public static JSONObject getUpdateUserJSON() {
		return null;
	}
	
	public static boolean isReplySuccessful(JSONObject obj) {
		return obj.get("REPLY").equals("succes");
	}
	
	public static UserDTO jsonToUserDTO(JSONObject obj) {
		return new UserDTO(obj.get("USERNAME").toString(), obj.get("EMAIL").toString(),
				obj.get("FIRSTNAME").toString(), obj.get("LASTNAME").toString(),
				obj.get("PASSWORD").toString(), (List<String>) obj.get("SUBBEDROOMS"));
		// Kan man bare caste subbedrooms til en List?
	}
	
	
	
	
}
