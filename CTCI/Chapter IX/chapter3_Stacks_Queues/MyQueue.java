package chapter3_Stacks_Queues;

public class MyQueue<T>{
	private  Node<T> first = null;
	private  Node<T> last = null;
	private class Node<T>{
		T item;
		Node next;
		Node(T it){
			item = it;
			next = null;		
		}	
	}
	public  T peek() throws Exception{
		if(first == null) throw new Exception();
		return first.item;	
	}
	public  void add(T value){
		Node n = new Node(value);
		if(last == null){
			first = n;
			last = n;
		}
		else{
			last.next = n;
			last = n;
		}
	}
	public  void remove() throws Exception{
		if(first == null) throw new Exception("asdfsd");
		first = first.next;	
	}
	public  boolean isEmpty(){
		return first == null;	
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyQueue<Integer> a = new MyQueue<>();
		a.add(3);
		a.add(5);
		a.add(6);
		try {
			System.out.println(a.peek());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			a.remove();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			System.out.println(a.peek());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
