package com.fst.atsmasterdataservice.controller;

import com.fst.atsmasterdataservice.dto.BootcampDTO;
import com.fst.atsmasterdataservice.dto.candidate.CandidateDTO;
import com.fst.atsmasterdataservice.service.BootcampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class BootcampController {

    private final BootcampService bootcampService;

    @Autowired
    public BootcampController(BootcampService bootcampService) {
        this.bootcampService = bootcampService;
    }

    @PostMapping(path = "/bootcamp", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BootcampDTO> createBootcamp(@RequestBody BootcampDTO bootcampDTO) {
        BootcampDTO savedBootcampDTO = bootcampService.createBootcamp(bootcampDTO);
        return ResponseEntity.ok(savedBootcampDTO);
    }

    @GetMapping(path = "/bootcamps/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BootcampDTO> getBootcampById(@PathVariable("id") Long id) throws IOException {
        BootcampDTO bootcamp = bootcampService.getBootcampById(id);
        return ResponseEntity.ok(bootcamp);
    }

    @GetMapping(path = "/bootcamps", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BootcampDTO>> getBootcamps() throws IOException {
        List<BootcampDTO> bootcamps = bootcampService.getBootcamps();
        return ResponseEntity.ok(bootcamps);
    }
}
