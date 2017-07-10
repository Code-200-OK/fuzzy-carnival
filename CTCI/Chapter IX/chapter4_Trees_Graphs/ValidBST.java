package chapter4_Trees_Graphs;

import java.util.ArrayList;
import java.util.List;

// min max solution in book. start with (min, max). every root must exist in this range.
public class ValidBST {
	private static List<Integer> vals;
	public static boolean isValidBST(TreeNode root){
		vals = new ArrayList<Integer>();
		inorder(root);
		for(int i=1;i<vals.size();i++){
			if(vals.get(i)< vals.get(i-1)) return false;
			if(vals.get(i)==vals.get(i-1) && (i>=2 && vals.get(i)==vals.get(i-2))) return false;
		}
		return true;
		//return check(root) != Integer.MIN_VALUE;	
	}
	public static void inorder(TreeNode root){
		if(root==null)return;
		inorder(root.next[0]);
		vals.add(root.value);
		inorder(root.next[1]);	
	}
	public static int check(TreeNode root){
		if(root == null) return Integer.MAX_VALUE;
		int l1 = check(root.next[0]);
		if(l1==Integer.MIN_VALUE) return Integer.MIN_VALUE;
		int l2 = check(root.next[1]);
		if(l2==Integer.MIN_VALUE) return Integer.MIN_VALUE;
		if(l1 == Integer.MAX_VALUE || l2==Integer.MAX_VALUE){
			if(l1 == Integer.MAX_VALUE && l2==Integer.MAX_VALUE) return root.value;
			if(l1 == Integer.MAX_VALUE){
				if(root.value < l2) return root.value;
				return 	Integer.MIN_VALUE;		
			}
			else{
				if(root.value >= l1) return root.value;
				return 	Integer.MIN_VALUE;			
			}
		}
		if(root.value >= l1 && root.value < l2) return root.value;
		return Integer.MIN_VALUE;	
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode t = new TreeNode(4,2);
		t.next[0] = new TreeNode(2,2);
		t.next[1] = new TreeNode(6,2);
		t.next[0].next[0] = new TreeNode(1,2);
		t.next[0].next[1] = new TreeNode(3,2);
		t.next[1].next[0] = new TreeNode(5,2);
		t.next[1].next[1] = new TreeNode(7,2);
		t.traverse();
		System.out.println(isValidBST(t));

	}

}
