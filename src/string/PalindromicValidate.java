package string;

/*
 * 125. Valid Palindrome
 * https://leetcode.com/problems/valid-palindrome/
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases
 * Example 1:

Input: "A man, a plan, a canal: Panama"
Output: true
Example 2:

Input: "race a car"
Output: false

Time Complexity: O(N) where N is the length of the string. Each of two checks of whether some substring is a palindrome is O(N)

Space Complexity: O(1) additional complexity. Only pointers were stored in memory.

 */ 

public class PalindromicValidate  {
	
	//Input is String 
	// Use 2 pointers - start from 0 index and end from last index
	// using s.charAt(..) to comapare each character
	// ignore non-character like : , space. So use remove them by replaceAll
	public static boolean isPalindrome(String s) {

	    if(s==null || s.length()==1) return true;
	    
		// replace all characters that are not alphabets and numbers and convert the alphabets to lower case.
	     s= s.replaceAll("[^a-zA-Z0-9]","").toLowerCase();
	    
	    int start = 0;
	    int end = s.length() - 1;
	    
	    while(start < end){
	        if(s.charAt(start) == s.charAt(end)){
	            start++;
	            end--;
	        }else {
	            return false;
	        }
	    }
	    return true;
	}	
	
	// Input is Number  
	// Step: if input is number, then convert it to string
	// use 2 pointers to compare each element
	
	public static boolean isPalindromeNumber(int x) {
	    String s = String.valueOf(x);
	    int start = 0;
	    int end = s.length() - 1;
	    
	    while(start < end){
	    	if(s.charAt(start) == s.charAt(end)){
	            start++;
	            end--;
	        } else {
	            return false;
	        }
	    }
	    return true;
	}
	
	//without converting the input number to a string. 
	public static boolean isPalindromeNumber2(int num){
	   if(num < 0) return  false; 
	   int reversed = 0, remainder, original = num;
	   
	   while(num != 0) {
	        remainder = num % 10; // reversed integer is stored in variable
	        num = num / 10;  //the last digit is removed from num after division by 10.
	        reversed = reversed * 10 + remainder; //multiply reversed by 10 then add the remainder so it gets stored at next decimal place.
	        
	    }
	    // palindrome if original and reversed are equal
	    return original == reversed;
	}
	
	public static void main (String[] args)
    {
		String s = "top, pot";  
		
		//by charAt
		System.out.println("Is a palindrome " + isPalindrome(s)); //true
		
	    String s2 = "A man, a plan, a canal: Panama";
		System.out.println("Is a palindrome " + isPalindrome(s2)); //true 
		
		int num = 121;
		System.out.println("Is a palindrome " + isPalindromeNumber(num));
		System.out.println("Num 121 is a palindrome ?" + isPalindromeNumber2(num));
    }
	
}