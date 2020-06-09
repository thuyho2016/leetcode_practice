package binarytree;

/*
 * have k lists of sorted integers in ascending order. 
 * Find the smallest range that includes at least one number from each of the k lists.
 * Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
   Output: [20,24]

Explanation: 
List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
List 2: [0, 9, 12, 20], 20 is in range [20,24].
List 3: [5, 18, 22, 30], 22 is in range [20,24].
 *
 */

public class SmallestRange {
public static void main (String[] agrs) {
		
		int[][] input = {{4, 10, 15, 24,26}, {0, 9, 12, 20}, {5, 18, 22, 30}};
		
		//Collections.sort(input);
		SmallestRange  m = new SmallestRange();
		int[] range = m.smallestRange(input);
		
		for (int i : range) {
			System.out.print( i + " ");
		}
		
	}

	public int[] smallestRange(int[][] nums) {
		int minx = 0, miny = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                for (int k = i; k < nums.length; k++) {
                    for (int l = (k == i ? j : 0); l < nums[k].length; l++) {
                        int min = Math.min(nums[i][j], nums[k][l]);
                        int max = Math.max(nums[i][j], nums[k][l]);
                        int n, m;
                        for (m = 0; m < nums.length; m++) {
                            for (n = 0; n < nums[m].length; n++) {
                                if (nums[m][n] >= min && nums[m][n] <= max)
                                    break;
                            }
                            if (n == nums[m].length)
                                break;
                        }
                        if (m == nums.length) {
                            if (miny - minx > max - min || (miny - minx == max - min && minx > min)) {
                                miny = max;
                                minx = min;
                            }
                        }
                    }
                }
            }
        }
        return new int[] {minx, miny};
    }

}