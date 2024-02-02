package com.fst.atsmailresumefetcher.configuration;

import com.fst.atsmailresumefetcher.mail.EmailListener;
import jakarta.mail.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
@RefreshScope
public class EmailConfiguration {

    private final String emailHost;

    private final String emailPort;


    public EmailConfiguration(@Value("${email.host}") String emailHost, @Value("${email.port}") String emailPort) {
        this.emailHost = emailHost;
        this.emailPort = emailPort;
    }

    @Bean
    public Session mailSession() {
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");
        props.setProperty("mail.imaps.host", emailHost);
        props.setProperty("mail.imaps.port", emailPort);

        // Create a new session with the properties

        return Session.getInstance(props);
    }

    @Bean
    public EmailListener emailListener() {
        return new EmailListener(mailSession());
    }
}