package others;

/*Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate 
(i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and
(i, 0). Find two lines, which together with x-axis forms a container, such that the container 
contains the most water. */
public class ContainerWithMostWater {
	public int maxArea(int[] height) {
		int max = 0;
		int st = 0;
		int end = height.length-1;
		while(st<end){
			max= Math.max(max,(end-st)*Math.min(height[st],height[end]));
			if(height[st]<height[end])
				st++;
			else
				end--;
		}
		return max;
	}
}
/*TC: O(n)
Method: Two Pointers. Start by max area =0. current area would be 
(end pointer - start pointer )* distance between them. if start was lesser then increment 
start else decrement end.*/