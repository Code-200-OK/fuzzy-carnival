package trivia;

import java.util.Iterator;

public class CircularArray<E> implements Iterable<E>{
private E[] data;
private int head;
public CircularArray(int size){
	data = (E[])new Object[size];
	head = 0;
}
public void set(int pos, E value){
	data[pos] = value;
}
public void display(){
	for(E element: data)
		System.out.println(element);
}
public int convert(int pos){
	pos %= data.length;
	return pos+head-(pos+head>=data.length?data.length:0);
}
public void rotate(int pos){
	head = convert(pos);
}
public E get(int pos) throws Exception{
	if(pos<0 || pos>=data.length)
		throw new Exception("Invalid arguments");
	return data[convert(pos)];
} 
public static void main(String[] args) {
	try{
	CircularArray<Integer> obj = new CircularArray<Integer>(6);
	obj.set(0, 1);
	obj.set(1, 2);
	obj.set(2, 3);
	obj.set(3, 4);
	obj.set(4, 5);
	obj.set(5, 6);
	System.out.println();
	for(Integer e:obj){
		System.out.print(e+".");
	}
	System.out.println();
	obj.rotate(39);
	System.out.println();
	for(Integer e:obj){
		System.out.print(e+".");
	}
	System.out.println();
	
	}
	catch(Exception e){
		System.out.println("In catch");
		e.printStackTrace();
	}
}

public Iterator<E> iterator(){
	return new CircularArrayIterator<E>(this);
}

private class CircularArrayIterator<E> implements Iterator<E>{
	private E[] items;
	private int currentpos;
	private int total;
	public CircularArrayIterator(CircularArray e){
		items = (E[]) e.data;
		currentpos = e.head;
		total = 0;
	}
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return total!=items.length;
	}

	@Override
	public E next() {
		// TODO Auto-generated method stub
		E ans = items[currentpos];
		currentpos = currentpos==items.length-1?0:currentpos+1;
		total++;
		return ans;
	}
}
}
