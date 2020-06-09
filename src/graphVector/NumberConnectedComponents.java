package graphVector;

import java.util.*;

/* 
 * 323. Number of Connected Components in an Undirected Graph
https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/

Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), 
write a function to find the number of connected components in an undirected graph.

Example 1:

Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]

     0          3
     |          |
     1 --- 2    4 

Output: 2

Example 2:

Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]

     0           4
     |           |
     1 --- 2 --- 3

Output:  1

You can assume that no duplicate edges will appear in edges. 
Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

Time Complexity: O(n)
*/

public class NumberConnectedComponents {
   
	//DFS
	List<List<Integer>> graph = new ArrayList<>();
	
	public int countComponents(int n, int[][] edges) {
        int res = 0;
        for(int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int[] anEdge : edges) {  // int[] anEdge = [0, 1]
            int a = anEdge[0]; //0 , 1, 3
            int b = anEdge[1];  //1, 2, 4
            graph.get(a).add(b);  //[[1], [], [], [], []], [[1], [0, 2], [], [], []] , [[1], [0, 2], [1], [4], []]
            graph.get(b).add(a); //[[1], [0], [], [], []], [[1], [0, 2], [1], [], []], [[1], [0, 2], [1], [4], [3]]
        }
        
        boolean[] visited = new boolean[n]; //[false, false, false, false, false]
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                dfs(i, visited);
                res++; //1, 2
            }
        }
        return res;
    }
	    
    public void dfs(int i, boolean[] visited) {
        if(visited[i]) {  // i goes from 0 to 4
            return;
        }
        visited[i] = true;  //i = 0, mark true at i = 0 [true, false, false, false, false]
        for(int edge : graph.get(i)) { //edge = 1 from graph = [[1], [0, 2], [1], [4], [3]]
            dfs(edge, visited); //graph = [true, true, false, false, false], [true, true, true, false, false]
        }
    }
    

	public static void main(String[] args) {
		int n = 5;
		int[][] edges = {{0, 1}, {1, 2}, {3, 4}};
		
		NumberConnectedComponents o = new NumberConnectedComponents();
		System.out.println(o.countComponents(n, edges)); //2
		
		int[][] edges2 = {{0, 1}, {1, 2}, {2, 3}, {3, 4}};
	
	  
	}
}
	