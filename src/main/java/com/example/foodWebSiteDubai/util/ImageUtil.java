package com.example.foodWebSiteDubai.util;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.foodWebSiteDubai.configuration.AwsConfig;
import com.example.foodWebSiteDubai.entity.FoodImages;
import com.example.foodWebSiteDubai.exception.CommonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Component
public class ImageUtil {
    @Value("${aws.region}")
    private String region;
    @Value("${aws.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 getS3Object;

    public String uploadImage(MultipartFile file) throws IOException {
        String key = generateUniqueKey(file.getOriginalFilename());
        byte[] fileBytes = file.getBytes();
        ByteArrayInputStream imageFile = new ByteArrayInputStream(fileBytes);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());
        try {
            getS3Object.putObject(bucketName, key, imageFile, objectMetadata);
        } catch (AmazonServiceException e) {
            throw new CommonException(HttpStatus.INTERNAL_SERVER_ERROR,"File upload failed" );
        }
        return "https://" + bucketName + ".s3.amazonaws.com/" + key;
    }


    private String generateUniqueKey(String originalFilename) {
        return "images" + UUID.randomUUID().toString() + originalFilename;
    }
    public List<FoodImages> uploadImages(List<MultipartFile> images) {
        List<FoodImages> productImages = new ArrayList<>();
        long count=1;
        for (MultipartFile image : images) {
            String key = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
            uploadImage(key, image);
            FoodImages productImage = new FoodImages();
//            productImage.setId(count);
            productImage.setImageUrl(getImageURL(key));
            productImage.setImageName(image.getName());
            productImages.add(productImage);
            count++;
        }

        return productImages;
    }

    private void uploadImage(String key, MultipartFile image) {
        try {
            byte[] fileBytes = image.getBytes();
            ByteArrayInputStream imageFile = new ByteArrayInputStream(fileBytes);
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(image.getContentType());
            metadata.setContentLength(image.getSize());
            getS3Object.putObject(new PutObjectRequest(bucketName, key, imageFile, metadata));
        } catch (IOException e) {
            throw new RuntimeException("Error uploading image to S3", e);
        }
    }

    private String getImageURL(String key) {

        return getS3Object.getUrl(bucketName, key).toString();
    }
}

