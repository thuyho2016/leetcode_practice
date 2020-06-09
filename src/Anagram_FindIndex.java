
/*
 * Compare 2 array for elements
  A = [12, 28, 46]
  B = [46, 12, 28] 
  
  Output [1, 2, 0]  // index
  
We want to know where the 12 occurs in B, say at position 1; then where the 28 occurs in B, which is position 2; 
then where the 46 occurs in B, which is position 0.

We should return a list of index:  [1, 2, 0]  
 */

public class Anagram_FindIndex {
	
	//It is not optimize solution
	public static int[] anagramMapping(int[] a ,int[]b){
		int[] result = new int [a.length];   // create a new array to hold indexes
		
		for (int i = 0 ; i < a.length; i++) {
			for (int j = 0 ; j < b.length; j++) {
				//compare 2 array for elements. If same, then get index of element of 2nd array 
				if (a[i] == b[j]) {
					result[i] = j; //get index of element in B
				}
			}
		}
		return result;
	}	
	
	public static void main (String[] agrs) {
		int [] a = {12, 28, 46};
		int [] b = {46, 12, 28};
		int[] result = anagramMapping(a,b);
		for (int e : result) {
			System.out.println(e);
		}
		System.out.println("DONE");
	}
}

	  
	