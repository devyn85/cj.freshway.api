package cjfw.wms.portal.common.main.entity;

import cjfw.core.model.UserContext;
import cjfw.wms.portal.common.main.dto.UserMyMenuDeleteReqDto;
import cjfw.wms.portal.common.main.dto.UserMyMenuInsertReqDto;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * Dto -> Entity Mapper
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMyMenuEntityMapper {

    UserMyMenuEntityMapper INSTANCE = Mappers.getMapper(UserMyMenuEntityMapper.class);

    /**
     * UserMyMenuInsertReqDto => UserMyMenuEntity
     * @param dto
     * @param userContext
     * @return
     */
    UserMyMenuEntity insertMyMenuDtoToEntity(UserMyMenuInsertReqDto dto, UserContext userContext);

    /**
     * UserMyMenuDeleteReqDto => UserMyMenuEntity
     * @param dto
     * @param userContext
     * @return
     */
    UserMyMenuEntity deleteMyMenuDtoToEntity(UserMyMenuDeleteReqDto dto, UserContext userContext);
}
