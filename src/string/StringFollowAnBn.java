package string;
/*
Validate String:

Problem1: Check if input is an integer or a string
	Input : 127
	Output : Integer
	
	Explanation : All digits are in the range '0-9'.
	
	Input : 122B
	Output : String
	
	Explanation : A alphabet is present.


Problem 2: Given a string str, return true string follows pattern anbn, i.e., it has a’s followed by b’s 

	Input : str = "aabb"
	Output : Yes
	
	Input : str = "abab"
	Output : No
	
	Input : str = "aabbb"
	Output : No

	 My thought is to first count a’s. If number of a’s is not equal to half of string’s length, 
	 then return false. Else check if all remaining characters are b’s or not.
 
 
 */

public class StringFollowAnBn {
	
	// Problem1: Check if input is an integer or a string  
	public static boolean isNumber(String s) {
		
	    for (int i = 0; i < s.length(); i++) {
	    	// if the specified character is a digit.
	        if (Character.isDigit(s.charAt(i))) 
	            return true;  
	    }
		return false;
	}
	
	//Problem 2: String has pattern a’s followed by b’s  
	public static boolean isAnBn(String s) {
	    int l = s.length();
	    if (l % 2 == 1) return false;
	    // Set two pointers, one starts from the left and another starts from right 
	    
	    int start = 0;
	    int end = s.length() -1;
	    
	    while ( start < end) {
	      if (s.charAt(start) != 'a' || s.charAt(end) != 'b') {  // aaabbb  , n = 3
	        return false;
	      }
	      start++; // move forward to right
	      end--; // move forward to left
	    }
	    return true;
	  }
	  	
	
	public static void main(String[] args) {
	    String input = "6790"; 
	    System.out.println(input + " isNumber ? " + isNumber(input));
	    
	    String input2 = "122b"; 
	    System.out.println( input2 + " isNumber ? " + isNumber(input2));
	    
	    String s = "aabb"; 
	    
	    System.out.println(s + " Is pattern AnBn ? " + isAnBn(s)); //true
	    
	    String s2 = "abab"; 
	    
	    System.out.println(s2 + " Is pattern AnBn ? " + isAnBn(s2)); //false
	      	    
	}
}
	
	