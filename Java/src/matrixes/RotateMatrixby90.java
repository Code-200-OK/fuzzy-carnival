package matrixes;

/*You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).*/

public class RotateMatrixby90 {
	public void rotate(int[][] matrix) {
		if(matrix==null || matrix.length==0 || matrix[0].length==0)
			return;
		int s = matrix.length;
		int t;
		for(int i=0;i<s;i++)
			for(int j=0;j<i;j++){
				t = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = t;
			}
		for(int i=0;i<s;i++){
			for(int j=0;j<=s/2-1;j++){
				t = matrix[i][j];
				matrix[i][j] = matrix[i][s-j-1];
				matrix[i][s-j-1] = t;
			}
		}
	}
}
/*TC: O(n^2)
SC: O(1)
Method: 
CLOCKWISE: Transpose the matrix on the topleft-bottomright diagonal and then mirror the image.
ANTICLOCK:  Transpose the matrix on the topright-bottomleft diagonal and then mirror the image.
For not square matrix:

for(int i=0;i<R;i++)
for(int j=0;j<C;j++){
	new[j][R-1-i] = a[i][j]
}*/
