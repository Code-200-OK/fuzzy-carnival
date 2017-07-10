package javaSyntax;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Maps {
	public static void main(String[] args){
		Map<String,String> a= new HashMap<String,String>();
		Map<String,String> b= new  LinkedHashMap<String,String>();
		Map<String,String> c= new  TreeMap<String,String>();
		b.getOrDefault("key", "0");
		Iterator<Map.Entry<String,String>> d = b.entrySet().iterator();
		while(d.hasNext()){
			Map.Entry<String, String> e = d.next();
			e.getKey();
			e.getValue();
		}
	}
}