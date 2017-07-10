package others;

import java.util.ArrayDeque;
import java.util.Deque;


/***Given an array nums, there is a sliding window of size k which is moving from the very 
left of the array to the very right. You can only see the k numbers in the window. Each time
the sliding window moves right by one position.***/
public class SlidingWindowMaximum {
	public int[] maxSlidingWindow(int[] a, int k) {		
		if (a == null || k <= 0) {
			return new int[0];
		}
		int n = a.length;
		int[] r = new int[n-k+1];
		int ri = 0;
		// store index
		Deque<Integer> q = new ArrayDeque<>();
		for (int i = 0; i < a.length; i++) {
			// remove numbers out of range k
			if (!q.isEmpty() && q.peek() < i - k + 1) {
				q.poll();
			}
			// remove smaller numbers in k range as they are useless
			while (!q.isEmpty() && a[q.peekLast()] < a[i]) {
				q.pollLast();
			}
			// q contains index... r contains content
			q.offer(i);
			if (i >= k - 1) {
				r[ri++] = a[q.peek()];
			}
		}
		return r;
	}
}
/***TC**: O(n).
**Method**: Usage of Deque. It is mantained such that the max element's position is at 
the front. all the elements are in the order. Instead of 2,4,3 it is better to store 2,4 as, 
the 4th position would serve as the max even for the 3rd positions' window. Everytime check if
the max is in the window else remove. Insert the current in the better position in the queue. 
At last the top of the queue is the max and update the array on the go.*/
