package matrixes;

/***if some obstacles are added to the grids. How many unique paths would there be?
An obstacle and empty space is marked as 1 and 0 respectively in the grid.
Movement: Down and Right.***/
public class UniquePathWithObstacles {
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		//Empty case
		if(obstacleGrid.length == 0) return 0;

		int rows = obstacleGrid.length;
		int cols = obstacleGrid[0].length;

		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				if(obstacleGrid[i][j] == 1)
					obstacleGrid[i][j] = 0;
				else if(i == 0 && j == 0)
					obstacleGrid[i][j] = 1;
				else if(i == 0)
					obstacleGrid[i][j] = obstacleGrid[i][j - 1] * 1;// For row 0, if there are no paths to left cell, then its 0,else 1
				else if(j == 0)
					obstacleGrid[i][j] = obstacleGrid[i - 1][j] * 1;// For col 0, if there are no paths to upper cell, then its 0,else 1
				else
					obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
			}
		}

		return obstacleGrid[rows - 1][cols - 1];
	}
}
/***TC**: O(n)
**Method**: Same as Unique Paths, just that for all obstacles the answer is 0.
And for the base case, once a 1 is encountered, rest of the row/col's ans becomes
zero as well.*/