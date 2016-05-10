package model;

public class App {
	
	public static UserDTO currentUser;
	public static RoomDTO currentRoom;
	public static EventDTO currentEvent;
	public static QuestionDTO currentQuestion;
	public static AnswerDTO currentAnswer;
	public static String sessionKey;
	
	App app = new App();
	
	private App() {
		
	}
	
	public App getInstance() {
		return app;
	}
	
	public String getCurrentUsername() {
		if(currentUser != null) return currentUser.getUsername();
		return null;
	}

}
