package string;

/*
 * 1041. Robot Bounded In Circle (medium level)
https://leetcode.com/problems/robot-bounded-in-circle/

On an infinite plane, a robot initially stands at (0, 0) and faces north.  The robot can receive one of three instructions:

"G": go straight 1 unit;
"L": turn 90 degrees to the left;
"R": turn 90 degress to the right.
The robot performs the instructions given in order, and repeats them forever.

Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.

 

Example 1:

Input: "GGLLGG"
Output: true
Explanation: 
The robot moves from (0,0) to (0,2), turns 180 degrees, and then returns to (0,0).
When repeating these instructions, the robot remains in the circle of radius 2 centered at the origin.

Example 2:

Input: "GG"
Output: false
Explanation: 
The robot moves north indefinitely.
Example 3:

Input: "GL"
Output: true
Explanation: 
The robot moves from (0, 0) -> (0, 1) -> (-1, 1) -> (-1, 0) -> (0, 0) -> ...


Solution:
The idea is track the robot's route, and repeat it fourth.
North - 0, East - 1, South - 2, West - 3
If it returns the origin at any time, the rebot can return to the origin.
Otherwise, return false

if change one direction at a time, you will take 4 repetitions to form a circle.
 */

public class RobotBoundedInCircle {
	
	public static boolean isRobotBounded(String instructions) {
        int x = 0, y =0; 
        int index = 0;
        
        int[][] delta = {{0,1}, {-1, 0}, {0, -1}, {1, 0}};
        
        for (char c : instructions.toCharArray()) {
        	switch(c) {
	        	case 'L':
	        		index = (index + 1) % 4; // 1, 2 
	        		break;
	        	case 'R':
	        		index = (index + 3) % 4;
	        		break;
        		default: // c = G
	        		x += delta[index][0]; // x = 0, 0, 0, 0
	        		y += delta[index][1]; // y = 1, 2, 1, 0
	        		break;
        	}
        }
        
        return x ==0 && y ==0 || index > 0;  //x =0, y = 0, index = 2
    }
	
	
	public static void main(String[] args) {
		String instructions = "GGLLGG";
		System.out.println(isRobotBounded(instructions)); //true
		
		String instructions1 = "GG";
		System.out.println(isRobotBounded(instructions1)); //false
		
		String instructions2 = "GL";
		System.out.println(isRobotBounded(instructions2)); //true
	}
}
