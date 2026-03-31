package cjfw.wms.sysmgt.func.menuI18N.entity;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import cjfw.wms.sysmgt.func.menu.entity.MenuEntity;

/**
 * MenuI18NEntityMapper
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuI18NEntityMapper {

    MenuI18NEntityMapper INSTANCE = Mappers.getMapper(MenuI18NEntityMapper.class);

    /**
     * MenuEntity => MenuI18NEntity
     * @param entity
     * @return
     */
    MenuI18NEntity menuEntityToEntity(MenuEntity entity);
}
