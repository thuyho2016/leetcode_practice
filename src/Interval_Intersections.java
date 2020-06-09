
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* 
986. Interval List Intersections
https://leetcode.com/problems/interval-list-intersections/

  Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

(Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  
The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval. 
For example, the intersection of [1, 3] and [2, 4] is [2, 3].)


Example 1:

Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]

Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]

Reminder: The inputs and the desired output are lists of Interval objects, and not arrays or lists.

Time Complexity: O(M + N), where M, NM,N are the lengths of A and B respectively.

Space Complexity: O(M + N), the maximum size of the answer

 */

public class Interval_Intersections {
	
	/*
        Arrays.sort(intervals, (int[] a, int[] b) -> a[0] - b[0]);
        //Arrays.sort(arr, (a,b) -> Integer.compare(a[0], b[0]));
	*/
	public static int[][] intervalIntersection(int[][] A, int[][] B) {
 
		List<int[]> res = new ArrayList<>();
	    int i = 0, j = 0;
	    
	    while( i < A.length && j < B.length){
	    	
	        int low = Math.max( A[i][0], B[j][0]);
	        int high = Math.min(A[i][1],B[j][1]);
	        
	        if( low <= high) {
	        	res.add( new int[]{low, high} );
	        }
	        
	        if (A[i][1] < B[j][1])
	            i++;
	        else
	            j++;
	            
	    }
	   
	    return res.toArray( new int[res.size()][]);
		
    }
	
	// use Interval object
	public static Interval[] intervalIntersection2(Interval[] A, Interval[] B) {
		 
		List<Interval> res = new ArrayList<>();
	    int i = 0, j = 0;
	    
	    while( i < A.length && j < B.length){
	    	
	        int low = Math.max( A[i].start, B[j].start); //1, 5, 8
	        int high = Math.min(A[i].end,B[j].end); //2 , 5, 10
	        
	        if( low <= high)
	        res.add(new Interval(low, high) ); //[1,2] , [5,5]
	        
	        if ( A[i].end < B[j].end)
	            i++;
	        else   // A[i].end >= B[j].end
	            j++;
	            
	    }
	   
	    return res.toArray( new Interval[res.size()] );
		
    }
	
     /**
      * Create a class IntervalComparator implemnts Comparator to sort
      * returns -1, 0 or 1 to say if it is less than, equal, or greater to the other.
      */
	public class IntervalComparator implements Comparator<Object>{
        public int compare(Object o1, Object o2){
            Interval i1 = (Interval)o1;
            Interval i2 = (Interval)o2;
            return i1.start - i2.start;  //use compareTo if it is String
        }
	}
	
	
	public static void main (String[] agrs) {
		
/*		int[][] A = {{0,2},{5,10},{13,23},{24,25}};
		int[][] B = {{1,5},{8,12},{15,24},{25,26}};
		
		int[][] res = intervalIntersection(A,B);
		System.out.println("output: " + Arrays.deepToString(res));  //[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
*/		
		//create Interval List
		Interval i1 = new Interval(0, 2);
		Interval i2 = new Interval(5, 10);
		Interval i3 = new Interval(13, 23);
		Interval[] input = {i1, i2, i3};
		
		Interval i4 = new Interval(1, 5);
		Interval i5 = new Interval(8, 12);
		Interval i6 = new Interval(15, 24);
		Interval[] input2 = {i4, i5, i6};
		
		Interval[] res2 = intervalIntersection2(input,input2);
		System.out.println("output: " + Arrays.deepToString(res2));  
		for (Interval i : res2) {
			System.out.println("merged: [" + i.start +"," + i.end + "]");
		}
	}
}