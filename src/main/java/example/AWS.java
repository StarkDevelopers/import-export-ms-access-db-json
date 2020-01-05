package example;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class AWS {
    AWSCredentials credentials = new BasicAWSCredentials(
            "AKIAJJFJYZHIOREASTLQ",
            "W6a+WfInU8iC/PuAmB7QqEdllwXDf4Lsrakc7/fe"
    );
    AmazonS3 s3Client;
    public void init() {
        this.s3Client = AmazonS3ClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(this.credentials))
            .withRegion(Regions.AP_SOUTH_1)
            .build();
    }
    public void uploadFile(String bucketName, String filePath, String uploadPath) {
        this.s3Client.putObject(
            bucketName,
            uploadPath,
            new File(filePath)
        );

        System.out.println("File Uploaded to S3 Successfully");
    }
    public void downloadFile(String bucketName, String filePath, String downloadPath) throws IOException {
        S3Object s3object = this.s3Client.getObject(bucketName, downloadPath);
        S3ObjectInputStream inputStream = s3object.getObjectContent();
        Files.copy(inputStream, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

        System.out.println("File Downloaded from S3 Successfully");
    }
}
