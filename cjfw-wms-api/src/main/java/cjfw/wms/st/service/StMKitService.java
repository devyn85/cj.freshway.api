package cjfw.wms.st.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.cm.entity.CmSyProcessTempAjEntity;
import cjfw.wms.cm.entity.CmSyProcessTempStEntity;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.common.util.EtcUtil;
import cjfw.wms.st.entity.StMKitIFEntity;
import cjfw.wms.st.dto.StMKitReqDto;
import cjfw.wms.st.dto.StMKitResT1Dto;
import cjfw.wms.webservice.sso.webservice.SSO_NON_SAP_EAIPortTypeProxy;
import cjfw.wms.webservice.sso.webservice.xsd.SSO_NON_SAP_REQUEST;
import cjfw.wms.webservice.sso.webservice.xsd.SSO_NON_SAP_RESPONSE;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright 2025. CJ Freshway Co. all rights reserved.
 * @author : 고혜미
 * @date : 2025.11.05 
 * @description : KIT처리 조회기능을 구현한 Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE AUTHOR MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.12.05 고혜미 (laksjd0606@cj.net) 생성 </pre>
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StMKitService {
	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "stMKitService.";
	private transient static final String PAKAGE_NAME_CONVERT = "SPST_CONVERT_KIT";
	private transient static final String PAKAGE_NAME_REQUEST = "SPAJ_REQUEST_KIT";
	private transient static final String PAKAGE_NAME_REQUEST_DEL = "SPAJ_REQUEST";
	private transient static final String AVC_COMMAND_CREATEZERO = "CREATEZERO_STKIT";
	private transient static final String AVC_COMMAND_APPROVALREQ = "APPROVALREQ_STKIT";
	private transient static final String AVC_COMMAND_APPROVAL = "APPROVAL_STKIT";
	private transient static final String AVC_COMMAND_CONFIRM = "CONFIRM_STKIT";
	private transient static final String AVC_COMMAND_APPROVAL_CANCEL = "APPROVAL_CANCELKIT";
	private transient static final String PROCESSTYPE_REQ_MAIN = "AJ_ADJUSTMENTREQ_KIT_MAIN";
	private transient static final String PROCESSTYPE_REQ_SUB = "AJ_ADJUSTMENTREQ_KIT_SUB";
	private transient static final String PROCESSTYPE_REQ_ELEC = "AJ_ADJUSTMENTREQ_KIT_ELEC";
	private transient static final String PROCESSTYPE_REQ_PROC_MAIN = "AJ_ADJUSTMENTREQ_KIT_PROC_MAIN";
	private transient static final String PROCESSTYPE_REQ_PROC_SUB = "AJ_ADJUSTMENTREQ_KIT_PROC_SUB";
	private transient static final String SERVICEID_TEMP_PREFIX = "cmTempTableService.";  
	private transient static final String STOCKTRANSTYPE_948 = "948";
	private transient static final String STOCKTRANSTYPE_947 = "947";
	private transient static final String IF_SEND_TYPE = "WMSAJ";
	private transient static final String WORKPROCESSCODE = "WMSAJ";
	private transient static final String OMS_FLAG = "Y";
	
	/** 공통.CommonDao */
	private final CommonDao commonDao;
	private final CmCommonService cmCommonService;
	private final UserContext userContext;	
	
	/**
	 * @description : 리포트 대상 조회[이체대상TAB]
	 * @issues : <pre>
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.13 김동한 생성 </pre>
	 */
	public List<StMKitResT1Dto> getReportList(StMKitReqDto dto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getReportList", dto);
	}
	
	
	/**
	 * @description : KIT처리 목록 조회[이체대상TAB]
	 * @issues : <pre>
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.05 고혜미 생성 </pre>
	 */
	public List<StMKitResT1Dto> getMasterList01(StMKitReqDto dto) {
	    if ("1".equals(dto.getProcdiv())) {
	        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList01", dto);
	    } else {
	        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList04", dto);
	    }
	}
	
	/**
     * @description : KIT 목록 조회[이체대상TAB]
     * @issues : <pre>
     * ----------------------------------------------------------- 
     * DATE        AUTHOR                          MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.30  KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<StMKitResT1Dto> getKitList01(StMKitReqDto dto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getKitList01", dto);
    }
    
    /**
     * @description : 전자결재 목록 조회[전자결재TAB]
     * @issues : <pre>
     * ----------------------------------------------------------- 
     * DATE        AUTHOR                          MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.30  KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<StMKitResT1Dto> getMasterList02(StMKitReqDto dto) {        
        if ("1".equals(dto.getProcdiv())) {
	        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList02", dto);
	    } else {
	        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList05", dto);
	    }
    }
    
    /**
     * @description : 처리 목록 조회[처리TAB]
     * @issues : <pre>
     * ----------------------------------------------------------- 
     * DATE        AUTHOR                          MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.30  KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<StMKitResT1Dto> getMasterList03(StMKitReqDto dto) {        
        if ("1".equals(dto.getProcdiv())) {
	        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList03", dto);
	    } else {
	        return commonDao.selectList(SERVICEID_PREFIX + "getMasterList06", dto);
	    }
    }
    
    /**
     * @description : KIT처리 생산저장 결과 조회[이체대상TAB]
     * @issues : <pre>
     * ----------------------------------------------------------- 
     * DATE AUTHOR MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.05 고혜미 생성 </pre>
     */
    public List<StMKitResT1Dto> getMasterResultList01(StMKitReqDto dto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getMasterResultList01", dto);
    }
    
    /**
     * @description : 생산 임시테이블에 저장
     * @issues : <pre>
     * ----------------------------------------------------------- 
     * DATE AUTHOR MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.05 고혜미 생성 </pre>
     */
    public String saveSubItemsList01(StMKitReqDto paramDto) {
        StMKitReqDto reqDto = ModelMapperUtil.map(paramDto, StMKitReqDto.class);
        
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        
        List<StMKitResT1Dto> saveList = reqDto.getSaveList();
        List<StMKitResT1Dto> saveKitList = reqDto.getSaveKitList();
        List<CmSyProcessTempAjEntity> insertList = new ArrayList<CmSyProcessTempAjEntity>();

        log.info("saveList ==> {}", saveList);
        log.info("saveKitList ==> {}", saveKitList);
        
        // 모품목 임시테이블 삭제
        CmSyProcessTempAjEntity tempDeleteReqEntity = ModelMapperUtil.map(reqDto, userContext, CmSyProcessTempAjEntity.class);
        tempDeleteReqEntity.setProcesstype(PROCESSTYPE_REQ_MAIN);
        tempDeleteReqEntity.setSpid(reqDto.getGSpid());
        commonDao.delete(SERVICEID_TEMP_PREFIX +"deleteSyProcessTempAJ", tempDeleteReqEntity);
        
        //1. kit 상품(모품목) 역감모
        if (null != saveKitList) {
            for (StMKitResT1Dto kitDto : saveKitList) {                
                //String loc = (String) commonDao.selectOne(SERVICEID_PREFIX + "getLocationList", kitDto);
                //dto.setLoc(loc);
                kitDto.setLoc("STAGE");
                List<HashMap> headerZeroList = commonDao.selectList(SERVICEID_PREFIX + "getDataHeaderKitZeroList", kitDto);
                if (null != headerZeroList || headerZeroList.size() > 0) {
                    // kit 상품 임시테이블 데이터 생성
                    int chunkSize = 200;                    
                    CmSyProcessTempAjEntity entity = ModelMapperUtil.map(kitDto, userContext, CmSyProcessTempAjEntity.class);
                    
                    entity.setProcesstype(PROCESSTYPE_REQ_MAIN);
                    entity.setStorerkey(kitDto.getStorerkey());
                    entity.setDccode(headerZeroList.get(0).get("DCCODE").toString());
                    entity.setDocdt(headerZeroList.get(0).get("PLAN_DT").toString());
                    entity.setArea((String)headerZeroList.get(0).get("AREA"));
                    entity.setOrganize((String)headerZeroList.get(0).get("ORGANIZE"));
                    entity.setSku((String)headerZeroList.get(0).get("SKU"));
                    entity.setKitSku((String)headerZeroList.get(0).get("SKU"));
                    entity.setStocktype((String)headerZeroList.get(0).get("STOCKTYPE"));
                    entity.setStocktypenm((String)headerZeroList.get(0).get("STOCKTYPENM"));
                    entity.setStockgrade((String)headerZeroList.get(0).get("STOCKGRADE"));
                    entity.setStockgradename((String)headerZeroList.get(0).get("STOCKGRADENAME"));
                    entity.setLoc((String)headerZeroList.get(0).get("LOC"));
                    entity.setLot((String)headerZeroList.get(0).get("LOT"));
                    entity.setProcessqty((BigDecimal)headerZeroList.get(0).get("QTYALLOCATED"));
                    entity.setWorkqty((BigDecimal)headerZeroList.get(0).get("QTYPICKED"));
                    entity.setOpenqty((BigDecimal)headerZeroList.get(0).get("OPENQTY"));
                    entity.setOrderqty((BigDecimal)headerZeroList.get(0).get("ORDERQTY"));
                    entity.setUom((String)headerZeroList.get(0).get("UOM")); 
                    entity.setCustkey((String)headerZeroList.get(0).get("CUSTKEY"));
                    entity.setCostcd((String)headerZeroList.get(0).get("RESP_DEPT_CD"));
                    insertList.add(entity);
              
                    // AJ임시 테이블에 저장 실행
                    if (insertList.size() > chunkSize) {
                        commonDao.insert(SERVICEID_PREFIX + "insertSyProcessTempAJ", insertList);
                        insertList.clear(); // 다음 배치 준비  
                    }
                }
            } //end.. for (StMKitResT1Dto kitDto : saveList) {
            
            // AJ임시 테이블에 저장 실행
            if (insertList.size() > 0) {
                commonDao.insert(SERVICEID_PREFIX + "insertSyProcessTempAJ", insertList);
                insertList.clear(); // 다음 배치 준비  
            }
        } //end..if (null != saveKitList) 
        
        //2. 구성상품(자품목) 감모       
        tempDeleteReqEntity = ModelMapperUtil.map(reqDto, userContext, CmSyProcessTempAjEntity.class);
        tempDeleteReqEntity.setProcesstype(PROCESSTYPE_REQ_SUB);
        tempDeleteReqEntity.setSpid(reqDto.getGSpid());
        commonDao.delete(SERVICEID_TEMP_PREFIX +"deleteSyProcessTempAJ", tempDeleteReqEntity);
        if (null != saveList) {
            // kit 구성상품 임시테이블 데이터 생성
            insertList.clear(); 
            int chunkSize = 200;
            for (StMKitResT1Dto resDto : saveList) {
                CmSyProcessTempAjEntity entity = ModelMapperUtil.map(resDto, userContext, CmSyProcessTempAjEntity.class);
                entity.setProcesstype(PROCESSTYPE_REQ_SUB);
                entity.setStorerkey(resDto.getStorerkey());
                entity.setConfirmqty(resDto.getReqQty().multiply(BigDecimal.valueOf(-1.0)));
               
                //DCCODE|STORERKEY|ORGANIZE|AREA|ZONE|LOC|SKU|UOM|LOT|STOCKID|STOCKGRADE|STOCKTYPE|QTY|OPENQTY|QTYALLOCATED|QTYPICKED|CALLQTY|DURATION|REASONCODE|DISUSETYPE|IMPUTETYPE|PROCESSMAIN|COSTCD|CUSTKEY|KIT_SKU|PLANORDER|PLANDT
                //DCCODE|STORERKEY|ORGANIZE|AREA|ZONE|LOC|SKU|UOM|LOT|STOCKID|STOCKGRADE|STOCKTYPE|ORDERQTY|OPENQTY|PROCESSQTY|WORKQTY|CONFIRMQTY|MIXBOXKEY|TASKKEY|DELIVERYGROUP|WAVEKEY|INVOICENO|OTHER01|CUSTKEY|OTHER02|OTHER03|OTHER04"
                
                entity.setDccode(resDto.getDccode());
                entity.setOrganize(resDto.getOrganize());
                entity.setArea(resDto.getArea());
                entity.setZone(resDto.getZone());
                entity.setDocdt(resDto.getPlanDt());
                entity.setLoc(resDto.getLoc());
                entity.setSku(resDto.getSku());
                entity.setKitSku(resDto.getKitSku());
                entity.setUom(resDto.getSkuUom());
                entity.setLot(resDto.getLot());
                entity.setStockid(resDto.getStockid());
                entity.setStockgrade(resDto.getStockgrade());
                entity.setOpenqty(resDto.getOpenqty());
                entity.setOrderqty(resDto.getReqQty().multiply(BigDecimal.valueOf(-1.0))); 
                entity.setQtyallocated(resDto.getQtyallocated());
                entity.setQtypicked(resDto.getQtypicked());
                entity.setCallqty(resDto.getCallqty());
                entity.setDuration(resDto.getDuration());
                entity.setReasoncode(resDto.getReasoncode());
                entity.setDisusetype(resDto.getDisusetype());
                entity.setImputetype(resDto.getImputetype());
                entity.setProcessmain(resDto.getProcessmain());
                entity.setCostcd(resDto.getCostcd());
                entity.setCustkey(resDto.getCustkey());  
                entity.setPlanorder(null);
                entity.setPlandt(resDto.getPlanDt());
                insertList.add(entity);
          
                // AJ임시 테이블에 저장 실행
                if (insertList.size() > chunkSize) {
                    commonDao.insert(SERVICEID_PREFIX + "insertSyProcessTempAJ", insertList);
                    insertList.clear(); // 다음 배치 준비  
                }
            }
            
            // AJ임시 테이블에 저장 실행
            if (insertList.size() > 0) {
                commonDao.insert(SERVICEID_PREFIX + "insertSyProcessTempAJ", insertList);
                insertList.clear(); // 다음 배치 준비  
            }            
        }
        
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    
    /**
     * @description : 해제 임시테이블에 저장
     * @issues : <pre>
     * ----------------------------------------------------------- 
     * DATE AUTHOR MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2026.01.14 공두경 생성 </pre>
     */
    public String saveSubItemsList02(StMKitReqDto paramDto) {
        StMKitReqDto reqDto = ModelMapperUtil.map(paramDto, StMKitReqDto.class);
        
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        
        List<StMKitResT1Dto> saveList = reqDto.getSaveList();
        List<StMKitResT1Dto> saveKitList = reqDto.getSaveKitList();
        List<CmSyProcessTempAjEntity> insertList = new ArrayList<CmSyProcessTempAjEntity>();

        log.info("saveList ==> {}", saveList);
        log.info("saveKitList ==> {}", saveKitList);
        
        // 모품목 임시테이블 삭제
        CmSyProcessTempAjEntity tempDeleteReqEntity = ModelMapperUtil.map(reqDto, userContext, CmSyProcessTempAjEntity.class);
        tempDeleteReqEntity.setProcesstype(PROCESSTYPE_REQ_MAIN);
        tempDeleteReqEntity.setSpid(reqDto.getGSpid());
        commonDao.delete(SERVICEID_TEMP_PREFIX +"deleteSyProcessTempAJ", tempDeleteReqEntity);
        
        //1. kit 상품(모품목) 감모
        if (null != saveKitList) {
            for (StMKitResT1Dto kitDto : saveKitList) {                
                StMKitResT1Dto dto = saveKitList.get(0);
                List<HashMap> headerZeroList = commonDao.selectList(SERVICEID_PREFIX + "getDataHeaderKitDivInfo", dto);
                if (null != headerZeroList || headerZeroList.size() > 0) {
                    // kit 상품 임시테이블 데이터 생성
                    int chunkSize = 200;                    
                    CmSyProcessTempAjEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempAjEntity.class);
                    
                    entity.setProcesstype(PROCESSTYPE_REQ_MAIN);
                    entity.setStorerkey(dto.getStorerkey());
                    entity.setDccode(headerZeroList.get(0).get("DCCODE").toString());
                    entity.setDocdt(headerZeroList.get(0).get("PLAN_DT").toString());
                    entity.setArea((String)headerZeroList.get(0).get("AREA"));
                    entity.setOrganize((String)headerZeroList.get(0).get("ORGANIZE"));
                    entity.setSku((String)headerZeroList.get(0).get("SKU"));
                    entity.setKitSku((String)headerZeroList.get(0).get("SKU"));
                    entity.setStocktype((String)headerZeroList.get(0).get("STOCKTYPE"));
                    entity.setStocktypenm((String)headerZeroList.get(0).get("STOCKTYPENM"));
                    entity.setStockgrade((String)headerZeroList.get(0).get("STOCKGRADE"));
                    entity.setStockgradename((String)headerZeroList.get(0).get("STOCKGRADENAME"));
                    entity.setLoc((String)headerZeroList.get(0).get("LOC"));
                    entity.setLot((String)headerZeroList.get(0).get("LOT"));
                    entity.setProcessqty((BigDecimal)headerZeroList.get(0).get("QTYALLOCATED"));
                    entity.setWorkqty((BigDecimal)headerZeroList.get(0).get("QTYPICKED"));
                    entity.setOpenqty((BigDecimal)headerZeroList.get(0).get("OPENQTY"));
                    entity.setOrderqty((BigDecimal)headerZeroList.get(0).get("ORDERQTY"));
                    entity.setOrderqty(entity.getOrderqty().multiply(BigDecimal.valueOf(-1.0)));
                    entity.setUom((String)headerZeroList.get(0).get("UOM")); 
                    entity.setCustkey((String)headerZeroList.get(0).get("CUSTKEY"));
                    entity.setCostcd((String)headerZeroList.get(0).get("RESP_DEPT_CD"));
                    insertList.add(entity);
              
                    // AJ임시 테이블에 저장 실행
                    if (insertList.size() > chunkSize) {
                        commonDao.insert(SERVICEID_PREFIX + "insertSyProcessTempAJ", insertList);
                        insertList.clear(); // 다음 배치 준비  
                    }
                }
            } //end.. for (StMKitResT1Dto kitDto : saveList) {
            
            // AJ임시 테이블에 저장 실행
            if (insertList.size() > 0) {
                commonDao.insert(SERVICEID_PREFIX + "insertSyProcessTempAJ", insertList);
                insertList.clear(); // 다음 배치 준비  
            }
        } //end..if (null != saveKitList) 
        
        //2. 구성상품(자품목) 감모       
        tempDeleteReqEntity = ModelMapperUtil.map(reqDto, userContext, CmSyProcessTempAjEntity.class);
        tempDeleteReqEntity.setProcesstype(PROCESSTYPE_REQ_SUB);
        tempDeleteReqEntity.setSpid(reqDto.getGSpid());
        commonDao.delete(SERVICEID_TEMP_PREFIX +"deleteSyProcessTempAJ", tempDeleteReqEntity);
        if (null != saveList) {
            // kit 구성상품 임시테이블 데이터 생성
            insertList.clear(); 
            int chunkSize = 200;
            for (StMKitResT1Dto resDto : saveList) {
                CmSyProcessTempAjEntity entity = ModelMapperUtil.map(resDto, userContext, CmSyProcessTempAjEntity.class);
                entity.setProcesstype(PROCESSTYPE_REQ_SUB);
                entity.setStorerkey(resDto.getStorerkey());
                entity.setConfirmqty(resDto.getReqQty());
               
                //DCCODE|STORERKEY|ORGANIZE|AREA|ZONE|LOC|SKU|UOM|LOT|STOCKID|STOCKGRADE|STOCKTYPE|QTY|OPENQTY|QTYALLOCATED|QTYPICKED|CALLQTY|DURATION|REASONCODE|DISUSETYPE|IMPUTETYPE|PROCESSMAIN|COSTCD|CUSTKEY|KIT_SKU|PLANORDER|PLANDT
                //DCCODE|STORERKEY|ORGANIZE|AREA|ZONE|LOC|SKU|UOM|LOT|STOCKID|STOCKGRADE|STOCKTYPE|ORDERQTY|OPENQTY|PROCESSQTY|WORKQTY|CONFIRMQTY|MIXBOXKEY|TASKKEY|DELIVERYGROUP|WAVEKEY|INVOICENO|OTHER01|CUSTKEY|OTHER02|OTHER03|OTHER04"
                
                entity.setDccode(resDto.getDccode());
                entity.setOrganize(resDto.getOrganize());
                entity.setArea(resDto.getArea());
                entity.setZone(resDto.getZone());
                entity.setDocdt(resDto.getPlanDt());
                entity.setLoc(resDto.getLoc());
                entity.setSku(resDto.getSku());
                entity.setKitSku(resDto.getKitSku());
                entity.setUom(resDto.getSkuUom());
                entity.setLot(resDto.getLot());
                entity.setStockid(resDto.getStockid());
                entity.setStockgrade(resDto.getStockgrade());
                entity.setOpenqty(resDto.getOpenqty());
                entity.setOrderqty(resDto.getReqQty()); 
                entity.setQtyallocated(resDto.getQtyallocated());
                entity.setQtypicked(resDto.getQtypicked());
                entity.setCallqty(resDto.getCallqty());
                entity.setDuration(resDto.getDuration());
                entity.setReasoncode(resDto.getReasoncode());
                entity.setDisusetype(resDto.getDisusetype());
                entity.setImputetype(resDto.getImputetype());
                entity.setProcessmain(resDto.getProcessmain());
                entity.setCostcd(resDto.getCostcd());
                entity.setCustkey(resDto.getCustkey());  
                entity.setPlanorder(null);
                entity.setPlandt(resDto.getPlanDt());
                insertList.add(entity);
          
                // AJ임시 테이블에 저장 실행
                if (insertList.size() > chunkSize) {
                    commonDao.insert(SERVICEID_PREFIX + "insertSyProcessTempAJ", insertList);
                    insertList.clear(); // 다음 배치 준비  
                }
            }
            
            // AJ임시 테이블에 저장 실행
            if (insertList.size() > 0) {
                commonDao.insert(SERVICEID_PREFIX + "insertSyProcessTempAJ", insertList);
                insertList.clear(); // 다음 배치 준비  
            }            
        }
        
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    
    /**
     * @description : 이체대상저장
     * @issues : <pre>
     * ----------------------------------------------------------- 
     * DATE AUTHOR MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.07 고혜미 생성 </pre>
     */
    public String saveMasterList01(StMKitReqDto paramDto) {
        StMKitReqDto reqDto = ModelMapperUtil.map(paramDto, StMKitReqDto.class);
        
        List<StMKitResT1Dto> saveList = reqDto.getSaveList();
        List<StMKitResT1Dto> saveKitList = reqDto.getSaveKitList();
        List<CmSyProcessTempAjEntity> insertList = new ArrayList<CmSyProcessTempAjEntity>();

        log.info("saveList ==> {}", saveList);
        log.info("saveKitList ==> {}", saveKitList);
        
        // 프로시저 에러코드 및 메세지
        String resultCode = "0";
        String resultMessage  = "";
        

        // kit 상품의 상세정보 저장(패키지안에서 STATUS 변경하기에 미리 저장함)
        for (StMKitResT1Dto dto : saveList) {
            var entity = ModelMapperUtil.map(dto, userContext, StMKitResT1Dto.class);
            commonDao.selectOne(SERVICEID_PREFIX + "saveStKitPlandetail", entity);
        }
        
        if (null != saveKitList) {
            for (StMKitResT1Dto kitDto : saveKitList) {
            	StMKitResT1Dto dto = saveKitList.get(0);
                //String loc = (String) commonDao.selectOne(SERVICEID_PREFIX + "getLocationList", kitDto);                
                //dto.setLoc(loc);
                dto.setLoc("STAGE");
                List<Object> zeroList = commonDao.selectList(SERVICEID_PREFIX + "getExistKitZeroList", dto);
                if (null == zeroList || zeroList.size() == 0) {
                    // kit 상품 재고생성 전이면 Zero 재고 생성
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                    //String stockId = "C" + dateFormat.format(calendar.getTime());
                    String stockId = "STD";
                    
                    // PKG 파라마터 세팅 - 공통(1/4)
                    StMKitResT1Dto procedureDto = new StMKitResT1Dto();
                    ProcedureParametersFactory.initParamDto(dto, procedureDto, PAKAGE_NAME_CONVERT);
                    procedureDto.setAvc_DCCODE(dto.getDccode());
                    procedureDto.setAvc_COMMAND(AVC_COMMAND_CREATEZERO);
                    
                    // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>                
                    String[] keyList = new String[] {"DCCODE"
                                                    , "STORERKEY"
                                                    , "ORGANIZE"
                                                    , "SKU"
                                                    , "LOC"
                                                    , "STOCKID"
                                                    , "STOCKGRADE"
                                                    , "LOTTABLE01"};
                    Object[] valueList = new Object[] {dto.getDccode()
                                                     , dto.getStorerkey()
                                                     , dto.getOrganize()
                                                     , dto.getKitSku()
                                                     , dto.getLoc()
                                                     , stockId
                                                     , "STD"
                                                     , dto.getMixboxkey()};
                    procedureDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
                    
                    // 제로재고 생성 프로시저 호출
                    int rv = cmCommonService.saveProcedure(procedureDto); 
                    log.info("rv->{}",rv);
                    
                    // 프로시저 OUT Parameter(3/4)
                    resultCode    = (String)procedureDto.getResultCode();
                    resultMessage = (String)procedureDto.getResultMessage();
                    
                    log.info("resultCode->{}",resultCode);
                    log.info("resultMessage->{}",resultMessage);
                    
                    // 프로시저 Exception 처리(4/4)
                    if (!resultCode.equals("0")){
                        log.error("▶KIT 처리 -> 저장 오류 발생 ");
                        throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
                    }  
                }//END..if (null == zeroList || zeroList.size() == 0) 
                
              //1. 구성상품(자품목) 감모       
                if (null != saveList && resultCode.equals("0")) {
                    reqDto.setProcesstype(PROCESSTYPE_REQ_SUB);
                    reqDto.setKitSku(kitDto.getKitSku());
                    reqDto.setPlanDt(kitDto.getPlanDt());
                    List<Map<String, String>> tempList = commonDao.selectList(SERVICEID_PREFIX + "getSyProcessTempAJ", reqDto);
                    
                    for (Map<String, String> obj : tempList) {
                        // PKG 파라마터 세팅 - 공통(1/4)
                        StMKitResT1Dto procedureDto = new StMKitResT1Dto();
                        ProcedureParametersFactory.initParamDto(reqDto, procedureDto, PAKAGE_NAME_REQUEST);
                        procedureDto.setAvc_DCCODE(reqDto.getFixdccode());
                        procedureDto.setAvc_COMMAND(AVC_COMMAND_APPROVALREQ);
                        
                        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>                
                        String[] keyList = new String[] {"PROCEDURE"
                                                        , "PROCESSTYPE"
                                                        , "PROCESSCREATOR"
                                                        , "SPID"
                                                        , "STOCKTRANSTYPE"
                                                        , "DOCDT"
                                                        , "IF_SEND_TYPE"
                                                        , "WORKPROCESSCODE"
                                                        , "OMS_FLAG"
                                                        , "SERIALKEY"
                                                        };
                        Object[] valueList = new Object[] {PAKAGE_NAME_REQUEST
                                                         , PROCESSTYPE_REQ_SUB
                                                         , reqDto.getGUserId()
                                                         , reqDto.getGSpid()
                                                         , STOCKTRANSTYPE_947
                                                         , null
                                                         , IF_SEND_TYPE
                                                         , WORKPROCESSCODE
                                                         , OMS_FLAG
                                                         , obj.get("SERIALKEY")
                                                         };
                        procedureDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
                        
                        // KIT구성상품 감모(-) 프로시저 호출
                        int rv = cmCommonService.saveProcedure(procedureDto); 
                        log.info("rv->{}",rv);
                        
                        // 프로시저 OUT Parameter(3/4)
                        resultCode    = (String)procedureDto.getResultCode();
                        resultMessage = (String)procedureDto.getResultMessage();
                        
                        log.info("resultCode->{}",resultCode);
                        log.info("resultMessage->{}",resultMessage);
                        
                        // 프로시저 Exception 처리(4/4)
                        if (!resultCode.equals("0")){
                            log.error("▶KIT 처리 -> 자품목 재고 저장 오류 발생 ");
                            break;
                        //    throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
                        }
                    }//END..for (Map<String, String> obj : tempList)
                }//END..if (null != saveList)
              //END..1. 구성상품(자품목) 감모 
                
              //2. kit 상품(모품목) 역감모
                if (resultCode.equals("0")) {
                    reqDto.setProcesstype(PROCESSTYPE_REQ_MAIN);
                    reqDto.setKitSku(kitDto.getKitSku());
                    reqDto.setPlanDt(kitDto.getPlanDt());
                    List<Map<String, String>> tempList = commonDao.selectList(SERVICEID_PREFIX + "getSyProcessTempAJ", reqDto);
                    
                    // PKG 파라마터 세팅 - 공통(1/4)
                    StMKitResT1Dto procedureDto = new StMKitResT1Dto();
                    ProcedureParametersFactory.initParamDto(reqDto, procedureDto, PAKAGE_NAME_REQUEST);
                    procedureDto.setAvc_DCCODE(reqDto.getFixdccode());
                    procedureDto.setAvc_COMMAND(AVC_COMMAND_APPROVALREQ);
                    
                    // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>                
                    String[] keyList = new String[] {"PROCEDURE"
                                                    , "PROCESSTYPE"
                                                    , "PROCESSCREATOR"
                                                    , "SPID"
                                                    , "STOCKTRANSTYPE"
                                                    , "DOCDT"
                                                    , "IF_SEND_TYPE"
                                                    , "WORKPROCESSCODE"
                                                    , "OMS_FLAG"
                                                    , "SERIALKEY"
                                                    };
                    Object[] valueList = new Object[] {PAKAGE_NAME_REQUEST
                                                     , PROCESSTYPE_REQ_MAIN
                                                     , reqDto.getGUserId()
                                                     , reqDto.getGSpid()
                                                     , STOCKTRANSTYPE_948
                                                     , saveKitList.get(0).getPlanDt()
                                                     , IF_SEND_TYPE
                                                     , WORKPROCESSCODE
                                                     , OMS_FLAG
                                                     , tempList.get(0).get("SERIALKEY")
                                                     };
                    procedureDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
                    
                    // KIT상품 역감모(+) 프로시저 호출
                    int rv = cmCommonService.saveProcedure(procedureDto); 
                    log.info("rv->{}",rv);
                    
                    // 프로시저 OUT Parameter(3/4)
                    resultCode    = (String)procedureDto.getResultCode();
                    resultMessage = (String)procedureDto.getResultMessage();
                    
                    log.info("resultCode->{}",resultCode);
                    log.info("resultMessage->{}",resultMessage);
                    
                    // 프로시저 Exception 처리(4/4)
                    if (!resultCode.equals("0")){
                        log.error("▶KIT 처리 -> 모품목 재고 저장 오류 발생 ");
                        throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
                    }
                }
                //END..//2. kit 상품(모품목) 역감모
            }//END..for (StMKitResT1Dto kitDto : saveKitList) 
        }//END..if (null != saveKitList) 
        

        // kit 상품의 생산수량 저장
        for (StMKitResT1Dto dto : saveList) {
            var entity = ModelMapperUtil.map(dto, userContext, StMKitResT1Dto.class);
            commonDao.selectOne(SERVICEID_PREFIX + "updateStKitPlan", entity);
        }
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    

    /**
     * @description : 해제대상저장
     * @issues : <pre>
     * ----------------------------------------------------------- 
     * DATE AUTHOR MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2026.01.15 공두경 생성 </pre>
     */
    public String saveMasterList04(StMKitReqDto paramDto) {
        StMKitReqDto reqDto = ModelMapperUtil.map(paramDto, StMKitReqDto.class);
        
        List<StMKitResT1Dto> saveList = reqDto.getSaveList();
        List<StMKitResT1Dto> saveKitList = reqDto.getSaveKitList();
        List<CmSyProcessTempAjEntity> insertList = new ArrayList<CmSyProcessTempAjEntity>();

        log.info("saveList ==> {}", saveList);
        log.info("saveKitList ==> {}", saveKitList);
        
        // 프로시저 에러코드 및 메세지
        String resultCode = "0";
        String resultMessage  = "";
        
        // kit 상품의 생산수량 저장
        for (StMKitResT1Dto dto : saveList) {
            var entity = ModelMapperUtil.map(dto, userContext, StMKitResT1Dto.class);   
            entity.setReqQty(entity.getReqQty().multiply(BigDecimal.valueOf(-1.0)));
            entity.setConfirmqty(entity.getReqQty());
            commonDao.selectOne(SERVICEID_PREFIX + "saveStKitPlandetail", entity);
        }
        
        if (null != saveKitList) {
            for (StMKitResT1Dto kitDto : saveKitList) {
            	StMKitResT1Dto dto = saveKitList.get(0);
                
              //1. 구성상품(자품목) 역감모       
                if (null != saveList && resultCode.equals("0")) {
                    reqDto.setProcesstype(PROCESSTYPE_REQ_SUB);
                    reqDto.setKitSku(kitDto.getKitSku());
                    reqDto.setPlanDt(kitDto.getPlanDt());
                    List<Map<String, String>> tempList = commonDao.selectList(SERVICEID_PREFIX + "getSyProcessTempAJ", reqDto);
                    
                    for (Map<String, String> obj : tempList) {
                        // PKG 파라마터 세팅 - 공통(1/4)
                        StMKitResT1Dto procedureDto = new StMKitResT1Dto();
                        ProcedureParametersFactory.initParamDto(reqDto, procedureDto, PAKAGE_NAME_REQUEST);
                        procedureDto.setAvc_DCCODE(reqDto.getFixdccode());
                        procedureDto.setAvc_COMMAND(AVC_COMMAND_APPROVALREQ);
                        
                        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>                
                        String[] keyList = new String[] {"PROCEDURE"
                                                        , "PROCESSTYPE"
                                                        , "PROCESSCREATOR"
                                                        , "SPID"
                                                        , "STOCKTRANSTYPE"
                                                        , "DOCDT"
                                                        , "IF_SEND_TYPE"
                                                        , "WORKPROCESSCODE"
                                                        , "OMS_FLAG"
                                                        , "SERIALKEY"
                                                        };
                        Object[] valueList = new Object[] {PAKAGE_NAME_REQUEST
                                                         , PROCESSTYPE_REQ_SUB
                                                         , reqDto.getGUserId()
                                                         , reqDto.getGSpid()
                                                         , STOCKTRANSTYPE_948
                                                         , null
                                                         , IF_SEND_TYPE
                                                         , WORKPROCESSCODE
                                                         , OMS_FLAG
                                                         , obj.get("SERIALKEY")
                                                         };
                        procedureDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
                        
                        // KIT구성상품 역감모(-) 프로시저 호출
                        int rv = cmCommonService.saveProcedure(procedureDto); 
                        log.info("rv->{}",rv);
                        
                        // 프로시저 OUT Parameter(3/4)
                        resultCode    = (String)procedureDto.getResultCode();
                        resultMessage = (String)procedureDto.getResultMessage();
                        
                        log.info("resultCode->{}",resultCode);
                        log.info("resultMessage->{}",resultMessage);
                        
                        // 프로시저 Exception 처리(4/4)
                        if (!resultCode.equals("0")){
                            log.error("▶KIT 처리 -> 자품목 재고 저장 오류 발생 ");
                            break;
                        //    throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
                        }
                    }//END..for (Map<String, String> obj : tempList)
                }//END..if (null != saveList)
              //END..1. 구성상품(자품목) 역감모 
                
              //2. kit 상품(모품목) 감모
                if (resultCode.equals("0")) {
                    reqDto.setProcesstype(PROCESSTYPE_REQ_MAIN);
                    reqDto.setKitSku(kitDto.getKitSku());
                    reqDto.setPlanDt(kitDto.getPlanDt());
                    List<Map<String, String>> tempList = commonDao.selectList(SERVICEID_PREFIX + "getSyProcessTempAJ", reqDto);
                    
                    // PKG 파라마터 세팅 - 공통(1/4)
                    StMKitResT1Dto procedureDto = new StMKitResT1Dto();
                    ProcedureParametersFactory.initParamDto(reqDto, procedureDto, PAKAGE_NAME_REQUEST);
                    procedureDto.setAvc_DCCODE(reqDto.getFixdccode());
                    procedureDto.setAvc_COMMAND(AVC_COMMAND_APPROVALREQ);
                    
                    // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>                
                    String[] keyList = new String[] {"PROCEDURE"
                                                    , "PROCESSTYPE"
                                                    , "PROCESSCREATOR"
                                                    , "SPID"
                                                    , "STOCKTRANSTYPE"
                                                    , "DOCDT"
                                                    , "IF_SEND_TYPE"
                                                    , "WORKPROCESSCODE"
                                                    , "OMS_FLAG"
                                                    , "SERIALKEY"
                                                    };
                    Object[] valueList = new Object[] {PAKAGE_NAME_REQUEST
                                                     , PROCESSTYPE_REQ_MAIN
                                                     , reqDto.getGUserId()
                                                     , reqDto.getGSpid()
                                                     , STOCKTRANSTYPE_947
                                                     , saveKitList.get(0).getPlanDt()
                                                     , IF_SEND_TYPE
                                                     , WORKPROCESSCODE
                                                     , OMS_FLAG
                                                     , tempList.get(0).get("SERIALKEY")
                                                     };
                    procedureDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
                    
                    // KIT상품 역감모(+) 프로시저 호출
                    int rv = cmCommonService.saveProcedure(procedureDto); 
                    log.info("rv->{}",rv);
                    
                    // 프로시저 OUT Parameter(3/4)
                    resultCode    = (String)procedureDto.getResultCode();
                    resultMessage = (String)procedureDto.getResultMessage();
                    
                    log.info("resultCode->{}",resultCode);
                    log.info("resultMessage->{}",resultMessage);
                    
                    // 프로시저 Exception 처리(4/4)
                    if (!resultCode.equals("0")){
                        log.error("▶KIT 처리 -> 모품목 재고 저장 오류 발생 ");
                        throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
                    }
                }
                //END..//2. kit 상품(모품목) 역감모
            }//END..for (StMKitResT1Dto kitDto : saveKitList) 
        }//END..if (null != saveKitList) 
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    
    
    
    /**
     * @description : 전자결재
     * @issues : <pre>
     * ----------------------------------------------------------- 
     * DATE AUTHOR MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.07 고혜미 생성 </pre>
     */
    public String saveMasterList02(StMKitReqDto paramDto) {
        StMKitReqDto reqDto = ModelMapperUtil.map(paramDto, StMKitReqDto.class);
        
     // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        String returnMessage = "";

        List<StMKitResT1Dto> saveList = reqDto.getSaveList();
        List<StMKitResT1Dto> saveKitList = reqDto.getSaveKitList();
        List<CmSyProcessTempAjEntity> insertList = new ArrayList<CmSyProcessTempAjEntity>();

        log.info("saveList ==> {}", saveList);
        log.info("saveKitList ==> {}", saveKitList);
        
     // 모품목 임시테이블 삭제
        CmSyProcessTempAjEntity tempDeleteReqEntity = ModelMapperUtil.map(reqDto, userContext, CmSyProcessTempAjEntity.class);
        tempDeleteReqEntity.setProcesstype(PROCESSTYPE_REQ_ELEC);
        tempDeleteReqEntity.setSpid(reqDto.getGSpid());
        commonDao.delete(SERVICEID_TEMP_PREFIX +"deleteSyProcessTempAJ", tempDeleteReqEntity);
        
      //1. kit 상품(모품목) 역감모
        if (null != saveKitList) {
            for (StMKitResT1Dto kitDto : saveKitList) {
                // kit 상품 임시테이블 데이터 생성
                int chunkSize = 200;                    
                CmSyProcessTempAjEntity entity = ModelMapperUtil.map(kitDto, userContext, CmSyProcessTempAjEntity.class);
                
                entity.setProcesstype(PROCESSTYPE_REQ_ELEC);
                entity.setStorerkey(reqDto.getStorerkey());
                entity.setDccode(kitDto.getDccode());
                entity.setOrganize(kitDto.getDccode());
                entity.setDocdt(kitDto.getPlanDt());
                entity.setDocno(kitDto.getKitSku());
                entity.setDocline("001");
                entity.setSlipdt(kitDto.getPlanDt());
                entity.setSlipno(kitDto.getDocno());
                entity.setSlipline("001");
                entity.setEtcqty1(new BigDecimal(0));
                entity.setSku(kitDto.getKitSku());
                entity.setKitSku(kitDto.getKitSku());
                entity.setSlipdt(kitDto.getPlanDt()); 
                insertList.add(entity);
          
                // AJ임시 테이블에 저장 실행
                if (insertList.size() > chunkSize) {
                    commonDao.insert(SERVICEID_PREFIX + "insertSyProcessTempAJ", insertList);
                    insertList.clear(); // 다음 배치 준비  
                }
            } //end.. for (StMKitResT1Dto kitDto : saveList) {
            
            // AJ임시 테이블에 저장 실행
            if (insertList.size() > 0) {
                commonDao.insert(SERVICEID_PREFIX + "insertSyProcessTempAJ", insertList);
                insertList.clear(); // 다음 배치 준비  
            }
            
            
            //2. 패키지 실행
            // PKG 파라마터 세팅 - 공통(1/4)
            StMKitResT1Dto procedureDto = new StMKitResT1Dto();
            ProcedureParametersFactory.initParamDto(reqDto, procedureDto, PAKAGE_NAME_REQUEST);
            procedureDto.setAvc_DCCODE(reqDto.getFixdccode());
            procedureDto.setAvc_COMMAND(AVC_COMMAND_APPROVAL);
            
            // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>                
            String[] keyList = new String[] {"PROCEDURE"
                                            , "PROCESSTYPE"
                                            , "PROCESSCREATOR"
                                            , "SPID"
                                            , "STOCKTRANSTYPE"
                                            };
            Object[] valueList = new Object[] {PAKAGE_NAME_REQUEST
                                         , PROCESSTYPE_REQ_ELEC
                                         , reqDto.getGUserId()
                                         , reqDto.getGSpid()
                                         , STOCKTRANSTYPE_948   //역감모
                                         };
            procedureDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
            
            // 프로시저 호출
            int rv = cmCommonService.saveProcedure(procedureDto); 
            log.info("rv->{}",rv);
            
            // 프로시저 OUT Parameter(3/4)
            resultCode    = (String)procedureDto.getResultCode();
            resultMessage = (String)procedureDto.getResultMessage();
            returnMessage = (String)procedureDto.getReturnMessage();
            
            log.info("resultCode->{}",resultCode);
            log.info("resultMessage->{}",resultMessage);
            log.info("returnMessage->{}",returnMessage);

            // 프로시저 Exception 처리(4/4)
            if (!resultCode.equals("0")){
                log.error("▶KIT 처리 -> 전자결재 저장 오류 발생 ");
                throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
            }
            
            //3. 전자결재용 SSO_ID 요청
            try {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");

                SSO_NON_SAP_REQUEST reqData = new SSO_NON_SAP_REQUEST();
                reqData.setXSYS("WMS");
                reqData.setXDATS(dateFormat.format(calendar.getTime()));
                reqData.setXTIMS(timeFormat.format(calendar.getTime()));
                reqData.setXROWS("1");
                reqData.setINT_SVC_NO(reqDto.getGUserNo());

                SSO_NON_SAP_EAIPortTypeProxy proxy = new SSO_NON_SAP_EAIPortTypeProxy();
                SSO_NON_SAP_RESPONSE response = proxy.GET_SSO_TICKET(reqData);
                String ssoId = "<SSOID>" + response.getSSO_TICKET() + "</SSOID>";
                resultMessage = resultMessage + ssoId + returnMessage;

            } catch (Exception e) {
                log.error("▶sso proxy 오류 발생 ");
                throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
            }
            //end-3. 전자결재용 SSO_ID 요청

            //4. 결과메시지(SSO ID) 리턴   
            return resultMessage;
        } //end..if (null != saveKitList) 
        
      /*  
      //2. 구성상품(자품목) 감모       
        tempDeleteReqEntity = ModelMapperUtil.map(reqDto, userContext, CmSyProcessTempAjEntity.class);
        tempDeleteReqEntity.setProcesstype(PROCESSTYPE_REQ_SUB);
        tempDeleteReqEntity.setSpid(reqDto.getGSpid());
        commonDao.delete(SERVICEID_TEMP_PREFIX +"deleteSyProcessTempAJ", tempDeleteReqEntity);
        if (null != saveList) {
            // kit 구성상품 임시테이블 데이터 생성
            insertList.clear(); 
            int chunkSize = 200;
            for (StMKitResT1Dto resDto : saveList) {
                CmSyProcessTempAjEntity entity = ModelMapperUtil.map(resDto, userContext, CmSyProcessTempAjEntity.class);
                entity.setProcesstype(PROCESSTYPE_REQ_SUB);
                entity.setStorerkey(resDto.getStorerkey());
                entity.setConfirmqty(resDto.getReqQty().multiply(BigDecimal.valueOf(-1.0)));
               
                //DCCODE|STORERKEY|ORGANIZE|AREA|ZONE|LOC|SKU|UOM|LOT|STOCKID|STOCKGRADE|STOCKTYPE|QTY|OPENQTY|QTYALLOCATED|QTYPICKED|CALLQTY|DURATION|REASONCODE|DISUSETYPE|IMPUTETYPE|PROCESSMAIN|COSTCD|CUSTKEY|KIT_SKU|PLANORDER|PLANDT
                //DCCODE|STORERKEY|ORGANIZE|AREA|ZONE|LOC|SKU|UOM|LOT|STOCKID|STOCKGRADE|STOCKTYPE|ORDERQTY|OPENQTY|PROCESSQTY|WORKQTY|CONFIRMQTY|MIXBOXKEY|TASKKEY|DELIVERYGROUP|WAVEKEY|INVOICENO|OTHER01|CUSTKEY|OTHER02|OTHER03|OTHER04"
                
                entity.setDccode(resDto.getDccode());
                entity.setOrganize(resDto.getOrganize());
                entity.setArea(resDto.getArea());
                entity.setZone(resDto.getZone());
                entity.setDocdt(resDto.getPlanDt());
                entity.setSlipdt(resDto.getPlanDt());
                entity.setLoc(resDto.getLoc());
                entity.setSku(resDto.getSku());
                entity.setKitSku(resDto.getKitSku());
                entity.setUom(resDto.getSkuUom());
                entity.setLot(resDto.getLot());
                entity.setStockid(resDto.getStockid());
                entity.setStockgrade(resDto.getStockgrade());
                entity.setOpenqty(resDto.getOpenqty());
                entity.setOrderqty(resDto.getReqQty().multiply(BigDecimal.valueOf(-1.0))); 
                entity.setQtyallocated(resDto.getQtyallocated());
                entity.setQtypicked(resDto.getQtypicked());
                entity.setCallqty(resDto.getCallqty());
                entity.setDuration(resDto.getDuration());
                entity.setReasoncode(resDto.getReasoncode());
                entity.setDisusetype(resDto.getDisusetype());
                entity.setImputetype(resDto.getImputetype());
                entity.setProcessmain(resDto.getProcessmain());
                entity.setCostcd(resDto.getCostcd());
                entity.setCustkey(resDto.getCustkey());  
                entity.setPlanorder(null);
                entity.setPlandt(resDto.getPlanDt());
                insertList.add(entity);
          
                // AJ임시 테이블에 저장 실행
                if (insertList.size() > chunkSize) {
                    commonDao.insert(SERVICEID_PREFIX + "insertSyProcessTempAJ", insertList);
                    insertList.clear(); // 다음 배치 준비  
                }
            }
            
            // AJ임시 테이블에 저장 실행
            if (insertList.size() > 0) {
                commonDao.insert(SERVICEID_PREFIX + "insertSyProcessTempAJ", insertList);
                insertList.clear(); // 다음 배치 준비  
            }            
        } 
        */
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
    
    /**
     * @description : 처리 임시테이블에 저장
     * @issues : <pre>
     * ----------------------------------------------------------- 
     * DATE AUTHOR MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.05 고혜미 생성 </pre>
     */
    public String saveSubItemsList03(StMKitReqDto paramDto) {
        StMKitReqDto reqDto = ModelMapperUtil.map(paramDto, StMKitReqDto.class);
        
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        
        List<StMKitResT1Dto> saveList = reqDto.getSaveList();
        List<StMKitResT1Dto> saveKitList = reqDto.getSaveKitList();
        List<CmSyProcessTempAjEntity> insertList = new ArrayList<CmSyProcessTempAjEntity>();

        log.info("saveList ==> {}", saveList);
        log.info("saveKitList ==> {}", saveKitList);
        
        // 모품목 임시테이블 삭제
        CmSyProcessTempAjEntity tempDeleteReqEntity = ModelMapperUtil.map(reqDto, userContext, CmSyProcessTempAjEntity.class);
        tempDeleteReqEntity.setProcesstype(PROCESSTYPE_REQ_PROC_MAIN);
        tempDeleteReqEntity.setSpid(reqDto.getGSpid());
        commonDao.delete(SERVICEID_TEMP_PREFIX +"deleteSyProcessTempAJ", tempDeleteReqEntity);
        
        //1. kit 상품(모품목) 역감모
        if (null != saveKitList) {
            for (StMKitResT1Dto kitDto : saveKitList) {
                // kit 상품 임시테이블 데이터 생성
                int chunkSize = 200;                    
                CmSyProcessTempAjEntity entity = ModelMapperUtil.map(kitDto, userContext, CmSyProcessTempAjEntity.class);
                
                entity.setProcesstype(PROCESSTYPE_REQ_PROC_MAIN);
                entity.setStorerkey(reqDto.getStorerkey());
                entity.setDccode(kitDto.getDccode());
                entity.setOrganize(kitDto.getDccode());
                entity.setDocdt(kitDto.getPlanDt());
                entity.setDocno(kitDto.getKitSku());
                entity.setDocline("001");
                entity.setSlipdt(kitDto.getPlanDt());
                entity.setSlipno(kitDto.getDocno());
                entity.setSlipline("001");
                entity.setOrderqty(kitDto.getConfirmqty());
                entity.setEtcqty1(new BigDecimal(0));
                entity.setSku(kitDto.getKitSku());
                entity.setKitSku(kitDto.getKitSku());
                entity.setSlipdt(kitDto.getPlanDt());
                entity.setUom(kitDto.getUom());
                insertList.add(entity);
          
                // AJ임시 테이블에 저장 실행
                if (insertList.size() > chunkSize) {
                    commonDao.insert(SERVICEID_PREFIX + "insertSyProcessTempAJ", insertList);
                    insertList.clear(); // 다음 배치 준비  
                }
            } //end.. for (StMKitResT1Dto kitDto : saveList) {
            
            // AJ임시 테이블에 저장 실행
            if (insertList.size() > 0) {
                commonDao.insert(SERVICEID_PREFIX + "insertSyProcessTempAJ", insertList);
                insertList.clear(); // 다음 배치 준비  
            }
        } //end..if (null != saveKitList) 
        
        //2. 구성상품(자품목) 감모       
        tempDeleteReqEntity = ModelMapperUtil.map(reqDto, userContext, CmSyProcessTempAjEntity.class);
        tempDeleteReqEntity.setProcesstype(PROCESSTYPE_REQ_PROC_SUB);
        tempDeleteReqEntity.setSpid(reqDto.getGSpid());
        commonDao.delete(SERVICEID_TEMP_PREFIX +"deleteSyProcessTempAJ", tempDeleteReqEntity);
        if (null != saveList) {
            // kit 구성상품 임시테이블 데이터 생성
            insertList.clear(); 
            int chunkSize = 200;
            for (StMKitResT1Dto resDto : saveList) {
                CmSyProcessTempAjEntity entity = ModelMapperUtil.map(resDto, userContext, CmSyProcessTempAjEntity.class);
                entity.setProcesstype(PROCESSTYPE_REQ_PROC_SUB);
                entity.setStorerkey(resDto.getStorerkey());
                entity.setConfirmqty(resDto.getReqQty().multiply(BigDecimal.valueOf(-1.0)));
               
                //DCCODE|STORERKEY|ORGANIZE|AREA|ZONE|LOC|SKU|UOM|LOT|STOCKID|STOCKGRADE|STOCKTYPE|QTY|OPENQTY|QTYALLOCATED|QTYPICKED|CALLQTY|DURATION|REASONCODE|DISUSETYPE|IMPUTETYPE|PROCESSMAIN|COSTCD|CUSTKEY|KIT_SKU|PLANORDER|PLANDT
                //DCCODE|STORERKEY|ORGANIZE|AREA|ZONE|LOC|SKU|UOM|LOT|STOCKID|STOCKGRADE|STOCKTYPE|ORDERQTY|OPENQTY|PROCESSQTY|WORKQTY|CONFIRMQTY|MIXBOXKEY|TASKKEY|DELIVERYGROUP|WAVEKEY|INVOICENO|OTHER01|CUSTKEY|OTHER02|OTHER03|OTHER04"
                
                entity.setDccode(resDto.getDccode());
                entity.setOrganize(resDto.getOrganize());
                entity.setArea(resDto.getArea());
                entity.setZone(resDto.getZone());
                entity.setDocdt(resDto.getPlanDt());
                entity.setLoc(resDto.getLoc());
                entity.setSku(resDto.getSku());
                entity.setKitSku(resDto.getKitSku());
                entity.setUom(resDto.getSkuUom());
                entity.setLot(resDto.getLot());
                entity.setStockid(resDto.getStockid());
                entity.setStockgrade(resDto.getStockgrade());
                entity.setOpenqty(resDto.getOpenqty());
                entity.setOrderqty(resDto.getReqQty().multiply(BigDecimal.valueOf(-1.0))); 
                entity.setQtyallocated(resDto.getQtyallocated());
                entity.setQtypicked(resDto.getQtypicked());
                entity.setCallqty(resDto.getCallqty());
                entity.setDuration(resDto.getDuration());
                entity.setReasoncode(resDto.getReasoncode());
                entity.setDisusetype(resDto.getDisusetype());
                entity.setImputetype(resDto.getImputetype());
                entity.setProcessmain(resDto.getProcessmain());
                entity.setCostcd(resDto.getCostcd());
                entity.setCustkey(resDto.getCustkey());  
                entity.setPlanorder(null);
                entity.setPlandt(resDto.getPlanDt());
                insertList.add(entity);
          
                // AJ임시 테이블에 저장 실행
                if (insertList.size() > chunkSize) {
                    commonDao.insert(SERVICEID_PREFIX + "insertSyProcessTempAJ", insertList);
                    insertList.clear(); // 다음 배치 준비  
                }
            }
            
            // AJ임시 테이블에 저장 실행
            if (insertList.size() > 0) {
                commonDao.insert(SERVICEID_PREFIX + "insertSyProcessTempAJ", insertList);
                insertList.clear(); // 다음 배치 준비  
            }            
        }
        
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }

    
    /**
     * @description : 처리 저장
     * @issues : <pre>
     * ----------------------------------------------------------- 
     * DATE AUTHOR MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.11.07 고혜미 생성 </pre>
     */
    public String saveMasterList03(StMKitReqDto paramDto) {
        StMKitReqDto reqDto = ModelMapperUtil.map(paramDto, StMKitReqDto.class);
        
        List<StMKitResT1Dto> saveList = reqDto.getSaveList();
        List<StMKitResT1Dto> saveKitList = reqDto.getSaveKitList();
        List<CmSyProcessTempAjEntity> insertList = new ArrayList<CmSyProcessTempAjEntity>();

        log.info("saveList ==> {}", saveList);
        log.info("saveKitList ==> {}", saveKitList);
        
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        

        

        if (null != saveKitList) {
            for (StMKitResT1Dto kitDto : saveKitList) {

              //20260111 공두경 KIT생산 내역 인터페이스 테이블 INSERT  
              StMKitIFEntity entity = ModelMapperUtil.map(kitDto, userContext, StMKitIFEntity.class);
              commonDao.insert(SERVICEID_PREFIX + "insertIF_ST_KIT_PLANDETAIL", entity);
              
              //1. 구성상품(자품목) 감모       
                //if (null != saveList && resultCode.equals("0")) {
                    reqDto.setProcesstype(PROCESSTYPE_REQ_PROC_SUB);
                    reqDto.setKitSku(kitDto.getKitSku());
                    reqDto.setPlanDt(kitDto.getPlanDt());
                    List<Map<String, String>> tempList = commonDao.selectList(SERVICEID_PREFIX + "getSyProcessTempAJ", reqDto);
                    
                    for (Map<String, String> obj : tempList) {
                        // PKG 파라마터 세팅 - 공통(1/4)
                        StMKitResT1Dto procedureDto = new StMKitResT1Dto();
                        ProcedureParametersFactory.initParamDto(reqDto, procedureDto, PAKAGE_NAME_REQUEST);
                        procedureDto.setAvc_DCCODE(reqDto.getFixdccode());
                        procedureDto.setAvc_COMMAND(AVC_COMMAND_CONFIRM);
                        
                        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>                
                        String[] keyList = new String[] {"PROCEDURE"
                                                        , "PROCESSTYPE"
                                                        , "PROCESSCREATOR"
                                                        , "SPID"
                                                        , "STOCKTRANSTYPE"
                                                        , "DOCDT"
                                                        , "SLIPDT"
                                                        , "IF_SEND_TYPE"
                                                        , "WORKPROCESSCODE"
                                                        , "OMS_FLAG"
                                                        , "SERIALKEY"
                                                        };
                        Object[] valueList = new Object[] {PAKAGE_NAME_REQUEST
                                                         , PROCESSTYPE_REQ_PROC_SUB
                                                         , reqDto.getGUserId()
                                                         , reqDto.getGSpid()
                                                         , STOCKTRANSTYPE_947
                                                         , obj.get("DOCDT")
                                                         , obj.get("DOCDT")
                                                         , IF_SEND_TYPE
                                                         , WORKPROCESSCODE
                                                         , OMS_FLAG
                                                         , obj.get("SERIALKEY")
                                                         };
                        procedureDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
                        
                        // KIT구성상품 감모(-) 프로시저 호출
                        int rv = cmCommonService.saveProcedure(procedureDto); 
                        log.info("rv->{}",rv);
                        
                        // 프로시저 OUT Parameter(3/4)
                        resultCode    = (String)procedureDto.getResultCode();
                        resultMessage = (String)procedureDto.getResultMessage();
                        
                        log.info("resultCode->{}",resultCode);
                        log.info("resultMessage->{}",resultMessage);
                        
                        // 프로시저 Exception 처리(4/4)
                        if (!resultCode.equals("0")){
                            log.error("▶KIT 처리 -> 자품목 재고 저장 오류 발생 ");
                            break;
                        //    throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
                        }
                    }//END..for (Map<String, String> obj : tempList)
                //}//END..if (null != saveList)
              //END..1. 구성상품(자품목) 감모 
                
              //2. kit 상품(모품목) 역감모
                if (resultCode.equals("0")) {
                    reqDto.setProcesstype(PROCESSTYPE_REQ_PROC_MAIN);
                    reqDto.setKitSku(kitDto.getKitSku());
                    reqDto.setPlanDt(kitDto.getPlanDt());
                    List<Map<String, String>> tempList2 = commonDao.selectList(SERVICEID_PREFIX + "getSyProcessTempAJ", reqDto);
                    
                    // PKG 파라마터 세팅 - 공통(1/4)
                    StMKitResT1Dto procedureDto = new StMKitResT1Dto();
                    ProcedureParametersFactory.initParamDto(reqDto, procedureDto, PAKAGE_NAME_REQUEST);
                    procedureDto.setAvc_DCCODE(reqDto.getFixdccode());
                    procedureDto.setAvc_COMMAND(AVC_COMMAND_CONFIRM);
                    
                    // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>                
                    String[] keyList = new String[] {"PROCEDURE"
                                                    , "PROCESSTYPE"
                                                    , "PROCESSCREATOR"
                                                    , "SPID"
                                                    , "STOCKTRANSTYPE"
                                                    , "DOCDT"
                                                    , "IF_SEND_TYPE"
                                                    , "WORKPROCESSCODE"
                                                    , "OMS_FLAG"
                                                    , "SERIALKEY"
                                                    };
                    Object[] valueList = new Object[] {PAKAGE_NAME_REQUEST
                                                     , PROCESSTYPE_REQ_PROC_MAIN
                                                     , reqDto.getGUserId()
                                                     , reqDto.getGSpid()
                                                     , STOCKTRANSTYPE_948
                                                     , saveKitList.get(0).getPlanDt()
                                                     , IF_SEND_TYPE
                                                     , WORKPROCESSCODE
                                                     , OMS_FLAG
                                                     , tempList2.get(0).get("SERIALKEY")
                                                     };
                    procedureDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
                    
                    // KIT상품 역감모(+) 프로시저 호출
                    int rv = cmCommonService.saveProcedure(procedureDto); 
                    log.info("rv->{}",rv);
                    
                    // 프로시저 OUT Parameter(3/4)
                    resultCode    = (String)procedureDto.getResultCode();
                    resultMessage = (String)procedureDto.getResultMessage();
                    
                    log.info("resultCode->{}",resultCode);
                    log.info("resultMessage->{}",resultMessage);
                    
                    // 프로시저 Exception 처리(4/4)
                    if (!resultCode.equals("0")){
                        log.error("▶KIT 처리 -> 모품목 재고 저장 오류 발생 ");
                        throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
                    }
                }
                //END..//2. kit 상품(모품목) 역감모
                
            }//END..for (StMKitResT1Dto kitDto : saveKitList) 
        }//END..if (null != saveKitList) 
        
        
        
        
        
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
	
	/**
	 * @description : 이체대상저장
	 * @issues : <pre>
	 * ----------------------------------------------------------- 
	 * DATE AUTHOR MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.11.07 고혜미 생성 </pre>
	 */
	public String saveMasterList01_ASIS(StMKitReqDto paramDto) {
	    StMKitReqDto reqDto = ModelMapperUtil.map(paramDto, StMKitReqDto.class);
	    
	    List<StMKitResT1Dto> saveList = reqDto.getSaveList();
	    List<StMKitResT1Dto> saveKitList = reqDto.getSaveKitList();
	    List<CmSyProcessTempAjEntity> insertList = new ArrayList<CmSyProcessTempAjEntity>();

	    log.info("saveList ==> {}", saveList);
	    log.info("saveKitList ==> {}", saveKitList);
	    
	    // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";

	    // kit 상품의 생산수량 저장
	    for (StMKitResT1Dto dto : saveList) {
	        var entity = ModelMapperUtil.map(dto, userContext, StMKitResT1Dto.class);
	        commonDao.selectOne(SERVICEID_PREFIX + "updateStKitPlan", entity);
	        commonDao.selectOne(SERVICEID_PREFIX + "saveStKitPlandetail", entity);
	    }
	    
	    // 임시테이블 삭제
	    CmSyProcessTempAjEntity tempDeleteReqEntity = ModelMapperUtil.map(reqDto, userContext, CmSyProcessTempAjEntity.class);
        tempDeleteReqEntity.setProcesstype(PROCESSTYPE_REQ_MAIN);
        tempDeleteReqEntity.setSpid(reqDto.getGSpid());
        commonDao.delete(SERVICEID_TEMP_PREFIX +"deleteSyProcessTempAJ", tempDeleteReqEntity);
	    
	    //1. kit 상품(모품목) 역감모
	    if (null != saveKitList) {
	        for (StMKitResT1Dto kitDto : saveKitList) {
//    	        String loc = (String) commonDao.selectOne(SERVICEID_PREFIX + "getLocationList", kitDto);
//    	        StMKitResT1Dto dto = saveKitList.get(0);
//    	        dto.setLoc(loc);
	        	StMKitResT1Dto dto = saveKitList.get(0);	        	
	        	dto.setLoc("STAGE");
    	        List<Object> zeroList = commonDao.selectList(SERVICEID_PREFIX + "getExistKitZeroList", dto);
    	        if (null == zeroList || zeroList.size() == 0) {
    	            // kit 상품 재고생성 전이면 Zero 재고 생성
    	            Calendar calendar = Calendar.getInstance();
    	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    	            String stockId = "C" + dateFormat.format(calendar.getTime());
    	            
    	            // PKG 파라마터 세팅 - 공통(1/4)
    	            StMKitResT1Dto procedureDto = new StMKitResT1Dto();
    	            ProcedureParametersFactory.initParamDto(dto, procedureDto, PAKAGE_NAME_CONVERT);
    	            procedureDto.setAvc_DCCODE(dto.getDccode());
    	            procedureDto.setAvc_COMMAND(AVC_COMMAND_CREATEZERO);
    	            
    	            // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>                
    	            String[] keyList = new String[] {"DCCODE"
    	                                            , "STORERKEY"
    	                                            , "ORGANIZE"
    	                                            , "SKU"
    	                                            , "LOC"
    	                                            , "STOCKID"
    	                                            , "STOCKGRADE"
    	                                            , "LOTTABLE01"};
    	            Object[] valueList = new Object[] {dto.getDccode()
    	                                             , dto.getStorerkey()
    	                                             , dto.getOrganize()
    	                                             , dto.getKitSku()
    	                                             , "STAGE"
    	                                             , stockId
    	                                             , "STD"
    	                                             , dto.getMixboxkey()};
    	            procedureDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
    	            
    	            // 제로재고 생성 프로시저 호출
    	            int rv = cmCommonService.saveProcedure(procedureDto); 
    	            log.info("rv->{}",rv);
    	            
    	            // 프로시저 OUT Parameter(3/4)
    	            resultCode    = (String)procedureDto.getResultCode();
    	            resultMessage = (String)procedureDto.getResultMessage();
    	            
    	            log.info("resultCode->{}",resultCode);
    	            log.info("resultMessage->{}",resultMessage);
    	            
    	            // 프로시저 Exception 처리(4/4)
    	            if (!resultCode.equals("0")){
    	                log.error("▶KIT 처리 -> 저장 오류 발생 ");
    	                throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
    	            }    	        
    	        }
    	        
    	        List<HashMap> headerZeroList = commonDao.selectList(SERVICEID_PREFIX + "getDataHeaderKitZeroList", dto);
    	        if (null != headerZeroList || headerZeroList.size() > 0) {
    	            // kit 상품 임시테이블 데이터 생성
    	            int chunkSize = 200;    	            
    	            CmSyProcessTempAjEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempAjEntity.class);
                    
                    entity.setProcesstype(PROCESSTYPE_REQ_MAIN);
                    entity.setStorerkey(dto.getStorerkey());
                    entity.setDccode(headerZeroList.get(0).get("DCCODE").toString());
                    entity.setDocdt(headerZeroList.get(0).get("PLAN_DT").toString());
                    entity.setArea((String)headerZeroList.get(0).get("AREA"));
                    entity.setOrganize((String)headerZeroList.get(0).get("ORGANIZE"));
                    entity.setSku((String)headerZeroList.get(0).get("SKU"));
                    entity.setKitSku((String)headerZeroList.get(0).get("SKU"));
                    entity.setStocktype((String)headerZeroList.get(0).get("STOCKTYPE"));
                    entity.setStocktypenm((String)headerZeroList.get(0).get("STOCKTYPENM"));
                    entity.setStockgrade((String)headerZeroList.get(0).get("STOCKGRADE"));
                    entity.setStockgradename((String)headerZeroList.get(0).get("STOCKGRADENAME"));
                    entity.setLoc((String)headerZeroList.get(0).get("LOC"));
                    entity.setLot((String)headerZeroList.get(0).get("LOT"));
                    entity.setProcessqty((BigDecimal)headerZeroList.get(0).get("QTYALLOCATED"));
                    entity.setWorkqty((BigDecimal)headerZeroList.get(0).get("QTYPICKED"));
                    entity.setOpenqty((BigDecimal)headerZeroList.get(0).get("OPENQTY"));
                    entity.setOrderqty((BigDecimal)headerZeroList.get(0).get("ORDERQTY"));
                    entity.setUom((String)headerZeroList.get(0).get("UOM")); 
                    entity.setCustkey((String)headerZeroList.get(0).get("CUSTKEY"));
                    entity.setCostcd((String)headerZeroList.get(0).get("RESP_DEPT_CD"));
                    insertList.add(entity);
              
                    // AJ임시 테이블에 저장 실행
                    if (insertList.size() > chunkSize) {
                        commonDao.insert(SERVICEID_PREFIX + "insertSyProcessTempAJ", insertList);
                        insertList.clear(); // 다음 배치 준비  
                    }
    	        }
	        } //end.. for (StMKitResT1Dto kitDto : saveList) {
	        
	        // AJ임시 테이블에 저장 실행
	        if (insertList.size() > 0) {
                commonDao.insert(SERVICEID_PREFIX + "insertSyProcessTempAJ", insertList);
                insertList.clear(); // 다음 배치 준비  
            }
	        
	        // PKG 파라마터 세팅 - 공통(1/4)
	        StMKitResT1Dto procedureDto = new StMKitResT1Dto();
            ProcedureParametersFactory.initParamDto(reqDto, procedureDto, PAKAGE_NAME_REQUEST);
            procedureDto.setAvc_DCCODE(reqDto.getFixdccode());
            procedureDto.setAvc_COMMAND(AVC_COMMAND_APPROVALREQ);
	        
	        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>                
            String[] keyList = new String[] {"PROCEDURE"
                                            , "PROCESSTYPE"
                                            , "PROCESSCREATOR"
                                            , "SPID"
                                            , "STOCKTRANSTYPE"
                                            , "DOCDT"
                                            , "IF_SEND_TYPE"
                                            , "WORKPROCESSCODE"
                                            , "OMS_FLAG"
                                            };
            Object[] valueList = new Object[] {PAKAGE_NAME_REQUEST
                                             , PROCESSTYPE_REQ_MAIN
                                             , reqDto.getGUserId()
                                             , reqDto.getGSpid()
                                             , STOCKTRANSTYPE_948
                                             , saveKitList.get(0).getPlanDt()
                                             , IF_SEND_TYPE
                                             , WORKPROCESSCODE
                                             , OMS_FLAG
                                             };
            procedureDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
            
            // KIT상품 역감모(+) 프로시저 호출
            int rv = cmCommonService.saveProcedure(procedureDto); 
            log.info("rv->{}",rv);
            
            // 프로시저 OUT Parameter(3/4)
            resultCode    = (String)procedureDto.getResultCode();
            resultMessage = (String)procedureDto.getResultMessage();
            
            log.info("resultCode->{}",resultCode);
            log.info("resultMessage->{}",resultMessage);
            
            // 프로시저 Exception 처리(4/4)
            if (!resultCode.equals("0")){
                log.error("▶KIT 처리 -> 모품목 재고 저장 오류 발생 ");
                throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
            }
	    } //end..if (null != saveKitList) 
	    
	    //2. 구성상품(자품목) 감모 	    
        tempDeleteReqEntity = ModelMapperUtil.map(reqDto, userContext, CmSyProcessTempAjEntity.class);
        tempDeleteReqEntity.setProcesstype(PROCESSTYPE_REQ_SUB);
        tempDeleteReqEntity.setSpid(reqDto.getGSpid());
        commonDao.delete(SERVICEID_TEMP_PREFIX +"deleteSyProcessTempAJ", tempDeleteReqEntity);
        if (null != saveList) {
            // kit 구성상품 임시테이블 데이터 생성
            insertList.clear(); 
            int chunkSize = 200;
            for (StMKitResT1Dto resDto : saveList) {
                CmSyProcessTempAjEntity entity = ModelMapperUtil.map(resDto, userContext, CmSyProcessTempAjEntity.class);
                entity.setProcesstype(PROCESSTYPE_REQ_SUB);
                entity.setStorerkey(resDto.getStorerkey());
                entity.setConfirmqty(resDto.getReqQty().multiply(BigDecimal.valueOf(-1.0)));
               
                //DCCODE|STORERKEY|ORGANIZE|AREA|ZONE|LOC|SKU|UOM|LOT|STOCKID|STOCKGRADE|STOCKTYPE|QTY|OPENQTY|QTYALLOCATED|QTYPICKED|CALLQTY|DURATION|REASONCODE|DISUSETYPE|IMPUTETYPE|PROCESSMAIN|COSTCD|CUSTKEY|KIT_SKU|PLANORDER|PLANDT
                //DCCODE|STORERKEY|ORGANIZE|AREA|ZONE|LOC|SKU|UOM|LOT|STOCKID|STOCKGRADE|STOCKTYPE|ORDERQTY|OPENQTY|PROCESSQTY|WORKQTY|CONFIRMQTY|MIXBOXKEY|TASKKEY|DELIVERYGROUP|WAVEKEY|INVOICENO|OTHER01|CUSTKEY|OTHER02|OTHER03|OTHER04"
                
                entity.setDccode(resDto.getDccode());
                entity.setOrganize(resDto.getOrganize());
                entity.setArea(resDto.getArea());
                entity.setZone(resDto.getZone());
                entity.setDocdt(resDto.getPlanDt());
                entity.setLoc(resDto.getLoc());
                entity.setSku(resDto.getSku());
                entity.setKitSku(resDto.getKitSku());
                entity.setUom(resDto.getSkuUom());
                entity.setLot(resDto.getLot());
                entity.setStockid(resDto.getStockid());
                entity.setStockgrade(resDto.getStockgrade());
                entity.setOpenqty(resDto.getOpenqty());
                entity.setOrderqty(resDto.getReqQty().multiply(BigDecimal.valueOf(-1.0))); 
                entity.setQtyallocated(resDto.getQtyallocated());
                entity.setQtypicked(resDto.getQtypicked());
                entity.setCallqty(resDto.getCallqty());
                entity.setDuration(resDto.getDuration());
                entity.setReasoncode(resDto.getReasoncode());
                entity.setDisusetype(resDto.getDisusetype());
                entity.setImputetype(resDto.getImputetype());
                entity.setProcessmain(resDto.getProcessmain());
                entity.setCostcd(resDto.getCostcd());
                entity.setCustkey(resDto.getCustkey());  
                entity.setPlanorder(null);
                entity.setPlandt(resDto.getPlanDt());
                insertList.add(entity);
          
                // AJ임시 테이블에 저장 실행
                if (insertList.size() > chunkSize) {
                    commonDao.insert(SERVICEID_PREFIX + "insertSyProcessTempAJ", insertList);
                    insertList.clear(); // 다음 배치 준비  
                }
	        }
            
            // AJ임시 테이블에 저장 실행
            if (insertList.size() > 0) {
                commonDao.insert(SERVICEID_PREFIX + "insertSyProcessTempAJ", insertList);
                insertList.clear(); // 다음 배치 준비  
            }
            
            reqDto.setProcesstype(PROCESSTYPE_REQ_SUB);
            List<Map<String, String>> tempList = commonDao.selectList(SERVICEID_PREFIX + "getSyProcessTempAJ", reqDto);
            
            for (Map<String, String> obj : tempList) {
                // PKG 파라마터 세팅 - 공통(1/4)
                StMKitResT1Dto procedureDto = new StMKitResT1Dto();
                ProcedureParametersFactory.initParamDto(reqDto, procedureDto, PAKAGE_NAME_REQUEST);
                procedureDto.setAvc_DCCODE(reqDto.getFixdccode());
                procedureDto.setAvc_COMMAND(AVC_COMMAND_APPROVALREQ);
                
                // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>                
                String[] keyList = new String[] {"PROCEDURE"
                                                , "PROCESSTYPE"
                                                , "PROCESSCREATOR"
                                                , "SPID"
                                                , "STOCKTRANSTYPE"
                                                , "DOCDT"
                                                , "IF_SEND_TYPE"
                                                , "WORKPROCESSCODE"
                                                , "OMS_FLAG"
                                                , "SERIALKEY"
                                                };
                Object[] valueList = new Object[] {PAKAGE_NAME_REQUEST
                                                 , PROCESSTYPE_REQ_SUB
                                                 , reqDto.getGUserId()
                                                 , reqDto.getGSpid()
                                                 , STOCKTRANSTYPE_947
                                                 , null
                                                 , IF_SEND_TYPE
                                                 , WORKPROCESSCODE
                                                 , OMS_FLAG
                                                 , obj.get("SERIALKEY")
                                                 };
                procedureDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
                
                // KIT구성상품 감모(-) 프로시저 호출
                int rv = cmCommonService.saveProcedure(procedureDto); 
                log.info("rv->{}",rv);
                
                // 프로시저 OUT Parameter(3/4)
                resultCode    = (String)procedureDto.getResultCode();
                resultMessage = (String)procedureDto.getResultMessage();
                
                log.info("resultCode->{}",resultCode);
                log.info("resultMessage->{}",resultMessage);
                
                // 프로시저 Exception 처리(4/4)
                if (!resultCode.equals("0")){
                    log.error("▶KIT 처리 -> 자품목 재고 저장 오류 발생 ");
                //    throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
                }
            }
	    }
	    

        
	    return CanalFrameConstants.MSG_COM_SUC_CODE;
	}


	public static boolean isNull(String str) {		 
		return str == null || str.trim().isEmpty();
	}
	
    /**
     * @description : 전자결재탭 저장
     * @issues : <pre>
     * ----------------------------------------------------------- 
     * DATE AUTHOR MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2026.01.21 공두경 생성 </pre>
     */
    public String saveMasterList05(StMKitReqDto paramDto) {
        StMKitReqDto reqDto = ModelMapperUtil.map(paramDto, StMKitReqDto.class);
        
        List<StMKitResT1Dto> saveList = reqDto.getSaveList();

        log.info("saveList ==> {}", saveList);
        
        // 1. DOCNO를 Key로 하여 리스트를 그룹화합니다.
        Map<String, List<StMKitResT1Dto>> groupedByDocNo = saveList.stream()
            .collect(Collectors.groupingBy(StMKitResT1Dto::getDocno));
        
        // 2. 그룹화된 Map을 순회하며 동일한 DOCNO를 가진 리스트별로 로직을 수행합니다.
        groupedByDocNo.forEach((docno, group) -> {
            System.out.println("현재 처리 중인 DOCNO: " + docno);
            
            if(!isNull(docno)) {
	            // 이 블록 안에서 group(List<StMKitResT1Dto>)은 모두 같은 DOCNO를 가집니다.
            	// 프로시저 에러코드 및 메세지
                String resultCode = "";
                String resultMessage  = "";
	            for (StMKitResT1Dto dto : group) {
	            	ProcedureParametersFactory.initParamDto(reqDto, dto, PAKAGE_NAME_REQUEST_DEL);
	            	
	            	dto.setAvc_DCCODE(reqDto.getFixdccode());
	            	dto.setAvc_COMMAND(AVC_COMMAND_APPROVAL_CANCEL);
	            	
	            	// PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>                
                    String[] keyList = new String[] { "DCCODE"
                                                    , "STORERKEY"
                                                    , "ORGANIZE"
                                                    , "AREA"
                                                    , "DOCDT"
                                                    , "DOCNO"
                                                    , "DOCLINE"
                                                    , "SLIPDT"
                                                    , "SLIPNO"
                                                    , "SLIPLINE"
                                                    , "SLIPTYPE"
                                                    , "ORDERTYPE"
                                                    , "SKU"
                                                    , "UOM"
                                                    , "TRANQTY"
                                                    , "STOCKID"
                                                    , "STOCKGRADE"
                                                    , "LOC"
                                                    , "LOT"
                                                    , "IOTYPE"
                                                    };
                    Object[] valueList = new Object[] {dto.getDccode()
                                                     , dto.getStorerkey()
                                                     , dto.getOrganize()
                                                     , dto.getArea()
                                                     , dto.getDocdt()
                                                     , dto.getDocno()
                                                     , dto.getDocline()
                                                     , dto.getSlipdt()
                                                     , dto.getSlipno()
                                                     , dto.getSlipline()
                                                     , dto.getSliptype()
                                                     , dto.getOrdertype()
                                                     , dto.getKitSku()
                                                     , dto.getKitUom()
                                                     , dto.getTranqty()
                                                     , dto.getKitStockid()
                                                     , dto.getKitStockgrade()
                                                     , dto.getKitLoc()
                                                     , dto.getKitLot()
                                                     , dto.getIotype()
                                                     };
                    dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
                    
                    // KIT구성상품 감모(-) 프로시저 호출
                    int rv = cmCommonService.saveProcedure(dto); 
                    log.info("rv->{}",rv);
                    
                    // 프로시저 OUT Parameter(3/4)
                    resultCode    = (String)dto.getResultCode();
                    resultMessage = (String)dto.getResultMessage();
                    
                    log.info("resultCode->{}",resultCode);
                    log.info("resultMessage->{}",resultMessage);
                    
                    // 프로시저 Exception 처리(4/4)
                    if (!resultCode.equals("0")){
                        log.error("▶KIT 생산 삭제 -> 생산 삭제 오류 발생 ");
                        break;
                        //throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
                    }
	            }
	            
	            // 동일 DOCNO 그룹 처리가 끝난 후 수행할 로직 (예: 마스터 테이블 업데이트 등)
            }
        });
        
        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }
	

}
