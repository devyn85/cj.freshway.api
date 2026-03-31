package cjfw.wms.dev.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.Page;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.ms.dto.MsSkuDcSetReqDto;
import cjfw.wms.ms.dto.MsSkuDcSetResDto;
import cjfw.wms.ms.entity.MsSkuDcSetEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.05.23 
 * @description : 센터상품속성 Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.05.23    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DevPilot01Service {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "devPilot01Service.";
	
	private final CommonDao commonDao;
	
	private final UserContext userContext;

	/**
	 * @description : 센터상품속성 목록 페이징 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.23    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	public Page<MsSkuDcSetResDto> getSkuDcSetPagingList(MsSkuDcSetReqDto dto, Page page) {
		Page<MsSkuDcSetResDto> result = commonDao.selectPageList(SERVICEID_PREFIX + "getSkuDcSetList", dto, page);
		return result;		
	}
	
	
	/**
	 * @description : 센터상품속성 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.23    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	public List<MsSkuDcSetResDto> getSkuDcSetLList(MsSkuDcSetReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "geSkuDcSetList", dto);
	}
	
	/**
	 * @description : 센터상품속성 단건 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.23    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	public MsSkuDcSetResDto getSkuDcSetDtl(MsSkuDcSetReqDto dto) {
		return commonDao.selectOne(SERVICEID_PREFIX + "getSkuDcSetDtl", dto);
	}
	
	/**
	 * @description : 센터상품속성 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.22    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	public String saveSkuDcSet(MsSkuDcSetReqDto reqDto) {
        if (null != reqDto) {
            for (MsSkuDcSetResDto skuDcSet : reqDto.getSaveList()) {
                MsSkuDcSetEntity msSkuDcSetEntity = ModelMapperUtil.map(skuDcSet, userContext, MsSkuDcSetEntity.class);
                if ((CanalFrameConstants.INSERT).equals(skuDcSet.getRowStatus())) {
                    commonDao.insert(SERVICEID_PREFIX +"insert", msSkuDcSetEntity);
                } else if ((CanalFrameConstants.UPDATE).equals(skuDcSet.getRowStatus())) {
                    //commonDao.update(SERVICEID_PREFIX +"update", msSkuDcSetEntity);
                    commonDao.selectOne(SERVICEID_PREFIX +"update", msSkuDcSetEntity);                  
                } else if ((CanalFrameConstants.DELETE).equals(skuDcSet.getRowStatus())) {
                    commonDao.delete(SERVICEID_PREFIX +"delete", msSkuDcSetEntity);
                }
            }
        }
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

}
