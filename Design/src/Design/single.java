package Design;

public class single {
	private static String name;
	private static final single INSTANCE = new single();
	private single() {
	}
	public String getName(){
		return name;
	}
	public void setName(String id){
		name = id;
	}
	public static single getInstance(){
		return INSTANCE;
	}
}


