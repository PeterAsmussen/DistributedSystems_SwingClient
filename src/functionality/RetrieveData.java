package functionality;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import client.CreateRoom;
import client.LoginScreen;

public class RetrieveData {

	CreateRoomFunc createroom;
	LoginScreen loginscreen;
	Connection connection;
	public static String room;
	String roomkey;
	public String [] roomkeyList = new String[]{};
	
	
	public void getData(){
		
		createroom = new CreateRoomFunc();
		loginscreen = new LoginScreen();
		connection = new Connection();
		
		new Thread(new Runnable() {
	        public void run() {
	            try {
	                JSONObject obj = new JSONObject();
	                try {
	                    obj.put("TASK", "getuser");
	                    obj.put("USERNAME", loginscreen.username);
	                    obj.put("SESSIONKEY", connection.sessionkey);
	                    obj.put("ROOMKEY", roomkey);

	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	                String combinedMessage = "?logininfo=" + obj.toString();
	                System.out.printf("CombinedMessage", combinedMessage);
	                URL url = new URL("http://52.58.112.107:8080/HelpingTeacherServer2/HTSservlet"+combinedMessage);
	                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

	                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

	                String returnString = "";
	                returnString = in.readLine();
	                System.out.printf(returnString,returnString);
	                
	                JSONObject recieve = new JSONObject();
	                JSONObject recievedRoom = new JSONObject();
	                JSONParser parser = new JSONParser();
	                recievedRoom = (JSONObject) parser.parse(recieve.get("USER").toString()); 
	                room = recievedRoom.get("SUBBEDROOMS").toString(); 
	                
	                room = room.replace('[',' ');
	                room = room.replace(']',' ');
	                room.trim();
	                
	                System.out.printf("ReturnMessageROOM:", returnString);

	                if (recieve.get("REPLY").equals("succes")) {
	                	roomkeyList = room.split(",");
	                	roomkey = roomkeyList[0];
	                	System.out.printf(returnString,returnString);
	                	System.out.printf("room",room);
	                
	                }
	            
	                System.out.printf("ReturnMessage:", returnString);
	                in.close();

	            } catch (Exception e) {
	            	System.out.printf("Exception", e.toString());
	            }

	        }

	    }).start();
		}
	}