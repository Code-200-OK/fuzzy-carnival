package matrixes;

/***Given an integer matrix, find the length of the longest increasing path.
From each cell, you can either move to four directions: left, right, up or down.
You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is 
		not allowed).***/
public class LongestIncreasingPathInAMatrix {
	private int[][] dp;
	private int r,c;
	public int longestIncreasingPath(int[][] matrix) {
		if(matrix==null || matrix.length==0 || matrix[0].length==0)
			return 0;
		r = matrix.length;
		c = matrix[0].length;
		dp = new int[r][c];
		int ans = 0;
		for(int i = 0;i<r;i++)
			for(int j=0;j<c;j++){
				ans = Math.max(ans,find(i,j,matrix));
			}
		return ans;
	}
	private int find(int i,int j, int[][] m){
		if(dp[i][j]!=0) return dp[i][j];
		int t = 0;
		int co = m[i][j];
		if(i>=1 && m[i-1][j]>co)
			t = Math.max(t,find(i-1,j,m));
		if(j>=1 && m[i][j-1]>co)
			t = Math.max(t,find(i,j-1,m));
		if(j<=c-2 && m[i][j+1]>co)
			t = Math.max(t,find(i,j+1,m));
		if(i<=r-2 && m[i+1][j]>co)
			t = Math.max(t,find(i+1,j,m));
		dp[i][j] = 1+t;
		return dp[i][j];
	}
}
/***TC**: O(mn). For each cell it is computed only once.

**Method**: DP. DP[i][j]: Length of the longest increasing sequence. 
DP[i][j] = Math.max (1+ DP of all the adjacent ones who are larger than this value).*/
