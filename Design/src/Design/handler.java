package Design;

public class handler {
public shape  getShape(String type){
	if(type==null) return null;
	if(type.equalsIgnoreCase("circle")) return new Circle();
	if(type.equalsIgnoreCase("rectangle")) return new Rectangle();
	return null;
}
}
