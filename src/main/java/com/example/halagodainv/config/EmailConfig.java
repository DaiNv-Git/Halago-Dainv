package com.example.halagodainv.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {
    @Autowired
    private Environment env;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(env.getProperty("spring.mail.host"));
        mailSender.setPort(Integer.parseInt(env.getProperty("spring.mail.port")));
        mailSender.setUsername(env.getProperty("spring.mail.username"));
        mailSender.setPassword(env.getProperty("spring.mail.password"));

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.starttls.enable", env.getProperty("spring.mail.properties.mail.smtp.starttls.enable"));
        props.put("mail.smtp.auth.mechanisms", env.getProperty("spring.mail.properties.mail.smtp.auth.mechanisms"));
        props.put("mail.smtp.auth.login.disable", env.getProperty("spring.mail.properties.mail.smtp.auth.login.disable"));
        props.put("mail.smtp.auth.plain.disable", env.getProperty("spring.mail.properties.mail.smtp.auth.plain.disable"));
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.auth.xoauth2.clientid", env.getProperty("spring.mail.properties.mail.smtp.auth.xoauth2.clientid"));
        props.put("mail.smtp.auth.xoauth2.clientsecret", env.getProperty("spring.mail.properties.mail.smtp.auth.xoauth2.clientsecret"));

        return mailSender;
    }
}