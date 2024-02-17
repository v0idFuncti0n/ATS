package com.fst.atsmasterdataservice.controller;

import com.fst.atsmasterdataservice.entity.Candidate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CandidateController {

    @PostMapping(path = "/candidate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createCandidate(@RequestBody Object json) {
        System.out.println(json);
        return ResponseEntity.ok(json);
    }

}
