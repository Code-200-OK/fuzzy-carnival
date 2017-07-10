package linkedlist;

/***Reverse a singly linked list.***/
public class ReverseLinkedList {
	public ListNode reverseList(ListNode head) {
		/* iterative solution */
		ListNode newHead = null;
		while (head != null) {
			ListNode next = head.next;
			head.next = newHead;
			newHead = head;
			head = next;
		}
		return newHead;
	}
}
/***TC**: O(n)
**Method**: Start with tail = null. for every node add the tail to its last. 
Now head of this becomes the tail for next one.*/
