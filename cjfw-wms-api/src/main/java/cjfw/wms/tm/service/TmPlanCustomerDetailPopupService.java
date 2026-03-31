package cjfw.wms.tm.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.wms.tm.dto.TmCrmWmsMemoReqDto;
import cjfw.wms.tm.dto.TmPlanCustomerDetailPopupMemoReqDto;
import cjfw.wms.tm.dto.TmPlanCustomerDetailPopupReqDto;
import cjfw.wms.tm.dto.TmPlanCustomerDetailPopupResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : JuSungbin (wntjdqls9818@cj.net) 
 * @date : 2025.10.29 
 * @description : 거래처 상세 팝업 Service 
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.29 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TmPlanCustomerDetailPopupService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
    private transient static final String SERVICEID_PREFIX = "tmPlanCustomerDetailPopupService.";

    private final CommonDao commonDao;
    private final TmCrmWmsMemoService tmCrmWmsMemoService;
    
    /**
     * @description :  실착지기준 거래처 상세팝업 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.29 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
    public List<TmPlanCustomerDetailPopupResDto> getMasterList(TmPlanCustomerDetailPopupReqDto dto) {
    	dto.setCustCodeListToArray();
    	return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
    }
    
    /**
     * @description : 거래처 상세팝업 일별 메모 업데이트 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.29 JuSungbin (wntjdqls9818@cj.net) 생성 </pre>
     */
    public String saveMemo(TmPlanCustomerDetailPopupMemoReqDto dto) {
    	commonDao.update(SERVICEID_PREFIX + "saveMemo", dto);

    	// CRM 일별메모 신규 등록
    	TmCrmWmsMemoReqDto memoReqDto = new TmCrmWmsMemoReqDto();
    	memoReqDto.setRowStatus(CanalFrameConstants.INSERT);
    	memoReqDto.setMemoLevel("M");
    	memoReqDto.setCustKey(dto.getCustkey());
    	memoReqDto.setMemoType("03");
    	memoReqDto.setInquiryDate(LocalDate.now().plusDays(1).format(DateTimeFormatter.BASIC_ISO_DATE));
    	memoReqDto.setTransTarget("02");
    	memoReqDto.setSourceSystem("WMS");
    	memoReqDto.setDescription(dto.getMemo());
    	memoReqDto.setCarNo(dto.getCarno());
    	tmCrmWmsMemoService.saveDetail(memoReqDto);

    	return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    
}
