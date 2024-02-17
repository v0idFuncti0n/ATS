package com.fst.atsmailresumefetcher;

import com.fst.atsmailresumefetcher.feign.ATSResumeParserFeignClient;
import com.fst.atsmailresumefetcher.mail.EmailListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
public class AtsMailResumeFetcherApplication implements CommandLineRunner {

    private final ApplicationContext context;

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    ATSResumeParserFeignClient atsResumeParserFeignClient;

    @Autowired
    public AtsMailResumeFetcherApplication(ApplicationContext context) {
        this.context = context;
    }


    public static void main(String[] args) {
        SpringApplication.run(AtsMailResumeFetcherApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        EmailListener emailListener = context.getBean(EmailListener.class);
        emailListener.startListening();
    }

}
