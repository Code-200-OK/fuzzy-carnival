package chapter3_Stacks_Queues;

import java.util.Stack;

public class SortStack {
	private Stack<Integer> sorted;
	public void sort(Stack<Integer> toSort){
		sorted = new Stack<Integer>();
		int top;
		while(!toSort.isEmpty()){
			top = toSort.pop();
			while(!sorted.isEmpty() && sorted.peek()<top) toSort.push(sorted.pop());
			toSort.push(top);	
		}	
	}
	public static void main(String[] args) {
		Stack<Integer> toSort = new Stack<Integer>();
		toSort.push(5);
		toSort.push(10);
		toSort.push(3);
		toSort.push(7);
		toSort.push(1);
		toSort.push(8);	
		SortStack obj = new SortStack();
		obj.sort(toSort);
		System.out.println(toSort);
	}

}
