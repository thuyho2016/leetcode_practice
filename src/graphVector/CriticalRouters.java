
package graphVector;

import java.util.*;
import java.util.stream.Collectors;

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


https://www.geeksforgeeks.org/bridge-in-a-graph/


Related problems:

Leetcode 1192. Critical Connections 
https://www.geeksforgeeks.org/articulation-points-or-cut-vertices-in-a-graph/
https://cp-algorithms.com/graph/cutpoints.html





//A simple brute force solution, for every vertex, remove the corresponding edges and perform dfs. 
//If total vertices traversed in dfs != numNodes - 1, then the current removed vertex is a critical router.
 */

public class CriticalRouters {
    
	static int time = 0;

	public static void main(String[] args) {
		int numNodes = 7;
		int numEdges = 7;
		int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {2, 3}, {2, 5}, {5, 6}, {3, 4}};
		//int[][] edges = {{1, 2}, {1, 3}, {2,4}, {3, 4}, {3, 6}, {6, 7}, {4, 5}};
		System.out.println(getCriticalNodes(edges, numEdges, numNodes)); // [2, 3, 5]
		
		
		
	 //int[][] edges2 = {{1, 2}, {1, 3}, {2,4}, {3, 4}, {3, 6}, {6, 7}, {4, 5}};
	  ArrayList<Integer> l1 = new ArrayList<Integer>();
	  l1.add(1);
	  l1.add(2);
	  ArrayList<Integer> l2 = new ArrayList<Integer>();
	  l2.add(1);
	  l2.add(3);
	  ArrayList<Integer> l3 = new ArrayList<Integer>();
	  l3.add(2);
	  l3.add(4);
	  ArrayList<Integer> l4 = new ArrayList<Integer>();
	  l4.add(3);
	  l4.add(4);
	  ArrayList<Integer> l5 = new ArrayList<Integer>();
	  l5.add(3);
	  l5.add(6);
	  ArrayList<Integer> l6 = new ArrayList<Integer>();
	  l6.add(6);
	  l6.add(7);
	  ArrayList<Integer> l7 = new ArrayList<Integer>();
	  l7.add(4);
	  l7.add(5);

	  
	  ArrayList<ArrayList<Integer>> input = new ArrayList<>();
	  input.add(l1);
	  input.add(l2);
	  input.add(l3);
	  input.add(l4);
	  input.add(l6);
	  input.add(l7);
	  
	//  System.out.println(criticalRouters(7, 7, input)); //- wrong output, expect [3, 4, 6]
	  
	  int[][] edges2 = {{1, 2}, {1, 3}, {2,4}, {3, 4}, {3, 6}, {6, 7}, {4, 5}};
	  System.out.println(getCriticalNodes3( 7 ,7, edges2)); 	  
	  
	}

	
	public static List<Integer> getCriticalNodes3(int v, int e, int [][]eg) {
	    List<Integer> ans = new LinkedList<>();
	    int[]id = new int[v];
	    Arrays.fill(id, -1);
	    
	    int[]low = new int[v];
	    int[]ar = new int[v];
	    
	    List<Integer>[] graph = new List[v];
	    for (int i = 0; i < v; i++) 
	    	graph[i] = new ArrayList<>();
	    
	    for (int edg[] : eg) {
	        int x = edg[0];
	        int y = edg[1];
	        graph[x].add(y);
	        graph[y].add(x);
	    }
	    for (int i = 0; i < v; i++) {
	        if (id[i] == -1) {
	            count = 0;
	            dfs(i, i, -1, id, low, graph, ar);
	            if (count > 1) ar[i] = 1;
	            else ar[i] = 0;
	        }
	        if (ar[i] == 1) ans.add(i);
	    }
	    return ans;
	}
	static int ii = 0;
	static int count;
	
	public static void dfs(int root, int at, int parent, int[] id, int[] low, List<Integer>[]graph, int[] ans) {
	    if (root == parent) count++;
	    
	    id[at] = ii;
	    low[at] = ii;
	    ii++;
	    for (int to : graph[at]) {
	        if (to == parent) continue;
	        
	        if (id[to] == -1) {
	            dfs(root, to, at, id, low, graph, ans);
	            low[at] = Math.min(low[at], low[to]);
	            if (id[at] <= low[to]) {
	                ans[at] = 1;
	            }
	        }
	        else {
	            low[at] = Math.min(low[at], id[to]);
	        }
	    }
	}
	
	//Amazon Test - ArrayList<ArrayList<Integer>> - wrong output
	public static List<Integer> criticalRouters(int numRouters, int numLinks, ArrayList<ArrayList<Integer>> links) {
		
		 ArrayList<Integer> result = new ArrayList<>();
	    
		 //convert ArrayList of ArrayList Integer to 2D array
		 int[][] array = new int[links.size()][links.get(0).size()];
		 
		 for(int i=0; i < links.size(); i++){
			for (int j = 0; j < links.get(i).size(); j++) {
				array[i][j] = links.get(i).get(j);
			}
		 }
		 System.out.println("ArrayListof arraylist " + Arrays.deepToString(array)); //[[1, 2], [1, 3], [2, 4], [3, 4], [6, 7], [4, 5]]	       
		 
	     for(int i = 0; i < array.length; i++){
	    	 
	         if(!helper(array, i ,  numRouters)){
	             result.add(i);
	         }
	     }
	     
	     return result;
	 }
 
	// int[][]
	 public static List<Integer> getCriticalNodes(int[][] edges, int numEdges, int numNodes) {
		 ArrayList<Integer> list = new ArrayList<>();
	     
	     for(int i = 0; i < edges.length; i++){
	         //i is the edge I'm ignoring
	         if(!helper(edges, i,  numNodes)){
	             list.add(i);
	         }
	     }
	     
	     return list;
	 }
	 
	 public static boolean helper(int[][] links, int vertice, int numRouters){
		 HashSet<Integer> set = new HashSet<Integer>();
		 boolean flag = false; // use the flag to track if links already add to hashSet or not
		     
	     // Get neighbors
	     for(int i=0; i < links.length; i++){
	         //if links that have vertice , don't do anything
	         if(links[i][0] ==  vertice || links[i][1] ==  vertice)
	             continue;
	         
	         //add links to hashSet and set flag is true
	         if(!flag){
	             set.add(links[i][0]);
	             set.add(links[i][1]);
	             flag = true;
	         }
	         
	         //if the next link has one of links in the hashSet, then visit it
	         if(set.contains(links[i][0]) || set.contains(links[i][1])){
	             set.add(links[i][0]);
	             set.add(links[i][1]);
	         }
	     }
	     //total vertices traversed in dfs == numNodes - 1, 
		 return set.size() == numRouters - 1;
	 }

}
	