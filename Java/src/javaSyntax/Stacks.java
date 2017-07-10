package javaSyntax;
import java.util.Collections;
import java.util.Stack;

public class Stacks {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//General:
		// 1: type of stack can be customized as shown in line 13.
		// 2: Peek of an empty stack gives EmptyStackException.
		
		Stack<String> stackOfStrings = new Stack<>();
		
		// EmptyStackException
		//System.out.println("try"+stackOfStrings.peek());
		
		//Stack<List<Integer>> s = new Stack<>();
		stackOfStrings.push("one");
		stackOfStrings.push("two");
		stackOfStrings.push("three");

		System.out.println("Stack:"+stackOfStrings+" of Size:"+ stackOfStrings.size());

		// iteration
		
		while(!stackOfStrings.isEmpty()){
			System.out.println("Stack Top:"+stackOfStrings.peek());
			System.out.println("Removing: "+stackOfStrings.pop());
			System.out.println("Stack:"+stackOfStrings);

		}


	}

}
