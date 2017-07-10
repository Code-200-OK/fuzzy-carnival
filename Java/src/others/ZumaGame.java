package others;

import java.util.HashMap;
import java.util.Map;

/*
 * **Each time, you may choose a ball in your hand, and insert it into the row (including the leftmost place and rightmost place). Then, if there is a group of 3 or more balls in the same color touching, remove these balls. Keep doing this until no more balls can be removed.
Find the minimal balls you have to insert to remove all the balls on the table. If you cannot remove all the balls, output -1.**
 * 
 */
public class ZumaGame {
    Map<Character,Integer> handMap;
    public int findMinStep(String board, String hand) {
        if(board==null || board.length()==0) return 0;
        handMap = new HashMap<Character,Integer>();
        for(int i=0;i<hand.length();i++)handMap.put(hand.charAt(i),handMap.getOrDefault(hand.charAt(i),0)+1);
        return dfs(board);
    }
    public int dfs(String s){
        //System.out.println(s);
        s=truncate(s);
        //System.out.println("TU:"+s +":"+s.length());
        if(s.length()==0) return 0;
        char c;
        int ans = Integer.MAX_VALUE;
        int t;
        int start = 0,end = 0,len=s.length();
        while(start<=end && end<=len-1){
            while(end<=len-1 && s.charAt(end)==s.charAt(start))end++;
            c = s.charAt(start);
            if(handMap.getOrDefault(c,0)>=(3-(end-start))){
                handMap.put(c,handMap.get(c)-(3-(end-start)));
                t = dfs(s.substring(0,start)+s.substring(end,s.length()));
                if(t!=-1) ans = Math.min(ans,t+(3-(end-start)));
                handMap.put(c,handMap.get(c)+(3-(end-start)));
            }
            start = end;
        }
        return ans==Integer.MAX_VALUE?-1:ans;
    }
    public String truncate(String s){
        int start = 0,end = 0,len=s.length();
        while(start<=end && end<=len-1){
            while(end<=len-1 && s.charAt(end)==s.charAt(start))end++;
            if(end-start>=3)
                return truncate(s.substring(0,start)+s.substring(end,len));
            start = end;
        }
        return s;
    }
    }
/*
 * **Method**: DFS. No memoization. Try every possibility.Make a map of hand. For every continous streak of Alphabets, see if 3-that number is present if yes then proceed.
 */
