package matrixes;


// DOES NOT WORK
// ACTUAL ANSWER IS : 6
// THIS GIVES:        10

public class ShortestPathMazeDFS {
	private static int[][] m;
	private static int[][] dp;
	private static int endi,endj;
	private static int path(int[][] maze,int si, int sj, int di, int dj){
		int rows = maze.length;
		int cols = maze[0].length;
		dp = new int[rows][cols];
		//0: non visited
		//-1: not possible
		//-2:running
		m = new int[rows][cols];
		for(int i=0;i<rows;i++)
			for(int j=0;j<cols;j++)
				m[i][j] = maze[i][j];
		endi=di;
		endj=dj;
		return find(si,sj);
	}
	private static int find(int r, int c){
		if(r==endi && c==endj)
			return 0;
		if(m[r][c]==1)
			return -1;
		if(dp[r][c]!=0)
			return dp[r][c];
		dp[r][c]=-2;
		int ans=Integer.MAX_VALUE,t=0;
		// DOWN
		if(isSafe(r+1,c)){
			t = find(r+1,c);
			if(t!=-1)
				ans = Math.min(ans,t+1);
		}
		//RIGHT
		if(isSafe(r,c+1)){
			t = find(r,c+1);
			if(t!=-1)
				ans = Math.min(ans,t+1);
		}
		//UP
		if(isSafe(r-1,c)){
			t = find(r-1,c);
			if(t!=-1)
				ans = Math.min(ans,t+1);
		}
		//LEFT
		if(isSafe(r,c-1)){
			t = find(r,c-1);
			if(t!=-1)
				ans = Math.min(ans,t+1);
		}
		dp[r][c] = ans==Integer.MAX_VALUE?-1:ans;
		return dp[r][c];
	}
	
	private static boolean isSafe(int i, int j){
		return i>=0 && i<dp.length && j>=0 && j<dp[0].length && dp[i][j]!=-2;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] maze = new int[][]{
				{1,1,0,0,0,0,0},
				{1,1,0,1,1,1,0},
				{1,1,0,1,1,1,0},
				{0,0,0,0,0,0,0},
				{0,1,1,1,1,0,1},
				{0,0,0,0,0,0,1}
		};
		System.out.println(path(maze,3,0,3,6));
	}

}
