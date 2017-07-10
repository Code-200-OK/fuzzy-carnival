package javaSyntax;
import java.util.PriorityQueue;
public class MinMaxHeaps {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// General:
		// 1. By default it is min heap
		// 2. Comparator can be changed
		
		
		// FOR MIN HEAP
		PriorityQueue<String> a = new PriorityQueue<>();
		
		// FOR MAX HEAP
		//PriorityQueue<String> a = new PriorityQueue<>(Collections.reverseOrder());
		a.offer("one");
		System.out.println("offer:\t"+a);
		
		a.offer("two");
		a.offer("three");
		a.offer("abc");
		System.out.println(a);
		System.out.println("size:\t"+ a.size());
		
		while(!a.isEmpty()){
			System.out.println("peek:\t"+a.peek());
			System.out.println("poll:\t"+a.poll());
			System.out.println(a);
			System.out.println("isEmpty:\t"+a.isEmpty());
		}
		
	}

}
