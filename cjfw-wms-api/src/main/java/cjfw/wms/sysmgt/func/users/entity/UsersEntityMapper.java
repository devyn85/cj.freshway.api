package cjfw.wms.sysmgt.func.users.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import cjfw.core.model.UserContext;
import cjfw.wms.sysmgt.func.users.dto.UsersSaveReqDto;

/**
 * Dto -> Entity Mapper
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsersEntityMapper {

    UsersEntityMapper INSTANCE = Mappers.getMapper(UsersEntityMapper.class);

    @Mapping(source = "userContext.userId", target = "regId")
    @Mapping(source = "userContext.userId", target = "modId")
    @Mapping(source = "dto.userId", target = "userId")
    @Mapping(source = "dto.userNm", target = "userNm")
    UsersEntity saveUsersDtoToEntity(UsersSaveReqDto.User dto, UserContext userContext);
}
