package com.cloud.cc.mycloud;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

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
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;

public class appCloudPi {
	private static AmazonEC2 amazonEC2client;
	private static AmazonSQS sqs;
	private static String myQueueUrl;
	private static BasicAWSCredentials awsCreds;
	private static String bucketName = "aws-cc";
	private static String sgName = "launch-wizard-2";
	private static String imageId = "ami-513cb231";
	private static String keyName = "cc-key-pair";
	private static ExecutorService service;
	private static Map<Message,String> pool = new HashMap <Message,String>();

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		// TODO Auto-generated method stub
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
		sqs = new AmazonSQSClient(awsCreds);
		sqs.setEndpoint("https://sqs.us-west-2.amazonaws.com");
		List<String> queueUrls = new ArrayList<String>();
		try{
			queueUrls = sqs.listQueues("CloudPiRequestQueue.fifo").getQueueUrls();
			myQueueUrl = queueUrls.get(0);
		} catch(Exception e){
			throw new AmazonClientException("Problem with queue initialization",e);
		}
		amazonEC2client = AmazonEC2ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion("us-west-2").build();
		String message = "";
		Message nextMessage;
		while(true){
			/*List<Message> nextTenMessages = getTopTen();
			service = Executors.newFixedThreadPool(10);
			for(int i=0;i<nextTenMessages.size();i++){
				ca(nextTenMessages.get(i)).get();
			}*/
			int curRun = 0;
			List<Message> nextMessages = getTop();
			//Map<String,String> data = listToMap(nextTenMessages);
			//Map<String,String> pool = new HashMap <String,String>();
			curRun = nextMessages.size();
			System.out.println("Picked up "+curRun+" elements");
			for(int i=0;i<curRun;i++){
				
				nextMessage = nextMessages.get(i);
				message = nextMessage.getBody();
				System.out.println("Got "+ message);
				if(!pool.containsKey(message)){
					System.out.println("Not currently under process");
					System.out.println("Initiating");
					String Id = initiateNewInstance(message);
				}
				else {System.out.println("Similar data under process");
				}
			}
			for(Map.Entry<Message, String> e: pool.entrySet()){
					if(isPresentInS3((e.getKey().getBody()+".txt"),false)!=null){
						System.out.println("Wrote the message in S3");
						terminateInstance(e.getValue());
						System.out.println("Deleting from Queue");
						deleteMessage(e.getKey());
						System.out.println("Deleted Message");
						pool.remove(e.getKey());
					}	
				}
				/*message = nextMessage.getBody();
				System.out.println("Got "+ message);
				while(countInstances()>11);
				System.out.println("Initi	ating");
				String Id = initiateNewInstance(message);
				while(isPresentInS3((message+".txt"),false)==null);
				System.out.println("Wrote the message in S3");
				terminateInstance(Id);
				System.out.println("Deleting from Queue");
				deleteMessage(nextMessage);
				System.out.println("Deleted Message");*/
			}

		}
		public static Future<Boolean> ca(final Message nextMessage){
			return service.submit(new Callable<Boolean>() {
				public Boolean call() throws Exception {
					String message = nextMessage.getBody();
					System.out.println("Got "+ message);
					System.out.println("Initiating");
					String Id = initiateNewInstance(message);
					while(isPresentInS3((message+".txt"),false)==null);
					System.out.println("Wrote the message in S3");
					terminateInstance(Id);
					System.out.println("Deleting from Queue");
					deleteMessage(nextMessage);
					System.out.println("Deleted Message");
					return true;
				}

			});
		}/*
	public static  void process implements Callable(Message nextMessage){
		String message = nextMessage.getBody();
		System.out.println("Got "+ message);
		System.out.println("Initiating");
		String Id = initiateNewInstance(message);
		while(isPresentInS3((message+".txt"),false)==null);
		System.out.println("Wrote the message in S3");
		terminateInstance(Id);
		System.out.println("Deleting from Queue");
		deleteMessage(nextMessage);
		System.out.println("Deleted Message");

	}*/
		public static void terminateInstance(String Id){
			System.out.println("Terminating Instance");
			TerminateInstancesRequest terminate = new TerminateInstancesRequest();
			terminate.withInstanceIds(Id);
			TerminateInstancesResult result = amazonEC2client.terminateInstances(terminate);
			System.out.println("Terminated");
		}
		public static S3Object isPresentInS3(String key,boolean flag){
			if(flag) System.out.println("Checking. if Key present in Bucket:"+bucketName);
			AmazonS3 s3client = new AmazonS3Client(awsCreds);
			S3Object result=null;
			try{
				result = s3client.getObject(bucketName, key);
			}
			catch(Exception e){
				if(flag) System.out.println("Not Found in buckets");
				return null;
			}
			if(flag) System.out.println("Found in buckets");
			return result;
		}
		public static String initiateNewInstance(String processMessage){
			System.out.println("Initiating New Instance");
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
		public static Message deleteMessage(Message message){
			//System.out.println("Deleting Message");
			String messageReceiptHandle = message.getReceiptHandle();
			sqs.deleteMessage(new DeleteMessageRequest(myQueueUrl, messageReceiptHandle));
			return message;
			//System.out.println("Deleted Message");
		}
		/*public static Map<String,String> listToMap(List<Message> mlist){
			Map<String,String> data = new HashSet<String>();
			for(Message M: mlist) data.add(M.getBody());
			return data;
		}*/
		public static List<Message> getTop(){
			ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(myQueueUrl);
			List<Message> listOfMessages = sqs.receiveMessage(receiveMessageRequest).getMessages();
			System.out.println("Found"+listOfMessages.size());
			if(listOfMessages.size()>=10-pool.size()) return listOfMessages.subList(0, 10-pool.size());
			else return listOfMessages;
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

	}
