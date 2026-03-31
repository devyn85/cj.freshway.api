package cjfw.wms.tm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.model.UserContext;
import cjfw.wms.tm.dto.TmMultiPopAllocationReqDto;
import cjfw.wms.tm.dto.TmMultiPopAllocationResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.06.23
 * @description : 거래처별이중 POP 배차 현황 Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.23    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmMultiPopAllocationService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "tmMultiPopAllocationService.";
	
	private final CommonDao commonDao;
	
	private final UserContext userContext;

	/**
	 * @description : 거래처별이중 POP 배차 현황 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.23    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	public List<TmMultiPopAllocationResDto> getMasterList(TmMultiPopAllocationReqDto tmMultiPopAllocationReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMultiPopAllocationList", tmMultiPopAllocationReqDto);
	}
	
	/**
     * @description : 배차조정 저장
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.06.23    KimSunHo(sunhokim6229@cj.net)   생성
     */
//	
//    public String saveTmInplan(TmMultiPopAllocationReqDto reqDto) {
//        if (null != reqDto) {
//            for (TmMultiPopAllocationResDto dto : reqDto.getSaveList()) {                
//                TmMultiPopAllocationEntity tmMultiPopAllocationEntity = ModelMapperUtil.map(dto, userContext, TmMultiPopAllocationEntity.class);
//                commonDao.selectOne(SERVICEID_PREFIX + "updateDlvInfo", tmMultiPopAllocationEntity);
//                
//                if ("CJFW".equals(reqDto.getGStorerkey())) {
//                    commonDao.selectOne(SERVICEID_PREFIX + "updateDmDocumentHDocNo", tmMultiPopAllocationEntity);
//                } else if ("FW00".equals(reqDto.getGStorerkey())) {
//                    commonDao.insert(SERVICEID_PREFIX + "insertIfTmResultDocNo", tmMultiPopAllocationEntity);    
//                }
//            }
//        }
//        return CanalFrameConstants.MSG_COM_SUC_CODE;
//    }

}
