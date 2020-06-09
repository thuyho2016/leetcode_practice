package string;

import java.util.Arrays;

/*
 * 242. Valid Anagram
 * https://leetcode.com/problems/valid-anagram/
 * 
 * Given two strings s and t , write a function to determine if t is an anagram of s.

	Example 1:

	Input: s = "anagram", t = "nagaram"
	Output: true
	Example 2:

	Input: s = "rat", t = "car"
	Output: false


There are 26 letters in the English alphabet which range from 'a' to 'z' 
Time complexity : O(n)
Space complexity : O(1)

 */

public class Anagram_Validation {
	
	public static boolean isAnagram(String s, String t) {
	    
	    int a = s.length();
	    int b = t.length();
	    if(a != b) //a and b have different length
	        return false;
	    
	    int[] count = new int[26];  // to store count index from s and t 	                             
	    
	    for (int i =0; i < a; i++) {
	      int ind1 = s.charAt(i) - 'a';  //'b' - 'a' = 1, 'a' - 'a' = 0, 'c' - 'a' = 2
	      //  s.charAt(i) Returns the char value at the specified index
	      System.out.println( "Index from 1st string:  " + ind1 );
	      count[s.charAt(i) - 'a']++;   // s.charAt(i) Returns the char value at the specified index

	      
	      int ind2 = t.charAt(i) - 'a';
	      System.out.println( "Index from 2nd string:  " + ind2 );	      
	      count[t.charAt(i) - 'a']--;
	    
	    }
	    
	    for (int i = 0; i < 26; i++) 
	      if (count[i] != 0)  // count = 0, that means each element has same character
	         return false;
	    return true;
  	}
	 
	// convert to char array, sort by Array.sort, then compare two arrays
	public static boolean isAnagram2(String s, String t) {
		if(s.isEmpty() && t.isEmpty()){
			return true;
		}
		
		if(s.length() != t.length()){
			return false;
		}
		
		char[] a = s.toCharArray();
		char[] b = t.toCharArray();

		Arrays.sort(a);
		Arrays.sort(b);
		
		for(int i = 0; i < a.length; i++) {
			if(a[i] != b[i]) {
				return false;
			}
		}
		return true;
	}

	
	  
	public static void main(String[] args) {
		String s = "anagram";
		String t = "nagaram";
		System.out.println(isAnagram2(s,t));
    
		String s2 = "bac";
		String t2 = "abc";
		System.out.println(isAnagram2(s2,t2));
		
		// s = "rat", t = "car"
		String s3 = "cog";
		String t3 = "dog";
		System.out.println(isAnagram(s3,t3));  //c , d indexes [0, 0, 1, -1, 0, 0, 0, 0,...]
	}
	
	
	
}
