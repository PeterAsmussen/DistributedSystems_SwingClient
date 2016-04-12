package client;

import java.util.Arrays;

public class VerifyLogin {

	public boolean isPasswordCorrect(char[] input) {
	    boolean isCorrect = true;
	    char[] correctPassword = { 'p', 'a', 's', 's', 'w', 'o', 'r', 'd' };

	    if (input.length != correctPassword.length) {
	        isCorrect = false;
	    } else {
	        isCorrect = Arrays.equals (input, correctPassword);
	    }

	    //Zero out the password.
	    Arrays.fill(correctPassword,'0');

	    return isCorrect;
	}
}
