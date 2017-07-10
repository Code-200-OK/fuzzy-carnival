package others;

import java.util.ArrayList;
import java.util.List;

/*Given a string s, partition s such that every substring of the partition is a palindrome.
Return all possible palindrome partitioning of s.
For example, given s = "aab",
Return
[
  ["aa","b"],
  ["a","a","b"]
]*/
public class PalindromePartitioning {
	public List<List<String>> partition(String s) {
		List<List<String>> list = new ArrayList<>();
		backtrack(list, new ArrayList<>(), s, 0);
		return list;
	}

	public void backtrack(List<List<String>> list, List<String> tempList, String s, int start){
		if(start == s.length())
			list.add(new ArrayList<>(tempList));
		else{
			for(int i = start; i < s.length(); i++){
				if(isPalindrome(s, start, i)){
					tempList.add(s.substring(start, i + 1));
					backtrack(list, tempList, s, i + 1);
					tempList.remove(tempList.size() - 1);
				}
			}
		}
	}

	public boolean isPalindrome(String s, int low, int high){
		
		while(low < high)
			if(s.charAt(low++) != s.charAt(high--)) return false;
		return true;
	}
}
/***TC**: O(n2^n) . String length is n. Can cut string in 2^n-1 ways. 
(i.e. n-1 gaps between characters) ans we have the option to cut or not 
cut so 2 times that many times ways. For each partition have to check if it is
palindrome which is O(n).
**Method**: Call the func with 0 as start. do for len =1 till len = whole string. 
if palindrome then add to the list and recursively do else skip. For palindrome checking 
don't form a new string, instead check palindrome using the same string using two pointers
denoting the start and end.*/

