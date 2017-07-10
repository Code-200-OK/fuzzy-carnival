package javaSyntax;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
public class Queues {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue<String> queueOfStrings = new LinkedList<>();
		queueOfStrings.offer("one");// returns true or false
		System.out.println("offer:\t"+queueOfStrings);		
		queueOfStrings.offer("two");
		queueOfStrings.offer("three");
		System.out.println("Queue:"+queueOfStrings+" of Size:"+ queueOfStrings.size());
		while(!queueOfStrings.isEmpty()){
			System.out.println("peek:\t"+queueOfStrings.peek());
			System.out.println("poll:\t"+queueOfStrings.poll());
			System.out.println(queueOfStrings);
		}
		
		String a = "done,fell,short";
		Queue<String> v = new LinkedList<String>();
		v.addAll(Arrays.asList(a.split(",")));
		System.out.println("addAll:\t"+v); // requires ArrayList
		System.out.println(v);
	}

}
