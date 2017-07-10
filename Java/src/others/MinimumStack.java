package others;

import java.util.Stack;

/*Design a stack that supports push, pop, top, and retrieving the minimum element in 
 * constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.*/

public class MinimumStack {
	    long min;
	    Stack<Long> stack;

	    public MinimumStack(){
	        stack=new Stack<>();
	    }
	    
	    public void push(int x) {
	        if (stack.isEmpty()){
	            stack.push(0L);
	            min=x;
	        }else{
	            stack.push(x-min);//Could be negative if min value needs to change
	            if (x<min) min=x;
	        }
	    }

	    public void pop() {
	        if (stack.isEmpty()) return;
	        
	        long pop=stack.pop();
	        
	        if (pop<0)  min=min-pop;//If negative, increase the min value
	        
	    }

	    public int top() {
	        long top=stack.peek();
	        if (top>0){
	            return (int)(top+min);
	        }else{
	           return (int)(min);
	        }
	    }

	    public int getMin() {
	        return (int)min;
	    }
	}
/***Method**: Store the first element as the minimum and push 0 on the stack. As you keep 
on pushing, if it is lesser than min then update min and push the diff of both the mins. 
When top is called return top - min if top is +ve. Else return the minimum as it would have
		been the top element. Removing a positive from the top doesn't effect but when a -ve
		is removed then must update min to min - pop which represents the minimum of the lower
		stack.*/
