
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* Apple interview 
 
Given a list of matching/equivalent 

 pairs and a list of unique songs, create unique sets of equivalent songs.

Example 1:

Input: 
matching pairs: [[1, 2], [2, 3], [5, 6], [4, 6], [2, 7], [3, 8], [9, 10]]
unique songs: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

Output: [[1, 2, 3, 7, 8], [4, 5, 6], [9, 10]]

Java solution with dfs. Time and space complexity are O(n).
*/


public class UniqueSetsSong {

	static List<List<Integer>> res = new ArrayList<List<Integer>>();
	
	public static void main(String[] args) {
		int[][] matchingPairs = {{1, 2}, {2, 3}, {5, 6}, {4, 6}, {2, 7}, {3, 8}, {9, 10}};
		int[] uniqueSongs = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		
		findMatch(matchingPairs, uniqueSongs);
		System.out.println(res);
	}

	static void findMatch(int[][] matchingPairs, int[] uniqueSongs) {
		
		Map<Integer, List<Integer>> map = new HashMap<>();
		
		for(int[] mp : matchingPairs) {
			List<Integer> res = map.getOrDefault(mp[0], new ArrayList<Integer>());
			res.add(mp[1]);
			map.put(mp[0], res);
			
			List<Integer> res1 = map.getOrDefault(mp[1], new ArrayList<Integer>());
			res1.add(mp[0]);
			map.put(mp[1], res1);
		}
		
		boolean[] visited = new boolean[uniqueSongs.length + 1 ];
		
		for(int i=0;i < uniqueSongs.length;i++) {
			
			if(!visited[uniqueSongs[i]]) {
				visited[uniqueSongs[i]] = true;
				
				List<Integer> temp = new ArrayList<>();
				temp.add(uniqueSongs[i]);
				
				dfs(map, uniqueSongs, visited, temp, i, res);
				res.add(new ArrayList<>(temp));
			}
		}
	}
	
	static void dfs(Map<Integer, List<Integer>> map, int[] uniqueSongs, boolean[] visited,  List<Integer> temp, int cur, List<List<Integer>> res) {
		
		for(int song : map.get(uniqueSongs[cur])) {
			
			if(!visited[song]) {
				visited[song] = true;
				temp.add(song);
				dfs(map, uniqueSongs, visited, temp, song - 1 , res);
			}
		}
	}
	
}