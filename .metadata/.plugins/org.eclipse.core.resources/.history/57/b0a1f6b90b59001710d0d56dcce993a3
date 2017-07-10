
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MedianInAStreamOfIntegers {
	static int median = 0;
	static PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
	static PriorityQueue<Integer> right = new PriorityQueue<>();
	public static int getMedian(){
		if(left.size()+right.size()==0) return -1;
		return median;
	}
	public static void insert(int n){
		int l = left.size();
		int r = right.size();
		if(l==r){
			if(l==0){
				left.offer(n);
				median = n;
			}
			else if(n>left.peek()){
				right.offer(n);
				median = right.peek();
			}
			else{
				left.offer(n);
				median = left.peek();
			}
		}
		else if(l<r){
			if(n>left.peek()){
				right.offer(n);
				left.offer(right.poll());
			}
			else left.offer(n);
			median = (right.peek()+left.peek())/2;
		}
		else{
			if(n>left.peek()) right.offer(n);
			else{
				left.offer(n);
				right.offer(left.poll());
			} 
			median = (right.peek()+left.peek())/2;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("MedianInAStreamOfIntegers");
		Scanner s = new Scanner(System.in);
		while(true){
			System.out.println("Enter the new Number");
			int n = s.nextInt();
			insert(n);
			System.out.println(left);
			System.out.println(right);
			System.out.println("Median: "+ getMedian());
		}
	}

}
