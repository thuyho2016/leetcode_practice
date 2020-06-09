import java.util.ArrayList;
import java.util.List;

/*
 Given all locations and totalSteakhouse
 Steak house location of (x, y) is the square root of x^2 and y^2 
  [x,y]  -> x^2 + y^2 = z -> find square root of (z)
  
  
  Input : 
  totalSteakHouses = 3
  All locations = [[1, 2], [3, 4], [1, -1]]
  numbSteakHouses = 2
  
  Output: [[1,-1],[1,2]]
    
  Explanation: 
  The distance of customer's location from [1, 2] is square root(5) = 2.2236	
 */
public class SquareRoot_NearestSteadHouses {
    
	public static List<List<Integer>> nearestXsteadHouses(int totalSteakhouses, List<List<Integer>> allocations, int numberSteakhouses) {
		
		List<List<Integer>> output = new ArrayList<List<Integer>>();
		
		for(List<Integer> a :  allocations){
			int total = 0;
		    for(int i = 0; i < a.size(); ++i){
		    	//total += a.get(i) * a.get(i);  //x * x + y * y or  using Math.pow of 2
		    	total +=  Math.pow(a.get(i), 2); // 1^2 + 2^2 = 10

		    }
		    
		    float sqr = sqroot(total);
			System.out.println("Square root of " + total + " = "+ sqr);
			
			// numberSteakhouses < sqr < totalSteakhouses)
			if (sqr <= numberSteakhouses || ( sqr >= numberSteakhouses && sqr <= totalSteakhouses)) {
				output.add(a);
			}
		}
		return output;
	}
	
	public static float sqroot(int number) { 
        float start = 0.0f; 
        float end = number; 
		float middle = start; 
        float precision = 0.01f; 
		
		if (number < 0) return -1; 
		if (number == 0 || number == 1) return number; 
		
		
		float difference = (float) Math.abs(Math.pow(middle, 2) - number); 
		
		while (difference >= precision) { 
			middle = (start + end) / 2.0f; 
			
			if (Math.pow(middle, 2) > number) 
			{ 
				end = middle; 
			} 
			else { 
				start = middle; 
			} 
			difference = (float) Math.abs(Math.pow(middle, 2) - number); 
		} 
		return middle;
	}
		
	
    public static void main (String[] agrs) {
    	// totalSteakHouses = 3
    	// All locations = [[1, 2], [3, 4], [1, -1]]
    	//numbSteakHouses = 2
    	
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
		
		List<List<Integer>> output = nearestXsteadHouses(3,a,2);
		System.out.println(output);  // ouput = [[1,-1],[1,2]]
		
		
    }
}
