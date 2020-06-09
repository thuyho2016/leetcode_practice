package string;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

/*
 * given a string S consisting of N lowercase letters, returns the minimum number of letters that must be deleted 
 * to obtain a word in which every letter occurs a unique number of times. 
 * We only care about occurrences of letters that appear at least once in result.

1.	Given S = “aaaabbbb” the function should return 1. We can delete one occurrence of a or one occurrence of b. Then one letter will occur four times and the other three times
2.	Given S =” eeee”, the function should return 0 (there is no need to delete any characters)
3.	Given S= “example”, the function  should return 4

 */
public class MininumNumberLetters_ToBeDeleted {
	/**
	Approach:
	We can count the frequencies of all 26 possible lower case characters.
	Then construct a map that tells how many letters have a given frequency.
	Iterate the map from higher frequencies to lower.
	If more than 1 letter has a given frequency we delete 1 occurrence of all but 1 letter with that frequency.
	Deleting 1 occurrence of those letters increases the number of the letter that has the frequency one less.
	If the occurrence of a letter is 0 then it is not present in the string.

	https://www.geeksforgeeks.org/minimum-deletions-required-to-make-frequency-of-each-letter-unique/

	 */
	
	//Returns the mininum number of characters to be deleted 
	//so that all remaining characters occur unique number of times. 
	public static int minimumDeletions(char[] s) 
	{ 
	 // Stores how many times a character occurs. freqLetter[0] 
	 // stores the frequency of letter 'a' 
		 int[] freqLetter = new int[26]; 
		
		 for (int i = 0; i < 26; i++) 
		     freqLetter[i] = 0; 
		
		 for (int i = 0; i < s.length; i++) 
		 { 
		     freqLetter[s[i] - 'a']++; 
		 } 
		
		 // Number of characters that  have a given frequency 
		 Map<Integer, Integer > freqMap = new TreeMap<Integer, Integer >(Collections.reverseOrder()); 
		
		 for (int i = 0; i < 26; i++)  
		 { 
			 // insert the frequency of all lowercase letters in the map that have a  frequency greater than 0 
		     if (freqLetter[i] != 0) 
		     { 
		          freqMap.put( freqLetter[i], freqMap.getOrDefault(freqLetter[i], 0) + 1 ); 		   
		      } 
		        
		 } 
	   
		 // Stores the number of deletions needed 
		 int deletions = 0; 
		
		 Set<Map.Entry<Integer, Integer>> set = freqMap.entrySet(); 
		 for (Map.Entry<Integer, Integer> me : set) 
		 { 		
		     // If frequency is 0 then all occurrences have been deleted. 
		     if (me.getKey() == 0) 
		         break; 
		
		     // Till more than 1 character has a  given frequency. 
		     while (me.getValue() > 1)  
		     { 		
		         // Delete a character that has conflicting frequency 
		         deletions++; 
		
		         // Now 1 less character has the given frequency  
		         freqMap.put(me.getKey(), freqMap.get(me.getKey()) - 1); 
		
		         // But 1 more character has the frequency  - 1 
		         
		         if (freqMap.containsKey(me.getKey() - 1))  
		         { 
		             freqMap.put(me.getKey() - 1,  freqMap.get(me.getKey() - 1) + 1); 
		         } 
		         else
		             freqMap.put(me.getKey() - 1, 1); 
		     } 
		 } 
		 return deletions; 
	} 

	
	public static void main (String[] args)
    {  
		String s = "aaacccc"; 
		char[] arr =  s.toCharArray();
		System.out.println(minimumDeletions(arr));
		
		s = "example";
		char[] arr2 =  s.toCharArray();
		System.out.println(minimumDeletions(arr2));
    }

}
