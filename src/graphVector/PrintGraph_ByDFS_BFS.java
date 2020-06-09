package graphVector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/*
Breadth first search (BFS) and Depth first search (DFS) are two algorithms used for traversing and searching a node in a graph.
	
				A
		B		C		D
	E		F	

In DFS, it visits the root node and go deeply to children before asking neighbor node for a path.
nodes are visited by going through the depth of the tree from the starting node. 
it will be “A B E F C D”. 

It go to check children nodes until it reaches the end node, i.e. E and F nodes. 

Steps:
1. Push the root node in the Stack.  
2. Loop until stack is empty. 
3. Peek the node of the stack.  
4. If the node has unvisited child nodes, get the unvisited child node, mark it as traversed and push it on stack.   
5. If the node does not have any unvisited child nodes, pop the node from the stack.

*/
public class PrintGraph_ByDFS_BFS
{	
	public ArrayList<Node> nodes = new ArrayList<Node>();
	public HashMap<Integer, Node> noodeLookup = new HashMap<Integer, Node>();
	
	public static class Node {
		private int id;
		LinkedList<Node> adjacent = new LinkedList<Node>();
		
		private Node(int id) {
			this.id = id;
		}
	}
	
	private Node getNode(int id) {
		Node node = new Node(id);
		return node;
	}
	
	public void addEdge(int source, int destination) {
		Node s = getNode(source);  // source node
		Node d = getNode(destination);
		s.adjacent.add(d); // add together		
	}
	
	//DFS
	public boolean hasPathDFS(int source, int destination) {   
		Node s = getNode(source);  // source node
		Node d = getNode(destination);  // destination node	
		
		HashSet<Integer> visited = new HashSet<Integer>(); // create a HashSet to contains all node Id

		return hasPathDFS(s, d, visited);	//recursive			
	}
	
	public boolean hasPathDFS(Node source, Node destination, HashSet<Integer> visited) {
		if ( visited.contains(source.id)) {  // already visited
			return false; // because it has no path
		}
		
		visited.add(source.id); // update node visited
		if (source == destination) {
			return true;
		}
		
		//go to check my children, to see if they have a path
		for (Node child: source.adjacent) {
			if (hasPathDFS(child, destination, visited)) {
				return true;
			}
		}
		return false;
	}
	
	
	/*If we do the breadth first traversal of the above graph and print the visited node. It will be “A B C D E F”. 
	 * The BFS visits the nodes level by level, so it will start with level 0 which is the root node, 
	 * and then it moves to the next levels which are B, C and D, then the last levels which are E and F. 
	 */

	public boolean hasPathBFS(int source, int destination) {   		
		return hasPathBFS(getNode(source), getNode(destination));	//call recursive			
	}
	
	private boolean hasPathBFS(Node source, Node destination) {
		LinkedList<Node> nextToVisit = new LinkedList<Node>();
		HashSet<Integer> visited = new HashSet<Integer>();
		
		nextToVisit.add(source);
		
		while (!nextToVisit.isEmpty()) {// while nextToVisit is not empty, keep going
			Node node = nextToVisit.remove(); //remove very first element of the list
			if(node == destination) {
				return true;
			}
			
			if (visited.contains(node.id)) { // do visited checking
				continue;
			}
			visited.add(node.id);   // update my visited - who i visited
			
			for (Node child: node.adjacent) { // add children to nextToVisit
				nextToVisit.add(child);
			}
		}
		return false;
	}
				

	public static void main(String args[])
	{
		
	}
		
}