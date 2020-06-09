package string;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * We have a pattern, which is a string that contains special characters
 * the special characters are { and }
 * Things inside the {} are considered optional.
 * 
 * For example: {hello }world is a pattern,
 * There are two strings that MATCH this pattern: 
 * 1. hello world 
 * 2. world
 * 
 * Another exmaple: 
 *    call John{ later} today{!}
 *
 * There are four strings that match:
 * 1. call John today
 * 2. call John later today
 * 3. call John today!
 * 4. call John later today!
 *
 * Now, please write a function that print out all the string that match
 * the pattern.
 */
 
public class RemoveSpecialChars_PrintPattern {   

	// Class Token to identify word is an optional or not
	class Token {
	  String value;
	  boolean isOptional;
	  
	  Token (String v, boolean isOpt) {
		  value = v;
		  isOptional = isOpt;
	  }

		@Override
		public String toString() {
			return "Token [value=" + value + ", isOptional=" + isOptional + "]";
		}
	  
	}

   /**
    * This method tokenizes the expression into a list of tokens.
    * Each token contains a value and a flag denoting whether this token is optional or not.    
    * e.g. pattern = call John{ later} today{!}
    * 
    * later, ! is optional --> mark true
    * 
    * output -> [token("call John", false), token(" later", true), token(" today", false), token("!", true)]
    */
    private List<Token> parsePattern(String pattern) {
    	Token t = null;
    	List<Token> tokens = new ArrayList<Token>();
    	StringTokenizer st = new StringTokenizer(pattern, "{");   //delemiter is (nextToken, "}") ; // later} today
	   	
		while (st.hasMoreTokens()) {  
			String nextToken = st.nextToken();
			System.out.println(nextToken); //call John, later} today, !}			
			
			if (nextToken.contains("}")) {  // later} today
				System.out.println("MATCH: " + nextToken);
				StringTokenizer st2 = new StringTokenizer(nextToken, "}"); // call John{ later 
				nextToken = st2.nextToken();
				System.out.println("Expect first token later? " + nextToken);
				t = new Token(nextToken, true);
		    	tokens.add(t);
				while (st2.hasMoreElements()) {  //if no next token, exit while loop
					String newToken = st2.nextToken();
					System.out.println("Expect second token today? " + newToken);
					t = new Token(newToken, false);
			    	tokens.add(t);
				}
			}
			else {
				t = new Token(nextToken, false);
	    		tokens.add(t);
			}
		
		}
	 	return tokens;
    
    }
	    
  public void printAllMatchingStrings(String pattern) {
     // Your code starts here.
    if (pattern == null) return;    
    
    List<Token> tokens = parsePattern(pattern);
    for (Token t: tokens) {
		System.out.println(t.toString());
	}
      
    StringBuilder sb = new StringBuilder();
   
    for (Token t: tokens) {
    	sb.append(t.value);
    }
    System.out.println(sb);
    
   // printAllHelper(tokens) ;
        
      
  }
  
  /** call John{ later} today{!}
   * Print 
   * 1. call John today
   * 2. call John later today
   * 3. call John today!
   * 4. call John later today!

   */
  private void printAllHelper(List<Token> tokens) {
    StringBuilder sb = new StringBuilder();
   int show = 0;
    
    for (int i = 0; i < tokens.size(); i++) {
    	    	
    		 if (tokens.get(i).isOptional && tokens.get( tokens.size()-1).isOptional) {
    			sb.append(tokens.get(i).value);
    			show++;
    			
	    	} else {
	    		sb.append(tokens.get(i).value);
	    		
	    	}
    		 System.out.println(sb);
    	
    }
    
  }
 
    public static void main(String[] args) {

    	RemoveSpecialChars_PrintPattern s = new RemoveSpecialChars_PrintPattern();
    	String pattern = "{hello }world";
    //	String pattern = "call John{ later} today{!}";
 /*   	List<Token> tokens = s.parsePattern(pattern);
    	for (Token t: tokens) {
    		System.out.println(t.toString());
    	}
   */
	    s.printAllMatchingStrings(pattern);
    }
    
}




