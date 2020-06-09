import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * 332. Reconstruct Itinerary
 * https://leetcode.com/problems/reconstruct-itinerary/
 * 
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. 
 * All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:
If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. 
For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].

All airports are represented by three capital letters (IATA code). You may assume all tickets form at least one valid itinerary.

Example 1:

Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]

Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]


Example 2:

Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]

Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]

Explanation: Another possible reconstruction is ["JFK", "SFO",  "ATL", "JFK","ATL","SFO"].
             But it is larger in lexical order                         
 */

// Use HashMap, PriorityQueue. dfs
// Create map to store departure and arrival airport [from, to]
//  PriorityQueue to store destination.  destination is also next curAirport
public class ReconstructItinerary {
	
    public static List<String> findItinerary(List<List<String>> tickets) {  

        List<String> result = new ArrayList<>();
        if (tickets == null || tickets.size() == 0) {
            return result;
        }
        
        HashMap<String, PriorityQueue<String>> map = new HashMap<>();         
       
        for(List<String> ticket: tickets) {  // [[MUC, LHR], [JFK, MUC], [SFO, SJC], [LHR, SFO]]
        	String from = ticket.get(0);   //MUC
        	String to = ticket.get(1);     //LHR
              
        	if (!map.containsKey(from)) { //MUC
        		PriorityQueue<String> pq = new PriorityQueue<>(); 
                map.put(from, pq);  // {MUC=[]}, {MUC=[LHR], JFK=[]},....
            } 
        	// when map cointains key 'MUC', then add to = LHR as value - {MUC=[]}
            map.get(from).add(to);  // {MUC=[LHR]} , {MUC=[LHR], JFK=[MUC]}           
        }
       
        //hashmap = {LHR=[SFO], MUC=[LHR], SFO=[SJC], JFK=[MUC]}
        String start = "JFK";
        construct(result, map, start);
        return result;
    }
    
    // dfs on the mappings
    private static void construct(List<String> result, HashMap<String, PriorityQueue<String>> map, String curAirport) {
        // get value as destination from map
        PriorityQueue<String> pq = map.get(curAirport);  //JFK -> MUC: pq = [MUC], MUC maps LHR: pq = [LHR]
        
        while(pq != null && !pq.isEmpty()) { //if destination exists,
        	String dest = pq.poll();         // remove it from the queue: dest = MUC, dest = LHR, dest = SFO, dest = SJC
            construct(result, map, dest); // get the next destination, 
        }
        
        result.add(0, curAirport); // resutl[0]= [SJC] ; result = [SFO, SJC];...
       // System.out.println("Add to result: " + result); //[JFK, MUC, LHR, SFO, SJC]
    }

	
	public static void main(String args[])
	{

		List<String>  itinerary1 =  new ArrayList<>();
	    itinerary1.add("MUC");
	    itinerary1.add("LHR");
	    
	    List<String>  itinerary2 =  new ArrayList<>();
	    itinerary2.add("JFK");
	    itinerary2.add("MUC");
	    
	    List<String>  itinerary3 =  new ArrayList<>();
	    itinerary3.add("SFO");
	    itinerary3.add("SJC");
	    
	    List<String>  itinerary4 =  new ArrayList<>();
	    itinerary4.add("LHR");
	    itinerary4.add("SFO");
	    
	    List<List<String>> tickets = new ArrayList<>();
	    tickets.add(itinerary1);
	    tickets.add(itinerary2);
	    tickets.add(itinerary3);
	    tickets.add(itinerary4);
	    
	    System.out.println("Output: " + findItinerary(tickets)); //["JFK", "MUC", "LHR", "SFO", "SJC"]
		
	}
}
