
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
 * Given scores = [4, 8, 7], lowerLimits = [2, 4], and upperLimits = [8, 4]. we perform the following q =  2 queries.
 * 
 * 0. Find all the scores in the inclusive range [2, 8]: there are three such candidates (i.e.,scores 4, 7, and 8),  so we store 3 in index 0 of our return array.
 * 1. Find all the scores in the inclusive range [4, 4]: there is one such candidate (i.e., score 4),so we store 1 in index 1 of our return array.
 * 
 * Output: returns array [3, 1].
 */

public class HackerRank_JobOffers {
	public static int[] jobOffers(int[] scores, int[] lowerLimits, int[] upperLimits) {
	    
    }
	
	
	public static void main (String[] args)
    {
		int[] scores = {4, 8, 7};
		int[] lowerLimits = {2, 4};
		int[] upperLimits = {8, 4};
		                     
		int[] output = jobOffers(scores, lowerLimits, upperLimits);
		System.out.println(Arrays.toString(output));  // [3,1]
    }
}
