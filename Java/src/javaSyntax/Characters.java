package javaSyntax;

// All of them start with Character.something() and not variable.something()
public class Characters {
	public static void main(String[] args){
		char c = '7';
		int b = 'a';
		char d = 'z';
		System.out.println("All of them start with Character.something() and not variable.something()");
		int a = Character.getNumericValue(c);
		System.out.println("getNumericValue:\t"+a);
		System.out.println("valueOf:\t"+Character.valueOf(d));
		System.out.println("Characters can be assigned to integers");
		System.out.println("a is:\t"+b);
		System.out.println("'z'-'a':\t"+('z'-'a'));
		System.out.println("toString:\t"+Character.toString(d));
		System.out.println("toLowerCase:\t"+Character.toLowerCase(d));
		System.out.println("toUpperCase:\t"+Character.toUpperCase(d));
		//Character.toString(arg0);
		System.out.println("Strings & Characters can be added together");
		String s = "asdf"+'c';
		System.out.println(s);
	}
}
