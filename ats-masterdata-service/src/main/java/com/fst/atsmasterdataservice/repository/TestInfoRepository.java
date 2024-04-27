package com.fst.atsmasterdataservice.repository;

import com.fst.atsmasterdataservice.entity.BootcampEntity;
import com.fst.atsmasterdataservice.entity.TestEntity;
import com.fst.atsmasterdataservice.entity.TestInfoEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestInfoRepository extends CrudRepository<TestInfoEntity, Long> {
    @Modifying
    @Query("delete from TestInfoEntity t where t.id = ?1")
    void deleteById(Long id);

    List<TestInfoEntity> findByTestOrderByFinalNoteDescIdAsc(TestEntity test);
}
