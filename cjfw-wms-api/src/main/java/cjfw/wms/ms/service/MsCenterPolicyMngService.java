package cjfw.wms.ms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.ms.dto.MsCenterPolicyMngDetailResDto;
import cjfw.wms.ms.dto.MsCenterPolicyMngLocationResDto;
import cjfw.wms.ms.dto.MsCenterPolicyMngReqDto;
import cjfw.wms.ms.dto.MsCenterPolicyMngResDto;
import cjfw.wms.ms.entity.MsCarrierEntity;
import cjfw.wms.ms.entity.MsCenterPolicyMngEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net)
 * @date : 2025.10.20 
 * @description : 기준정보 > 센터기준정보 > 센터정책관리 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.20 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MsCenterPolicyMngService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "msCenterPolicyMngService.";
	
	private final CommonDao commonDao;

	private final UserContext userContext;
	
	/**
	 * @description : 센터정책관리 조회(목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.20 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<MsCenterPolicyMngResDto> getMasterList(MsCenterPolicyMngReqDto dto) {
		List<MsCenterPolicyMngResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
		return result;
	}
	
	/**
	 * @description : 센터정책관리 상세조회(목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.20 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<MsCenterPolicyMngDetailResDto> getDetailList(MsCenterPolicyMngReqDto dto) {
		List<MsCenterPolicyMngDetailResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getDetailList", dto);
		
		return result;
	}
	
	/**
	 * @description : 센터정책관리 로케이션 조회(목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.20 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<MsCenterPolicyMngLocationResDto> getLocationList(MsCenterPolicyMngReqDto dto) {
		List<MsCenterPolicyMngLocationResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getLocationList", dto);
		
		return result;
	}
	
	/**
	 * @description : 센터정책관리 코드 유효성검사
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.26 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public String getValidateCodeList(List<MsCenterPolicyMngReqDto> dtoList) {
		
		for(MsCenterPolicyMngReqDto dto : dtoList) {
			
			MsCenterPolicyMngDetailResDto result = commonDao.selectOne(SERVICEID_PREFIX + "validateCodeList", dto);
			
			if (result.getCodeChk() == 0 && (StringUtils.hasText(dto.getCommCd()) || StringUtils.hasText(dto.getCommDescr()))) {
		    	throw new UserHandleException("코드 혹은 코드명이 존재하지 않습니다.");
		    }else if (result.getDetailCodeChk() == 0 && (StringUtils.hasText(dto.getCommDtlCd()) || StringUtils.hasText(dto.getCommDtlDescr()))) {
		    	throw new UserHandleException("상세코드 혹은 상세코드명이 존재하지 않습니다.");
		    }else if (result.getRefCodeChk() == 0 && (StringUtils.hasText(dto.getRefCommCd()) || StringUtils.hasText(dto.getRefCommDescr()))) {
		    	throw new UserHandleException("연관코드 혹은 연관코드명이 존재하지 않습니다.");
		    }
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}
	
	/**
	 * @description : 센터정책관리 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.20  생성 </pre>
	 */
	@Transactional
	public String saveMasterList(List<MsCenterPolicyMngReqDto> dtoList) {
        for (var dto : dtoList) {
           var entity = ModelMapperUtil.map(dto, userContext, MsCenterPolicyMngEntity.class);
           int existCount = commonDao.selectOne(SERVICEID_PREFIX +"checkPolicyExists", entity); 
           // 중복 검사 
           if (existCount > 0) {
               throw new UserHandleException("정책정보가 존재합니다.");
          }
          commonDao.insert(SERVICEID_PREFIX +"insertMaster", entity);
          }
     return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
	

    /**
     * @description : 센터정책관리 세부항목 저장
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2026.03.20  생성</pre>
     */
    @Transactional
    public String saveMasterList3(MsCenterPolicyMngReqDto dto) {
        List<MsCenterPolicyMngEntity> detailList = dto.getDetailList();
        // 센터정책 기타설정
        if (null != detailList) {
            for (MsCenterPolicyMngEntity detailEntity : detailList) {
                if ((CanalFrameConstants.INSERT).equals(detailEntity.getRowStatus())) {
                    commonDao.insert(SERVICEID_PREFIX +"insertDetailList", detailEntity);
                } else if ((CanalFrameConstants.UPDATE).equals(detailEntity.getRowStatus())) {
                    commonDao.update(SERVICEID_PREFIX +"updateDetailList", detailEntity);
                }                
            }
        }
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }    
	/**
	 * @description : 센터정책관리 기타정책 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.20  </pre>
	 */
	@Transactional
	public String saveMasterList2(MsCenterPolicyMngReqDto dto) {
		List<MsCenterPolicyMngEntity> detailList = dto.getDetailList();
		List<MsCenterPolicyMngEntity> locationList = dto.getLocationList();
		MsCenterPolicyMngEntity entity = ModelMapperUtil.map(dto, userContext, MsCenterPolicyMngEntity.class);
//		String plcycode = dto.getPlcycode();
//		// 센터정책
//		if ((CanalFrameConstants.INSERT).equals(dto.getRowStatus())) {
//			plcycode = commonDao.selectOne(SERVICEID_PREFIX + "getPlcycode", dto);
//			entity.setPlcycode(plcycode);
//			commonDao.insert(SERVICEID_PREFIX +"insertMaster", entity);
//		} else if ((CanalFrameConstants.UPDATE).equals(dto.getRowStatus())) {
		
//			
		commonDao.update(SERVICEID_PREFIX +"updateMaster", entity);
//		} 
//		
//		// 센터정책 기타설정
		if (null != detailList) {
			for (MsCenterPolicyMngEntity detailEntity : detailList) {
//				if ((CanalFrameConstants.INSERT).equals(detailEntity.getRowStatus())) {
//					detailEntity.setPlcycode(plcycode);
//					commonDao.insert(SERVICEID_PREFIX +"insertDetailList", detailEntity);
//				} else if ((CanalFrameConstants.UPDATE).equals(detailEntity.getRowStatus())) {
					commonDao.update(SERVICEID_PREFIX +"updateDetailList1", detailEntity);
//				}				
			}
		}
//		
//		// 멀티로케이션 설정
		if (null != locationList) {
			for (MsCenterPolicyMngEntity locationEntity : locationList) {
				commonDao.selectOne(SERVICEID_PREFIX +"updateLocationList", locationEntity);
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
	
}
