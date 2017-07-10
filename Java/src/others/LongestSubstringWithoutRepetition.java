package others;

import java.util.HashMap;

/***Given a string, find the length of the longest substring without repeating characters.**
*/
public class LongestSubstringWithoutRepetition {
	public int lengthOfLongestSubstring(String s) {
		if (s.length()==0) return 0;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		int max=0;
		for (int i=0, j=0; i<s.length(); ++i){
			if (map.containsKey(s.charAt(i))){
				j = Math.max(j,map.get(s.charAt(i))+1);
			}
			map.put(s.charAt(i),i);
			max = Math.max(max,i-j+1);
		}
		return max;
	}
}

/***TC**: O(n)
**Method**: Sliding Window. Put a left pointer on 0, use a right ptr to move. Keep on 
adding charactes as keys and their indices as values in a Hashmap. 
The moment a conflict is found then, check if the conflict is after the left pointer 
if yes then update left pointer to the next value of the conflict else update the entry's value.
Meanwhile check the length so far and compare with max.
*/
