import java.util.ArrayList;
import java.util.List;

/*
 * 
[x,y]  -> x^2 + y^2 = z -> find square root of (z)

Input 2: [[1, 2], [3, 4], [1, -1]]
Total of steak house  = 3
number of steak house = 2

Output: [[1,-1],[1,2]]

* Algorithm:
  1) Start with ‘start’ = 0, end = ‘x/2’,
  2) Do following while ‘start’ is smaller than or equal to ‘end’.
      a) Compute ‘mid’ as (start + end)/2
      b) compare mid*mid with x.
      c) If x is equal to mid * mid, return mid.
      d) If x is smaller, do binary search between start and mid - 1
      e) If x is greater, do binary search between mid+1 and end. In this case, we also update ans.
     
 
 Note: The Binary Search can be further optimized to start with ‘start’ = 0 and ‘end’ = x/2. 
        square root of x cannot be more than x/2 when x > 1.
 */

public class SquareRoot_NearestSteakHouse_ByBT {
    
	public static List<List<Integer>> nearestXsteadHouses(int totalSteakhouses, List<List<Integer>> allocations, int numberSteakhouses) {
		
		List<List<Integer>> output = new ArrayList<List<Integer>>();
		
		for(List<Integer> a :  allocations){
			int total = 0;
		    for(int i = 0; i < a.size(); ++i){
		    	//total += a.get(i) * a.get(i);  //x * x + y * y = 1 * 1 + 2 * 2 = 5
		    	total += Math.pow(a.get(i), 2);   // or x^2 + y^2  using Math.pow of 2
		    }
		    
		    //find square root of total 
		    float sqr = squareRootByBinarySearch(total); // total = 5, 25, 2
			System.out.println("Square root of " + total + " = "+ sqr);
			
			// numberSteakhouses < sqr < totalSteakhouses)
			if (sqr <= numberSteakhouses || ( sqr >= numberSteakhouses && sqr <= totalSteakhouses)) {
				output.add(a);
			}
		}
		return output;
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
							
			// if x > mid * mid , search start = mid + 1
			else {
				// discard left search space 
				start = mid + 1;     // 2 + 1 =3  < end = 4
				
				//update answer 
				ans = mid;    //2 
			} 
			
		}
		return ans;
    }
		
	
    public static void main (String[] agrs) {

		//Input: [[1, 2], [3, 4], [1, -1]]
		//Output: [[1,-1],[1,2]]
		List<Integer> l = new ArrayList<Integer>();
		l.add(new Integer(1));
		l.add(new Integer(2));
		
		List<Integer> l2 = new ArrayList<Integer>();
		l2.add(new Integer(3));
		l2.add(new Integer(4));
		
		List<Integer> l3 = new ArrayList<Integer>();
		l3.add(new Integer(1));
		l3.add(new Integer(-1));
		
	
		List<List<Integer>> a = new ArrayList<List<Integer>>();
		a.add(l);
		a.add(l2);
		a.add(l3);
	      
		System.out.println(a);
    	
		//ouput = [[1,2]]
		List<List<Integer>> output = nearestXsteadHouses(3,a,2);
		System.out.println(output);
		
    }
}
