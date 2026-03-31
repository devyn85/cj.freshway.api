package cjfw.wms.om.service;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.scheduling.annotation.Async;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.ObjectUtils;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.core.utility.StringUtil;
import cjfw.wms.cm.entity.CmSyProcessTempStEntity;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.CommonConstants;
import cjfw.wms.om.dto.OmOrderCreationSTOBatchResultDetailDto;
import cjfw.wms.om.dto.OmOrderCreationSTOBatchResultHeadDto;
import cjfw.wms.om.dto.OmOrderCreationSTOOrdBaseReqDto;
import cjfw.wms.om.dto.OmOrderCreationSTOOrdBaseResDto;
import cjfw.wms.om.dto.OmOrderCreationSTOOrdBaseResultResDto;
import cjfw.wms.om.dto.OmPurchaseOutSTOReqDto;
import cjfw.wms.om.dto.OmPurchaseOutSTOResDto;
import cjfw.wms.om.entity.OmOrderCreationSTOOrdBaseEntity;
import cjfw.wms.sysmgt.func.commoncode.dto.CodeDtlAsyncTemplateGetReqDto;
import cjfw.wms.sysmgt.func.commoncode.dto.CodeDtlGetResDto;
import cjfw.wms.wd.dto.WdAllocationBatchGroupReqDto;
import cjfw.wms.wd.dto.WdAllocationBatchGroupResTab1Dto;
import cjfw.wms.webservice.mmStoIf.DT_SCM0160_SCM;
import cjfw.wms.webservice.mmStoIf.DT_SCM0160_SCMIF_DM_DOCUMENT_D;
import cjfw.wms.webservice.mmStoIf.DT_SCM0160_SCMIF_DM_DOCUMENT_H;
import cjfw.wms.webservice.mmStoIf.DT_SCM0160_SCM_responseT_RETURN;
import cjfw.wms.webservice.mmStoIf.SI_SCM0160_SCM_SOProxy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ OliveNetworks Co. all rights reserved.
 * @author : JeongHyeongCheol (wjdgudcjf@cj.net)
 * @date : 2025.10.31 
 * @description : 주문 > 주믄등록 > 당일광역보충발주 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE       AUTHOR                MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.10.31 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OmOrderCreationSTOOrdBaseService {

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "omOrderCreationSTOOrdBaseService.";
	private transient static final String SERVICEID_PO_PREFIX = "omPurchaseOutSTOServiceService.";
	private transient static final String PAKAGE_NAME = "SPOM_ORDERCREATIONSTO_ORDBASE";
	private transient static final String PAKAGE_NAME_STO = "SPWD_ALLOCATION";
	
	// 공통으로 사용할 스레드 풀 정의 (매번 생성하지 말고 빈이나 상수로 관리하는 게 좋습니다)
    private final Executor executor = Executors.newFixedThreadPool(3);
	
	private final CommonDao commonDao;

	private final UserContext userContext;
	
	private final CmCommonService cmCommonService;
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	/**
	 * @description : 당일광역보충발주 마스터 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.31 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<OmOrderCreationSTOOrdBaseResDto> getMasterList(OmOrderCreationSTOOrdBaseReqDto dto) {
		List<OmOrderCreationSTOOrdBaseResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getMasterList", dto);
		return result;
	}
	
	/**
	 * @description : 당일광역보충발주 마감유형 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.31 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<OmOrderCreationSTOOrdBaseResDto> getDailyDeadlineSto(OmOrderCreationSTOOrdBaseReqDto dto) {
		List<OmOrderCreationSTOOrdBaseResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getDailyDeadlineSto", dto);
		return result;
	}
	

	/**
	 * @throws RemoteException 
	 * @description :당일광역보충발주 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.31 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<OmOrderCreationSTOOrdBaseResultResDto> saveMasterListMulti(OmOrderCreationSTOOrdBaseReqDto dto) throws RemoteException {
				
		List<OmOrderCreationSTOOrdBaseEntity> saveList = dto.getSaveList(); // 저장리스트
		
		// 1. 독립적인 트랜잭션 템플릿 생성 (매번 만들기보다 Bean 설정을 권장하지만 우선 코드 내 적용)
	    TransactionTemplate newTemplate = new TransactionTemplate(transactionManager);
	    newTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);

	    // 2. 임시테이블 삭제 (이것도 트랜잭션 안에 넣는 것이 안전합니다)
	    newTemplate.execute(status -> {
	    	// 임시테이블 삭제(1/3)		
	    	commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTempST", dto);
	    	int chunkSize = 100;
	    	List<CmSyProcessTempStEntity> insertList = new ArrayList<>();
	    	for (int i = 0; i < saveList.size(); i++) {
	    		OmOrderCreationSTOOrdBaseEntity entity = saveList.get(i);
	    		// 임시테이블에 등록(2/3)
	    		CmSyProcessTempStEntity tempEntity = ModelMapperUtil.map(entity, userContext, CmSyProcessTempStEntity.class);
	    		tempEntity.setProcesstype(dto.getProcesstype()); // 프로세스타입
	    		tempEntity.setProcesscreator(dto.getGUserId());
	    		tempEntity.setSpid(dto.getGSpid());
	    		tempEntity.setFromArea(entity.getFromArea());
	    		tempEntity.setFromDccode(entity.getFromDccode());
	    		tempEntity.setFromOrganize(entity.getFromOrganize());
	    		tempEntity.setFromSku(entity.getFromSku());
	    		tempEntity.setFromStockgrade(entity.getFromStockgrade());
	    		tempEntity.setFromStorerkey(entity.getFromStorerkey());
	    		tempEntity.setFromUom(entity.getFromUom());
	    		tempEntity.setToArea(entity.getToArea());
	    		tempEntity.setToDccode(entity.getToDccode());
	    		tempEntity.setToOrderqty(entity.getToOrderqty());
	    		tempEntity.setToOrganize(entity.getToOrganize());
	    		tempEntity.setToSku(entity.getToSku());
	    		tempEntity.setToStockgrade(entity.getToStockgrade());
	    		tempEntity.setToStorerkey(entity.getToStorerkey());
	    		tempEntity.setToUom(entity.getToUom());
	    		
	    		// 메모에 docnoList 추가
	    		tempEntity.setMemo1(entity.getDocnoList());
	    		// 우선순위 추가
	    		tempEntity.setWorkNo(entity.getWorkNo());
	    		
	    		// 병렬처리를 위한 flag세팅
	    		tempEntity.setProcessflag("S");
	    		
	    		insertList.add(tempEntity);
	    		// 100개마다 혹은 마지막 루프일 때 insert(3/3)
	    		if (insertList.size() == chunkSize || i == saveList.size() -1) {
	    			commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "insertSyProcessTempSt", insertList);
	    			insertList.clear();
	    		}
	    	}
	    	// 동기화 로그 등록을 블록 맨 마지막에 위치
	    	// [수정된 코드] 인터페이스를 직접 구현하되 필요한 메소드만 오버라이드
	    	if (TransactionSynchronizationManager.isSynchronizationActive()) {
	    	    TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
	    	        @Override
	    	        public void afterCommit() {
	    	            // 물리 커밋 완료 후 실행될 로직
	    	            log.info(">>>> [SUCCESS] DB 물리 커밋 완료! 이제 비동기 스레드가 데이터를 읽을 수 있습니다.");
	    	        }
	    	    });
	    	}
	        return null;
	    });
		
		//commonDao.commit();
		
		/*END.Temp Table Insert*/
		// PKG 파라마터 세팅 - 공통(1/4)
		ProcedureParametersFactory.initParamDto(dto, dto, PAKAGE_NAME);
		
		// PKG 파라마터 세팅 및 실행 - 업무(2/4)
		String[] keyList = {"PROCEDURE","PROCESSTYPE","PROCESSCREATOR","SPID","DELIVERYDATE","CONVERTTYPE","STOTYPE","STO_STORAGETYPE"};
		Object[] valueList = {PAKAGE_NAME,dto.getProcesstype(), dto.getGUserId(), dto.getGSpid(), dto.getDeliverydate(),"", dto.getStotype() ,dto.getStoStoragetype()};
		dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));		
		dto.setAvc_EXECUTEMODE("NOCOMMIT");
		dto.setAvc_ORGANIZE("%");
		dto.setAvc_AREA("%");
		dto.setAvc_REQUESTCODE("NODATA");
		
		// 속도이슈 프로시저 병렬호출		
		// 1. 각각 별도의 메모리 공간을 가진 객체로 생성
		OmOrderCreationSTOOrdBaseReqDto param1 = new OmOrderCreationSTOOrdBaseReqDto();
		OmOrderCreationSTOOrdBaseReqDto param2 = new OmOrderCreationSTOOrdBaseReqDto();
		OmOrderCreationSTOOrdBaseReqDto param3 = new OmOrderCreationSTOOrdBaseReqDto();
		OmOrderCreationSTOOrdBaseReqDto param4 = new OmOrderCreationSTOOrdBaseReqDto();

		// 2. 원본 dto의 내용을 각각의 복사본에 채워넣습니다.
		BeanUtils.copyProperties(dto, param1);
		BeanUtils.copyProperties(dto, param2);
		BeanUtils.copyProperties(dto, param3);
		BeanUtils.copyProperties(dto, param4);
		
		// 3. 비동기 작업 정의
        CompletableFuture<Void> task1 = CompletableFuture.runAsync(() -> {
            // 기존 commonDao.exec 형식을 그대로 사용하되 파라미터만 분리
        	param1.setAi_HASHID("0");
        	if("OSTO".equals(param1.getStoStoragetype())) {
        		commonDao.update(SERVICEID_PREFIX+"updateSyProcessTempSt", param1);       		
        	}
            commonDao.exec(SERVICEID_PREFIX+"callProcedure", param1);
        }, executor);

        CompletableFuture<Void> task2 = CompletableFuture.runAsync(() -> {
        	
        	param2.setAi_HASHID("1");
        	if("OSTO".equals(param2.getStoStoragetype())) {
        		commonDao.update(SERVICEID_PREFIX+"updateSyProcessTempSt", param2);       		
        	}
        	commonDao.exec(SERVICEID_PREFIX+"callProcedure", param2);
        }, executor);

        CompletableFuture<Void> task3 = CompletableFuture.runAsync(() -> {
        	param3.setAi_HASHID("2");
        	if("OSTO".equals(param3.getStoStoragetype())) {
        		commonDao.update(SERVICEID_PREFIX+"updateSyProcessTempSt", param3);       		
        	}
        	commonDao.exec(SERVICEID_PREFIX+"callProcedure", param3);
        }, executor);
        
        CompletableFuture<Void> task4 = CompletableFuture.runAsync(() -> {
        	param4.setAi_HASHID("3");
        	if("OSTO".equals(param4.getStoStoragetype())) {
        		commonDao.update(SERVICEID_PREFIX+"updateSyProcessTempSt", param4);       		
        	}
        	commonDao.exec(SERVICEID_PREFIX+"callProcedure", param4);
        }, executor);

        // 4. 모든 작업이 끝날 때까지 기다림
        CompletableFuture<Void> allTasks = CompletableFuture.allOf(task1, task2, task3, task4);
        // 5. 최종 완료 후 결과 세팅
        allTasks.thenRun(() -> {
            // 모든 프로시저가 끝난 후 실행할 로직
            log.info("모든 프로시저 실행 완료");
        }).join(); // 메인 스레드에서 대기가 필요하다면 join() 사용
        //commonDao.exec(CommonConstants.COMMON_CALLPROCEDURE, dto);
		
		// 5. 각 파라미터 보따리에서 결과 꺼내서 확인하기
		// 보통 프로시저 3개 중 하나라도 에러가 나면 실패로 간주하는 경우가 많으므로 체크가 필요합니다.
		String resCode1 = param1.getResultCode();
		String resCode2 = param2.getResultCode();
		String resCode3 = param3.getResultCode();
		String resCode4 = param4.getResultCode();
		// 6. 원본 dto에 대표 결과값 세팅		
		if(!"0".equals(resCode1) || !"0".equals(resCode2) || !"0".equals(resCode3)|| !"0".equals(resCode4)) {
			dto.setResultCode("E");
		    dto.setResultMessage("일부 프로시저 실패: " + resCode1 + "/" + resCode2 + "/" + resCode3+ "/" + resCode4);
		}else {
			dto.setResultCode("S");
		}
     
		// 프로시저 OUT Parameter(3/4)
		String resultCode    = (String)dto.getResultCode();
		String resultMessage = (String)dto.getResultMessage();
		
		// 프로시저 Exception 처리(4/4)
		if (resultCode.equals("E")) {
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"당일광역보충발주"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}
		
		// interface Call
		OmOrderCreationSTOOrdBaseResDto callDto = commonDao.selectOne(SERVICEID_PREFIX + "getCallYn", dto);
		String callYn = callDto.getCallYn();
		
		if("FW00".equals(dto.getGStorerkey()) && "Y".equals(callYn)) {
			
			List<OmPurchaseOutSTOResDto> headerList = commonDao.selectList(SERVICEID_PO_PREFIX+ "selectScm0160Header", dto);
			
			if (!ObjectUtils.isEmpty(headerList) && headerList.size() > 0) {
				// 날짜 포멧팅
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmSS");
                
                SI_SCM0160_SCM_SOProxy proxy = new SI_SCM0160_SCM_SOProxy();
                DT_SCM0160_SCM mmData = null;
                DT_SCM0160_SCMIF_DM_DOCUMENT_H header = null;
                
                for (OmPurchaseOutSTOResDto headerDto : headerList) {
                    OmPurchaseOutSTOReqDto stoReqDto = new OmPurchaseOutSTOReqDto();
                    stoReqDto.setSearchIfFlag("N");
                    stoReqDto.setUpdateIfFlag("P");
                    stoReqDto.setDocno(headerDto.getDocno());

                    commonDao.update(SERVICEID_PO_PREFIX + "updateScm0160Header", stoReqDto);
                    commonDao.update(SERVICEID_PO_PREFIX + "updateScm0160Detail", stoReqDto);
                    
                    mmData = new DT_SCM0160_SCM();
                    mmData.setXSYS("SCM");
                    mmData.setXDATS(dateFormat.format(calendar.getTime()));
                    mmData.setXTIMS(timeFormat.format(calendar.getTime()));
                    mmData.setXROWS("1");
                        
                    header = new DT_SCM0160_SCMIF_DM_DOCUMENT_H();

                    header.setORDERTYPE(headerDto.getOrdertype());
                    header.setDCCODE(headerDto.getDccode());
                    header.setDOCNO(headerDto.getDocno());
                    header.setFROM_BILLTOKEY("FW00");
                    header.setSTORERKEY(headerDto.getStorerkey());
                    header.setSHOPPINGMALL("Z01");
                    
                    // 실제로 실적전송
                    mmData.setIF_DM_DOCUMENT_H(header);

                    List<OmPurchaseOutSTOResDto> detailDoc = commonDao.selectList(SERVICEID_PO_PREFIX + "selectScm0160Detail", stoReqDto);
                    
                    if (!ObjectUtils.isEmpty(detailDoc) && detailDoc.size() > 0) {
                        int detailCnt = detailDoc.size();
                            
                        // 실적 Detail정보 설정
                        DT_SCM0160_SCMIF_DM_DOCUMENT_D[] detailList = new DT_SCM0160_SCMIF_DM_DOCUMENT_D[detailCnt];
                        DT_SCM0160_SCMIF_DM_DOCUMENT_D detail  = null;
                            
                        for(int j = 0; j < detailCnt; j++) {
                            detail = new DT_SCM0160_SCMIF_DM_DOCUMENT_D();
                            
                            detail.setDOCNO(detailDoc.get(j).getDocno());
                            detail.setDOCLINE(detailDoc.get(j).getDocline());
                            detail.setDEL_YN(detailDoc.get(j).getDelYn());
                            detail.setPLANT(detailDoc.get(j).getPlant());
                            detail.setSTORAGELOC(detailDoc.get(j).getStorageloc());
                            detail.setSKU(detailDoc.get(j).getSku());
                            detail.setSTORERORDERQTY(detailDoc.get(j).getStorerorderqty());
                            detail.setSTORERUOM(detailDoc.get(j).getStoreruom());
                            detail.setDELIVERYDATE(detailDoc.get(j).getDeliverydate());

                            detailList[j] = detail;
                        }    
                        
                        mmData.setIF_DM_DOCUMENT_D(detailList);

                        DT_SCM0160_SCM_responseT_RETURN[] response = null;
                        response = proxy.si_scm0160_scm_so(mmData);

                        // 응답에 대한 결과 처리
                        if (response != null && response.length > 0) {
                            if ("E".equals(response[0].getXSTAT())) {
                                throw new UserHandleException("인터페이스 처리중 에러발생["+response[0].getXMSGS()+"]");
                            }
                        } else {
                            throw new UserHandleException("인터페이스 처리중 에러발생[실적자료 전송중 오류발생]");
                        }
                    } else {
                        throw new UserHandleException("인터페이스 처리중 에러발생[STO예정 문서 Detail 정보를 찾을 수 없습니다.]");
                    }
                    
                    OmPurchaseOutSTOReqDto stoIFDto = new OmPurchaseOutSTOReqDto();
                    
                    stoIFDto.setSearchIfFlag("P");
                    stoIFDto.setUpdateIfFlag("Y");
                    stoIFDto.setUpdateIfMemo("");
                    stoIFDto.setDocno(headerDto.getDocno());
                    
                    // 처리결과 업데이트
                    commonDao.update(SERVICEID_PO_PREFIX + "updateScm0160Header", stoIFDto);
                    commonDao.update(SERVICEID_PO_PREFIX + "updateScm0160Detail", stoIFDto);
                }     
			}
		}
		/* 처리결과 조회*/
		return commonDao.selectList(SERVICEID_PREFIX + "getDataResultlist", dto);
	}
	
	/**
	 * @throws RemoteException 
	 * @description :당일광역보충발주 저장
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.10.31 JeongHyeongCheol (wjdgudcjf@cj.net) 생성 </pre>
	 */
	public List<OmOrderCreationSTOOrdBaseResultResDto> saveMasterList(OmOrderCreationSTOOrdBaseReqDto dto) throws RemoteException {
				
		// 임시테이블 삭제(1/3)
		commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "deleteSyProcessTempST", dto);

		List<OmOrderCreationSTOOrdBaseEntity> saveList = dto.getSaveList(); // 저장리스트
		

		int chunkSize = 100;
		List<CmSyProcessTempStEntity> insertList = new ArrayList<>();
		for (int i = 0; i < saveList.size(); i++) {
			OmOrderCreationSTOOrdBaseEntity entity = saveList.get(i);
			// 임시테이블에 등록(2/3)
			CmSyProcessTempStEntity tempEntity = ModelMapperUtil.map(entity, userContext, CmSyProcessTempStEntity.class);
			tempEntity.setProcesstype(dto.getProcesstype()); // 프로세스타입
			tempEntity.setProcesscreator(dto.getGUserId());
			tempEntity.setProcessflag("S");
			tempEntity.setSpid(dto.getGSpid());
			tempEntity.setFromArea(entity.getFromArea());
			tempEntity.setFromDccode(entity.getFromDccode());
			tempEntity.setFromOrganize(entity.getFromOrganize());
			tempEntity.setFromSku(entity.getFromSku());
			tempEntity.setFromStockgrade(entity.getFromStockgrade());
			tempEntity.setFromStorerkey(entity.getFromStorerkey());
			tempEntity.setFromUom(entity.getFromUom());
			tempEntity.setToArea(entity.getToArea());
			tempEntity.setToDccode(entity.getToDccode());
            tempEntity.setToOrderqty(entity.getToOrderqty());
            tempEntity.setToOrganize(entity.getToOrganize());
            tempEntity.setToSku(entity.getToSku());
            tempEntity.setToStockgrade(entity.getToStockgrade());
            tempEntity.setToStorerkey(entity.getToStorerkey());
            tempEntity.setToUom(entity.getToUom());
            
            // 메모에 docnoList 추가
            tempEntity.setMemo1(entity.getDocnoList());
            // 우선순위 추가
            tempEntity.setWorkNo(entity.getWorkNo());
            
			insertList.add(tempEntity);
			// 100개마다 혹은 마지막 루프일 때 insert(3/3)
			if (insertList.size() == chunkSize || i == saveList.size() -1) {
				commonDao.insert(CommonConstants.COMMON_TEMP_PREFIX + "insertSyProcessTempSt", insertList);
				insertList.clear();
			}
		}
		/*END.Temp Table Insert*/
		// PKG 파라마터 세팅 - 공통(1/4)
		ProcedureParametersFactory.initParamDto(dto, dto, PAKAGE_NAME);
		
		// PKG 파라마터 세팅 및 실행 - 업무(2/4)
		String[] keyList = {"PROCEDURE","PROCESSTYPE","PROCESSCREATOR","SPID","DELIVERYDATE","CONVERTTYPE","STOTYPE","STO_STORAGETYPE"};
		Object[] valueList = {PAKAGE_NAME,dto.getProcesstype(), dto.getGUserId(), dto.getGSpid(), dto.getDeliverydate(),"", dto.getStotype() ,dto.getStoStoragetype()};
		dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
		dto.setAvc_COMMAND("CONFIRM_AUTO");
		dto.setAvc_EXECUTEMODE("NOCOMMIT");
		dto.setAvc_ORGANIZE("%");
		dto.setAvc_AREA("%");
		dto.setAvc_REQUESTCODE("NODATA");
		dto.setAi_HASHID("0");
		commonDao.exec(SERVICEID_PREFIX+"callProcedure", dto);
		
		// 프로시저 OUT Parameter(3/4)
		String resultCode    = (String)dto.getResultCode();
		String resultMessage = (String)dto.getResultMessage();
		
		// 프로시저 Exception 처리(4/4)
		if (!resultCode.equals("0")) {
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"당일광역보충발주"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}
		
		// interface Call
		OmOrderCreationSTOOrdBaseResDto callDto = commonDao.selectOne(SERVICEID_PREFIX + "getCallYn", dto);
		String callYn = callDto.getCallYn();
		
		if("FW00".equals(dto.getGStorerkey()) && "Y".equals(callYn)) {
			
			List<OmPurchaseOutSTOResDto> headerList = commonDao.selectList(SERVICEID_PO_PREFIX+ "selectScm0160Header", dto);
			
			if (!ObjectUtils.isEmpty(headerList) && headerList.size() > 0) {
				// 날짜 포멧팅
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmSS");
                
                SI_SCM0160_SCM_SOProxy proxy = new SI_SCM0160_SCM_SOProxy();
                DT_SCM0160_SCM mmData = null;
                DT_SCM0160_SCMIF_DM_DOCUMENT_H header = null;
                
                for (OmPurchaseOutSTOResDto headerDto : headerList) {
                    OmPurchaseOutSTOReqDto stoReqDto = new OmPurchaseOutSTOReqDto();
                    stoReqDto.setSearchIfFlag("N");
                    stoReqDto.setUpdateIfFlag("P");
                    stoReqDto.setDocno(headerDto.getDocno());

                    commonDao.update(SERVICEID_PO_PREFIX + "updateScm0160Header", stoReqDto);
                    commonDao.update(SERVICEID_PO_PREFIX + "updateScm0160Detail", stoReqDto);
                    
                    mmData = new DT_SCM0160_SCM();
                    mmData.setXSYS("SCM");
                    mmData.setXDATS(dateFormat.format(calendar.getTime()));
                    mmData.setXTIMS(timeFormat.format(calendar.getTime()));
                    mmData.setXROWS("1");
                        
                    header = new DT_SCM0160_SCMIF_DM_DOCUMENT_H();

                    header.setORDERTYPE(headerDto.getOrdertype());
                    header.setDCCODE(headerDto.getDccode());
                    header.setDOCNO(headerDto.getDocno());
                    header.setFROM_BILLTOKEY("FW00");
                    header.setSTORERKEY(headerDto.getStorerkey());
                    header.setSHOPPINGMALL("Z01");
                    
                    // 실제로 실적전송
                    mmData.setIF_DM_DOCUMENT_H(header);

                    List<OmPurchaseOutSTOResDto> detailDoc = commonDao.selectList(SERVICEID_PO_PREFIX + "selectScm0160Detail", stoReqDto);
                    
                    if (!ObjectUtils.isEmpty(detailDoc) && detailDoc.size() > 0) {
                        int detailCnt = detailDoc.size();
                            
                        // 실적 Detail정보 설정
                        DT_SCM0160_SCMIF_DM_DOCUMENT_D[] detailList = new DT_SCM0160_SCMIF_DM_DOCUMENT_D[detailCnt];
                        DT_SCM0160_SCMIF_DM_DOCUMENT_D detail  = null;
                            
                        for(int j = 0; j < detailCnt; j++) {
                            detail = new DT_SCM0160_SCMIF_DM_DOCUMENT_D();
                            
                            detail.setDOCNO(detailDoc.get(j).getDocno());
                            detail.setDOCLINE(detailDoc.get(j).getDocline());
                            detail.setDEL_YN(detailDoc.get(j).getDelYn());
                            detail.setPLANT(detailDoc.get(j).getPlant());
                            detail.setSTORAGELOC(detailDoc.get(j).getStorageloc());
                            detail.setSKU(detailDoc.get(j).getSku());
                            detail.setSTORERORDERQTY(detailDoc.get(j).getStorerorderqty());
                            detail.setSTORERUOM(detailDoc.get(j).getStoreruom());
                            detail.setDELIVERYDATE(detailDoc.get(j).getDeliverydate());

                            detailList[j] = detail;
                        }    
                        
                        mmData.setIF_DM_DOCUMENT_D(detailList);

                        DT_SCM0160_SCM_responseT_RETURN[] response = null;
                        response = proxy.si_scm0160_scm_so(mmData);

                        // 응답에 대한 결과 처리
                        if (response != null && response.length > 0) {
                            if ("E".equals(response[0].getXSTAT())) {
                                throw new UserHandleException("인터페이스 처리중 에러발생["+response[0].getXMSGS()+"]");
                            }
                        } else {
                            throw new UserHandleException("인터페이스 처리중 에러발생[실적자료 전송중 오류발생]");
                        }
                    } else {
                        throw new UserHandleException("인터페이스 처리중 에러발생[STO예정 문서 Detail 정보를 찾을 수 없습니다.]");
                    }
                    
                    OmPurchaseOutSTOReqDto stoIFDto = new OmPurchaseOutSTOReqDto();
                    
                    stoIFDto.setSearchIfFlag("P");
                    stoIFDto.setUpdateIfFlag("Y");
                    stoIFDto.setUpdateIfMemo("");
                    stoIFDto.setDocno(headerDto.getDocno());
                    
                    // 처리결과 업데이트
                    commonDao.update(SERVICEID_PO_PREFIX + "updateScm0160Header", stoIFDto);
                    commonDao.update(SERVICEID_PO_PREFIX + "updateScm0160Detail", stoIFDto);
                }     
			}
		}
		/* 처리결과 조회*/
		return commonDao.selectList(SERVICEID_PREFIX + "getDataResultlist", dto);
	}
		
	public void saveSTOAllocationBatch(OmOrderCreationSTOOrdBaseReqDto dto) throws RemoteException 
	{
		// 프로시저 에러코드 및 메세지
		String resultCode = "";
		String resultMessage  = "";
		
		// 파라미터 위변조 적용(paramDto->reqDto)
		OmOrderCreationSTOOrdBaseReqDto reqDto = ModelMapperUtil.map(dto, OmOrderCreationSTOOrdBaseReqDto.class);
				
		ProcedureParametersFactory.initParamDto(reqDto,reqDto, PAKAGE_NAME_STO);
		
		
		// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
		String[] keyList   = {"PROCEDURE" 		,"ALLOCTYPE"  ,"DCCODE"    	 		,"STORERKEY"	     	,"SLIPDT"		 		};
		Object[] valueList = {PAKAGE_NAME_STO 	,"BATCHGROUP" ,reqDto.getDccode() 	,reqDto.getGStorerkey() ,reqDto.getDeliverydate()  };
		reqDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));	
		reqDto.setAvc_DCCODE(dto.getDccode());
		reqDto.setAvc_COMMAND("CONFIRM_BATCHGROUP_STO2");
		reqDto.setAvc_EXECUTEMODE(null);
		reqDto.setAvc_REQUESTCODE(null);
		reqDto.setResultCode(null);
		int rv = cmCommonService.saveProcedure(reqDto); 
		log.info("rv->{}",rv);
		
		// 프로시저 OUT Parameter(3/4)
		resultCode    = StringUtil.nvl(reqDto.getResultCode());
		resultMessage = StringUtil.nvl((String)reqDto.getResultMessage());
		log.info("resultCode->{}",resultCode);
		log.info("resultMessage->{}",resultMessage);

		// 프로시저 Exception 처리(4/4)
		if(!"0".equals(resultCode)){
			log.error("▶STO분배 저장시 오류 발생 ");
			throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"STO분배 저장"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
		}  	
	}	
	
	/**
	 * @description : 당일광역보충발주 배치결과 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.06 LeeJeongCheol (progs@cj.net) 생성 </pre>
	 */
	public List<OmOrderCreationSTOOrdBaseResDto> getBatchResultList(OmOrderCreationSTOOrdBaseReqDto dto) {
		List<OmOrderCreationSTOOrdBaseResDto> result = commonDao.selectList(SERVICEID_PREFIX + "getBatchResultList", dto);
		return result;
	}
	
	/**
	 * @description : 당일광역보충발주 배치결과 헤더 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.12 LeeJeongCheol (progs@cj.net) 생성 </pre>
	 */
	public List<OmOrderCreationSTOBatchResultHeadDto> getBatchResultHeadList(OmOrderCreationSTOOrdBaseReqDto dto) {
		log.info(">>>>>> dto: {}", dto);
		List<OmOrderCreationSTOBatchResultHeadDto> result = commonDao.selectList(SERVICEID_PREFIX + "getBatchResultHeadList", dto);
		return result;
	}
	
	/**
	 * @description : 당일광역보충발주 배치결과 상세 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2026.03.12 LeeJeongCheol (progs@cj.net) 생성 </pre>
	 */
	public List<OmOrderCreationSTOBatchResultDetailDto> getBatchResultDetailList(OmOrderCreationSTOOrdBaseReqDto dto) {
		List<OmOrderCreationSTOBatchResultDetailDto> result = commonDao.selectList(SERVICEID_PREFIX + "getBatchResultDetailList", dto);
		return result;
	}
	
}
