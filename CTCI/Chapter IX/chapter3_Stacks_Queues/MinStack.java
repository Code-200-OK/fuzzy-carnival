package chapter3_Stacks_Queues;

import java.util.Stack;
// pop uses peek to return the top element

public class MinStack extends Stack<Integer>{
	private int min;
	public MinStack(){
		min = -1;
	}
	public void push(int item){
		if(isEmpty()){
			min = item;
			super.push(-1);		
		}
		else if(item < min){
			super.push(item-min);
			min = item;		
		}
		else{
			super.push(item-min);	
		}	
	}
	
	public Integer pop(){
		if(isEmpty()) return Integer.MAX_VALUE;
		int top = super.pop();
		int toreturn;
		if(top<0){
			toreturn = min;
			min -= top;
			return toreturn;		
		}
		else{
			return top + min;		
		}
	}
	public int min() throws Exception{
		if(isEmpty()) throw new Exception("Empty Stack");
		return min;
	}

	public static void main(String[] args) {
		MinStack obj = new MinStack();
		try{
			obj.push(5);
			obj.push(7);
			obj.push(2);
			obj.push(10);
			while(!obj.isEmpty()){
				System.out.println(obj.min());
				System.out.println(obj.pop());
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
