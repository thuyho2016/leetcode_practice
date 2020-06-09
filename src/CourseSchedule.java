import java.util.ArrayList;
import java.util.List;

/*
 * 207. Course Schedule
 * https://leetcode.com/problems/course-schedule/
 * 

There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example 1:

Input: 2, [[1,0]] 
Output: true
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0. So it is possible.

Example 2:

Input: 2, [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.


https://leetcode.com/problems/course-schedule/discuss/58827/Java-BSF-solution
 */



public class CourseSchedule 
{
	public static boolean canFinish(int numCourses, int[][] prerequisites) {
		if(numCourses == 0 || prerequisites == null) return false;
        boolean[] taken = new boolean[numCourses];
        boolean[] visited = new boolean[numCourses];
        
        List<List<Integer>> prereqs = new ArrayList<List<Integer>>();
        for(int i = 0; i < numCourses; i++) {
            prereqs.add(new ArrayList<Integer>());
        }
        
        for(int[] p: prerequisites) {
            prereqs.get(p[0]).add(p[1]);
        }
        
        for(int i = 0; i < numCourses; i++) {
            if(prereqs.get(i).size() == 0) taken[i] = true;
        }

        for(int i = 0; i < numCourses; i++) {
            if(taken[i]) continue;
            if(dfs(prereqs, i, taken, visited) == false) return false;
        }
        
        return true;
	}
	
	private static boolean dfs(List<List<Integer>> prereqs, int course, boolean[] taken, boolean[] visited) {
        if(taken[course]) return true;
        if(visited[course]) return false;
        
        visited[course] = true;
        for(int req: prereqs.get(course)) {
            if(dfs(prereqs, req, taken, visited) == false) return false;
        }
		
        visited[course] = false;
        taken[course] = true;
        return true;
    }
	
	public static void main(String[] args)
	{
		int[][] pre = {{1,0}};
		System.out.println(canFinish(2, pre)); //true
		
		int[][] prerequisites = {{1,0},{0,1}};
		System.out.println(canFinish(2, prerequisites)); //false
	}
}
