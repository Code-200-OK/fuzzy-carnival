package algorithms;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

// CLASSICS BFS
// USED: MAP OF SETS FOR STORING THE GRAPH
// IMPORTANT: START WITH MARKING START NODE AS VISITED AND SETTING IT'S DISTANCE AS 0 AND PUT IT INTO THE QUEUE
// NOW FOR EACH UNVISITED NEIGHBOUR OF ANY NODE IN THE QUEUE, SET IT AS VISITED AND SET IT'S DISTANCE USING THE EDGE 
// BETWEEN BOTH OF THESE. 

// ALWAYS SET AS VISITED AFTER ADDING TO QUEUE. AND ONLY CONSIDER UNVISITED NODES TO BE ADDED TO QUEUE.S 

public class BFS {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int q = s.nextInt();
		for(int i=0;i<q;i++){
			// each query
			int numOfNodes = s.nextInt();
			int numOfVer = s.nextInt();
			Map<Integer,Set<Integer>> graph = new HashMap<Integer,Set<Integer>>();
			int node1,node2;
			for(int j=0;j<numOfVer;j++){
				node1 = s.nextInt();
				node2 = s.nextInt();
				if(!graph.containsKey(node1))
					graph.put(node1,new HashSet<Integer>());
				if(!graph.containsKey(node2))
					graph.put(node2,new HashSet<Integer>());
				graph.get(node1).add(node2);
				graph.get(node2).add(node1);
			}
			int stnode = s.nextInt();
			Queue<Integer> next = new LinkedList<Integer>();
			int[] distance = new int[numOfNodes];
			Arrays.fill(distance, -1);
			int[] visited = new int[numOfNodes];
			visited[stnode-1]=1;
			distance[stnode-1] = 0;
			next.offer(stnode);
			int node;
			Set<Integer> neigh = new HashSet<Integer>();
			while(!next.isEmpty()){
				node = next.poll();
				// put all unvisited neighbours of node into the queue
				if(graph.containsKey(node)){
					neigh = graph.get(node);
					for(Integer a:neigh)
						if(visited[a-1]==0){
							visited[a-1]=1;
							next.offer(a);
							distance[a-1]= distance[node-1]+6;
						} 
				}
				
			}
			for(int j=0;j<numOfNodes;j++){
				if(j==stnode-1)continue;
				System.out.print(distance[j]+" ");
			}
			System.out.println();

		}
		/* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
	}
}