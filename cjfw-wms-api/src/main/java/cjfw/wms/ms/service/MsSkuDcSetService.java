package cjfw.wms.ms.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
//import cjfw.core.excel.ExcelUploader;
import cjfw.core.model.Page;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.ms.dto.MsSkuDcSetReqDto;
import cjfw.wms.ms.dto.MsSkuDcSetResDto;
import cjfw.wms.ms.entity.MsSkuDcSetEntity;
import cjfw.wms.om.dto.OmOrderCreationSTOForOutResDto;
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
public class MsSkuDcSetService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "msSkuDcSetService.";
	
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
	public Page<MsSkuDcSetResDto> getSkuDcSetPagingList(MsSkuDcSetReqDto msSkuDcSetReqDto, Page page) {
		Page<MsSkuDcSetResDto> result = commonDao.selectPageList(SERVICEID_PREFIX + "getSkuDcSetList", msSkuDcSetReqDto, page);
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
	public List<MsSkuDcSetResDto> getSkuDcSetLList(MsSkuDcSetReqDto msSkuDcSetReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "geSkuDcSetList", msSkuDcSetReqDto);
	}
	
	/**
	 * @description : 센터상품속성 단건 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.05.23    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	public MsSkuDcSetResDto getSkuDcSetDtl(MsSkuDcSetReqDto msSkuDcSetReqDto) {
		return commonDao.selectOne(SERVICEID_PREFIX + "getSkuDcSetDtl", msSkuDcSetReqDto);
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
					commonDao.insert(SERVICEID_PREFIX +"update", msSkuDcSetEntity);
				} else if ((CanalFrameConstants.UPDATE).equals(skuDcSet.getRowStatus()) || "R".equals(skuDcSet.getRowStatus())) {
					commonDao.selectOne(SERVICEID_PREFIX +"update", msSkuDcSetEntity);					
				} else if ((CanalFrameConstants.DELETE).equals(skuDcSet.getRowStatus())) {
					commonDao.delete(SERVICEID_PREFIX +"delete", msSkuDcSetEntity);
				}
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
     * @description : 센터상품속성 저장
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.05.22    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public String saveSkuDcSetExcel(MsSkuDcSetReqDto reqDto) {
        if (null != reqDto) {
            for (MsSkuDcSetResDto skuDcSet : reqDto.getSaveList()) {
                MsSkuDcSetEntity msSkuDcSetEntity = ModelMapperUtil.map(skuDcSet, userContext, MsSkuDcSetEntity.class);
                if ((CanalFrameConstants.INSERT).equals(skuDcSet.getRowStatus())) {
                    commonDao.insert(SERVICEID_PREFIX +"updateExcel", msSkuDcSetEntity);
                } else if ((CanalFrameConstants.UPDATE).equals(skuDcSet.getRowStatus()) || "R".equals(skuDcSet.getRowStatus())) {
                    commonDao.selectOne(SERVICEID_PREFIX +"updateExcel", msSkuDcSetEntity);
                } else if ((CanalFrameConstants.DELETE).equals(skuDcSet.getRowStatus())) {
                    commonDao.delete(SERVICEID_PREFIX +"delete", msSkuDcSetEntity);
                }
            }
        }
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
	
    /**
     * @description : 센터상품속성 엑셀업로드 데이터 검증
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.09.23    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<MsSkuDcSetResDto> validateExcel(MsSkuDcSetReqDto reqDto) {
        List<MsSkuDcSetResDto> result = new ArrayList<MsSkuDcSetResDto>();
        
        if (null != reqDto) {
            result = commonDao.selectList(SERVICEID_PREFIX + "getWithTempTable", reqDto);
        }
        
        for (MsSkuDcSetResDto dto : result) {
            if (ObjectUtils.isEmpty(dto.getStorerkey()) || !"FW00".equals(dto.getStorerkey())) {
                dto.setProcessflag("E");
                dto.setProcessmsg("회사코드 입력 오류");
            } else if (ObjectUtils.isEmpty(dto.getDccodename())) {
                dto.setProcessflag("E");
                dto.setProcessmsg("물류센터 입력 오류");
            } else if (ObjectUtils.isEmpty(dto.getSkuDescr())) {
                dto.setProcessflag("E");
                dto.setProcessmsg("상품 입력 오류");
            } else if (ObjectUtils.isEmpty(dto.getWharea())) {
                dto.setProcessflag("E");
                dto.setProcessmsg("창고구분 입력 오류"); 
            } else if (ObjectUtils.isEmpty(dto.getWhareafloor())) {
                dto.setProcessflag("E");
                dto.setProcessmsg("창고층 입력 오류");
            } else if (ObjectUtils.isEmpty(dto.getLoccategory())) {
                dto.setProcessflag("E");
                dto.setProcessmsg("로케이션종류 입력 오류"); 
            } else if (ObjectUtils.isEmpty(dto.getLoclevel())) {
                dto.setProcessflag("E");
                dto.setProcessmsg("로케이션레벨 입력 오류");
            } else if (ObjectUtils.isEmpty(dto.getZone())) {
                dto.setProcessflag("E");
                dto.setProcessmsg("피킹존 입력 오류");
            } else if (ObjectUtils.isEmpty(dto.getLoc())) {
                dto.setProcessflag("E");
                dto.setProcessmsg("로케이션 입력 오류");
//            } else if (!ObjectUtils.isEmpty(dto.getPutawaytype()) && ObjectUtils.isEmpty(dto.getPutawaytypename())) {
//                dto.setProcessflag("E");
//                dto.setProcessmsg("적치유형 입력 오류");
//            } else if (!ObjectUtils.isEmpty(dto.getCrossdocktype()) && ObjectUtils.isEmpty(dto.getCrossdocktypename())) {
//                dto.setProcessflag("E");
//                dto.setProcessmsg("C/D타입 입력 오류");
//            } else if (!ObjectUtils.isEmpty(dto.getOther02()) && ObjectUtils.isEmpty(dto.getOther02name())) {
//                dto.setProcessflag("E");
//                dto.setProcessmsg("선피킹조건 입력 오류");    
//            } else if (!ObjectUtils.isEmpty(dto.getSmsYn()) && (!(dto.getSmsYn().equals("Y") && !dto.getSmsYn().equals("N")))) {
//                dto.setProcessflag("E");
//                dto.setProcessmsg("소터사용여부 입력 오류");
            } else if (ObjectUtils.isEmpty(dto.getInvoiceCrtType())) {
                dto.setProcessflag("E");
                dto.setProcessmsg("배송분류표생성유형 입력 오류");
            } else if (!ObjectUtils.isEmpty(dto.getInvoiceCrtType()) && ObjectUtils.isEmpty(dto.getInvoiceCrtTypename())) {
                dto.setProcessflag("E");
                dto.setProcessmsg("배송분류표생성유형 입력 오류"); 
            } else if (ObjectUtils.isEmpty(dto.getStatusname())) {
                dto.setProcessflag("E");
                dto.setProcessmsg("진행상태 입력 오류");
            } else if (ObjectUtils.isEmpty(dto.getDelYnname())) {
                dto.setProcessflag("E");
                dto.setProcessmsg("사용여부 입력 오류");
//            } else if (!ObjectUtils.isEmpty(dto.getCubeYn()) && !(dto.getCubeYn().equals("Y") || dto.getCubeYn().equals("N"))) {
//                dto.setProcessflag("E");
//                dto.setProcessmsg("고부피여부 입력 오류");
//            } else if (!ObjectUtils.isEmpty(dto.getAbc()) && !(dto.getAbc().equals("A") || dto.getAbc().equals("B") || dto.getAbc().equals("C"))) {
//                dto.setProcessflag("E");
//                dto.setProcessmsg("ABC 입력 오류"); 
//            } else if (ObjectUtils.isEmpty(dto.getValidEffectivedate()) || "0".equals(dto.getValidEffectivedate())) {
//                dto.setProcessflag("E");
//                dto.setProcessmsg("적용일자 입력 오류"); 
            } else {
                dto.setProcessflag("Y");
            }
        }    
        
        return result;
    }

}
