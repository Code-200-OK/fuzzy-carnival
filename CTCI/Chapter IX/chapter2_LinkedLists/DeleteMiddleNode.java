package chapter2_LinkedLists;
public class DeleteMiddleNode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode head = new ListNode(1);
		ListNode one = new ListNode(2);
		ListNode two = new ListNode(2);
		ListNode thr = new ListNode(3);
		ListNode fou = new ListNode(2);
		ListNode fiv = new ListNode(1);
		ListNode six = new ListNode(5);
		head.next = one;
		one.next = two;
		two.next = thr;
		thr.next = fou;
		fou.next = fiv;
		fiv.next = six;
		//ListNode head = new LinkedListt(new int[]{1,2,3,4,5,6,7,8}).getHead();
		LinkedListt obj = new LinkedListt();
		obj.show(head);
		remove(head);
		obj.show(head);
	}
	public static void remove(ListNode middle){/*
		ListNode tail = middle;
		ListNode cur = middle.next;
		while(cur.next != null){
			tail.val = cur.val;
			tail= cur;
			cur = cur.next;		
		}
		tail.val = cur.val;
		tail.next = null;	*/
		while(middle.next.next !=null){
			middle.val = middle.next.val;
			middle = middle.next;
		}
		middle.val = middle.next.val;
		middle.next = null;
	}

}
