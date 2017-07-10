package others;

/***You are given a list of non-negative integers, a1, a2, ..., an, and a target, S.
Now you have 2 symbols + and -. For each integer, you should choose one from + and -
as its new symbol. ***/
public class TargetSum {
	public int findTargetSumWays(int[] nums, int S) {
		int sumofAll = 0;
		int target;
		for(int i=0;i<nums.length;i++)
			sumofAll += nums[i];
		if(S>sumofAll || (sumofAll+S)%2 != 0)
			return 0;
		target = (sumofAll +S)/2;
		return find(target,nums);
	}
	private int find(int target,int[] nums){
		int[] dp = new int[target+1];
		dp[0] = 1;
		for(int i=0;i<nums.length;i++)
			for(int j=target;j-nums[i]>=0;j--)
				dp[j] += dp[j-nums[i]];
		return dp[target];
	}
}
/***TC**: O(n^2)
**Method**: It is equivalent to finding the number  of ways to partition the given 
subset into (target + sumOfAll)/2.
                  **sum(P) - sum(N) = target
sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
                       2 * sum(P) = target + sum(nums)**
Hence if the total sum > sumOfall or target+sum(nums) is odd then no ways are possible
else find the total possible ways to form sum(P). where sum(p) is the subset 
of positives.*/