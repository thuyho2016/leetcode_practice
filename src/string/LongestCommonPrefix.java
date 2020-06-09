
package string;

/*
 * 14. Longest Common Prefix
 * https://leetcode.com/problems/longest-common-prefix/
 * 
Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:

Input: ["flower","flow","flight"]
Output: "fl"

Example 2:

Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.

*/

public class LongestCommonPrefix {
	
	/**
	 * Prefer this solution
	 * go all characters in the first string,
	 * For each character from the first string, check if other strings has the same character.
     * if it does, add character to longestCommonPrefix and incresae index
     * 
     * Time complexity : O(S) , where S is the sum of all characters in all strings
     * Space complexity : O(1). We only used constant extra space.
     */
	public static String longestCommonPrefix(String[] s) {
        String longestComonPrefix = "";
        
		if (s == null || s.length == 0) return "";
	   
	  //  for(char c: s[0].toCharArray())
		for (int i = 0; i < s[0].length() ; i++) {  //go all characters in the first string
	    	
	        char c = s[0].charAt(i);
	        for (int j = 1; j < s.length; j ++) {   // j = 1 to start from second string 
	            
	        	if (i >= s[j].length() || c != s[j].charAt(i))  // character is not the same with other string 
	                return longestComonPrefix;             
	        }
	        longestComonPrefix += c;
	       
	    }
	    return longestComonPrefix;
	}
	
    //Divide and conquer
	public static String longestCommonPrefix_DC(String[] strs) {
	    if (strs == null || strs.length == 0) return "";    
	        return longestCommonPrefix(strs, 0 , strs.length - 1);
	}

	private static String longestCommonPrefix(String[] strs, int l, int r) {
	    if (l == r) {
	        return strs[l];
	    }
	    else {
	        int mid = (l + r) / 2;
	        String lcpLeft =   longestCommonPrefix(strs, l , mid);
	        String lcpRight =  longestCommonPrefix(strs, mid + 1, r);
	        
	        return commonPrefix(lcpLeft, lcpRight);
	   }
	}

	private static String commonPrefix(String left,String right) {
	    
		int min = Math.min(left.length(), right.length());       
	    for (int i = 0; i < min; i++) {
	        if ( left.charAt(i) != right.charAt(i) )
	            return left.substring(0, i);
	    }
	    return left.substring(0, min);
	}
	
	
	public static void main(String[] args) 
	{ 
	    String[] s = {"flower","flow","flight"};
	    System.out.println("LCP is " + longestCommonPrefix(s));  //fl
	    System.out.println("LCP is " + longestCommonPrefix_DC(s));
	    
	    String[] s2 = {"leets","leetcode","leeds"};
	    System.out.println("Common prefix is " + longestCommonPrefix(s2));  //lee , lcp = {1,4}
	    System.out.println("Common prefix is " + longestCommonPrefix_DC(s2));
	    		
	} 
	  
}
