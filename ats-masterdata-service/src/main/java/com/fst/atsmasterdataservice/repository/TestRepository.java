package com.fst.atsmasterdataservice.repository;

import com.fst.atsmasterdataservice.entity.TestEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends CrudRepository<TestEntity, Long> {
    List<TestEntity> findAll();
}
