/*
639. Decode Ways II
https://leetcode.com/problems/decode-ways-ii/

A message containing letters from A-Z is being encoded to numbers using the following mapping way:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Beyond that, now the encoded string can also contain the character '*', which can be treated as one of the numbers from 1 to 9.

Given the encoded message containing digits and the character '*', return the total number of ways to decode it.

Also, since the answer may be very large, you should return the output mod 109 + 7.

Example 1:
Input: "*"
Output: 9
Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".

Example 2:
Input: "1*"
Output: 9 + 9 = 18

Note:
The length of the input string will fit in range [1, 105].
The input string will only contain the character '*' and digits '0' - '9'.

 */
public class DecodeWaysII {
	/**
	     Single character --> single letter --> easy handle
		 Double character --> single letter --> 4 cases

		 num + num

		 "*"+ num

		 num + "*"

		 "" + ""
	 */

	 public static  int numDecodings(String s) {
		
		 if(s.length() == 0 || s == null || s.charAt(0) == '0') return 0;
		 
		 long[] dp = new long[s.length() + 1];
		 // initialize dp[0] and dp[1]
		 dp[0] = 1;
		 
		 if(s.charAt(0) == '*') { // case: first character is * 
			 dp[1] = 9;   //[1, 9]
		 } else {
			 dp[1] = 1;
		 }
		 
		 for(int i = 2; i <= s.length(); i++) {   //[1, 1, 0]

		      // first part: single character --> single letter, e.g 1->A, 2->B, * -> (1-9)
		      if(s.charAt(i - 1) == '*')  
		    	  dp[i] = 9 * dp[i - 1];    //[1, 1, 9]
		      else if (s.charAt(i - 1) != '0')  
		    	  dp[i] = dp[i - 1];
		      
		      
		      // Second part: two characters --> single letter -- 4 cases
		      if(s.charAt(i - 1) != '*') { 
		          if(s.charAt(i - 2) != '*') { 
		              // case 1: num + num
		              int num = (s.charAt(i - 2) - '0') * 10 + s.charAt(i - 1) - '0';
		              if(num >= 10 && num < 27)  dp[i] += dp[i - 2];
		          } else {
		              // case 2: * + num
		              // current >= 7 means, * can just be 1.   e.g: 17 is valid, 27 is not valid
		              if(s.charAt(i - 1) - '0' >= 7)  dp[i] += dp[i - 2]; 
		              else dp[i] += 2 * dp[i - 2];  // current is less than 7, * can be 1 and 2.  e.g: 14 and 24 are valid
		          }
		      }
		      
		      else if(s.charAt(i - 1) == '*') {  //Input = "1*"
		          //case 3: num + *
		          if(s.charAt(i - 2) - '0' == 1)   // s.charAt(0) = 1
		        	  dp[i] += 9 * dp[i - 2];  // 11 -> 19   , dp[2] = 9 + 9 * dp[0] = 9 + 9 = 18
		          if(s.charAt(i - 2) - '0' == 2)  
		        	  dp[i] += 6 * dp[i - 2];  // 21 -> 26
		          
		          //case 4: * + *
		          if(s.charAt(i - 2) == '*') {
		              dp[i] += 15 * dp[i - 2];  // (11 -> 19) + (21 -> 26)
		          }
		      }
		      
		      dp[i] = dp[i] % 1000000007;
		  }
		  return (int)dp[s.length()];  //[1, 1, 18]
	 }
	 
	 public static void main(String args[])
	 {
	     String input = "*";
	     System.out.println("ways to decode: " + numDecodings(input));  //9
	     
	     String input2 = "1*";
	     System.out.println("ways to decode: " + numDecodings(input2)); //18
     
	 }

}
