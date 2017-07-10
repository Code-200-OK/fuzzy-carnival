package threads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophers {
	public static void main(String[] args){
		Chopstick[] chopsticks= new Chopstick[5];
		for(int i=0;i<5;i++)
			chopsticks[i] = new Chopstick();
		Philosopher[] philosophers = new Philosopher[5];
		philosophers[0] = new Philosopher(0,chopsticks[0],chopsticks[1]);
		philosophers[1] = new Philosopher(1,chopsticks[1],chopsticks[2]);
		philosophers[2] = new Philosopher(2,chopsticks[2],chopsticks[3]);
		philosophers[3] = new Philosopher(3,chopsticks[3],chopsticks[4]);
		philosophers[4] = new Philosopher(4,chopsticks[4],chopsticks[0]);
		
		for(int i=0;i<5;i++)
			philosophers[i].start();
	}


}
class Chopstick{
	private Lock lock;
	public Chopstick(){
		lock = new ReentrantLock();
	}
	public boolean pickUp(){
		return lock.tryLock();
	}
	public void putDown(){
		lock.unlock();
	}
}
class Philosopher extends Thread{
	int bites;
	private int id;
	Chopstick left,right;
	public Philosopher(int id, Chopstick l, Chopstick r){
		bites = 10;
		left = l;
		right = r;
		this.id = id;
	}
	public boolean pickUp(){
		if(!left.pickUp())return false;
		if(!right.pickUp()){
			left.putDown();
			return false;
		}
		return true;
	}
	public void chew(){

	}
	public void putDown(){
		left.putDown();
		right.putDown();
	}
	public void eat(){
		if(pickUp()){
		chew();
		putDown();
		}
	}
	public void  run(){
		for(int i=0;i<bites;i++){
			eat();
			try {
				System.out.println("Philosopher "+id+" Eating Bite Num: "+(i+1));
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
