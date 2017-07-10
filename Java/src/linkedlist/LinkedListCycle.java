package linkedlist;

import java.util.HashSet;
import java.util.Set;

/***Given a linked list, determine if it has a cycle in it.***/

//                      APPROACH 1
public class LinkedListCycle {
	public boolean hasCycle(ListNode head) {
		if (head == null || head.next == null) {
			return false;
		}
		ListNode slow = head;
		ListNode fast = head;
		while (!(fast==null || fast.next==null)) {

			slow = slow.next;
			fast = fast.next.next;
			if(fast==slow)
				return true;
		}
		return false;
	}	
}
/***TC**: O(n+k) n: non cyclic length k:cyclic length:
 **SC**: O(1)
 **Method**: Runner & a Walker. Both are set at the head. Runner jumps two steps. 
 *Go on till runner has another chance to jump. Inside the loop, make them both jump,
 * if both land at the same place then say true, else after the end of all iterations,
 *  say false;

 */
/*
 * 			Approach 2:
 * public boolean hasCycle(ListNode head) {
    Set<ListNode> nodes = new HashSet<ListNode>();
    nodes.add(null);
    while(!nodes.contains(head)){
    	nodes.add(head);
    	head = head.next;
    }
	return head == null;
}*/
