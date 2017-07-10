package chapter4_Trees_Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BuildOrder {
	private static Set<Integer> unvisited;
	private static Set<Integer> visiting;
	private static Map<Integer,List<Integer>> nodes;
	private static StringBuilder answer;
	public static String build(int[] items, int[][] depend){
		unvisited = new HashSet<Integer>();
		visiting = new HashSet<Integer>();
		nodes = new HashMap<Integer,List<Integer>>();
		answer = new StringBuilder();
		for(Integer a : items)
			if(!unvisited.contains(a))
				unvisited.add(a);
		for(int i=0;i<depend.length;i++){
			if(!nodes.containsKey(depend[i][1]))
				nodes.put(depend[i][1],new ArrayList<Integer>());
			nodes.get(depend[i][1]).add(depend[i][0]);
		}
		for(int i=0;i<items.length;i++){
			if(!handle(items[i])) return null; 	
		}
		return answer.toString();
	}
	public static boolean handle(int node){
		if(visiting.contains(node)) return false;
		if(!unvisited.contains(node)) return true;
		unvisited.remove(node);
		visiting.add(node);
		List<Integer> neigh = nodes.getOrDefault(node,null);
		if(neigh != null){
			for(int i=0;i<neigh.size();i++){
				if(!handle(neigh.get(i))) return false;			
			}		
		}
		answer.append(node);
		visiting.remove(node);
		return true;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] depend = {
				{2,1},
				{3,2},
				{1,3}		
			};
		int[] items = {1,2,3};
		System.out.println(build(items,depend));
	}

}

