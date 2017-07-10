package javaSyntax;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/*
 * In this regard there is a rule of thumb that if you are going to override the one of the 
 * methods( ie equals() and hashCode() 
 */
public class Pair implements Comparable{
	int x,y;
	public Pair(int a, int b){
		x=a;
		y=b;
	}
	public boolean equals(Object a){
		// for equality
		Pair b = (Pair) a;
		return x==b.x && y==b.y;
	}
	public int hashCode(){
		//
		return x*10+y;
	}
	public int compareTo(Object a) {
		// for ordering
		Pair b = (Pair) a;
		return y-b.y;
	}
	public String toString(){
		// for class name
		return getClass().getSimpleName()+"["+x+":"+y+"]";
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<Pair,Integer> store = new HashMap<Pair,Integer>();
		Set<Pair> s = new HashSet<Pair>();
		
		store.put(new Pair(2,3), 1);
		store.put(new Pair(2,3), 5);
		store.put(new Pair(2,3), 10);
		store.put(new Pair(0,23), 15);
		
		s.add(new Pair(2,3));
		s.add(new Pair(2,3));
		
		System.out.println(store);
		System.out.println(s);
	}

}
