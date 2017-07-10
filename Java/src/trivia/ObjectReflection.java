package trivia;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// Getting information about the methods and fields present inside the class at runtime.
// Creating a new instance of a class
// Getting and setting the object fields directly by getting field reference, regardless of
// what the access modifier is

/*
 * 				USES:
 * Debugging & testing as we have direct access to methods, constructors & Fields.
 * Observing & manupulating the runtime behaviour of class
 * We can call methods by name, by allowing user to pass className, methodName, and constructor arguments.
 */

public class ObjectReflection {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		// Below is the equivalent code of following
		/*Rectangle rectangle = new Rectangle(4.2,3.9);
		System.out.println(rectangle.area());*/
		
		/* Parameters */
		Object[] doubleArgs = new Object[] {4.2, 3.9};
		
		/* Get class */
		Class rectangleDefinition = Class.forName("trivia.Rectangle");
		
		/* Equivalent: Rectangle rectangle = new Rectangle(4.2, 3.9); */
		Class[] doubleArgsClass = new Class[] {double.class, double.class};
		Constructor doubleArgsConstructor = rectangleDefinition.getConstructor(doubleArgsClass);
		Rectangle rectangle = (Rectangle) doubleArgsConstructor.newInstance(doubleArgs);
		
		
		/* Equivalent: Double area = rectangle.area(); */
		Method m = rectangleDefinition.getDeclaredMethod("area");
		Double area = (Double) m.invoke(rectangle);
		System.out.println(area);
		
	}
	
}
class Rectangle{
	double len,wid;
	public Rectangle(double l, double w){
		len = l;
		wid = w;
	}
	public double area(){
		return len*wid;
	}
}
