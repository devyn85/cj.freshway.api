package cjfw.wms.st.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.st.dto.StConvertCFMDetailResDto;
import cjfw.wms.st.dto.StConvertCFMReqDto;
import cjfw.wms.st.dto.StConvertCFMResDto;
import cjfw.wms.st.entity.StConvertCFMEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.07.11 
 * @description : 중계영업확정처리 Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.11    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StConvertCFMService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "stConvertCFMService.";	
	
	private final CommonDao commonDao;
	
	private final UserContext userContext;
	
	/**
	 * @description : 중계영업확정처리 헤더 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.07.11    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	public List<StConvertCFMResDto> getMasterList(StConvertCFMReqDto paramDto) {	    
	    StConvertCFMReqDto reqDto = ModelMapperUtil.map(paramDto, StConvertCFMReqDto.class);
	    
	    if ("20".equals(reqDto.getMapDiv())) {
	        return commonDao.selectList(SERVICEID_PREFIX + "getDataHeaderList", reqDto);
	    } else {
	        // "10", "30", "40"
	        return commonDao.selectList(SERVICEID_PREFIX + "getDataLocalHeaderList", reqDto);
	    }
	}
	
	
	/**
     * @description : 중계영업확정처리 상세 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.07.11    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<StConvertCFMDetailResDto> getDetailList(StConvertCFMReqDto paramDto) {     
        StConvertCFMReqDto reqDto = ModelMapperUtil.map(paramDto, StConvertCFMReqDto.class);
        
        if ("C".equals(reqDto.getCfmType())) {
            // 확정 탭
            if ("20".equals(reqDto.getMapDiv())) {
                return commonDao.selectList(SERVICEID_PREFIX + "getDataDetailList", reqDto);
            } else if ("10".equals(reqDto.getMapDiv()) || "30".equals(reqDto.getMapDiv())) {
                return commonDao.selectList(SERVICEID_PREFIX + "getDataLocalDetailList", reqDto);
            } else {                
                // "40"
                return null;
            }
        } else {
            // 반려 탭
            if ("20".equals(reqDto.getMapDiv())) {
                return commonDao.selectList(SERVICEID_PREFIX + "getDataDetailList", reqDto);
            } else if ("10".equals(reqDto.getMapDiv()) || "30".equals(reqDto.getMapDiv())) {
                return commonDao.selectList(SERVICEID_PREFIX + "getDataLocalDetailList", reqDto);
            } else {
                // "40"
                return null;
            }
        }
    }
    
    /**
     * @description : 중계영업확정처리 저장
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.07.11    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public String saveMasterList(StConvertCFMReqDto paramDto) {        
        StConvertCFMReqDto reqDto = ModelMapperUtil.map(paramDto, StConvertCFMReqDto.class);
        
        if (null != reqDto) {
            for (StConvertCFMDetailResDto dto : reqDto.getSaveList()) {
                StConvertCFMEntity entity = ModelMapperUtil.map(dto, userContext, StConvertCFMEntity.class);
                
                if ("20".equals(reqDto.getMapDiv())) {
                    commonDao.insert(SERVICEID_PREFIX +"save", entity);
                } else if ("10".equals(reqDto.getMapDiv()) || "30".equals(reqDto.getMapDiv())) {
                    commonDao.insert(SERVICEID_PREFIX +"saveLocal", entity);
                }
            }                
        }

        return CanalFrameConstants.MSG_COM_SUC_CODE;        
    }
    
    /**
     * @description : 중계영업확정처리 확정
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.07.11    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public String confirmMasterList(StConvertCFMReqDto paramDto) {        
        StConvertCFMReqDto reqDto = ModelMapperUtil.map(paramDto, StConvertCFMReqDto.class);
        
        if (null != reqDto) {
            for (StConvertCFMDetailResDto dto : reqDto.getSaveList()) {
                StConvertCFMEntity entity = ModelMapperUtil.map(dto, userContext, StConvertCFMEntity.class);
                
                int checkExistCnt = 0;
                HashMap<String, Object> checkExist = commonDao.selectOne(SERVICEID_PREFIX + "checkExist", entity);

                if (checkExist == null || checkExist.isEmpty()) {
                    checkExistCnt = 0;
                } else {
                    Object cntObj = checkExist.get("CHECK_CNT");
                    if (cntObj != null) {
                        checkExistCnt = Integer.parseInt(cntObj.toString());
                    }
                }
                
                if((checkExistCnt > 0)){
                    log.error("▶중계영업확정처리 확정 -> 이미 확정되었습니다.");
                    throw new UserHandleException(""+"이미 확정되었습니다."); 
                } else {
                    if ("20".equals(reqDto.getMapDiv())) {
                        commonDao.insert(SERVICEID_PREFIX +"confirm", entity);
                    } else if ("10".equals(reqDto.getMapDiv()) || "30".equals(reqDto.getMapDiv())) {
                        commonDao.insert(SERVICEID_PREFIX +"confirmLocal", entity);
                    }
                }
            }                
        }

        return CanalFrameConstants.MSG_COM_SUC_CODE;        
    }
    
    /**
     * @description : 중계영업확정처리 반려
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.07.11    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public String rejectMasterList(StConvertCFMReqDto paramDto) {        
        StConvertCFMReqDto reqDto = ModelMapperUtil.map(paramDto, StConvertCFMReqDto.class);
        
        if (null != reqDto) {
            for (StConvertCFMDetailResDto dto : reqDto.getSaveList()) {
                StConvertCFMEntity entity = ModelMapperUtil.map(dto, userContext, StConvertCFMEntity.class);
                
                int checkExistCnt = 0;
                HashMap<String, Object> checkExist = commonDao.selectOne(SERVICEID_PREFIX + "checkExist", entity);

                if (checkExist == null || checkExist.isEmpty()) {
                    checkExistCnt = 0;
                } else {
                    Object cntObj = checkExist.get("CHECK_CNT");
                    if (cntObj != null) {
                        checkExistCnt = Integer.parseInt(cntObj.toString());
                    }
                }
                
                if((checkExistCnt > 0)){
                    log.error("▶중계영업확정처리 반려 -> 이미 반려되었습니다.");
                    throw new UserHandleException(""+"이미 반려되었습니다."); 
                } else {
                    if ("20".equals(reqDto.getMapDiv())) {
                        commonDao.insert(SERVICEID_PREFIX +"reject", entity);
                    } else if ("10".equals(reqDto.getMapDiv()) || "30".equals(reqDto.getMapDiv())) {
                        commonDao.insert(SERVICEID_PREFIX +"rejectLocal", entity);
                    }
                }
            } 
        }

        return CanalFrameConstants.MSG_COM_SUC_CODE;        
    }
    
}
