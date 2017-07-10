package linkedlist;
/*You are given two non-empty linked lists representing two non-negative integers. 
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 *  Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8*/

// Recursion
public class Add2ReversedLinkedLists {
	    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	        return sum(l1,l2,0);
	    }
	    private ListNode sum(ListNode one, ListNode two, int c){
	        if(one!=null && two!=null){
	            int sum = one.val + two.val + c;
	            ListNode t = new ListNode(sum%10);
	            t.next = sum(one.next,two.next,sum/10);
	            return t;
	        }
	        if(one==null && two==null){
	            if(c<=0)
	                return null;
	            else
	                {
	                  ListNode t = new ListNode(c%10);
	                  t.next = sum(one,two,c/10);
	                  return t;
	                }
	        }
	        else{
	            if(one==null)
	                c += two.val;
	            else
	                c += one.val;
	             ListNode t = new ListNode(c%10);
	             if(one==null)
	                t.next = sum(one,two.next,c/10);
	             else
	                t.next = sum(one.next,two,c/10);
	           return t;    
	        }
	    }
	}
	// Iterative
/*	public class Solution {
		public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
			ListNode head = null;
			ListNode current = null;
			int c = 0,s;
			ListNode t;
			while(l1!=null || l2!=null || c!=0){
				if(l1!=null){
					c+=l1.val;
					l1 = l1.next;
				}
				if(l2!=null){
					c+=l2.val;
					l2 = l2.next;
				}
				t = new ListNode(c%10);
				c = c/10;
				if(head==null){
					head = t;
					current = t;
				}
				else{
					current.next = t;
					current= t;
				}
			}
			return head;
		}
	}*/


/*TC: O(m+n)
Method: Recursive: taken carry and the two nodes as input. Following cases arise:
Both noth null
Both null and carry zero
both null and carry not zero
one of them null. Handle them separately and proceed.
Iterative: Loop on till either carry exists or any of the two head nodes. 
Store the head separtely to return at last.
 */