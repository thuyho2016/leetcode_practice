import java.util.Arrays;

/*
 * 338. Counting Bits
 * https://leetcode.com/problems/counting-bits/
 * 

Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.

Example 1:

Input: 2
Output: [0,1,1]


Example 2:

Input: 5
Output: [0,1,1,2,1,2]
Follow up:

It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
Space complexity should be O(n).
Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.

DP transition relation is counts[i] is 1 + counts[remove the highest set bit of i]
By use a mask which is 2^n and increase it along the way, we can achieve this.

 */

public class CountingBits 
{
	public static int[]  countBits(int num) {
		  if (num < 0) {
              return new int[0];
          }
		  
          int[] counts = new int[num + 1];  //[0, 0, 0, 0, 0, 0], size = 6
          int mask = 1;
          
          for (int i = 1; i <= num; i++) {
              if (i == mask) {   //i = 1,2 4
                  counts[i] = 1;  //[0, 1, 0, 0, 0, 0], [0, 1, 1, 0, 0, 0], [0, 1, 1, 2, 1, 0]
                  mask <<= 1;    // mask = 2, 4
              } else {    // i = 3
                  counts[i] = counts[i ^ (mask >> 1)] + 1; //[0, 1, 1, 2, 0, 0], [0, 1, 1, 2, 1, 2]
              }
          }
          return counts;
	}
	
	public static void main(String[] args)
	{
		System.out.println(Arrays.toString(countBits(5)));
	}
}
