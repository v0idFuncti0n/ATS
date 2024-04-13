package com.fst.atsmasterdataservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ATS-RESUME-PARSER", url = "${ats.resume-parser-url}")
public interface ATSResumeParserFeignClient {

    @GetMapping(path = "/resume/get/{name}/")
    ResponseEntity<Resource> getResumeFile(@PathVariable String name);
}
