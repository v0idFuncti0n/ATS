package com.fst.atsmailresumefetcher.service;

import com.fst.atsmailresumefetcher.feign.ATSResumeParserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;

@Service
public class ATSResumeParserService {

    public static final String ATS_RESUME_PARSER_URL = "http://ats-resume-parser:5000";
    public static final String ATS_RESUME_PARSER_RESUME_UPLOAD_ENDPOINT = "/resume/";

    private final RestTemplate restTemplate = new RestTemplate();
    private final ATSResumeParserFeignClient atsResumeParserFeignClient;

    @Autowired
    public ATSResumeParserService(ATSResumeParserFeignClient atsResumeParserFeignClient) {
        this.atsResumeParserFeignClient = atsResumeParserFeignClient;
    }

    public ResponseEntity<String> sendResume(File resumePDF) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        final FileSystemResource fileSystemResource = new FileSystemResource(resumePDF);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.set("file", fileSystemResource);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        return restTemplate.exchange(ATS_RESUME_PARSER_URL + ATS_RESUME_PARSER_RESUME_UPLOAD_ENDPOINT, HttpMethod.POST, requestEntity, String.class);
    }

    public ResponseEntity<Object> resumeToJSON(String name) {
        return atsResumeParserFeignClient.resumeToJSON(name);
    }
}
