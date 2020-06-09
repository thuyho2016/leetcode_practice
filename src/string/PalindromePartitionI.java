package string;

import java.util.ArrayList;
import java.util.List;

/*
 * 131. Palindrome Partitioning
https://leetcode.com/problems/palindrome-partitioning/

Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

Example:

Input: "aab"
Output:
[
  ["aa","b"],
  ["a","a","b"]
]


 */ 

public class PalindromePartitionI  {
	
	 public static List<List<String>> partition(String s) {
	        List<List<String>> res = new ArrayList<>();
	        
	        partition(res, new ArrayList<>(), 0, s);	        
	        return res;
	 }
	 
	 private static void partition(List<List<String>> result, List<String> list, int start, String s) {
		 if (start == s.length()) {
			 result.add(new ArrayList<>(list));
			 return;
		 }  else {  // if start < s.len, no else is also OK
			 
			 //IMPORTANT start index + 1
			 for (int i = start + 1; i <= s.length(); i++) {
				 String subStr = s.substring(start, i);
				 
				 if (!isPalindrome(subStr)) { // check substring is panlindrome or not. If it is panlindrom, then add to list.
					 continue;
				 }
				 list.add(subStr);
				 
				 //call recursive
				 partition(result, list, i, s);
				 list.remove(list.size() - 1); //remove last character from current list
			 }
		 }
	 }
	 
	 
	//Input is String 
	// Use 2 pointers - start from 0 index and end from last index
	// using s.charAt(..) to compare each character	
	public static boolean isPalindrome(String s) {

	   // if(s == null || s.length()== 1) return true;
	    
	    int start = 0;
	    int end = s.length() -1 ;
	    
	    while(start < end){
	        if(s.charAt(start) == s.charAt(end)){ //b == b, move pointers to next char
	        	start++;
	            end--;
	        } else {
	        	return false;	  	      
	        }	        
	    }
	    return true;
	}	
	
	
	public static void main (String[] args)
    {
		String s = "aab";  

		System.out.println(partition(s)); //["aa","b"], ["a","a","b"]
		
    }
	
}