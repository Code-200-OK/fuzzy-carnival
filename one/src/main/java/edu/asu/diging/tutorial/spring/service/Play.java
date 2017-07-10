package edu.asu.diging.tutorial.spring.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Play {
	@Autowired
	private Sender send;
	final static Logger logger = Logger.getLogger(Play.class);
	boolean started = false;
	private String tossValue;
	boolean won = false;
	int current = -1;
	Queue<String> rec;
	
	public void getCurrentNumber(){
		//
	}
	public void getListOfANumbers(){
		
	}
	public void getListOfBNumbers(){
		
	}
	
	public void toss(){
		rec = new LinkedList<String>();
		Random rand = new Random();
		tossValue = Integer.toString((int)rand.nextInt(10000) + 0);
		//tossValue = "3";
		try {			
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] array = md.digest(tossValue.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i)
		          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
			//send.send("question", sb.toString());
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		/*while(rec.size()==0){
			System.out.println("Waiting ...."+rec.size());
		}*/
		
		System.out.println("Received the very first message");
		String message = rec.poll();
		//int tossChoice = Integer.parseInt(message);
		int tossChoice = 0;
		tossValue = "1";
		send.send("question", "Number Chosen for Toss: "+ tossValue);
		// odd is 0
		// even is 1
		if((tossChoice == 1 && Integer.parseInt(tossValue) % 2 == 0) || (tossChoice == 0 && Integer.parseInt(tossValue) % 2 == 1)){
			// B Wins
			System.out.println("B Won...");
			//send.send("question", "You won the toss!!! Start the game");
			while(rec.size()==0){
				System.out.println("Waiting ...."+rec.size());
			}
			message = rec.poll();
			started = true;
			current = Integer.parseInt(message);
			int chosen = logicAndSend();
			//send.send("question", "I Choose: "+ Integer.toString(chosen));
			//send.send("question", "Current: "+ Integer.toString(current));
			//send.send("question", "Choose from 1 to "+(int)Math.round(current/2));
			
		}
		else{
			// A Wins
			System.out.println("A Won...");
			//send.send("question", "I won the toss!!! Wait for me");
			current = logicAndSend();
			//send.send("question", "I Choose: "+ Integer.toString(current));
			//send.send("question", "Current: "+ Integer.toString(current));
			//send.send("question", "Choose from 1 to "+(int)Math.round(current/2));
			started = true;
		}
		System.out.println("Current is : "+current);
		Scanner s = new Scanner(System.in);
		System.out.println("ok??");
		//s.nextLine();
		
		while(current>0){
			while(rec.size()==0){
				System.out.println("Waiting ...."+rec.size());
			}
			message = rec.poll();
			current -= Integer.parseInt(message);
			if(current == 0){
				System.out.println("B won the game");
				//send.send("question","You Won thank you for Playing!!!!");
				break;
			}
			int chosen = logicAndSend();
			/*if(current>0){
			send.send("question", "I Choose: "+ Integer.toString(chosen));
			send.send("question", "Current: "+ Integer.toString(current));
			send.send("question", "Choose from 1 to "+(int)Math.round(current/2));
			}*/
		}
		
	}
	public int logic(){
		System.out.println("In Logic");
		Scanner s = new Scanner(System.in);
		//System.out.println("Enter your choice");
		//int choice = Integer.parseInt(s.nextLine());
		int choice;
		if(!started)
			return 55;
		else
			choice = Math.max(1,(int)Math.round(current/2));
		return choice;
	}
	public int logicAndSend(){
		System.out.println("In logic and send");
		int num = logic();
		current -= num;
		if(current == 0){
			// user wins
			System.out.println("A won the game");
			send.send("question",Integer.toString(num));
			send.send("question","I Won thank you for Playing!!!!");
		}
		else{
			System.out.println("Game in progress");
			send.send("question",Integer.toString(num));
		}
		return num;
	}
	
	@KafkaListener(id="test.listener.id", topics = "answer")
	public void receiveMessage(String message) {
	    logger.info("Received here topic: " + message);
		System.out.println("Received topic: " + message);
		rec.offer(message);
		
	}
	
}
