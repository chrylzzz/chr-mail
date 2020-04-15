package com.chryl;

import com.chryl.send.MailReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ChrMailApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChrMailApplication.class, args);
    }

    @Autowired
    private MailReceiver mailReceiver;

    @GetMapping("/sendMail")
    public void show() {
        mailReceiver.handler();
    }

}
