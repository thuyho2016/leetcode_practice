import java.util.ArrayList;
import java.util.List;

/*
 * 295. Find Median from Data Stream  (hard level)
 * https://leetcode.com/problems/find-median-from-data-stream/
 * 
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. 
 * So the median is the mean of the two middle value.

For example,
[2,3,4], the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
 

Example:

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3) 
findMedian() -> 2


1 , 2, 3, 4   Odd len = 5, 
To get index of 3 and 4: 
 For 3: len/2 - 1 = 2 - 1 = 1
 For 4:  len /2

Define data structure to use. Cannot sort array
- Priority Queue

 */
public class MedianFinder {
	List<Integer> list ;
	
	 /** initialize your data structure here. */
    public MedianFinder() {
		list = new ArrayList<Integer>();
    }
    
    //use binary Search to decide where to add num - left side or right side
    //Run time: O(logN) 
    public void addNum(int num) {
        int len = list.size();
        
        int lo = 0;
        int hi = len - 1;
        int mid;
        
        while (lo <= hi) {
        	mid = lo + (hi - lo) /2;
        	
        	int MidValue = list.get(mid);
        	
        	// if number to be add is smaller than Mid Value, then add that number in the left part
        	if (num <= MidValue) { 
        		hi = mid - 1;
        	} else {
        		lo = mid +1;
        	}
        }
        list.add(lo, num); //add num at low index
    }
    
    /**
     *  
     *  O(1)
     */
    public double findMedian() {
    	int len = list.size();
    	
        if (len == 0) return 0;
        
        int index = len/2;
        
        if(len % 2 == 1) { // Length is odd , pick the number at mid index.        	
        	return list.get(index);  //  Example: [1, 2, 3], len =  3, 3%2 = 1, so list.get(len/2) = 2
        
        } else {  // len % 2 == 0, then Length is even. Formula is Add 2 numbers at index - 1 and index, then Division by 2 
        	return ( list.get(index - 1) + list.get(index) ) / 2.0;  // (1 + 2) / 2.0 = 5.5
        }
    }
    
/**   
    Solution 2:  Use Two Heap to solve this problem. Add operation is O(logn), findMedian is O(1)
    
 	https://www.programcreek.com/2015/01/leetcode-find-median-from-data-stream-java/
 	Steps:
 	1. Each element is added to minHeap
 	2. the minimum element from minHeap is poped out and added to maxHeap. (This assures all elements in minHeap are greater than maxHeap.
 	3. Two heaps need to be load balanced.
 	
 	
    PriorityQueue<Integer> minHeap = null;
    PriorityQueue<Integer> maxHeap = null;
 

    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    }
 
    public void addNum(int num) {
        minHeap.offer(num);
        maxHeap.offer(minHeap.poll());
 
        if(minHeap.size() < maxHeap.size()){
            minHeap.offer(maxHeap.poll());
        }
    }
 
    public double findMedian() {
        if(minHeap.size() > maxHeap.size()){
            return minHeap.peek();
        }else {
            return (minHeap.peek()+ maxHeap.peek())/2.0;
        }
    }
   */ 
    
    
    public static void main(String[] args) {
    	MedianFinder obj = new MedianFinder();
    	obj.addNum(1);
    	obj.addNum(2);
    	double ans1 = obj.findMedian();  //1.5
    	System.out.println(ans1);
    	
    	obj.addNum(3);
    	double ans2 = obj.findMedian(); //2.0
    	System.out.println(ans2);
    }
}
