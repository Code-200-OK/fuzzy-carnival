package chapter2_LinkedLists;
public class Palindrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode head = new LinkedListt(new int[]{1,1}).getHead();
		System.out.println(isPal1(head,length(head)).isPal);

	}
	public static int length(ListNode n){
		int l = 0;
		while(n!=null){
			l++;
			n= n.next;
		}
		return l;
	}
	public static BoolNode isPal1(ListNode n, int l){
		if(l==1 || l==0) return new BoolNode(l==1?n.next:n,true);
		BoolNode next = isPal1(n.next,l-2);
		if(next.isPal){
			return new BoolNode(next.n.next,next.n.val==n.val);
		}
		return new BoolNode(null,false);
	}
	public static boolean isPal(ListNode n){
		if(n== null || n.next==null) return true;
		ListNode slow = n;
		ListNode fast = n.next;
		while(fast!=null && fast.next!=null){
			slow = slow.next;
			fast = fast.next.next;		
		}
		slow = slow.next;
		slow = reverse(slow);
		fast = n;
		while(slow!=null){
			if(slow.val != fast.val) return false;
			System.out.println("Cont");
			slow = slow.next;
			fast = fast.next;		
		}
		return true;	
	}
	public static ListNode reverse(ListNode n){
		ListNode head = n;
		ListNode rev = null;
		ListNode cur = null;
		while(head!=null){
			cur = head.next;
			head.next = rev;
			rev = head;
			head = cur;
		}
		return rev;	
	}
}
class BoolNode{
	ListNode n;
	boolean isPal;
	public BoolNode(ListNode ode, boolean b){
		n = ode;
		isPal = b;
	}
}
