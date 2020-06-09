
/* 
 * 91. Decode Ways (Medium) 
https://leetcode.com/problems/decode-ways/

A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26

Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:

Input: "12"
Output: 2 ways
Explanation: It could be decoded as "A B" (1 2) or "L" (12).

Example 2:

Input: "226"
Output: 3 ways
Explanation: It could be decoded as "B Z" (2 26), "V F" (22 6), or "B B F" (2 2 6).


Decode one character or 2 character : (22) 6 , 2 (26), or 2 2 6  
      
s = 123

build up from right =>

num_ways ("") => 1 (empty string can be represented by empty string) (i.e. num_ways[n] = 1) 


NOTE: only for build up with a valid string. Empty string on it's own doesn't need to be decoded.

num_ways ("3") => 1 (only one way), i.e. num_ways[n-1] = 1
num_ways ("23") => "23" or "2"-"3",
num_ways ("33") => "3" "3"
num_ways ("123") => "12"(num_ways("3")) + "1" ("num_ways("23")) (i.e. num_ways[i+2] + num_ways[i+1])
num_ways ("323") => "3"(num_ways("23")) (i.e. num_ways[i+1])

case with 0:

num_ways ("103")
num_ways ("3") => 1 (only one way)
num_ways ("03") => 0 (can't decode 0)
num_ways ("003") => "00"(num_ways("3")) + "0"(num_ways("03")) => no way to decode "00" = 0 + 0
num_ways ("103") => "10"(num_ways("3")) + "1"(num_ways("03")) => num_ways[i+2] + num_ways[i+1](= 0 in this case)
num_ways ("1003") => "10"(num_ways("03")) + "1"(num_ways("003")) => same eq = 0(no way to decode "03") + 0(no way to decode 003)

Therefore, if i = '0', let memo[i] = 0, also implements for a string where the ith character == '0', string[i:end] can be decoded in 0 ways.

For example, the String now is "123xxxx" and we know all the result from 2. 
Because 12 < 26, we can make this string either "12" + "3xxxx" or 1 + 23xxxx which is exactly memo[i] = memo[i-1]+memo[i-2]


Time complexity: O(n)
Space complexity : O(1). Constant extra space is used
 */

public class DecodeWays_DP
{
	  
	// top down DP - youbtube 
	public static int numDecodings(String s) {
        if(s == null || s.length() == 0) return 0;
        int len = s.length();  //3
        
        int[] dp = new int[len + 1];   
        dp[0] = 1;     //dp = [1, 0, 0,0]
        
        dp[1] = s.charAt(0) == '0' ? 0 : 1; // dp[1] depends on the first character.   dp = [1, 1, 0, 0]
       // if(s.charAt(0) != '0') dp[1] = 1;
        
        for(int i = 2; i < len + 1; i ++){
            
        	int oneDigits = Integer.valueOf(s.substring(i - 1, i));  //2 , 6
        	if (oneDigits >= 1) { 
        		System.out.println("dp[i]=" + dp[i] + " + dp[i-1]=" + dp[i-1]);
        		dp[i] += dp[i-1];   //dp = [1, 1, 1, 0], dp = [1, 1, 2, 2]
        	}
        	//if(s.charAt(i - 1) != '0') // one digit
            //    dp[i] += dp[i - 1];
            
            int twoDigits = Integer.valueOf(s.substring(i - 2, i)); //last 2 digits = 22, 26
            if(twoDigits >= 10 && twoDigits <= 26) {
            	System.out.println("dp[i]=" + dp[i] + " + dp[i-2]=" + dp[i-2]);
                dp[i] += dp[i - 2];  //dp = [1, 1, 2, 0], dp = [1, 1, 2, 3]
            }
       
        }
        return dp[len]; //dp[3] = 3
    }
	  
   public static void main(String args[])
   {
  //   String input = "12";
  //   System.out.println("ways to decode: " + numDecodings(input));  //12 -> AB,L -> 12
     
     String input2 = "226";
     System.out.println("number of ways to decode: " + numDecodings(input2));
     
   }
	
	
	
}