package matrixes;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ShortestPathMazeBFS {
	private static int[][] m;
	private static int path(int[][] maze,int si, int sj, int di, int dj){
		int rows = maze.length;
		int cols = maze[0].length;
		Set<Pair> start = new HashSet<Pair>();
		Set<Pair> end = new HashSet<Pair>();
		Set<Pair> visited  = new HashSet<Pair>();
		int[] rchange = new int[]{1,0,-1,0};
		int[] cchange = new int[]{0,1,0,-1};
		//Set<Pair> swap = new HashSet<Pair>();
		int cur = 0;
		start.add(new Pair(si,sj));
		//start.add(new Pair(0,1));
		end.add(new Pair(di,dj));
		m = new int[rows][cols];
		for(int i=0;i<rows;i++)
			for(int j=0;j<cols;j++)
				m[i][j] = maze[i][j];
		//Scanner s = new Scanner(System.in);
		while(!(start.size()==0 || end.size()==0)){
			
			if(start.size()>end.size()){
				Set<Pair> swap = start;
				start = end;
				end = swap;
			}
			System.out.println(start);
			System.out.println(end);
			//s.nextLine();
			Set<Pair> temp = new HashSet<Pair>();
			for(Pair cell: start){
				visited.add(cell);
				for(int i=0;i<4;i++){
					if(isSafe(cell.x+rchange[i],cell.y+cchange[i],rows,cols)){
						Pair next = new Pair(cell.x+rchange[i],cell.y+cchange[i]);
						if(visited.contains(next)) continue;
						if(!end.contains(next)) temp.add(next);
						else return ++cur;
					}
				}
			}
			cur++;
			start = temp;
		}
		return cur;
	}
	private static boolean isSafe(int i, int j,int R, int C){
		return i>=0 && i<R && j>=0 && j<C && m[i][j]==0;
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
class Pair implements Comparable<Pair>{
	int x,y;
	public Pair(int a, int b){
		x=a;
		y=b;
	}
	public boolean equals(Object a){
		Pair b = (Pair) a;
		return x==b.x && y==b.y;
	}
	public int hashCode(){
		return x*10+y;
	}
	public int compareTo(Pair a) {
		return y-a.y;
	}
	public String toString(){
		return getClass().getSimpleName()+"["+x+":"+y+"]";
	}
}
