
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
/*
 * You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
Input: 2
Output: 2
Explanation: There are two ways to climb to the top.
 1 step + 1 step
 2 steps

Input: 3
Output: 3
Explanation: There are three ways to climb to the top.
 2 steps + 1 step
 
 1 step + 1 step + 1   // previous result + 1) 
 2 steps + 1

Input: 4
Output: 4
Explanation: There are five ways to climb to the top.
 1 step + 1 step + 2 step  
 2 step + 2 steps
  
 2 steps + 1 step  + 1     //previous result + 1 
 1 step + 1 step + 1  + 1 
 2 steps + 1 + 1
 
 
 Summary:
 stairs:0  1  2  3  4  5 
 steps: 1  1  2  3  5  8 (= 3 + 5)
  
 f(1) = 1, f(2) = 2, f(3) = f(1) + f(2) = 1 + 2 = 3
 
 so, formula f(n) = f(n-1) + f(n -2)
 
 if allow 3 steps jump , formula = f(n) = f(n-1) + f(n -2) + f(n -3)
 
 */

public class ClimbStair {
        
    public static void main(String[] args) {
   	int steps = climbStairs(3);
    	System.out.println("Steps " + steps);
    	steps = climbStairs(4);
    	System.out.println("Steps " + steps);
    	steps = climbStairs(5);
    	System.out.println("Steps " + steps);
    	
    	
    	//Allow 3 / 2/ 1 steps at a time 
    	steps = climbStairs2(3);
    	System.out.println("Stair 3 takes steps:  "  + steps);
    	steps = climbStairs2(4);
    	System.out.println("Stair 4 takes steps: " + steps);
    	steps = climbStairs2(5);
    	System.out.println("Stair 5 takes steps: " + steps);
    	steps = climbStairs2(6);
    	System.out.println("Stair 6 takes steps: " + steps);
    	steps = climbStairs2(7);
    	System.out.println("Stair 7 takes steps: " + steps);
    	steps = climbStairs2(8);
    	System.out.println("Stair 8 takes steps: " + steps);
    }
    
    //use Fibonacci  f(n) = f(n -2) + f(n-1) + 1
    public static int climbStairs(int n) {
        int steps = 0;
        
        if (n == 1) return 1;
        if (n == 2) return 2;
  
        //start 3 steps to n steps
        for (int i = 3; i <= n; i++) {
        
        	steps = climbStairs(i-1) + climbStairs(i-2) + 1 ; 
        	// if Allow jums 3 steps: 
        }
    	return steps;
    }
	
 /* if allow 3 steps jump: steps = (2 * climbStairs(i-1)) - 1; 
   //  f(n) = f(n-1) * 2 - 1
    
    -------- Allow jumping 3 steps -------------
       stairs: 0  1  2  3  4  5  6 
       steps:  1  1  2  4  7  13  25
    
     stair = 3: steps = 3  + 1 ( is 3 steps jump)
     stair = 4: steps = 4 + 2 + 1 = 7      
     stair = 5: steps = 7 + 4 + 2 = 13
      n = 6: f(6) = 13 + 7 + 5 = 25
      n = 7:  f(7) = 25 + 13 + 7 = 45
      n = 8: f(8) = 81
       
    so, formula is f(n) = f(n-1) + f(n -2) + f(n -3)
    
    */
    public static int climbStairs2(int n) {
        int steps = 0;
        
        if (n ==1) return 1;
        if (n == 2) return 2;
        if (n==3) return 4;
        //start 4 steps to n steps
        steps =  climbStairs2(n - 3) + climbStairs2(n - 2) + climbStairs2(n - 1);       	     
    	return steps;
    }
	
}
	
	