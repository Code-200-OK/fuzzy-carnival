package others;

public class ValidTriangle {
	private static boolean valid(Point a, Point b, Point c){
		double one = len(a,b);
		double two = len(a,c);
		double three = len(b,c);
		if(one+two<three || one+three<two || two+three<one)
			return false;
		if(one==two && two==three)
			System.out.println("Equilateral");
		else
			System.out.println("Not Equilateral");
		if(one==two || two==three || one==three)
			System.out.println("Isoceles");
		else
			System.out.println("Not isosceles");
		return true;
	}
	private static double len(Point a, Point b){
		return Math.sqrt(Math.pow((a.x-b.x), 2)+Math.pow((a.y-b.y),2));
	}
	public static void main(String[] args){
		System.out.println(valid(new Point(0,0),new Point(0,1),new Point(1,0.5)));

	}
}
