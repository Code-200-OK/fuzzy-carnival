package chapter1_Arrays_Strings;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ZeroMatrix {

	public static void main(String[] args) {
		int[][] m = {{1,0,3,4},
				{5,6,7,0},
				{9,10,11,12},
				{0,14,15,16}};
		zeroMatrix(m);
		for(int i=0;i<m.length;i++){
			System.out.println();
			for(int j=0;j<m[0].length;j++){
				System.out.print(m[i][j]+"--->");			
			}		
		}

	}
	public static void zeroMatrix(int[][] m){
		List<int[]> positions = new ArrayList<>();
		for(int i=0;i<m.length;i++)
			for(int j=0;j<m[0].length;j++){
				
				
			
				if(m[i][j]==0){
					int[] pos = new int[2];
					pos[0] = i;
					pos[1] = j;
					positions.add(pos);
				}			
			}
		Set<String> done = new HashSet<>();
		for(int i=0;i<positions.size();i++){
			int[] pos = positions.get(i);
			String s = "Row"+ pos[0];
			String t = "Col" + pos[1];
			if(!done.contains(s)){
				doR(pos[0],m);
				done.add(s);
				}
			if(!done.contains(t)){
				doC(pos[1],m);
				done.add(t);
				}
		}
	}
	public static void doR(int row,int[][] m){
		int r = m.length;
		int c = m[0].length;

		for(int i=0;i<c;i++){
			m[row][i] = 0;		
		}	
	}
	public static void doC(int col, int[][] m){
		int r = m.length;
		int c = m[0].length;
		for(int i=0;i<r;i++){
			m[i][col] = 0;		
		}	
	}

}

