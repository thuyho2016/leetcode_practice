package binarytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * 297. Serialize and Deserialize Binary Tree ( hard level) 
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 * 
 * 449. Serialize and Deserialize BST
 * https://leetcode.com/problems/serialize-and-deserialize-bst/

Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer,
or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. 
You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Example: 

You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"

1. serialize(TreeNode root) - encode a binary tree to a string
2. deserialize(String data) - decode string to the original tree structure.

note:
Serialization : storing a given tree in a file or an array
Deserialization: reverse  of serialization

 */
public class SerializeDeserializeBT {
	
	//Way1:  Use recursive to encodes/Convert a tree to String
    public String serialize(TreeNode root) {
    	if (root == null) {
            return "";
        }
    	
        StringBuilder sb = new StringBuilder();
        
        serializeHelper(sb, root);  //recursive
        sb.deleteCharAt(sb.length() - 1);   //remove the last ","
        
        //System.out.println(sb.toString());
        return sb.toString();
    }
        
    public void serializeHelper(StringBuilder sb, TreeNode root) {
    	
    	//if no left child or no right child
        if (root == null) {
            sb.append("null").append(","); //sb.append("#").append(",");
        } else {
            sb.append(root.val);
            sb.append(",");
            serializeHelper(sb, root.left);   // make a recursive call for left child and right child
            serializeHelper(sb, root.right);
        }
    }
    
    
    //Way2: Use BFS to convert Tree to String: 1,2,3,null,null,4,5,null,null,null,null,
    public String serialize2(TreeNode root) {
        if (root == null) return "";
        
        String n = "null", seperator = ",";
        
        Queue<TreeNode> dq = new LinkedList<>();
        dq.offer(root);
        
        int size = 0;
        TreeNode cur;
        StringBuilder sb = new StringBuilder();
        
        while (!dq.isEmpty()) {
            size = dq.size();
            
            for (int i = 0; i < size; ++i) {
                cur = dq.poll();
                
                if (cur != null) {
                    sb.append(cur.val);
                    dq.offer(cur.left);  // insert left node to the queue
                    dq.offer(cur.right);   // insert left node to the queue
                } else {
                    sb.append(n);
                }
                sb.append(seperator);
            }
        }
        
        sb.deleteCharAt(sb.length() - 1);  // remove last ,
        return sb.toString();
    }

    
    //Use Queue & Recursive -  Decodes your encoded data from String to Tree.
    public TreeNode deserialize(String data) {
       if (data.isEmpty()) return null;	 // check empty String
    	
       Queue<String> q = new LinkedList<String>();
       
       String[] strSplit = data.split(","); //convert string to array
       for (String s : strSplit) {         // add each element to queue
           q.add(s);
       }
      
       return deserializeHelper(q); // put array to queue = [1, 2, null, null, 3, 4, null, null, 5, null, null]
    }
    
    //recursive for left node and right node
    public TreeNode deserializeHelper(Queue<String> q) {
        String currNode = q.poll(); // remove the head node from queue
        
        if (currNode.equals("null")) {   //curNode.equals("#")
            return null;
        
        } else {
            TreeNode newNode = new TreeNode(Integer.parseInt(currNode)); //convert string to integer (Integer.valueOf(s); , create a new node
            newNode.left = deserializeHelper(q);   // create left node
            newNode.right = deserializeHelper(q);  // create child node
            return newNode;
            
        }
    }

    
    //print Binary Tree by DFS PreOrder (Root - Left - Right), return List<Integer>
    public static List<Integer> printTree_preorder(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        dfs_preorder(root, res);
        return res;
    }
    
    public static void dfs_preorder( TreeNode root, List<Integer> result) {
    	if(root == null) return;
    	
		result.add(root.val);
    	        
        if (root.left != null) dfs_preorder(root.left, result); // visit all nodes of left subtree
        
        if (root.right != null) dfs_preorder(root.right, result);  // visit all nodes of right subtree     
       
    }
    
    
    public static void main (String[] agrs) {
      	//	Input: [1,2,3,null,null,4,5]
    	
  		TreeNode root = new TreeNode(1);
		root.right = new TreeNode(3);	
  		root.left = new TreeNode(2);
  	   
  		root.right.left = new TreeNode(4);
  		root.right.right  = new TreeNode(5);
  		
  		SerializeDeserializeBT obj = new SerializeDeserializeBT();
  		String data = obj.serialize(root);
		System.out.println("serialize tree to string: " +  data);  //[1,2,3,null,null,4,5]"

		TreeNode des = obj.deserialize(data);
		System.out.println("deserialize String to Tree: " +  des); 
		System.out.println("print BinaryTree in DFS PreOrder: " +  printTree_preorder(des));  // [1, 2, 3, 4, 5]
   
    }
    
}
