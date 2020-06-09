package string;

/*
 * 680. Valid Palindrome II ( easy level)
 * https://leetcode.com/problems/valid-palindrome-ii/
 Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

Example 1:
Input: "aba"
Output: True

Example 2:
Input: "abca"
Output: True
Explanation: You could delete the character 'c'.

https://www.geeksforgeeks.org/remove-character-string-make-palindrome/
 */ 

public class PalindromicValidate2_DeleteAtMostChar  {
	
	//Input is String 
	// Use 2 pointers - start from 0 index and end from last index
	// using s.charAt(..) to comapare each character
	
	public static boolean validPalindrome(String s) {

	    if(s==null || s.length()==1) return true;
	    
	    int start = 0;
	    int end = s.length() -1 ;
	    
	    while(start < end){
	        if(s.charAt(start) == s.charAt(end)){ //b != c
	        	start++;
	            end--;
	        } else {
	        	return isPalindrome(s, start + 1, end) ||  isPalindrome(s, start, end - 1);	  	      
	        }	        
	    }
	    return true;
	}	
	
	public static boolean isPalindrome(String s, int i, int j) {
		 while(i <= j){
	        if(s.charAt(i++) != s.charAt(j--)){
	            return false;
	        
	        }
		 }
		 return true;
	}
	
	
	public static void main (String[] args)
    {
		String s = "aba";  
		
		//by charAt
		System.out.println("Is a palindrome " + validPalindrome(s)); //true
		
	    String s2 = "abcca";
		System.out.println("Is a palindrome " + validPalindrome(s2)); //true 
		
		String s3 = "racecar";
		System.out.println("Is a palindrome " + validPalindrome(s3)); //true
		
    }
	
}