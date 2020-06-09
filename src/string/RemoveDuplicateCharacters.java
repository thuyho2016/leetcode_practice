package string;

import java.util.HashSet;
import java.util.Set;
/*
Remove Duplicate Letters
 
Input: "Java""
Output: "Jav"   


 */
public class RemoveDuplicateCharacters {
  
      // Use Hashset to filter duplicate of hyphen
	  // when hyphen is seen, mark true
  public static String removeDuplicateFromString( String input) {
    if (input == null) return null;
  
    StringBuilder sb = new StringBuilder();
    Set<Character> set = new HashSet<>();

    for(int i = 0; i < input.length(); i++){
    	Character c = input.charAt(i);
       
        if (!set.contains(c)){ 
        	set.add(c);
        	sb.append(c); 
        }
    }
 
    return sb.toString();    
    
  } 

 
		  
  public static void main(String[] args) {
     String input = "Java";
     System.out.println(removeDuplicateFromString(input));
    
     String input2 = "Sandeep";
     System.out.println(removeDuplicateFromString(input2)); //Sandep
 
  }
  
}
