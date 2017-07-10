package others;

import java.util.ArrayList;
import java.util.List;

/*Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.*/
public class Combinations {
    public static List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> combs = new ArrayList<List<Integer>>();
		combine(combs, new ArrayList<Integer>(), 1, n, k);
		return combs;
	}
	public static void combine(List<List<Integer>> combs, List<Integer> comb, int start, int n, int k) {
		if(k==0) {
			combs.add(new ArrayList<Integer>(comb));
			return;
		}
		for(int i=start;i<=n-k+1;i++) {
			comb.add(i);
			combine(combs, comb, i+1, n, k-1);
			comb.remove(comb.size()-1);
		}
	}
}

/***TC**: O(n^min{k,n-k})
**Method**: Backtrack. Start from the first position. Append every next element. 
Call the fn recursively. Every time after entering, check if k have been choosen. 
For every i consider till n-k+i. eg: if N=4, k=2 then go from 1 to 3.*/