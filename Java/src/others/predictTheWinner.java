package others;

import java.util.Arrays;

/***Given an array of scores that are non-negative integers. Player 1 picks one of the numbers from either end 
of the array followed by the player 2 and then player 1 and so on. Each time a player picks a number, that 
number will not be available for the next player. This continues until all the scores have been chosen.
The player with the maximum score wins.
Given an array of scores, predict whether player 1 is the winner. You can assume each player plays to 
maximize his score. ***/
public class predictTheWinner {
	int[][] dp;
	public boolean PredictTheWinner(int[] nums) {
		if(nums==null || nums.length==0)
			return true;
		int totalSum = 0;
		for(int i=0;i<nums.length;i++)
			totalSum += nums[i];
		dp = new int[nums.length][nums.length];
		for(int[] row: dp)
			Arrays.fill(row,-1);
		int maxPossible = findMax(0,nums.length-1,nums);
		return totalSum-maxPossible<=maxPossible ? true:false;
	}
	private int findMax(int i, int j,int[] nums){
		if(i>j)     return 0;
		if(i==j)    return nums[i];
		if(i+1==j)  return Math.max(nums[i],nums[j]);
		if(dp[i][j]==-1)
			dp[i][j] = Math.max(Math.min(findMax(i+1,j-1,nums),findMax(i+2,j,nums))+nums[i],Math.min(findMax(i,j-2,nums),findMax(i+1,j-1,nums))+nums[j]);
		return dp[i][j];
	}
}
/***TC**: O(n^2) as is clear from the second form of solution where we use two nested for loops.
**Method**: DP. dp[i][j] = max sum the first player can form by using the elements in the range [i,j].
Base cases are i==j, i+1==j. Also i<=j. For these cases we assume that we chose one of the ends for the 
next chance the player chooses the best, so we have to consider the minimum of our possible chances for 
our next iteration.*/

// SECOND FORM OF DP
/*
public boolean PredictTheWinner(int[] nums) {
	if(nums==null || nums.length==0)
		return true;
	int totalSum = 0;
	for(int i=0;i<nums.length;i++)
		totalSum += nums[i];
	int[][] dp = new int[nums.length][nums.length];
	for(int i=nums.length-1;i>=0;i--)
		for(int j=i;j<=nums.length-1;j++){
			if(i==j) dp[i][j]=nums[i];
			else if(i+1==j) dp[i][j] = Math.max(nums[i],nums[j]);
			else dp[i][j] = Math.max(Math.min(dp[i+1][j-1],dp[i+2][j])+nums[i],Math.min(dp[i][j-2],dp[i+1][j-1])+nums[j]);
		}
	return totalSum-dp[0][nums.length-1]<=dp[0][nums.length-1] ? true:false;
}*/

