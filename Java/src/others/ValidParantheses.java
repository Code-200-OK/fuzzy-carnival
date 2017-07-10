package others;

import java.util.Stack;

/***Given a string containing just the characters '(', ')', '{', '}', '[' and ']',
determine if the input string is valid.***/

public class ValidParantheses {
	public boolean isValid(String s) {
		if(s==null)
			return false;
		char[] c = s.toCharArray();
		Stack<Character> st = new Stack<Character>();
		for(int i=0;i<c.length;i++){
			if(c[i]=='('||c[i]=='{'||c[i]=='[')
				st.push(c[i]);
			else{
				if((c[i]==')'&&(st.size()==0 || st.peek()!='('))||(c[i]=='}'&&(st.size()==0 || st.peek()!='{'))||(c[i]==']'&&(st.size()==0 || st.peek()!='[')))
					return false;
				st.pop();
			}
		}
		return st.isEmpty();
	}
}
/***TC**: O(n)
**Method**: Usage of a stack. At last return stack.isEmpty();*/