package string;

import java.util.*;

/* 
 * 763. Partition Labels
 * https://leetcode.com/problems/partition-labels/
 * 
A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part,
and return a list of integers representing the size of these parts.

Example 1:
Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]

Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.

A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.


Time Complexity: O(N), where N is the length of S.

Space Complexity: O(N).
 */

public class PartitionLabels { 
	/*
	 * Steps:
	 * char a, where is the last index of a, char b, where is the last index of b  ( I mean last b)
	 * Go each character of S to keep track Last index of each character
	 */
	public static List<Integer> partitionLabels(String S) {
		 List<Integer> partitionLen = new ArrayList<>();
		 
		 int[] lastIndexes = new int[26];
		 
		 for (int i =0; i < S.length(); i++) {
			 // keep track current index
			 lastIndexes[S.charAt(i) - 'a'] = i  ;// a - 'a' = 0, b -'b' = 1
		 }   // lastIndexes = [8, 5, 7, 14, 15, 11, 13, 19, 22, 23, 20, 21, 0,...] 
		 
		 // want to know where is the last index of character
		 int i = 0;  // to start first character 
		 while ( i < S.length()) {
			  int end = lastIndexes[S.charAt(i) - 'a']; // find appearance of character 8
		      int j = i;  // last index of this character 
		      
			 // ask if there is a further appear
		      while (j != end) { // keep going to hit the last index of this character
		    	  end = Math.max(end, lastIndexes[S.charAt(j++) - 'a']); // next char - 'a' , 8
		      }
		      
		      partitionLen.add(j - i + 1);
		      i = j + 1;
		 }
		 return partitionLen;
	 }
 
		
    public static void main(String[] args) 
    { 
    	 String s = "ababcbacadefegdehijhklij"; 
    	 List<Integer> list = partitionLabels(s);  // [9,7,8]
    	 System.out.println(list);
    }
  
}
	
	