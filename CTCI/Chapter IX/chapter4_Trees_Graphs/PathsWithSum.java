package chapter4_Trees_Graphs;

import java.util.HashMap;
import java.util.Map;

public class PathsWithSum {
	
	int count;
	Map<Integer,Integer> preCounts;
	public int count(TreeNode root, int target){
		preCounts = new HashMap<Integer,Integer>();
		count = 0;
		preCounts.put(0,1);
		find(root, target,0);
		return count;
	}
	public void find(TreeNode root, int target, int rsum){
		if(root == null) return;
		int sum = rsum + root.value;
		count += preCounts.getOrDefault(sum-target,0);
		if(!preCounts.containsKey(sum))
			preCounts.put(sum,0);
		preCounts.put(sum,preCounts.get(sum)+1);
		find(root.next[0],target,sum);
		find(root.next[1],target,sum);
		preCounts.put(sum,preCounts.get(sum)-1);
	} 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode t = new TreeNode(0,2);
		t.next[0] = new TreeNode(0,2);
		t.next[1] = new TreeNode(0,2);
		t.next[0].next[0] = new TreeNode(4,2);
		t.next[0].next[1] = new TreeNode(4,2);
		t.next[1].next[0] = new TreeNode(4,2);
		t.next[1].next[1] = new TreeNode(4,2);
		
		PathsWithSum obj = new PathsWithSum();
		System.out.println(obj.count(t,5));

	}

}
