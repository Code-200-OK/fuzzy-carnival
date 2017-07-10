package threads;

public class SleepAThread extends Thread{  
	public void run(){  
		for(int i=1;i<5;i++){  
			try{Thread.sleep(500);}catch(InterruptedException e){System.out.println(e);}  
			System.out.println(i);  
		}  
	}  
	public static void main(String args[]){  
		SleepAThread t1=new SleepAThread();  
		SleepAThread t2=new SleepAThread();  

		t1.start();  
		t2.start();  
	}  
}  
