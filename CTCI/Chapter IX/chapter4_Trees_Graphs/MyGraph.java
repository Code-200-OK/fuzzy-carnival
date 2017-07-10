package chapter4_Trees_Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyGraph {
	Map<Integer,GraphNode<Integer>> nodes;
	public MyGraph(){
		nodes = new HashMap<Integer,GraphNode<Integer>>();	
	}
	public GraphNode<Integer> getNode(int name){
		if(!nodes.containsKey(name))
			nodes.put(name,new GraphNode(name));
		return nodes.get(name);
	}
	public void form(int[][] mat){
		for(int i=0;i<mat.length;i++)
			for(int j=0;j<mat[0].length;j++){
				if(mat[i][j]!=0){
					GraphNode<Integer> source = getNode(i);
					GraphNode<Integer> dest = getNode(j);
					Edge e = new Edge(mat[i][j],dest);
					source.addEdge(e);
				}			
			}	
	}

	public static void main(String[] args) {
		int[][] b = {
			{0,1,0,0,0},
			{1,0,1,1,0},
			{0,1,0,1,1},
			{0,1,1,0,0},
			{0,0,1,0,0}
			};
		MyGraph a = new MyGraph();
		a.form(b);
		for(Map.Entry<Integer, GraphNode<Integer>> node: a.nodes.entrySet()){
			System.out.println(node.getValue());
		}
		System.out.println(a.nodes);

	}

}

class GraphNode<T>{
	public T name;
	public List<Edge> edges;	// outgoing
	public GraphNode(T name){
		this.name = name;
		edges = new ArrayList<Edge>();	
	}
	public void addEdge(Edge e){
		edges.add(e);	
	}
	public String toString(){
		if(name instanceof Integer){
			StringBuilder s = new StringBuilder(Integer.toString((int)name) + "-->(");
			for(Edge e: edges)
				s.append(e.end.name+",");
			s.append(")");
			return s.toString();
		}
		else return "";
	}
	public int hashCode(){
		if(name instanceof Integer) return (int)name;
		if(name instanceof Character) return (Character)name - 'A';
		return 0;
	}
	public boolean equals(GraphNode<T> g){
		return this.name == g.name;
	}
}
class Edge{
	public int weight;
	public GraphNode end;
	public Edge(int w, GraphNode end){
		weight = w;
		this.end = end;
	}	
}
