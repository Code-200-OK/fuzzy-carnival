package Design;

public class check {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		single a = single.getInstance();
		a.setName("sakthi");
		single b = single.getInstance();
		b.setName("vel");
		System.out.println(b.getName());
		System.out.println(a.getName());
		
		handler h = new handler();
		shape one = h.getShape("circle");
		shape two = h.getShape("rectangle");
		one.draw();
		two.draw();
		
	}

}
