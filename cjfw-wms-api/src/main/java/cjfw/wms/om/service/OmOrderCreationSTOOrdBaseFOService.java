package cjfw.wms.om.service;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronization;
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
import cjfw.wms.dp.controller.DpInspectController;
import cjfw.wms.om.dto.OmOrderCreationSTOOrdBaseFODcDto;
import cjfw.wms.om.dto.OmOrderCreationSTOOrdBaseFOReqDto;
import cjfw.wms.om.dto.OmOrderCreationSTOOrdBaseFOResDto;
import cjfw.wms.om.dto.OmOrderCreationSTOOrdBaseFOResultDto;
import cjfw.wms.om.dto.OmOrderCreationSTOOrdBaseFOStoResDto;
import cjfw.wms.om.dto.OmPurchaseOutSTOReqDto;
import cjfw.wms.om.dto.OmPurchaseOutSTOResDto;
import cjfw.wms.webservice.mmStoIf.DT_SCM0160_SCM;
import cjfw.wms.webservice.mmStoIf.DT_SCM0160_SCMIF_DM_DOCUMENT_D;
import cjfw.wms.webservice.mmStoIf.DT_SCM0160_SCMIF_DM_DOCUMENT_H;
import cjfw.wms.webservice.mmStoIf.DT_SCM0160_SCM_responseT_RETURN;
import cjfw.wms.webservice.mmStoIf.SI_SCM0160_SCM_SOProxy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * 
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2026.03.10 
 * @description : 당일광역보충발주(FO) Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2026.03.10    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OmOrderCreationSTOOrdBaseFOService {

    private final DpInspectController dpInspectController;
    
    /**
     * Use this prefix to explicitly indicate a workspace name with a query id.
     * @return .sqlx returns the location
     */
    private transient static final String SERVICEID_PREFIX = "omOrderCreationSTOOrdBaseFOService.";
    private transient static final String SERVICEID_PO_PREFIX = "omPurchaseOutSTOServiceService."; 
    private transient static final String SERVICEID_TEMP_PREFIX = "cmTempTableService.";
    private transient static final String PAKAGE_NAME = "SPOM_ORDERCREATIONSTO_ORDBASE";
    private transient static final String PAKAGE_NAME_STO = "SPWD_ALLOCATION";
    private transient static final String PROCESSTYPE = "SPOM_ORDERCREATIONSTO_OB_FO";
    private transient static final String TEMPTABLETYPE = "ST";
    private transient static final String STOTYPE = "STO";
    private transient static final String STO_STORAGETYPE = "OSTO";

    private final CommonDao commonDao;
    private final CmCommonService cmCommonService;
    private final UserContext userContext;
    
    @Autowired
    private PlatformTransactionManager transactionManager;
    
    // 공통으로 사용할 스레드 풀 정의 (매번 생성하지 말고 빈이나 상수로 관리하는 게 좋습니다)
    private final Executor executor = Executors.newFixedThreadPool(3);

    /**
     * @description : 당일광역보충발주(FO) 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE          AUTHOR                  MAJOR_ISSUE
     * ----------------------------------------------------------- 
     * 2026.03.10    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<OmOrderCreationSTOOrdBaseFOResDto> getMasterList(OmOrderCreationSTOOrdBaseFOReqDto reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getDataHeaderList", reqDto);
    }

    /**
     * @description : 처리 결과 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE          AUTHOR                  MAJOR_ISSUE
     * ----------------------------------------------------------- 
     * 2026.03.10    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<OmOrderCreationSTOOrdBaseFOResultDto> getResultList(OmOrderCreationSTOOrdBaseFOReqDto reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getDataResultList", reqDto);
    }
    
    /**
     * @description : 이동대상 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE          AUTHOR                  MAJOR_ISSUE
     * ----------------------------------------------------------- 
     * 2026.03.10    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<OmOrderCreationSTOOrdBaseFOStoResDto> getDataHeaderStoList(OmOrderCreationSTOOrdBaseFOReqDto reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getDataHeaderStoList", reqDto);
    }
    
    /**
     * @description : 당일광역보충발주(FO) 물류센터 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE          AUTHOR                  MAJOR_ISSUE
     * ----------------------------------------------------------- 
     * 2026.03.10    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<OmOrderCreationSTOOrdBaseFODcDto> getDcname(OmOrderCreationSTOOrdBaseFOReqDto reqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getDcname", reqDto);
    }
    
    /**
     * @throws RemoteException 
     * @description : 당일광역보충발주(FO)  저장
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2026.03.10    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<OmOrderCreationSTOOrdBaseFOResultDto> saveMasterList(OmOrderCreationSTOOrdBaseFOReqDto paramDto) throws RemoteException {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        
        // 파라미터 위변조 적용(reqDto->paramDto)
        OmOrderCreationSTOOrdBaseFOReqDto reqDto = ModelMapperUtil.map(paramDto, OmOrderCreationSTOOrdBaseFOReqDto.class);
        
        List<OmOrderCreationSTOOrdBaseFOResDto> saveList = reqDto.getSaveList(); // 저장리스트
        List<OmOrderCreationSTOOrdBaseFOStoResDto> returnList = new ArrayList<OmOrderCreationSTOOrdBaseFOStoResDto>();  // ???
        Map<String,BigDecimal> skuQtyMap = new HashMap<String, BigDecimal>();   //???
        String avc_DCCODE = reqDto.getAvc_DCCODE(); // 센터코드
        
        log.info("▶saveList.size->{}",saveList);
        log.info("▶avc_DCCODE->{}",avc_DCCODE);
        
        if (null != saveList) {
            //*1. 독립적인 트랜잭션 템플릿 생성 
            TransactionTemplate newTemplate = new TransactionTemplate(transactionManager);
            newTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
            
            /*2. 임시 테이블 삭제 및 대상 데이터 저장*/
            newTemplate.execute(status -> {
                // 임시테이블 삭제
                CmSyProcessTempStEntity tempDeleteReqEntity = ModelMapperUtil.map(reqDto, userContext, CmSyProcessTempStEntity.class);
                tempDeleteReqEntity.setProcesstype(PROCESSTYPE);
                tempDeleteReqEntity.setSpid(reqDto.getGSpid());

                commonDao.delete(SERVICEID_TEMP_PREFIX +"deleteSyProcessTemp" + TEMPTABLETYPE, tempDeleteReqEntity);
                
                // 임시테이블에 저장 200개씩
                int chunkSize = 200;
                List<CmSyProcessTempStEntity> insertList = new ArrayList<CmSyProcessTempStEntity>();
                
                BigDecimal ordCnt = new BigDecimal("0");

                for (int i = 0; i < saveList.size(); i++) {
                    OmOrderCreationSTOOrdBaseFOResDto dto = saveList.get(i);
                    CmSyProcessTempStEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempStEntity.class);
                    
                    /*
                     * 이 부분에 대한 설명은 ASIS 개발자가 작성한 설명을 옮겨놓으려고 한다.
                     */
                    
                    entity.setProcesstype(PROCESSTYPE);
                    entity.setTemptabletype(TEMPTABLETYPE);
                    entity.setFromStorerkey(dto.getGStorerkey());
                    entity.setFromDccode(dto.getFromDccode());
                    entity.setFromOrganize(dto.getFromOrganize());
                    entity.setFromArea(dto.getFromArea());
                    entity.setFromSku(dto.getFromSku());
                    entity.setFromUom(dto.getFromUom());
                    entity.setFromStockgrade(dto.getFromStockgrade());
                    entity.setToStorerkey(dto.getGStorerkey());
                    entity.setToDccode(dto.getToDccode());
                    entity.setToOrganize(dto.getToOrganize());
                    entity.setToArea(dto.getToArea());
                    entity.setToSku(dto.getToSku());
                    entity.setToUom(dto.getToUom());
                    entity.setToStockgrade(dto.getToStockgrade());
                    entity.setToOrderqty(dto.getToOrderqty());
                    entity.setMemo1(String.valueOf(dto.getOpenqty1()));
                    entity.setMemo1(dto.getDocnoList());
                    entity.setCrossdocktype(dto.getFromCrossdocktype());
                    // 병렬처리를 위한 flag세팅
                    entity.setProcessflag("S");
                    
                    // 발주 수량 
                    BigDecimal bfOrdCnt = dto.getOpenqty1();
                    ordCnt = new BigDecimal("0");
                    BigDecimal returnOrdCnt = new BigDecimal("0");
                    dto.setSku(dto.getToSku());
                    dto.setDeliverydate(reqDto.getDeliverydate());
                    dto.setToDccode(reqDto.getToDccode());
                    
                    List<OmOrderCreationSTOOrdBaseFOStoResDto> soList = commonDao.selectList(SERVICEID_PREFIX + "getDataOrderDetail", dto);

                    if (!ObjectUtils.isEmpty(soList) && soList.size() > 0) {
                        for (int j = 0; j < soList.size(); j++) {
                            ordCnt = ordCnt.add(soList.get(j).getOrderqty());
                            if (bfOrdCnt.compareTo(ordCnt) > 0 || "CROSS".equals(dto.getFromCrossdocktype())) {
                                returnList.add(soList.get(j));
                                returnOrdCnt = ordCnt;
                            } else { 
                                continue;
                            }
                        }
                    }
                    
                    if (returnOrdCnt.compareTo(BigDecimal.ZERO) > 0) {
                        //cnt++;
                        
                        entity.setToOrderqty(returnOrdCnt);
                        skuQtyMap.put(dto.getToSku(), returnOrdCnt);
                    }
                    
                    insertList.add(entity);
              
                    // 200개마다 혹은 마지막 루프일 때 insert
                    if (insertList.size() == chunkSize || i == saveList.size() -1) {
                        commonDao.insert(SERVICEID_PREFIX + "insertSyProcessTemp" + TEMPTABLETYPE, insertList);
                        insertList.clear(); // 다음 배치 준비
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
            
            /*3. 패키지 프로시저 실행*/
            OmOrderCreationSTOOrdBaseFOReqDto procedureDto = new OmOrderCreationSTOOrdBaseFOReqDto();
           
            ProcedureParametersFactory.initParamDto(reqDto, procedureDto, PAKAGE_NAME);
            procedureDto.setAvc_DCCODE(reqDto.getToDccode());
            procedureDto.setAvc_EXECUTEMODE("NOCOMMIT");
            procedureDto.setProcesstype(PROCESSTYPE);
            
            // 3-1. PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>                
            String[] keyList = {
                                 "PROCEDURE"
                                ,"PROCESSTYPE"
                                ,"PROCESSCREATOR"
                                ,"SPID"
                                ,"DELIVERYDATE"
                                ,"CONVERTTYPE"
                                ,"STOTYPE"
                                ,"STO_STORAGETYPE"
                                };
            Object[] valueList = { 
                                  PAKAGE_NAME   
                                , PROCESSTYPE
                                , reqDto.getGUserId()
                                , reqDto.getGSpid()
                                , reqDto.getDeliverydate()
                                , ""
                                , STOTYPE
                                , STO_STORAGETYPE
                                };
            procedureDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
            procedureDto.setAvc_EXECUTEMODE("NOCOMMIT");
            
            // 3-2. 각각 별도의 메모리 공간을 가진 객체로 생성
            OmOrderCreationSTOOrdBaseFOReqDto param1 = new OmOrderCreationSTOOrdBaseFOReqDto();
            OmOrderCreationSTOOrdBaseFOReqDto param2 = new OmOrderCreationSTOOrdBaseFOReqDto();
            OmOrderCreationSTOOrdBaseFOReqDto param3 = new OmOrderCreationSTOOrdBaseFOReqDto();
            OmOrderCreationSTOOrdBaseFOReqDto param4 = new OmOrderCreationSTOOrdBaseFOReqDto();

            // 3-3. 원본 dto의 내용을 각각의 복사본에 채워넣습니다.
            BeanUtils.copyProperties(procedureDto, param1);
            BeanUtils.copyProperties(procedureDto, param2);
            BeanUtils.copyProperties(procedureDto, param3);
            BeanUtils.copyProperties(procedureDto, param4);
            
            // 3-4. 비동기 작업 정의
            CompletableFuture<Void> task1 = CompletableFuture.runAsync(() -> {
                // 기존 commonDao.exec 형식을 그대로 사용하되 파라미터만 분리
                param1.setAi_HASHID("0");
                //commonDao.update(SERVICEID_PREFIX+"updateSyProcessTempSt", param1);             
                commonDao.exec(SERVICEID_PREFIX+"callProcedure", param1);
            }, executor);

            CompletableFuture<Void> task2 = CompletableFuture.runAsync(() -> {
                
                param2.setAi_HASHID("1");
                //commonDao.update(SERVICEID_PREFIX+"updateSyProcessTempSt", param2);             
                commonDao.exec(SERVICEID_PREFIX+"callProcedure", param2);
            }, executor);

            CompletableFuture<Void> task3 = CompletableFuture.runAsync(() -> {
                param3.setAi_HASHID("2");
                //commonDao.update(SERVICEID_PREFIX+"updateSyProcessTempSt", param3);             
                commonDao.exec(SERVICEID_PREFIX+"callProcedure", param3);
            }, executor);
            
            CompletableFuture<Void> task4 = CompletableFuture.runAsync(() -> {
                param4.setAi_HASHID("3");
                //commonDao.update(SERVICEID_PREFIX+"updateSyProcessTempSt", param4);             
                commonDao.exec(SERVICEID_PREFIX+"callProcedure", param4);
            }, executor);

            // 3-5. 모든 작업이 끝날 때까지 기다림
            CompletableFuture<Void> allTasks = CompletableFuture.allOf(task1, task2, task3, task4);
            //      최종 완료 후 결과 세팅
            allTasks.thenRun(() -> {
                // 모든 프로시저가 끝난 후 실행할 로직
                log.info("모든 프로시저 실행 완료");
            }).join(); // 메인 스레드에서 대기가 필요하다면 join() 사용
            
            // 3-6. 각 파라미터 보따리에서 결과 꺼내서 확인하기
            String resCode1 = param1.getResultCode();
            String resCode2 = param2.getResultCode();
            String resCode3 = param3.getResultCode();
            String resCode4 = param4.getResultCode();
            
            // 3-7. 원본 dto에 대표 결과값 세팅     
            if (!"0".equals(resCode1) || !"0".equals(resCode2) || !"0".equals(resCode3) || !"0".equals(resCode4)) {
                procedureDto.setResultCode("E");
                procedureDto.setResultMessage("일부 프로시저 실패: " + resCode1 + "/" + resCode2 + "/" + resCode3+ "/" + resCode4);
            }else {
                procedureDto.setResultCode("S");
            }
            
            // 3-8. 프로시저 OUT Parameter(3/4)
            resultCode    = (String)procedureDto.getResultCode();
            resultMessage = (String)procedureDto.getResultMessage();
            
            // 3-9. 프로시저 Exception 처리(4/4)
            if (resultCode.equals("E")) {
                throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"당일광역보충발주(FO)"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
            }

            /*4. 인터페이스 실행*/  
            String callYn = commonDao.selectOne(SERVICEID_PREFIX + "getCallYn", procedureDto);
            
            if ("FW00".equals(reqDto.getGStorerkey()) && "Y".equals(callYn)) {
            
                List<OmPurchaseOutSTOResDto> headerList = commonDao.selectList(SERVICEID_PO_PREFIX + "selectScm0160Header", reqDto);
            
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
                        mmData.setXSYS("WMS");
                        mmData.setXDATS(dateFormat.format(calendar.getTime()));
                        mmData.setXTIMS(timeFormat.format(calendar.getTime()));
                        mmData.setXROWS("1");
                            
                        header = new DT_SCM0160_SCMIF_DM_DOCUMENT_H();
    
                        header.setORDERTYPE(headerDto.getOrdertype());
                        header.setDCCODE(headerDto.getDccode());
                        header.setDOCNO(headerDto.getDocno());
                        header.setFROM_BILLTOKEY(headerDto.getStorerkey());
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
                                
                            for (int j = 0; j < detailCnt; j++) {
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
                                
                                /*
                                if (returnList.size() > 0) {
                                    for (int k = 0; k < returnList.size(); k++) {
                                        OmOrderCreationSTOOrdBaseFOStoResDto stoResDto = returnList.get(k);
                                        if (stoResDto.getSku().equals(detail.getSKU())) {
                                            OmOrderCreationSTOOrdBaseFOEntity entity = new OmOrderCreationSTOOrdBaseFOEntity();
                                            entity.setStorerkey(reqDto.getGStorerkey());
                                            entity.setDocdt(stoResDto.getDocdt());
                                            entity.setDoctype(stoResDto.getDoctype());
                                            entity.setDocno(stoResDto.getDocno());
                                            entity.setDocline(stoResDto.getDocline());
                                            entity.setDccode(stoResDto.getDccode());
                                            entity.setDeliverydate(stoResDto.getDeliverydate());
                                            entity.setStoDocdt(detailDoc.get(j).getDeliverydate());
                                            entity.setStoDoctype("WD");
                                            entity.setStoDocno(detailDoc.get(j).getDocno());
                                            entity.setStoDocline(detailDoc.get(j).getDocline());
                                            entity.setStoDccode(headerDto.getDccode());
                                            entity.setStoDeliverydate(detailDoc.get(j).getDeliverydate());
                                            entity.setSku(stoResDto.getSku());
                                            entity.setOrderqty(stoResDto.getOrderqty());
                                            entity.setStoOrderqty(skuQtyMap.get(stoResDto.getSku()));
                                            entity.setDelYn("N");
                                            
                                            commonDao.insert(SERVICEID_PREFIX + "insertDmDocumentDMap", entity);
                                        }
                                    }
                                }
                                */
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
          
            /*5. 처리결과 조회*/
            reqDto.setProcesstype(PROCESSTYPE);
            return commonDao.selectList(SERVICEID_PREFIX + "getDataResultList", reqDto);
        }
        
        return null;
    
    }
    
    /**
     * @throws RemoteException 
     * @description : 당일광역보충발주(FO) STO 분배 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2026.03.10    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public void saveSTOAllocationBatch(OmOrderCreationSTOOrdBaseFOReqDto dto) throws RemoteException 
    {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        
        // 파라미터 위변조 적용(paramDto->reqDto)
        OmOrderCreationSTOOrdBaseFOReqDto reqDto = ModelMapperUtil.map(dto, OmOrderCreationSTOOrdBaseFOReqDto.class);
                
        ProcedureParametersFactory.initParamDto(reqDto,reqDto, PAKAGE_NAME_STO);
        
        
        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>
        String[] keyList   = {"PROCEDURE"       ,"ALLOCTYPE"  ,"DCCODE"             ,"STORERKEY"            ,"SLIPDT"               };
        Object[] valueList = {PAKAGE_NAME_STO   ,"BATCHGROUP" ,reqDto.getFromDccode()   ,reqDto.getGStorerkey() ,reqDto.getDeliverydate()  };
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
            log.error("▶당일광역보충발주(FO) - STO분배 저장시 오류 발생 ");
            throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"STO분배 저장"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
        }   
    }

}
