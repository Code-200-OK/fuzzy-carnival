package linkedlist;

/***Intersection of Two Linked Lists***/
public class IntersectionOfTwoLinkedLists {
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		//boundary check
		if(headA == null || headB == null) return null;

		ListNode a = headA;
		ListNode b = headB;

		//if a & b have different len, then we will stop the loop after second iteration
		while( a != b){
			//for the end of first iteration, we just reset the pointer to the head of another linkedlist
			a = a == null? headB : a.next;
			b = b == null? headA : b.next;    
		}

		return a;
	}
}
/***TC**: O(n+m)
 **Method**: Start by putting two pointers on both the heads. Move both forward. When 
each of them reaches their null, shift them to other one's head. In that manner, the
longer's pointer will exhaust itself and point to shorter's head and by that time
shorter would have tarversed itself plus the remaining number of elements in the 
long.*/

/*
public class IntersectionOfTwoLinkedLists {
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		ListNode p1 = headA, p2 = headB;
		int len1 = 0, len2 = 0;
		while (p1 != null) {
			p1 = p1.next;
			len1++;
		}
		while (p2 != null) {
			p2 = p2.next;
			len2++;
		}
		p1 = headA;
		p2 = headB;
		if (len1 > len2) {
			for (int i = 0;i < len1 - len2; i++) {
				p1 = p1.next;
			}
		} else {
			for (int i = 0;i < len2 - len1; i++) {
				p2 = p2.next;
			}
		}
		while (p1 != p2) {
			p1 = p1.next;
			p2 = p2.next;
		}
		return p1;
	}
}*/
/***TC**: O(n+m)
 **SC**: O(1)
 **Method**: Find the lengths. Move the largest of both diff number of times forward.
then keep on moving till both are not equal. If they break at null, then null is the 
answer else the node is the answer.

Approach 2:
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if( null==headA || null==headB )
			return null;

		ListNode curA = headA, curB = headB;
		while( curA!=curB){
			curA = curA==null?headB:curA.next;
			curB = curB==null?headA:curB.next;
		}
		return curA;
    }
 */
