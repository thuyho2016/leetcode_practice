
package matrix;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/*
 * 1102. Path With Maximum Minimum Value
https://leetcode.com/problems/path-with-maximum-minimum-value/
 
Given a matrix of integers A with R rows and C columns, find the maximum score of a path starting at [0,0] and ending at [R-1,C-1].

The score of a path is the minimum value in that path.  For example, the value of the path 8 →  4 →  5 →  9 is 4.

A path moves some number of times from one visited cell to any neighbouring unvisited cell in one of the 4 cardinal directions (north, east, west, south).

 

Example 1:
Input: [[5,4,5],[1,2,6],[7,4,6]]
Output: 4
Explanation: 
The path with the maximum score is highlighted in yellow. 

Example 2:
Input: [[2,2,1,2,2,2],[1,2,2,2,1,2]]
Output: 2

Example 3:
Input: [[3,4,6,3,4],[0,2,1,1,7],[8,8,3,2,7],[3,2,4,9,8],[4,1,2,0,0],[4,6,5,4,3]]
Output: 3


 we are using Max heap/ priority queue. This will ensure that we always pick up next node in queue with maximum smallest value. 
 So queue will still have all possible values from a cell, but we only pick node with maximum value amongst them.
 */

public class MaximumMinimumPath {

	//Basic Idea is using PriorityQueue to BFS. Every time we poll the greatest point that we can reach, so that we can gurantee that the minimum score of the path to this point is fixed. 
	//No need to update the value later. Use visited to record the point we traversed.
	public static int maximumMinimumPath(int[][] A) {
		
        
        int n = A.length;
        int m = A[0].length;
        boolean[][] visited = new boolean[n][m];
      
       
     // we are interested in getting the maximum min that we have seen so far, thus we reverse the ordering in the pq
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (b[2] - a[2]));  // maxHeap
        pq.add(new int[]{0, 0, A[0][0]});        

        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int row = cur[0];
            int col = cur[1];
            
            if (row == n - 1 && col == m - 1) {
              return cur[2];
            }
            
            visited[row][col] = true;
            
            for (int[] dir : dirs) {
                int nx = cur[0] + dir[0];
                int ny = cur[1] + dir[1];
                
                if (nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny]) {
                    continue;
                }
                
                // we are keeping track of the min element that we have seen until now
                pq.add(new int[]{nx, ny,  Math.min(cur[2], A[nx][ny]) });
            }
        }
        return -1;
    }
	
	
	
	public static void main (String[] agrs) {
		int[][] grid = {
				{5,4,5},
		 		{1,2,6},
		 		{7,4,6}};
		
		System.out.println(maximumMinimumPath(grid));//4
		
		
	}
}
