package com.torison.api;

import com.torison.model.ResEntity;
import javafx.util.Pair;

import java.io.File;
import java.util.List;

/**
 * 邮件发送方式
 * The way email is send
 *
 * 1.简单邮件发送
 * 1.Simple email only with message
 *
 * 2.带有附件的简单邮件发送
 * 2.Simple email with attachments & message
 *
 * @author dongjj
 * @createtime 1.19
 */
public interface EmailSentService {


    /**
     * 发送简单邮件
     * 1.Simple email only with message
     * @param sendTo
     * @param title
     * @param content
     * @return
     */
    public ResEntity<String> sendSimpleEmail(String sendTo, String title, String content);

    /**
     * 2.带有附件的简单邮件发送
     * 2.Simple email with attachments & message
     * @param sendTo
     * @param title
     * @param content
     * @param attachments
     * @return
     */
    public ResEntity<String> sendAttachmentEmail(String sendTo, String title, String content, List<Pair<String, File>> attachments);


}
