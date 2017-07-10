package linkedlist;

/*Merge two sorted linked lists and return it as a new list. 
The new list should be made by splicing together the nodes of the first two lists.
*/

public class MergeTwoSortedLinkedLists {
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if(l1==null||l2==null) return l1==null?l2:l1;
        if(l1.val < l2.val){
        	l1.next = mergeTwoLists(l1.next,l2);
        	return l1;
        }
        else{
        	l2.next = mergeTwoLists(l1,l2.next);
        	return l2;
        }
	}
}

/*TC: O(m+n). total number of nodes.
Method: if any one of them null then return other. 
Pick the smallest of the heads of both as a new head. 
Now till both are not nulls iterate and keep updating the 
head's next and head. After end, join the remaining to the head's next.*/
						
/*						ITERATIVE
public class MergeTwoSortedLinkedLists {
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if(l1==null||l2==null) return l1==null?l2:l1;
		ListNode head = null;
		if(l1.val<=l2.val) {
			head=l1;
			l1=l1.next;
		}
		else {
			head=l2;
			l2=l2.next;
		}
		ListNode temp = head;
		while(l1!=null&&l2!=null){
			if(l1.val<=l2.val) {
				temp.next=l1;
				l1=l1.next;
			}
			else {
				temp.next=l2;
				l2=l2.next;
			}
			temp=temp.next;
		}
		temp.next=l1==null?l2:l1;
		return head;
	}
}*/