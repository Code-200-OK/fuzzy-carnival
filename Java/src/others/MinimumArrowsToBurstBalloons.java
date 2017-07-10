package others;

import java.util.Arrays;
import java.util.Comparator;

/***There are a number of spherical balloons spread in two-dimensional space. For each 
 * balloon, provided input is the start and end coordinates of the horizontal 
 * diameter. Since it's horizontal, y-coordinates don't matter and hence the
 *  x-coordinates of start and end of the diameter suffice. Start is always 
 *  smaller than end. There will be at most 104 balloons.
An arrow can be shot up exactly vertically from different points along 
the x-axis. A balloon with xstart and xend bursts by an arrow shot at x 
if xstart <= x<= xend. There is no limit to the number of arrows that can be shot. 
An arrow once shot keeps travelling up infinitely. The problem is to find the 
minimum number of arrows that must be shot to burst all balloons. **
*/
public class MinimumArrowsToBurstBalloons {
    public int findMinArrowShots(int[][] points) {
        if(points==null || points.length==0)
            return 0;
        Arrays.sort(points, new Comparator<int[]>(){
           public int compare(int[] a, int[] b){
               if(a[0]==b[0]) return a[1]-b[1];
               return a[0]-b[0];
           } 
        });
        int count=1;
        int end = points[0][1];
        for(int i=1;i<points.length;i++){
            if(points[i][0]>end){
                count++;
                end = points[i][1];
            }
            else end = Math.min(end,points[i][1]);
        }
        return count;
    }
}
/*```
**TC**: O(nlogn)
**SC**: O(1)
**Method**: Greedy. Sort the given diameters on the basis of their start points. 
*then find all the overlapping ones. Whose start falls within current end, is 
*considered to be in the same group.
*/