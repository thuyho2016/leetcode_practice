
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
Find all the elements of [1, n] inclusive that do not appear in this array.
Input:
[4,3,2,7,8,2,3,1]

Output:
[5,6]

There, use Set to filter duplicate number --

   1. go through each element from Hash Set to find which number is missing by using increment index from 1 , 2, 3, 
   2.if not found number, then add to List
*/

public class FirstNumberNotAppearInArray  {
	
	public static List<Integer> findDisappearedNumbers(int[] nums) {
		
        System.out.println("Length: " + nums.length); //8
		
		Set<Integer> set = new HashSet<Integer>();    //use Set to remove duplicate value --> [1, 2, 3, 4, 7, 8]
        List<Integer> result =new ArrayList<Integer>();
        
        //remove duplicate
        for(int n: nums){
            set.add(n);      // [1, 2, 3, 4, 7, 8]
        }
        
        //print out Set
        for (int n : set) {
			System.out.println("Each element: " + n);
		}
               
        for(int i = 1; i <= nums.length; i++){   // Index = 1, 2, 3, 4, 5, 6,7,8
        	
           //if not found number, then add to List
        	if(!set.contains(i)){  //
            	result.add(i);
            }
        }
        return result;
		
	}
	

    public static List<String> findDisappearedWords(List<String> words, String word ) {
		
        System.out.println("Length: " + words.size()); //8
		
		Set<String> set = new HashSet<String>();    //use Set to remove duplicate value --> [1, 2, 3, 4, 7, 8]
        List<String> result =new ArrayList<String>();
        
        //remove duplicate
        for(String w: words){
            set.add(w);      // [1, 2, 3, 4, 7, 8]
        }
        
        //print out Set
        for (String n : set) {
			System.out.println("Each element: " + n);
		}
               
        for(int i = 1; i <= words.size(); i++){   // Index = 1, 2, 3, 4, 5, 6,7,8
        	
           //if not found number, then add to List
        	if(!set.contains(words.get(i))){  //
            	result.add(words.get(i));
            }
        }
        return result;
		
	}

	public static List<Integer> countDuplicateChars(String s, char[] c) {
		List<Integer> counts = new ArrayList<Integer>();
		
		// create a Map that hold Char as key, count as Integer value 
		HashMap<Character, Integer> map = new HashMap<>(); // {a = 2, b = 3}
		
		//check map contains Key
		for  (int i = 0; i < s.length(); i++) {
			if (!map.containsKey(  s.charAt(i)  ))  {// add element to HashMap, set value = 1
				map.put(s.charAt(i), 1);
			} else {
				int count = map.get(s.charAt(i)); //
				map.put(s.charAt(i), count + 1); // if key found duplicate, then increase value = count + 1
			}
		}
		
		//print HashMap
		for (Character key: map.keySet()) {
			System.out.println(" key = " + key);
		}
		
		for (Integer value: map.values()) {
			System.out.println(" value = " + value);
		}
		
		//{a,b}
		for ( int j = 0; j < c.length; j++) { 
			//int count;
			if (!map.containsKey(c[j])) {
				counts.add(0);  //
			} else {  //map contains key a , b
				System.out.println("Map contains character  : " + c[j] + " so, add value to list: " + map.get(c[j]));
				counts.add(map.get(c[j])); //Returns the value to which the specified key (a, b) is mapped
			}
		}
		
		return counts;
	}

	public static void main (String[] args)
    {  
		int[] nums = {4,3,2,7,8,2,3,1}; 
		System.out.println("Numbers Not appear : "+ findDisappearedNumbers(nums)); // 5,6 
		
		List<String> siteUrls = new ArrayList<>();
		siteUrls.add("ccauser");
		siteUrls.add("ccasp");
		siteUrls.add("webex");
		siteUrls.add("tsp");
		
		System.out.println("Words Not appear : " + findDisappearedWords(siteUrls, "webex"));
    }
}