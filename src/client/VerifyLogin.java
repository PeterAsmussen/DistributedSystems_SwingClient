package client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
	
	public String nameGenerator(int i){
		
		List<String> anonNames = new ArrayList<String>();
		anonNames.add("Anonymous Moose");
		anonNames.add("Anonymous Skunk");
		anonNames.add("Anonymous Squirrel");
		anonNames.add("Anonymous Kiwi");
		anonNames.add("Anonymous Woodpecker");
		anonNames.add("Anonymous Armadillo");
		anonNames.add("Anonymous Sloth");
		anonNames.add("Anonymous Ostrich");
		
		String name = (String)anonNames.get(i);
		return name;
	}
	
	public int randomInteger(int min, int max) {
		Random rand = new Random();
	    int randomNum = rand.nextInt(max - min + 1) + min;
	    return randomNum;    
	}
}
