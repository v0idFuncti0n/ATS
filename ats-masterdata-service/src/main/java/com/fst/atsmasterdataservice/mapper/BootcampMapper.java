package com.fst.atsmasterdataservice.mapper;

import com.fst.atsmasterdataservice.dto.BootcampDTO;
import com.fst.atsmasterdataservice.entity.BootcampEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface BootcampMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "version", ignore = true)
    })
    BootcampEntity dtoToEntity(BootcampDTO dto);

    BootcampDTO entityToDTO(BootcampEntity entity);
}
