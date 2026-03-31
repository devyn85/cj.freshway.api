package cjfw.wms.sysmgt.func.commoncode.entity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import cjfw.core.model.UserContext;
import cjfw.wms.sysmgt.func.commoncode.dto.CommonCodeSaveReqDto;

/**
 * Dto -> Entity Mapper
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommonCodeDtlEntityMapper {
    CommonCodeDtlEntityMapper INSTANCE = Mappers.getMapper(CommonCodeDtlEntityMapper.class);

    /**
     * CommonCodeSaveReqDto.CodeDtl => CommonCodeDtlEntity
     * @param dto
     * @param userContext
     * @return
     */
    @Mapping(source = "userContext.userId", target = "regId")
    @Mapping(source = "userContext.userId", target = "modId")
    CommonCodeDtlEntity saveCodeDtlDtoToEntity(CommonCodeSaveReqDto.CodeDtl dto, UserContext userContext);
}
