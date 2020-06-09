
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/* 
136. Single Number
https://leetcode.com/problems/single-number/

Given a non-empty array of integers, every element appears twice except for one. Find that single one.

	Input: [2,2,1]
	Output: 1


	Input: [4,1,2,1,2]
	Output: 4

Ideal is to find numbers that ONLY appear one time from array

Time complexity : O(n)
Space complexity : O(n)

*/

public class FindUniqueNumber {
	
	// Q1: find numbers that ONLY appeared one time in array, int[] arr = {1, 2, 2, 3 , 1 , 4} 	
	// return List<Integer>
	public static List<Integer> singleNumber(int[] arr) {
		List<Integer> result = new ArrayList<Integer>();   
		HashMap<Integer, Integer> map = new HashMap<Integer,Integer>();
	   
	   for (int i = 0; i < arr.length; i++) {
		   if (map.containsKey(arr[i])) { 
			   int count = map.get(arr[i]);
			   map.put(arr[i], count + 1); //increase value = count + 1
			   System.out.println("key " + arr[i] + " repeated " + map.get(arr[i])); //call get(key) to get value
		   } else {
			   map.put(arr[i], 1);
		   }
	    }
	   
/*	   Set<Map.Entry<Integer, Integer>> entires = map.entrySet();       
       for(Map.Entry<Integer, Integer> entry: entires){
           if ( entry.getValue() == 1) {
			   result.add(entry.getKey());
			   System.out.println(entry.getKey() + " = "+ entry.getValue());
		   }
       }
   */   
	   map.forEach((key, value) -> { 
			System.out.println(key + " = " + value);  //[4, 5, 7]
			}
	   );
	   
       map.forEach((key, value) -> { if (value == 1 ) { 
			result.add(key); 
		}}
	   );
       
   
       Collections.sort(result); // if the output is not sorted like [ 5, 4, 7]
       //Arrays.sort(result.toArray());
       return result;
	}
	
	
	public static void main (String[] agrs) {	
		
		int[] array1 = {1, 2, 2, 4 , 1, 2, 3};
		System.out.println("Numbers ONLY appears one time : "+ singleNumber(array1)); //3, 4	
		
		int[] array2 = {2, 7, 5, 6, 6, 4, 2};
		System.out.println("Numbers ONLY appears one time : " + singleNumber(array2)); //[4, 5, 7]
	
		int[] array4 = {2, 7, 5};
		Arrays.sort(array4);
		System.out.println("Sorted " + Arrays.toString(array4));
	}
}
