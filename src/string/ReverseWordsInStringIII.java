package string;

import java.util.HashSet;
import java.util.Set;

/*
557. Reverse Words in a String III ( easy )
 * https://leetcode.com/problems/reverse-words-in-a-string/
Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.

Example 1:
Input: "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"

Note: In the string, each word is separated by single space and there will not be any extra space in the string.

Time complexity : O(n). where nn is the length of the string.
Space complexity : O(n). resres of size nn is used.
*/

public class ReverseWordsInStringIII  {
	
	public static void main (String[] args)
    {
		String s = "Let's take LeetCode contest";
		System.out.println("Reversed: " + reverseWords(s));		
		
    }

	//two pointers approach
	public static String reverseWords(String s) {
        int i = 0, j = 0;
        int n = s.length();
        
        char[] ary = s.toCharArray();
        
        while (i < n && j < n) {
            while (i < n && ary[i] == ' ') {
                i++;
            }
            j = i;
            while (j < n && ary[j] != ' ') {   // increase j index until it reaches to space
                j++;
            }
            
            swap(ary, i, j - 1); // when j == ' ', then swap char at j - 1 and i . next shift i to j index ( j = 5, so i is updated from 0 to 5)
            i = j;
        }
        return new String(ary);
    }
    
    private static void swap(char[] chs, int s, int t) {
        while (s < t) {
            char tmp = chs[s];
            chs[s] = chs[t];
            chs[t] = tmp;
            s++;
            t--;
        }
    }
	
    
}