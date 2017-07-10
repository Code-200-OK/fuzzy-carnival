package javaSyntax;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Arrayss {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] points = new int[][]{
			{1,2},
			{0,4},
			{3,5},
			{-1,5}
		};
		
		Arrays.sort(points, new Comparator<int[]>(){
	           public int compare(int[] a, int[] b){
	               if(a[0]==b[0]) return a[1]-b[1];
	               return a[0]-b[0];
	           } 
	        });
		Character[] row = {'q','w','e','r','t','y','u','i','o','p'};
	    List<Character> one = Arrays.asList(row);
	    List<Character> two = Arrays.asList('A','B');
	    
	    
	    // Arrays to List
	    String[] b = new String[]{"one","two"};
	    List<String> c = Arrays.asList(b);
	    
	    // List to Array
	    String[] answer = c.toArray(new String[0]);
	    
	    // creates a mutable list from an array
	    List<Integer> a = new ArrayList<Integer> (Arrays.asList(5));
	}

}
