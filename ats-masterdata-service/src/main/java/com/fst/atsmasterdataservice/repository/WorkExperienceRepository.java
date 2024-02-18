package com.fst.atsmasterdataservice.repository;

import com.fst.atsmasterdataservice.entity.WorkExperienceEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface WorkExperienceRepository extends CrudRepository<WorkExperienceEntity, Long> {
    Optional<WorkExperienceEntity> findById(Long id);
}
