package others;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsOfAPhoneNumber {
	char[][] key = {{'a','b','c','.'},
			{'d','e','f','.'},
			{'g','h','i','.'},
			{'j','k','l','.'},
			{'m','n','o','.'},
			{'p','q','r','s'},
			{'t','u','v','.'},
			{'w','x','y','z'},
	};
	List<String> ans;
	public List<String> letterCombinations(String digits) {
		ans = new ArrayList<String>();
		if(digits==null || digits.length()==0)
			return ans;
		char[] c = digits.toCharArray();
		find(c,0,new StringBuilder());
		return ans;
	}
	private void find(char[] c, int pos, StringBuilder s){
		if(pos>= c.length)
		{
			ans.add(s.toString());
			return;
		}
		int n = Character.getNumericValue(c[pos]);
		if(n<2 || n>9)
			return;
		for(int i=0;i<4;i++){
			if(key[n-2][i]=='.') continue;
			s.append(key[n-2][i]);
			find(c,pos+1,s);
			s.setLength(s.length()-1);
		}
	}
}
/***TC**: O(4^n)
**Method**: Backtrackking. Call fn with 0. When pos becomes greater than the length of the input 
string then add to the answer list
**Method2**: Use queue initialized with "". for each digit pop every element in the queue 
and add every possible character of that button and offer back to the queue. Performs same.*/
