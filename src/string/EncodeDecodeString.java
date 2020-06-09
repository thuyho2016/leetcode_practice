package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*  
 * 271. Encode and Decode Strings
 * https://leetcode.com/problems/encode-and-decode-strings/
 
Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.

Machine 1 (sender) has the function:

string encode(List<String> strs) {
  // ... your code
  return encoded_string;
}

Machine 2 (receiver) has the function:

List<String> decode(string s) {
  //... your code
  return strs;
}


So Machine 1 does:
string encoded_string = encode(strs);

and Machine 2 does:
List<String> strs2 = decode(encoded_string);

strs2 in Machine 2 should be the same as strs in Machine 1.

Implement the encode and decode methods.

 
Time complexity : O(N) both for encode and decode, where N is a number of strings in the input array.

Space complexity : O(1) for encode to keep the output, since the output is one string.
 */

public class EncodeDecodeString {
	
	
	 // Encodes a list of strings to a single string.
	//join strings using delimiters.
    public static String encode(List<String> strs) {
    	if (strs.size() == 0) return Character.toString( (char)258 );
    	
    	String delimiter = Character.toString( (char)257 );
    	
    	StringBuilder sb = new StringBuilder();
    	for (String s:strs) {
    		sb.append(s);
    		sb.append(delimiter);
    	}
    	
    	sb.deleteCharAt(sb.length() - 1); //HelloāWorldā
		return sb.toString();
        
    }

    // Decodes a single string to a list of strings.
    public static List<String> decode(String s) {
    	String delimiter = Character.toString( (char)258 );
    	if (s.equals(delimiter)) return new ArrayList<String>();
    	
    	delimiter = Character.toString( (char)257 );
    	return Arrays.asList(s.split(delimiter, -1));
    	
    }
	  
    public static void main(String[] args) {
    	List<String> s = Arrays.asList("Hello","World");  
    	
    	String out = encode(s);
    	System.out.println("Encoded: " + out);  // HelloāWorld
    	System.out.println("Decoded: " + decode(out));
    	
    	out = encode(new ArrayList<String>());
    	System.out.println("Encoded: " + out); //Ă
    }
  
}
	
	