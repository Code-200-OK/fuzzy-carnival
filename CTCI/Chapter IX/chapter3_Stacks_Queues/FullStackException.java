package chapter3_Stacks_Queues;

public class FullStackException extends Exception {
	public FullStackException(){
		super("The stack is full");
	}
	
}
