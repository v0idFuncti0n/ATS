package com.fst.atsmasterdataservice.mapper;

import com.fst.atsmasterdataservice.dto.TestInfoDTO;
import com.fst.atsmasterdataservice.entity.TestInfoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TestInfoMapper {

    TestInfoEntity dtoToEntity(TestInfoDTO dto);

    TestInfoDTO entityToDTO(TestInfoEntity entity);
}
