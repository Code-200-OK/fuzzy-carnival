package linkedlist;

/***Given a linked list, swap every two adjacent nodes and return its head.***/
public class SwapNodeInPairs {
	public ListNode swapPairs(ListNode head) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode cur = dummy;
		ListNode one,two;
		while(cur.next!=null && cur.next.next!=null){
			one = cur.next;
			two = one.next;
			cur.next = two;
			one.next = two.next;
			two.next = one;
			cur = one;
		}
		return dummy.next;
	}
}
/***TC**:  O(n)
**Method**: Use dummy. Carry on till there are two more which need to be swapped.*/