package com.fst.atsmasterdataservice.mapper;

import com.fst.atsmasterdataservice.dto.TestDTO;
import com.fst.atsmasterdataservice.dto.TestInfoDTO;
import com.fst.atsmasterdataservice.entity.TestEntity;
import com.fst.atsmasterdataservice.entity.TestInfoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TestInfoMapper {

    TestInfoEntity dtoToEntity(TestInfoDTO dto);

    TestInfoDTO entityToDTO(TestInfoEntity entity);

    List<TestInfoDTO> listEntityToDTO(List<TestInfoEntity> testsInfo);
}
