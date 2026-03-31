package cjfw.wms.sysmgt.func.ipallow.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import cjfw.core.model.UserContext;
import cjfw.wms.sysmgt.func.ipallow.dto.IpAllowSaveReqDto;

/**
 * Dto -> Entity Mapper
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IpAllowEntityMapper {

    IpAllowEntityMapper INSTANCE = Mappers.getMapper(IpAllowEntityMapper.class);

    @Mapping(source = "userContext.userId", target = "regId")
    @Mapping(source = "dto.userId", target = "userId")
    IpAllowEntity saveIpAllowDtoToEntity(IpAllowSaveReqDto.IpAllow dto, UserContext userContext);
}
