package others;

/*Given an array of integers that is already sorted in ascending order, find two numbers 
such that they add up to a specific target number. (Not zero based)*/
public class TwoSumSorted {
	public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length-1;
        int[] ans = new int[2];
        int sum;
        while(left<right){
            sum = numbers[left]+numbers[right];
            if( sum == target){
                ans[0] = left+1;
                ans[1] = right+1;
                return ans;
            }
            if(sum < target)
                left++;
            else
                right--;
        }
        return ans;
    }
}

/***TC**: O(n)
**SC**: O(1)
**Method**: Start two pointers from both ends and if their sum is the target then 
return else if their sum fall short then increment the left else decrement the right.*/