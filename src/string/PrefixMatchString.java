package string;

import java.util.*;
/* Given a list of street names, Return a list of string that matches with prefix.  
 */

public class PrefixMatchString { 
	
	//to find prefix using startsWith or substring
	public static List<String> namesMatchPrefix(String prefix, List<String> streetNames, int numberOfStreetName) {
		List<String> result = new ArrayList<String>();
		
        int count = 0;
      
        // Check for each prefix element 
        for (String name: streetNames) {
        	
        	// use substring
       /* 	int len = prefix.length();
        	if(prefix.equalsIgnoreCase(name.substring(0,len))) {
        		System.out.println("Match " + prefix);
        	}
        */	  
        	// use startsWith
            if (name.startsWith(prefix)) { 
            	System.out.println(" MATCHED");
            	result.add(name);
                count++; 
                
                if (count == numberOfStreetName) {
                	return result;
                } 
            } 
        }
		return result; 
    } 
		
    public static void main(String[] args) 
    { 
    	 String prefix = "fr"; 
    	 List<String> streetNames = new ArrayList<String>();
		 streetNames.add("freaks");
		 streetNames.add("for");
		 streetNames.add("frank");
		 streetNames.add("french");
	  
    	 List<String> list = namesMatchPrefix(prefix, streetNames, 2);  // return the top of 2
    	 System.out.println(list);
    	 
    	 List<String> list2 = namesMatchPrefix("ff", streetNames, 2);  // no prefix, return [] 
    	 System.out.println(list2);
    }
  
}
	
	