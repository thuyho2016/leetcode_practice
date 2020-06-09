
package string;
/*
 * 1221. Split a String in Balanced Strings
https://leetcode.com/problems/split-a-string-in-balanced-strings/

Balanced strings are those who have equal quantity of 'L' and 'R' characters.

Given a balanced string s split it in the maximum amount of balanced strings.

Return the maximum amount of splitted balanced strings.
 

Example 1:

Input: s = "RLRRLLRLRL"
Output: 4
Explanation: s can be split into "RL", "RRLL", "RL", "RL", each substring contains same number of 'L' and 'R'.

Example 2:

Input: s = "RLLLLRRRLR"
Output: 3
Explanation: s can be split into "RL", "LLLRRR", "LR", each substring contains same number of 'L' and 'R'.

Example 3:

Input: s = "LLLLRRRR"
Output: 1
Explanation: s can be split into "LLLLRRRR".
Example 4:

Input: s = "RLRRRLLRLL"
Output: 2
Explanation: s can be split into "RL", "RRRLLRLL", since each substring contains an equal number of 'L' and 'R'


Solution: use a count to determine whether or not the given substring is balanced. 
Increment your count when you see 'L' and decrement your count when you see 'R'. 
If your count is every 0, increment the number of balanced strings you've found.

Time complexity: O(n)
Space: O(1)
*/

public class BalancedStrings_ReturnMaxNumberSplit{
 
	public static int balancedStringSplit(String s) {
		int balancedCount = 0;
	    int count =0;
	    
	    for (int i = 0; i < s.length(); i++) {
	      char current = s.charAt(i);
	      
	      if (current =='L') {
	    	  count++;
	      } else if ( current =='R') {
	    	  count--;
	      }
	      
	      if (count == 0) 
	    	  balancedCount++;
	    }
	    
	    return balancedCount;
    }
    
    public static void main(String[] args) {
     	
    	String s = "RLRRLLRLRL";
    	System.out.println(balancedStringSplit(s));  //4
    
   }

}
	
	