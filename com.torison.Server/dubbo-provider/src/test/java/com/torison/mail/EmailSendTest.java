package com.torison.mail;

import com.Application;
import com.torison.util.EmailSentServiceImp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class EmailSendTest {

    @Autowired
    EmailSentServiceImp emailSentServiceImp;

    @Test
    public void sendMailTest(){
        System.out.println("ss");
        emailSentServiceImp.sendSimpleEmail("294879521@qq.com","test","测试文本1");
    }

}
