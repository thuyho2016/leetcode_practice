/*
 * 383. Ransom Note ( easy level)
 * https://leetcode.com/problems/ransom-note/
 * 
Given an arbitrary ransom note string and another string containing letters from all the magazines, 
write a function that will return true if the ransom note can be constructed from the magazines ; otherwise, it will return false.

Each letter in the magazine/text string can only be used ONCE in your ransom note.

Note:
You may assume that both strings contain only lowercase letters.

canConstruct("a", "b") -> false
canConstruct("aa", "ab") -> false
canConstruct("aa", "aab") -> true
 */


public class RandomNote {
	
	//Time Complexity: O(m + n) , Space : O(1)
	public static boolean canConstructLetter( String note, String text) {
	        // number of character of note should be same number character of text

	        if (note == null || note.length() == 0) return true;
	        if (text == null || text.length() == 0) return false;

	        int[] counts = new int[256]; // keep track count of letter in text

	        for (char c : text.toCharArray()) {
	        
	      //  Use Math.abs for Capitol case, e.g letter T , int pos =	c - 'a' = -13. It will cause outbound exception
	           counts[Math.abs(c - 'a')] += 1;  // convert character to int and store to array count. if character is repeated, increase by 1
	        }

	        for (int j = 0; j < note.length(); j++) {
	            if (counts[Math.abs(note.charAt(j) - 'a')] == 0) { //If the count is equal to zero, return false
	                return false;
	            } else
	                counts[Math.abs(note.charAt(j) - 'a')]--; // decrease the count of the letter. Eg counts= [2,0,0,..] will be [1,0,0,...]
	        }
	        return true;
	}
	
	public static void main (String[] args)
    {

		 System.out.println(canConstructLetter("a", "b")); // false
		 System.out.println(canConstructLetter("aa", "aab")); //true
		 System.out.println(canConstructLetter("visa", "The quick brown fox jumps over the lazy dog")); //true
    }
}
