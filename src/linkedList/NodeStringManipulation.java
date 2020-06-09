package linkedList;

/*
 * 1. Insert a new node as the first one of linkedlist
 * 2. Delete a new node from the first one of linkedlist 
 * 3. Insert last Node from Linkedlist
 * 4. Deleting the first occurrence of node from a LinkedList
 * 5. print all nodes from linkedlist
 * 6. search a node in the LinkedList
 */
public class NodeStringManipulation
{
	  public String info;
	  public NodeStringManipulation next;
	  
	  public NodeStringManipulation() {
		  this(null,null);
	  }
	  public NodeStringManipulation(String info) {
		  this(null, info);
	  }
	  
	  public NodeStringManipulation(NodeStringManipulation next, String info) {
		  this.info = info;
	        this.next = next;
	  }
	  
	  //1. insert a new element as the fist one of a linkedList
	  public static NodeStringManipulation insertFirst(NodeStringManipulation head, String s) {
		  NodeStringManipulation p = new NodeStringManipulation();     // 1. create a new node to be added
		  p.info = s;                         // 2. assign value to that node
		  p.next = head;                      // 3. point to head of the list
		  head = p;                           // 4. p becomes head node (new list point to p)
		  
		  return head;
		}
	  
	  //2. delete first element from a LinkedList
	  public static NodeStringManipulation deleteFirst(NodeStringManipulation node) {
		  if (node != null)
		    node = node.next;
		  return node;
		}
	  
	  
	  //3. insert last Node from Linkedlist 
	  public static NodeStringManipulation insertLast(NodeStringManipulation head, String data) {
		  if (head == null)
			  return new NodeStringManipulation(data); //create a new node
		  
		  else if ( head.next == null) {
			  head.next = new NodeStringManipulation(data);
		  } else {
			  insertLast(head.next, data);  //using recursive
		  }
		  return head;
		  
	  }
	  
	  //4. Deleting the first occurrence of node from a LinkedList
	  public static NodeStringManipulation delete(NodeStringManipulation node, String s) {
		  NodeStringManipulation p = new NodeStringManipulation();    // create the generator node
		  p.next = node;
		  node = p;

		  boolean found = false;
		  while ((p.next != null) && !found) {
		    if (p.next.info.equals(s)) {
		      p.next = p.next.next;       // delete the element
		      found = true;               // forces exit of the loop
		    } else
		      p = p.next;
		  }

		  return node.next;                // delete generator node
		}
	  
	  //5. print all nodes from linkedlist
	  public static void print(NodeStringManipulation head) {
		  NodeStringManipulation p = head;   // create temporary p to point to head node
		  while (p != null) {
		    System.out.print(p.info + "-->");
		    p = p.next;
		  }
		  System.out.println(p); // this line for print "null"
		}
	  
	  //6. search a node in the LinkedList
	  public static boolean search(NodeStringManipulation node, String s) {
		  while (node !=null ){
			  if (node.info.equals(s)) return true;
			  node = node.next;
		  }
		return false;
		  
	  }
	  
	  public static void main (String[] agrs) {
		  NodeStringManipulation head = new NodeStringManipulation("A");
		  System.out.println("Head node data: " + head.info);  //print the first node data

		  //insert node B to node A 
		  NodeStringManipulation newNode = insertLast(head , "B");
		 //insert node C to node A
		  newNode= insertLast(newNode , "C");
		  
		  //display all nodes
		  print(head);
		  
		  NodeStringManipulation newList = insertFirst(head, "D");
		  System.out.print("Insert D at the first : ");
		  print(newList); // after insertion
		  
		  System.out.print("Delete D from first: ");
		  print(deleteFirst(newList)); //after deletion
		  
		  //insert Last
		 NodeStringManipulation newList2 = insertLast(head, "D"); 
		 System.out.print("Insert D at the last : ");
		 print(newList2);
		 
		 System.out.print("Delete C from Linkedlist: ");
		 print(delete(newList2, "C"));
		  
		
		 boolean isNodeB = search(head, "B"); 
		 System.out.println("Is node B in Linkedlist: " + isNodeB);
		 
		 boolean isNodeE = search(head, "E"); 
		 System.out.println("Is node E in Linkedlist: " + isNodeE);
	  }
}	






