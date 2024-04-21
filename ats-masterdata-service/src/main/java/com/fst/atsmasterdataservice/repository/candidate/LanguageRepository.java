package com.fst.atsmasterdataservice.repository.candidate;

import com.fst.atsmasterdataservice.entity.candidate.LanguageEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageRepository extends CrudRepository<LanguageEntity, Long> {
    Optional<LanguageEntity> findById(Long id);
    @Modifying
    @Query("delete from LanguageEntity l where l.id = ?1")
    void deleteById(Long id);
}
