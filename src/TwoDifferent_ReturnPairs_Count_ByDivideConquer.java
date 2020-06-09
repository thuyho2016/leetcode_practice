import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*  
 
Given an integer array and a positive integer k, Count all distinct pairs with difference equal to k

Require: first and second steps take O(nLogn). So overall complexity is O(nLogn).

Input: arr[] = {1, 5, 3, 4, 2}, k = 3
Output: 2
Two pairs with difference 3 are {1, 4} and {5, 2} 

 
Use Sorting:
Steps: 
1) Initialize count as 0
2) Sort all numbers in increasing order.
3) Remove duplicates from array.
4) Do following for each element arr[i]
   a) Search for arr[i] + k in subarray from i+1 to n-1.
   b) If arr[i] + k found, increment count. 
5) Return count. 

Note:

- Merge Sort, Heap Sort is O(nLogn)
- Time complexity: The first step (sorting) takes O(nLogn) time. The second step runs binary search n times, 
so the time complexity of second step is also O(nLogn). Therefore, overall time complexity is O(nLogn).

 */

public class TwoDifferent_ReturnPairs_Count_ByDivideConquer{

    
	public static int countPairsWithGivenDifference(int[] arr, int k) {
		 List<List<Integer>> res = new ArrayList<List<Integer>>();
		int count = 0;  
		int n = arr.length ;
		
		//sort array elements
		Arrays.sort(arr);
		
		// code to remove duplicate from arry - ??
		//removeDuplicateNumber(arr) ;
		
		//pick a first element point - [1, 2, 3, 4, 5]
		for (int i = 0; i < n-1 ; i++) { 
			
			//Case 1 (positive number): x - y = k -> x = y + k 
			int y = arr[i] + k;  
			
			int tmp = searchByDivideConquer(arr, i + 1, n - 1, y);
			if(tmp != -1) { // if dont check index = -1, it will include [3, 6], [4, 7]]
				res.add(Arrays.asList(arr[i], y));
				count++;
			}
		}
		System.out.println("Pairs of Two numbers diff: " + res);
		return count;
	}
	
	static int searchByDivideConquer(int arr[], int low,  int high, int x)  
	{ 
		if (low <= high)  //1 < 4
		{ 
			int mid = low + (high - low) / 2;   // find mid
			
			if (x == arr[mid]) 
				return mid; 
			if (x < arr[mid]) {
				return searchByDivideConquer(arr, low, (mid - 1), x); 
			} else {
				return searchByDivideConquer(arr, (mid + 1), high, x); 
			}
		} 
		
		return -1; 
	} 
	
	
	public static void removeDuplicateNumber(int[] arr) {
		   
		HashMap<Integer, Integer> map = new HashMap<Integer,Integer>();
		   for (int i = 0; i < arr.length; i++) {
		   
			   //put each number to hash to filter duplicate
			   if (map.containsKey(arr[i])) { 
				   
				   int count = map.get(arr[i]);
				   map.put(arr[i], count + 1); //increase value = count + 1
				  
			   } else {
				   map.put(arr[i], 1);
			   }
		    }
		   
		   //{1=2, 2=2, 3=1}
		   System.out.print("Print keys from hashmap: ");
		   Set<Integer> keys = map.keySet();
	       for(Integer k: keys) {
	           System.out.println(k + " ") ;
	       }
	}
	

	public static void main(String[] args) {
	  
		 int[] arr = {1, 5, 3, 4, 2}; 
		 int k = 3;
		//output : {1, 4} and {5, 2} 
		 System.out.println("Count of pairs:  " + countPairsWithGivenDifference(arr, k)); //2
		 

		 int[] arr1 = {0, -1, -2, 2, 1};
		 k = 1;
		 // output: [[1, 0], [0, -1], [-1, -2], [2, 1]]
		 System.out.println("Count of pairs: " + countPairsWithGivenDifference(arr1, k)); //4
		 
	}
}


