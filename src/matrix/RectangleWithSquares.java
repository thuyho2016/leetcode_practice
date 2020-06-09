package matrix;

/*
 * 1240. Tiling a Rectangle with the Fewest Squares (hard level)
 * https://leetcode.com/problems/tiling-a-rectangle-with-the-fewest-squares/
 * 
 * 
 * Best solution
 * https://leetcode.com/problems/tiling-a-rectangle-with-the-fewest-squares/discuss/417654/6ms-clean-Java-backtrack-solution-with-detailed-explanation
 * https://leetcode.com/problems/tiling-a-rectangle-with-the-fewest-squares/discuss/415344/Java-Solution-without-cheating
 * */
public class RectangleWithSquares {
	
	//using a boolean[][] to store the status of the Rectangle, cut some situation when current num of squares > current min result
	public int tilingRectangle(int n, int m) {
        boolean[][] grid = new boolean[n][m];
        return put(grid, 0, m*n);
    }
    
    public int put(boolean[][] grid, int count, int cur_min) {
        int i = 0, j = 0;
        if(count >= cur_min) return cur_min;
        
        //find input position
        for(i = 0; i < grid.length; i++){
            for(j = 0; j < grid[0].length; j++){
            	
                if(!grid[i][j]){
                	
                    int ret = Integer.MAX_VALUE;
                    int s = Math.min(grid.length - i, grid[0].length - j);
                    
                    for(int k = s; k >= 1; k--) {
                        if(filled(grid, k, i, j, true)){
                            ret = Math.min(ret, put(grid, count+1, Math.min(ret, cur_min)));
                            //backtrack
                            filled(grid, k, i, j, false);
                        }
                    }
                    return ret;
                }
            }
        }
        //all filled
        return count;
    }
    
    public boolean filled(boolean[][] grid, int s, int i, int j, boolean f){
        int count = 0;
        
        for(int a = 0; a < s; a++){
            for(int b = 0; b < s; b++){
            	
                if(grid[i+a][j+b] == f)
                    return false; 
            }
        }
        
        for(int a = 0; a < s; a++){
            for(int b = 0; b < s; b++){
                grid[i+a][j+b] = f;
            }
        }
        return true;
    }
}
