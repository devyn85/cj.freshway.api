package cjfw.wms.om.service;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.cm.entity.CmSyProcessTempStEntity;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.comfunc.func.http.feign.client.FeignTestClient;
import cjfw.wms.om.dto.OmOrderCreationSTOForOutReqDto;
import cjfw.wms.om.dto.OmOrderCreationSTOForOutResDto;
import cjfw.wms.om.dto.OmOrderCreationSTOForOutResultResDto;
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
 * @date : 2025.07.08 
 * @description : 외부센터 보충발주 Service
 * @issues :<pre> 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.07.08    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OmOrderCreationSTOForOutService {
	
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "omOrderCreationSTOForOutService.";
	private transient static final String SERVICEID_PO_PREFIX = "omPurchaseOutSTOServiceService.";   
	private transient static final String SERVICEID_TEMP_PREFIX = "cmTempTableService.";	
	private transient static final String PAKAGE_NAME = "SPOM_ORDERCREATIONSTO_FOROUT";	
    private transient static final String PROCESSTYPE = "SPOM_ORDERCREATIONSTO_FO";      
    private transient static final String TEMPTABLETYPE = "ST"; 

	private final CommonDao commonDao;
	private final CmCommonService cmCommonService;
	private final UserContext userContext;

	/**
	 * @description : 외부센터 보충발주 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE          AUTHOR                  MAJOR_ISSUE
	 * ----------------------------------------------------------- 
	 * 2025.07.08    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	public List<OmOrderCreationSTOForOutResDto> getMasterList(OmOrderCreationSTOForOutReqDto omOrderCreationSTOForOutReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDataHeaderlist", omOrderCreationSTOForOutReqDto);
	}
	
	/**
     * @description : 처리 결과 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE          AUTHOR                  MAJOR_ISSUE
     * ----------------------------------------------------------- 
     * 2025.07.08    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<OmOrderCreationSTOForOutResDto> getResultList(OmOrderCreationSTOForOutReqDto omOrderCreationSTOForOutReqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getDataResultlist", omOrderCreationSTOForOutReqDto);
    }
    
    /**
     * @throws RemoteException 
     * @description : 외부센터 보충발주 저장
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.07.08    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<OmOrderCreationSTOForOutResultResDto> saveMasterList(OmOrderCreationSTOForOutReqDto paramDto) throws RemoteException {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        
        // 파라미터 위변조 적용(reqDto->paramDto)
        OmOrderCreationSTOForOutReqDto reqDto = ModelMapperUtil.map(paramDto, OmOrderCreationSTOForOutReqDto.class);
        
        List<OmOrderCreationSTOForOutResDto> saveList = reqDto.getSaveList(); // 저장리스트
        String avc_DCCODE = reqDto.getAvc_DCCODE(); // 센터코드
        
        log.info("▶saveList.size->{}",saveList);
        log.info("▶avc_DCCODE->{}",avc_DCCODE);
        
        if (null != saveList) {
            /*1. 임시 테이블 삭제 및 대상 데이터 저장*/
            // 임시테이블 삭제
            CmSyProcessTempStEntity tempDeleteReqEntity = ModelMapperUtil.map(reqDto, userContext, CmSyProcessTempStEntity.class);
            tempDeleteReqEntity.setProcesstype(PROCESSTYPE);
            tempDeleteReqEntity.setSpid(reqDto.getGSpid());

            commonDao.delete(SERVICEID_TEMP_PREFIX +"deleteSyProcessTemp" + TEMPTABLETYPE, tempDeleteReqEntity);
            
            // 임시테이블에 저장 100개씩
            int chunkSize = 200;
            List<CmSyProcessTempStEntity> insertList = new ArrayList<CmSyProcessTempStEntity>();

            for (int i = 0; i < saveList.size(); i++) {
                OmOrderCreationSTOForOutResDto dto = saveList.get(i);
                CmSyProcessTempStEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempStEntity.class);
                //ProcedureParametersFactory.initParamDto(reqDto, entity, PAKAGE_NAME);
                
                entity.setProcesstype(PROCESSTYPE);
                entity.setTemptabletype(TEMPTABLETYPE);
                entity.setFromStorerkey(dto.getGStorerkey());
                entity.setFromDccode(dto.getFromDccode());
                entity.setFromOrganize(dto.getFromOrganize());
                entity.setFromArea(dto.getFromArea());
                entity.setFromSku(dto.getFromSku());
                entity.setFromUom(dto.getFromUom());
                entity.setFromStockid(dto.getFromStockid());
                entity.setFromStockgrade(dto.getFromStockgrade());
                entity.setFromLoc(dto.getFromLoc());
                entity.setFromLot(dto.getFromLot());
                
                entity.setToOrderqty(dto.getToOrderqty());
                entity.setToStorerkey(dto.getGStorerkey());
                entity.setToDccode(dto.getToDccode());
                entity.setToOrganize(dto.getToOrganize());
                entity.setToArea(dto.getToArea());
                entity.setToSku(dto.getToSku());
                entity.setToUom(dto.getToUom());
                entity.setToStockid(dto.getToStockid());
                entity.setToStockgrade(dto.getToStockgrade());
                entity.setEtcqty2(dto.getEtcqty2());
                entity.setMemo1(dto.getMemo1());  
                insertList.add(entity);
          
                // 200개마다 혹은 마지막 루프일 때 insert
                if (insertList.size() == chunkSize || i == saveList.size() -1) {
                    commonDao.insert(SERVICEID_PREFIX + "insertSyProcessTemp" + TEMPTABLETYPE, insertList);
                    insertList.clear(); // 다음 배치 준비
                }
            }
            
            /*2. 패키지 프로시저 실행*/
            OmOrderCreationSTOForOutReqDto dto = new OmOrderCreationSTOForOutReqDto();
           
            ProcedureParametersFactory.initParamDto(reqDto, dto, PAKAGE_NAME);
            dto.setAvc_DCCODE(reqDto.getFromDccode());
            dto.setAvc_EXECUTEMODE("NOCOMMIT");
            
            // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>                
            String[] keyList = {
                                 "PROCEDURE"
                                ,"PROCESSTYPE"
                                ,"PROCESSCREATOR"
                                ,"SPID"
                                ,"DELIVERYDATE"
                                ,"CONVERTTYPE"
                                ,"STOTYPE"
                                };
            Object[] valueList = { 
                                  PAKAGE_NAME   
                                , PROCESSTYPE
                                , reqDto.getGUserId()
                                , reqDto.getGSpid()
                                , reqDto.getDeliverydate()
                                , ""
                                , "STO"
                                };
            dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
            dto.setAvc_EXECUTEMODE("NOCOMMIT");

            int rv = cmCommonService.saveProcedure(dto); 
            log.info("rv->{}",rv);
            
            // 프로시저 OUT Parameter(3/4)
            resultCode    = (String)dto.getResultCode();
            resultMessage = (String)dto.getResultMessage();
            
            log.info("resultCode->{}",resultCode);
            log.info("resultMessage->{}",resultMessage);
            
            // 프로시저 Exception 처리(4/4)
            if(!"0".equals(resultCode)){
                log.error("▶외부센터 보충발주 저장 -> 저장 오류 발생 ");
                throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"외부센터 보충발주"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
            }  

            /*3. 인터페이스 실행*/            
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
          
            /*4. 처리결과 조회*/
            return commonDao.selectList(SERVICEID_PREFIX + "getDataResultlist", reqDto);
        }
        
        return null;
    
    }
    
    /**
     * @throws RemoteException 
     * @description : 외부센터 보충발주 엑셀업로드  저장 
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.07.08    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<OmOrderCreationSTOForOutResDto> saveExcelList(OmOrderCreationSTOForOutReqDto paramDto) throws RemoteException {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        List<OmOrderCreationSTOForOutResDto> resultList = new ArrayList<OmOrderCreationSTOForOutResDto>();
        
        // 파라미터 위변조 적용(reqDto->paramDto)
        OmOrderCreationSTOForOutReqDto reqDto = ModelMapperUtil.map(paramDto, OmOrderCreationSTOForOutReqDto.class);
        
        List<OmOrderCreationSTOForOutResDto> saveList = reqDto.getSaveList(); // 저장리스트
        String avc_DCCODE = reqDto.getAvc_DCCODE(); // 센터코드
        
        log.info("▶saveList.size->{}",saveList);
        log.info("▶avc_DCCODE->{}",avc_DCCODE);
        
        if (null != saveList) {
            /*1. 임시 테이블 삭제 및 대상 데이터 저장*/
            // 임시테이블 삭제
            CmSyProcessTempStEntity tempDeleteReqEntity = ModelMapperUtil.map(reqDto, userContext, CmSyProcessTempStEntity.class);
            tempDeleteReqEntity.setProcesstype(PROCESSTYPE);
            tempDeleteReqEntity.setSpid(reqDto.getGSpid());

            commonDao.delete(SERVICEID_TEMP_PREFIX +"deleteSyProcessTemp" + TEMPTABLETYPE, tempDeleteReqEntity);
            
            // 임시테이블에 저장 100개씩
            int chunkSize = 200;
            List<CmSyProcessTempStEntity> insertList = new ArrayList<CmSyProcessTempStEntity>();

            for (int i = 0; i < saveList.size(); i++) {
                OmOrderCreationSTOForOutResDto dto = saveList.get(i);
                CmSyProcessTempStEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempStEntity.class);
                //ProcedureParametersFactory.initParamDto(reqDto, entity, PAKAGE_NAME);
                
                entity.setProcesstype(PROCESSTYPE);
                entity.setTemptabletype(TEMPTABLETYPE);
                entity.setFromStorerkey(dto.getGStorerkey());
                entity.setFromDccode(dto.getFromDccode());
                entity.setFromOrganize(dto.getFromOrganize());
                entity.setFromArea(dto.getFromArea());
                entity.setFromSku(dto.getFromSku());
                entity.setFromUom(dto.getFromUom());
                entity.setFromStockid(dto.getFromStockid());
                entity.setFromStockgrade(dto.getFromStockgrade());
                entity.setToOrderqty(dto.getToOrderqty());
                entity.setToStorerkey(dto.getGStorerkey());
                entity.setToDccode(dto.getToDccode());
                entity.setToOrganize(dto.getToOrganize());
                entity.setToArea(dto.getToArea());
                entity.setToSku(dto.getToSku());
                entity.setToUom(dto.getToUom());
                entity.setToStockid(dto.getToStockid());
                entity.setToStockgrade(dto.getToStockgrade());
                entity.setEtcqty2(dto.getEtcqty2());
                entity.setMemo1(dto.getMemo1());  
                insertList.add(entity);
          
                // 200개마다 혹은 마지막 루프일 때 insert
                if (insertList.size() == chunkSize || i == saveList.size() -1) {
                    commonDao.insert(SERVICEID_PREFIX + "insertSyProcessTemp" + TEMPTABLETYPE, insertList);
                    insertList.clear(); // 다음 배치 준비
                }
            }
            
            /*2. 패키지 프로시저 실행*/
            OmOrderCreationSTOForOutReqDto dto = new OmOrderCreationSTOForOutReqDto();
           
            ProcedureParametersFactory.initParamDto(reqDto, dto, PAKAGE_NAME);
            dto.setAvc_DCCODE(reqDto.getFromDccode());
            dto.setAvc_EXECUTEMODE("NOCOMMIT");
            
            // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>                
            String[] keyList = {
                                 "PROCEDURE"
                                ,"PROCESSTYPE"
                                ,"PROCESSCREATOR"
                                ,"SPID"
                                ,"DELIVERYDATE"
                                ,"CONVERTTYPE"
                                ,"STOTYPE"
                                };
            Object[] valueList = { 
                                  PAKAGE_NAME   
                                , PROCESSTYPE
                                , reqDto.getGUserId()
                                , reqDto.getGSpid()
                                , reqDto.getDeliverydate()
                                , ""
                                , "STO"
                                };
            dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
            dto.setAvc_EXECUTEMODE("NOCOMMIT");

            int rv = cmCommonService.saveProcedure(dto); 
            log.info("rv->{}",rv);
            
            // 프로시저 OUT Parameter(3/4)
            resultCode    = (String)dto.getResultCode();
            resultMessage = (String)dto.getResultMessage();
            
            log.info("resultCode->{}",resultCode);
            log.info("resultMessage->{}",resultMessage);
            
            // 프로시저 Exception 처리(4/4)
            if(!"0".equals(resultCode)){
                log.error("▶외부센터 보충발주 저장 -> 저장 오류 발생 ");
                throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"외부센터 보충발주"}) + resultMessage ); // {0} 처리 시 문제가 발생했습니다.
            }  

            /*3. 인터페이스 실행*/            
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
          
            /*4. 처리결과 조회*/
            resultList = commonDao.selectList(SERVICEID_PREFIX + "getDataExcelResultlist", reqDto);
