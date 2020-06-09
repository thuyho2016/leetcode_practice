
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/* 
  56. Merge Intervals
  https://leetcode.com/problems/merge-intervals/
  
Given a collection of intervals, merge all overlapping intervals.
 
For example,
Given [1,3],[2,6],[8,10],[15,18]
return [1,6],[8,10],[15,18]
	
	Explanation: 
	1. sorted list: [[1,3], [2,6], [8,10], [15,18]]
	  if [1,3], [2,6] has overlap ( end element  of 1st is greater than first element of 2nd array, so merged
	  else move to next interval
	2. merged interval: [[1,6], [8,10], [15,18]]      
		
Input: [[8,10], [1,3], [2,6], [3,9], [15,18]]
1. sorted list: [[1,3], [2,6], [3,9], [8,10], [15,18]]
2. merged interval: [[1,10], [15,18]]
	

Note: if current.last < first of next, then interval = [current.first, next.last]
   
   Use Math.max to find which number is larger?
   
   Time complexity : O(nlogn)
   Space complexity : O(1)
   shopping cart - focus buy item easy 
   integrate different service 
   
 */

public class Interval_MergeOverLap {
	
	/*
	 Best solution - method is passing double array
	 2. Go through the list
	 If start of current element <= end of previous element
	  	then merge and use Math.max to pick larger one
     Else 
       not overlap, then add to result and move to next interval
      
      [1,3],[2,6],[8,10],[15,18]
      
        Arrays.sort(intervals, (int[] a, int[] b) -> a[0] - b[0]);
        //Arrays.sort(arr, (a,b) -> Integer.compare(a[0], b[0]));
	*/
	
	private static int[][] merge(int[][] intervals){
        if(intervals == null || intervals.length == 0) {
            return new int[][] {};
        }
        
        Arrays.sort(intervals, (int[] a, int[] b) -> a[0] - b[0]);
        
        ArrayList<int[]> result = new ArrayList<int[]>();
        
        int[] previous = intervals[0];  // first array = [1,3]
        
        for(int i = 1; i < intervals.length; i++) {
            int[] current = intervals[i]; //[2,6] , [8,10]
            
            //compare start element of current array with end element of previous array 
            if(previous[1] > current[0] ) { //overlap, compare 3 > 2 ? , previous=[1,6] and current=[8,9] so compare 6 > 8 ?
            	previous = new int[]{ previous[0], Math.max(previous[1], current[1] )};  //[1, max(3,6)] = [1,6]           
            }
            else { //no overlap
            	 result.add(previous);  //previous=[1,6], [8,10]
                 previous = current;
            }
        }
        
        result.add(previous); //add last element
        
        return result.toArray(new int[result.size()][2]);   //convert to int array
    }

		
	/* Prefer this method - passing Interval array
	1.sort by create Comparator Class with using comparator interface. E.g Collections.sort(arry, new Sortbyroll());
	
	2. Go through the list
	 If start of current element <= end of previous element
	  	then merge and use Math.max to pick larger one
     Else 
       not overlap, then add to result and move to next interval       
        
	*/
	
	public ArrayList<Interval> merge2(ArrayList<Interval> intervals) {
        
        if(intervals.size() == 0 || intervals.size() == 1)
            return intervals;
        
        //sort by calling new IntervalComparator Class 
       
        Collections.sort(intervals, new IntervalComparator()); // sort [3,1] to [1,3]
        
        Interval first = intervals.get(0);   //first interval is [1,3]
        int start = first.start;   //1
        int end = first.end;       //3
        
        //Create Interval arrayList to hold result
        ArrayList<Interval> result = new ArrayList<Interval>();
                    
        
        for(int i = 1; i < intervals.size(); i++) { // start at 2nd interval
            Interval current = intervals.get(i);  //[2,6]
            
            if(current.start <= end) {  // 2 < 3 ,
                end = Math.max(current.end, end);   //find the max of (6,3) by Math.max (6,3) = 6
            
            }else{     //not overlap, then add to result and move to next interval
                result.add(new Interval(start, end)); //[8,10]
                //move to next interval
                start = current.start;  
                end = current.end;
            }
            
        }
        
     // Add the last interval
        result.add(new Interval(start, end));  //[15,18]
        return result;
    }
	
     //Create a class IntervalComparator implemnts Comparator to sort in ascending
	// returns -1, 0 or 1 to say if it is less than, equal, or greater to the other.
	public class IntervalComparator implements Comparator<Object>{
        public int compare(Object o1, Object o2){
            Interval i1 = (Interval)o1;
            Interval i2 = (Interval)o2;
            return i1.start - i2.start;  //use compareTo if it is String
        }
	}
	
	
	public static void main (String[] agrs) {
		
		int [][] input1 = {{1,3},{2,6},{8,10},{15,18}};
		int[][] res = merge(input1);
		System.out.println("output: " + Arrays.deepToString(res));  //[1, 6], [8, 10], [15, 18]]
		
		
		//create IntervalList
		ArrayList<Interval> input = new ArrayList<Interval> ();
		input.add(new Interval(1, 3));
		input.add(new Interval(2, 6));
		input.add(new Interval(8, 10));
		input.add(new Interval(15, 18));
		
		System.out.println("Input: ");
		for(Interval interval: input) {
			System.out.println(Arrays.asList(interval.start, interval.end));
		}
	
		Interval_MergeOverLap  m = new Interval_MergeOverLap();
		ArrayList<Interval>  output = m.merge2(input);
		for(Interval interval: output) {
			System.out.println(Arrays.asList(interval.start, interval.end));
		}
		
	}
}