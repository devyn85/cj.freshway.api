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
import cjfw.wms.wd.dto.WdTaskReqDto;
import cjfw.wms.wd.dto.WdTaskResPrintBarcodeDto;
import cjfw.wms.wd.dto.WdTaskResPrintDto;
import cjfw.wms.wd.dto.WdTaskResTab1DetailDto;
import cjfw.wms.wd.dto.WdTaskResTab1Dto;
import cjfw.wms.wd.dto.WdTaskResTab2DetailDto;
import cjfw.wms.wd.dto.WdTaskResTab2Dto;
import cjfw.wms.wd.dto.WdTaskResTab3Dto;
import cjfw.wms.wd.dto.WdTaskResTab4Dto;
import cjfw.wms.wd.entity.WdTaskMobileEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.08.29 
 * @description : 피킹작업지시 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.08.29 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WdTaskService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "wdTaskService.";
	private transient static final String PAKAGE_NAME = "SPWD_TASK";
	private transient static final String PROCESSTYPE = "WD_TASK";
	private transient static final String PROCESSTYPE_DIVISION = "WD_TASK_DIVISION";
	private transient static final String PROCESSTYPE_MERGE = "WD_TASK_MERGE";
	private transient static final String TEMPTABLETYPE = "WD";
	
	private final CommonDao commonDao;
	private final UserContext userContext;
	private final CmCommonService cmCommonService;

	/**
	 * @description : 피킹작업지시-조회생성 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdTaskResTab1Dto> getTab1MasterList(WdTaskReqDto dto) {
		log.info("### parameter = "+dto.toString());
		
		List<WdTaskResTab1Dto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab1MasterList", dto);
		return list;
	}
	/**
	 * @description : 피킹작업지시-조회생성 상세 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdTaskResTab1DetailDto> getTab1DetailList(WdTaskReqDto dto) {
		
		log.info("#### parameter = "+dto.toString());		
		
		List<WdTaskResTab1DetailDto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab1DetailList", dto);
		return list;
	}
	/**
	 * @description : 피킹작업지시-진행현황 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdTaskResTab2Dto> getTab2MasterList(WdTaskReqDto dto) {
		
		log.info("##### parameter = "+dto.toString());		
		
		List<WdTaskResTab2Dto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab2MasterList", dto);
		return list;
	}
	
	/**
	 * @description : 피킹작업지시-진행현황 상세 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.29 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdTaskResTab2DetailDto> getTab2DetailList(WdTaskReqDto dto) { 
		
		log.info("##### parameter = "+dto.toString());		
		
		List<WdTaskResTab2DetailDto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab2DetailList", dto);
		return list;
	}
	
	/**
	 * @description : 피킹작업지시-피킹작업자현황 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.08.11 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdTaskResTab3Dto> getTab3MasterList(WdTaskReqDto dto) { 
		
		log.info("##### parameter = "+dto.toString());		
		
		List<WdTaskResTab3Dto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab3MasterList", dto);
		return list;
	}
	
	/**
	 * @description : 피킹작업지시-조회생성(차량) 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.19 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdTaskResTab4Dto> getTab4MasterList(WdTaskReqDto dto) {
		log.info("### parameter = "+dto.toString());
		
		List<WdTaskResTab4Dto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab4MasterList", dto);
		return list;
	}
	
	/**
	 * @description : 피킹작업지시-피킹리스트 STD 출력 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.03 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public WdTaskResPrintDto getPrintSTDList(WdTaskReqDto dto) { 
		WdTaskResPrintDto reportDto = new WdTaskResPrintDto();		
		log.info("##### parameter = "+dto.toString());		
		
		reportDto.setReportSTDlList(commonDao.selectList(SERVICEID_PREFIX + "getPrintSTDList", dto));
		return reportDto;
	}
	
	
	/**
	 * @description : 피킹작업지시-피킹리스트 출력 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.03 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public WdTaskResPrintDto getPrintList(WdTaskReqDto dto) { 
		WdTaskResPrintDto reportDto = new WdTaskResPrintDto();
		log.info("##### parameter = "+dto.toString());		
		
		commonDao.insert(SERVICEID_PREFIX + "saveInvoiceNoPrtYn", dto);
		reportDto.setReportHeaderList(commonDao.selectList(SERVICEID_PREFIX + "getPrintHeaderList", dto));
		reportDto.setReportDetailList(commonDao.selectList(SERVICEID_PREFIX + "getPrintDetailList", dto));
		return reportDto;
	}
	
	/**
	 * @description : 피킹작업지시-피킹리스트 멀티 출력 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.23 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public WdTaskResPrintDto getMultiPrintList(WdTaskReqDto dto) { 
		WdTaskResPrintDto reportDto = new WdTaskResPrintDto();
		log.info("##### parameter = "+dto.toString());		
		

		String pickListNos = dto.getPickListNos(); // "1,2,3,4,5"

		if (pickListNos != null) {
		    String[] types = pickListNos.split(","); // 콤마를 기준으로 자르기
		    for (String t : types) {
		        System.out.println("꺼내온 값: " + t.trim());
		        dto.setPickListNo(t.trim());
		        commonDao.insert(SERVICEID_PREFIX + "saveInvoiceNoPrtYn", dto);
		    }
		}		
		reportDto.setReportHeaderList(commonDao.selectList(SERVICEID_PREFIX + "getMultiPrintHeaderList", dto));
		reportDto.setReportDetailList(commonDao.selectList(SERVICEID_PREFIX + "getMultiPrintDetailList", dto));
		return reportDto;
	}
	
	public static boolean isNull(String str) {
		return str == null || str.trim().isEmpty();
	}
	
	/**
	 * @description : 피킹작업지시-PLT바코드 출력 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.22 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public WdTaskResPrintDto getPrintBarcodeList(WdTaskReqDto dto) { 
		WdTaskResPrintDto reportDto = new WdTaskResPrintDto();
		WdTaskResPrintBarcodeDto barcodeDto = new WdTaskResPrintBarcodeDto();
		
		log.info("##### parameter = "+dto.toString());		
		//log.info("##### 쿼리 barcode = "+commonDao.selectOne(SERVICEID_PREFIX + "getPrintBarcodeList", dto));		
		
		if(!isNull(dto.getPrintcnt())) {
		
			String barcode = commonDao.selectOne(SERVICEID_PREFIX + "getPrintBarcodeList", dto);
			
			log.info("##### barcode = "+barcode);		
			
			if(isNull(barcode)) {
				//해당 센터의 바코드번호가 없을때 insert
				//바코드형태 예시 : PLT_2600_00000000000001 (구분자_센터코드+ 박스번호)
				barcode = "PLT_"+dto.getFixdccode()+"_00000000000001";
				commonDao.insert(SERVICEID_PREFIX + "insertBarcode", dto);
			} else {
				//해당 센터의 바코드번호가 있을때 update
				dto.setBarcode(barcode);
				commonDao.update(SERVICEID_PREFIX + "updateBarcode", dto);
			}
			
			
			
			barcodeDto.setPrintcnt(dto.getPrintcnt());
			barcodeDto.setStartBarcodeNo(barcode);
		}
		
		reportDto.setReportBarcodelList(barcodeDto);
				
		return reportDto;
	}
	

	/**
	 * @description : 피킹생성 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.02 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String savePickingBatch(WdTaskReqDto paramDto) throws Exception {
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage  = "";
		String sAVC_DCCODE = "";
		
		// 파라미터 위변조 적용(paramDto->reqDto)
		WdTaskReqDto reqDto = ModelMapperUtil.map(paramDto, WdTaskReqDto.class);
		List<WdTaskResTab1Dto> saveList = reqDto.getSavePickingBatchList(); // 저장리스트
		reqDto.setPackagename(PAKAGE_NAME);
		reqDto.setProcesstype(PROCESSTYPE);
		log.info("▶reqDto.size->{}",reqDto);
		log.info("▶saveList.size->{}",saveList);
		log.info("▶command->{}",reqDto.getAvc_COMMAND());
		
		/*1. 임시테이블 삭제*/
		commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp"+TEMPTABLETYPE, reqDto); 
		int chunkSize = 200;
		List<CmSyProcessTempWdEntity> insertList = new ArrayList<>();
		
		List<WdTaskResTab1Dto> list = new ArrayList<WdTaskResTab1Dto>();		
		for (int i = 0; i < saveList.size(); i++) {
			WdTaskResTab1Dto dto = saveList.get(i);
			
			CmSyProcessTempWdEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempWdEntity.class);
        	entity.setPackagename(PAKAGE_NAME); // 패키지명
        	entity.setProcesstype(PROCESSTYPE); // 프로세스타입
			
			log.info("▶변환후 entity->{}",entity);
			// START.필수입력 check - 그리드 변수 등
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDccode())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"dccode"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getStorerkey())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"storerkey"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getTaskdt())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"taskdt"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getOther01())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"other01"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getOther02())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"other02"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getOther03())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"other03"}));

			// END.필수입력 check
        	insertList.add(entity);
        	
        	// 200개마다 혹은 마지막 루프일 때 insert(3/3)
        	if (insertList.size() == chunkSize || i == saveList.size() -1) {
        		commonDao.insert(SERVICEID_PREFIX + "insertTempExcel", insertList); 
            	insertList.clear();
            }
        	sAVC_DCCODE = entity.getDccode();
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
			log.error("▶피킹지시시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"피킹지시"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}
		/*END.PAKAGE 호출*/  
		
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
	
	/**
	 * @description : 피킹생성취소
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.04 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String savePickingBatchDelete(WdTaskReqDto paramDto) throws Exception {
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage  = "";
		String sAVC_DCCODE = "";
		
		// 파라미터 위변조 적용(paramDto->reqDto)
		WdTaskReqDto reqDto = ModelMapperUtil.map(paramDto, WdTaskReqDto.class);
		List<WdTaskResTab2Dto> saveList = reqDto.getSavePickingBatchDeleteList(); // 저장리스트
		reqDto.setPackagename(PAKAGE_NAME);
		reqDto.setProcesstype(PROCESSTYPE);
		log.info("▶reqDto.size->{}",reqDto);
		log.info("▶saveList.size->{}",saveList);
		log.info("▶command->{}",reqDto.getAvc_COMMAND());
		
		/*1. 임시테이블 삭제*/
		commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp"+TEMPTABLETYPE, reqDto); 
		int chunkSize = 200;
		List<CmSyProcessTempWdEntity> insertList = new ArrayList<>();
		
		List<WdTaskResTab2Dto> list = new ArrayList<WdTaskResTab2Dto>();		
		for (int i = 0; i < saveList.size(); i++) {
			WdTaskResTab2Dto dto = saveList.get(i);
			
			CmSyProcessTempWdEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempWdEntity.class);
			entity.setPackagename(PAKAGE_NAME); // 패키지명
			entity.setProcesstype(PROCESSTYPE); // 프로세스타입
			
			log.info("▶변환후 entity->{}",entity);
			// START.필수입력 check - 그리드 변수 등
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDccode())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"dccode"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getOrganize())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"organize"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getStorerkey())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"storerkey"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getTaskdt())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"작업일자"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getTasksystem())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"작업방법"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getPickBatchNo())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"대배치키"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getPickListNo())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"피킹리스트번호"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getPickNo())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"피킹번호"}));
			
			// END.필수입력 check
			insertList.add(entity);
			
			// 200개마다 혹은 마지막 루프일 때 insert(3/3)
			if (insertList.size() == chunkSize || i == saveList.size() -1) {
				commonDao.insert(SERVICEID_PREFIX + "insertTempExcel", insertList); 
				insertList.clear();
			}
			sAVC_DCCODE = entity.getDccode();
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
			log.error("▶피킹생성취소시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"피킹생성취소"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}
		/*END.PAKAGE 호출*/  
		
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
	
	/**
	 * @description : 수동피킹 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.02 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String saveManualPickingBatch(WdTaskReqDto paramDto) throws Exception {
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage  = "";
		String sAVC_DCCODE = "";
		
		// 파라미터 위변조 적용(paramDto->reqDto)
		WdTaskReqDto reqDto = ModelMapperUtil.map(paramDto, WdTaskReqDto.class);
		List<WdTaskResTab2Dto> saveList = reqDto.getSaveManualPickingBatchList(); // 저장리스트
		reqDto.setPackagename(PAKAGE_NAME);
		reqDto.setProcesstype(PROCESSTYPE);
		log.info("▶reqDto.size->{}",reqDto);
		log.info("▶saveList.size->{}",saveList);
		log.info("▶command->{}",reqDto.getAvc_COMMAND());
		
		/*1. 임시테이블 삭제*/
		commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp"+TEMPTABLETYPE, reqDto); 
		int chunkSize = 200;
		List<CmSyProcessTempWdEntity> insertList = new ArrayList<>();
		
		List<WdTaskResTab2Dto> list = new ArrayList<WdTaskResTab2Dto>();		
		for (int i = 0; i < saveList.size(); i++) {
			WdTaskResTab2Dto dto = saveList.get(i);
			
			CmSyProcessTempWdEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempWdEntity.class);
			entity.setPackagename(PAKAGE_NAME); // 패키지명
			entity.setProcesstype(PROCESSTYPE); // 프로세스타입
			
			log.info("▶변환후 entity->{}",entity);
			// START.필수입력 check - 그리드 변수 등
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDccode())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"dccode"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getOrganize())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"organize"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getStorerkey())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"storerkey"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getTaskdt())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"작업일자"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getTasksystem())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"작업방법"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getPickBatchNo())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"대배치키"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getPickListNo())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"피킹리스트번호"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getPickNo())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"피킹번호"}));
			
			// END.필수입력 check
			insertList.add(entity);
			
			// 200개마다 혹은 마지막 루프일 때 insert(3/3)
			if (insertList.size() == chunkSize || i == saveList.size() -1) {
				commonDao.insert(SERVICEID_PREFIX + "insertTempExcel", insertList); 
				insertList.clear();
			}
			sAVC_DCCODE = entity.getDccode();
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
			log.error("▶수동피킹시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"수동피킹"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}
		/*END.PAKAGE 호출*/  
		
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
	
	/**
	 * @description : 모바일피킹지시 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.22 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String saveMobilePickingBatch(WdTaskReqDto paramDto) throws Exception {
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage  = "";
		
		// 파라미터 위변조 적용(paramDto->reqDto)
		WdTaskReqDto reqDto = ModelMapperUtil.map(paramDto, WdTaskReqDto.class);
		List<WdTaskResTab2Dto> saveList = reqDto.getSaveMobilePickingBatchList(); // 저장리스트
		log.info("▶reqDto.size->{}",reqDto);
		log.info("▶saveList.size->{}",saveList);
		log.info("▶mobileFlag->{}",reqDto.getMobileFlag());
		
		int chunkSize = 200;
		List<WdTaskMobileEntity> insertList = new ArrayList<>();
			
		for (int i = 0; i < saveList.size(); i++) {
			WdTaskResTab2Dto dto = saveList.get(i);
			
			WdTaskMobileEntity entity = ModelMapperUtil.map(dto, userContext, WdTaskMobileEntity.class);
			
			entity.setMobileFlag(reqDto.getMobileFlag());
			
			log.info("▶변환후 entity->{}",entity);
			// START.필수입력 check - 그리드 변수 등
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getTaskdt())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"작업일자"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getTasksystem())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"작업방법"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getPickBatchNo())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"대배치키"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getPickListNo())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"피킹리스트번호"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getPickNo())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"파킹번호"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getMobileFlag())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"모바일지시여부"}));
			
			// END.필수입력 check
			insertList.add(entity);
			
			// 200개마다 혹은 마지막 루프일 때 insert(3/3)
			if (insertList.size() == chunkSize || i == saveList.size() -1) {
				commonDao.insert(SERVICEID_PREFIX + "updateMobileOrder", insertList); 
				insertList.clear();
			}
		}
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
	
	/**
	 * @description : 피킹분리 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.02 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String saveDivisionTask(WdTaskReqDto paramDto) throws Exception {
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage  = "";
		String sAVC_DCCODE = "";
		
		// 파라미터 위변조 적용(paramDto->reqDto)
		WdTaskReqDto reqDto = ModelMapperUtil.map(paramDto, WdTaskReqDto.class);
		List<WdTaskResTab2DetailDto> saveList = reqDto.getSaveDivisionTaskList(); // 저장리스트
		reqDto.setPackagename(PAKAGE_NAME);
		reqDto.setProcesstype(PROCESSTYPE_DIVISION);
		log.info("▶reqDto.size->{}",reqDto);
		log.info("▶saveList.size->{}",saveList);
		log.info("▶command->{}",reqDto.getAvc_COMMAND());
		
		/*1. 임시테이블 삭제*/
		commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp"+TEMPTABLETYPE, reqDto); 
		int chunkSize = 200;
		List<CmSyProcessTempWdEntity> insertList = new ArrayList<>();
		
		List<WdTaskResTab2DetailDto> list = new ArrayList<WdTaskResTab2DetailDto>();		
		for (int i = 0; i < saveList.size(); i++) {
			WdTaskResTab2DetailDto dto = saveList.get(i);
			
			CmSyProcessTempWdEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempWdEntity.class);
			entity.setPackagename(PAKAGE_NAME); // 패키지명
			entity.setProcesstype(PROCESSTYPE_DIVISION); // 프로세스타입
			
			log.info("▶변환후 entity->{}",entity);
			// START.필수입력 check - 그리드 변수 등
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDccode())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"dccode"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getOrganize())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"organize"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getStorerkey())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"storerkey"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getTaskdt())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"작업일자"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getTasksystem())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"작업방법"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getSlipdt())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"전표일자"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getSlipno())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"전표번호"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDocdt())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"docdt"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDocno())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"docno"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getWavekey())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"wavekey"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getCustkey())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"고객코드"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getSku())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"상품코드"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getPickBatchNo())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"대배치키"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getPickListNo())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"피킹리스트번호"}));
			if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getPickNo())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"피킹번호"}));
			
			// END.필수입력 check
			insertList.add(entity);
			
			// 200개마다 혹은 마지막 루프일 때 insert(3/3)
			if (insertList.size() == chunkSize || i == saveList.size() -1) {
				commonDao.insert(SERVICEID_PREFIX + "insertTempExcel", insertList); 
				insertList.clear();
			}
			sAVC_DCCODE = entity.getDccode();
		}
		
		// PKG 파라마터 세팅 - 공통(1/4)
		ProcedureParametersFactory.initParamDto(reqDto,reqDto, PAKAGE_NAME);
		
		// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
		String[] keyList   = {"PROCEDURE", "PROCESSTYPE"};
		Object[] valueList = {PAKAGE_NAME, PROCESSTYPE_DIVISION};
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
			log.error("▶피킹분리시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"피킹분리"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}
		/*END.PAKAGE 호출*/  
		
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
	
	/**
	 * @description : 피킹병합 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.22 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String saveMergeTask(WdTaskReqDto paramDto) throws Exception {
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage  = "";
		String sAVC_DCCODE = "";
		
		// 파라미터 위변조 적용(paramDto->reqDto)
		WdTaskReqDto reqDto = ModelMapperUtil.map(paramDto, WdTaskReqDto.class);
		List<WdTaskResTab2Dto> saveList = reqDto.getSaveMergeTaskList(); // 저장리스트
		reqDto.setPackagename(PAKAGE_NAME);
		reqDto.setProcesstype(PROCESSTYPE_MERGE);
		log.info("▶reqDto.size->{}",reqDto);
		log.info("▶saveList.size->{}",saveList);
		log.info("▶command->{}",reqDto.getAvc_COMMAND());
		
		/*1. 임시테이블 삭제*/
		commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp"+TEMPTABLETYPE, reqDto); 
		int chunkSize = 200;
		List<CmSyProcessTempWdEntity> insertList = new ArrayList<>();
		
		List<WdTaskResTab2Dto> list = new ArrayList<WdTaskResTab2Dto>();		
		for (int i = 0; i < saveList.size(); i++) {
			WdTaskResTab2Dto dto = saveList.get(i);
			//log.info("▶dto.getPickNo->{}",dto.getPickNo());
			if(!dto.getPickNo().equals(reqDto.getMinPickNo())) {
				//병합하려는(minPicNo) 행은 병합대상에서 제외한다.
				CmSyProcessTempWdEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempWdEntity.class);
				entity.setPackagename(PAKAGE_NAME); // 패키지명
				entity.setProcesstype(PROCESSTYPE_MERGE); // 프로세스타입
				
				log.info("▶변환후 entity->{}",entity);
				// START.필수입력 check - 그리드 변수 등
				if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDccode())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"dccode"}));
				if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getOrganize())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"organize"}));
				if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getStorerkey())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"storerkey"}));
				if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getTaskdt())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"작업일자"}));
				if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getTasksystem())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"작업방법"}));
				if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getPickBatchNo())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"대배치키"}));
				if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getPickListNo())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"피킹리스트번호"}));
				if ("".equals(cjfw.core.utility.StringUtil.nvl(entity.getPickNo())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"피킹번호"}));
				
				// END.필수입력 check
				insertList.add(entity);
				sAVC_DCCODE = entity.getDccode();
			}
			// 200개마다 혹은 마지막 루프일 때 insert(3/3)
			if (insertList.size() == chunkSize || i == saveList.size() -1) {
				commonDao.insert(SERVICEID_PREFIX + "insertTempExcel", insertList); 
				insertList.clear();
			}			
		}
		
		// PKG 파라마터 세팅 - 공통(1/4)
		ProcedureParametersFactory.initParamDto(reqDto,reqDto, PAKAGE_NAME);
		
		// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
		String[] keyList   = {"PROCEDURE", "PROCESSTYPE", "MINPICKNO", 			 "MINPICKBATCHNO", 			 "MINPICKLISTNO"};
		Object[] valueList = {PAKAGE_NAME, PROCESSTYPE_MERGE,   reqDto.getMinPickNo(), reqDto.getMinPickBatchNo(), reqDto.getMinPickListNo()	};
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
			log.error("▶피킹병합시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"피킹병합"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}
		/*END.PAKAGE 호출*/  
		
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	
	

	/**
	 * @description : 피킹작업자 삭제
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.09.03 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public String deletePicker(WdTaskReqDto paramDto) throws Exception {
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage  = "";
		// 파라미터 위변조 적용(paramDto->reqDto)
		WdTaskReqDto reqDto = ModelMapperUtil.map(paramDto, WdTaskReqDto.class);
		
		List<WdTaskResTab3Dto> rawList = reqDto.getDeletePickerList();
		log.info("▶rawList.size->{}",rawList);     
		WdTaskResTab3Dto dto  = rawList.get(0);			    
		log.info("▶dto->{}",dto);
		ProcedureParametersFactory.initParamDto(reqDto,reqDto, PAKAGE_NAME);
				
		String[] keyList   = {"PROCEDURE" ,"DCCODE"    	 	,"DCCODE"		 ,"TASKDT"		  ,"TASKSYSTEM"		   ,"PICKER"		};														
		Object[] valueList = {PAKAGE_NAME ,dto.getDccode() 	,dto.getDccode() ,dto.getTaskdt() ,dto.getTasksystem() ,dto.getPicker()	};
		reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
		reqDto.setAvc_DCCODE(dto.getDccode());		
		int rv = cmCommonService.saveProcedure(reqDto); 
		log.info("rv->{}",rv);
		
		// 프로시저 OUT Parameter(3/4)
		resultCode    = StringUtil.nvl(reqDto.getResultCode());
		resultMessage = StringUtil.nvl((String)reqDto.getResultMessage());
		log.info("resultCode->{}",resultCode);
		log.info("resultMessage->{}",resultMessage);
		
		// 프로시저 Exception 처리(4/4)
		if(!"0".equals(resultCode)){
			log.error("▶피킹작업자 삭제시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"피킹작업자 삭제"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}  	
		/*END.PAKAGE 호출*/  
		
		
		return CanalFrameConstants.MSG_COM_SUC_CODE;
	}	

}
