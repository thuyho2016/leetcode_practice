
package string;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
72. Edit Distance
https://leetcode.com/problems/edit-distance/

Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character

Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3

Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')

Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5

Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')


operations:
___________________
|replace | insert |
------------------
|delete  |I'm here|
------------------

Solution: ( subproblem)

   "" R O S
""  0 1 2 3
 H  1 1 2 3
 O  2 2 1 2 <-- min of 3 cells + 1 = 2
 R  3 2 2 2 
 S  4 3 3 2
 E	5 4 4 3
 
 Transform:
 "R" to empty "" , It means 1 deletion operation -> Number of operations = 1
 "RO" to empty "" , It means 2 deletion -> operations = 2
 ...
 Empty string "" to H. It means 1 insertion, operations = 1
 Empty string "" to HO (2 characters). It means 2 insertion, operations = 2
 
 
 ""R" to "H" . It means we do 1 replace opertation  -> operations = 1 
 
  "O" -> "O"  Match, Same distance, No operation. Then go to cell of index [i - 1][j-1] and plus + 1 
  
 Formula =  Take minimum of 3 cells ( replace, delete, insert) + 1
  
  
*/

public class EditDistance {
	
	public static int minDistance(String s1, String s2) {
		int[][] opt = new int[s1.length()][s2.length()];
		  for (int[] row: opt) {
		    Arrays.fill(row, -1);
		  }

		  return levenshteinDistance(s1, s1.length() - 1, s2, s2.length() - 1, opt);
	}

	private static int levenshteinDistance(String s1, int s1Index , String s2, int s2Index, int[][] opt) {
		  if (s1Index < 0) {
			  return s2Index + 1; // If s1 is "", it is all insertions to get s1 to s2
		  } else if (s2Index < 0) {
			  return s1Index + 1; // If s2 is "", it is all deletions to get s1 to s2
		  }
		
		  if (opt[s1Index][s2Index] != -1) {
		    return opt[s1Index][s2Index];
		  }
		
		  if (s1.charAt(s1Index) == s2.charAt(s2Index)) {  // Characters match 
		    //then no operation needs to take place, no addition to distance
		    opt[s1Index][s2Index] = levenshteinDistance(s1, s1Index - 1, s2, s2Index - 1, opt);
		  } else {
		  
			  
	   /* We have a character mismatch. Remember we want to transform s1 into s2 and
		  we hold the i'th character of s1 and the j'th character of s2:
		  
		  Deletion:
		    Find levenshteinDistance() of s1[0...(i-1)] => s2[0...j]
		    i'th character of s1 is deleted
		  Insertion:
		    Find levenshteinDistance() of s1[0...i] => s2[0...(j-1)]
		    We then insert s2[j] into s2 to regain s2[0...j]
		  
		  Substitution:
		    Find levenshteinDistance() of s1[0...(i-1)] => s2[0...(j-1)]
		    We then insert s2[j] as i'th character of s1 effectively substituting it
		*/
		int delete = levenshteinDistance(s1, s1Index - 1, s2, s2Index, opt);
		
		int insert = levenshteinDistance(s1, s1Index, s2, s2Index - 1, opt);
		
		int replace = levenshteinDistance(s1, s1Index - 1, s2, s2Index - 1, opt);  //substitute
		
		/*
		  We want to take the minimum of these 3 options to fix the problem (we add
		  1 to the min cost action to symbolize performing the operation)
		*/
		    opt[s1Index][s2Index] = 1 + Math.min(delete , Math.min(insert, replace));
		  }
		
		  return opt[s1Index][s2Index];
    }
	
	public static void main (String[] agrs) {
		
		System.out.println(minDistance("horse", "ros"));  //3
		
	}
}

	  
	