package javaSyntax;

import java.util.Deque;
import java.util.LinkedList;

public class Deques {
	public static void main(String[] args){
		Deque<String> a = new LinkedList<String>();
		System.out.println(a);
		a.offerFirst("sakthi");
		System.out.println("offerFirst:\t"+a);
		a.offerLast("vel");
		System.out.println("offerLast:\t"+a);
		a.offerLast("veluuu");
		System.out.println("\t\t"+a);
		System.out.println("peekFirst:\t"+a.peekFirst());
		System.out.println("peekLast:\t"+a.peekLast());
		System.out.println("size:\t"+a.size());
		System.out.println("isEmpty:\t"+a.isEmpty());
		a.pollFirst();
		System.out.println("pollFirst:\t"+a);
		a.pollLast();
		System.out.println("pollLast:\t"+a);
		
		System.out.println("");
	}

}
