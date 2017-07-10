package chapter2_LinkedLists;
public class SumLists {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode one = new LinkedListt(new int[]{9,9,3}).getHead();
		ListNode two = new LinkedListt(new int[]{9}).getHead();
		LinkedListt obj = new LinkedListt();
		obj.show(sumUsingIteration(one,two));

	}
	public static ListNode sumUsingRecursion(ListNode n1, ListNode n2, int carry){
		if(n1==null && n2==null){
			if(carry == 0) return null;
			return new ListNode(carry);		
		}
		int total = carry;
		if(n1 != null) total += n1.val;
		if(n2 != null) total += n2.val;
		ListNode cur = new ListNode(total%10);
		if(n1== null) cur.next = sumUsingRecursion(n1,n2.next,total/10);
		else if(n2==null) cur.next = sumUsingRecursion(n1.next,n2,total/10);
		else cur.next = sumUsingRecursion(n1.next,n2.next,total/10);
		return cur;
	}
	public static ListNode sumUsingIteration(ListNode n1, ListNode n2){
		int total;
		int carry = 0;
		ListNode head = null;
		ListNode cur = null;
		while(n1!=null || n2!=null){
			total = carry;
			if(n1!=null){
				total += n1.val;
				n1 = n1.next;	
			}
			if(n2!=null){
				total += n2.val;
				n2 = n2.next;			
			}
			if(head ==null){
				head = new ListNode(total%10);
				cur = head;			
			}
			else{
				cur.next = new ListNode(total%10);
				cur = cur.next;			
			}
			carry = total/10;		
		}
		if(carry>0){
			cur.next = new ListNode(carry);		
		}
		return head;
	}

}
