package linkedlist;

/***Given a linked list, remove the nth node from the end of list and return its head.**
*/
public class RemoveNthNodeFromTheEnd {
	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode dummy = new ListNode(0);
		ListNode del = dummy;
		ListNode end = head;
		dummy.next = head;
		for(int i=1;i<n;i++)end=end.next;
		while(end.next!=null){
			end = end.next;
			del = del.next;
		}
		del.next = del.next.next;
		return dummy.next;
	}
}
/***TC**: O(n)
**Method**: Use a dummy node and use two pointers to intially set the right pointer at nth element in the list. then move both the pointers simulataneosly forward until the right reaches the last element.
	At this point you have your left at the parent of the node to be deleted.*/