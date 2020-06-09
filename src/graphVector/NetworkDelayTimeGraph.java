package graphVector;

import java.util.*;

/* 
 * 743. Network Delay Time
 * https://leetcode.com/problems/network-delay-time/
 * 
 * There are N network nodes, labelled 1 to N.

Given times, a list of travel times as directed edges times[i] = (u, v, w), 
where u is the source node, 
v is the target node, 
and w is the time it takes for a signal to travel from source to target.

Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? 
If it is impossible, return -1.

                   2
             (1) /    \(1)
                1       3
                      / (1)
                     4

Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
Output: 2

 */
public class NetworkDelayTimeGraph {
    
	/** Dijkstra's algorith - Entry from node 2
	 * Distance(int): { 1 , 0 , 1 , 2}
	          node      1 , 2 , 3 , 4
	 
	 boolean visited: { , T,  T ,		}
	                   1 , 2 , 3 , 4
	 */
	public static int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap();
        
        for (int[] edge : times) {
        	if ((!graph.containsKey(edge[0]))) {
        		graph.put(edge[0], new ArrayList<int[]>());
        	}
        	graph.get(edge[0]).add(new int[] { edge[1], edge[2]});
        	
        }
        
        Map<Integer, Integer> dist = new HashMap();
        
        for (int node =1; node <= N; node++) dist.put(node, Integer.MAX_VALUE);
		dist.put(K,  0);
		
		boolean[] visited = new boolean[N+1];
		
		while (true) {
			int kNode = -1;
			int cDist = Integer.MAX_VALUE;
			
			for (int i = 1; i <= N; i++) {
				if (!visited[i] && dist.get(i) < cDist) {
					cDist = dist.get(i);
					kNode = i;
				}
			}
			if (kNode < 0) break;
			visited[kNode] = true;
			
			if (graph.containsKey(kNode)) { 
				for (int[] info: graph.get(kNode)) {// check all neighbors
					dist.put( info[0], Math.min( dist.get(info[0]), (dist.get(kNode) + info[1])));			
				}
			}
		}
		
		int ans = 0;
		for(int d: dist.values()) {
			if (d == Integer.MAX_VALUE) return -1;
			ans = Math.max( ans,  d);
		}
		return ans;
    }
	
	
	public static void main(String args[])
    {
		int[][] times = {{2,1,1}, {2,3,1},{3,4,1}};
		int N = 4; // 4 nodes
		int K = 2; // signal is sent from node 2
		
		System.out.println( networkDelayTime(times, N, K)); //2
    }
	
}
	