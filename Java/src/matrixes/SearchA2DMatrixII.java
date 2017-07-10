package matrixes;

/*Write an efficient algorithm that searches for a value in an m x n matrix. 
 * This matrix has the following properties:

    Integers in each row are sorted in ascending from left to right.
    Integers in each column are sorted in ascending from top to bottom.
*/
public class SearchA2DMatrixII {
	public boolean searchMatrix(int[][] matrix, int target) {
		if(matrix == null || matrix.length < 1 || matrix[0].length <1) {
			return false;
		}
		int col = matrix[0].length-1;
		int row = 0;
		while(col >= 0 && row <= matrix.length-1) {
			if(target == matrix[row][col]) {
				return true;
			} else if(target < matrix[row][col]) {
				col--;
			} else if(target > matrix[row][col]) {
				row++;
			}
		}
		return false;
	}
}
/*
TC: O(m+n)
Method: Start at the top-right corner. Move left or down accordingly. */
