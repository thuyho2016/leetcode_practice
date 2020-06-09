package string;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/* Amazon OA
You work on a team whose job is to understand the most sought after toys for the holiday season. A teammate of yours has built a webcrawler that extracts a list of quotes about toys from different articles. You need to take these quotes and identify which toys are mentioned most frequently. Write an algorithm that identifies the top N toys out of a list of quotes and list of toys.

Your algorithm should output the top N toys mentioned most frequently in the quotes.

Input:
The input to the function/method consists of five arguments:

numToys, an integer representing the number of toys
topToys, an integer representing the number of top toys your algorithm needs to return;
toys, a list of strings representing the toys,
numQuotes, an integer representing the number of quotes about toys;
quotes, a list of strings that consists of space-sperated words representing articles about toys

Output:
Return a list of strings of the most popular N toys in order of most to least frequently mentioned

Note:
The comparison of strings is case-insensitive. If the value of topToys is more than the number of toys, return the names of only the toys mentioned in the quotes. If toys are mentioned an equal number of times in quotes, sort alphabetically.

Example 1:

Input:

numToys = 6
topToys = 2
toys = ["elmo", "elsa", "legos", "drone", "tablet", "warcraft"]
numQuotes = 6
quotes = [
"Elmo is the hottest of the season! Elmo will be on every kid's wishlist!",
"The new Elmo dolls are super high quality",
"Expect the Elsa dolls to be very popular this year, Elsa!",
"Elsa and Elmo are the toys I'll be buying for my kids, Elsa is good",
"For parents of older kids, look into buying them a drone",
"Warcraft is slowly rising in popularity ahead of the holiday season"
];

Output:
["elmo", "elsa"]

Explanation:
elmo - 4
elsa - 4
"elmo" should be placed before "elsa" in the result because "elmo" appears in 3 different quotes and "elsa" appears in 2 different quotes.

time complexity O(Nlog(k))  
Space complexity : O(N) to store the hash map

This problem is similar with Top K Frequent Element
 */

public class TopNToys
{

	public static void main(String[] args) {
		
		int numToys = 6;
        int topToys = 2;
        String[] toys = {"elmo", "elsa", "legos", "drone", "tablet", "warcraft"};
        int numQuotes = 6;
        String[] quotes = {
            "Emo is the hottest of the season! Elmo will be on every kid's wishlist!",
            "The new Elmo dolls are super high quality",
            "Expect the Elsa dolls to be very popular this year",
            "Elsa and Elmo are the toys I'll be buying for my kids",
            "For parents of older kids, look into buying them a drone",
            "Warcraft is slowly rising in popularity ahead of the holiday season"};
        
        List<String> result = topToys(numToys, topToys, toys, numQuotes, quotes);
        System.out.println(result.toString());
        
        System.out.println(returnTopToys(numToys, topToys, toys, numQuotes, quotes));
        
	}

	public static List<String> topToys(int numToys, int topToys, String[] toys, int numQuotes, String[] quotes) {
		List<String> result = new ArrayList<>();
		
		if(numToys==0||numQuotes==0) return result;
        
        Map<String, Integer> map = new HashMap<>(); //use a hashmap to keep trach how many time each word appear. (K, V) where K to store word and V is to count the number of appearance of the word
        for(String toy: toys) {
            map.put(toy, 0);   //map = {tablet=0, warcraft=0, legos=0, elmo=0, elsa=0, drone=0}
        }

        for(String quote: quotes) {
        	String[] list = quote.replaceAll("[^a-zA-Z]"," ").toLowerCase().split(" ") ;
        	
            for(String word: list) {
                if(word != null|| word.length() > 0) {
                    String wordInLowerCase = word.toLowerCase();
                    
                    if(map.containsKey(wordInLowerCase)) { //if key match words from quote, increase value
                        map.put(wordInLowerCase, map.get(wordInLowerCase)+1);
                    }
                }
            }
        }

        PriorityQueue<String> pq = new PriorityQueue<String>((w1, w2) -> map.get(w1).equals(map.get(w2)) ? w2.compareTo(w1) : map.get(w1) - map.get(w2) ); // sort words


        for (String word: map.keySet()) {
            pq.offer(word);
            if (pq.size() > topToys) 
            	pq.poll();
        }
	   
	    
	    while (!pq.isEmpty()) {
	      result.add(pq.poll());
	    }

	    Collections.reverse(result);

	    return result;
	  }
	
	
	public static List<String> returnTopToys(int numToys, int topToys, String[] toys, int numQuotes, String[] quotes) {
        List<String> res = new ArrayList<>();
        
        if(numToys==0||numQuotes==0) return res;
        
        Map<String, Integer> map = new HashMap<>();
        for(String toy: toys) {
            map.put(toy, 0);   //map = {tablet=0, warcraft=0, legos=0, elmo=0, elsa=0, drone=0}
        }

        for(String quote: quotes) {
        	String[] list = quote.replaceAll("[^a-zA-Z]"," ").toLowerCase().split(" ") ;
        	
            for(String word: list) {
            	if(word != null|| word.length() > 0) { 
                    String wordInLowerCase = word.toLowerCase();
                    if(map.containsKey(wordInLowerCase)) { //if key match words from quote, increase value
                        map.put(wordInLowerCase, map.get(wordInLowerCase)+1);
                    }
                }
            }
        }

        //map = {tablet=0, warcraft=1, legos=0, elmo=3, elsa=2, drone=1}
        List<Map.Entry<String, Integer>> entryList= new ArrayList<>(map.entrySet());

        Collections.sort(entryList, (a, b) -> (b.getValue() - a.getValue()) != 0 ? (b.getValue() - a.getValue()) : a.getKey().compareTo(b.getKey()));
        
        int i = 0;
        while (i < entryList.size() && topToys > 0) {
            topToys--;
            res.add(entryList.get(i).getKey());
            i++;
        }

        return res;

    }

}