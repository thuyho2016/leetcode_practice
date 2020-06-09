package binarytree;
import java.util.ArrayList;
import java.util.List;


/*
given the input is 2 trees (not binary tree) and a value of one of the nodes in the first tree. 
The 2 trees have the same structure but different values. Return the corresponding value of the second tree.


*/

public class ValueSecondTree {
	
	public int getValueOfSecondTree(NaryTree first, NaryTree second, int firstVal) {
		return firstVal;
	
	}
	
	 public static void main(String a[]){		 
	    /** build Tree 1
	         1
	     3   2  4
		*/ 
		NaryTree root = new NaryTree(1);
		
		NaryTree n3 = new NaryTree(3);
		NaryTree n2 = new NaryTree(2);
		NaryTree n4 = new NaryTree(4);
		
		List<NaryTree> children = new ArrayList<>();
		children.add(n3);
		children.add(n2);
		children.add(n4);
		root.setChildren(children);
			
		 // build Tree 2
		NaryTree root2 = new NaryTree(6);
		NaryTree child1 = new NaryTree(7);
		NaryTree child2 = new NaryTree(8);
		NaryTree child3 = new NaryTree(9);
		
		List<NaryTree> children2 = new ArrayList<NaryTree>();
		children2.add(child1);
		children2.add(child2);
		children2.add(child3);
		
		root2.setChildren(children2);
		
	 }
}
	      
	
	
	