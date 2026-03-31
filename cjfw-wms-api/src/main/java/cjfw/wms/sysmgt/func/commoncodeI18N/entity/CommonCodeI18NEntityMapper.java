package cjfw.wms.sysmgt.func.commoncodeI18N.entity;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import cjfw.wms.sysmgt.func.commoncode.entity.CommonCodeDtlEntity;
import cjfw.wms.sysmgt.func.commoncode.entity.CommonCodeGrpEntity;

/**
  CommonCodeI18NEntityMapper
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommonCodeI18NEntityMapper {
    CommonCodeI18NEntityMapper INSTANCE = Mappers.getMapper(CommonCodeI18NEntityMapper.class);

    /**
     * CommonCodeDtlEntity => CommonCodeI18NEntity
     * @param entity
     * @return
     */
    CommonCodeI18NEntity dtlCdEntityToEntity(CommonCodeDtlEntity entity);

    /**
     * CommonCodeGrpEntity => CommonCodeI18NEntity
     * @param entity
     * @return
     */
    CommonCodeI18NEntity grpCdEntityToEntity(CommonCodeGrpEntity entity);
}
