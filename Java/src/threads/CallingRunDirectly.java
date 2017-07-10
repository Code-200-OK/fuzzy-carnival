package threads;

//Invoking the run() method from main thread, the run() method goes onto the current call stack rather than 
//at the beginning of a new call stack.

// Behaves serially
public class CallingRunDirectly extends Thread{  
	 public void run(){  
	  for(int i=1;i<5;i++){  
	    try{Thread.sleep(500);}catch(InterruptedException e){System.out.println(e);}  
	    System.out.println(i);  
	  }  
	 }  
	 public static void main(String args[]){  
	  CallingRunDirectly t1=new CallingRunDirectly();  
	  CallingRunDirectly t2=new CallingRunDirectly();  
	   
	  t1.run();  
	  t2.run();  
	 }  
	}