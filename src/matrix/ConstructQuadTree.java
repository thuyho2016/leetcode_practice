package matrix;

/* 427. Construct Quad Tree
 * https://leetcode.com/problems/construct-quad-tree/

We want to use quad trees to store an N x N boolean grid. Each cell in the grid can only be true or false. The root node represents the whole grid. For each node, it will be subdivided into four children nodes until the values in the region it represents are all the same.

Each node has another two boolean attributes : isLeaf and val. isLeaf is true if and only if the node is a leaf node. The val attribute for a leaf node contains the value of the region it represents.

Your task is to use a quad tree to represent a given grid. The following example may help you understand the problem better:

Given the 8 x 8 grid below, we want to construct the corresponding quad tree:
....


It can be divided according to the definition above:

...


The corresponding quad tree should be as following, where each node is represented as a (isLeaf, val) pair.

For the non-leaf nodes, val can be arbitrary, so it is represented as *.



 */
public class ConstructQuadTree {
	
	static class Node {
	    public boolean val;
	    public boolean isLeaf;
	    public Node topLeft;
	    public Node topRight;
	    public Node bottomLeft;
	    public Node bottomRight;

	    public Node() {}

	    public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
	        val = _val;
	        isLeaf = _isLeaf;
	        topLeft = _topLeft;
	        topRight = _topRight;
	        bottomLeft = _bottomLeft;
	        bottomRight = _bottomRight;
	    }
	}
	
	/**
  Solution:
	check to see if the grid is a leaf by checking if ALL cells are equal
	if it is a leaf return a leaf node
	
	if it's not a leaf:
		divide them into four equal regions (top left, top right, bottom left, bottom right)
		check if each region is leaf node
		attach result of constructNode of each region to the node
		
	return node
	
	 */
	 public static Node construct(int[][] grid) {
	        if(grid.length == 0) return null;
	        return constructNode(grid, 0, 0, grid.length);
	    }
	    
	  private  static Node constructNode(int[][] grid, int x, int y, int size) {
	        Node node = new Node();
	        
	        node.isLeaf = isLeaf(grid, x, y, size);
	        
	        if(node.isLeaf) {
	            node.val = grid[y][x] == 1;
	            return node;
	        }
	        
	        int newSize = size / 2;
	        node.val = true;
	        
	        //4 equal regions - recursive to check if each region is leaf
	        node.topLeft = constructNode(grid, x, y, newSize);
	        node.topRight = constructNode(grid, x + newSize, y, newSize);
	        node.bottomLeft = constructNode(grid, x, y + newSize, newSize);
	        node.bottomRight = constructNode(grid, x + newSize, y + newSize, newSize);
	        return node;
	    }
	    
	   
	  static boolean isLeaf(int[][] grid, int x, int y, int size) {
	        if(size == 1) return true;
	        int val = grid[y][x];
	        
	        for(int i = y; i < y + size; i += 1) {
	            for(int j = x; j < x + size; j += 1) {
	                if(val != grid[i][j]) return false;
	            }
	        }
	        return true;
	    }
	  

		 public static Node construct2(int[][] grid) { 
			 Node ret = build(grid, 0, 0, grid.length - 1, grid.length - 1);
			 return ret;
		 }

		 private static Node build(int[][] grid, int r1, int c1, int r2, int c2 ) {
	        if (r1 > r2 || c1 > c2) return null;
	        
	        boolean isLeaf = true;
	        int val = grid[r1][c1];
	        
	        for (int i = r1; i <= r2; i++)
	            for (int j = c1; j <= c2; j++)
	                if (grid[i][j] != val) {
	                    isLeaf = false;
	                    break;
	                }
	        
	     // If current block is identified as Leaf Node then return it.
	        if (isLeaf)
	        	return new Node(val == 1, true, null, null, null, null);
	        
	        int rowMid = r1 + (r2 - r1) / 2;
	        int colMid = c1 + (c2 - c1) / 2;
	        
	     // Break current block into four parts and construct the Node.
	        
	        return new Node(false, false,
	            build(grid, r1, c1, rowMid, colMid),     //top left 
	            build(grid, r1, colMid + 1, rowMid, c2),//top right
	            
	            build(grid, rowMid + 1, c1, r2, colMid),//bottom left 
	            build(grid, rowMid + 1, colMid + 1, r2, c2));//bottom right
		 }  

   
	public static void main (String[] agrs) {
		int[][] grid = {
					{1,1,1,1,0,0,0,0},
				  {1,1,1,1,0,0,0,0},
				  {1,1,1,1,1,1,1,1},
				  {1,1,1,1,1,1,1,1},
				  {1,1,1,1,0,0,0,0},
				  {1,1,1,1,0,0,0,0},
				  {1,1,1,1,0,0,0,0},
				  {1,1,1,1,0,0,0,0 }};
		
		for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
            	System.out.print(grid[i][j] + " ");
            }
			System.out.println("");
		}
		
		construct(grid);
	}

}
