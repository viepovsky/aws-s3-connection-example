## Example connection with AWS S3 service

This repository contains a simple application that connects to the AWS S3 service. 
The application demonstrates how to upload and download objects from an S3 Bucket. 
For testing purposes, a basic frontend is provided, lacking extensive functionalities but verifying the proper functioning of the S3 connection.

A faux database has been implemented to store, retrieve, and delete objects. 
Additionally, it maintains links to images stored in the S3 bucket.

## Technologies

The application is built using the following frameworks and technologies:

- Spring: Boot, Web
- AWS S3
- Maven

## How to run

1. [(Optional)Run the frontend](react-frontend/README.md)

2. Create a `credentials` file in the `.aws` folder under your computer user directory. 
Ensure that this file includes the Access Key for your AWS account. The file should look like this:
```
[default]
aws_access_key_id=your-access-key
aws_secret_access_key=your-secret-key
```
3. Specify your bucket name in `application.yml` under `aws.s3.bucket-name.localTesting`

4. Select your region in `application.yml` under `aws.region`
   
5. (Optional) Specify the maximum image size in `application.yml` under `spring.servlet.multipart.max-file-size`

6. Run the application by typing `mvn spring-boot:run` or by executing the `Main.java` class.
