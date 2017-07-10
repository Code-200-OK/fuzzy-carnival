package others;

/***Suppose you have N integers from 1 to N. We define a beautiful arrangement as an array that is constructed by
 *  these N numbers successfully if one of the following is true for the ith position (1 <= i <= N) in this array:
The number at the ith position is divisible by i.
i is divisible by the number at the ith position.
Now given N, how many beautiful arrangements can you construct?***/
public class BeautifulArrangement {
    int[] dp;
    public int countArrangement(int N) {
        dp = new int[N+1];
        return find(1,N);
    }
    public int find(int pos,int N){
        if(pos==N+1) return 1;
        int ans = 0;
        for(int i=1;i<=N;i++){
            if(dp[i]==1)continue;
            if(pos%i==0 || i%pos==0){
                dp[i]=1;
                ans += find(pos+1,N);
                dp[i]=0;
            }
        }
        return ans;
    }
}
/***TC**: O(n!)
**Method**: Backtracking. Simply try 1 to N for every position, and remember that numbers cannot be reused.*/