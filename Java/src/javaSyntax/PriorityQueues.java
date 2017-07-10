package javaSyntax;

import java.util.Comparator;
import java.util.PriorityQueue;

class tr implements Comparable{
	int id;
	String name;
	public tr(int x, String n){
		id = x;
		name = n;
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		tr p = (tr) o;
		return Integer.compare(id, p.id);
	}
	
}
public class PriorityQueues {
	public static void main(String[] args){
		PriorityQueue<tr> ab = new PriorityQueue<tr>();
		ab.offer(new tr(10,"sakthi"));
		ab.offer(new tr(8,"vel"));
		ab.offer(new tr(2,"azhaku"));
		System.out.println(ab.peek().id);
		ab.poll();
		System.out.println(ab.peek().id);
		
		Comparator<String> hut = new Comparator<String>(){
				public int compare(String a, String b){
					return 0;
				}
		};
		
		
	}
	
}
