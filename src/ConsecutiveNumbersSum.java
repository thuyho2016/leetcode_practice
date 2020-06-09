import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

import binarytree.TreeNode;
import binarytree.ShortestDistanceTwoNodesInBT.Node;

/* 
829. Consecutive Numbers Sum ( hard level)

Given a positive integer N, how many ways can we write it as a sum of consecutive positive integers?

Example 1:

Input: 5
Output: 2
Explanation: 5 = 5 and 5 = 2 + 3

Example 2:

Input: 9
Output: 3
Explanation: 9 = 9, 9 = 4 + 5 , and 9 = 2 + 3 + 4

Input: 15
Output: 4
Explanation: 15 = 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
1 + 2 + ... + n = n(n+1)/2

*/

public class ConsecutiveNumbersSum{
	
	 public static int consecutiveNumbersSum(int n) {
        int total = 0;
        int extra = 0;
        
        for (int i = 1; i < n + 1; i++) {
        	
            extra = extra + (i - 1); //0, 1, 
            if (extra >= n) {
                break;
            }
            int diff = n - extra;
            
            System.out.println(diff + "%" + i + " = " + diff % i);
            if (diff % i == 0) { //5 %1 = 0, 
                total++;
            }
        }
        return total;
    }
	 
	/* Best Solution:
	 * 
	 * let N = k + (k+1) + (k+2) + (k+3) + ... + (k+i-1) = i*k + (1+2+3+... + i-1)
	let sum(i) = (1+2+3+...+i-1), then we have
	 n= sum(i) + i*k --> i*k = n - sum(i)
	
	for each i, we can calculate n-sum(i). If n-sum(i) can be divided by i, there is an answer with length i.

	 */
   public static int consecutiveNumbersSum2(int n) {
	   int sum = 0, ans = 0;
	   
	    for(int i = 1; sum < n; i++) {
	        sum += i;
	        
	        int diff = n - sum;
	        
            System.out.println(diff + "%" + i + " = " + diff % i);
	        if ((diff % i) == 0)
	            ans++;
	    }
	    return ans;
    }
	 
	public static void main(String args[])
    {    
		System.out.println("consecutiveNumbersSum: " + consecutiveNumbersSum(5)); 
        
		System.out.println("consecutiveNumbersSum: " + consecutiveNumbersSum2(5)); //2
    }

}
	
	