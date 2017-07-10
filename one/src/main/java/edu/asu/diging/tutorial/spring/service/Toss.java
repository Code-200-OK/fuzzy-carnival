package edu.asu.diging.tutorial.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.*;

@Service
public class Toss {
	@Autowired
	private Sender send;
	
	public void toss(){
		String message = "TOSS";
		try {
			byte[] bytesOfMessage = message.getBytes("UTF-8");
			
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] array = md.digest(message.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i)
		          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
			send.send("question", sb.toString());
			
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
