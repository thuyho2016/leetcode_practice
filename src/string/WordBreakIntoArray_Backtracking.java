package string;

import java.util.HashSet;

/*
 * Given an string and a dictionary of words, find out if the input string can be broken into a space-separated sequence of one or more dictionary words.

Example:
dictionary = ["I" , "have", "Jain", "Sumit", "am", "this", "dog"]

String = "IamSumit"
Output: "I am Sumit"

String ="thisisadog"
Output : String can't be broken

Approach: Backtracking- Naive Approach

- Navigate the given input string.
1. Take a blank string and keep adding one character at a time to it.
2. Keep checking if the word exist in the dictionary.
3. If word exist in the dictionary then add that word to the answer string and make recursive call to the rest of the string.
4. If any of the recursive call returns false, then backtrack and remove the word from the answer string and again keep adding the characters to string.
5. If all the recursive calls return true, that means string has been broken successfully.

 */

public class WordBreakIntoArray_Backtracking {
    
	public void wordBreak(String s, HashSet<String> hashset) {
        if (find(s, hashset, "")) {
        } else {
          System.out.println("String cant be broken");
        }
      }

      public boolean find(String s, HashSet<String> dict, String answer) {
        // System.out.println(s + "  " + answer);
        if (s.length() == 0) {
           System.out.println("output: " + answer);
           return true;
           
        } else {
           int index = 0;
           String word = "";
          
          while (index < s.length()) {
             word += s.charAt(index); // t, th, th, thi, this
           
             // check if word exists in dictionary				
             if (dict.contains(word)) { //this
            	System.out.println ("word: " + word); //this, is, sumit, jain
            	
            	answer = answer + word + " "; //keep previous word and current word
            	System.out.println(answer + " , s.substring(index + 1)=  " + s.substring(index + 1));
            	 //add word to the answer and make a recursive call the rest of the string "issumitjain"
                if (find(s.substring(index + 1), dict, answer)) { // find( "issumitjain", .sumitjain.., "this " )
                   return true;
                } else {
                  // System.out.println(word + "  backtrack");
                  index++;
                }
             } else {
                  index++;
             }
          }
          return false;
        }
      }
      
      public static void main(String[] args) {
	        HashSet<String> hs = new HashSet<String>();
	        hs.add("this");
	        hs.add("is");
	        hs.add("sumit");
	        hs.add("jain");
	        hs.add("the");
	        hs.add("problem");
	        
	        String s = "thisissumitjain";
	
	        WordBreakIntoArray_Backtracking ws = new WordBreakIntoArray_Backtracking();
	        ws.wordBreak(s, hs);
      }
}