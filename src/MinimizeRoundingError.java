import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/* 1058. Minimize Rounding Error to Meet Target
 * https://leetcode.com/problems/minimize-rounding-error-to-meet-target
 * 
 * Prices = [0.70, 2.80, 4.90]  
 * Output: "1.000"
 * 
 * Explanation: Use Floor, Ceil and Ceil operations to get
 * (0.7 - 0) + (3 - 2.8) + (5 - 4.9) = 0.7 + 0.2 + 0.1 = 1.0 
 
3 possible
roundedPrice = [1, 3, 4]  --> error: [0.3, 0.2, 0.9] -> total error is 1.4
roundedPrices= [1, 2, 5] --> error: [0.3, 0.8, .10] -> total error is 1.2
roundedPrice = [0, 3, 5]  --> error: [0.7, 0.2, 0.1] -> total error is 1.0
 
Compute the loss when the number is rounded up and rounded down.
Add this loss to the deviation computed so far. if the cumulative loss of deviation + rounded down less than deviation + rounded up, round the number down. 
Else round the number up.
The goal is to keep the deviation minimal at any given index.


celing - cur = 1 - 0.70 = 0.3
cur - floor = 0.70-0 = 0.7
diff = 0.4

Walmart interview - need to return [0.7, 0.2, 0.1] which has min error 1.0

Return the string "-1" if the rounded array is impossible to sum to target
Example 2:

Input: prices = ["1.500","2.500","3.500"], target = 10
Output: "-1"
Explanation: 
It is impossible to meet the target.

 */
public class MinimizeRoundingError {
	
	public static String minError(String[] prices, int target) {
		int count = 0;
		double sum = 0;
		List<Integer> output = new ArrayList<>();
		
		float res = 0;
        PriorityQueue<Double> pq = new PriorityQueue<>();
        
        for (String s : prices) {
            float cur = Float.valueOf(s);   //0.7
            double floor = Math.floor(cur);  //0.0
            double ceil = Math.ceil(cur);   //1.0
            
            if (floor != ceil) {
                pq.offer((ceil - cur) - (cur- floor)); //[-0.8000001907348633, -0.3999999761581421, -0.5999999046325684]
               // output.add((int) (ceil - cur));
            }
            
            double diff = cur - floor; 
            System.out.println("diff : " + diff);
            
            res += diff; //res = 0.7 , 1.5, 2.4
            target = (int) (target - floor); //8, 6, 2
            System.out.println("target " + target + ", result " + res);
        }
        
        if (target < 0 || target > pq.size())
            return "-1";
        
        while (target > 0) {
        	target--;
        	System.out.println("target: " + target);
            res += pq.poll();
        }
        
        System.out.println("List: " + output);
        return String.format("Result %.3f", res);
	}
	
	 public static void main(String[] args) {
		 // float[] prices = { (float) 0.70, (float) 2.80, (float) 4.90};
		 String[] prices =  {"0.70","2.80","4.90"};
		 int target = 8;
		 System.out.println(minError(prices, target)); //1.000
		    //[0,3,5]		   
	 }
}
