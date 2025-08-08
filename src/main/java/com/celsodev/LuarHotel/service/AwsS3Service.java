package com.celsodev.LuarHotel.service;

import com.celsodev.LuarHotel.exception.OurException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public class AwsS3Service {

    private final String bucketName = "luar-hotel";

    @Value("${aws.s3.access.key}")
    private String awsS3AccessKey;

    @Value("${aws.s3.secret.key}")
    private String awsS3SecretKey;

    public String saveImageToS3(MultipartFile photo) {
        String s3LocationImage = null;

        try {

            String s3Filename = photo.getOriginalFilename();

            //No caso não estou usando serviços AWS
           // BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsS3AccessKey, awsS3SecretKey);
           // AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
              //      .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
              //      .withRegion(Regions.US_EAST_2)
              //      .build();

            InputStream inputStream = photo.getInputStream();

          //  ObjectMetadata metadata = new ObjectMetadata();
          //  metadata.setContentType("image/jpeg");

          //  PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, s3Filename, inputStream, metadata);
          //  s3Client.putObject(putObjectRequest);
            return "https://" + bucketName + ".s3.amazonaws.com/" + s3Filename;

        } catch (Exception e) {
            e.printStackTrace();
            throw new OurException("Não é possível carregar a imagem no s3 bucket" + e.getMessage());
        }
    }

}
