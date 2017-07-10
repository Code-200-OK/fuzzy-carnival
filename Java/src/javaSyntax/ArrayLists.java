package javaSyntax;

import java.util.ArrayList;
import java.util.List;

public class ArrayLists {
public static void main(String[] args){
	List<String> a = new  ArrayList<String>();
	a.add("dskthi");
	System.out.println("add:\t"+a);
	a.add("vel");
	System.out.println("\t"+a);
	a.set(0, "sakthi");
	a.add(0, "first");
	System.out.println("set\t:"+a);
	a.remove(1);
	System.out.println("set\t:"+a);
}
}
