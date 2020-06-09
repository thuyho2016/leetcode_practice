import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/*
Given 2 lists a and b. Each element is a pair of integers where the first integer represents the unique id and the second integer represents a value.
 Your task is to find an element from a and an element form b such that the sum of their values is less or equal to target and as close to target as possible. 
 Return a list of ids of selected elements. If no pair is possible, return an empty list.

Example 1:

Input:
a = [[1, 2], [2, 4], [3, 6]]   //[id, value]
b = [[1, 2]]
target = 7

Output: [[2, 1]]

Explanation:
There are only three combinations of [ id from list a, id from listb ] = [1, 1], [2, 1], and [3, 1], which have a total sum of 4, 6 and 8, respectively.
[2,1] - id 2 from list a and id 1 from list b
Since 6 is the largest sum that does not exceed 7, [2, 1] is the optimal pair.

Example 2:

Input:
a = [[1, 3], [2, 5], [3, 7], [4, 10]]
b = [[1, 2], [2, 3], [3, 4], [4, 5]]
target = 10

Output: [[2, 4], [3, 2]]

Explanation:
There are two pairs possible. Element with id = 2 from the list a has a value 5, and element with id = 4 from the list b also has a value 5. Combined, they add up to 10. 
Similarily, element with id = 3 from `a` has a value 7, and element with id = 2 from `b` has a value 3. Combined, they add up to 10

These also add up to 10. Therefore, the optimal pairs are [2, 4] and [3, 2].

 */
public class OptimalUltization_LargestSumFrom2Lists {
	
	   private static List<int[]> getPairs(List<int[]> a, List<int[]> b, int target) {
		   Map<Integer, List<int[]>> map = new HashMap<>();//key is sum , value is list of ids from a and b.

	        for (int i = 0; i < a.size(); i ++){
	            for (int j = 0; j < b.size(); j ++){
	            	
	            	//add value of list a and b as key
	                List<int[]> sums = map.getOrDefault( a.get(i)[1] + b.get(j)[1], new ArrayList<int[]>()); //[1, 1] , i =1, j =0 so [2, 1] , 
	                sums.add(new int[] {a.get(i)[0], b.get(j)[0]});
	                map.put(a.get(i)[1] + b.get(j)[1], sums); // { 4 = [1, 1]} , { 6 = [2, 1]} , {8 = [3, 1]}
	            }
	        }

	        List<Integer> allSums = new ArrayList<>();
	        
	        for (Integer i : map.keySet()){  // i = 4, 6, 8
	            if (i < target){
	                allSums.add(i);  //[4, 6]
	            } 
	            else if (i == target){
	                return map.get(target);
	            }
	        }
	        
	        if (allSums.size() == 0){
	            return new ArrayList<>();//target is less than every possible sums.
	        }
	        
	        return map.get(Collections.max(allSums)); 
	    } 
	
	public static void main (String[] args)
    {
		int[] arr1 = {1, 2};
		int[] arr2 = {2, 4};
		int[] arr3 = {3, 6};
		
		List<int[]> a = new ArrayList<>();		
		a.add(arr1);
		a.add(arr2);
		a.add(arr3);
	
		int[] arr = {1, 2};
		List<int[]> b = new ArrayList<>();		
		b.add(arr);
		
		List<int[]> output = getPairs(a, b, 7);
		System.out.println(Arrays.deepToString(output.toArray()));  //[[2, 1]]
	
		
    }
}
