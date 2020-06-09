package binarytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/* 
 * Deserialize iList to N-ary Tree ( Apple, Uber interview)
 * 
 * Input: a list of list, each list has {parent id, child id}
 *  
 *  int[][] iList = new int[][] { {9,3}, {11, 8}, {8,7}, {4,6}, {11,9}, {9,1}, {11,4}};
 * 
 * 
 * Output:
 *     		 11
 *      /	 |	  \
 *     9     8      4
 *   /  \	  \		 \
 *  1    3     7     6
 *   
 *  
 * Return  Level order traversal as
 *  [ [11]
 *    [9, 8, 4] , 
 *    [1, 3,7, 6]
 *  ]
 *    
 */
public class ConvertListOfListToNaryTree {

	//sort to get root is largest one
	//{11,9}, {11, 8}, {11,4}, {9,3}, {9,1}, {8,7}, {4,6}};
	
	public static int[][] sortParent(int[][] iList) {
       // sort in ascending
       // Arrays.sort(iList, (a,b) -> Integer.compare(a[0], b[0]));
		
		//sort in descending
	   // Arrays.sort(iList, (a,b) -> b[0] - a[0]);		
		
		//Arrays.sort(iList, new ParentIDComparator());
		
		Arrays.sort(iList, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2 ) {
				return o2[0] - o1[0];
			}
		});
        return iList;
		
	}	
  
	// if input is list of List , then write comparator class
	//or use Collections.sort(iList, new ParentIDComparator());
	public static class ParentIDComparator implements Comparator<Object>{
        public int compare(Object o1, Object o2){
        	MyTree t1 = (MyTree)o1;
        	MyTree t2 = (MyTree)o2;
        	
            return t1.parentID - t2.parentID;  //use compareTo if it is String
        }
	}

	//convert iList to tree - use HashSet to keep track visit, when each number is visited and add to visited list
	public static MyTree constructTree(int[][] pairs) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        HashSet<Integer> visited = new HashSet<>();

        for(int[] p : pairs) {
            map.computeIfAbsent(p[0], k -> new ArrayList<>()).add(p[1]);
        }

        MyTree root = new MyTree(pairs[0][0]);
        
        dfs(root, map, visited);   //{4=[6], 8=[7], 9=[3, 1], 11=[8, 9, 4]}
        return root;
    }

    private static void dfs(MyTree root, HashMap<Integer, List<Integer>> map, HashSet<Integer> visited) {
        
    	visited.add(root.parentID);   //[11] , visit 8, add to visited list [8, 11], [7, 8, 11]
        
        if (root.children == null) {
            root.children = new ArrayList<>();
        }

        if (map.containsKey(root.parentID)) {  //11, 8
        	List<Integer> children = map.get(root.parentID);  //[8, 9, 4] ; parentID =8 so children = [7] 
            for (Integer n : children) { // get child 8, then child 7 of parent 8
                
            	if (!visited.contains(n)) {
                    MyTree next = new MyTree(n); 
                    root.children.add(next);
                    dfs(next, map, visited);   //recursive
                }
            }
        }
    }
	
	//print tree in level order traversal - BFS and return a list of list
	public static List<List<Integer>> printLists_levelOrder(MyTree root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
		
        Queue<MyTree> queue = new LinkedList<>();
        queue.add(root);
		
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> currentList = new ArrayList<>();
           
            for (int i = 0; i < size; i++) {
            	MyTree node = queue.poll();
                currentList.add(node.parentID); //add root
                
                if (node.children != null) {
                    queue.addAll(node.children);
                    
                }
            }
            result.add(currentList);
        }
        return result;
    }
	

	public static void main(String[] args) {		 
		
		int[][] pairs = {{9,3}, {11, 8}, {8,7}, {4,6}, {11,9}, {9,1}, {11,4}};
		
		int[][] sorted = sortParent(pairs);
		System.out.println("sort in order: " + Arrays.deepToString(sorted));
		
		MyTree tree = constructTree(pairs);
		List<List<Integer>> out = printLists_levelOrder(tree);
		System.out.println("Display N-ary Tree in level traversal" + out);
		
	}
}
