package string;
/*
 * 151. Reverse Words in a String
 * https://leetcode.com/problems/reverse-words-in-a-string/
 *
Given an input string, reverse the string word by word.

Example 1:
Input: "the sky is blue"
Output: "blue is sky the"

Example 2:
Input: "  hello world!  "
Output: "world! hello"
Explanation: Your reversed string should not contain leading or trailing spaces.

Example 3:
Input: "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
 
Example 4:

Input: "How how Are HOW You are you"
Output: "You Are How"
Explanation: If words are repeated, the first occurence should be considered. 

Note:

A word is defined as a sequence of non-space characters.
Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
You need to reduce multiple spaces between two words to a single space in the reversed string.
 *   
 *  
 */

import java.util.HashSet;
import java.util.Set;

public class ReverseWordsInString  {
	
	public static void main (String[] args)
    {
	//	String s = " ";
	//	System.out.println("Reversed: " + reverseWords(s));
		
		String s1 = "the sky is blue";
		System.out.println("Reversed: " + reverseWords(s1));
		
		String s2 = "  hello  world!  ";
		System.out.println("Reversed: "+ reverseWords(s2));	
		

		String s3 = "How how Are HOW You are you";
		System.out.println("Keep the first occurence: "+ reverseWords(s3));
		
		System.out.println("Keep the first occurence: "+ reverseWords_ex4(s3));
    }

 	
	//this method works, but does NOT work Example 4:
	public static String reverseWords(String s) {
		s = s.trim();  //not contain leading or trailing spaces
        
		int len = s.length()-1;
       
        if (s.length() <= 1) {
			return s;
		}
        StringBuilder ans = new StringBuilder();
        
        while(len >= 0){
          
        	while(s.charAt(len) == ' ') len--;
            	int index = s.lastIndexOf(' ', len); //10, 7
            	
	            if(index == -1){
	                ans.append(s.substring(0,len+1)); //the = s.subtring(0, 2 +1)
	            } else{
	                ans.append(s.substring(index + 1, len + 1)).append(" "); //blue = s.subdyting(10 + 1, 14 +1), is = substring(8, 10)
	            }
	            len = index - 1; //9, 6, ..,-2
        }
        return ans.toString();
        
    }
	
    
    //This solution is to filter duplicate word. for example 4 - "How how Are HOW You are you"	
   	public static String reverseWords_ex4(String s) {
   		StringBuilder sb = new StringBuilder();
   		s = s.trim();  // any leading and trailing whitespace removed.
   		
   		if (s.length() <= 1) {
   			return s;
   		}
   		
   		Set<String> seen = new HashSet<String>();
   		
   		String[] words = s.split(" ");
   		
   		for (String word : words) {
   			String lowerCaseWord =  word.toLowerCase();
   			
   			if(seen.contains(lowerCaseWord)) continue;  // // If this word has been seen before, skip it
   			seen.add(lowerCaseWord); 					//[how, are, you]
   			
   			if (word.length() == 0) continue; // case : empty string
   			
   			//sb.append(" ");  // make space between words
   			sb.insert(0, " " + word);
   			
   			//System.out.println(word);
   		}

   	   // to delete " " at the first index
   		 if(sb.length() > 0){
   	            sb.deleteCharAt(0);    
   	     }
   		return sb.toString();
   	}

}