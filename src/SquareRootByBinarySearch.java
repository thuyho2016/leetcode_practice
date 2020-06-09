/*
 * 
 * 69. Sqrt(x)  (Level = easy)
 * Find a square root of number - use binary tree for better solution
 * Input: 9
 * Output: 3
 * 
 * Explanation: 3 * 3 = 9
 * 
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

public class SquareRootByBinarySearch {
	
	//Best Solution to do Binary Search.
	public static int squareRootByBinarySearch(int x) {	
		if ( x == 0 || x == 1) 
            return x;
	
		int start = 1, end = x/2;
		int ans = 0;		
		
		while ( start <= end) {  //1, 2, 3  < end = 4
			int mid = (start + end ) / 2;  //2 
			
			if ( x == mid * mid ) {
				return mid;
			}
			
			// if x < mid * mid   ,  e.g = 9 < 4 * 4 = 16
			if ( x < mid * mid) {
				end =  mid - 1; // discard right search space 
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
	
	
    public static int squareRoot(int y) 
    { 
        int x = 0, square = 0;
        
        if ( y < 0) return -1;
        if ( y == 0 || y == 1) 
            return y; 
        
        //x * x is greater than or equal to y.   
        while (square < y) { 
            x++; 
            square = x * x; 
        } 
        return x; 
    } 
    
    public static float root(int x) 
    { if (x < 0) return -1; 
    	if (x == 0 || x == 1) return x; 
    	
    	float root = 0.0f; 
    	float precision = 0.1f; 
    	float square = root; 
    	
    	while (square < x) 
    	{ 
    		root = root + precision; 
    		square = root * root; 
		} 
    	return root; 
	}

    
    public static void main(String[] args) 
    { 
        int y = 9; 
        System.out.println("Answer: " + squareRoot(y)); 
        System.out.println("Answer: " + squareRootByBinarySearch(y));
        
   /*     y = 16; 
        System.out.println("Answer: " + squareRoot(y)); 
        System.out.println("Answer: " + squareRootByBinarySearch(y));
  */      
        
    } 
}
