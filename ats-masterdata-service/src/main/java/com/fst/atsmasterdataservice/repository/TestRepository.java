package com.fst.atsmasterdataservice.repository;

import com.fst.atsmasterdataservice.entity.TestEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends CrudRepository<TestEntity, Long> {
    List<TestEntity> findAll();

    @Modifying
    @Query("delete from TestEntity t where t.id = ?1")
    void deleteById(Long id);
}
