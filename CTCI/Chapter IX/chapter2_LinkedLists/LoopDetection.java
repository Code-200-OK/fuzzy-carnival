package chapter2_LinkedLists;
public class LoopDetection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode r1 = new ListNode(2);
		ListNode r2 = new ListNode(3);
		ListNode r3 = new ListNode(4);
		ListNode r4 = new ListNode(5);
		
		r1.next = r2;
		r2.next = r3;
		r3.next = r4;
		r4.next = r2;
		System.out.println(loop(r1).val);
 	}
	public static ListNode loop(ListNode n){
		ListNode slow = n;
		ListNode fast = n.next;
		while(slow!=fast){
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

}
