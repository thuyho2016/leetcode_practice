
package graphVector;

import java.util.*;

/* Amazon Online https://leetcode.com/discuss/interview-question/436073/
 * 
You are given an undirected connected graph. An articulation point (or cut vertex) is defined as a vertex which, when removed along with associated edges, 
makes the graph disconnected (or more precisely, increases the number of connected components in the graph). 
The task is to find all articulation points in the given graph.

Input:
The input to the function/method consists of three arguments:

	numNodes, an integer representing the number of nodes in the graph.
	numEdges, an integer representing the number of edges in the graph.
	edges, the list of pair of integers - A, B representing an edge between the nodes A and B.

Output:
Return a list of integers representing the critical nodes.

Example:

Input: numNodes = 7, numEdges = 7, edges = [[0, 1], [0, 2], [1, 3], [2, 3], [2, 5], [5, 6], [3, 4]]
			 4
			/ 
           3
         /  \
        1    2
         \  / \
          0    5
               /
              6

Output: [2, 3, 5]


Related problems:  https://www.geeksforgeeks.org/bridge-in-a-graph/

Leetcode 1192. Critical Connections 
https://www.geeksforgeeks.org/articulation-points-or-cut-vertices-in-a-graph/
https://cp-algorithms.com/graph/cutpoints.html

How to find all articulation points in a given graph?
A simple approach is to one by one remove all vertices and see if removal of a vertex causes disconnected graph. Following are steps of simple approach for connected graph.

1) For every vertex v, I do in the following
a) Remove v from graph
b) See if the graph remains connected (We can either use BFS or DFS)
c) Add v back to the graph

 */

public class CriticalRouters2 {
	static int time = 0;
	
	private static List<Integer> getCriticalRouters(int[][] links, int numLinks, int numRouters) {
		time = 0;
		Map<Integer, Set<Integer>> map = new HashMap<>();
		
		for(int i = 0;i < numRouters;i++) {
			map.put(i, new HashSet<>());
		}
		
		for(int[] link : links) {
			map.get(link[0]).add(link[1]);
			map.get(link[1]).add(link[0]);
		}
		
		
		Set<Integer> set = new HashSet<>();
		int[] low = new int[numRouters];
		int[] ids = new int[numRouters];
		int parent[] = new int[numRouters]; 
		
		Arrays.fill(ids, -1);
		Arrays.fill(parent, -1);
		
		for(int i=0; i < numRouters;i++) {
			if(ids[i] == -1)
				dfs(map, low, ids, parent, i, set);
		}
		return new ArrayList<>(set);
	}



	private static void dfs(Map<Integer, Set<Integer>> map, int[] low, int[] ids, int[] parent, int cur, Set<Integer> res) {
		int children = 0; 
		ids[cur] = low[cur]= ++time;
		
		for(int nei : map.get(cur)) {
			
			if(ids[nei] == -1) {
				children++;
				parent[nei] = cur;
				
				dfs(map, low, ids, parent, nei, res);
				
				low[cur] = Math.min(low[cur], low[nei]);
				
				if((parent[cur] == -1 && children > 1) || (parent[cur] != -1 && low[nei] >= ids[cur]))
					res.add(cur);
			}
			else if(nei != parent[cur])
				low[cur] = Math.min(low[cur], ids[nei]);
		}
	}
	
	public static void main(String[] args) {

		int numRouters = 7;
		int numLinks = 7;
		int[][] links = {{0, 1}, {0, 2}, {1, 3}, {2, 3}, {2, 5}, {5, 6}, {3, 4}};
		System.out.println(getCriticalRouters(links, numLinks, numRouters));  //[2, 3, 5]

	    int numRouters2 = 5;
	    int numLinks2 = 5;
	    int[][] links2 = {{1,2}, {0,1}, {2,0}, {0,3}, {3,4}};
	    System.out.println(getCriticalRouters(links2, numLinks2, numRouters2));  //[0, 3]

	    int numRouters3 = 4;
	    int numLinks3 = 4;
	    int[][] links3 = {{0,1},{1,2},{2,3}};
	    System.out.println(getCriticalRouters(links3, numLinks3, numRouters3));  //[1, 2]

	    int numRouters4 = 7;
	    int numLinks4 = 8;
	    int[][] links4 = {{0,1},{0,2},{1,2},{1,3},{1,4},{1,6},{3,5},{4,5}};
	    System.out.println(getCriticalRouters(links4, numLinks4, numRouters4)); //[1]

	    int numRouters1 = 7;
	    int numLinks1 = 7;
	    int[][] links1 = {{1, 2}, {1, 3}, {2,4}, {3, 4}, {3, 6}, {6, 7}, {4, 5}};
	    System.out.println(getCriticalRouters(links1, numLinks1, numRouters1)); // - wrong output, throw exception

	}
}
	