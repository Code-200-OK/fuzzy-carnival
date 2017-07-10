package threads;

public class createThreadExtendingThread extends Thread{  
	public void run(){  
		System.out.println("thread is running...");  
	}  
	public static void main(String args[]){  
		createThreadExtendingThread t1=new createThreadExtendingThread();  
		t1.start();  
	}  
}  