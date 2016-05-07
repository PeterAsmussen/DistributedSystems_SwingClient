package functionality;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import client.CreateUser;

public class CreateUserFunc {
	
	CreateUser createuser;

	public void createUser(){

		new Thread(new Runnable() {
            public void run() {
                try {
                    URL url = new URL("http://52.58.112.107:8080/HelpingTeacherServer2/HTSservlet");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    JSONObject obj = new JSONObject();
                    try {
                        obj.put("TASK", "CREATEUSER");
                        obj.put("USERNAME", createuser.getUsername);
                        obj.put("PASSONE", createuser.getPassword);
                        obj.put("PASSTWO", createuser.getPassword2);
                        obj.put("EMAIL", createuser.getEmail);
                        obj.put("FIRSTNAME", createuser.getFirstname);
                        obj.put("LASTNAME", createuser.getLastname);
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    
                    String combinedMessage = obj.toString();
                    System.out.printf("CombinedMessage", combinedMessage);
                    
                    connection.setDoOutput(true);
                    connection.setRequestMethod("PUT");
                    
                    OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
                    out.write(combinedMessage);
                    out.close();

                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    String returnString = "";
                    returnString = in.readLine();
                    JSONObject recieve = new JSONObject();
                    System.out.printf(returnString, "");

                    
                    while(CreateUser.getPassword.equals(CreateUser.getPassword2)){
                   
                    	if(recieve.get("REPLY").equals("succes")){
                          	 /*
                          	  * TODO
                          	  */
                           }
                           
                           else if(recieve.get("REPLY").equals("failed")) {
                          	 /*
                          	  * TODO
                          	  */
                           }                       
                           
                           System.out.printf("ReturnMessage:", returnString);
                           in.close();
                           
                           }
                    }
                    
                 catch (Exception e) {
	            	System.out.printf("Exception", e.toString());
                 }
            }
	    }).start();
	}
}
		