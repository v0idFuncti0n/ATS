package com.fst.atsmailresumefetcher.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "ATS-MASTERDATA-SERVICE", url = "${ats.masterdata-url}")
public interface ATSMasterdataServiceFeignClient {

    @PostMapping(path = "/candidate", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> createCandidate(Object json);

}
