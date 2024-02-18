package com.fst.atsmasterdataservice.repository;

import com.fst.atsmasterdataservice.entity.LanguageEntity;
import com.fst.atsmasterdataservice.entity.SkillEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LanguageRepository extends CrudRepository<LanguageEntity, Long> {
    Optional<LanguageEntity> findById(Long id);
}
