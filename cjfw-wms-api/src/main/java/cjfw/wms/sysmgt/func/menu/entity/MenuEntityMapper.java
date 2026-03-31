package cjfw.wms.sysmgt.func.menu.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import cjfw.core.model.UserContext;
import cjfw.wms.sysmgt.func.menu.dto.MenuSaveReqDto;

/**
 * MenuEntityMapper
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuEntityMapper {

    MenuEntityMapper INSTANCE = Mappers.getMapper(MenuEntityMapper.class);

    @Mapping(source = "userContext.userId", target = "regId")
    MenuEntity saveMenuDtoToEntity(MenuSaveReqDto.Menu dto, UserContext userContext);
}
