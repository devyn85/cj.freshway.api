package cjfw.wms.wd.service;

import java.beans.PropertyDescriptor;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

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
import cjfw.wms.wd.dto.WdDeliveryLabelReqDto;
import cjfw.wms.wd.dto.WdDeliveryLabelResTab1DetailDto;
import cjfw.wms.wd.dto.WdDeliveryLabelResTab1Dto;
import cjfw.wms.wd.dto.WdDeliveryLabelResTab1PrintDto;
import cjfw.wms.wd.dto.WdDeliveryLabelResTab1ReportCrossDto;
import cjfw.wms.wd.dto.WdDeliveryLabelResTab1ReportDto;
import cjfw.wms.wd.entity.WdDeliveryLabelPrintStoEntity;
import cjfw.wms.wd.dto.WdDeliveryLabelResCenterPickGroupDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : 공두경 (medstorm@cj.net) 
 * @date : 2025.11.15 
 * @description : 배송라벨출력 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.11.15 공두경 (medstorm@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WdDeliveryLabelService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "wdDeliveryLabelService.";
	private transient static final String PAKAGE_NAME = "SPWD_INVOICE";
	private transient static final String PROCESSTYPE = "WD_DELIVERYLABEL";
	private transient static final String TEMPTABLETYPE = "WD";
	
	private final CommonDao commonDao;
	private final UserContext userContext;
	private final CmCommonService cmCommonService;

	/**
	 * @description : 배송라벨출력-분류표출력 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.15 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdDeliveryLabelResTab1Dto> getTab1MasterList(WdDeliveryLabelReqDto dto) {
		log.info("### parameter = "+dto.toString());
		
		List<WdDeliveryLabelResTab1Dto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab1MasterList", dto);
		return list;
	}
	
	/**
	 * @description : 배송라벨출력-분류표출력 상세 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.15 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdDeliveryLabelResTab1DetailDto> getTab1DetailList(WdDeliveryLabelReqDto dto) {
		
		log.info("#### parameter = "+dto.toString());		
		
		List<WdDeliveryLabelResTab1DetailDto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab1DetailList", dto);
		return list;
	}
	
	
	/**
	 * @description : 배송분류표회수리스트 일반 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.15 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdDeliveryLabelResTab1ReportDto> getTab1ReportList(WdDeliveryLabelReqDto dto) {
		
		log.info("#### parameter = "+dto.toString());		
		
		List<WdDeliveryLabelResTab1ReportDto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab1ReportList", dto);
		return list;
	}
	/**
	 * @description : 배송분류표회수리스트 Cross 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.15 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdDeliveryLabelResTab1ReportCrossDto> getTab1ReportCrossList(WdDeliveryLabelReqDto dto) {
		
		log.info("#### parameter = "+dto.toString());		
		
		List<WdDeliveryLabelResTab1ReportCrossDto> list = commonDao.selectList(SERVICEID_PREFIX + "getTab1ReportCrossList", dto);
		return list;
	}
	/**
	 * @description : 센터별 피킹그룹 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.12.19 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdDeliveryLabelResCenterPickGroupDto> getCenterPickGroupList(WdDeliveryLabelReqDto dto) {
		
		log.info("#### parameter = "+dto.toString());		
		
		List<WdDeliveryLabelResCenterPickGroupDto> list = commonDao.selectList(SERVICEID_PREFIX + "getCenterPickGroupList", dto);
		return list;
	}
	
	/**
	 * @description : 배송라벨출력 인쇄 생성(목록)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.15 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdDeliveryLabelResTab1PrintDto> savePrintHeaderList(WdDeliveryLabelReqDto paramDto) throws Exception {
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage  = "";
		
		// 파라미터 위변조 적용(paramDto->reqDto)
		WdDeliveryLabelReqDto reqDto = ModelMapperUtil.map(paramDto, WdDeliveryLabelReqDto.class);
		List<WdDeliveryLabelResTab1Dto> saveList = reqDto.getSavePrintHeaderList(); // 저장리스트
		reqDto.setPackagename(PAKAGE_NAME);
		reqDto.setProcesstype(PROCESSTYPE);
		log.info("▶reqDto.size->{}",reqDto);
		log.info("▶saveList.size->{}",saveList);
		log.info("▶command->{}",reqDto.getAvc_COMMAND());
		
		/*1. 임시테이블 삭제*/
		commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp"+TEMPTABLETYPE, reqDto); 
		int chunkSize = 200;
		List<CmSyProcessTempWdEntity> insertList = new ArrayList<>();
		List<WdDeliveryLabelResTab1PrintDto> printList = new ArrayList<>();
		List<WdDeliveryLabelResTab1PrintDto> tempList = new ArrayList<>();
		/*2.목록그리드의 체크된 row 항목 임시테이블에 저장*/
		for (int i = 0; i < saveList.size(); i++) {
			WdDeliveryLabelResTab1Dto dto = saveList.get(i);
			
			reqDto.setDccode(dto.getDccode());
			reqDto.setSlipdt(dto.getSlipdt());
			reqDto.setTasksystem(dto.getTasksystem());
			reqDto.setPickListNo(dto.getPickListNo());
			
//			if(dto.getTasksystem().equals("STO")) {
//				//STO 조회
//	        	/*3.목록그리드의 체크된 row를 기준으로 상세그리드 전체 조회*/
//				reqDto.setInvoiceno(dto.getPickListNo());
//				
//				//log.info("▶ ######List->{}",commonDao.selectList(SERVICEID_PREFIX + "getTab1DetailSTOList", reqDto));
//				reqDto.setSavePrintDetailList(commonDao.selectList(SERVICEID_PREFIX + "getTab1DetailSTOList", reqDto));
//	        	tempList = savePrintDetailList(reqDto);
//	        	
//	        	
//			} else {
				//SO 조회
				/*3.목록그리드의 체크된 row를 기준으로 상세그리드 전체 조회*/
				reqDto.setPickBatchNo(dto.getPickBatchNo());
				reqDto.setPickNo(dto.getPickNo());
										
				reqDto.setSavePrintDetailList(commonDao.selectList(SERVICEID_PREFIX + "getTab1DetailList", reqDto));
	        	tempList = savePrintDetailList(reqDto);
			//}
        	
			printList.addAll(tempList);
		}		
		
		if(!isNull(reqDto.getPrintorder())) {
			List<HashMap> sortList = commonDao.selectList(SERVICEID_PREFIX + "getDataSortList", reqDto);
						
			if(sortList.size() > 0) {
				Map<String, Object> sortMap = sortList.get(0);
			
			    reqDto.setPrdOrd1(Optional.ofNullable(sortMap.get("PRD_ORD1")).map(Object::toString).orElse(""));
			    reqDto.setPrdOrd2(Optional.ofNullable(sortMap.get("PRD_ORD2")).map(Object::toString).orElse(""));
			    reqDto.setPrdOrd3(Optional.ofNullable(sortMap.get("PRD_ORD3")).map(Object::toString).orElse(""));
			    reqDto.setPrdOrd4(Optional.ofNullable(sortMap.get("PRD_ORD4")).map(Object::toString).orElse(""));
			    reqDto.setPrdOrd5(Optional.ofNullable(sortMap.get("PRD_ORD5")).map(Object::toString).orElse(""));
			    reqDto.setPrdOrd6(Optional.ofNullable(sortMap.get("PRD_ORD6")).map(Object::toString).orElse(""));
			    reqDto.setPrdOrd7(Optional.ofNullable(sortMap.get("PRD_ORD7")).map(Object::toString).orElse(""));
			    reqDto.setPrdOrd8(Optional.ofNullable(sortMap.get("PRD_ORD8")).map(Object::toString).orElse(""));
			    
			 // 1. 정렬 기준 컬럼 리스트 추출 (Snake -> Camel 변환)
			    List<String> sortColumns = Stream.of("PRD_ORD1", "PRD_ORD2", "PRD_ORD3", "PRD_ORD4", 
			                                         "PRD_ORD5", "PRD_ORD6", "PRD_ORD7", "PRD_ORD8")
			        .map(key -> (String) sortMap.get(key))
			        .filter(val -> val != null && !val.trim().isEmpty()) // 값이 있는 것만 필터링
			        .map(this::snakeToCamel) // Camel Case로 변환
			        .collect(Collectors.toList());
		
			    // 2. Comparator를 이용한 동적 정렬
			    if (!sortColumns.isEmpty()) {
			        printList.sort((o1, o2) -> {
			            for (String property : sortColumns) {
			                try {
			                    // Reflection을 사용하여 getter 호출 (Field 직접 접근도 가능하나 getter 권장)
			                    Object val1 = new PropertyDescriptor(property, WdDeliveryLabelResTab1PrintDto.class).getReadMethod().invoke(o1);
			                    Object val2 = new PropertyDescriptor(property, WdDeliveryLabelResTab1PrintDto.class).getReadMethod().invoke(o2);
		
			                    if (val1 == null && val2 == null) continue;
			                    if (val1 == null) return -1;
			                    if (val2 == null) return 1;
		
			                    int compare = ((Comparable<Object>) val1).compareTo(val2);
			                    if (compare != 0) return compare; // 값이 다르면 정렬 결과 반환, 같으면 다음 우선순위 컬럼으로
			                    
			                } catch (Exception e) {
			                    log.error("Sorting error on field: " + property, e);
			                }
			            }
			            return 0;
			        });
			    }
			}
		}
		return printList;
	}

	public static boolean isNull(String str) {		 
		return str == null || str.trim().isEmpty();
	}
	
	private String snakeToCamel(String str) {
	    if (str == null || str.isEmpty()) return "";
	    StringBuilder result = new StringBuilder();
	    boolean nextUpper = false;
	    for (char c : str.toLowerCase().toCharArray()) {
	        if (c == '_') {
	            nextUpper = true;
	        } else {
	            if (nextUpper) {
	                result.append(Character.toUpperCase(c));
	                nextUpper = false;
	            } else {
	                result.append(c);
	            }
	        }
	    }
	    return result.toString();
	}
	
	/**
	 * @description : 배송라벨출력 인쇄 생성(상세)
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.15 공두경 (medstorm@cj.net) 생성 </pre>
	 */
	public List<WdDeliveryLabelResTab1PrintDto> savePrintDetailList(WdDeliveryLabelReqDto paramDto) throws Exception {
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage  = "";
		int chunkSize = 200;
		List<WdDeliveryLabelResTab1PrintDto> list = new ArrayList<>(); 
		
		// 파라미터 위변조 적용(paramDto->reqDto)
		WdDeliveryLabelReqDto reqDto = ModelMapperUtil.map(paramDto, WdDeliveryLabelReqDto.class);
		List<WdDeliveryLabelResTab1DetailDto> saveList = reqDto.getSavePrintDetailList(); // 저장리스트
		if(saveList.size() == 0) return list;

		String sFlag = saveList.get(0).getTasksystem();
		/*2026-02-04 STO 로직 삭제 STO 도 원로직으로가야함*/
//		if(sFlag.equals("STO")) {
//			//STO의 상세목록인 경우
//			//WD_INVOICE가 아니므로 OM_SDY_REGN_REPL_D의 PRINT_YN을 업데이트 한다.
//			List<WdDeliveryLabelPrintStoEntity> insertList = new ArrayList<>();
//
//			// 현재 시각 구하기
//			Timestamp now = Timestamp.valueOf(LocalDateTime.now());
//
//			for (int i = 0; i < saveList.size(); i++) {
//				WdDeliveryLabelResTab1DetailDto dto = saveList.get(i);
//
//				WdDeliveryLabelPrintStoEntity entity = ModelMapperUtil.map(dto, userContext, WdDeliveryLabelPrintStoEntity.class);
//
//				entity.setStotime(now);
//
//				// START.필수입력 check - 그리드 변수 등
//				if("".equals(StringUtil.nvl(entity.getInvoiceno())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"invoiceno"}));
//				if("".equals(StringUtil.nvl(entity.getDocno())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"docno"}));
//				if("".equals(StringUtil.nvl(entity.getDocline())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"docline"}));
//
//				// END.필수입력 check
//	        	insertList.add(entity);
//
//	        	// 200개마다 혹은 마지막 루프일 때 insert(3/3)
//	        	if (insertList.size() == chunkSize || i == saveList.size() -1) {
//	        		commonDao.insert(SERVICEID_PREFIX + "updateOM_SDY_REGN_REPL_D", insertList);
//	            	insertList.clear();
//	            }
//			}
//			list = commonDao.selectList(SERVICEID_PREFIX + "getTab1PrintSTOList", reqDto);
//		} else {
			//STO의 상세목록이 아닌 경우
			//WD_INVOICE의 PRINT_YN을 업데이트 한다.(기존 패키지 활용)
			
			reqDto.setPackagename(PAKAGE_NAME);
			reqDto.setProcesstype(PROCESSTYPE);
			log.info("▶reqDto.size->{}",reqDto);
			log.info("▶saveList.size->{}",saveList);
			log.info("▶command->{}",reqDto.getAvc_COMMAND());
			
			/*1. 임시테이블 삭제*/
			commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTemp"+TEMPTABLETYPE, reqDto); 
		
			List<CmSyProcessTempWdEntity> insertList = new ArrayList<>();
			
			for (int i = 0; i < saveList.size(); i++) {
				WdDeliveryLabelResTab1DetailDto dto = saveList.get(i);
				
				CmSyProcessTempWdEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempWdEntity.class);
	        	entity.setPackagename(PAKAGE_NAME); // 패키지명
	        	entity.setProcesstype(PROCESSTYPE); // 프로세스타입
			
				// START.필수입력 check - 그리드 변수 등
				if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDccode())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"dccode"}));
				if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getInvoiceno())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"invoiceno"}));
				if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getCustkey())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"custkey"}));
				if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDocno())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"docno"}));
				if("".equals(cjfw.core.utility.StringUtil.nvl(entity.getDocline())) ) throw new UserHandleException(MessageUtil.getMessage("MSG_COM_ERR_056", new String[]{"docline"}));			
	
				// END.필수입력 check
	        	insertList.add(entity);
	        	
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
			reqDto.setAvc_DCCODE(saveList.get(0).getDccode());
			int rv = cmCommonService.saveProcedure(reqDto); 
			log.info("rv->{}",rv);
			
			// 프로시저 OUT Parameter(3/4)
			resultCode    = StringUtil.nvl(reqDto.getResultCode());
			resultMessage = StringUtil.nvl((String)reqDto.getResultMessage());
			log.info("resultCode->{}",resultCode);
			log.info("resultMessage->{}",resultMessage);
			
			// 프로시저 Exception 처리(4/4)
			if(!"0".equals(resultCode)){
				log.error("▶인쇄 생성시 오류 발생 ");
				throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"인쇄 생성"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
			}
			/*END.PAKAGE 호출*/
			
		    
			list = commonDao.selectList(SERVICEID_PREFIX + "getTab1PrintList", reqDto);
		
//		}
		return list;
	}	
	
}
