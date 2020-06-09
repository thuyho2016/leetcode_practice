/* 887. Super Egg Drop ( hard level)
 * https://leetcode.com/problems/super-egg-drop/

You are given K eggs, and you have access to a building with N floors from 1 to N. 

Each egg is identical in function, and if an egg breaks, you cannot drop it again.

You know that there exists a floor F with 0 <= F <= N such that any egg dropped at a floor higher than F will break, and any egg dropped at or below floor F will not break.

Each move, you may take an egg (if you have an unbroken one) and drop it from any floor X (with 1 <= X <= N). 

Your goal is to know with certainty what the value of F is.

What is the minimum number of moves that you need to know with certainty what F is, regardless of the initial value of F?

 

Example 1:

Input: K = 1, N = 2
Output: 2
Explanation: 
Drop the egg from floor 1.  If it breaks, we know with certainty that F = 0.
Otherwise, drop the egg from floor 2.  If it breaks, we know with certainty that F = 1.
If it didn't break, then we know with certainty F = 2.
Hence, we needed 2 moves in the worst case to know what F is with certainty.
Example 2:

Input: K = 2, N = 6
Output: 3
Example 3:

Input: K = 3, N = 14
Output: 4


 * https://github.com/bephrem1/backtobackswe/blob/master/Dynamic%20Programming,%20Recursion,%20&%20Backtracking/EggDrop/EggDrop.java
 * 
 * //Solution - https://www.geeksforgeeks.org/egg-dropping-puzzle-dp-11/
 * 
 *  we could DP with state (num_of_eggs, num_of_trials) and the value of the DP state is the max floors we can figure out with this num of eggs and trials.
 *  
 */

public class EggDrop {	
	
	/* Function to get minimum number of trials needed in worst case with n eggs and k floors */
    static int eggDrop(int n, int k)  
    {  
        // If there are no floors, then no trials needed. 
    	//OR if there is one floor, one trial needed.  
        if (k == 1 || k == 0)  
            return k;  
      
        // We need k trials for one egg and k floors  
        if (n == 1)  
            return k;  
      
        int min = Integer.MAX_VALUE;  
        int x, res;  
      
        // Consider all droppings from 1st floor to kth floor and  
        // return the minimum of these values plus 1.  
        for (x = 1; x <= k; x++)  
        {  
            res = Math.max(eggDrop(n-1, x-1), eggDrop(n, k - x));  
            if (res < min)  
                min = res;  
        }  
      
        return min + 1;  
    }  
      
    // Driver code  
    public static void main(String args[])  
    {  
        int n = 2, k = 10;  
        System.out.print("Minimum number of trials in worst case with "
                + n + " eggs and " + k  + " floors is " + eggDrop(n, k));  
    } 
}
