package com.amazonaws.samples;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.TerminateInstancesRequest;
import com.amazonaws.services.ec2.model.TerminateInstancesResult;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;

public class cloudPi {
	private static AmazonEC2 amazonEC2client;
	private static AmazonSQS sqs;
	private static String myQueueUrl;
	private static String message;
	private static BasicAWSCredentials awsCreds;
	private static String bucketName = "aws-cc";
	private static String sgName = "launch-wizard-2";
	private static String imageId = "ami-513cb231";
	private static String keyName = "cc-key-pair";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("Server Responding");
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
		S3Object result = isPresentInS3((message+".txt"),true);
		if(result!=null){
			// return the data
			System.out.println(getObjectContents(result));
			return;
		}
		sqs = new AmazonSQSClient(awsCreds);

		//sqs = new AmazonSQSClient(credentials);
		sqs.setEndpoint("https://sqs.us-west-2.amazonaws.com");
		List<String> queueUrls = new ArrayList<String>();
		try{
			queueUrls = sqs.listQueues("CloudPiRequestQueue.fifo").getQueueUrls();
			myQueueUrl = queueUrls.get(0);
		} catch(Exception e){
			throw new AmazonClientException("Problem with queue initialization",e);
		}
		//System.out.println("Queue URL Obtained:"+ myQueueUrl);
		////System.out.println("Success in sending Message to queue: " + sendMessage()[1]);

		///////////////  NEXT PART //////////////////////////////


		amazonEC2client = AmazonEC2ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("us-west-2").build();
		while(countInstances()>11);
		//System.out.println("New instance needs to be started");

		String Id = initiateNewInstance(deleteMessage(popMessage()).getBody());
		//System.out.println("Waiting for the instance to process..");
		while(isPresentInS3((message+".txt"),false)==null);
		//System.out.println("Wait Over");
		result = isPresentInS3((message+".txt"),true);
		//if(listMessages().size()<listInstances().size())
		terminateInstance(Id);
		System.out.println(getObjectContents(result));
	}
	public static List<Message> listMessages(){
		//System.out.println("Listing Messages");
		ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(myQueueUrl);
		List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
		//System.out.println("Listed Messages");
		return messages;
	}
	public static void terminateInstance(String Id){
		TerminateInstancesRequest terminate = new TerminateInstancesRequest();
		terminate.withInstanceIds(Id);
		TerminateInstancesResult result = amazonEC2client.terminateInstances(terminate);
		//System.out.println("Terminate Instance Result: " + result.toString());
	}
	public static String initiateNewInstance(String processMessage){
		//System.out.println("Initiating New Instance");
		RunInstancesRequest run = new RunInstancesRequest();
		//String userData = "#!/bin/bash\n./toRun"+message;
		//String userData = "#!/bin/bash \n mkdir sakthi";
		String userData = "";
		userData = userData + "#!/bin/bash" + "\n";
		userData = userData + "./toRun "+ processMessage + "\n";
		//System.out.println(userData);
		String formattedString="";
		try {
			formattedString = new String( Base64.encodeBase64(userData.getBytes( "UTF-8" )), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("Running with ..."+formattedString);
		run.withImageId(imageId).withInstanceType("t2.micro").withMinCount(1).withMaxCount(1)
		.withKeyName(keyName).withSecurityGroups(sgName).withUserData(formattedString);
		

		RunInstancesResult result = amazonEC2client.runInstances(run);
		//result.getReservation().re
		String id = result.getReservation().getInstances().get(0).getInstanceId();
		//System.out.println("Initiated New Instance with ID:"+id+" \n description: " + result.toString());
		return id;

	}
	public static int countInstances(){
		//System.out.println("Counting Instances");
		DescribeInstancesResult d = new DescribeInstancesResult();
		DescribeInstancesResult result = amazonEC2client.describeInstances();
		List<Reservation> listReservations = result.getReservations();
		List<Instance> listInstances = new ArrayList<Instance>();
		int code;
		int count = 0;
		for(Reservation r:listReservations){
			for(Instance i: r.getInstances()){
				code = i.getState().getCode();
				if(code==0 || code==16) count++;
				////System.out.println("Instance Description:"+i.toString());
			}
		}
		//System.out.println("Counted Instances");
		return count;
	}
	public static String getObjectContents(S3Object object){
		//System.out.println("Getting Object Contents");
		S3ObjectInputStream s3is = object.getObjectContent();
		byte[] read_buf = new byte[1024];
		StringBuilder body = new StringBuilder();
		try {
			while (s3is.read(read_buf) > 0) {
				body.append(new String(read_buf));
			}
			s3is.close();
		} catch (IOException e) {
			//System.out.println("Error in reading from object");
			e.printStackTrace();
		}
		//System.out.println("Got Object Contents");
		return body.toString();
	}
	public static S3Object isPresentInS3(String key,boolean flag){
		//if(flag) System.out.println("Checking. if Key present in Bucket:"+bucketName);
		AmazonS3 s3client = new AmazonS3Client(awsCreds);
		S3Object result=null;
		try{
			result = s3client.getObject(bucketName, key);
		}
		catch(Exception e){
			if(flag) //System.out.println("Not Found in buckets");
			return null;
		}
		//if(flag) System.out.println("Found in buckets");
		return result;
	}
	public static Message popMessage(){
		//System.out.println("Popping Message");
		ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(myQueueUrl);
		List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
        if(messages.size()<=0){
        	//System.out.println("Nothing to Pop");
        	return null;
        }
        //System.out.println("Popped Message");
        return messages.get(0);
	}
	public static Message deleteMessage(Message message){
		//System.out.println("Deleting Message");
		String messageReceiptHandle = message.getReceiptHandle();
        sqs.deleteMessage(new DeleteMessageRequest(myQueueUrl, messageReceiptHandle));
        return message;
        //System.out.println("Deleted Message");
	}
	public static String[] sendMessage(){
		//System.out.println("Sending Message...");
		String[] answer = new String[2];
		SendMessageRequest sendMessageRequest = new SendMessageRequest(myQueueUrl,message);
		sendMessageRequest.setMessageGroupId("messageGroup1");
		//sendMessageRequest.setMessageDeduplicationId("1");
		SendMessageResult sendMessageResult = sqs.sendMessage(sendMessageRequest);
		answer[0] = sendMessageResult.getSequenceNumber();
		answer[1] = sendMessageResult.getMessageId();
		//System.out.println("Sent Message");
		return answer;
	}

}
