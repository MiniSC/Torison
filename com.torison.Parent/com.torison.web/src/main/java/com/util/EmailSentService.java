package com.util;


import com.util.config.EmailConfig;
import com.util.model.MailCode;
import com.util.model.ResEntity;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

@Service
public class EmailSentService   {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private EmailConfig emailConfig;


    /**
     * 发送简单邮件
     * @param sendTo
     * @param title
     * @param content
     * @return
     */
    public ResEntity sendSimpleEmail(String sendTo, String title, String content) {
        ResEntity resEntity = new ResEntity();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailConfig.getEmailFrom());
        message.setTo(sendTo);
        message.setSubject(title);
        message.setText(content);
        try{
        javaMailSender.send(message);
        } catch (Exception e){
            resEntity.setRespCode(MailCode.MailErrCode.ERROR);
            resEntity.setRespMsg("系统异常");
            return resEntity;
            }
            resEntity.setRespCode(MailCode.MailErrCode.SUCCESS);
        return resEntity;
    }

    /**
     * 发送附件邮件
     * @param sendTo
     * @param title
     * @param content
     * @param attachments
     * @return
     */
    public ResEntity<String> sendAttachmentEmail(String sendTo, String title, String content, List<Pair<String, File>> attachments) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        ResEntity resEntity = new ResEntity();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(emailConfig.getEmailFrom());
            helper.setTo(sendTo);
            helper.setSubject(title);
            helper.setText(content);
            for (Pair<String, File> pair : attachments) {
                helper.addAttachment(pair.getKey(), new FileSystemResource(pair.getValue()));
            }
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            resEntity.setRespCode(MailCode.MailErrCode.ERROR);
            resEntity.setRespMsg("系统异常");
            return resEntity;
        }
        resEntity.setRespMsg(MailCode.MailErrCode.SUCCESS);
        return resEntity;
    }

}
