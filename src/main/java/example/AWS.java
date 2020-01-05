package example;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class AWS {
    AWSCredentials credentials = new BasicAWSCredentials(
            "AKIAJBOMIEQQHNW5HYPA",
            "wR308SbWNCLVvXNeg8p8lBmgIbkNkCrZsyjP11uL"
    );
    AmazonS3 s3Client;
    public void init() {
        this.s3Client = AmazonS3ClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(this.credentials))
            .withRegion(Regions.AP_SOUTH_1)
            .build();
    }
    public void uploadFile(String bucketName, String filePath, String uploadPath) throws FileNotFoundException {
        InputStream fileStream = new FileInputStream(new File(filePath));

        this.s3Client.putObject(new PutObjectRequest(
                bucketName,
                uploadPath,
                fileStream,
                new ObjectMetadata()
        ).withCannedAcl(CannedAccessControlList.PublicRead));

        System.out.println("File Uploaded to S3 Successfully");
    }
    public void downloadFile(String bucketName, String filePath, String downloadPath) throws IOException {
        S3Object s3object = this.s3Client.getObject(bucketName, downloadPath);
        S3ObjectInputStream inputStream = s3object.getObjectContent();
        Files.copy(inputStream, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

        System.out.println("File Downloaded from S3 Successfully");
    }
}
