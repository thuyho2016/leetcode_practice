/*
 * What is DynamicProgramming?
 * Dynamic Programming is a method for solving a complex problem by breaking it down into a collection of subproblems, 
 * solving each of those subproblems just once, and storing their solutions using a memory-based data structure (array, map,etc
 * 
 * The idea is to simply store the results of subproblems, so that we do not have to re-compute them when needed later.
 * This technique of storing solutions to subproblems instead of recomputing them is called memoization.
 * 
 * Wherever we see a recursive solution that has repeated calls for same inputs, we can optimize it using Dynamic Programming.
 * 
 * 3 Solutions:
 * 1. Recursion
 * 2. Memoization ( Store)
 * 3. Bottom-up
 * 
 * Example: 1, 1, 2, 3, 5, 8
 * 1st Number is 1 + 2nd Number is 2  = 2
 *  f(5)  = f(4) + f(3) = 3 + 2 + 5
 *  
 * 1. Recursive:
 *  n == 1 || n == 2 return 1
 *  
 *  return f(n - 1) + f(n -2)
 *  
 *  Time Complexity: 0(n)
 *  
 *  3. Dynamic Programming:
 *  Bottom_up:
 *  bottom_up[1] = 1
 *  bottom_up[2] = 1
 *  Start from 3 to n
 *  for (int i = 3; i <=n ; i++)
 *     bottom_up[i] = bottom_up[i-1] + bottom_up[i-2]; // b[3] = b[3-1] + b[3-2] = b[2] + b[1] = 1 + 1 = 2
 *      												  b[4] = b[4 - 1] + b[4-2] = b[3] + b[2] = 2 + 1 = 3		
 *  return bottom_up[n]   
 */


public class DynamicProgramming_fibonacci {
	
	public static int fib(int n) {
		if ( n == 1) return 1;
		
		// declare an array to store Fibonacci numbers
	    int f[] = new int[n+2]; // 1 extra to handle case, n = 0 
		
		/* 0th and 1st number of the series are 0 and 1*/
		f[0] = 0;
		f[1] = 1;
		
		//start from 2
		for (int i = 2; i <= n; i++) {
			f[i] = f[i-1] + f[i-2];
		}
		
		return f[n];
	}
	
	//Bottom-Up Approach using Memoization
	// Time and Space complexity : O(N).
	public int fib2(int N) {
        if (N <= 1) {
            return N;
        }
        return memoize(N);
    }

    public int memoize(int N) {
      int[] cache = new int[N + 1];
      cache[1] = 1;

      for (int i = 2; i <= N; i++) {
          cache[i] = cache[i-1] + cache[i-2];
      }
      return cache[N];
    }
    
    
	public static void main (String args[]) 
    { 
        int n = 9; 
        System.out.println(fib(n)); 
    } 
}
