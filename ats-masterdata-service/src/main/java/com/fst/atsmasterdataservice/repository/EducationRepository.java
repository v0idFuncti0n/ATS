package com.fst.atsmasterdataservice.repository;

import com.fst.atsmasterdataservice.entity.EducationEntity;
import com.fst.atsmasterdataservice.entity.WorkExperienceEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EducationRepository extends CrudRepository<EducationEntity, Long> {
    Optional<EducationEntity> findById(Long id);
}
