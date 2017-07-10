package chapter4_Trees_Graphs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class RouteBetweenTwoNodes {
	
	public static boolean find(GraphNode<Integer> source, GraphNode<Integer> dest){
		if(source == dest) return true;
		Queue<GraphNode<Integer>> bfs = new LinkedList<GraphNode<Integer>>();
		Set<GraphNode<Integer>> visited = new HashSet<GraphNode<Integer>>();
		bfs.offer(source);
		visited.add(source);
		while(!bfs.isEmpty()){
			GraphNode g = bfs.poll();
			List<Edge> ga = g.edges;
			for(Edge e: ga){
				GraphNode end = e.end;
				if(!visited.contains(end)){
					if(end == dest) return true;
					bfs.offer(end);
					visited.add(end);			
				}			
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int[][] b = {
			{0,1,0,0,0,0,0},
			{1,0,1,1,0,0,0},
			{0,1,0,1,1,0,0},
			{0,1,1,0,0,0,0},
			{0,0,1,0,0,0,0},
			{0,0,0,0,0,0,1},
			{0,0,0,0,0,1,0}
			};
		MyGraph a = new MyGraph();
		a.form(b);
		System.out.println(find(a.nodes.get(5),a.nodes.get(6)));
	}

}
