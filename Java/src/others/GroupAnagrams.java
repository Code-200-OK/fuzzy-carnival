package others;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class GroupAnagrams {
	public static List<List<String>> groupAnagrams(String[] strs) { 
		int[] prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};

		List<List<String>> res = new ArrayList<>();
		HashMap<Integer, Integer> map = new HashMap<>();
		for (String s : strs) {
			int key = 1;
			for (char c : s.toCharArray()) {
				key *= prime[c - 'a'];
			}
			List<String> t;
			if (map.containsKey(key)) {
				t = res.get(map.get(key));
			} else {
				t = new ArrayList<>();
				res.add(t);
				map.put(key, res.size() - 1);
			}
			t.add(s);
		}
		return res;
	}
}

/***TC**: 
**Method**: Use prime numbers to differentiate the 26 alphabets. 
find the product of the characters in each string use it as key.and check if the 
hashmap already contains any such key else create one. */
/*


**Approach 2:**

public class Solution {
	public List<List<String>> groupAnagrams(String[] strs) {
		Map<String,List<String>> store = new HashMap<String,List<String>>();
		List<List<String>> ans = new ArrayList<List<String>>();
		List<String> list = null;
		int done  = 0;
		StringBuilder search = new StringBuilder();
		char[] c;
		for(int i=0;i<strs.length;i++){
			c = strs[i].toCharArray();
			Arrays.sort(c);
			search.setLength(0);
			search.append(c);
			if(store.containsKey(search.toString())){
				store.get(search.toString()).add(strs[i]);
			}
			else{
				list = new ArrayList<>();
				list.add(strs[i]);
				store.put(search.toString(),list);
			}
		}
		//System.out.println(store);
		for(Map.Entry<String,List<String>> e: store.entrySet()){
			ans.add(e.getValue());
		}  


		return ans;
	}
	public boolean isAnagram(String s, String t) {
		int[] one = new int[26];
		for(int i=0;i<s.length();i++)
			one[s.charAt(i)-'a']++;
		int pos  = -1;
		for(int i=0;i<t.length();i++)
		{   
			pos = t.charAt(i)-'a';
			if(one[pos]==0)
				return false;
			one[pos]--;
		}
		for(int i=0;i<26;i++){
			if(one[i]!=0)
				return false;
		}
		return true;
	}
}
```
**TC**: --- This method peforms slower than the previous one.
**Method**:  Use a HashMap to store sorted anagram string as key and their list as value.
Consider every string and sort it. Check if any of the keys in the Map matches with it 
else create a new key value pair.*/
