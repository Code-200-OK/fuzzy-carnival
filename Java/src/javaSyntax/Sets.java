package javaSyntax;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.SortedSet;
import java.util.TreeSet;

public class Sets {

	public static void main(String[] args) {
		// look for 329
		// TODO Auto-generated method stub
		// General:
		// Deletion without iterator leads ConcurrentModificationException
		// Does print normally in sysout
		
		
		//Exclusive:
		// unordered set
		
		System.out.println("UnOrder Set.............");
		Set<Integer> unorderedSet = new HashSet<>();
		unorderedSet.add(1000);
		unorderedSet.add(5);
		unorderedSet.add(3);
		unorderedSet.add(30);
		unorderedSet.add(3);
		//a.add(null); // any number of nulls can be added because duplicates will be eliminated
		unorderedSet.add(2);
		System.out.println("Set:\t"+ unorderedSet);
		System.out.println("Size:\t"+unorderedSet.size());
		unorderedSet.remove(2); // takes element not index.
		System.out.println("Set:\t"+ unorderedSet);
		delete(unorderedSet,3);
		System.out.println("Set:\t"+ unorderedSet);
		
		iterateUsingIterator(unorderedSet);
		iterateUsingForLoop(unorderedSet);
		iterateUsingForandIterator(unorderedSet);
		
		System.out.println("Insertion Order Set.............");
		Set<Integer> insertionOrderSet = new LinkedHashSet<>();
		insertionOrderSet.add(5);
		insertionOrderSet.add(3);
		insertionOrderSet.add(30);
		insertionOrderSet.add(3);
		insertionOrderSet.add(2);
		iterateUsingForandIterator(insertionOrderSet);
		
		System.out.println("Comparator Order Set.............");
		
		// Using comparator
		Comparator<String> comparator = new Comparator<String>() {
            // a< b -ve
			// a==b 0
			// a>b  +ve
			@Override
            public int compare(String a, String b) {
                if(a.length()>b.length())
                	return 1;
                else if(a.length()== b.length())
                	return a.compareTo(b);
                else
                	return -1;
            }
        };
        
        
		SortedSet<String> OrderSet = new TreeSet<>(comparator);
		//SortedSet<Integer> OrderSet = new TreeSet<>(Collections.reverseOrder());
		
		OrderSet.add("d");
		OrderSet.add("a");
		OrderSet.add("sa");
		OrderSet.add("da");
		OrderSet.add("asd");
		iterateUsingForandIterator(OrderSet);
		System.out.println(OrderSet);
		
		
	}
	public static void delete(Set<Integer> a, int b){
		System.out.println("Deleting..."+b);
		Iterator<Integer> it = a.iterator();
		while(it.hasNext()){
			if(it.next() == b)
				it.remove();
		}
		for(Integer ba: a){
			System.out.println(ba);
		}
	}
	
	public static void iterateUsingForandIterator(SortedSet<String> a){
		// using for loop limits the iterator to a smaller scope.
		// can delete in this
		for (Iterator<String> i = a.iterator(); i.hasNext();) {
		    String element = i.next();
		    System.out.print(element+",");
		}
		System.out.println();
	}
	
	public static void iterateUsingForandIterator(Set<Integer> a){
		// using for loop limits the iterator to a smaller scope.
		// can delete in this
		for (Iterator<Integer> i = a.iterator(); i.hasNext();) {
			int element = i.next();
			System.out.print(element+",");
		}
		System.out.println();
	}
	
	public static void iterateUsingIterator(Set<Integer> a){
		// can delete in this
		Iterator<Integer> it = a.iterator();
		Integer b;
		while(it.hasNext()){
			b = it.next();
			System.out.print(b+",");
		}
		System.out.println();
	}
	
	public static void iterateUsingForLoop(Set<Integer> a){
		// cannot delete in this
		for(Integer s: a){
			System.out.print(s+",");
			
		}
		System.out.println();
	}

}
