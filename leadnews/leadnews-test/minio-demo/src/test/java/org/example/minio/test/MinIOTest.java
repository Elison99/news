package org.example.minio.test;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.example.file.service.FileStorageService;
import org.example.minio.MinIOApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@SpringBootTest(classes = MinIOApplication.class)
@RunWith(SpringRunner.class)
public class MinIOTest {

    @Autowired
    private FileStorageService fileStorageService;

    @Test
    public void testUpload(){
        FileInputStream fileInputStream = null;

        try {
            fileInputStream =  new FileInputStream("E:\\Chrome\\下载\\er.jpg");

            //创建minio连接客户端
            MinioClient minioClient = MinioClient.builder()
                    .credentials("minio", "minio123")
                    .endpoint("http://192.168.200.130:9000")
                    .build();
            //上传
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .object("avatar.jpg") //文件名
                    .contentType("image/jpeg") //文件类型
                    .bucket("leadnews") //同名称 与minio创建的名词一样
                    .stream(fileInputStream, fileInputStream.available(), -1).build();//文件流

            minioClient.putObject(putObjectArgs);

            System.out.println("http://192.168.200.130:9000/leadnews/list.html");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStarter() throws FileNotFoundException {
        FileInputStream in = new FileInputStream("E:\\Chrome\\下载\\er.jpg");
        String path = fileStorageService.uploadHtmlFile("", "avatar.jpg", in);
        System.out.println(path);
    }
}
