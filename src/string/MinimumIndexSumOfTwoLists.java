package string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* 599. Minimum Index Sum of Two Lists (Easy level)
 * https://leetcode.com/problems/minimum-index-sum-of-two-lists/

Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants represented by strings.

You need to help them find out their common interest with the least list index sum. If there is a choice tie between answers, output all of them with no order requirement. 
You could assume there always exists an answer.

Example 1:
Input:
["Shogun", "Tapioca Express", "Burger King", "KFC"]
["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]

Output: ["Shogun"]

Explanation: The only restaurant they both like is "Shogun".


Example 2:
Input:
["Shogun", "Tapioca Express", "Burger King", "KFC"]
["KFC", "Shogun", "Burger King"]

Output: ["Shogun"]

Explanation: The restaurant they both like and have the least index sum is "Shogun" with index sum 1 (0+1).


Solution:
I find out shorter list and insert those elements in HashMap along with their index, (K,V) = (current element, index)

Then, iterate through longer list, and if a common restaurant is found, calculate index sum. 

If it is less than current lowest, then need to clear ArrayList and add element with new lowest. 
If it is equal to current lowest, then add it to ArrayList. 

Finally, return the ArrayList as a String array.
*/

public class MinimumIndexSumOfTwoLists {
	

	
	public static String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<>();  
        
        List<String> res = new LinkedList<>();
        
        int indexSum = Integer.MAX_VALUE;
        
        for(int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);  //map = {Tapioca Express=1, Shogun=0, Burger King=2}
        }
        
        for(int i = 0; i < list2.length; i++) {
        	
            // if same restaurant is existing && sum of the indexes are not greater than previous sum.
            if(map.containsKey(list2[i]) && indexSum >= map.get(list2[i])  +  i) { 
            	System.out.println(map.get(list2[i]) + ", i = " + i);
            	
                // if sum is less than previous sum, get rid of the previous sum, create a new list.
                if(indexSum > map.get(list2[i]) + i) {  
                    res =  new LinkedList<>();
                    indexSum = map.get(list2[i]) + i;   //indexSum = (0 + 1) = 1
                    
                    System.out.println("IndexSum = " + indexSum);
                }
                // add the same restaurant's name anyway.
                res.add(list2[i]);
            }
        }
        return res.toArray(new String[res.size()]);
    }
	
	public static void main(String[] args) {
		String[] list1 = {"Shogun", "Tapioca Express", "Burger King", "KFC" };
		String[] list2 = {"KFC", "Shogun", "Burger King"};
		System.out.println(Arrays.toString(findRestaurant(list1, list2)));
	}
}
