
package string;

import java.util.ArrayDeque;
import java.util.Deque;

/*
 * 1209. Remove All Adjacent Duplicates in String II
https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/

Given a string s, a k duplicate removal consists of choosing k adjacent and equal letters from s 
and removing them causing the left and the right side of the deleted substring to concatenate together.

We repeatedly make k duplicate removals on s until we no longer can.

Return the final string after all such duplicate removals have been made. It is guaranteed that the answer is unique.

 
Example 1:

Input: s = "abcd", k = 2
Output: "abcd"
Explanation: There's nothing to delete.

Example 2:

Input: s = "deeedbbcccbdaa", k = 3
Output: "aa"

Explanation: 
First delete "eee" and "ccc", get "ddbbbdaa"
Then delete "bbb", get "dddaa"
Finally delete "ddd", get "aa"


Example 3:

Input: s = "pbbcggttciiippooaais", k = 2
Output: "ps"


Time complexity: O(n), where n is a string length. We process each character in the string once.

Space complexity: O(n) for the stack.

*/

public class RemoveAdjacentDuplicatesII{
        
	/* Solution: Two Pointers
   Algorithm

	1. Initialize the slow pointer (j) with zero.

	2. Move the fast pointer (i) through the string:

	   -  Copy s[i] into s[j].

	   - If s[j] is the same as s[j - 1], increment the count on the top of the stack. Otherwise, push 1 to the stack.
	   
	   -If the count equals k, decrease j by k and pop from the stack.

	3. Return j first characters of the string.
  	*/
	  
    public static String removeDuplicates(String s, int k) {
    	 int i = 0, n = s.length(), count[] = new int[n];
         char[] stack = s.toCharArray();
         for (int j = 0; j < n; ++j, ++i) {
             stack[i] = stack[j];
             count[i] = i > 0 && stack[i - 1] == stack[j] ? count[i - 1] + 1 : 1;
             if (count[i] == k) i -= k;
         }
         return new String(stack, 0, i);
	}
    
    
    public String removeDuplicates2(String s, int k) {
        StringBuilder sb = new StringBuilder();
        Deque<int[]> q = new ArrayDeque<>();
        
        for(char ch : s.toCharArray()) {
            if(q.isEmpty() || q.peekFirst()[0] != ch) {
                q.push(new int[]{ch, 1});
            } else {
                q.peekFirst()[1]++;
            }
            if(q.peekFirst()[1] == k) q.poll();
        }
        while(!q.isEmpty()) {
            int[] info = q.pollLast();
            for(int i=0; i<info[1]; ++i) {
                sb.append((char)(info[0]));
            }
        }
        return sb.toString();
    }
    
    
    public static void main(String[] args) {
    	
    	String input =  "abcd";
    	int k = 2;
    	System.out.println(removeDuplicates(input, k)); //"abcd"
    	
    	String input2 =  "deeedbbcccbdaa";
    	int k2 = 3;
    	System.out.println(removeDuplicates(input2, k2)); //aa
    }

}
	
	