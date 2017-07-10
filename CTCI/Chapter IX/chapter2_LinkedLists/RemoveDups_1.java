package chapter2_LinkedLists;
import java.util.HashSet;
import java.util.Set;

public class RemoveDups_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*ListNode head = new ListNode(1);
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
		fiv.next = six;*/
		ListNode head = new LinkedListt(new int[]{1,2,3,4,5,6,7,8}).getHead();
		show(head); 
		removeDups(head);
		show(head); 
	}
	private static void removeDupsNoBuffer(ListNode head){
		if(head == null) return;
		ListNode cur = head.next;
		ListNode tail = head;
		ListNode temp;
		int val;
		boolean present;
		while(cur != null){
			val = cur.val;
			present = false;
			temp = head;
			// search from [head,tail]
			while(temp!=tail){
				if(temp.val==val){
					present = true;
					break;				
				}
				temp = temp.next;			
			}
			if(tail.val == val) present = true;
			if(!present){
				tail.next.val = val;
				tail = tail.next;			
			}
			cur = cur.next;
		}
		tail.next = null;
			
	}
	private static void removeDups(ListNode head) {
		if(head == null) return;
		Set<Integer> unique = new HashSet<>();
		unique.add(head.val);
		ListNode cur = head.next;
		ListNode tail = head;
		while(cur != null){
			if(!unique.contains(cur.val)){
				unique.add(cur.val);
				tail.next.val = cur.val;
				tail = tail.next;		
			}
			cur = cur.next;
		}
		tail.next = null;
	}
	public static void show(ListNode Node){
		while(Node != null){
			System.out.print(Node.val+"-->");
			Node = Node.next;		
		}
		System.out.println();
	}

}
