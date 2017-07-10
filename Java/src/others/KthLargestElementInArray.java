package others;

public class KthLargestElementInArray {
	public int findKthLargest(int[] nums, int k) {
		if (nums == null || nums.length == 0) return Integer.MAX_VALUE;
	    return findKthLargest(nums, 0, nums.length - 1, nums.length - k);
	}    

	public int findKthLargest(int[] nums, int start, int end, int k) {// quick select: kth smallest
		if (start > end) return Integer.MAX_VALUE;
		
		int pivot = nums[end];// Take A[end] as the pivot, 
		int left = start;
		for (int i = start; i < end; i++) {
			if (nums[i] <= pivot) // Put numbers < pivot to pivot's left
				swap(nums, left++, i);			
		}
		swap(nums, left, end);// Finally, swap A[end] with A[left]
		
		if (left == k)// Found kth smallest number
			return nums[left];
		else if (left < k)// Check right part
			return findKthLargest(nums, left + 1, end, k);
		else // Check left part
			return findKthLargest(nums, start, left - 1, k);
	} 

	void swap(int[] A, int i, int j) {
		int tmp = A[i];
		A[i] = A[j];
		A[j] = tmp;				
	}
}
/*
 * **TC**: O(n) guranteed.
**Method**: Quicksort Algo. When current pivot's final position is K then return the pivot as answer else try in left / right part according to the value of k. Motto is to return nums.length-k th element. 

**Approach 2: Faster**
```
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length-k];
    }
```
 */
