package matrix;

/*
 * 10. Regular Expression Matching
 * https://leetcode.com/problems/regular-expression-matching/
 * 
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like . or *.

Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".

Example 2:

Input:
s = "aa"
p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".


Solution: Dynamic Programming

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

 Consider situations: i --> text, j --> pattern
 
 T[i][j] = 
 			T[i -1][j-1]  if s[i] == p[j] || p[j] == '.'
 			
 			T[i -1][j-1]  if  '*' represent 0                    \  if p[j] == '*'
 			T[i -1][j]    if s[i] == p[j-1] || p[j-1] == '.'     /
 
            otherwise, false
            
 
 
 Draw table:
 
Index    0  1  2  3  4  5  6
   	     	x  a  *  b  .  c   <-- pattern
   0     T  F  F  F  F  F  F
t  1  x  F  T  F  T  F  F  F
e  2  a  F  F  T  T  F  F  F
x  3  a  F  F  F  T  F  F  F
t  4  b  F  F  F  F  T  F  F
   5  y  F  F  F  F  F  T  F
      c  F  F  F  F  F  F  T
 
Time and Space complexity: O (m x n) 

 */

public class RegularExpression_DB {
	
	public static boolean isMatch(String s, String p) {
		char[] text = s.toCharArray();
		char[] pattern = p.toCharArray();
        
		boolean T[][]=  new boolean[s.length() + 1][p.length() + 1];
        T[0][0] = true;
        
        //case of a*b*c* or a*b*  or a*
        for (int i = 1; i < p.length() + 1; i++) {
        	if(pattern[i-1] == '*')
        		T[0][i] = T[0][i - 2]; 
        	else 
        		T[0][i] = false;
        }
        
        //sub-string s compare to empty string
        for(int i = 1; i < s.length() + 1; i++)
            T[i][0] = false;
        
        // calculate all the value in the table
        for (int i = 1; i < T.length; i++) {
        	for (int j = 1; j < T[0].length;j++) {
        		
        		if (pattern[j - 1] == '.' || pattern[j - 1] == text[i - 1]) {
        			T[i][j] = T[i - 1][j - 1 ];
        			
        		} else if (pattern[j - 1] == '*') {
        			T[i][j] = T[i][j - 2];
        			
        			if(pattern[j-2] == '.' || pattern[j - 2] == text[i - 1] ) {
        				T[i][j] = T[i][j] | T[i - 1][j];
        			}
        			
        		} else {
        			T[i][j] = false;
        		}
        	}
        }
        
        return T[text.length][pattern.length];
	}
	
	public static void main(String args[])
	{

		System.out.println(isMatch("aa", "a")); //false
		System.out.println(isMatch("aa", "a*")); //true
	}

}
