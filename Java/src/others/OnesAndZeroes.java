package others;
/*
 * **In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.
For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there is an array with strings consisting of only 0s and 1s.
Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used at most **
 */
public class OnesAndZeroes {
	/*public static int findMaxForm(String[] strs, int m, int n) {
        int num = strs.length;
		return find(num-1,n,m,strs);
    }
    private static int find(int pos, int one, int zero, String[] strs){
        if(pos<0 || !(one>0 || zero>0))return 0;
        int[] st = count(strs[pos]);
        if(st[0]>zero || st[1]>one)
            return find(pos-1,one,zero,strs);
        return Math.max(1+find(pos-1,one-st[1],zero-st[0],strs),find(pos-1,one,zero,strs));
    }
    private static int[] count(String str){
        int[] ans =  new int[2];
        char[] letters = str.toCharArray();
        for(int i=0;i<letters.length;i++)
            if(letters[i]=='1') ans[1]++;
            else ans[0]++;
        return ans;
    }*/
	public static int findMaxForm(String[] strs, int m, int n) {
        int num = strs.length;
        int[][] dp = new int[m+1][n+1];
        int max = 0;
        int[] co= new int[2];
        for(String s: strs){
            co = count(s);
            for(int i=m;i-co[0]>=0;i--){
                for(int j=n;j-co[1]>=0;j--){
                    dp[i][j] = Math.max(dp[i][j],1+dp[i-co[0]][j-co[1]]);
                    max = Math.max(max,dp[i][j]);
                }
            }
        }
		return max;
    }
    private static int[] count(String str){
        int[] ans =  new int[2];
        char[] letters = str.toCharArray();
        for(int i=0;i<letters.length;i++)
            if(letters[i]=='1') ans[1]++;
            else ans[0]++;
        return ans;
    }
    public static void main(String[] args){
    	long start = System.currentTimeMillis();
    	String[] a = new String[]{"0","11","1000","01","0","101","1","1","1","0","0","0","0","1","0","0110101","0","11","01","00","01111","0011","1","1000","0","11101","1","0","10","0111"};
    	System.out.println(findMaxForm(a,9,80));
    	System.out.println(System.currentTimeMillis()-start);
    }
}
/*
 * **Method**: Typical application of bagpack problem. Using a multi dimensional array to store the max reachable sum.
 */
