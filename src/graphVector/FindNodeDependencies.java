package graphVector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 * design algorithm to find dependencies of a specific node. 
 * 
 * For example:
 * input stream 2d array in which nodes are given  [[7,11], [5,11], [3,8], [11,2], [11,9], [8,9],[3,10]] . 
 * Now for a given node 2, the output should be [7,5,11,2] or [5,7,11,2]. 
 * 
 *         7    5
 *       /   \ /
 *      8     11   3
 *       \   / | \ |
 *         9   2  10
 *      
 */

public class FindNodeDependencies {
	
	public static void main(String[] args) {
		int[][] nodes =  {{7,11}, {5,11}, {3,8}, {11,2}, {11,9}, {8,9},{3,10}};
		System.out.println(findNodes(nodes, 2));
	}

	private static List<Integer> findNodes(int[][] nodes, int startNode) {
		
		List<Integer> result = new ArrayList<>();
		Map<Integer, Set<Integer>> map = new HashMap<>();
		
		for(int[] node : nodes) {  // n = [7,11], n = [5, 11], [3,8], [11, 2]
			map.putIfAbsent(node[1], new HashSet<>());  //{11=[7]} mean key = 11, value is a hashset 7  , 8=[] , 2=[]
			map.get(node[1]).add(node[0]); //add first element of each node to hashset: {11=[7]},  {11=[5, 7]} , {8=[3], 11=[5, 7]}, {2=[], 8=[3], 11=[5, 7]},..
		}
		
		Set<Integer> visited = new HashSet<>();
		
		bfs(map, result, visited, startNode);  // map = {2=[11], 8=[3], 9=[8, 11], 10=[3], 11=[5, 7]}
		Collections.reverse(result); // result before reverse = [2, 11, 5, 7]. After reverse, result = [7, 5, 11, 2]
		return result;
	}

	private static void bfs(Map<Integer, Set<Integer>> map, List<Integer> res, Set<Integer> visited, int startNode) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(startNode);  //[2]
		
		while(!q.isEmpty()) {  // 
			int cur = q.poll(); // 2, 11, 5 , 7
			
			if(!visited.contains(cur)) {
				visited.add(cur); //[2], [2, 11], [2, 5, 11], [2, 5, 7, 11]
				res.add(cur);     //[2] , [2, 11],  [2, 11, 5], [2, 11, 5, 7]
				//read map - 2=[11] cur 2 has value set = 11 , cur 11 has return value is a hashset =[5, 7], cur 5 does no exist in map, so has no value, cur 7 doesn't exist in map
				for(int eVal : map.getOrDefault(cur, new HashSet<>())) { 
					if(!visited.contains(eVal)) {
						q.offer(eVal);  // q = [11] , q = [5], q = [5, 7], 
					}
				}
			}
		}
	}
}
