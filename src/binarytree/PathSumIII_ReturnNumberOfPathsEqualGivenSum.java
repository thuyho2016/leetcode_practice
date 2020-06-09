package binarytree;
import java.util.HashMap;
import java.util.Map;

/*
 * 437. Path Sum III ( easy level)
https://leetcode.com/problems/path-sum-iii/
 
You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11

Solution:

for each parent node in the tree, we have 2 choices:
1. include it in the path to reach um.
2. not include it in the path to reach sum. 

NOTE: every node in the tree can only try to be the start point once.


for example, When we try to start with node 1, node 3, as a child, could choose to start by itself.
             Later when we try to start with 2, node 3, still as a child, 
             could choose to start by itself again, but we don't want to add the count to result again.
     1
      \
       2
        \
         3
        
    
        
The pathSum mehtod basically iterate to each node and add everything up.
 The helper method (isMeetSum method) find the number of solutions that starting at the root.
 
 */


public class PathSumIII_ReturnNumberOfPathsEqualGivenSum {
 
	//The solution is easy to understand, but does a lot for repeated calculation. Other solution: Hash map
	public static int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        
        int curCount = pathSumHelper(root, sum);
       
        return curCount + pathSum(root.left, sum) + pathSum(root.right, sum); //add case when root is excluded.
     
	}
	
	//2.Recursive DFS - Brute force to check if Sum is meet	
	//Time: O(n^2) in worst case (no branching); O(nlogn) in best case (balanced tree).
	//Space: O(n) due to recursion.
	private static int pathSumHelper(TreeNode root, int sum) {
		
		if (root == null) return 0;
		
		int count = 0;
		if (root.val == sum ) count++;
		
		//I need to continue traverse the tree , so I do recursive call with remaining value in left subtree and right subtree
		int leftPath_Count = pathSumHelper(root.left, sum - root.val);
		int rightPath_Count = pathSumHelper(root.right, sum - root.val);
		
		return count + leftPath_Count + rightPath_Count;
	}
	
	
	//Way 2: Hashmap     
    Map<Integer, Integer> map = new HashMap(); //Current sum : # of occurrences 
    int sums = 0; //path sum counter
    
	//Use a hashmap to map the current sum at each node to the number of times 
	//that sum occurs. Then at each node, calculate the difference between the current
	//sum and the target sum. If there exists such a key in the map, increment the counter
	//by the value of the key.
	
	//**Current sum = the sum of the node plus all of its ancestors
	
    public int pathSum2(TreeNode root, int sum) {
        if(root == null) {
            return 0;
        }
        
        map.put(0, 1);  //A 0 indicates that the current sum == the target
        getSum(root, 0, sum);
        return sums;
    }
	    
	    
	    
    void getSum(TreeNode node, int currSum, int sum) {
        
        if(node == null) {
            return;
        }
        
        currSum += node.val; 
        int diff = currSum - sum; 
        
      //Checking to see if the difference is a previous current sum 
        if(map.containsKey(diff)) {
            sums += map.get(diff);
        }
        
		//If the current sum already exists, increment its value, otherwise add to map with 
		//a value of 1
        int k = map.getOrDefault(currSum, 0);
        map.put(currSum, k+1);
        
        //Recurse right and left subtrees
        getSum(node.left, currSum, sum);
        getSum(node.right, currSum, sum);
        
		//Backtrack
	        map.put(currSum, map.get(currSum)-1);
	        
	    }
   

    public static void main(String[] args) {
    	//Input: []
		TreeNode root = new TreeNode(10);
		root.left = new TreeNode(5);
		root.right = new TreeNode(-3);
		
		// left subtree
		root.left.left = new TreeNode(3); 
		root.left.right = new TreeNode(2); 
		root.left.right.right = new TreeNode(1); 
		root.left.left.left = new TreeNode(3); 
		root.left.left.right = new TreeNode(-2); 
		
		//right subtree
		root.right.right = new TreeNode(11);		
		
		System.out.println("Sum paths: " +  pathSum(root, 8)); // 3
		
    }
}