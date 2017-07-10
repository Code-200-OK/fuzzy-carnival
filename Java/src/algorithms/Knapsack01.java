package algorithms;

/*
 * Given :	values,weights and weight array.
 * 
 * Method:  dynamic programming
 */
public class Knapsack01 {
	public static int knap(int[] values, int[] weights,int weight){
		int num = values.length;
		int[][] dp = new int[num][weight+1];
		
		for(int i=0;i<num;i++){
			for(int j=0;j<=weight;j++){
				if(j==0)continue;
				if(i==0 && j>=weights[i]) dp[i][j]=values[i];
				else if(i==0 && j<weights[i]) dp[i][j]=0;
				else if(weights[i]>j)dp[i][j]=dp[i-1][j];
				else dp[i][j] = Math.max(dp[i-1][j], values[i]+dp[i-1][j-weights[i]]);
			}
		}
		return dp[num-1][weight];
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
/*		int val[] = new int[]{60, 100, 120};
        int wt[] = new int[]{10, 20, 30};
        int  W = 50;*/
        int val[] = new int[]{10,20,30};
        int wt[] = new int[]{1,1,1};
        int  W = 2;
		System.out.println(knap(val,wt,W));
		
	}

}
