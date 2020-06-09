package string;

import java.util.HashSet;
import java.util.Set;
/*
 * — Given a string, format this string to (I) keep only one space between each word (II) remove any prefix or suffix spaces
For example:
-represent space below.


Input: -I---am----happy-                   Output: I-am-happy
Input: I---------am--very-------happy      Output: I-am-very-happy

 */
public class RemovePrefixSuffixandDuplicateHyphen {
      //You can use deleteCharAt(0) to remove the first character 
	   //deleteCharAt(length - 1) to remove the last character from String in Java

      // Use Hashset to filter duplicate of hyphen
	  // when hyphen is seen, mark true
	  public static String removeDashFromString( String input) {
		    if (input == null) return null;
		    
		    if (input.startsWith("-")) { 
		    	input = input.substring(1);
		    }
		    
		    if (input.endsWith("-")) { 
		    	input = input.substring(0, input.length() - 1);  // without last character
		    }
	   
		    StringBuilder sb = new StringBuilder();
		    Set<Character> set = new HashSet<>();
		    boolean seenHyphen = false;
		
		    for(int i = 0; i < input.length(); i++){
		    	Character c = input.charAt(i);
		    	
		        if (!Character.isLetter(c)) {
			        if (!set.contains(c)){ 
			        	set.add(c);
			        	sb.append(c); 
			        	seenHyphen = true;  // when hyphen is seen, mark true
			        } else if (set.contains(c) && !seenHyphen ){
			        	sb.append(c);
			        	seenHyphen = true;
			        }
		        }
		        else {
		        	sb.append(c); 
		        	seenHyphen = false;
		        }
		    }
		 
		    return sb.toString();    
		    
		  } 
		  
		  
		  
	  public static void main(String[] args) {
	     String input = "-I---am----happy-";
	     System.out.println(removeDashFromString(input));
	     
	     String input2 = "-I---am----happy-";
	     System.out.println(removeDashFromString(input2));
	  }
	  
	}
