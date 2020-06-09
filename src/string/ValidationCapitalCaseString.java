package string;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/*
 * Given a word, you need to judge whether the usage of capitals in it is right or not.
Example - Input: "USA" or "Google"
Output: True

Other input , it will return false.
Example: "FlaG" or "abcd" or "abCD"
Output: False
 */
public class ValidationCapitalCaseString{
    
	public static boolean detectCapitalCase(String word) {
		// when first char is Capitol, 
			//Case 1: the rest of chars must be Upper case
			//Case 2: the rest of chars must be lower case
		if (Character.isUpperCase(word.charAt(0))) {
			String a = word.substring(1,word.length());
			String b = a.toLowerCase();
			String c = a.toUpperCase();
			if (a.equals(b)) return true;
			else if (a.equals(c))  return true;
			else return false;
			
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) {
	   String s1 = "Google";
	   System.out.println(detectCapitalCase(s1));
	   
	   String s2 = "USA";
	   System.out.println(detectCapitalCase(s2));
	   
	   String s3 = "FlaG";
	   System.out.println(detectCapitalCase(s3)); //false
	   
	   String s4 = "abcd";
	   System.out.println(detectCapitalCase(s4)); //false
	   
	   
	    String s = "Hello    World and    aloha";
	    String removeSpace = s.replaceAll("\\s+", " ");
	    System.out.println(removeSpace);
	
	}

}
	
	