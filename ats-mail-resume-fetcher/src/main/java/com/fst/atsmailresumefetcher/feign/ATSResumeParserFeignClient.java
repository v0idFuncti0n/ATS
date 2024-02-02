package com.fst.atsmailresumefetcher.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ATS-RESUME-PARSER")
public interface ATSResumeParserFeignClient {

    @GetMapping(path = "/resume/{name}/")
    ResponseEntity<Object> resumeToJSON(@PathVariable String name);
}
