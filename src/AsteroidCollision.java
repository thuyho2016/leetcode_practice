import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/* 735. Asteroid Collision
 We are given an array asteroids of integers representing asteroids in a row.

For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left).
Each asteroid moves at the same speed.

Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. 
If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.

Example 1:
Input: asteroids = [5, 10, -5]
Output: [5, 10]
Explanation: 
The 10 and -5 collide resulting in 10.  The 5 and 10 never collide.

Example 2:
Input: asteroids = [8, -8]
Output: []
Explanation: 
The 8 and -8 collide exploding each other.

Example 3:
Input: asteroids = [10, 2, -5]
Output: [10]
Explanation: 
The 2 and -5 collide resulting in -5.  The 10 and -5 collide resulting in 10.

Example 4:
Input: asteroids = [-2, -1, 1, 2]
Output: [-2, -1, 1, 2]
Explanation: 
The -2 and -1 are moving left, while the 1 and 2 are moving right.

Asteroids moving the same direction never meet, so no asteroids will meet each other.
 */

public class AsteroidCollision{
	
	public static int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<Integer>();
        for (int asteroid : asteroids) {
            if (asteroid > 0) {          //push only positive number 
                stack.push(asteroid);  // stack = [5,10]
                continue;
            }
            
            boolean survived = true;
            while (!stack.isEmpty() && stack.peek()>0) {
            	
                if (stack.peek() < Math.abs(asteroid)) { //10 < Math.abs(-5) ? no
                    stack.pop();
                    continue;
                }
                else if (stack.peek() == Math.abs(asteroid)) {
                    stack.pop();
                } 
                else { //stack.peek() < Math.abs(asteroid)   10 < Math.abs (-5) yes           
                	survived = false;
                	break;
                }
            }
            if (survived) {
                stack.push(asteroid);
            }
        }
        
        int[] res = new int[stack.size()];
        
        for (int i = stack.size() - 1; i >= 0; i--) {
            res[i] = stack.pop();
            System.out.println("res: " + res[i]);
        }
        return res;
    }

	public static int[] asteroidCollision2(int[] asteroids) {
		Stack<Integer> stack = new Stack<Integer>();
	  
		asteroids:
	    for (int ast: asteroids) {
	        while (!stack.isEmpty() && ast < 0 && 0 < stack.peek()) //ast = -5
	            if (-ast < stack.peek() || -ast == stack.pop()) //
	                continue asteroids;
	        stack.push(ast); //[5,10]
	    }
	    
	    int[] ans = new int[stack.size()];
	    for (int i = ans.length -1; i >=0; i--) { //[5, 10]
	    	ans[i] = stack.pop(); 
	    	System.out.print(ans[i] + " ");
	    }
	    return ans;
	    //return stack.stream().mapToInt(i->i).toArray();
	}
	
	
	public static void main(String[] args) {
	   int[] nums = { 5, 10, -5 };
	   
	   int[] res = asteroidCollision(nums);
	/*   for (int i = 0; i < res.length; i++) { 
		   System.out.println("res: " + res[i]);//[5, 10] 
	   }
	*/   
	   int[] res2 = asteroidCollision2(nums);
	
	}

}
	
	