package chapter4_Trees_Graphs;

public class CheckBalanced {
	public static boolean isBalanced(TreeNode root){
		return check(root)>=0;
	}
	public static int check(TreeNode root){
		if(root == null) return 0;
		int l1 = check(root.next[0]);
		if(l1<0) return -1;
		int l2 = check(root.next[1]);
		if(l2<0 || Math.abs(l1-l2)>1) return -1;
		return Integer.max(l1,l2)+1;	
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode t = new TreeNode(1,2);
		t.next[0] = new TreeNode(2,2);
		//t.next[1] = new TreeNode(3,2);
		t.next[0].next[0] = new TreeNode(4,2);
		t.next[0].next[1] = new TreeNode(5,2);
		//t.next[1].next[0] = new TreeNode(6,2);
		//t.next[1].next[1] = new TreeNode(7,2);
		t.traverse();
		System.out.println(isBalanced(t));

	}

}
