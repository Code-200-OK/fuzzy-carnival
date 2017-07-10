package algorithms;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

// CLASSIC KRUSKAL FOR FINDING MINIMUM SPANNING TREE
// CONDITIONS FOR EDGES OF SAME WEIGHT IS THAT ONE WITH U+V MUST BE CONSIDERED BEFORE

// IMPORATANT: UNION-FIND METHOD FOR MANTATING SETS OF FORESTS FOR DETECTION OF CYCLES.
// REPRESENTATIVES MUST BE UPDATED AT EVERY UNION, AND FOR FIND WE NEED TO GO TILL WE FIND A REPRESENTATIVE.

// ALGORITHM:
// SORT EDGES ON THE BASIS OF WEIGHTS
// CONSIDER EDGES TILL THEY DON'T RESULT IN THE FORMATION OF ANY CYCLES
// N-1 EDGES MUST BE PRESENT

// TC: O(ElogE)
// ElogE for sorting the edges
// ElogV for inserting and checking cycle.
public class Kruskal {

	public static void main(String[] args) {
		/* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
		Scanner s = new Scanner(System.in);
		int numOfNodes = s.nextInt();
		int numOfEdges = s.nextInt();
		PriorityQueue<pair> graph = new PriorityQueue<pair>();
		for(int i=0;i<numOfEdges;i++)graph.offer(new pair(s.nextInt(),s.nextInt(),s.nextInt()));
		int done = 0;
		int total=0;
		pair p;
		int[] sets = new int[numOfNodes+1];
		for(int i=1;i<numOfNodes+1;i++)
			sets[i]=i;
		while(done<numOfNodes-1 && !graph.isEmpty()){
			p=graph.poll();
			if(find(p.x,sets)!=find(p.y,sets)){
				// No cycle
				done++;
				total += p.len;
				union(p.x,p.y,sets);
			}
		}
		if(done!=numOfNodes-1) System.out.println("0");
		else System.out.println(total);

	}
	public static void union(int a, int b , int[] E){
		int one = find(a,E);
		int two = find(b,E);
		if(one<two)E[two] = one;
		else E[one] = two;
	}
	public static int find(int element, int[] a){
		while(a[element]!=element)
			element = a[element];
		return element;
	}

}