package linkedList;

/* 1290. Convert Binary Number in a Linked List to Integer
 * https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer/

Given head which is a reference node to a singly-linked list. The value of each node in the linked list is either 0 or 1. The linked list holds the binary representation of a number.

Return the decimal value of the number in the linked list.


Example 1:   1 --> 0 --> 1

Input: head = [1,0,1]
Output: 5
Explanation: (101) in base 2 = (5) in base 10


Example 2:

Input: head = [0]
Output: 0

 */

public class ConvertLinkListToDecimal_Integer {
	
	//best solution - hackerrank - use long sum for long linkedlist
	public static int getDecimalValue2(ListNode head) {
	  long sum = 0;
      while (head != null) {
          sum = sum * 2 + head.val;  // sum = 1 , 2 , 5 = ( 2 * 2 + 1)
          head = head.next;
      }
      return (int) sum; 
	}
		
		
    // use parseInt
	public static int getDecimalValue(ListNode head) {
		String value = "";
		while (head != null) {
			value += head.val;
			head = head.next;
		}
		return Integer.parseInt(value,2);
	}
	
	
	
    public static void printNodes(ListNode head){
    	if (head == null) {
    		return;
    	}
    	ListNode cur = head;
    	
    	while ( cur != null) {
    		System.out.print(cur.val + "->");
    		cur = cur.next;
    	}
    	System.out.println(cur); 
    }

    public static void main(String[] args) {
    	// [1,0,1]
    	ListNode head = new ListNode(1);
		head.next = new ListNode(0);	
		head.next.next= new ListNode(1);
		printNodes(head);
		
		System.out.println(getDecimalValue2(head)); //5
		
		System.out.println(getDecimalValue(head)); //5
    }	
		
	
}


