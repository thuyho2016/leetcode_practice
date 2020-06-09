
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/*
 * Write a program to print all permutations of a given string
  
  Input: ABC
  Permutations of string ABC: is ABC ACB BAC BCA CBA CAB
    
  
   Problem 2:
   
   Distinct permutations of the string
   Input : ABCA
   
   Output : AABC AACB ABAC ABCA ACBA 
            ACAB BAAC BACA BCAA CABA 
            CAAB CBAA
   prints only distinct permutations even if there are duplicates in input.
            
 https://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/
 
 Steps:

1. We need to fill position one by one
   - To fill a position, we need to know what is our choice
   - swap current index with the choice index
   - Follow each choice one by one and keep moving forward
   - When we run out of choice, we print our permutation and we backtrack

 Use Backtracking:
	   1. Swap A with A  = ABC, Swap A with B = BAC, Swap A with C = CBA
	   2a. ABC (A is fixed): Swap B with B = ABC, Swap B with C -> ACB 
	   2b  BAC (B is fixed): Swap A with A = BAC , Swap A with C = BCA 
	   2c. CBA (C is fixed): Swap B with B = CBA, swap B with A = CAB


   permute (arr, 0 , n -1) = permute (ABC, 0, 2)

 Level 1:
 permute (ABC, 0, 2) 
     i = 0 
     swap (A, A)
     permute (ABC, 1, 2) 
     swap (A, A)
     
     i = 1
     swap (A, B)
     permute(BAC, 1, 2)
     swap (B, A)
     
     i = 2
     swap (A, C)
     permute (CAB, 1, 2)
     swap (C, A)
     
Level 2:     
 2a.  permute (ABC, 1, 2)
      i = 1 
      swap (B, B)
      permute (ABC, 2, 2) -->print ABC
      swap (B, B)
      
      i = 2
      swap (B, C)
      permute(ACB, 2, 2) --> print ACB
      swap (C, B)


 2b. permute (BAC, 1, 2)
  	 i = 1
     swap (A, A)
     permute (BAC, 2, 2) -->print BAC
     swap (A, A)
     
     i = 2
     swap (A, C)
     permute (BCA, 2, 2) -->print BCA
     swap (C, A)
  
  
2c.  permute (CBA, 1, 2)
  	 i = 1
     swap (B, B)
     permute (CBA, 2, 2) -->print CBA
     swap (A, A)
     
     i = 2
     swap (B, A)
     permute (CAB, 2, 2) --> print CAB
     swap (A, B)
     
 */


public class PermutationString {
	
	/**  Use Backtracking:
	   1. Swap A with A  = ABC, Swap A with B = BAC, Swap A with C = CBA
	   2a. ABC (A is fixed): has Swap B with B = ABC, Swap B with C -> ACB 
	   2b  BAC (B is fixed): Swap A with A = BAC , Swap A with C = BCA 
	   2c. CBA (C is fixed): Swap B with B = CBA, sway B with A = CAB
	   */
	
	// Case 1: Input is char array
	public static void permutation(char[] str, int l, int len) {
		
		//when we reach to size, then to print out string, ABC, BAC
	    if (l >= len) { //left == len
	    	for (int i = 0; i < str.length; i++) {
				System.out.print(str[i]); // A, AB, ABC; , A, AC, ACB; B, BA, BAC; B, BC, BCA;...
			}
			System.out.print(" ");
			
        } else {
	    
		    for (int i = l; i < len; i++) {  // i = l = 0, 1, 2; l = 1: i = 1, 2; l = 2: i = 2
	    		swap (str, l, i); 
	    		permutation(str, l + 1, len); //
	    		swap(str, l, i);
		    	
		    }
        }
	}	 
		
	public static void swap(char[] str, int i, int j) {
	    char tmp = str[i];
	    str[i] = str[j];
	    str[j] = tmp;
	    
	}
	  
	// Case 2: Input is String
	public static void permute(String str, int l, int len) {
		
	    if (l >= len) { // print when l reach to len
	    	System.out.print(str + " "); //ABC, ACB,..
	    	
	    } else {
	    
		    for (int i = l; i < len; i++) { // l = 0, 1, 2, 34
	    		str = swap2(str, l, i); // swap A, A
	    		permute(str, l + 1, len); // permute (ABC, 1, 2),....; permute (ABC, 2, 2) -->print ABC
	    		str = swap2(str, l, i);
		    }
	    }
	  
	  }
	  
     /** 
     * Swap Characters at position 
     * @param a string value 
     * @param i position 1 
     * @param j position 2 
     * @return swapped string 
     */	  
	  public static String swap2(String s, int i, int j) {
		 char tmp;
		 char[] cArray = s.toCharArray(); // convert str to char array
		 tmp = cArray[i];
		 cArray[i] = cArray[j];
		 cArray[j] = tmp;
		 return String.valueOf(cArray); //after swap , convert back to String		 		  
	  }
	
	 
	  //way 2: DFS and Recursvie	  
	  public static List<List<Character>> permute_Recursive(String s) {
		    char[] arr = s.toCharArray();
		    
		    boolean[] visited = new boolean[s.length()]; //[false, false, false]
		    Arrays.sort(arr);
		    
		    List<List<Character>> ret = new ArrayList<>();
		    dfs(arr, visited, new ArrayList<>(), ret);
		    return ret;
		}

		private static void dfs(char[] arr, boolean[] visited, List<Character> path, List<List<Character>> ret) {
		    if (path.size() == arr.length) { //first path.size is [], when p=[A, B, C], add to ret
		        ret.add(path);      //[[A, B, C]] , then go to line 195 . [A, C, B]; [[A, B, C], [A, C, B]]
		        return;
		    }
		    for (int i = 0; i < arr.length; i++) {
		        if (visited[i] || (i > 0 && arr[i] == arr[i-1] && !visited[i-1])) {
		            continue;   // remove duplicates
		        }
		        List<Character> p = new ArrayList<>(path); //copy path to new list p:...., [A, C]
		        p.add(arr[i]);   //i = 0: p=[A], i = 1: p=[A, B], i = 2: p=[A, B, C]; i = 2: p = [A, C], i = 1:[A, C, B]; 
		                         //i=1: p = [B] , i = 0: [B, A]
		        
		        visited[i] = true;   // ....[true, false, true]
		        dfs(arr, visited, p, ret);  //recursive  
		        visited[i] = false;  // recover - this line is executed after [A, B, C]] is added to ret line 184
		    }
		}
		
		
	  public static void main(String[] args) { 		  
		  
		  char str[] = {'A', 'B', 'C'}; 
	//	  permutation(str, 0, str.length);
		  
	/*	  String str1 = "ABC"; 
		  char[] arr = str1.toCharArray();
		  permutation(arr, 0, arr.length );
		  
		  permute(str1, 0, str1.length());  // ABC ACB BAC BCA CBA CAB
	*/		
		 // String str2 = "ABCA"; 
		//  permute(str2, 0, str2.length());
		  
		  
		String str3 = "ABC"; 
		System.out.println(permute_Recursive(str3));
	  
	  } 
}




