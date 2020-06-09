import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/* 815. Bus Routes ( hard level)
 * https://leetcode.com/problems/bus-routes/
 * 
We have a list of bus routes. Each routes[i] is a bus route that the i-th bus repeats forever. For example if routes[0] = [1, 5, 7],
 this means that the first bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->... forever.

We start at bus stop S (initially not on a bus), and we want to go to bus stop T. 
Travelling by buses only, what is the least number of buses we must take to reach our destination? 
Return -1 if it is not possible.

Example:
Input: 
routes = [[1, 2, 7], [3, 6, 7]]
S = 1
T = 6
Output: 2   ( return how many bus to take to reach destination stop 6)

Explanation:  1 -> 7 -> 6
The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.

Solution: BFS 
For each of the bus stop, we maintain all the buses (bus routes) that go through it.
  - I use a HashMap, where the key is bus stop number and all the buses (bus routes) that go through it are added to an ArrayList as Value.

  - maintain a HashSet that stores the buses that we have already visited.


 */

public class BusRoutes {
    // perform a breadth first search on bus numbers.
	  public static int numBusesToDestination(int[][] routes, int S, int T) {
	    if (S == T) return 0;
	    
	    HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
	    
	    for (int i = 0; i < routes.length; i++) {
	       for (int j = 0; j < routes[i].length; j++) {
	         
	         ArrayList<Integer> buses = map.getOrDefault( routes[i][j], new ArrayList<>());
	         buses.add(i); //[0]
	         map.put(routes[i][j], buses); // value is index of the route {1=[0]} ; or  map.get(routes[i][j]).add(i); 
	           
	       }
	    }
	    //  map = {1=[0], 2=[0], 3=[1], 6=[1], 7=[0, 1]}
	    Queue<Integer> q = new LinkedList<>();
	    Set<Integer> visited = new HashSet<>(); //hashset to record all visited stops and we won't check a stop for twice.	    
	    q.add(S);  //q = [1] add the first stop
	    
	    int count = 0;
	    
	    while (!q.isEmpty()) { // 2nd round: q has [1, 2, 7]
	       int sizeLevel = q.size(); //2nd round: q has size 3
	       count += 1;   //2nd round: count = 2
	        
	       for (int i = 0; i < sizeLevel; i++) {
	         
	          int curBus = q.poll();   // current Bus 1 has route index 0
	          ArrayList<Integer> buses = map.get(curBus); //[0] get the first route
	            
	          for (int bus: buses) {
	        	  if (visited.contains(bus)) continue;
	        	  
	        	  visited.add(bus); //[0], [0, 1]
	            
	        	  for ( int j = 0; j < routes[bus].length; j++ ) { 
	        		  if (routes[bus][j] == T) {//if possible Next Stop == T //[1, 2, 7], [3, 6, 7]
	        			  return count;
	        		  }
	        		  q.add(routes[bus][j]); //[1], [1, 2], [1, 2, 7]
	            }
	          
	          }
	       }
	    }
	    return -1; //not found
	        
	  }
	  	
	  
	  public static void main(String[] args) {
		  int[][] routes = {{1, 2, 7}, {3, 6, 7}};
		  System.out.println(numBusesToDestination( routes, 1, 6));
    
	  }
}
