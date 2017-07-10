package javaSyntax;


// MUTABLE OBJECT. i.e. actual content changed
public class StringBuilders {
	public static void main(String[] args){
		StringBuilder a = new StringBuilder("sakthi");
		char[] b = {'z','g'};
		System.out.println("toString:\t"+a.toString());
		System.out.println("length:\t"+a.length());
		System.out.println("charAt:\t"+a.charAt(2));
		System.out.println("indexOf:\t"+a.indexOf("kthi",0)); // else -1
		a.setCharAt(2, 'z'); // has to be within range 0 based position.
		System.out.println("setCharAt:\t"+a);
		a.append("vel");
		a.append(b);
		a.append('c');
		a.append(456);
		System.out.println("append:\t"+a);
		System.out.println("reverse:\t"+a);
		a.append(a);
		System.out.println("append:\t"+a);
		a.delete(2, 4);
		System.out.println("delete:\t"+a);
		StringBuilder ab = new StringBuilder("01234");
		ab.insert(3, "vel"); // zero based index
		System.out.println("insert:\t"+ab);
		System.out.println("subSequence:\t"+ab.subSequence(2, 5));
		
	}
}
