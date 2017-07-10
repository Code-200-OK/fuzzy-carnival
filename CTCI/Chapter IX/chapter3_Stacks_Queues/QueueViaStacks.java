package chapter3_Stacks_Queues;

import java.util.Stack;

// no need to bring back the elements from right stack to left stack
public class QueueViaStacks<T> {
	Stack<T> left, right;
	int leftsize,rightsize;
	public QueueViaStacks(){
		left = new Stack<T>();
		right = new Stack<T>();
		leftsize = 0;
		rightsize = 0;	
	}
	public void offer(T item){
		left.push(item);
		leftsize++;		
	}
	public T poll() throws Exception{
		if(isEmpty()) throw new Exception("Empty Queue");
		T top = peek();
		right.pop();
		rightsize--;
		return top;
	}
	public T peek() throws Exception{
		if(isEmpty()) throw new Exception("Empty Queue");
		if(rightsize==0){
			while(leftsize!=0){
				right.push(left.pop());
				rightsize++;
				leftsize--;		
			}
		}
		return right.peek();
	}
	public boolean isEmpty(){
		return leftsize+rightsize==0;	
	}
	public static void main(String[] args) {
		QueueViaStacks<Integer> obj = new QueueViaStacks<>();
		try{
		obj.offer(1);
		obj.offer(2);
		System.out.println(obj.peek());	
		System.out.println(obj.poll());	
		obj.offer(3);
		obj.offer(4);
		System.out.println(obj.peek());	
		System.out.println(obj.poll());	
		while(!obj.isEmpty()){
			System.out.println(obj.poll());	
		}
		}catch(Exception e){
			e.printStackTrace();		
		}
		

	}

}
