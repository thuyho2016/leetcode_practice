
package graphVector;

import java.util.*;

/* 
 * 1192. Critical Connections in a Network
https://leetcode.com/problems/critical-connections-in-a-network/

There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network where connections[i] = [a, b] represents a connection between servers a and b.
 Any server can reach any other server directly or indirectly through the network.

A critical connection is a connection that, if removed, will make some server unable to reach some other server.

Return all critical connections in the network in any order.

  			1--2 
critical    | \|
  			3  0
  		
  
Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
Output: [[1,3]]

Explanation: [[3,1]] is also accepted.

The essential point of the problem is to find circles. All edges within a circle are not critical.


Solutions:
 _______
 |		|
 0--1---2---3
    |_______|
    
In the main function:

1. build the map
2. do one time dfs (cause all node is connected, one time dfs is enough)

In DFS:
we record the depth, low, and parent info:

1. depth: d[0] = 0, d[1] = 1, d[2] = 2, d[3] = 3
2. low: low[0] = 0, low[1] = 1, low[2] = 0, low[0] = 1

   low means the lowest node (node with lowest depth) it connected. (parent node excluded)

3. parent: p[1] = 0, p[2] = 1, p[3] =2 (this parent array is used for not updating the current node low val with parent node)

   if(cur != parent[pre]) 
      low[pre] = Math.min(low[pre], depth[cur]);


**Add connect only if: **

low[cur] > depth[pre] (connection path is [pre, cur]
if (low[cur] <= depth[pre]) , it means there are other way connect node_cur and node_pre.

Check the example: low[3] = 1, means the lowest depth node 3 connect is 1. means that 3 can always go to 2 by stepping to node 1 first.

In addition, if we ignore depth array, use low array merely, you will find out that connection 2-3 is critical. (low[3] = 1 < 0 = low[2],
Thus we have to have a depth array to record the absolute depth.

Complexity:
Time = O(graph) + O(DFS) = O(|E| + |V|) + O(|E| + |V|) = O(|E| + |V|)
Space = O(graph) + O(visitedAt) + O(DFS) = O(|E| + |V|) + O(|V|) + O(|V|) = O(|V| + |E|)

 */
public class CriticalConnectionsInNetwork {
    
	boolean[] visited;
    List<List<Integer>> map = new ArrayList<List<Integer>>();
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    int[] depth, low, parent;
    int time = 0;
    
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
      
        visited = new boolean[n];
        for (int i=0; i < n; i++) 
        	map.add(new ArrayList<>());

        // build graph using map
        for (List<Integer> con: connections) {
            int a1 = con.get(0);
            int a2 = con.get(1);
            
            map.get(a1).add(a2);
            map.get(a2).add(a1);
        }
        
        depth = new int[n];
        low = new int[n];
        parent = new int[n];
        
        dfs(0);
        return result;
    }

    private void dfs(int pre) {
        visited[pre] = true;
        
        depth[pre] += ++time;
        low[pre] += time;
        
        for (int cur : map.get(pre)) {
            parent[cur] = pre;
            
            if (!visited[cur]) {
                dfs(cur);
                
                low[pre] = Math.min(low[pre], low[cur]);
                
                if (low[cur] > depth[pre]) {
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(pre);
                    tmp.add(cur);
                    result.add(tmp);
                }
            } else {
                if (cur != parent[pre]) {
                    low[pre] = Math.min(low[pre], depth[cur]);
                }
            }
        }
    }
	 
    
	public static void main(String args[])
    {
		//Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
		List<List<Integer>> connections = new ArrayList<List<Integer>>();
		List<Integer> l1 = Arrays.asList(0,1);
		List<Integer> l2 = Arrays.asList(1,2);
		List<Integer> l3 = Arrays.asList(2,0);
		List<Integer> l4 = Arrays.asList(1,3);
		
		connections.add(l1);
		connections.add(l2);
		connections.add(l3);
		connections.add(l4);
		
		CriticalConnectionsInNetwork o = new CriticalConnectionsInNetwork();
		System.out.println(o.criticalConnections(4, connections)); //[[1,3]]
		
		//Input: n = 4, connections = {{1, 2}, {1, 3}, {2,4}, {3, 4}, {3, 6}, {6, 7}, {4, 5}}
		List<List<Integer>> connections2 = new ArrayList<List<Integer>>();
		List<Integer> n1 = Arrays.asList(1,2);
		List<Integer> n2 = Arrays.asList(1,3);
		List<Integer> n3 = Arrays.asList(2,4);
		List<Integer> n4 = Arrays.asList(3,4);
		List<Integer> n5 = Arrays.asList(3,6);
		List<Integer> n6 = Arrays.asList(6,7);
		List<Integer> n7 = Arrays.asList(4,5);
		
		connections2.add(n1);
		connections2.add(n2);
		connections2.add(n3);
		connections2.add(n4);
		connections2.add(n5);
		connections2.add(n6);
		connections2.add(n7);
	
		System.out.println(o.criticalConnections(7, connections2)); //exception [[3,4,6]]
		
    }
	
}
	