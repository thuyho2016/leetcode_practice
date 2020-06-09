
package string;

import java.util.HashSet;
import java.util.Set;

/*
1062. Longest Repeating Substring
https://leetcode.com/problems/longest-repeating-substring/

Given a string S, find out the length of the longest repeating substring(s). Return 0 if no repeating substring exists.


Input: "abcd"
Output: 0
Explanation: There is no repeating substring.


Input: "abbaba"
Output: 2
Explanation: The longest repeating substrings are "ab" and "ba", each of which occurs twice.


Input: "aabcaabdaab"
Output: 3
Explanation: The longest repeating substring is "aab", which occurs 3 times.



Input: "aaaaa"
Output: 4
Explanation: The longest repeating substring is "aaaa", which occurs twice.


Solutions:
dp[i][j] means longest repeating substring that ends at i and j position of the string.

if s[i] == s[j] then dp[i][j] = dp[i - 1][j - 1] + 1;
else dp[i][j] = 0

It is easy to see that dp[i][j] only depends on dp[i - 1][j - 1], 
then we can reduce the space complexity to linear.
	  
	  
*/

public class LongestRepeatingSubstring{

	//In each step, just find the infrequent elements (is less than k times) as splits 
	//since any of these infrequent elements couldn't be any part of the substring we want.
	
	public static int longestRepeatingSubstring(String s) {
		
		int res = 0;
        int n = s.length();
        int[] dp = new int[n + 1]; //[0, 0, 0, 0, 0, 0]
        
        for (int i = 1; i <= n; i++) {
		   // Need start from i - 1 to use values from last iteration
            for (int j = i - 1; j >= 1; j--) {
                if (s.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[j] = dp[j - 1] + 1;  //dp[1] = dp[0] + 1 = 1 -> dp =[0, 1, 0, 0, 0, 0];[0, 1, 2, 0, 0, 0]
                } else {
                    dp[j] = 0;
                }
                
                res = Math.max(res, dp[j]); //max(0,1) = 1
            }
        }
        
        return res;
	}
	
	//Binary Search - Divide and Conquer
	public static int longestRepeatingSubstring2(String s) {
	    int start =1, end =s.length() - 1;
	    int max=0;
	    
	    while(start <= end){
	        int mid = start + (end -start)/2;  //2 , 3
	        
	        if(binarySearch(mid,s)) { //true
	            start = mid +1; //3
	            max = mid;  //2
	        }
	        else {
	        	end = mid - 1;  //4-1 = 3
	        }
	    }

	    return max;
	}

	//substring(beginIndex,endIndex) Returns substring begins at the specified beginIndex and extends to the character at index endIndex - 1
	//cur = s.sbustring (0,2) = aa; cur = s.substring(1,3) = aa, cur = s.substring(0,3) = aaa ; i = 2, cur = aab
	public static boolean binarySearch(int len,String s){ //len = 2, len =3

	    Set<String> set = new HashSet<>();
	    
	    for (int i = 0; i + len <= s.length(); i++) { //i <= s.length()- len
	        String cur = s.substring(i, i + len); //s.sbustring (0,2) = aa, substring(1,3) = aa, substring(0,3) = aaa, cur = aab
	    	if(set.contains(cur)) {
	    		return true;
	    	}
	        set.add(cur);  //if not seen, add aa, so set = [aa]; 2nd round set =[aaa], set = [aaa, abb], set = [aaa, abb, aab]
	    }
	    return false;
    }
	
	public static void main(String[] args) {
	   String s = "aaabb";
	   //System.out.println("Length: " + longestRepeatingSubstring(s));
	   System.out.println("Length: " + longestRepeatingSubstring2(s));
	   	
	   String s2 = "abbaba";
	   //System.out.println("Length: " + longestRepeatingSubstring(s2));
	   System.out.println("Length: " + longestRepeatingSubstring2(s2));  //2
	   
	   String s3 = "aaaaa";
	   System.out.println("Length: " + longestRepeatingSubstring2(s3)); //4
	
	}

}
	
	