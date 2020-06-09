package binarytree;

import java.util.HashMap;
import java.util.List;

/*
 * There is a brick wall in front of you. The wall is rectangular and has several rows of bricks. The bricks have the same height but different width. You want to draw a vertical line from the top to the bottom and cross the least bricks.

The brick wall is represented by a list of rows. Each row is a list of integers representing the width of each brick in this row from left to right.

If your line go through the edge of a brick, then the brick is not considered as crossed. You need to find out how to draw the line to cross the least bricks and return the number of crossed bricks.

You cannot draw a line just along one of the two vertical edges of the wall, in which case the line will obviously cross no bricks.

 

Example:

Input: [[1,2,2,1],
        [3,1,2],
        [1,3,2],
        [2,4],
        [3,1,2],
        [1,3,1,1]]

Output: 2

Explanation: 

Solution:

use of a HashMap  which is used to store entries in the form: (sum, count). Here, sum refers to the cumulative sum of the bricks' widths encountered in the current row, 
and count refers to the number of times the corresponding sum is obtained. Thus, sum in a way, represents the positions of the bricks's boundaries relative to the leftmost boundary.

We traverse over every row of the given wall. For every brick considered, we find the sum corresponding to the sum of the bricks' widths encountered so far in the current row. 
  If this sum's entry doesn't exist in the map, we create a corresponding entry with an initial count of 1. 
  If the sum already exists as a key, we increment its corresponding count value.

We will never obtain the same value of sum twice while traversing over a particular row. 
Thus, if the sum value is repeated while traversing over the rows, it means some row's brick boundary coincides with some previous row's brick boundary. 
This fact is accounted for by incrementing the corresponding count value.

But, for every row, we consider the sum only upto the second last brick, since the last boundary isn't a valid boundary for the solution.
At the end, we can obtain the maximum count value to determine the minimum number of bricks that need to be cut to draw a vertical line through them.


Time complexity : O(n). We traverse over the complete bricks only once. n is the total number of bricks in a wall.

Space complexity : O(m). map will contain atmost m entries, where m refers to the width of the wall.

 */
public class BrickWall {
	
	public int leastBricks(List < List < Integer >> wall) {
        HashMap< Integer, Integer > map = new HashMap <> ();
        
        for (List < Integer > row: wall) {
            int sum = 0;
            
            for (int i = 0; i < row.size() - 1; i++) {
                sum += row.get(i);
                
                if (map.containsKey(sum))
                    map.put(sum, map.get(sum) + 1);
                else
                    map.put(sum, 1);
            }
        }
        
        int res = wall.size();
        
        for (int key: map.keySet())
            res = Math.min(res, wall.size() - map.get(key));
        
        return res;
    }
	
}
