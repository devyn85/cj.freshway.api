package cjfw.wms.wd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.wd.dto.WdBeforeOrderAdjustRequestReqDto;
import cjfw.wms.wd.dto.WdBeforeOrderAdjustRequestResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/**
 *  
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 *
 * @author      : JangJaeHyun (jhjang43@cj.net)
 * @date        : 2025.09.25
 * @description : 사전주문 조정의뢰 기능을 구현한 Service Class
 * @issues      :
 * -----------------------------------------------------------
 * DATE              AUTHOR             MAJOR_ISSUE
 * -----------------------------------------------------------
 * 2025.09.25        JangJaeHyun (jhjang43@cj.net)       생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WdBeforeOrderAdjustRequestService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "wdBeforeOrderAdjustRequestService.";
	private transient static final String PAKAGE_NAME = "SPWD_BEFOREORDER";
	private final CommonDao commonDao;
	private final UserContext userContext;
	
	/**
	 *  공통.Service
	 */
	private final CmCommonService cmCommonService;

	/**
	 * @description : 저장품발주삭제PO 마스터 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.12        JangJaeHyun (jhjang43@cj.net)       생성
	 */
	public List<WdBeforeOrderAdjustRequestResDto> getMasterList(WdBeforeOrderAdjustRequestReqDto wdBeforeOrderAdjustRequestReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getMasterList", wdBeforeOrderAdjustRequestReqDto);
	}
	
	/**
	 *  조회생성 화면에서 사용자가 사전주문조정의뢰를 생성할때 처리
	 */
	public String saveOrderRequest(WdBeforeOrderAdjustRequestReqDto reqDto) throws Exception {
		
		// 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        
        List<WdBeforeOrderAdjustRequestReqDto> saveList = reqDto.getSaveList(); // 저장리스트
        String avc_DCCODE = reqDto.getAvc_DCCODE(); // 센터코드
        
        log.info("▶saveList.size->{}",saveList);
        log.info("▶avc_DCCODE->{}",avc_DCCODE);
        
        String skuName = "";
        
		if (null != saveList) {
			for (WdBeforeOrderAdjustRequestReqDto dto : reqDto.getSaveList()) { 
				// PKG 파라마터 세팅 - 공통(1/4)
				ProcedureParametersFactory.initParamDto(reqDto, dto, PAKAGE_NAME);
				
				// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
				
				String[] keyList = {"DCCODE","STORERKEY","DOCDT","DOCNO","DOCLINE","SKU","MD_RMK","TEXT","ALTSKU","RE_DELIVERYDATE"};
				Object[] valueList = {dto.getDcCode(),dto.getStorerKey(),dto.getDocDt(),dto.getDocNo(),dto.getDocLine(),
						dto.getSku(),dto.getMdRmk(),dto.getText(),dto.getAltSku(), dto.getReDeliveryDate()};
				dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
				commonDao.exec(CommonConstants.COMMON_CALLPROCEDURE, dto); 
				
				// 프로시저 OUT Parameter(3/4)
				resultCode    = (String)dto.getResultCode();
				resultMessage = (String)dto.getResultMessage();
				
				log.info("resultCode->{}",resultCode);
				log.info("resultMessage->{}",resultMessage);
				
				// 프로시저 Exception 처리(4/4)
				if(!resultCode.equals("0")){
					log.error("▶입고검수처리 - 저장 시 오류 발생 ");
					throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
				}
				
				skuName = "[" + dto.getSku() + "]" + dto.getSkuName() + " 저장 되었습니다.";
			}
			
		}
		return skuName;
	}

}
