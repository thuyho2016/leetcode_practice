package string;
/* 
 * https://leetcode.com/problems/analyze-user-website-visit-pattern/
 * 1152. Analyze User Website Visit Pattern
 * 
 * We are given some website visits: the user with name username[i] visited the website website[i] at time timestamp[i].

A 3-sequence is a list of websites of length 3 sorted in ascending order by the time of their visits.  (The websites in a 3-sequence are not necessarily distinct.)

Find the 3-sequence visited by the largest number of users. If there is more than one solution, return the lexicographically smallest such 3-sequence.


Example 1:

Input: username = ["joe","joe","joe","james","james","james","james","mary","mary","mary"], 
	timestamp = [1,2,3,4,5,6,7,8,9,10], 
	website = ["home","about","career","home","cart","maps","home","home","about","career"]

Output: ["home","about","career"]

Explanation: 
The tuples in this example are:
["joe", 1, "home"]
["joe", 2, "about"]
["joe", 3, "career"]
["james", 4, "home"]
["james", 5, "cart"]
["james", 6, "maps"]
["james", 7, "home"]
["mary", 8, "home"]
["mary", 9, "about"]
["mary", 10, "career"]
The 3-sequence ("home", "about", "career") was visited at least once by 2 users.
The 3-sequence ("home", "cart", "maps") was visited at least once by 1 user.
The 3-sequence ("home", "cart", "home") was visited at least once by 1 user.
The 3-sequence ("home", "maps", "home") was visited at least once by 1 user.
The 3-sequence ("cart", "maps", "home") was visited at least once by 1 user.

Solution:
Every user visits websites and the websites for each user visits can form 3 - sequences. Our goal is to find the most common 3-seq in those users.

we collect the time and website info according to every user
n^3 traverse all 3-seq in every user and use count map to record the 3-seq ocurring times(remember to sort by time and avoid same sequence in the same user)
get the result

 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MostVisitPattern {
	static class Pair {
	    int time;
	    String web;
	    public Pair(int time, String web) {
	        this.time = time;
	        this.web = web;
	    }
	}
	
	public static List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
		Map<String, List<Pair>> map = new HashMap<>();
		
		int n = username.length;
		// collect the website info for every user, key: username, value: (timestamp, website)
		for (int i = 0; i < n; i++) {
			map.putIfAbsent(username[i], new ArrayList<>());
			 map.get(username[i]).add(new Pair(timestamp[i], website[i]));
		}
		 // count map to record every 3 combination occuring time for the different user.
		Map<String, Integer> count = new HashMap<>();
        String res = "";
        
        for (String key: map.keySet()) {
        	Set<String> set = new HashSet<>();
            // this set is to avoid visit the same 3-seq in one user
        	List<Pair> list = map.get(key)  ;
        	Collections.sort(list, (a,b) ->(a.time - b.time)); // sort by timestamp
        	
        	//brutal force O(N^3)
        	for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    for (int k = j + 1; k < list.size(); k++) {
                    	String str = list.get(i).web + " " + list.get(j).web + " " + list.get(k).web;
                    	
                    	if (!set.contains(str)) {
                    		count.put(str, count.getOrDefault(str, 0) + 1);
                    		set.add(str);
                    	}
                    	
                    	if(res.equals("") || count.get(res) < count.get(str) || ( count.get(res) == count.get(str) && res.compareTo(str) > 0)) {
                    		res = str;
                    	}
                	}
                }
        	}      
		}
		//grap the right answer
        String[] r = res.split(" ");
        List<String> result = new ArrayList<>(); // website
        for (String s: r)
        	result.add(s);
		return result;
        
    }

	public static void main (String[] args)
    {
		String[] username = {"joe","joe","joe","james","james","james","james","mary","mary","mary"};
		int[] timestamp = {1,2,3,4,5,6,7,8,9,10};
		String[] website = {"home","about","career","home","cart","maps","home","home","about","career"};
		System.out.println(mostVisitedPattern(username, timestamp, website));
    }
}
