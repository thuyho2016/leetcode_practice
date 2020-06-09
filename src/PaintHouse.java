/*
 * 256. Paint House
 * https://leetcode.com/problems/paint-house/

There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. 
You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. 
For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... 
Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Example:

Input: [[17,2,17],[16,16,5],[14,3,19]]
Output: 10

Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue. 
             Minimum cost: 2 + 5 + 3 = 10.
 
Sample:  
         Red blue Green
House 1   3   2    1    ( min cost for painting for house 1 with green is 1)
House 2   16  7    6    ( min cost for painting with blue si 7, because you cannot go with green, same with house 1)
HOuse 3   4   8    9    ( house 3, you can pick either red or green, but min cost for red is 4)

so, min = 1 + 7 + 4 = 12
 */

public class PaintHouse {

	//use Dynamic programming
	public static int minCost(int[][] costs) {
	    if (costs == null || costs.length ==0) return 0;
	    
	    
	    for (int i = 1; i < costs.length; i++) { // looking backwark
	      
	      costs[i][0] += Math.min(costs[i-1][1] , costs[i-1][2]);
	      costs[i][1] += Math.min(costs[i-1][0] , costs[i-1][2]);  // for next color
	      costs[i][2] += Math.min(costs[i-1][0] , costs[i-1][1]);
	                             
	    }
	    //last row 
	    return Math.min(Math.min(costs[costs.length - 1][0], costs[costs.length - 1][1]),
	                             costs[costs.length - 1][2] );  // [14,3,19]
	  }
	  
	  public static void main(String[] args) {
	    int[][] costs = {
	                { 17, 2, 17 },
	                { 16, 16, 5 },
	                { 14, 3, 19 }};

	  
	    System.out.println(minCost(costs)); //10
	    
	  }
}
