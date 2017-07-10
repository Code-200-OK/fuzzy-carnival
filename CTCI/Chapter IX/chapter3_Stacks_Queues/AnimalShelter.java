package chapter3_Stacks_Queues;

import java.util.LinkedList;
import java.util.Queue;

abstract class Animal{
	int number;
	public Animal(int number){
		this.number = number;		
	}
}
class Dog extends Animal{
	public Dog(int num){
		super(num);
	}
	public String toString(){
		return "Dog"+ Integer.toString(this.number);
	}
}
class Cat extends Animal{
	public Cat(int num){
		super(num);
	}
	public String toString(){
		return "Cat"+ Integer.toString(this.number);
	}
}
public class AnimalShelter {
	
	Queue<Animal> up,down;
	public AnimalShelter(){
		up = new LinkedList<>();
		down = new LinkedList<>();		
	}
	public boolean isEmpty(){
		return up.size()+down.size()==0;
	}
	public void enqueue(Animal item){
		down.offer(item);		
	}
	public Animal dequeueAny() throws Exception{
		if(isEmpty()) throw new Exception("No Animals");
		if(!up.isEmpty()) return up.poll();
		return down.poll();
	}
	public Animal dequeueCat() throws Exception{
		if(isEmpty()) throw new Exception("No Animals");
		if(!up.isEmpty() && up.peek() instanceof Cat) return up.poll();
		while(!down.isEmpty() && down.peek() instanceof Dog) up.offer(down.poll());
		if(!down.isEmpty() && down.peek() instanceof Cat) return down.poll();
		throw new Exception("No Cats");	
	}
	public Animal dequeueDog() throws Exception{
		if(isEmpty()) throw new Exception("No Animals");
		if(!up.isEmpty() && up.peek() instanceof Dog) return up.poll();
		while(!down.isEmpty() && down.peek() instanceof Cat) up.offer(down.poll());
		if(!down.isEmpty() && down.peek() instanceof Dog) return down.poll();
		throw new Exception("No Dogs");	
	}

	public static void main(String[] args) {
		try{
		Animal one = new Cat(1);
		Animal two = new Dog(1);
		Animal thr = new Dog(2);
		Animal fou = new Cat(2);
		Animal fiv = new Dog(3);
		Animal six = new Cat(3);
		AnimalShelter obj = new AnimalShelter();
		obj.enqueue(one);
		obj.enqueue(two);
		obj.enqueue(thr);
		obj.enqueue(fou);
		obj.enqueue(fiv);
		obj.enqueue(six);
		
		System.out.println(obj.dequeueCat());
		System.out.println(obj.dequeueCat());
		System.out.println(obj.dequeueDog());
		while(!obj.isEmpty()){
			System.out.println(obj.dequeueAny());
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		

	}
}
