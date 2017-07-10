import java.util.List;

import org.apache.commons.codec.binary.Base64;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.amazonaws.services.ec2.model.StartInstancesResult;
import com.amazonaws.services.ec2.model.StopInstancesRequest;
import com.amazonaws.services.ec2.model.StopInstancesResult;
import com.amazonaws.services.ec2.model.TerminateInstancesRequest;
import com.amazonaws.services.ec2.model.TerminateInstancesResult;

public class EC2Commands {
	static AmazonEC2 amazonEC2client;
	static String keyName = "cc-key-pair";
	static String sgName = "launch-wizard-1";

	public static void main(String[] args) {
		//Initialize the client
		amazonEC2client = AmazonEC2ClientBuilder.standard().withRegion("us-west-2").build();
		//s3client = AmazonS3ClientBuilder.standard().withRegion("us-west-2").build();
		//Create Instance
		//createInstance();
		terminateInstance();
		listInstances();
		System.out.println("sakthi");

	}
	static void listInstances(){
		System.out.println("IN FUNCTION: LIST INSTANCES");
		DescribeInstancesResult result = amazonEC2client.describeInstances();
		List<Reservation> listReservations = result.getReservations();
		List<Instance> listInstances;
		for(Reservation r:listReservations){
			listInstances = r.getInstances();
			for(Instance i: listInstances){
				System.out.println("Instance Description:"+i.toString());
			}

		}
	}
	static void createInstance() {

		RunInstancesRequest run = new RunInstancesRequest();

		//run.withUserData(userData);
		//Amazon Machine Image ID - "ami-f173cc91"
		String userData = "#!/bin/bash\nmkdir sakthi";
		String formattedString = Base64.encodeBase64String(userData.getBytes());

		run.withImageId("ami-f173cc91").withInstanceType("t2.micro").withMinCount(1).withMaxCount(1)
		.withKeyName(keyName).withSecurityGroups(sgName).withUserData(formattedString);

		RunInstancesResult result = amazonEC2client.runInstances(run);
		System.out.println("Instance description: " + result.toString());

	}

	static void stopInstance(){
		StopInstancesRequest stop = new StopInstancesRequest();
		stop.withInstanceIds("i-0759fe8aca6af30f8");
		StopInstancesResult result = amazonEC2client.stopInstances(stop);
		System.out.println("Stop Instance Result: " + result.toString());
	}
	static void startInstance(){
		StartInstancesRequest start = new StartInstancesRequest();
		start.withInstanceIds("i-09a614cd1c7519225");
		StartInstancesResult result = amazonEC2client.startInstances(start);
		System.out.println("Start Instance Result: " + result.toString());
	}
	static void terminateInstance(){
		TerminateInstancesRequest terminate = new TerminateInstancesRequest();
		terminate.withInstanceIds("i-0759fe8aca6af30f8");
		TerminateInstancesResult result = amazonEC2client.terminateInstances(terminate);
		System.out.println("Terminate Instance Result: " + result.toString());
	}

}
