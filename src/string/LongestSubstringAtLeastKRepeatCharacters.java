package string;
/*
395. Longest Substring with At Least K Repeating Characters

Find the length of the longest substring T of a given string (consists of lowercase letters only) 
such that every character in T appears no less than k times.

Example 1:

Input:
s = "aaabb", k = 3

Output: 3
The longest substring is "aaa", as 'a' is repeated 3 times.

Example 2:

Input:
s = "ababbc", k = 2

Output:5
 
The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.

Solutions:
- this program achieves dividing string into substrings by recursive calling.

- Being a valid string means all the characters in the string appears more than k times.
So, use characters appeared less than k times as "splits", to divide the string into substrings,
and then recursively calls itself onto each substring to determine whether the substring is valid or not. 

Because there might be multiple valid substrings in a string, we store the length of the substring in variable "result"
and only returns the longest substring length using Math.max() function.


*/

public class LongestSubstringAtLeastKRepeatCharacters{

		
	//2 pointers - recursive
	public static int longestSubstring(String s, int k) {
		if (s == null || s.length() == 0) return 0;
		
        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++; //[1, 0, 0, 0, 0
            System.out.println(c + " count[" + count[c - 'a'] + "]");
        }
        //count = [3, 2, 0, 0..]
        int start = 0;
        int end = 0;
        
        while(end < s.length()) {
            char c = s.charAt(end); //end =0, so c = a ; end = 3, so c = b
            
            if(count[c - 'a'] < k) {
                if(start == end) {
                    start++;
                } else { // 0 < 3
                    return Math.max(longestSubstring(s.substring(start, end), k), 
                                   longestSubstring(s.substring(end + 1), k));
                }
            }
            end++;  //shift end to right
        }

        return end-start;
    }
	
	
	
	public static void main(String[] args) {
	   String s = "aaabb";
	   int res = longestSubstring(s, 3);
	   System.out.println("Length: " + res);
	   
	  String s1 = "ababbc";
	  System.out.println("Length: " + longestSubstring(s1, 2));
	
	}

}
	
	