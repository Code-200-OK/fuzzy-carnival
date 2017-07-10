package threads;

// java.lang.IllegalThreadStateException
public class StartingAThreadTwice extends Thread{  
	 public void run(){  
	   System.out.println("running...");  
	 }  
	 public static void main(String args[]){  
	  StartingAThreadTwice t1=new StartingAThreadTwice();  
	  t1.start();  
	  t1.start();  
	 }  
	}  