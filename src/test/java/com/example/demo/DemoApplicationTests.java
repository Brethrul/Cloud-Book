package com.example.demo;

import com.example.demo.entity.Document;
import com.example.demo.utils.WebSocketConfig;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static java.lang.System.out;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }
    @Test
    void documentCreate(){
        System.out.println("hello");
        //Document aa = new Document("aa",20,20,"bb");
        Document bb = Document.loadDocument("6010b9173c14b8c6aeedb265520d90e24f2321d2561557dcc59f07799f61324a");
        System.out.println("hello");
    }
}
