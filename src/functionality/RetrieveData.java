package functionality;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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
	public List<JSONObject> roomList = new ArrayList<>();


	public List<JSONObject> getRooms(){
		
		AtomicBoolean success = new AtomicBoolean(false);
		AtomicBoolean done = new AtomicBoolean(false);

		createroom = new CreateRoomFunc();
		loginscreen = new LoginScreen();
		connection = new LoginFunc();

		new Thread(new Runnable() {
			public void run() {
				
				System.out.println("--------------------------------");
				System.out.println("Pre-retrieve data");
				System.out.println("--------------------------------");
				
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
                    connection.disconnect();
                    
				
					System.out.println("Retrieving data");
					System.out.println(returnString);

					JSONObject recievedInfo = new JSONObject();
					JSONParser parser = new JSONParser();
					recievedInfo = (JSONObject) parser.parse(returnString); 
				
					JSONObject recievedUser = new JSONObject();
					recievedUser = (JSONObject) recievedInfo.get("USER");

					if (recievedInfo.get("REPLY").equals("succes")) {

						System.out.println("Entered if-statement");
						
						subbedroom = recievedUser.get("SUBBEDROOMS").toString();
						subbedroom = subbedroom.replace('[','_');
						subbedroom = subbedroom.replace(']','_');
						subbedroom = subbedroom.replace('"','_');
						subbedroom = subbedroom.replaceAll("_","");
						
						System.out.println(subbedroom);

						roomkeyList = subbedroom.split(",");
						
						for(String s : roomkeyList){
							System.out.println("Retrieving data " + s);

							JSONObject obj2 = new JSONObject();
							try{
								obj2.put("TASK" , "getroom");
								obj2.put("USERNAME" , loginscreen.username);
								obj2.put("SESSIONKEY" , LoginFunc.sessionkey);
								obj2.put("ROOMKEY" , s);
								
								String combinedMessageSubbed = "?logininfo=" + obj2.toString();
			                    System.out.println("CombinedMessage "+ combinedMessageSubbed);
			                    URL urlSubbed = new URL("http://52.58.112.107:8080/HelpingTeacherServer2/HTSservlet"+combinedMessageSubbed);
			                    HttpURLConnection connectionSubbed = (HttpURLConnection) urlSubbed.openConnection();
			                    BufferedReader inSubbed = new BufferedReader(new InputStreamReader(connectionSubbed.getInputStream()));
								
								 String returnStringSubbed = "";
								 returnStringSubbed = inSubbed.readLine();

								JSONObject recievedInfo2 = new JSONObject();
								JSONObject recievedRoom = new JSONObject();
								recievedInfo2 = (JSONObject) parser.parse(returnStringSubbed);
								recievedRoom = (JSONObject) recievedInfo2.get("ROOM");
								
								roomList.add(recievedRoom);
								
								success.set(true);
		                    	done.set(true);

							} catch (Exception e){
								e.printStackTrace();
							}
						}
						
						System.out.println(roomList);
						
					}
					
					in.close();

				} catch (Exception e) {
					System.out.println("Exception: " +  e.toString());
					e.printStackTrace();
				}

			}

		}).start();
		
		while(!done.get()){
			int i = 0;
			System.out.println("Retrieve data whileloop loading..." + i++);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return roomList;
	}
}