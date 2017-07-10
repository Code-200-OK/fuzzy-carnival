package others;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*You are given several projects. For each project i, it has a pure profit Pi and a minimum capital of Ci is needed to start the corresponding project. Initially, you have W capital. When you finish a project, you will obtain its pure profit and the profit will be added to your total capital.

To sum up, pick a list of at most k distinct projects from given projects to maximize your final capital, and output your final maximized capital.
*/
public class IPO {
    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        int myC = W;
        PriorityQueue<pair> store = new PriorityQueue<pair>();
        List<pair> next = new ArrayList<pair>();
        for(int i=0;i<Profits.length;i++)
            store.offer(new pair(Capital[i],Profits[i]));
        for(int i=0;i<k;i++){
            while(!store.isEmpty() && store.peek().C>myC){
                next.add(store.poll());
            }
            if(store.isEmpty()) return myC;
            myC += store.poll().P;
            for(int j=0;j<next.size();j++)
                store.offer(next.get(j));
            next.clear();
        }
        return myC;
    }
}
class pair implements Comparable<pair>{
    int C;
    int P;
    public pair(int cap, int pro){
        C=cap;
        P=pro;
    }
    public int compareTo(pair p){
        return -1*(P-p.P);
    }
    public String toString(){
        return "["+C+","+P+"]";
    }
}
/*
 * Method: Greedy using PriorityQueue
 */
