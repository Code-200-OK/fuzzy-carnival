package threads;

public class createClassImplementingRunnable implements Runnable{
	public void run(){  
		System.out.println("thread is running...");  
	}  

	public static void main(String args[]){  
		createClassImplementingRunnable m1=new createClassImplementingRunnable();  
		Thread t1 =new Thread(m1);  
		t1.start();  
	}  
}  
