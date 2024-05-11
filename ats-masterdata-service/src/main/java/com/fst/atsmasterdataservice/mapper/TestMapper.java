package com.fst.atsmasterdataservice.mapper;

import com.fst.atsmasterdataservice.dto.TestDTO;
import com.fst.atsmasterdataservice.dto.candidate.CandidateDTO;
import com.fst.atsmasterdataservice.entity.TestEntity;
import com.fst.atsmasterdataservice.entity.candidate.CandidateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TestMapper {

    TestEntity dtoToEntity(TestDTO dto);

    TestDTO entityToDTO(TestEntity entity);

    List<TestDTO> listEntityToDTO(List<TestEntity> tests);

}
