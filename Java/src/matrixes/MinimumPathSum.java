package matrixes;
/***Given a m x n grid filled with non-negative numbers, find a path from top left 
to bottom right which minimizes the sum of all numbers along its path.***/
public class MinimumPathSum {
	public int minPathSum(int[][] grid) {
		if(grid==null || grid.length ==0 || grid[0].length==0)
			return 0;
		int row = grid.length;
		int col = grid[0].length;
		int[][] dp = new int[row][col];
		for(int i=0;i<row;i++)
			for(int j=0;j<col;j++){
				if(i==0 && j==0) dp[i][j] = grid[i][j];
				else if(j==0)dp[i][j] = grid[i][j]+dp[i-1][j];
				else if(i==0)dp[i][j] = grid[i][j]+dp[i][j-1];
				else dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];
			}
		return dp[row-1][col-1];
	}
}
/***TC**: O(mn)
**Method**: Same as Unique Paths. For normal case, choose the minimum of upper cell
* and left cell and add current cell's value.
**Important**:
To fill matrix with -1
```
for(int[] roww: dp)
            Arrays.fill(roww,-1);
```*/