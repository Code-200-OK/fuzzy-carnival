package chapter3_Stacks_Queues;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StackOfPlates {
	private List<Stack<Integer>> plates;
	private int capacity;
	public StackOfPlates(int capacity){
		plates = new ArrayList<Stack<Integer>>();
		this.capacity = capacity;	
	}
	public void push(int plate){
		Stack<Integer> st;
		if(isEmpty()){
			plates.add(new Stack<Integer>());
			st  = plates.get(0);		
		}
		else{
			st = plates.get(plates.size()-1);		
		}
		if(st.size() == capacity){
			plates.add(new Stack<Integer>());
			st = plates.get(plates.size()-1);		
		}
		st.push(plate);
	}
	public int popAt(int num){
		if(num==0 || num>plates.size()) return Integer.MAX_VALUE;
		if(num==plates.size()){
			int ret = plates.get(plates.size()-1).pop();
			if(plates.get(plates.size()-1).isEmpty()){
				plates.remove(plates.size()-1);
			}
			return ret;
		}
		Stack<Integer> left;
		left = plates.get(num-1);
		int ret = left.pop();
		for(int i=num;i<plates.size();i++){
			Stack<Integer> right = plates.get(i);
			Stack<Integer> swap = new Stack<Integer>();
			while(right.size()>1){
				swap.push(right.pop());			
			}
			left.push(right.pop());
			while(!swap.isEmpty()){
				right.push(swap.pop());			
			}
			left = right;
		}
		if(plates.get(plates.size()-1).isEmpty()){
			plates.remove(plates.size()-1);
		}
		return ret;
	}
	public int pop(){
		if(isEmpty()) return Integer.MAX_VALUE;
		Stack<Integer> st = plates.get(plates.size()-1);
		if(st.size()==1){
			int ret = st.pop();
			plates.remove(plates.size()-1);
			return ret;		
		}
		return st.pop();	
	}
	public boolean isEmpty(){
		return plates.size()==0;		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StackOfPlates obj = new StackOfPlates(3);
		obj.push(1);
		obj.push(2);
		obj.push(3);
		obj.push(4);
		obj.push(5);
		obj.push(6);
		obj.push(7);
		System.out.println(obj.plates);
		System.out.println(obj.popAt(3));
		System.out.println(obj.plates);
		System.out.println(obj.pop());
		/*while(!obj.isEmpty()){
			System.out.println(obj.pop());		
		}*/
		

	}

}
