package functionality;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JPasswordField;

import org.json.simple.JSONObject;

import client.LoginScreen;

public class Connection {
	
	LoginScreen loginscreen = new LoginScreen();

	public boolean login(String username, JPasswordField txtPassword){
		
		AtomicBoolean success = new AtomicBoolean(false);
		AtomicBoolean done = new AtomicBoolean(false);
		
		new Thread(new Runnable() {
            public void run() {
                try {
                    URL url = new URL("http://52.58.137.252:8080/HelpingTeacherServer2/HTSservlet");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    
                    JSONObject obj = new JSONObject();
                    try {
                        obj.put("TASK", "loginauth");
                        obj.put("USERNAME", loginscreen.txtUsername.getText().toString());
                        obj.put("PASSWORD", loginscreen.txtPassword.toString());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String combinedMessage = obj.toString();
                    System.out.printf("CombinedMessage", combinedMessage);
                    //http://developer.android.com/reference/java/net/HttpURLConnection.html
                    //connection.setDoOutput(true);
                    //I would like to get a confirmed login - perhaps session ID
                    connection.setRequestMethod("GET");
                    OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
                    out.write(combinedMessage);
                    out.close();

                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    String returnString = "";
                    returnString = in.readLine();
                    if (returnString.equals("Succes")) {
                    	success.set(true);
                    	done.set(true);
                    } else {
                        System.out.printf("CreateUserERROR:", "Something went wrong in server");
                        success.set(false);
                        done.set(true);
                    }
                    System.out.printf("ReturnMessage:", returnString);
                    in.close();

                } catch (Exception e) {
                	System.out.printf("Exception", e.toString());
                }
            }
		});
	
		while(!done.get()){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return success.get();
	}
}
	
