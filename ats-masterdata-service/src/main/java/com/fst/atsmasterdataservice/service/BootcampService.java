package com.fst.atsmasterdataservice.service;

import com.fst.atsmasterdataservice.dto.BootcampDTO;
import com.fst.atsmasterdataservice.entity.BootcampEntity;
import com.fst.atsmasterdataservice.mapper.BootcampMapper;
import com.fst.atsmasterdataservice.repository.BootcampRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
