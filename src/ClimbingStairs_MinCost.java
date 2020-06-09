
/* 
 On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).

Our goal is to reach the top stair. And at each step we can take 1 or 2 steps. 
We need to find the minimum steps required to reach the top.

The final cost f[i] to climb the staircase from some step i is  f[i] = cost[i] + min(f[i+1], f[i+2]).

Steps:
f[1] = cost[0], f[2] = cost[1] and get current_cost = cost[i] + Math.min(f1, f2); 
update f1 and f2, 
return Math.min(f1, f2)   //return the min of last step or second last step.


Input: cost = [10, 15, 20]
Output: 15
Explanation: Cheapest is start on cost[1]. //find index of 15 = 1

*/

public class ClimbingStairs_MinCost {
   
	public static int minCostClimbingStairs(int[] cost) { 
		
		int f1 = 0, f2 =0;
		
		if(cost.length == 1) return 0;
		
		for (int i = cost.length - 1; i >= 0; i--) {
			int f0 = cost[i] + Math.min(f1,  f2);
			f2 = f1;
			f1 = f0;
		}
    	return Math.min(f1, f2); // Return the minimum of the cost when taken one step or two steps  from the 0th step.
    }
	
	
    public static void main (String args[]) {
	    int[] cost = {2, 7, 11, 15};  //Output: 15 
    	int output = minCostClimbingStairs(cost);
    	System.out.println(output);
    	
    	int[] cost2 = {10, 15, 20};  //Output: 15 
     	int output2 = minCostClimbingStairs(cost2);
     	System.out.println(output2);
    }
}


