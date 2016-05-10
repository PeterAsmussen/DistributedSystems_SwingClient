package functionality;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import client.LoginScreen;

public class RetrieveData {

	CreateRoomFunc createroom;
	LoginScreen loginscreen;
	LoginFunc connection;
	public static String subbedroom;
	String roomkey;
	public String [] roomkeyList = new String[]{};
	public List<String> roomList = new ArrayList<>();


	public void getData(){

		createroom = new CreateRoomFunc();
		loginscreen = new LoginScreen();
		connection = new LoginFunc();

		new Thread(new Runnable() {
			public void run() {
				System.out.println("Pre-retrieve data");
				try {
					JSONObject obj = new JSONObject();
					try {
						obj.put("TASK", "getuser");
						obj.put("USERNAME", loginscreen.username);
						obj.put("SESSIONKEY", LoginFunc.sessionkey);
						obj.put("GETNAME" , loginscreen.username);

					} catch (Exception e) {
						e.printStackTrace();
					}
					String combinedMessage = "?logininfo=" + obj.toString();
                    System.out.println("CombinedMessage "+ combinedMessage);
                    URL url = new URL("http://52.58.112.107:8080/HelpingTeacherServer2/HTSservlet"+combinedMessage);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    
                    String returnString = "";
                    returnString = in.readLine();

				
					System.out.println("Retrieving data");
					System.out.println(returnString);

					JSONObject recievedInfo = new JSONObject();
					JSONParser parser = new JSONParser();
					recievedInfo = (JSONObject) parser.parse(returnString); 
					subbedroom = recievedInfo.get("SUBBEDROOMS").toString(); 

					subbedroom = subbedroom.replace('[',' ');
					subbedroom = subbedroom.replace(']',' ');
					subbedroom.trim();
					System.out.println(subbedroom);

					System.out.println("ReturnMessageROOM:"+ returnString);

					if (recievedInfo.get("REPLY").equals("succes")) {
						System.out.println("Entered if-statement");
						roomkeyList = subbedroom.split(",");
						roomkey = roomkeyList[0];
						//System.out.println(returnString);
						//System.out.println("room: " + subbedroom);
//
//						for(String s : roomkeyList){
//							System.out.println("Retrieving data " + s);
//
//							JSONObject obj2 = new JSONObject();
//							try{
//								obj2.put("TASK" , "getroom");
//								obj2.put("USERNAME" , loginscreen.username);
//								obj2.put("SESSIONKEY" , Connection.sessionkey);
//								obj2.put("ROOMKEY" , s);
//
//								JSONObject recieve2 = new JSONObject();
//								System.out.println(recieve2);
//								JSONObject recievedRoom = new JSONObject();
//								System.out.println(recievedRoom);
//								recievedRoom = (JSONObject) parser.parse(recieve2.get("ROOM").toString());
//								roomList.add(recievedRoom.get("ROOM").toString());
//
//							} catch (Exception e){
//								e.printStackTrace();
//							}
//						}
//						
						

					}


					System.out.println("ReturnMessage:" + returnString);
					in.close();

				} catch (Exception e) {
					System.out.println("Exception: " +  e.toString());
					e.printStackTrace();
				}

			}

		}).start();
	}
}