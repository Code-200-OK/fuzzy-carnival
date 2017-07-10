package chapter4_Trees_Graphs;

import java.util.Random;

public class RandomNode {

	int data;
	RandomNode left,right;
	int size;
	public RandomNode(int d){
		data = d;
		size = 1;
	}
	public void insert(int data){
		if(data<= this.data){
			if(left == null) left = new RandomNode(data);
			else left.insert(data);		
		}
		else{
			if(right == null) right = new RandomNode(data);
			else right.insert(data);		
		}
		size++;
	}
	public RandomNode find(int data){
		if(data==this.data) return this;
		if(data<=this.data) return left==null?null:left.find(data);
		return right==null?null:right.find(data);
	}
	public result delete(int data){
		result n = new result(this,false);
		if(data== this.data){
			n.indicate = true;
			if(right==null){
				n.node = left;
				return n;
			}
			RandomNode ans[] = right.deleteleastnode();
			right = ans[0];
			ans[1].left = left;
			ans[1].right = right;
			ans[1].size = size-1;
			n.node = ans[1];
			return n;		
		}
		else if(data<= this.data && left!=null){
			n = left.delete(data);
			left = n.node;
			if(n.indicate) size--;
			
		} 
		else if(data> this.data && right!=null){
			n = right.delete(data);
			right = n.node;
			if(n.indicate) size--;
		}
		n.node = this;
		return n;
	}
	public RandomNode getRandomNode(){
		Random r = new Random();
		int chosen = r.nextInt(size);
		RandomNode node = this;
		int l;
		
		if(node.left==null) l = 0;
		else l = node.left.size;
		
		while(l!=chosen){
			if(l>chosen) node = node.left;
			else{
				node = node.right;
				chosen -= (l+1);
			} 
			
			if(node.left==null) l = 0;
			else l = node.left.size;
		}
		return node;
	}
	public  RandomNode[] deleteleastnode(){
		if (left == null) return new RandomNode[]{right,this};
		RandomNode[] ans = left.deleteleastnode(); 
		left = ans[0];
		ans[0] = this;
		size--;
		return ans; 
	}
	public void traverse(){
		System.out.println(data);
		if(left!=null) left.traverse();
		if(right!=null) right.traverse();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomNode obj = new RandomNode(6);
		obj.insert(5);
		obj.insert(8);
		obj.insert(9);
		System.out.println(obj.size);
		System.out.println(obj.find(8).data);
		System.out.println(obj.find(10));
		obj = obj.delete(9).node;
		obj.traverse();
		System.out.println("size"+obj.size);
		obj = obj.delete(14).node;
		obj.traverse();
		System.out.println("size"+obj.size);
		obj = obj.delete(19).node;
		obj.traverse();
		System.out.println("size"+obj.size);
		System.out.println(obj.getRandomNode().data);
	}
	/*
	
	public static mynode root;
	
	public RandomNode(){
		root = null;	
	}
	public static void traverse(mynode node){
		if(node == null){ 
			System.out.println("X");
			return;
		}
		System.out.println(node.value);
		traverse(node.left);
		traverse(node.right);
	}
	
	public static boolean insert(int data){
		if(root==null){
			root = new mynode(data);
			return true;	
		}
		Queue<mynode> nodes = new LinkedList<mynode>();
		nodes.offer(root);
		mynode temp;
		while(!nodes.isEmpty()){
			temp = nodes.poll();
			if(temp.left == null || temp.right==null){
				if(temp.left == null)
					temp.left = new mynode(data);
				else temp.right = new mynode(data);
				return true;			
			}
			else{
				nodes.offer(temp.left);
				nodes.offer(temp.right);			
			}
					
		}
		return false;
	}
	public static boolean find(int data){
		Queue<mynode> nodes = new LinkedList<mynode>();
		nodes.offer(root);
		mynode temp;
		while(!nodes.isEmpty()){
			temp = nodes.poll();
			if(temp.value == data) return true;
			if(temp.left !=null) nodes.offer(temp.left);
			if(temp.right !=null) nodes.offer(temp.right);					
		}
		return false;
	}
	public static boolean delete(int data){
		if(!find(data)) return false;
		root = del(root,data);
		return true;
	}
	public static mynode del(mynode node, int data){
		if(node==null) return null;
		if(node.value == data){
			if(node.right==null) return node.left;
			mynode temp = findRightSmallest(node);
			temp.left = node.left;
			temp.right = node.right;
			return temp;
		}
		node.left = del(node.left,data);
		node.right = del(node.right,data);
		return node;	
	}
	public static mynode findRightSmallest(mynode node){
		mynode act = node;
		mynode parent = node;
		node = node.right;
		while(node.left !=null){
			parent = node;
			node = node.left;
		}
		if(act==parent) parent.right = node.right;
		else parent.left = node.right;
		return node;
	}
	public static int getRandomNode(){
	
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomNode obj = new RandomNode();
		obj.insert(4);
		obj.insert(5);
		obj.insert(8);
		obj.insert(9);
		obj.traverse(obj.root);
		System.out.println(obj.find(8));
		System.out.println(obj.find(10));
		System.out.println(obj.delete(7));
		System.out.println(obj.delete(4));
		obj.traverse(obj.root);
		System.out.println(obj.delete(9));
		obj.traverse(obj.root);
	}

*/}
/*class mynode{

	public int value;
	public mynode left,right;
	
	public mynode(int value){
		this.value = value;
		left = null;
		right = null;	
	}
}*/
class result{
	RandomNode node;
	boolean indicate;
	public result(RandomNode n, boolean t){
		node = n;
		indicate = t;
	}
}
