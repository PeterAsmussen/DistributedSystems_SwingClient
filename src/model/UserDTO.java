package model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;

import org.json.JSONArray;

public class UserDTO implements Serializable {
	
	private String username = " ";
	private String email = " ";
	private String firstname = " ";
	private String lastname = " ";
	private String password = " ";
	
	private List<String> subscribedRooms = new ArrayList<>();
	
	public UserDTO() {
		
	}
	
	public UserDTO(String username, String password, List<String> subbedRooms) {
		this.username = username;
		this.password = password;
		this.subscribedRooms = subbedRooms;
		
	}

	public UserDTO(String username, String email, String firstname, String lastname, String password, List<String> subbedRooms) {
		if (username != null) this.username = username;
		if (email != null) this.email = email;
		if (firstname != null) this.firstname = firstname;
		if (lastname != null) this.lastname = lastname; 
		if (password != null) this.password = password;
		if (subbedRooms != null) this.subscribedRooms = subbedRooms;

	}
	
	public UserDTO(String username, String email, String firstname, String lastname, String password) {
		if (username != null) this.username = username;
		if (email != null) this.email = email;
		if (firstname != null) this.firstname = firstname;
		if (lastname != null) this.lastname = lastname; 
		if (password != null) this.password = password;

	}

	public UserDTO(String username, String password) {
		if (password != null) {
			this.password = password;
		}
		if (username != null) {
			this.username = username;
		}
	}
	

	public UserDTO(String userName) {
		username = userName;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public List<String> getSubscribedRooms() {
		return subscribedRooms;
	}

	public void addRoomKey(String roomkey){
		subscribedRooms.add(roomkey);
	}
	
	@Override
	public String toString() {
		return "UserDTO{" +
				"username='" + username + '\'' +
				", email='" + email + '\'' +
				", firstname='" + firstname + '\'' +
				", lastname='" + lastname + '\'' +
				", password='" + password + '\'' +
				'}';
	}
	
	public JSONObject toJSONObject(){
		JSONObject data = new JSONObject();
		data.put("USERNAME", username);
		data.put("EMAIL", email);
		data.put("FIRSTNAME", firstname);
		data.put("LASTNAME", lastname);
		data.put("PASSWORD", password);
		data.put("SUBBEDROOMS", subscribedRooms);
		return data;
	
	}
	
	public static UserDTO jsonToUserDTO(JSONObject obj) {
		UserDTO user;
		if(isJsonUser(obj)) {
			String subbed = obj.get("SUBBEDROOMS").toString();
			List<String> subbedList = Arrays.asList(getRoomStringArrayFromJsonRoomString(subbed));
			String username = obj.get("USERNAME").toString();
			String email = obj.get("EMAIL").toString();
			String firstname = obj.get("FIRSTNAME").toString();
			String lastname = obj.get("LASTNAME").toString();
			String password = obj.get("PASSWORD").toString();
			// skal der måske laves en til objekter uden subbed rooms?
			user = new UserDTO(username, email, firstname, lastname, password, subbedList);
			return user;
		}
		return null;	
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
	
	private static String[] getRoomStringArrayFromJsonRoomString(String s) {
		String str;
		str = s.replace('[', '_');
		str = str.replace(']', '_');
		str = str.replace('"', '_');
		str.replace("_", "");
		return str.split(",");
	}
}
