package functionality;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicBoolean;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import client.CreateRoom;
import client.LoginScreen;

public class CreateRoomFunc {
	
	CreateRoom createroom;
	LoginScreen loginscreen;

	public void createRoom(){
		
		createroom = new CreateRoom();
		loginscreen = new LoginScreen();
		
		new Thread(new Runnable() {
            public void run() {
                try {
                    URL url = new URL("http://52.58.112.107:8080/HelpingTeacherServer2/HTSservlet");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    JSONObject obj = new JSONObject();
                    try {
                        obj.put("TASK", "CREATEROOM");
                        obj.put("TITLE", createroom.txtRoomName.getText().toString());
                        obj.put("OWNER", loginscreen.username);
                        obj.put("TYPE", "public");
                        //obj.put("SESSIONKEY", sessionKey);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                   
                    String combinedMessage = obj.toString();
                    connection.setDoOutput(true);
                    connection.setRequestMethod("PUT");
                    OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
                    out.write(combinedMessage);
                    out.close();

                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    String returnString = "";
                    returnString = in.readLine();
                    JSONObject recieve = new JSONObject();

                    if (recieve.get("REPLY").equals("succes")){
                    	System.out.printf("ReturnMessage", returnString);
                    	System.out.printf(recieve.get("ROOM").toString(), "room");
                        System.out.println("Room successfully created");
                    }
                    else if(recieve.get("REPLY").equals("failed")){
                    
                    } 
                } catch (Exception e) {
                }

            }
        }).start();
	}
}
