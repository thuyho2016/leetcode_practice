package graphVector;

import java.util.*;

/* 133. Clone Graph
 * https://leetcode.com/problems/clone-graph/
 * 
 * 
 * Given a reference of a node in a connected undirected graph, return a deep copy (clone) of the graph.
 * 
 * Each node in the grap contains a val (int) and a list(List<Node>) of its neighbor.
 class Node {
    public int val;
    public List<Node> neighbors;
}

 Adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.

The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.

Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
Output: [[2,4],[1,3],[2,4],[1,3]]

Explanation: There are 4 nodes in the graph.
1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).


Time & Space Complexity : O(N)

 */
public class CloneGraph {
    
	class Node {
	    public int val;
	    public List<Node> neighbors;

	    public Node() {}

	    public Node(int _val,List<Node> _neighbors) {
	        val = _val;
	        neighbors = _neighbors;
	    }
	};
	
     //Prefer BFS
	/*
	 Algorithm

	1. We will use a hash map to store the reference of the copy of all the nodes that have already been visited and copied. 
	       The key is the node of the original graph and value would be the corresponding cloned node of the cloned graph. 

	2. Add the first node to the queue. Clone the first node and add it to visited hash map.

	3. Do the BFS traversal.

	- Pop a node from the front of the queue.
	- Visit all the neighbors of this node.
	- If any of the neighbors was already visited then it must be present in the visited dictionary. Get the clone of this neighbor from visited in that case.
	- Otherwise, create a clone and store in the visited.
	- Add the clones of the neighbors to the corresponding list of the clone node.

	 */
	 public Node cloneGraph(Node node) {
        if (node == null) {
            return node;
        }

        // Hash map to save the visited node and it's respective clone
        // as key and value respectively. This helps to avoid cycles.
        HashMap<Node, Node> visited = new HashMap();

        // Put the first node in the queue
        LinkedList<Node> queue = new LinkedList<Node> ();
        queue.add(node);
        
        // Clone the node and put it in the visited dictionary.
        visited.put(node, new Node(node.val, new ArrayList()));

        // Start BFS traversal
        while (!queue.isEmpty()) {
            // Pop a node say "n" from the front of the queue.
            Node n = queue.remove();
            
            // Iterate through all the neighbors of the node "n"
            for (Node neighbor: n.neighbors) {
                if (!visited.containsKey(neighbor)) {
                    visited.put(neighbor, new Node(neighbor.val, new ArrayList<>())); // Clone the neighbor and put in the visited, if not present already                    
                    queue.add(neighbor);  // Add the newly encountered node to the queue.
                }
                // Add the clone of the neighbor to the neighbors of the clone node "n".
                visited.get(n).neighbors.add(visited.get(neighbor));
            }
        }

        // Return the clone of the node from visited.
        return visited.get(node);
    }
	 
	 
	 //DFS 
	 private HashMap <Node, Node> visited = new HashMap <> ();
	 
	 public Node cloneGraph_dfs(Node node) {
        if (node == null) {
            return node;
        }

        // If the node was already visited before. Return the clone from the visited dictionary.
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        // Create a clone for the given node. Note that we don't have cloned neighbors as of now, hence [].
        Node cloneNode = new Node(node.val, new ArrayList());
       
        visited.put(node, cloneNode);   // The key is original node and value being the clone node.

        // Iterate through the neighbors to generate their clones and prepare a list of cloned neighbors to be added to the cloned node.
        for (Node neighbor: node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor)); // recursive
        }
        return cloneNode;
    }
	
	//Input = [[2,4],[1,3],[2,4],[1,3]]
	 public static void main(String[] args)
	 {
		int[][] input = {{2,4},{1,3},{2,4},{1,3}};
		//System.out.println(cloneGraph(input)); 
	 }
	 
}
	