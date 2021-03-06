package string;
import java.util.Stack;

/*  
 * 394. Decode String
 * 
 * https://leetcode.com/problems/decode-string/
 
Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".

s = "3[a2[c]]", return "accaccacc".  ( 3[acc] --> acc acc acc )

s = "2[abc]3[cd]ef", return "abcabccdcdcdef"   , "abc abc cd cd cd ef".
     
 */

public class DecodeString {
	
  //Prefer - use 2 Stacks
  public static String decodeString(String s) {
	  if (s == null || s.length() == 0) {
	        return s;
	  }
      
      Stack<String> letter = new Stack<String>(); //stack to hold the intermediate result
      Stack<Integer> times = new Stack<Integer>(); //stack to hold the number of times 
      
      String result = ""; //result string
      int number = 0; // keep track of repeat number
      
      for (int i = 0; i < s.length(); i++) {
    	  
          if (Character.isDigit(s.charAt(i))) {
              //calculate repeat number - case of 2 digits like 11 
              number = number * 10 + s.charAt(i) - '0'; // = 0 * 10 + 3 = 3
           
          }
          else if (s.charAt(i) == '[') {
        	  //push into Stack  multiplier and set the number back to default
              times.push(number); //mul stack =[3 ]
              number = 0;   //reset
              
              //push the intermediate string formed so far and reset the result to default
              letter.push(result);
              result = "";
              
          } else if (s.charAt(i) == ']') { //start to decode current string
        	  
              //pop number of times out Stack 
              int repeat = times.pop(); 
              
            // pop letter from Stack 
              String poper = letter.pop();   //"", aaa
        	  StringBuilder sb = new StringBuilder(poper);  //[ ,  ,  ,..], [a, a, a,  ,]
              for (int j = 0; j < repeat; j++ ) {
            	  sb.append(result);  //[a, a, a, b, c,  ,], [a, a, a, b, c, b, c,  ,
              }
              
              result = sb.toString();
              
          } else {  // char is letter like a
              //keep adding the char to result
              result += s.charAt(i);
          }
      }
      
      return result;
  }
  

	
	//use Recursive
	public static String decodeString2(String s) {
	  StringBuilder sb = new StringBuilder();
	  int count = 0;
	  
	  for (int i = 0; i < s.length(); i++) {
	  	
	    if (Character.isLetter(s.charAt(i))) {
	        sb.append(s.charAt(i));     // [a, , , .count..]
	        
	    } else if (s.charAt(i) =='[') {
	         int start = i;
	         int sCount = 1;
	         
	         while (sCount != 0) { //3
	           if (s.charAt(++i) == '[') 
	          	 sCount++;
	           if (s.charAt(i) == ']') 
	          	 sCount--;     
	        }
	         
	        //recursive
	        String tmp = decodeString(s.substring(start + 1, i)); //a  , substring (5 + 1, 5) = b , bc
	        
	        while (count != 0) {  //count = 3
	          sb.append(tmp);     //[a, a, a, , , ,...]
	          count--;
	       }
	      
	    } else { // Character.isDigit(s.charAt(i)  numeric 3  , 2 at i = 4
	        // count = count != 0 ? count * 10 + Character.getNumericValue(s.charAt(i)) : Character.getNumericValue(s.charAt(i));
	        if (count != 0 ) 
	          count = count * 10 + s.charAt(i) - '0'; //return int value
	        else //count = 0
	          count = s.charAt(i) - '0'; //3 , 2
	    }
	    
	  } //for
	  return sb.toString();              
	}

  
	  
  public static void main(String[] args) {
    String s = "3[a]2[bc]";  
    System.out.println("Output: " + decodeString(s));  //aaabcbc
    
    String s2 = "3[a2[c]]";
    System.out.println("Output: " + decodeString(s2));
 
    String s3 =	"2[abc]3[cd]ef";
    System.out.println("Output: " + decodeString(s3));		
  }
  
}
	
	