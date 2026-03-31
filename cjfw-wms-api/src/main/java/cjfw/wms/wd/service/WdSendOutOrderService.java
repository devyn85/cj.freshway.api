package cjfw.wms.wd.service;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

import cjfw.core.common.CanalFrameConstants;
import cjfw.core.dataaccess.CommonDao;
import cjfw.core.exception.UserHandleException;
import cjfw.core.model.UserContext;
import cjfw.core.utility.MessageUtil;
import cjfw.core.utility.ModelMapperUtil;
import cjfw.wms.ApiApplication;
import cjfw.wms.cm.entity.CmSyProcessTempStEntity;
import cjfw.wms.cm.entity.CmSyProcessTempWdEntity;
import cjfw.wms.cm.service.CmCommonService;
import cjfw.wms.cm.utility.ProcedureParametersFactory;
import cjfw.wms.wd.dto.WdSendOutOrderPrintResDto;
import cjfw.wms.wd.dto.WdSendOutOrderProcedureResDto;
import cjfw.wms.wd.dto.WdSendOutOrderReqDto;
import cjfw.wms.wd.dto.WdSendOutOrderResDto;
import cjfw.wms.wd.entity.WdSendOutOrderEntity;
import cjfw.wms.webservice.sendOutOrder.DT_COM1810_FS;
import cjfw.wms.webservice.sendOutOrder.DT_COM1810_FST_PARAM;
import cjfw.wms.webservice.sendOutOrder.DT_COM1810_FS_response;
import cjfw.wms.webservice.sendOutOrder.SI_COM1810_FS_SOProxy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Copyright 2025. CJ Freshway Co. all rights reserved. 
 *
 * @author : KimSunHo(sunhokim6229@cj.net) 
 * @date : 2025.06.19 
 * @description : 외부비축출고지시서 Service Class 
 * @issues : 
 * ----------------------------------------------------------- 
 * DATE          AUTHOR                  MAJOR_ISSUE 
 * ----------------------------------------------------------- 
 * 2025.06.19    KimSunHo(sunhokim6229@cj.net)   생성
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WdSendOutOrderService {

    private final ApiApplication apiApplication;

	/**
	 * Use this prefix to explicitly indicate a workspace name with a query id.
	 * @return .sqlx returns the location
	 */
	private transient static final String SERVICEID_PREFIX = "wdSendOutOrderService.";	
	private transient static final String SERVICEID_TEMP_PREFIX = "cmTempTableService.";   
	private transient static final String PAKAGE_NAME = "SPWD_SENDOUTORDER";	
	private transient static final String PAKAGE_NAME2 = "SPAJ_REQUEST";
	private transient static final String PROCESSTYPE = "WD_SENDOUTORDER";
	private transient static final String PROCEDURE_NAME_CANCEL = "APPROVAL_CANCELDC";
	 private transient static final String TEMPTABLETYPE = "WD"; 
	
	private final CommonDao commonDao;
	private final CmCommonService cmCommonService;
	private final UserContext userContext;

	/**
	 * @description : 오더있는 출고지시서 목록 조회
	 * @issues :<pre> 
	 * ----------------------------------------------------------- 
	 * DATE       AUTHOR                MAJOR_ISSUE 
	 * ----------------------------------------------------------- 
	 * 2025.06.19    KimSunHo(sunhokim6229@cj.net)   생성
	 */
	public List<WdSendOutOrderResDto> getDatalistOrder(WdSendOutOrderReqDto wdSendOutOrderReqDto) {
		return commonDao.selectList(SERVICEID_PREFIX + "getDatalistOrder", wdSendOutOrderReqDto);
	}
	
	/**
     * @description : 오더없는 출고지시서 목록 조회
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.06.19    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public List<WdSendOutOrderResDto> getDatalistNotOrder(WdSendOutOrderReqDto wdSendOutOrderReqDto) {
        return commonDao.selectList(SERVICEID_PREFIX + "getDatalistNotOrder", wdSendOutOrderReqDto);
    }	

    /**
     * @throws RemoteException 
     * @description : 수기출고 삭제
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.06.19    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public String deleteOrder(WdSendOutOrderReqDto paramDto) throws RemoteException {
        // 프로시저 에러코드 및 메세지
        String resultCode = "";
        String resultMessage  = "";
        
        // 파라미터 위변조 적용(reqDto->paramDto)
        WdSendOutOrderReqDto reqDto = ModelMapperUtil.map(paramDto, WdSendOutOrderReqDto.class);
        
        List<WdSendOutOrderResDto> saveList = reqDto.getSaveList(); // 저장리스트
        String avc_DCCODE = reqDto.getAvc_DCCODE(); // 센터코드
        
        log.info("▶saveList.size->{}",saveList);
        log.info("▶avc_DCCODE->{}",avc_DCCODE);
        
        if (null != saveList) {
            for (WdSendOutOrderResDto dto : saveList) { 
                
                if ("AJ".equals(dto.getDoctype())) {
                    // PKG 파라마터 세팅 - 공통(1/4)
                    ProcedureParametersFactory.initParamDto(reqDto, dto, PAKAGE_NAME);
                    dto.setAvc_DCCODE(reqDto.getFixdccode());
                    
                    // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>                
                    String[] keyList = { "DCCODE"
                                        , "STORERKEY"
                                        , "CUSTKEY"
                                        , "DOCTYPE"
                                        , "DOCNO"
                                        , "DOCLINE"};
                    Object[] valueList = { dto.getDccode()
                                        , dto.getStorerkey()
                                        , dto.getCustkey()
                                        , dto.getDoctype()
                                        , dto.getDocno()
                                        , dto.getDocline()};
                    dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
                    
                    int rv = cmCommonService.saveProcedure(dto); 
                    log.info("rv->{}",rv);
                    
                    // 프로시저 OUT Parameter(3/4)
                    resultCode    = (String)dto.getResultCode();
                    resultMessage = (String)dto.getResultMessage();
                    
                    log.info("resultCode->{}",resultCode);
                    log.info("resultMessage->{}",resultMessage);
                } else {                    
                    SI_COM1810_FS_SOProxy proxy = new SI_COM1810_FS_SOProxy();
                    
                    String result = null;
                    String resultMsg = null;
                    
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                    SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmSS");
                    
                    DT_COM1810_FS  outOrder = new DT_COM1810_FS();
                    outOrder.setXSYS("WMS");
                    outOrder.setXDATS(dateFormat.format(calendar.getTime()));
                    outOrder.setXTIMS(timeFormat.format(calendar.getTime()));
                    outOrder.setXROWS("1");
                    
                    DT_COM1810_FST_PARAM[] paramList = new DT_COM1810_FST_PARAM[1];
                    DT_COM1810_FST_PARAM   param = new DT_COM1810_FST_PARAM();
                    param.setCO_ID(dto.getStorerkey());
                    param.setPUR_REQ_NO(dto.getDocno());
                    param.setGD_REQ_SEQ(dto.getDocline());
                    param.setCUST_ID(dto.getCustkey());
                    param.setSALE_UNPRC("-1");
                    param.setJOB_DIV_CD("D");
                    
                    paramList[0] = param;
                    outOrder.setT_PARAM(paramList);
                    DT_COM1810_FS_response response = null;
                    try {
                         response = proxy.SI_COM1810_FS_SO(outOrder);
                    } catch (Exception e) {
                        log.error("▶외부비축출고지시서 수기출고 삭제 연동 오류", e);
                        throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"수기출고 삭제"}) + e.getMessage() );
                    }
                    
                    result = response.getXSTAT();
                    resultMsg = response.getXMSGS();
                    
                    if ("S".equals(result)){
                        // PKG 파라마터 세팅 - 공통(1/4)
                        ProcedureParametersFactory.initParamDto(reqDto, dto, PAKAGE_NAME);
                        dto.setAvc_DCCODE(reqDto.getFixdccode());
                        
                        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>                
                        String[] keyList = { "DCCODE"
                                            , "STORERKEY"
                                            , "CUSTKEY"
                                            , "DOCTYPE"
                                            , "DOCNO"
                                            , "DOCLINE"};
                        Object[] valueList = { dto.getDccode()
                                             , dto.getStorerkey()
                                             , dto.getCustkey()
                                             , dto.getDoctype()
                                             , dto.getDocno()
                                             , dto.getDocline()};
                        dto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
                        
                        int rv = cmCommonService.saveProcedure(dto); 
                        log.info("rv->{}",rv);
                        
                        // 프로시저 OUT Parameter(3/4)
                        resultCode    = (String)dto.getResultCode();
                        resultMessage = (String)dto.getResultMessage();
                        
                        log.info("resultCode->{}",resultCode);
                        log.info("resultMessage->{}",resultMessage);
                        
                        // 프로시저 Exception 처리(4/4)
                        if(!"0".equals(resultCode)){
                            log.error("▶외부비축출고지시서 -> 수기출고 삭제 오류 발생 ");
                            throw new UserHandleException( MessageUtil.getMessage("MSG_COM_ERR_055",new String[]{"수기출고 삭제"}) + resultMessage );
                        }  
                    } else {
                        throw new UserHandleException(resultMsg);
                    }
                    
                }                
                
                //자소오더 취소 시, 감모내역 연동 자동 삭제
                String checkyn = commonDao.selectOne(SERVICEID_PREFIX + "checkdeleteyn", dto);
                
                if ("01".equals(checkyn)) {
                    WdSendOutOrderProcedureResDto procDto = commonDao.selectOne(SERVICEID_PREFIX + "getParameterForProcedure", dto);
                    
                    if (procDto != null) {                    
                        // PKG 파라마터 세팅 - 공통(1/4)                    
                        ProcedureParametersFactory.initParamDto(reqDto, procDto, PAKAGE_NAME2);
                        procDto.setAvc_DCCODE(reqDto.getFixdccode());
                        procDto.setAvc_COMMAND(PROCEDURE_NAME_CANCEL);
                        
                        // PKG 파라마터 세팅 - 업무(2/4) -> <STORERKEY>FW00</STORERKEY><DCCODE>2600</DCCODE>                
                        String[] keyList = {"DCCODE"
                                            , "STORERKEY"
                                            , "ORGANIZE"
                                            , "AREA"
                                            , "DOCDT"
                                            , "DOCNO"
                                            , "DOCLINE"
                                            , "SLIPDT"
                                            , "SLIPNO"
                                            , "SLIPLINE"
                                            , "ORDERTYPE"
                                            , "SLIPTYPE"
                                            , "SKU"
                                            , "UOM"
                                            , "TRANQTY"
                                            , "STOCKID"
                                            , "STOCKGRADE"
                                            , "LOC"
                                            , "LOT"
                                            , "IOTYPE"
                                            };
                        Object[] valueList = {procDto.getDccode()
                                            , procDto.getStorerkey()
                                            , procDto.getOrganize()
                                            , procDto.getArea()
                                            , procDto.getDocdt() 
                                            , procDto.getDocno()
                                            , procDto.getDocline()
                                            , procDto.getSlipdt()
                                            , procDto.getSlipno()
                                            , procDto.getSlipline()
                                            , procDto.getOrdertype()
                                            , procDto.getSliptype()
                                            , procDto.getSku()
                                            , procDto.getUom()
                                            , procDto.getTranqty()
                                            , procDto.getStockid()
                                            , procDto.getStockgrade()
                                            , procDto.getLoc()
                                            , procDto.getLot()
                                            , procDto.getIotype()
                                            };
                        procDto.setAvc_REQUESTMSG(ProcedureParametersFactory.makeRequestMsgXml(keyList, valueList));
                        
                        int rv = cmCommonService.saveProcedure(procDto); 
                        log.info("rv->{}",rv);
                        
                        // 프로시저 OUT Parameter(3/4)
                        resultCode    = (String)procDto.getResultCode();
                        resultMessage = (String)procDto.getResultMessage();
                        
                        log.info("resultCode->{}",resultCode);
                        log.info("resultMessage->{}",resultMessage);
                        
                        // 프로시저 Exception 처리(4/4)
                        if(!"0".equals(resultCode)){
                            log.error("▶외부비축출고지시서 - 수기출고 삭제 감모내역 연동 자동 삭제 -> 오류 발생 ");
                            throw new UserHandleException(""+"에러코드 : "+ resultCode + ", 에러메세지 : " + resultMessage); // {0} 처리 시 문제가 발생했습니다.
                        }              
                    }
                }
            }            
        }

        return CanalFrameConstants.MSG_COM_SUC_CODE;
    }   
    
    /**
     * @throws RemoteException 
     * @description : 출고지시서 인쇄
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.06.19    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public WdSendOutOrderPrintResDto printMasterList(WdSendOutOrderReqDto paramDto) throws RemoteException {
        // 결과반환용 DTO
        WdSendOutOrderPrintResDto resultDto = new WdSendOutOrderPrintResDto();
        
        // 파라미터 위변조 적용(reqDto->paramDto)
        WdSendOutOrderReqDto reqDto = ModelMapperUtil.map(paramDto, WdSendOutOrderReqDto.class);
        
        List<WdSendOutOrderResDto> saveList = reqDto.getSaveList(); // 저장리스트
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
            List<CmSyProcessTempWdEntity> insertList = new ArrayList<CmSyProcessTempWdEntity>();

            for (int i = 0; i < saveList.size(); i++) {
                WdSendOutOrderResDto dto = saveList.get(i);
                CmSyProcessTempWdEntity entity = ModelMapperUtil.map(dto, userContext, CmSyProcessTempWdEntity.class);
                //ProcedureParametersFactory.initParamDto(reqDto, entity, PAKAGE_NAME);
                
                entity.setProcesstype(PROCESSTYPE);
                entity.setTemptabletype(TEMPTABLETYPE);
                entity.setStorerkey(dto.getStorerkey());
                entity.setDccode(dto.getDccode());
                entity.setOrganize(dto.getOrganize());
                entity.setDocno(dto.getDocno());
                entity.setDocline(dto.getDocline());
                entity.setSlipno(dto.getSlipno());
                entity.setSlipline(dto.getSlipline());
                entity.setSlipdt(dto.getSlipdt());
                entity.setUom(dto.getStoreruom()); 
                entity.setOrdertype(dto.getCheckyn());
                entity.setPrintType(reqDto.getPrintType());
                insertList.add(entity);
                // 200개마다 혹은 마지막 루프일 때 insert
                if (insertList.size() == chunkSize || i == saveList.size() -1) {
                    commonDao.insert(SERVICEID_PREFIX + "insertSyProcessTemp" + TEMPTABLETYPE, insertList);
                    insertList.clear(); // 다음 배치 준비
                }
                entity.setDocName(dto.getDocName());
//                commonDao.insert(SERVICEID_PREFIX + "insertPrintHistory", entity);
            }
                        
            /*2. 화면 기입 메모 저장*/
            for (int i = 0; i < saveList.size(); i++) {
                WdSendOutOrderResDto dto = saveList.get(i);
                WdSendOutOrderEntity entity = ModelMapperUtil.map(dto, userContext, WdSendOutOrderEntity.class);
                commonDao.update(SERVICEID_PREFIX + "mergeSendoutOrderMemo", entity);
            }
            
            /*3. 인쇄용 조회*/
            List<WdSendOutOrderPrintResDto> outResult = null;            
            reqDto.setProcesstype(PROCESSTYPE);

            String[] columns = {"userCotelNo","userHandphoneNo"};           
            
            if ("ORDER".equals(reqDto.getInstructtype())){
                outResult = commonDao.selectList(SERVICEID_PREFIX + "getDataPrintListOrder", reqDto);
                //outResult = DamoScpDbUtil.damoScpDecB64(commonDao.selectList(SERVICEID_PREFIX + "getDataPrintListOrder", reqDto), columns);
            }else if("NOTORDER".equals(reqDto.getInstructtype())) {
                outResult = commonDao.selectList(SERVICEID_PREFIX + "getDataPrintListNotOrder", reqDto);
                //outResult = DamoScpDbUtil.damoScpDecB64(commonDao.selectList(SERVICEID_PREFIX + "getDataPrintListNotOrder", reqDto), columns);
            }else if("RETURN".equals(reqDto.getInstructtype())) {
                outResult = commonDao.selectList(SERVICEID_PREFIX + "getDataPrintListReturn", reqDto);
                //outResult = DamoScpDbUtil.damoScpDecB64(commonDao.selectList(SERVICEID_PREFIX + "getDataPrintListReturn", reqDto), columns);
            }
            
            resultDto.setReportHeader(outResult);
        }
        
        return resultDto;
    }   
    
    /**
     * @throws RemoteException 
     * @description : 출고지시서 인쇄 로그 저장
     * @issues :<pre> 
     * ----------------------------------------------------------- 
     * DATE       AUTHOR                MAJOR_ISSUE 
     * ----------------------------------------------------------- 
     * 2025.06.19    KimSunHo(sunhokim6229@cj.net)   생성
     */
    public String savePrintHistory(WdSendOutOrderReqDto paramDto) {
        List<WdSendOutOrderResDto> saveList = paramDto.getSaveList(); // 저장리스트
        if(saveList != null) {
            for(var data : saveList) {
                CmSyProcessTempWdEntity entity = ModelMapperUtil.map(data, userContext, CmSyProcessTempWdEntity.class);
                entity.setPrintType(paramDto.getPrintType());
                entity.setDocName(paramDto.getDocName());
                commonDao.insert(SERVICEID_PREFIX + "insertPrintHistory", entity);
            }
        }

       	return CanalFrameConstants.MSG_COM_SUC_CODE;
     }
    
}
