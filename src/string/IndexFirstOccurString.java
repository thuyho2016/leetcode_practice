package string;
/*
 * 28. Implement strStr()
 * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Example 1:

Input: haystack = "hello", needle = "ll"

Output: 2  (first index of ll is 2 )

Example 2:

Input: haystack = "aaaaa", needle = "bba"
Output: -1

NOTE:  Not use Java's built-in functions
	
	i iterates each position in the haystack
	j iterates each position in the needle
	when the haystack matches the needle in one position, match the rest.
	
	
 */
public class IndexFirstOccurString {
	
	//Use IndexOf
	public static int strStr(String haystack, String needle) {
	       if (needle.length() == 0) {
	         return 0;
	         
	       } else if (haystack.length() == 0) {
	         return -1;
	       } else {
	        return haystack.indexOf(needle);
	       }
	         
	  }
	  
	
	public static int strStr2(String s, String t) {
	   
	    
	    if (t.length() == 0) return 0;
	    if (t.length() > s.length()) return -1;
	    
	    int diff = s.length() - t.length();
	    System.out.println("Diff len:  " + diff);
	    
        for (int i = 0; i <= s.length() - t.length(); i++) { //3
        	
            for (int j = 0; j < t.length() && s.charAt(i + j) == t.charAt(j); j++)
            	
                if (j == t.length() - 1) { // (j = 1) == 1 , then return i
                	return i;
                }
        }
        return -1;
	   
	}
	
	  public static void main(String[] args) {
	    System.out.println(strStr2("hello", "ll"));
	  }
}
