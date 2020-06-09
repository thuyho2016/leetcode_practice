
package string;

import java.util.ArrayDeque;
import java.util.Deque;

/*
 * 1047. Remove All Adjacent Duplicates In String (easy level)
https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/

Given a string S of lowercase letters, a duplicate removal consists of choosing two adjacent and equal letters, and removing them.

We repeatedly make duplicate removals on S until we no longer can.

Return the final string after all such duplicate removals have been made.  It is guaranteed the answer is unique.

 

Example 1:

Input: "abbaca"
Output: "ca"

Explanation: 
For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  
The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".


Time : O(n) and Space O(1) 

*/

public class RemoveAdjacentDuplicates{
        
	/* Solution: Two Pointers

   	slow index: position for the next suitable char to be copied,
    meaning of fast index, current char that is checking
	 */
     
    public static String removeDuplicates(String s) {
       
        int n = s.length();
        int slow = 0; 
        char[] arr = s.toCharArray();  //[a, b, b, a, c, a]
        
        for(int fast = 0; fast < n; fast++) {
            if(slow == 0 || arr[fast] != arr[slow - 1]) { // slow = 0, fast = 4
                arr[slow] = arr[fast];   // assign arry[0] = arr[4] = c, so arr = [c, b, b, a, c, a]. slow = 1. fast = 5, arr = [c, a, b, a, c, a]
                slow++;
            } else {
                slow--;
            }
        }
        return new String(arr, 0, slow); //[c, a, b, a, c, a] , slow = 2 so return String = ca
    }
    
    
    /* Easy to understand
     * i refers to the index to set next character in the output string.
	j refers to the index of current iteration in the input string.

	Iterate characters of S one by one by increasing j.

	If S[j] is same as the current last character S[i - 1],
	we remove duplicates by doing i -= 2.

	If S[j] is different as the current last character S[i - 1],
	we set S[i] = S[j] and increment i++.

     */
    public static String removeDuplicates2(String s) {
    
	    int i = 0, n = s.length();
	    char[] res = s.toCharArray();
	    
	    for (int j = 0; j < n; ++j) {
	    	res[i] = res[j];
	        if (i > 0 && res[i - 1] == res[i]) { // count = 2
	            i -= 2;
	    	}
	        ++i;
	       
	    }
	    return new String(res, 0, i);
    }
    
    
    //StringBuilder - If current char is same as the end of the StringBuilder, delete the char at end; otherwise, append it at the end.
    public String removeDuplicates3(String S) {
        StringBuilder sb = new StringBuilder();
        
        for (char c : S.toCharArray()) {
            int size = sb.length();
            
            if (size > 0 && sb.charAt(size - 1) == c) { 
                sb.deleteCharAt(size - 1); 
            }else { 
                sb.append(c); 
            }
        }
        return sb.toString();
    }
    
    public static void main(String[] args) {
    	
    	String s =  "abbaca";
    	
    	System.out.println("Output: " + removeDuplicates(s)); //"ca"
    	
    
    }

}
	
	