package com.fst.atsmasterdataservice.service;

import com.fst.atsmasterdataservice.dto.BootcampDTO;
import com.fst.atsmasterdataservice.dto.candidate.CandidateDTO;
import com.fst.atsmasterdataservice.entity.BootcampEntity;
import com.fst.atsmasterdataservice.entity.candidate.CandidateEntity;
import com.fst.atsmasterdataservice.mapper.BootcampMapper;
import com.fst.atsmasterdataservice.repository.BootcampRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BootcampService {

    private final BootcampRepository bootcampRepository;
    private final BootcampMapper bootcampMapper;


    @Autowired
    public BootcampService(BootcampRepository bootcampRepository, BootcampMapper bootcampMapper) {
        this.bootcampRepository = bootcampRepository;
        this.bootcampMapper = bootcampMapper;
    }

    public BootcampDTO createBootcamp(BootcampDTO bootcampDTO) {
        BootcampEntity bootcampEntity = bootcampMapper.dtoToEntity(bootcampDTO);
        return bootcampMapper.entityToDTO(bootcampRepository.save(bootcampEntity));
    }

    public BootcampDTO getBootcampById(Long id) {
        BootcampEntity bootcamp = bootcampRepository.findById(id).get();
        return bootcampMapper.entityToDTO(bootcamp);
    }

    public List<BootcampDTO> getBootcamps() {
        List<BootcampEntity> bootcamps = bootcampRepository.findAll();
        return bootcampMapper.listEntityToDTO(bootcamps);
    }

    public void deleteBootcamp(Long id) {
        BootcampEntity bootcamp = bootcampRepository.findById(id).orElseThrow(() -> new RuntimeException("No bootcamp found"));
        if (bootcamp.getTest() == null) {
            bootcampRepository.deleteById(id);
        } else {
            throw new RuntimeException("Can't delete bootcamp while test is created");
        }
    }

    public BootcampDTO updateBootcamp(BootcampDTO bootcampDTO, Long id) {
        BootcampEntity bootcamp = bootcampRepository.findById(id).orElseThrow(() -> new RuntimeException("No bootcamp found"));
        if (bootcamp.getTest() == null) {
            BeanUtils.copyProperties(bootcampDTO, bootcamp);
            bootcamp.setId(id);
            bootcampRepository.save(bootcamp);
        } else {
            throw new RuntimeException("Can't update bootcamp while test is created");
        }
        return bootcampMapper.entityToDTO(bootcamp);
    }
}
