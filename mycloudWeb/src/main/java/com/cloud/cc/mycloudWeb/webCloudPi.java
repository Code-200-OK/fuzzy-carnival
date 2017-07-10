package com.cloud.cc.mycloudWeb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;

public class webCloudPi {
	private static AmazonSQS sqs;
	private static String myQueueUrl;
	private static String message;
	private static BasicAWSCredentials awsCreds;
	private static String bucketName = "aws-cc";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			message = args[0];
			int num = Integer.parseInt(message);
		}
		catch(Exception e){
			System.out.println("Give an Input/Valid Input");
			return;
		}
		awsCreds = null;
		try {
			awsCreds = new BasicAWSCredentials("AKIAJRC5Z2SFW5TGFOGA", "6nLGFCXiEJNyAI/PqVBqXMDvRZL5PqrIsBCb9wVO");
		} catch (Exception e) {
			throw new AmazonClientException(
					"Cannot load the credentials from the credential profiles file. " +
							"Please make sure that your credentials file is at the correct " +
							"location (/home/ec2-user/.aws/credentials), and is in valid format.",
							e);
		}
		// check if present in s3
		System.out.println("Checking if present in S#");
		S3Object result = isPresentInS3((message+".txt"),true);
		if(result!=null){
			System.out.println("Found in S3");
			System.out.println(getObjectContents(result));
			return;
		}
		System.out.println("Not found in s3");
		sqs = new AmazonSQSClient(awsCreds);
		sqs.setEndpoint("https://sqs.us-west-2.amazonaws.com");
		List<String> queueUrls = new ArrayList<String>();
		try{
			queueUrls = sqs.listQueues("CloudPiRequestQueue.fifo").getQueueUrls();
			myQueueUrl = queueUrls.get(0);
		} catch(Exception e){
			throw new AmazonClientException("Problem with queue initialization",e);
		}
		System.out.println("Queue Initialized");
		sendMessage();
		while(isPresentInS3((message+".txt"),false)==null);
		result = isPresentInS3((message+".txt"),true);
		System.out.println(getObjectContents(result));
	}
	public static String[] sendMessage(){
		System.out.println("Sending Message...");
		String[] answer = new String[2];
		SendMessageRequest sendMessageRequest = new SendMessageRequest(myQueueUrl,message);
		sendMessageRequest.setMessageGroupId("messageGroup1");
		SendMessageResult sendMessageResult = sqs.sendMessage(sendMessageRequest);
		answer[0] = sendMessageResult.getSequenceNumber();
		answer[1] = sendMessageResult.getMessageId();
		System.out.println("Sent Message");
		return answer;
	}
	public static S3Object isPresentInS3(String key,boolean flag){
		if(flag) System.out.println("Checking. if Key present in Bucket:"+bucketName);
		AmazonS3 s3client = new AmazonS3Client(awsCreds);
		S3Object result=null;
		try{
			result = s3client.getObject(bucketName, key);
		}
		catch(Exception e){
			if(flag) //System.out.println("Not Found in buckets");
			return null;
		}
		if(flag) System.out.println("Found in buckets");
		return result;
	}
	public static String getObjectContents(S3Object object){
		System.out.println("Getting Object Contents");
		S3ObjectInputStream s3is = object.getObjectContent();
		byte[] read_buf = new byte[1024];
		StringBuilder body = new StringBuilder();
		try {
			while (s3is.read(read_buf) > 0) {
				body.append(new String(read_buf));
			}
			s3is.close();
		} catch (IOException e) {
			System.out.println("Error in reading from object");
			e.printStackTrace();
		}
		System.out.println("Got Object Contents");
		return body.toString();
	}

}
