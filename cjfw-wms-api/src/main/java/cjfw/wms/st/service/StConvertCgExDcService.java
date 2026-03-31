package cjfw.wms.st.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.Page;
import cjfw.core.model.UserContext;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.st.dto.StConvertCgExDcDetailListReqDto;
import cjfw.wms.st.dto.StConvertCgExDcDetailListResDto;
import cjfw.wms.st.dto.StConvertCgExDcHeaderListReqDto;
import cjfw.wms.st.dto.StConvertCgExDcHeaderListResDto;
import cjfw.wms.st.dto.StConvertCgExDcHeaderSubListReqDto;
import cjfw.wms.st.dto.StConvertCgExDcHeaderSubListResDto;
import cjfw.wms.st.dto.StConvertCgExDcSaveReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved. 
 *
 * @author : ParkJinWoo 
 * @date : 2025.06.17
 * @description : 외부비축재고속성변경 기능을 구현한 Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.17 ParkJinWoo 생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StConvertCgExDcService{
	private transient static final String SEERVICED_PREFIX = "stConvertCgExDcService.";
	private transient static final String PAKAGE_NAME = "SPST_CONVERT";
	private final CommonDao commonDao;
	private final UserContext userContext;
	private final CmCommonService cmCommonService;	
	/**
	 * @description : 외부비축재고속성변경 헤더목록 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.18 ParkJinWoo 생성
	 */
	public List<StConvertCgExDcHeaderListResDto> getStConertCgExDcHeaderList(StConvertCgExDcHeaderListReqDto stConertCgExDcHeaderListReqDto){
		return commonDao.selectList(SEERVICED_PREFIX + "getStConertCgExDcHeaderList",stConertCgExDcHeaderListReqDto);
		
	}
	/**
	 * @description : 외부비축재고속성변경 헤더서브목록 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.18 ParkJinWoo 생성
	 */
	public List<StConvertCgExDcHeaderSubListResDto> getStConertCgExDcHeaderHeaderSubList(StConvertCgExDcHeaderSubListReqDto stConertCgExDcHeaderSubListReqDto){
		return commonDao.selectList(SEERVICED_PREFIX + "getStConertCgExDcHeaderSubList",stConertCgExDcHeaderSubListReqDto);
		
	}
	/**
	 * @description : 외부비축재고속성변경 상세목록 조회 기능을 구현한 Method 
	 * @issues : 
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.18 ParkJinWoo 생성
	 */
	public Page<StConvertCgExDcDetailListResDto> getStConertCgExDcDetailList(StConvertCgExDcDetailListReqDto stConertCgExDcDetailListReqDto, Page page){
		 Page<StConvertCgExDcDetailListResDto> result = commonDao.selectPageList(SEERVICED_PREFIX + "getStConertCgExDcDetailList",stConertCgExDcDetailListReqDto,page);
		return result;
		
	}
	
	public String saveStConertCgExDc( StConvertCgExDcSaveReqDto saveReqDto) {
		String resultCode = "";
		String resultMessage = "";
		
		List<StConvertCgExDcDetailListResDto> saveList = saveReqDto.getSaveList();
//		String avc_DCCODE = saveReqDto.getAvc_DCCODE();
		if(null != saveReqDto) {
			for(StConvertCgExDcDetailListResDto dto : saveList) {
				 // PKG 파라마터 세팅 - 공통(1/4)
				dto.setPackagename(PAKAGE_NAME);            // 패키지명 SPST_CONVERT
                dto.setAvc_COMMAND("CONFIRM");              // 액션커멘드
//                dto.setConvertType("CG");					// CONVERTTYPE=CG
                dto.setAvc_SYSTEM(dto.getGSystem());        // 시스템
                dto.setAvc_DCCODE(dto.getDcCode());        // 센터코드
                dto.setAvc_STORERKEY(dto.getGStorerkey());  // 고객사코드
                dto.setAvc_WORKER(dto.getGUserId());        // 작업자
                dto.setAi_SPID(dto.getGSpid());             // SPID
                
                
                String[] keyList = {
                	    "DCCODE",
                	    "STORERKEY",
                	    "ORGANIZE",
                	    "AREA",
                	    "FROM_LOC",
                	    "SKU",
                	    "UOM",
                	    "FROM_LOT",
                	    "FROM_STOCKID",
                	    "FROM_STOCKGRADE",
                	    "FROM_STOCKTYPE",
                	    "TRANQTY",
                	    "CONVERTTYPE",
                	    "LOTTABLE01",
                	    "REASONCODE",
                	    "REASONMSG",
                	    "TO_STOCKGRADE",
                	    "TRANBOX"
                	};
                Object[] valueList = {
                			dto.getDcCode(),        	// "DCCODE"
                	        dto.getGStorerkey(),     // "STORERKEY"
                	        dto.getOrganize(),      // "ORGANIZE"
                	        dto.getArea(),          // "AREA"
                	        dto.getFromLoc(),       // "FROM_LOC"
                	        dto.getSku(),           // "SKU"
                	        dto.getUom(),           // "UOM"
                	        dto.getFromLot(),       // "FROM_LOT"
                	        dto.getFromStockId(),   // "FROM_STOCKID"
                	        dto.getFromStockGrade(),// "FROM_STOCKGRADE"
                	        dto.getFromStockType(), // "FROM_STOCKTYPE"
                	        dto.getTranQty(),       // "TRANQTY"
                	        "CG",
                	        dto.getLotTable01(),    // "LOTTABLE01"
                	        dto.getReasonCode(),    // "REASONCODE"
                	        dto.getReasonMsg(),     // "REASONMSG"
                	        dto.getToStockGrade(),  // "TO_STOCKGRADE"
                	        dto.getTranBox()        // "TRANBOX"
                };
                dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
//                commonDao.exec(CommonConstants.COMMON_CALLPROCEDURE, dto); 
                int rv = cmCommonService.saveProcedure(dto); 
                resultCode    = (String)dto.getResultCode();
                resultMessage = (String)dto.getResultMessage();
                
                log.info("resultCode->{}",resultCode);
                log.info("resultMessage->{}",resultMessage);
                
                // 프로시저 Exception 처리(4/4)
                if(!"0".equals(resultCode)){
                    log.error("▶외부비축소비기한변경 저장 -> 저장 오류 발생 ");
                    throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
                
                }
			}
		}
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}

} 