package javaSyntax;

// IMMUTABLE Object. i.e. copies created
public class Strings {
	public static void main(String[] args){
		String a = "sakthi";
		String b = "    vel   ";
		String c = "Sakthi";

		System.out.println("equals:\t"+a.equals(c));
		System.out.println("indexOf:\t"+a.indexOf("kthi",0)); // resultPos>= arg[1]. First occurence.else -1
		System.out.println("charAt:\t"+a.charAt(2));
		System.out.println("compareTo:\t"+a.compareTo(c)); // caps is lexicographically smaller
		System.out.println("compareToIgnoreCase:\t"+a.compareToIgnoreCase(c));
		System.out.println("equalsIgnoreCase:\t"+a.equalsIgnoreCase(c));
		System.out.println("length:\t"+a.length());
		System.out.println("matches:\t"+a.matches("/*a*/"));
		System.out.println("substring:\t"+a.substring(2, 5)); // [2,5)
		System.out.println("trim:\t"+b.trim()); // leading & trailing white space
		System.out.println("toCharArray:\t"+a.toCharArray()[2]);
		System.out.println("toLowerCase:\t"+c.toLowerCase());
		System.out.println("toUpperCase:\t"+c.toUpperCase());
		System.out.println("subSequence:\t"+a.subSequence(2, 5)); // returns char seq [2,5)
		System.out.println("startsWith:\t"+a.startsWith("ak"));
		System.out.println("endsWith:\t"+a.endsWith("hi"));
		System.out.println("split:\t"+a.split("h")[0]);
		System.out.println("valueOf:\t"+String.valueOf(new char[]{'a','b','c'})); // char array to string
		String d = "asdf,asdfasdf,asdfasdf,";
		System.out.println(d.split(",").length);
	}
}
