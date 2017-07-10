package trees;

/***Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
For example,
Given n = 3, there are a total of 5 unique BST's. **
*/public class BinarySearchTreeUniqueBSTs {
	public int numTrees(int n) {
		if(n<0)
			return 0;
		int[] dp = new int[n+1];
		dp[0]=1;
		int num= 0;
		for(int i=1;i<=n;i++){
			num = 0;
			for(int j=1;j<=i;j++)
				num += dp[i-j]*dp[j-1];
			dp[i]=num;
		}
		return dp[n];
	}
}
/***TC**: O(n^2)
**Method**: DP. Dp[i]: How many unique bsts can be formed of 1to i. Now dp[i] = sum of 
all dp[0]*dp[i-1] till dp[i-1]*dp[0].
Eg: for n = 5.
* [num_of_trees for 1-4] 5_as_root [null]
* [num_of_trees for 1-3] 4_as_root [num_of_trees for 5]
* [num_of_trees for 1-2] 3_as_root [num_of_trees for 4-5]
* [num_of_trees for 1] 2_as_root [num_of_trees for 3-5]
* [null] 1_as_root [num_of_tree for 2-5].
*/
