package string;

/*
 * 567. Permutation in String
https://leetcode.com/problems/permutation-in-string/

Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.

 

Example 1:

Input: s1 = "ab" s2 = "eidbaooo"
Output: True
Explanation: s2 contains one permutation of s1 ("ba").
Example 2:

Input:s1= "ab" s2 = "eidboaoo"
Output: False
     
Apply Sliding Window
     
     
Time complexity : O(l_1+(l_2-l_1)). where l1 is the length of string l1 and l2 is the length of string l2
Space complexity : O(1). Constant space is used.
     
 */


public class PermutationInString {
	
	// Optimized Sliding Window
	/**
	Count characters in s1 and store them in array.

	If right pointer in s2.charAt exists in array, decrement array and right++. If not, re-increment value at left pointer and left++.
	
	*/
	public static boolean checkPermutation(String s1, String s2) {
		int len1 = s1.length(), len2 = s2.length();
		int left = 0, right = 0;
		
		if ( len1 > len2) return false;
		
		int[] arr = new int[26];
		
		 // initialize the count for each unique character
		for (int i =0; i < len1; i++) {
			arr[s1.charAt(i) - 'a']++; //[1, 1, 0, 0, 0, 0, 0,...]
		}
		
		while (right < len2) {   //0 < 8
			int index = s2.charAt(right) - 'a';  //4
			
			if(arr[index] > 0) { //
				arr[index]--;  //shrink window 
				right++;
			} else {
				arr[s2.charAt(left) - 'a']++; //increase window  [1, 1, 0, 0, 1,0,...]
				left++;
			}
			
			if (right - left == len1) {
				return true;
			}
		}
		return false;
	}	 
		
	
	public static void main(String[] args) { 		  
		  
		String s1 = "ab";
		String s2 = "eidbaooo"; 
		System.out.println(checkPermutation(s1, s2));	
		
		s1 = "ab";
		s2 = "eidboaoo"; 
		System.out.println(checkPermutation(s1, s2));
	} 
	
}




