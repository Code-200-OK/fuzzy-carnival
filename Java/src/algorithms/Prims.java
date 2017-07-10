package algorithms;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


//CLASSIC PRIMS FOR FINDING MINIMUM SPANNING TREE

//ALGORITHM:
//CHOOSE A STARTING NODE
//SET IT AS VISITED AND PUT ALL THE NEIGHBOURING EDGES INTO A PRIORITYQUEUE
//NOW TILL N-1 EDGES HAVE BEEN CHOOSEN
//		CHOOSE THE MINIMUM EDGE AND CONSIDER IT ONLY IF ONE OF ITS NODES IS UNVISITED
//		MARK THE UNVISITED AS VISITED AND PUT ALL ITS NEIGHBOURING EDGES INTO THE PRIORITYQUEUE.

//N-1 EDGES MUST BE PRESENT

//TC: O(ElogV)
//E+V times outer loop
//LogV for polling 
public class Prims {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner s = new Scanner(System.in);
        int numOfNodes = s.nextInt();
        int numOfEdges = s.nextInt();
        Map<Integer,Set<pair>> graph = new HashMap<Integer,Set<pair>>();
        int one,two,th;
        for(int i=0;i<numOfEdges;i++){
            one = s.nextInt();
            two = s.nextInt();
            th = s.nextInt();
            if(!graph.containsKey(one)) graph.put(one,new HashSet<pair>());
            if(!graph.containsKey(two)) graph.put(two,new HashSet<pair>());
            graph.get(one).add(new pair(one,two,th));
            graph.get(two).add(new pair(two,one,th));
        }
       int st = s.nextInt();
       Set<pair> visitedEdges = new HashSet<pair>();
       int[] visitedNodes = new int[numOfNodes+1];
       visitedNodes[st]=1; 
       //System.out.println(graph);
       PriorityQueue<pair> so = new PriorityQueue<pair>();
       for(pair p: graph.get(st)){
           so.offer(p);
       }
       int done=0;
       pair min;
       int total=0;
       while(!so.isEmpty() && done<numOfNodes-1){
           // find the minimum edge
           min = so.poll();
           if(visitedNodes[min.x]==1 && visitedNodes[min.y]==1)continue;
           total += min.len;
           visitedNodes[min.y]=1;
           for(pair p: graph.get(min.y)){
           so.offer(p);
           }
           done++;
       }
       System.out.println(total);
    }

}
    class pair implements Comparable<pair>{
    int x;
    int y;
    int len;
    public pair(int a, int b, int c){
        x = a;
        y = b;
        len = c;
    }
    public int compareTo(pair p){
        if(len==p.len)return x+y-p.x-p.y;
        return len - p.len;
    }
    public boolean equals(Object a){
        pair p = (pair) a;
        return p.x==x && p.y==y && p.len==len;
    }
    public int hashCode(){
        return x+y+len;
    }   
    public String toString(){
        return "["+x+","+y+","+len+"]";
    }
}