
import java.util.*;

/*
 * Sliding Blocks - a type of puzzle in which a na N by M rectangular grid contains a single blank space
 * and (N*M) -1 numbered tiles.
 * The goal of the puzzle is to arrange the puzzle, the numbers are ordered Left-to-Right, Top-to-bottom, with the empty space in the top left. 
 * 
 * 1 6 3     1 6 3     0 1 2
 * 8 7 2  -> 8 0 2     3 4 5
 * 4 0 5     4 7 5     6 7 8
 */

public class ArrangePuzzle {

    public static int[][] puzzle(int[][] input) {
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    
    	
    	
    	//convert List to int[][]
		return result.toArray(new int[result.size()][]);
    }
    
    public static void main (String[] agrs) {
    	int [][] input = { {1, 6, 3}, {8, 7, 2}, {4, 0, 5} };
    	
    	int[][] output = puzzle(input);
		System.out.println(output);
		
	
    }
}