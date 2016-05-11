package functionality;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import client.CreateRoom;
import client.LoginScreen;

public class CreateRoomFunc {
	
	/*
	 * Mere eller mindre det samme som i Connection.java, dog tilpasset til CreateRoom 
	 */
	
	CreateRoom createroom;
	LoginScreen loginscreen;
	LoginFunc connection;
	public static String roomkey;

	public void createRoom(){
		
		createroom = new CreateRoom();
		loginscreen = new LoginScreen();
		connection = new LoginFunc();
		
		System.out.println("preThread - Create Room!");
		new Thread(new Runnable() {
            public void run() {
                try {
                   
                    JSONObject obj = new JSONObject();
                    try {
                        obj.put("TASK", "CREATEROOM");
                        obj.put("TITLE", createroom.roomname);
                        obj.put("OWNER", loginscreen.username);
                        obj.put("TYPE", "public");
                        obj.put("SESSIONKEY", connection.sessionkey);
                        obj.put("USERNAME", loginscreen.username);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                   
                    String combinedMessage = obj.toString();
                    URL url = new URL("http://52.58.112.107:8080/HelpingTeacherServer2/HTSservlet"+combinedMessage);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoOutput(true);
                    connection.setRequestMethod("PUT");
                    OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
                    out.write(combinedMessage);
                    out.close();
                    
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    String returnString = "";
                    returnString = in.readLine();
                    System.out.println("Create room: "+returnString);
                    
                    JSONObject recieve = new JSONObject();
                    JSONObject recievedRoom = new JSONObject();
                    JSONParser parser = new JSONParser();
                    
                    recieve = (JSONObject) parser.parse(returnString);
                    recievedRoom = (JSONObject) parser.parse(recieve.get("USER").toString());

                    if (recieve.get("REPLY").toString().equals("success")){
                    	roomkey = recievedRoom.get("ROOMKEY").toString();
                    	
                    	
                    }
                    else if(recieve.get("REPLY").toString().equals("failed")){
                    	System.out.printf("ReturnMessage from Create Room: ", returnString);
                    } 
                } catch (Exception e) {
                }

            }
        }).start();
	}
}
