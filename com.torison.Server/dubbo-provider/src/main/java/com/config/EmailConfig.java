package com.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 邮件发送方配置
 * @author dongjj
 * @createtime 1.19
 */
@Component
public class EmailConfig {

    /**
     *邮件发送方
     * 从application.yml内读取username值作为发送方
     */
    @Value("${spring.mail.username}")
    private String emailFrom;

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }
}
