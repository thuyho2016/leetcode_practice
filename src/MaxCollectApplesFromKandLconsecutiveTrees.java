import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/*
 * Alice and Bob work in a beautiful orchard. There are N  apple trees in the orchard. The apple trees are arranged in a row 
 * and they are numbered from 1 to N. Alice is planning to collect all the apples from K consecutive trees 
 * and Bob is planning to collect all the apples from L consecutive trees. 
 * 
 * They want to choose two disjoint segments (one consisting of K trees for Alice and the other consisting of L trees for Bob) 
 * so as not to disturb each other.  What is the maximum number of apples that they can collect? 
 * 
 * Write a function that given an array A consisting of N integers denoting the number of apples on each apple tree in the row, 
 * and integers K and L denoting, respectively, the number of trees that Alice and Bob can choose when collecting, 
 * returns the maximum number of apples that can be collected by them, or -1 if there are no such intervals. 
 * 
 * For example, given A =[6, 1,4,6,3,2,7,4], K=3, L=2, your function should return 24, 
 * because Alice can choose trees 3 to 5 and collect 4 + 6 + 3 = 13 apples, and Bob can choose trees 7 to 8 and collect 7 + 4 = 11 apples. 
 * Thus, they will collect 13 + 11 = 24 apples in total, and that is the maximum number that can be achieved.
 * 
 * Given A = [10, 19, 15], K = 2, L = 2, your function should return -1, 
 * because it is not possible for Alice and Bob to choose two disjoint intervals. 
 * 
 * Assume that:
 * N is an integer within the range [2..600]; 
 * K and L are integers within the range [1 .. N-1]; 
 * each element of array A is an integer within the range [1..500] 
 * In your solution focus on correctness. The performance of your solution will not be the focus of the assessment
 * 
 * https://gist.github.com/Audhil/d04ea81651504b3ee4c5c8c2266a366f
 * 
 */

public class MaxCollectApplesFromKandLconsecutiveTrees {
	
	   private static int solution(int[] A, int K, int L) {
	        // write your code in Java SE 8
	        if (K + L > A.length)
	            return -1;

	        int sum, aliceApples, bobApples;
	        int totalApples = 0;
	    
	        HashMap<Integer, Integer> aliceMap = new HashMap<>();
	        HashMap<Integer, Integer> bobMap = new HashMap<>();
	    	  
	        for (int i = 0; i < A.length; i++) {
	            sum = 0;
	            if (K + i > A.length)
	                break;
	            for (int j = i; j < K + i; j++) {
	                sum += A[j];
	            }
	            aliceMap.put(i, sum);
	        }
	    
	        for (int i = 0; i < A.length; i++) {
	            
	        	sum = 0;
	            if (L + i > A.length)
	                break;
	            for (int j = i; j < L + i; j++) {
	                sum += A[j];
	            }
	            bobMap.put(i, sum);
	        }
	    
	    
	        for (Map.Entry<Integer, Integer> aliceEntry : aliceMap.entrySet()) {
	            
	        	bobApples = 0;
	            aliceApples = aliceEntry.getValue();
	            
	            System.out.println("aliceApples: " + aliceApples);
	            
	            if (aliceEntry.getKey() + K < bobMap.size()) {
	            	
	                for (int i = aliceEntry.getKey() + K; i < bobMap.size(); i++) {
	                    if (bobApples < bobMap.get(i))
	                        bobApples = bobMap.get(i);
	    
	                }
	                
	                if (totalApples < aliceApples + bobApples)
	                    totalApples = aliceApples + bobApples;
	    
	            }
	        }
	        return totalApples;
		
	    }

	    public static void main(String[] args) {
	        System.out.println("total collected apples: " + solution(new int[]{6, 1, 4, 6, 3, 2, 7, 4}, 3, 2));
	        System.out.println("total collected apples: " + solution(new int[]{6, 1, 4, 3, 11}, 2, 3));
	    }
}
