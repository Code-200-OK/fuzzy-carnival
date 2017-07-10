package matrixes;

/***Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An 
island is surrounded by water and is formed by connecting adjacent lands horizontally 
or vertically. You may assume all four edges of the grid are all surrounded by water.***/
public class NumberOfIslands {
	public int numIslands(char[][] grid) {
		if(grid==null)
			return 0;
		int ans = 0;
		for(int i=0;i<grid.length;i++)
			for(int j=0;j<grid[0].length;j++){
				if(grid[i][j]=='1')
				{
					fill(i,j,grid);
					ans++;
				}
			}
		return ans;
	}
	private void fill(int r, int c, char[][] a){
		if(r<0 || r>= a.length || c<0 || c>=a[0].length || a[r][c]=='0')
			return;
		a[r][c]='0';
		fill(r+1,c,a);
		fill(r-1,c,a);
		fill(r,c+1,a);
		fill(r,c-1,a);
	}
}
/***TC**: O(mn)
**Method**: DFS. For every 1 encountered, count that as a new island and possibly enter
every adjacent land and drown it by setting to 0.*/
