package binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * 589. N-ary Tree Preorder Traversal
 * https://leetcode.com/problems/n-ary-tree-preorder-traversal/
 * 
 * For example, given a 3-ary tree:

              1
           /  |  \
          3   2   4
        /  \
       5    6
 * 
 * Return its preorder traversal as: [1,3,5,6,2,4].
 * 
 */
public class NaryTree_PrintLists_PreOrder_DFS {
	
	//use recursive - easy to understand
	public static List<Integer> preorder2(NaryTree root) {
		
        List<Integer> res = new ArrayList<Integer>();
        
        if (root == null) return res;
        
        res.add(root.val); // add value of root
        
        if (root.children != null) {  
	        for (NaryTree cur: root.children) {
	            res.addAll(preorder2(cur));  // recursive
	        }
        }
        return res;
    }
	
	

	// use Stack to add all nodes
	//1. add root to list
	//2. go through children of root, push them to stack
	//3. pop top element from stack to the list  
	public static List<Integer> preorder(NaryTree root) {
		Stack<NaryTree> s = new Stack<>();
	    List<Integer> res = new ArrayList<>();        
	    
	    if (root == null) return res;
	    s.add(root);  //add all nodes to stach
	
	    while(!s.isEmpty()) {
	    	NaryTree curr = s.pop(); // pop the top node from stack
	        res.add(curr.val);  //add value of root to the list
	        
	        if (curr.children != null) {  //[3, 2, 4]. when curr.val = 3, children = [5,6]
	            for(int i = curr.children.size() - 1; i >= 0; i--) { //i = 2
	                s.push(curr.children.get(i)); //so, get 4 from tree [3, 2, 4]
	            }
	        }
	    }
	    return res;
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
		
		System.out.print("Childs of node3: " );
		List<NaryTree> nodes = n3.getChildren();
		for (NaryTree node: nodes) {
			System.out.print(node.val + " ");
		}
		System.out.println();
		
	//	List<Integer> out = preorder(root);
	//	System.out.println(out);
		
		List<Integer> out2 = preorder2(root);
		System.out.println(out2);
	}
}
