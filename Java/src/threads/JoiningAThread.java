package threads;

public class JoiningAThread extends Thread{  
	 public void run(){  
		  for(int i=1;i<=5;i++){  
		   try{  
		    Thread.sleep(500);  
		   }catch(Exception e){System.out.println(e);}  
		  System.out.println(i);  
		  }  
		 }  
		public static void main(String args[]){  
		 JoiningAThread t1=new JoiningAThread();  
		 JoiningAThread t2=new JoiningAThread();  
		 JoiningAThread t3=new JoiningAThread();  
		 t1.start();  
		 try{  
		  t1.join();  
		 }catch(Exception e){System.out.println(e);}  
		  
		 t2.start();  
		 t3.start();  
		 }  
		} 
