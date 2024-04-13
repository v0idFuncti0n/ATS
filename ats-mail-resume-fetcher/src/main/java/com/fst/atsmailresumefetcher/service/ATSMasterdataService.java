package com.fst.atsmailresumefetcher.service;

import com.fst.atsmailresumefetcher.feign.ATSMasterdataServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ATSMasterdataService {

    private final ATSMasterdataServiceFeignClient atsMasterdataServiceFeignClient;

    @Autowired
    public ATSMasterdataService(ATSMasterdataServiceFeignClient atsMasterdataServiceFeignClient) {
        this.atsMasterdataServiceFeignClient = atsMasterdataServiceFeignClient;
    }

    public ResponseEntity<Object> createCandidate(Object json, String resumeFilename) {
        return atsMasterdataServiceFeignClient.createCandidate(json, resumeFilename);
    }
}
