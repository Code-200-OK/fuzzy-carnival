package threads;

public class NamingAThread extends Thread{  
	public void run(){  
		System.out.println("running..."+Thread.currentThread().getName());  
	}  
	public static void main(String args[]){  
		NamingAThread t1=new NamingAThread();  
		NamingAThread t2=new NamingAThread();  
		System.out.println("Name of t1:"+t1.getName());  
		System.out.println("Name of t2:"+t2.getName());  

		t1.start();  
		t2.start();  

		t1.setName("Sakthi");  
		System.out.println("After changing name of t1:"+t1.getName());  
	}  
}