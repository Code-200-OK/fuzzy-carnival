import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class S3Commands {
	static AmazonS3 s3client;
	public static void main(String[] args) throws IOException{
		s3client = new AmazonS3Client();
		//createBucket();
		//listBuckets();
		//listObjects("jatsakthi-aws-bucket1");
		//putObject("jatsakthi-aws-bucket1");
		downloadObject("jatsakthi-aws-bucket1","a3");
	}
	static void downloadObject(String bucketName, String key) throws IOException{
		System.out.println("IN FUNCTION: downloadObject");
		S3Object object= s3client.getObject(bucketName, key);
		S3ObjectInputStream data = object.getObjectContent();
		File f = new File("C:/Users/jatsa/Downloads/Test1.pdf");
		if(!f.exists())
		    f.createNewFile();
	    FileOutputStream outputFile = new FileOutputStream(f);
		byte[] read_buf = new byte[1024];
		int read_len = 0;
		while ((read_len = data.read(read_buf)) > 0) {
			outputFile.write(read_buf, 0, read_len);
	    }
		data.close();
	    outputFile.close();
	}
	static void putObject(String bucketName){
		System.out.println("IN FUNCTION: putObject");
		String key = "a3";
		try{
		File file = new File("M:/ASU/SML/Assignment 2/a2.pdf");
		PutObjectResult result= s3client.putObject(bucketName, key,file);
		System.out.println(result.toString());
		}
		catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " +
            		"means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " +
            		"means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
		
	}
	static void createBucket(){
		System.out.println("IN FUNCTION: createBucket");
		String bucketName = "jatsakthi-aws-bucket1";
		Bucket result = s3client.createBucket(bucketName);
		System.out.println(result.toString());
	}
	static void listObjects(String bucketName){
		System.out.println("IN FUNCTION: listObjects");
		ObjectListing objects = s3client.listObjects(bucketName);
		List<S3ObjectSummary>all = objects.getObjectSummaries();
		for(S3ObjectSummary each: all){
			System.out.println(each.getKey());
		}
	}
	static void listBuckets(){
		System.out.println("IN FUNCTION: listBuckets");
		List<Bucket> buckets = s3client.listBuckets();
		for(Bucket each: buckets){
			System.out.println(each.getName());
		}

		//[S3Bucket [name=aws-cc, creationDate=Tue Feb 21 14:31:08 MST 2017, owner=S3Owner [name=awsedu014562,id=ae56c37f25e216ea2c95322a489260a02c638ebdaeca9a7875b315693062d9c6]], S3Bucket [name=jatsakthi-aws-bucket1, creationDate=Tue Feb 21 14:36:47 MST 2017, owner=S3Owner [name=awsedu014562,id=ae56c37f25e216ea2c95322a489260a02c638ebdaeca9a7875b315693062d9c6]], S3Bucket [name=ql-cf-templates-1487108631-ceb1ef4a9c9b6d50-us-west-2, creationDate=Tue Feb 14 14:43:53 MST 2017, owner=S3Owner [name=awsedu014562,id=ae56c37f25e216ea2c95322a489260a02c638ebdaeca9a7875b315693062d9c6]], S3Bucket [name=qltrail-lab-266-1487108635, creationDate=Tue Feb 14 14:43:56 MST 2017, owner=S3Owner [name=awsedu014562,id=ae56c37f25e216ea2c95322a489260a02c638ebdaeca9a7875b315693062d9c6]]]

	}
}