//            for (int i = 0; i < saveList.size(); i++) {
//                OmOrderCreationSTOForOutResDto resultDto = saveList.get(i);
//                
//                for (int j = 0; j < processResult.size(); j++) {
//                    OmOrderCreationSTOForOutResDto processDto = processResult.get(j);
//                    
//                    if (processDto.getFromSku().equals(resultDto.getFromSku()) && processDto.getFromUom().equals(resultDto.getFromUom())) {
//                        resultDto.setProcessflag(processDto.getProcessflag());
//                        resultDto.setProcessmsg(processDto.getProcessmsg());
//                        break;
//                    }
//                }
//            }
        }
        
        return resultList;
    
    }
	
    /**
     * @description : 외부센터 보충발주 엑셀업로드 데이터 검증
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.10.22    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<OmOrderCreationSTOForOutResDto> validateExcel(OmOrderCreationSTOForOutReqDto reqDto) {
        List<OmOrderCreationSTOForOutResDto> result = new ArrayList<OmOrderCreationSTOForOutResDto>();
        
        if (null != reqDto) {
            for  (OmOrderCreationSTOForOutResDto dto : reqDto.getSaveList()) {
                dto.setStorerkey(reqDto.getStorerkey());
                dto.setFromDccode(reqDto.getFromDccode());
                dto.setToDccode(reqDto.getToDccode());
            }   
            
            result = commonDao.selectList(SERVICEID_PREFIX + "getWithTempTable", reqDto);
            
            for (OmOrderCreationSTOForOutResDto dto : result) {
                if (ObjectUtils.isEmpty(dto.getOrganizeName())) {
                    dto.setProcessflag("E");
                    dto.setProcessmsg("창고 입력 오류");
                } else if (ObjectUtils.isEmpty(dto.getFromArea())) {
                    dto.setProcessflag("E");
                    dto.setProcessmsg("작업구역 입력 오류");
                } else if (ObjectUtils.isEmpty(dto.getStockgradename())) {
                    dto.setProcessflag("E");
                    dto.setProcessmsg("재고속성 입력 오류");
                } else if (ObjectUtils.isEmpty(dto.getStocktypename())) {
                    dto.setProcessflag("E");
                    dto.setProcessmsg("재고위치 입력 오류");
                } else if (ObjectUtils.isEmpty(dto.getSkuname())) {
                    dto.setProcessflag("E");
                    dto.setProcessmsg("상품 입력 오류");
                } else if ((dto.getToOrderqty() == null) || (dto.getToOrderqty().compareTo(new BigDecimal("0")) <= 0)) {
                    dto.setProcessflag("E");
                    dto.setProcessmsg("이동수량 입력 오류");
                } else if (dto.getToOrderqty().compareTo(dto.getPosbqty()) > 0) {
                    dto.setProcessflag("E");
                    dto.setProcessmsg("이동가능수량 대비 이동수량 입력 오류");
                } else if (ObjectUtils.isEmpty(dto.getSerialno())) {
                    dto.setProcessflag("E");
                    dto.setProcessmsg("이력정보 미확인 오류");
                } else if (dto.getBoxflag() == "Y") {
                    if ((dto.getToOrderqty() == null) || (dto.getEtcqty2().compareTo(new BigDecimal("0")) <= 0)) {
                        dto.setProcessflag("E");
                        dto.setProcessmsg("박스수량 입력 오류");
                    } 
                } else {
                    dto.setProcessflag("Y");
                }
            }    
        }
        
        return result;
    }

}
