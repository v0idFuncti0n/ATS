package com.fst.atsmailresumefetcher.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ATS-MASTERDATA-SERVICE", url = "${ats.masterdata-url}")
public interface ATSMasterdataServiceFeignClient {

    @PostMapping(path = "/candidate/{filename}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> createCandidate(@RequestBody Object json, @RequestParam("filename") String resumeFilename);

}
