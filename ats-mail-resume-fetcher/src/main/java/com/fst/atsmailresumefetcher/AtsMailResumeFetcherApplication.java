package com.fst.atsmailresumefetcher;

import com.fst.atsmailresumefetcher.mail.EmailListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableFeignClients
public class AtsMailResumeFetcherApplication implements CommandLineRunner {

    private final ApplicationContext context;

    Logger logger = LoggerFactory.getLogger(AtsMailResumeFetcherApplication.class);

    @Autowired
    public AtsMailResumeFetcherApplication(ApplicationContext context) {
        this.context = context;
    }


    public static void main(String[] args) {
        SpringApplication.run(AtsMailResumeFetcherApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("START COMMAND LINE");
        EmailListener emailListener = context.getBean(EmailListener.class);
        logger.info("START EMAIL LISTENER");
        emailListener.startListening();
        logger.info("START LISTENING");
    }

}
