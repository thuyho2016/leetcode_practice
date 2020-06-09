import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/* 
973. K Closest Points to Origin
https://leetcode.com/problems/k-closest-points-to-origin/
 
Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]

Explanation: 
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].

Example 2:
Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]

This similar is SquareRoot_NearestSteakHouse_ByBT.java

 */

public class KClosestPointsToOrigin_ByPriorityQueue  {
	public static void main (String[] args)
    {
		int[][] points = new int[][] { {1,3}, {-2,2} };
		int k = 1;
		int[][] res = kClosest(points,k);
		System.out.println("Result: " + Arrays.deepToString(res)); //[[-2,2]]
	
		 
		 int[][] points2 = {{3,3},{5,-1},{-2,4}};
		 k = 2;
		 int[][] res2 = kClosest(points2,k);
		 System.out.println("Result: " + Arrays.deepToString(res2));//[[3,3],[-2,4]]	
						 
    }
	
	//Solution using Priority Queue - use maxHeap - easy to understand
	 public static int[][] kClosest(int[][] points, int K) {
        if (K == 0 || points == null || points.length == 0 || K > points.length) {
            return null;
        }
        
        //prefer this
        PriorityQueue<int[]> pq = new PriorityQueue<>( (a, b) -> (b[0] * b[0] + b[1] * b[1] - (a[0] * a[0] + a[1] * a[1])));
        
    /*    PriorityQueue<int[]> pq = new PriorityQueue<> (K, new Comparator<int[]>(){
        	
            public int compare(int[] p1, int[] p2){
                return (getDistance(p1) - getDistance(p2));  // 
            }
            
        });
      */  
        for (int[] point : points) {
            pq.add(point);  // [1, 3] or pq.offer to add element to the queue
            if (pq.size() > K) 
            	pq.remove();
        }
        //index [0] = [-2, 2], [1] = [1, 3]
        int[][] ans = new int[K][2];
        for (int j = 0; j < K; j++) {
            ans[j] = pq.poll(); //remove head element from the queue [[-2, 2]]
        }
        return ans;
    }
	 
	 private static int getDistance(int [] point) {
	        return point[0] * point[0] + point[1] * point[1];
	 }
	 
	 //way2 not working
	 public static int[][] kClosest2(int[][] points, int K) {
	        if (K == 0 || points == null || points.length == 0 || K > points.length) {
	            return null;
	        }
	       
	        //int[][] output = new int[][] {};
	        List<int[]> list = new ArrayList<>(); 
	        
	        for(int[] p :  points){
				int total = 0;
			    for(int i = 0; i < p.length; ++i) {
			    	total += p[i] * p[i]; 
			    }
		      //find square root of total 
			    float sqr = squareRootByBinarySearch(total); // total = 5, 25, 2
				System.out.println("Square root of " + total + " = "+ sqr);
	
				
	        }
	        return list.toArray(new int[list.size()][]);
	        
	 }
		public static int squareRootByBinarySearch(int x) {	 //9
			if ( x == 0 || x == 1) 
	            return x;
		
			int start = 1, end = x/2; //end 4.5
			int ans = 0;		
			
			while ( start <= end) {  //1, 2, 3  < end = 4
				int mid = (start + end ) / 2;  //2 
				
				if ( x == mid * mid ) {
					return mid;
				}
				
				                   
				if ( x < mid * mid) {  // e.g = 9 < 4 * 4 = 16
					end =  mid - 1;    // discard right search space 
				}			
				
				else { // if x > mid * mid , search start = mid + 1
					// discard left search space 
					start = mid + 1;     // 2 + 1 =3  < end = 4
					
					//update answer 
					ans = mid;    //2 
				} 
				
			}
			return ans;
	    }
	
}