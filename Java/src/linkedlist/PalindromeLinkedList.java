package linkedlist;

/***Given a singly linked list, determine if it is a palindrome***/
public class PalindromeLinkedList {
	public boolean isPalindrome(ListNode head) {
		// use fast and slow to find the middle
		ListNode slow=head,fast=head;
		while(!(fast==null || fast.next==null)){
			slow = slow.next;
			fast = fast.next.next;
		}
		if(fast!=null)
			slow = slow.next;
		fast = head;
		//reverse the right half
		ListNode tail = null,temp;
		while(slow!=null){
			temp = slow.next;
			slow.next = tail;
			tail = slow;
			slow = temp;
		}
		//compare both
		while(tail!=null && fast.val==tail.val){
			fast = fast.next;
			tail = tail.next;
		}
		return tail==null;
	}
}
/***TC**: O(n)
**Method**: Use fast and slow pointers to find the head of the second half of the linked list.
Then reverse the second half. Compare both.*/
