package com.torison.sendtest;


import com.minbo.dubbo.consumer.DubboConsumerApplication;


import com.torison.api.EmailSentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DubboConsumerApplication.class)
public class EmailSendTest {

    @Autowired
    EmailSentService emailSentService;

    @Test
    public void sendMailTest(){
        System.out.println("ss");
        emailSentService.sendSimpleEmail("294879521@qq.com","test","测试文本1");
    }

}
