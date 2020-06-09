import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 57. Insert Interval
 * https://leetcode.com/problems/insert-interval/
 * 
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].

 */
public class Interval_InsertNewInterval {
	
	public static int[][] merge(int[][] intervals, int[] newInterval) {
	    if ( intervals == null || intervals.length == 0 ) return new int[][] {newInterval};
	    
	    ArrayList<int[]> result = new ArrayList<int[]>();
	    
	    for (int[] current: intervals) {
	    	
	    	//compare end element of current Interval with start element of newInterval
	    	if (newInterval == null || current[1] < newInterval[0]) {
	    		result.add(current);
	    	}
	    	else if (current[0] > newInterval[1] ) { // no overlap
	    		result.add(newInterval); 
	    		result.add(current);
	    		newInterval = null;
	       }
	    	
	    	else { //(current[1] > newInterval[0] || current[0] < newInterval[1]) { // 3 > 2 from newInterval, then merge
	    		
	            newInterval[0] = Math.min(current[0],newInterval[0]); //min(1,2)= 1
	            newInterval[1] = Math.max(current[1], newInterval[1]); //max(3,5) = 5
	    	}
	    	
	    }
	    
	    if (newInterval != null)
	        result.add(newInterval);
 
	     return result.toArray(new int[result.size()][2]);
	  }
	
	//Input is List<Interval>
	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
		List<Interval> result = new ArrayList<Interval>();
		
		if(intervals == null || intervals.size() == 0) {
			result.add(newInterval);
			return result;
		}
        
        boolean flag = false;
       
        for (Interval interval : intervals) {
            if (interval.end < newInterval.start) {
                result.add(interval);
            } else if (interval.start > newInterval.end) {
                if (!flag) {
                    result.add(newInterval);
                    flag = true;
                }
                result.add(interval);
                
            } else {
                newInterval.start = Math.min(newInterval.start, interval.start);
                newInterval.end = Math.max(newInterval.end, interval.end);
            }
        }
        if (!flag) result.add(newInterval);
        return result;
    }
	
	  
	  public static void main(String[] args) {
	    int[][] interval = {{1,3},{6,9}};
	    int[] newInterval = {2,5};
	    System.out.println(Arrays.deepToString(merge(interval, newInterval))); //[[1, 5], [6, 9]]
	    
	    int[][] interval2 = {{1,3},{6,9}};
	    int[] newInterval2 = {4,5};
	    System.out.println(Arrays.deepToString(merge(interval2, newInterval2))); //[[1, 3], [4, 5], [6, 9]]
	    
	    int[][] interval3 = {{2,3},{6,9}};
	    int[] newInterval3 = {1,2};
	    System.out.println(Arrays.deepToString(merge(interval3, newInterval3))); //[1, 3], [6, 9]]
	    
	    int[][] interval4 = {{2,3},{6,9}};
	    int[] newInterval4 = {1,2};
	   // System.out.println(Arrays.deepToString(merge(interval4, newInterval4))); //[1, 3], [6, 9]]
	    
	  }
}
