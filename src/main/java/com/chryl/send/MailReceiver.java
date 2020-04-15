package com.chryl.send;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * Created by Chr.yl on 2020/4/15.
 *
 * @author Chr.yl
 */
@Slf4j
@Component
public class MailReceiver {
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    MailProperties mailProperties;
    @Autowired
    TemplateEngine templateEngine;

    public void handler() {
        //收到消息，发送邮件
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg);
        try {
            helper.setTo("1015670600@qq.com");//mail
            helper.setFrom(mailProperties.getUsername());
            helper.setSubject("入职欢迎");
            helper.setSentDate(new Date());
            Context context = new Context();
            context.setVariable("name", "Chr.yl");
            context.setVariable("posName", "客户经理");
            context.setVariable("joblevelName", "1");
            context.setVariable("departmentName", "zzzo");
            String mail = templateEngine.process("mail", context);
            helper.setText(mail, true);
            javaMailSender.send(msg);
            log.info("邮件发送成功");
        } catch (MessagingException e) {
            e.printStackTrace();
            log.error("邮件发送失败：" + e.getMessage());
        }
    }
}
