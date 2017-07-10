package threads;

public class Hello extends Myabstract implements Myinterface {

	
	Hello(int z) {
		super(z);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Hello b =  new Hello(23);
		b.dis();
		System.out.println(b.a);
		System.out.println(b.f);
		try {
			b.finalize();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public int show(int b) {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	int shh() {
		// TODO Auto-generated method stub
		return 0;
	}

	/*@Override
	void dis() {
		// TODO Auto-generated method stub
		System.out.println("Hello display");
	}*/

}
