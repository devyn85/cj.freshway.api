package cjfw.wms.wd.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.cm.entity.CmSyProcessTempWdEntity;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.wd.dto.WdTaskSkuReqDto;
import cjfw.wms.wd.dto.WdTaskSkuResDetailDto;
import cjfw.wms.wd.dto.WdTaskSkuResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.09.29 
 * @description : 피킹작업지시(상품별) Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.09.29 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WdTaskSkuService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "wdTaskSkuService.";
	private transient static final String PAKAGE_NAME = "SPWD_TASK";
	private transient static final String PROCESSTYPE = "WD_TASK_SKU";
	private transient static final String TEMPTABLETYPE = "WD";
	
	private final CommonDao commonDao;
	private final UserContext userContext;
	private final CmCommonService cmCommonService;

	/**
	 * @description : 피킹작업지시(상품별)-조회생성 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdTaskSkuResDto> getMasterList(WdTaskSkuReqDto dto) {
		log.info("### parameter = "+dto.toString());
		
		List<WdTaskSkuResDto> list = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
		return list;
	}
	/**
	 * @description : 피킹작업지시(상품별)-조회생성 상세 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdTaskSkuResDetailDto> getDetailList(WdTaskSkuReqDto dto) {
		
		log.info("#### parameter = "+dto.toString());		
		
		List<WdTaskSkuResDetailDto> list = commonDao.selectList(SERVICEID_PREFIX + "getDetailList", dto);
		return list;
	}

	/**
	 * @description : 피킹지시(대상확정) 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String savePickingBatch(WdTaskSkuReqDto paramDto) throws Exception {
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage  = "";
		String sAVC_DCCODE = "";
		
		// 파라미터 위변조 적용(paramDto->reqDto)
		WdTaskSkuReqDto reqDto = ModelMapperUtil.map(paramDto, WdTaskSkuReqDto.class);
		List<WdTaskSkuResDetailDto> saveList = reqDto.getSavePickingBatchList(); // 저장리스트
		reqDto.setPackagename(PAKAGE_NAME);
		reqDto.setProcesstype(PROCESSTYPE);
		log.info("▶reqDto.size->{}",reqDto);
		log.info("▶saveList.size->{}",saveList);
		log.info("▶command->{}",reqDto.getAvc_COMMAND());
		
		/*1. 임시테이블 삭제*/
		commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp"+TEMPTABLETYPE, reqDto); 
		int chunkSize = 200;
		List<CmSyProcessTempWdEntity> insertList = new ArrayList<>();
		
		List<WdTaskSkuResDetailDto> list = new ArrayList<WdTaskSkuResDetailDto>();		
		for (int i = 0; i < saveList.size(); i++) {
			WdTaskSkuResDetailDto dto = saveList.get(i);
			
			CmSyProcessTempWdEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempWdEntity.class);
        	entity.setPackagename(PAKAGE_NAME); // 패키지명
        	entity.setProcesstype(PROCESSTYPE); // 프로세스타입
			
			log.info("▶변환후 entity->{}",entity);
			// START.필수입력 check - 그리드 변수 등
			//CREATETYPE|WAVEKEY|DCCODE|STORERKEY|TASKDT|OTHER01|OTHER02|OTHER03|SKU
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDccode())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"dccode"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getStorerkey())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"storerkey"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getTaskdt())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"taskdt"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getOther01())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"other01"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getOther02())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"other02"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getOther03())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"other03"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getSku())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"sku"}));

			// END.필수입력 check
        	insertList.add(entity);
        	sAVC_DCCODE = entity.getDccode();
        	
        	// 200개마다 혹은 마지막 루프일 때 insert(3/3)
        	if (insertList.size() == chunkSize || i == saveList.size() -1) {
        		commonDao.insert(SERVICEID_PREFIX + "insertTempExcel", insertList); 
            	insertList.clear();
            }
        	
		}
		
		// PKG 파라마터 세팅 - 공통(1/4)
		ProcedureParametersFactory.initParamDto(reqDto,reqDto, PAKAGE_NAME);
		
		// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
		String[] keyList   = {"PROCEDURE", "PROCESSTYPE"};
		Object[] valueList = {PAKAGE_NAME, PROCESSTYPE};
		reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));	
		reqDto.setAvc_DCCODE(sAVC_DCCODE);
		int rv = cmCommonService.saveProcedure(reqDto); 
		log.info("rv->{}",rv);
		
		// 프로시저 OUT Parameter(3/4)
		resultCode    = StringUtil.nvl(reqDto.getResultCode());
		resultMessage = StringUtil.nvl((String)reqDto.getResultMessage());
		log.info("resultCode->{}",resultCode);
		log.info("resultMessage->{}",resultMessage);
		
		// 프로시저 Exception 처리(4/4)
		if(!"0".equals(resultCode)){
			log.error("▶대상확정시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"대상확정"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}
		/*END.PAKAGE 호출*/  
		
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	

}
