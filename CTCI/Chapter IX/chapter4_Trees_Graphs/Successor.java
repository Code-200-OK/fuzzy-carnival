package chapter4_Trees_Graphs;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class Successor {
	public static TreeNode findNext(TreeNode node){
		if(node == null) return null;
		if(node.next[1]!=null) return findSmallest(node.next[1]);
		TreeNode parent = node.parent;
		while(parent!=null && parent.next[1]==node){
			node = parent;
			parent = node.parent;
		}
		return parent;
	}
	public static TreeNode findSmallest(TreeNode node){
		if(node==null) return null;
		if(node.next[0] == null) return node;
		return findSmallest(node.next[0]);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode t = new TreeNode(1,2);
		t.next[0] = new TreeNode(2,2);
		t.next[1] = new TreeNode(3,2);
		t.next[0].parent = t;
		t.next[1].parent = t;
		t.next[0].next[0] = new TreeNode(4,2);
		t.next[0].next[1] = new TreeNode(5,2);
		t.next[0].next[0].parent = t.next[0];
		t.next[0].next[1].parent = t.next[0];
		t.next[1].next[0] = new TreeNode(6,2);
		t.next[1].next[1] = new TreeNode(7,2);
		t.next[1].next[0].parent = t.next[1];
		t.next[1].next[1].parent = t.next[1];
		t.traverse();
		System.out.println();
		System.out.println(findNext(t.next[1].next[1]));

	}

}
