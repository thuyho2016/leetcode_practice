package binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * 429. N-ary Tree Level Order Traversal
 * https://leetcode.com/problems/n-ary-tree-level-order-traversal/
 
 Given an n-ary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example, given a 3-ary tree:

              1
           /  |  \
          3   2   4
        /  \
       5    6
 
We should return its level order traversal:

[
     [1],
     [3,2,4],
     [5,6]
]
 */
public class NaryTree_PrintLists_LevelOrder_BFS {


	public static List<List<Integer>> levelOrder(NaryTree root) {
       List<List<Integer>> result = new ArrayList<>();
        
       if (root == null) return result;
		
       Queue<NaryTree> queue = new LinkedList<>();
       queue.add(root);
		
       while (!queue.isEmpty()) {
           int levelSize = queue.size(); // 1 ,3, 2
           
           List<Integer> currentList = new ArrayList<>();
          
           for (int i = 0; i < levelSize; i++) {
        	   NaryTree node = queue.poll(); //remove the head of the queue
               
        	   currentList.add(node.val); // [1]; [3, 2, 4]]; [5, 6]
               
               if (node.children != null) {
                   queue.addAll(node.children); //childreb nodes 3, 2, 4 are added to the queue
               }								//after children 5, 6 of node 3 added to queue, the queue has [2,4,5,6]
            
           }
           result.add(currentList); //[[1], [3, 2, 4]]; [[1], [3, 2, 4], [5, 6]]
       }
       return result;
   }

	public static void main(String[] args) {
	   
		NaryTree n5 = new NaryTree(5, null);
		NaryTree n6 = new NaryTree(6, null);
		List<NaryTree> children2 =  new ArrayList<NaryTree>();
		children2.add(n5);
		children2.add(n6);
		NaryTree n3 = new NaryTree(3, children2);
		
		
		NaryTree n2 = new NaryTree(2, null);
		NaryTree n4 = new NaryTree(4, null);
		
		List<NaryTree> children = new ArrayList<NaryTree>();
		children.add(n3);
		children.add(n2);
		children.add(n4);
	
		NaryTree root = new NaryTree(1, children);
		
		System.out.print("Childs of root " );
		List<NaryTree> nodes = root.getChildren();
		for (NaryTree node: nodes) {
			System.out.print(node.val + " ");
		}
		System.out.println();
		
		List<List<Integer>> out = levelOrder(root);
		System.out.println(out);
	}
}
