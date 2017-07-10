package chapter2_LinkedLists;
public class Partition {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode head = new LinkedListt(new int[]{5}).getHead();
		LinkedListt obj = new LinkedListt();
		obj.show(head);
		part(head,5);
		obj.show(head);
	}
	public static void part(ListNode head, int k){
		if(head == null) return;
		ListNode leftTail = head;
		while(leftTail != null && leftTail.val < k) leftTail = leftTail.next;
		ListNode cur = leftTail.next;
		while(cur!=null){
			while(cur !=null && cur.val >= k) cur = cur.next;
			if(cur != null){
				int temp = cur.val;
				cur.val = leftTail.val;
				leftTail.val = temp;
				leftTail = leftTail.next;
				cur = cur.next;			
			}		
		}	
	}

}
