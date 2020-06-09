
import java.util.Arrays;
import java.util.Collections;
import java.util.Queue;
import java.util.LinkedList;
import java.util.PriorityQueue;

/* 
 *  
 215. Kth Largest Element in an Array
 https://leetcode.com/problems/kth-largest-element-in-an-array/
 
 
 * Find Kth Largest Elements in an unsorted array
 * given array is [1, 23, 12, 9, 30, 2, 50] and k = 3 (you are asked for the largest 3 elements) 
 * i.e., k = 3 
 * Output: your program should print 50, 30 and 23
 * 
 * Time complexity : O(N * logk).
 * Space complexity : O(k)
 * 
 * Note: see KLargestByQuickSort.java under sort folder
 * 
 */

public class KthLargestElement_ByPriorityQueue
{
	    
    //Best Solution 1: use PriorityQueue
    // 1. if size of queue < k, continue Add element to queue:  so, three elements are added {1,23,12}
    // 2. compare head element in queue and  current element of array nums
    // 3. if head element is smaller, remove it from the queue and insert current element from array to the queue 
    // if head element is larger than cur num (12 < num = 2 ), then move to next num
	
    public static int findKthLargest(int[] nums, int k) {
        if (nums.length == 0) return 0;
    
        PriorityQueue<Integer> q = new PriorityQueue<>(k);  // queue with size k
        
        for(int num : nums) {   // [1, 23, 12, 9, 30, 2, 50] 
        	
            if (q.size() < k) {    // if size < k, insert to queue [1, 23, 12]
                q.offer(num);  //insert
            } else if(q.peek() < num) {  //compare head element in the queue < current element of array nums,  12 < num = 2 ? not go inside
            	
            	System.out.println("Queue before remove and insert: " + q);
                q.poll();  //remove the head element 
                q.offer(num); //insert to 
                System.out.println("Queue after: " + q);
                //if 1 < 9, remove 1, insert 9: [9, 23, 12]
                // if 9 < 30 , remove 9, insert 30: [12, 23, 30] //queue has sort elements
                //note  12 < num = 2 ? not go inside
                // if 12 < 50, remove 12, add 50: [23,30,50]
              
            }
        }
        System.out.println("Queue has kth largest numbers: " + q);  //[23, 30, 50]
        return q.peek();    //return first element of three from queue    
    }
    
    
    public static int[] findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        
        for(int i : nums)
        {
            pq.add(i);
            System.out.println("Queue after add: "+ pq);
            
            if(pq.size() > k) 
            	pq.poll(); //remove head element
        } 
        
        System.out.println("Queue has k largest numbers: "+ pq);
       // return pq.peek();   // return ONLY first element of three from queue  
        
        //to return k largest elements
        int[] newArr = new int[k];
        for (int i = 0; i < k; i++) {  
	    	newArr[i] = pq.poll();  
	    }
	    return newArr;
        
            
    }
    
  //Use existing Arrays.sort method  
    public static int[] kLargest3(int[] arr, int k) 
    {
        // Sort the given array arr in ascending order
        Arrays.sort(arr); //[1, 2, 9, 12, 23, 30, 50]
        int[] newArr = new int[k];
        
	    // Print the first kth largest elements 
        int length = arr.length - 1;
	    for (int i = 0; i < k; i++) {  
	    	newArr[i] = arr[length - i];  
	    }
	    return newArr; // descending order [50, 30, 23]   
	} 
    
    
    public static void main(String[] args) 
    {
        
        int k = 3;
        int[] nums = {1, 23, 12, 9, 30, 2, 50};  
        
        int r = findKthLargest(nums, k); 
        System.out.println("kth largest from Queue: "+ r); //23
        
        int[] arr = {3,2,1,5,6,4};
        System.out.println("2th largest from Queue: " + findKthLargest(arr, 2)); // 5
        
        int[] nums2 = {1, 23, 12, 9, 30, 2, 50};  //50, 30 , 23
        //System.out.println("kth largest from Queue: " + findKthLargest2(nums2, k));
        System.out.println("kth largest from Queue: " + Arrays.toString(findKthLargest2(nums2, k)));
        
        //int arr[] = {1, 23, 12, 9, 30, 2, 50};
        // int[] res = kLargest(arr,k); 
   	    // System.out.print(Arrays.toString(res));	  
    }
}