package chapter4_Trees_Graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BSTSequences {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode t = new TreeNode(2,2);
		t.next[0] = new TreeNode(1,2);
		t.next[1] = new TreeNode(4,2);
		t.next[1].next[0] = new TreeNode(3,2);
		t.next[1].next[1] = new TreeNode(5,2);
		//t.next[1].next[0] = new TreeNode(6,2);
		//t.next[1].next[1] = new TreeNode(7,2);
		//t.traverse();
		List<List<Integer>> answer = findWays(t);
		if(answer!=null) System.out.println(answer);

	}
	public static List<List<Integer>> findWays(TreeNode root){
		List<List<Integer>> answer = new ArrayList<List<Integer>>();
		
		if(root==null){
			answer.add(new ArrayList<Integer>());
			return answer;
		}
		List<List<Integer>> left = findWays(root.next[0]);
		List<List<Integer>> right = findWays(root.next[1]);
		
		for(List<Integer> a: left)
			for(List<Integer> b: right)
				weave(a,b,new ArrayList<Integer>(Arrays.asList(root.value)),answer);

		return answer;
	}
	private static void weave(List<Integer> left, List<Integer> right, List<Integer> pre, List<List<Integer>> ans) {
		// TODO Auto-generated method stub
		if(left.size()==0 || right.size()==0){
			List<Integer> a = new ArrayList<>(pre);
			a.addAll(left);
			a.addAll(right);
			ans.add(a);
		}
		else{
			pre.add(left.remove(0));
			weave(left,right,pre,ans);
			left.add(0,pre.remove(pre.size()-1));

			pre.add(right.remove(0));
			weave(left,right,pre,ans);
			right.add(0,pre.remove(pre.size()-1));
		}

	}

}
