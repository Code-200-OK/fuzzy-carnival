package chapter2_LinkedLists;
public class LinkedListt {
	private static ListNode head;
	public LinkedListt(){
		head = null;
	}
	public LinkedListt(int[] values){
		head = null;
		ListNode cur = null;
		for(int i: values){
			if(head == null){
				head = new ListNode(i);
				cur = head;		
			}
			else{
				cur.next = new ListNode(i);
				cur = cur.next;		
			}		
		}
		//cur.next = null;	
	}
	public ListNode getHead(){
		return head;	
	}
	public void show(ListNode Node){
		while(Node != null){
			System.out.print(Node.val+"-->");
			Node = Node.next;		
		}
		System.out.println();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
