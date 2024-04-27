package com.fst.atsmasterdataservice.mapper;

import com.fst.atsmasterdataservice.dto.TestDTO;
import com.fst.atsmasterdataservice.entity.TestEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TestMapper {

    @Mappings({
            @Mapping(target = "version", ignore = true)
    })
    TestEntity dtoToEntity(TestDTO dto);

    TestDTO entityToDTO(TestEntity entity);
}
