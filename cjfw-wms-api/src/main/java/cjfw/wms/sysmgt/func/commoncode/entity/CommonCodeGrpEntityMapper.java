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
public interface CommonCodeGrpEntityMapper {
    CommonCodeGrpEntityMapper INSTANCE = Mappers.getMapper(CommonCodeGrpEntityMapper.class);

    /**
     * CommonCodeSaveReqDto.CodeGrp => CommonCodeGrpEntity
     * @param dto
     * @param userContext
     * @return
     */
    @Mapping(source = "userContext.userId", target = "regId")
    @Mapping(source = "userContext.userId", target = "modId")
    CommonCodeGrpEntity saveCodeGrpDtoToEntity(CommonCodeSaveReqDto.CodeGrp dto, UserContext userContext);
}
